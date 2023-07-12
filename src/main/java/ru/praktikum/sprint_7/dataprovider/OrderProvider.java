package ru.praktikum.sprint_7.dataprovider;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import ru.praktikum.sprint_7.pojo.CreateOrderRequest;

import java.time.LocalDate;
import java.util.List;

public class OrderProvider {
    public static CreateOrderRequest getRandomCreateOrderRequestWithBlack() {
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setFirstName(RandomStringUtils.randomAlphabetic(5));
        createOrderRequest.setLastName((RandomStringUtils.randomAlphabetic(5)));
        createOrderRequest.setAddress(RandomStringUtils.randomAlphabetic(5));
        createOrderRequest.setMetroStation(RandomStringUtils.randomAlphabetic(5));
        createOrderRequest.setPhone(RandomStringUtils.randomAlphabetic(5));
        createOrderRequest.setRentTime(RandomUtils.nextInt());
        createOrderRequest.setDeliveryDate(LocalDate.now().toString());
        createOrderRequest.setComment(RandomStringUtils.randomAlphabetic(5));
        createOrderRequest.setColor(List.of("BLACK"));
        return createOrderRequest;
    }

    public static CreateOrderRequest getRandomCreateOrderRequestWithGrey() {
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setFirstName(RandomStringUtils.randomAlphabetic(5));
        createOrderRequest.setLastName((RandomStringUtils.randomAlphabetic(5)));
        createOrderRequest.setAddress(RandomStringUtils.randomAlphabetic(5));
        createOrderRequest.setMetroStation(RandomStringUtils.randomAlphabetic(5));
        createOrderRequest.setPhone(RandomStringUtils.randomAlphabetic(5));
        createOrderRequest.setRentTime(RandomUtils.nextInt());
        createOrderRequest.setDeliveryDate(LocalDate.now().toString());
        createOrderRequest.setComment(RandomStringUtils.randomAlphabetic(5));
        createOrderRequest.setColor(List.of("GREY"));
        return createOrderRequest;
    }

    public static CreateOrderRequest getRandomCreateOrderRequestWithBothColors() {
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setFirstName(RandomStringUtils.randomAlphabetic(5));
        createOrderRequest.setLastName((RandomStringUtils.randomAlphabetic(5)));
        createOrderRequest.setAddress(RandomStringUtils.randomAlphabetic(5));
        createOrderRequest.setMetroStation(RandomStringUtils.randomAlphabetic(5));
        createOrderRequest.setPhone(RandomStringUtils.randomAlphabetic(5));
        createOrderRequest.setRentTime(RandomUtils.nextInt());
        createOrderRequest.setDeliveryDate(LocalDate.now().toString());
        createOrderRequest.setComment(RandomStringUtils.randomAlphabetic(5));
        createOrderRequest.setColor(List.of("GREY", "Black"));
        return createOrderRequest;
    }

    public static CreateOrderRequest getRandomCreateOrderRequestWithoutColors() {
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setFirstName(RandomStringUtils.randomAlphabetic(5));
        createOrderRequest.setLastName((RandomStringUtils.randomAlphabetic(5)));
        createOrderRequest.setAddress(RandomStringUtils.randomAlphabetic(5));
        createOrderRequest.setMetroStation(RandomStringUtils.randomAlphabetic(5));
        createOrderRequest.setPhone(RandomStringUtils.randomAlphabetic(5));
        createOrderRequest.setRentTime(RandomUtils.nextInt());
        createOrderRequest.setDeliveryDate(LocalDate.now().toString());
        createOrderRequest.setComment(RandomStringUtils.randomAlphabetic(5));
        return createOrderRequest;
    }
}
