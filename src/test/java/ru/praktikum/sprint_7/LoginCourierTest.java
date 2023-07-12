package ru.praktikum.sprint_7;

import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Test;
import ru.praktikum.sprint_7.clients.CourierClient;
import ru.praktikum.sprint_7.dataprovider.CourierProvider;
import ru.praktikum.sprint_7.pojo.CreateCourierRequest;
import ru.praktikum.sprint_7.pojo.LoginCourierRequest;

public class LoginCourierTest {
    private CourierClient courierClient = new CourierClient();
    private Integer id;


    @Test
    @DisplayName("Successful courier authorization")
    public void courierShouldBeAuthorized() {
        CreateCourierRequest createCourierRequest = CourierProvider.getRandomCreateCourierRequest();
        courierClient.create(createCourierRequest);

        LoginCourierRequest loginCourierRequest = LoginCourierRequest.fromAll(createCourierRequest);
        id = courierClient.login(loginCourierRequest)
                .statusCode(200)
                .extract().jsonPath().get("id");
    }

    @Test
    @DisplayName("Authorization error without login")
    public void courierShouldNotBeAuthorizedWithoutLogin() {
        CreateCourierRequest createCourierRequest = CourierProvider.getRandomCreateCourierRequest();
        courierClient.create(createCourierRequest);

        LoginCourierRequest correctLoginCourierRequest = LoginCourierRequest.fromAll(createCourierRequest);
        id = courierClient.login(correctLoginCourierRequest).extract().jsonPath().get("id");

        LoginCourierRequest incorrectLoginCourierRequest = LoginCourierRequest.fromWithoutLogin(createCourierRequest);
        courierClient.login(incorrectLoginCourierRequest)
                .statusCode(400)
                .body("message", Matchers.equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Authorization error without password")
    public void courierShouldNotBeAuthorizedWithoutPassword() {
        CreateCourierRequest createCourierRequest = CourierProvider.getRandomCreateCourierRequest();
        courierClient.create(createCourierRequest);

        LoginCourierRequest correctLoginCourierRequest = LoginCourierRequest.fromAll(createCourierRequest);
        id = courierClient.login(correctLoginCourierRequest).extract().jsonPath().get("id");

        LoginCourierRequest incorrectLoginCourierRequest = LoginCourierRequest.fromWithoutPassword(createCourierRequest);
        courierClient.login(incorrectLoginCourierRequest)
                .statusCode(400)
                .body("message", Matchers.equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Authorization error with incorrect login")
    public void courierShouldNotBeAuthorizedWithInvalidLogin() {
        CreateCourierRequest createCourierRequest = CourierProvider.getRandomCreateCourierRequest();
        courierClient.create(createCourierRequest);

        LoginCourierRequest loginCourierRequest = LoginCourierRequest.fromAll(createCourierRequest);
        id = courierClient.login(loginCourierRequest).extract().jsonPath().get("id");

        loginCourierRequest.setLogin(RandomStringUtils.randomAlphabetic(5));
        courierClient.login(loginCourierRequest)
                .statusCode(404)
                .body("message", Matchers.equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Authorization error with incorrect password")
    public void courierShouldNotBeAuthorizedWithInvalidPassword() {
        CreateCourierRequest createCourierRequest = CourierProvider.getRandomCreateCourierRequest();
        courierClient.create(createCourierRequest);

        LoginCourierRequest loginCourierRequest = LoginCourierRequest.fromAll(createCourierRequest);
        id = courierClient.login(loginCourierRequest).extract().jsonPath().get("id");

        loginCourierRequest.setPassword(RandomStringUtils.randomAlphabetic(5));
        courierClient.login(loginCourierRequest)
                .statusCode(404)
                .body("message", Matchers.equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Authorization error under a non-existent user")
    public void notCreatedCourierNotBeAuthorized() {
        CreateCourierRequest createCourierRequest = CourierProvider.getRandomCreateCourierRequest();
        LoginCourierRequest loginCourierRequest = LoginCourierRequest.fromAll(createCourierRequest);
        courierClient.login(loginCourierRequest)
                .statusCode(404)
                .body("message", Matchers.equalTo("Учетная запись не найдена"));
    }

    @After
    public void tearDown() {
        if (id != null) {
            courierClient.delete(id)
                    .statusCode(200);
        }
    }
}
