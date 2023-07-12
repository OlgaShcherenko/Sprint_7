package ru.praktikum.sprint_7.pojo;

public class LoginCourierRequest {
    private String login;
    private String password;

    public LoginCourierRequest() {
    }

    public LoginCourierRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static LoginCourierRequest fromAll(CreateCourierRequest createCourierRequest) {
        return new LoginCourierRequest(createCourierRequest.getLogin(), createCourierRequest.getPassword());
    }

    public static LoginCourierRequest fromWithoutLogin(CreateCourierRequest createCourierRequest) {
        return new LoginCourierRequest("", createCourierRequest.getPassword());
    }

    public static LoginCourierRequest fromWithoutPassword(CreateCourierRequest createCourierRequest) {
        return new LoginCourierRequest(createCourierRequest.getLogin(), "");
    }
}

