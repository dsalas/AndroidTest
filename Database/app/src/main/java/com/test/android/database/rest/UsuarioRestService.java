package com.test.android.database.rest;

import com.test.android.database.entity.UserResponse;

import java.util.Collection;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Android on 9/12/2017.
 */

public interface UsuarioRestService {

    @GET("SrvSeguridad.svc/srestLoginAutorizacion/{login}/{pwd}/{nro_telefono}/{version_app}")
    Call<HashMap<String , Collection<UserResponse>>> getDatosUsuario(@Path("login") String login, @Path("pwd") String password, @Path("nro_telefono") String nro_telefono, @Path("version_app") String version_app);
}
