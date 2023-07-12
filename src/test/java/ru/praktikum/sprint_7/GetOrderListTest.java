package ru.praktikum.sprint_7;

import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.Matchers;
import org.junit.Test;
import ru.praktikum.sprint_7.clients.OrderClient;

public class GetOrderListTest {
    private OrderClient orderClient = new OrderClient();

    @Test
    @DisplayName("Getting a list of orders")
    public void orderRequestReturnListOfOrders() {
        orderClient.getOrderList()
                .statusCode(200)
                .body("orders", Matchers.notNullValue());
    }
}
