package com.example.pagoa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pagoa.CrudPagos.CrudUsuario;
import com.example.pagoa.model.Usuario;

public class LoginActivity extends AppCompatActivity {
    EditText formUsuario,formClave;
    Button btnIniciarSesion,btnCrearUsuario,btnSalir;
    CrudUsuario crud;
    Usuario objUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        formUsuario = (EditText) findViewById(R.id.formUsuario);
        formClave = (EditText)findViewById(R.id.formClave);

        btnSalir = (Button)findViewById(R.id.btnSalir);
        btnCrearUsuario = (Button)findViewById(R.id.btnCrearUsuario);
        btnIniciarSesion = (Button)findViewById(R.id.btnIniciarSesion);


        btnCrearUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String usuario = formUsuario.getText().toString();
                final String clave = formClave.getText().toString();
                crud = new CrudUsuario(LoginActivity.this);
                objUsuario = new Usuario();
                objUsuario = crud.login(usuario,clave);

                if (objUsuario.getUsuario().equals(usuario) & objUsuario.getClave().equals(clave)){
                    SharedPreferences preferences;
                    preferences = getSharedPreferences("usuario", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editors =preferences.edit();
                    editors.putInt("idusuario",objUsuario.getIdusuario());
                    editors.putString("usuario",usuario);
                    editors.putString("clave",clave);
                    editors.apply();
                    editors.commit();
                    Intent intent = new Intent(LoginActivity.this,MovimientosActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this,"Usuario o clave incorrectos",Toast.LENGTH_SHORT).show();
                }


            }
        });



    }
}
