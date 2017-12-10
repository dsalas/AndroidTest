package com.test.android.database.entity;

import android.widget.ListView;

import com.google.gson.annotations.SerializedName;
import com.test.android.database.rest.UsuarioRestService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android on 9/12/2017.
 */

public class UserResponse extends User {
    @SerializedName("lista_autorizacion_recurso")
    public List<AuthResource> auth_resource_list = new ArrayList<AuthResource>();

    @SerializedName("flg_existe_imei")
    public int flgExisteImei;

    @SerializedName("flg_existe_token")
    public int flgExisteToken;

    @SerializedName("flg_existe_login_otro_equipo")
    public int flgExisteLoginOtroEquipo;

    public UserResponse(int userid, String lastname, String name, List<AuthResource> auth_resource_list, int flgExisteToken) {
        super(userid,lastname,name);
        this.auth_resource_list = auth_resource_list;
        this.flgExisteToken = flgExisteToken;

    }
}
