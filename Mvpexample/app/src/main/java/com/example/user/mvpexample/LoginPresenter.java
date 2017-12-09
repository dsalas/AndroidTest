package com.example.user.mvpexample;

/**
 * Created by User on 17/05/2016.
 */
public interface LoginPresenter {

    void validateCredentials(String username, String password);

    void onDestroy();
}
