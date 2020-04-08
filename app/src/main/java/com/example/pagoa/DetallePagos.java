package com.example.pagoa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class DetallePagos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pagos);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.itCerrarSesion:
                SharedPreferences preferences;
                preferences = getSharedPreferences("datos_usuario",MODE_PRIVATE);
                preferences.edit().clear().apply();
                Intent intent3 = new Intent(DetallePagos.this,LoginActivity.class);
                startActivity(intent3);
                finish();
                return true;

            case R.id.itListaPagos:
                Intent intent9 = new Intent(DetallePagos.this, MovimientosActivity.class);
                startActivity(intent9);
                finish();
                return true;

            case R.id.itFormularioPago:
                Intent intent7= new Intent(DetallePagos.this, FormularioActivity.class);
                startActivity(intent7);
                finish();
                return true;

            case R.id.itSalir:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }

}
