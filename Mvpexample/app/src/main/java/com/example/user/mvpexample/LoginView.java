package com.example.user.mvpexample;

/**
 * Created by User on 17/05/2016.
 */
public interface LoginView {
    void showProgress();

    void hideProgress();

    void setUsernameError();

    void setPasswordError();

    void navigateToHome();

    void showPassword();

    void hidePassword();
}
