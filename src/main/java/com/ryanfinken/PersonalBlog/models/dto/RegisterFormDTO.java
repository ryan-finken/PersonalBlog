package com.ryanfinken.PersonalBlog.models.dto;

public class RegisterFormDTO extends LoginFormDTO {
    private String verifyPassword;

    public RegisterFormDTO() {
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }
}
