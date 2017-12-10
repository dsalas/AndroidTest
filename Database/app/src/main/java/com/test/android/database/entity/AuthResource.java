package com.test.android.database.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Android on 9/12/2017.
 */

public class AuthResource {
    Integer userId;
    @SerializedName("codigo_recurso")
    String resourceId;
    @SerializedName("acc_insertar")
    String insertAction;
    @SerializedName("acc_actualizar")
    String updatetAction;
    @SerializedName("acc_eliminar")
    String deleteAction;
    @SerializedName("acc_consultar")
    String queryAction;
    @SerializedName("acc_anular")
    String nulifyAction;

    public AuthResource(){

    }
}