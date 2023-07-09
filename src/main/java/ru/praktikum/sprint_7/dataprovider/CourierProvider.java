package ru.praktikum.sprint_7.dataprovider;

import org.apache.commons.lang3.RandomStringUtils;
import ru.praktikum.sprint_7.pojo.CreateCourierRequest;

public class CourierProvider {
    public static CreateCourierRequest getRandomCreateCourierRequest() {
        CreateCourierRequest createCourierRequest = new CreateCourierRequest();
        createCourierRequest.setLogin(RandomStringUtils.randomAlphabetic(5));
        createCourierRequest.setPassword(RandomStringUtils.randomAlphabetic(5));
        createCourierRequest.setFirstName(RandomStringUtils.randomAlphabetic(5));
        return createCourierRequest;
    }

    public static CreateCourierRequest getRandomCreateCourierRequestWithoutLogin() {
        CreateCourierRequest createCourierRequest = new CreateCourierRequest();
        createCourierRequest.setLogin(RandomStringUtils.randomAlphabetic(0));
        createCourierRequest.setPassword(RandomStringUtils.randomAlphabetic(5));
        createCourierRequest.setFirstName(RandomStringUtils.randomAlphabetic(5));
        return createCourierRequest;
    }

    public static CreateCourierRequest getRandomCreateCourierRequestWithoutPassword() {
        CreateCourierRequest createCourierRequest = new CreateCourierRequest();
        createCourierRequest.setLogin(RandomStringUtils.randomAlphabetic(5));
        createCourierRequest.setPassword(RandomStringUtils.randomAlphabetic(0));
        createCourierRequest.setFirstName(RandomStringUtils.randomAlphabetic(5));
        return createCourierRequest;
    }

    public static CreateCourierRequest getRandomCreateCourierRequestWithoutFirstName() {
        CreateCourierRequest createCourierRequest = new CreateCourierRequest();
        createCourierRequest.setLogin(RandomStringUtils.randomAlphabetic(5));
        createCourierRequest.setPassword(RandomStringUtils.randomAlphabetic(5));
        createCourierRequest.setFirstName(RandomStringUtils.randomAlphabetic(0));
        return createCourierRequest;
    }
}
