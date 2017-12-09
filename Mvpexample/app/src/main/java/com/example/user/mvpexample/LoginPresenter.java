package com.example.user.mvpexample;

import android.widget.EditText;

/**
 * Created by User on 17/05/2016.
 */
public interface LoginPresenter {

    void validateCredentials(String username, String password);
    void showPasswordClicked(EditText password);
    void onDestroy();
}
