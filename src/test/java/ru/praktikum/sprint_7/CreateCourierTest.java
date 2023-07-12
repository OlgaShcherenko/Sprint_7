package ru.praktikum.sprint_7;

import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Test;
import ru.praktikum.sprint_7.clients.CourierClient;
import ru.praktikum.sprint_7.dataprovider.CourierProvider;
import ru.praktikum.sprint_7.pojo.CreateCourierRequest;
import ru.praktikum.sprint_7.pojo.LoginCourierRequest;

public class CreateCourierTest {
    private CourierClient courierClient = new CourierClient();
    private Integer id;

    @Test
    @DisplayName("Successful courier creation")
    public void newCourierShouldBeCreated() {
        CreateCourierRequest createCourierRequest = CourierProvider.getRandomCreateCourierRequest();
        courierClient.create(createCourierRequest)
                .statusCode(201)
                .body("ok", Matchers.equalTo(true));

        LoginCourierRequest loginCourierRequest = LoginCourierRequest.fromAll(createCourierRequest);
        id = courierClient.login(loginCourierRequest).extract().jsonPath().get("id");
    }

    @Test
    @DisplayName("Successful courier creation without firstName")
    public void newCourierShouldBeCreatedWithoutFirstName() {
        CreateCourierRequest createCourierRequest = CourierProvider.getRandomCreateCourierRequestWithoutFirstName();
        courierClient.create(createCourierRequest)
                .statusCode(201)
                .body("ok", Matchers.equalTo(true));

        LoginCourierRequest loginCourierRequest = LoginCourierRequest.fromAll(createCourierRequest);
        id = courierClient.login(loginCourierRequest).extract().jsonPath().get("id");
    }

    @Test
    @DisplayName("Error creating courier without login")
    public void newCourierShouldNotBeCreatedWithoutLogin() {
        CreateCourierRequest createCourierRequest = CourierProvider.getRandomCreateCourierRequestWithoutLogin();
        courierClient.create(createCourierRequest)
                .statusCode(400)
                .body("message", Matchers.equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Error creating courier without password")
    public void newCourierShouldNotBeCreatedWithoutPassword() {
        CreateCourierRequest createCourierRequest = CourierProvider.getRandomCreateCourierRequestWithoutPassword();
        courierClient.create(createCourierRequest)
                .statusCode(400)
                .body("message", Matchers.equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Error creating two identical couriers")
    public void existingCourierShouldNotBeCreated() {
        CreateCourierRequest createCourierRequest = CourierProvider.getRandomCreateCourierRequest();
        courierClient.create(createCourierRequest);

        LoginCourierRequest loginCourierRequest = LoginCourierRequest.fromAll(createCourierRequest);
        id = courierClient.login(loginCourierRequest).extract().jsonPath().get("id");

        courierClient.create(createCourierRequest)
                .statusCode(409)
                .body("message", Matchers.equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Test
    @DisplayName("Error creating a courier with a login that is already taken")
    public void newCourierShouldNotBeCreatedWithNotUniqueLogin() {
        CreateCourierRequest firstCreateCourierRequest = CourierProvider.getRandomCreateCourierRequest();
        CreateCourierRequest secondCreateCourierRequest = CourierProvider.getRandomCreateCourierRequest();
        secondCreateCourierRequest.setLogin(firstCreateCourierRequest.getLogin());

        courierClient.create(firstCreateCourierRequest);

        LoginCourierRequest loginCourierRequest = LoginCourierRequest.fromAll(firstCreateCourierRequest);
        id = courierClient.login(loginCourierRequest).extract().jsonPath().get("id");

        courierClient.create(secondCreateCourierRequest)
                .statusCode(409)
                .body("message", Matchers.equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @After
    public void tearDown() {
        if (id != null) {
            courierClient.delete(id)
                    .statusCode(200);
        }
    }
}
