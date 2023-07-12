package ru.praktikum.sprint_7.clients;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.praktikum.sprint_7.pojo.CreateOrderRequest;

import static io.restassured.RestAssured.given;

public class OrderClient extends BaseClient {
    public static final String CREATE_ORDER = "/api/v1/orders";
    public static final String CANCEL_ORDER = "/api/v1/orders/cancel?track={track}";

    @Step("Request to create an order")
    public ValidatableResponse createOrder(CreateOrderRequest createOrderRequest) {
        return given()
                .spec(getSpec())
                .body(createOrderRequest)
                .when()
                .post(CREATE_ORDER)
                .then();
    }

    @Step("Request for a list of orders")
    public ValidatableResponse getOrderList() {
        return given()
                .spec(getSpec())
                .when()
                .get(CREATE_ORDER)
                .then();
    }

    @Step("Order Cancellation Request")
    public ValidatableResponse cancelOrder(int track) {
        return given()
                .spec(getSpec())
                .pathParam("track", track)
                .when()
                .put(CANCEL_ORDER)
                .then();
    }
}
