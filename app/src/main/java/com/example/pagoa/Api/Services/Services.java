package com.example.pagoa.Api.Services;

import com.example.pagoa.model.Pago;
import com.example.pagoa.model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Services {
    @GET("webservices/trunk/Proyecto_Facturas/get_all_usuario.php")
    Call<Usuario> getUsuario(@Query("usuario") String usuario);

    @GET("webservices/trunk/Proyecto_Facturas/get_all_usuario.php")
    Call<Pago> getPago();
}
