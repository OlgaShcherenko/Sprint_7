package ru.praktikum.sprint_7.clients;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.praktikum.sprint_7.pojo.CreateCourierRequest;
import ru.praktikum.sprint_7.pojo.LoginCourierRequest;

import static io.restassured.RestAssured.given;

public class CourierClient extends BaseClient {
    public static final String CREATE_COURIER_PATH = "/api/v1/courier";
    public static final String LOGIN_COURIER_PATH = "/api/v1/courier/login";
    public static final String DELETE_COURIER_PATH = "/api/v1/courier/{id}";

    @Step("Request to create a courier")
    public ValidatableResponse create(CreateCourierRequest createCourierRequest) {
        return given()
                .spec(getSpec())
                .body(createCourierRequest)
                .when()
                .post(CREATE_COURIER_PATH)
                .then();
    }

    @Step("Courier Authorization Request")
    public ValidatableResponse login(LoginCourierRequest loginCourierRequest) {
        return given()
                .spec(getSpec())
                .body(loginCourierRequest)
                .when()
                .post(LOGIN_COURIER_PATH)
                .then();
    }

    @Step("Deleting a created courier")
    public ValidatableResponse delete(int id) {
        return given()
                .spec(getSpec())
                .pathParam("id", id)
                .when()
                .delete(DELETE_COURIER_PATH)
                .then();
    }
}
