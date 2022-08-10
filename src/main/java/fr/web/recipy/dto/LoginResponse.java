package fr.web.recipy.dto;

public class LoginResponse {

    private String response;

    public LoginResponse() {
        super();
    }

    public LoginResponse(String response) {
        this();
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}

