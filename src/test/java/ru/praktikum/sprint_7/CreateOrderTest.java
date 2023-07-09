package ru.praktikum.sprint_7;

import io.qameta.allure.junit4.DisplayName;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum.sprint_7.clients.OrderClient;
import ru.praktikum.sprint_7.dataprovider.OrderProvider;
import ru.praktikum.sprint_7.pojo.CreateOrderRequest;

@RunWith(Parameterized.class)
public class CreateOrderTest extends BaseTest {
    private CreateOrderRequest createOrderRequest;
    private OrderClient orderClient;
    Integer track;

    public CreateOrderTest(CreateOrderRequest createOrderRequest) {
        this.createOrderRequest = createOrderRequest;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {OrderProvider.getRandomCreateOrderRequestWithBlack()},
                {OrderProvider.getRandomCreateOrderRequestWithGrey()},
                {OrderProvider.getRandomCreateOrderRequestWithBothColors()},
                {OrderProvider.getRandomCreateOrderRequestWithoutColors()},
        };
    }

    @Test
    @DisplayName("Creating orders with different colors of scooter")
    public void orderShouldBeCreated() {
        orderClient = new OrderClient();
        track = orderClient.createOrder(createOrderRequest)
                .statusCode(201)
                .body("track", Matchers.notNullValue())
                .extract().jsonPath().get("track");
    }

    @After
    public void tearDown() {
        if (track != null) {
            orderClient.cancelOrder(track)
                    .statusCode(200);
        }
    }
}
