package com.example.pagoa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pagoa.CrudPagos.CrudUsuario;
import com.example.pagoa.model.Usuario;

public class MainActivity extends AppCompatActivity {
    EditText formUsu,formPass,formConfirmar,formNombre,formApellido,formCedula,formCorreo;
    Button btnGuardarUsuariom,btnSalir;
    CrudUsuario crud;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        formUsu = (EditText) findViewById(R.id.formUsu);
        formPass = (EditText) findViewById(R.id.formPass);
        formConfirmar = (EditText) findViewById(R.id.formConfirmar);
        formNombre = (EditText) findViewById(R.id.formNombre);
        formApellido = (EditText) findViewById(R.id.formApellido);
        formCedula = (EditText) findViewById(R.id.formCedula);
        formCorreo = (EditText) findViewById(R.id.formCorreo);

        btnGuardarUsuariom = (Button) findViewById(R.id.btnGuardarUsuario);
        btnSalir = (Button) findViewById(R.id.btnSalir);

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnGuardarUsuariom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crud = new CrudUsuario(MainActivity.this);
                if (formPass.getText().toString().equals(formConfirmar.getText().toString())){
                Usuario obj = new Usuario();
                obj.setUsuario(formUsu.getText().toString());
                obj.setClave(formPass.getText().toString());
                obj.setNombre(formNombre.getText().toString());
                obj.setApellido(formApellido.getText().toString());
                obj.setCedula(formCedula.getText().toString());
                obj.setCorreo(formCorreo.getText().toString());
                crud.guardar_usuario(obj);
                    Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(MainActivity.this,"La contraseña no coincide",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }




}
