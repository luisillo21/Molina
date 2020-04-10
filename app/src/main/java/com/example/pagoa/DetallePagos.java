package com.example.pagoa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pagoa.CrudPagos.CrudPago;
import com.example.pagoa.model.Pago;

import java.util.ArrayList;

public class DetallePagos extends AppCompatActivity {
    EditText EditformCodigo,EditformDescripcion,EditformMonto;
    TextView EditformTipo;
    ArrayList<String> lstActividad;
    Spinner EditspTipo;
    Button btnFormEditar,btnFormEliminar;
    Pago Edobj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pagos);

        EditformCodigo = (EditText)findViewById(R.id.EditformCodigo);
        EditformDescripcion = (EditText)findViewById(R.id.EditformDescripcion);
        EditformMonto = (EditText)findViewById(R.id.EditformMonto);
        EditformTipo = (TextView) findViewById(R.id.EditformTipo);
        EditspTipo = (Spinner) findViewById(R.id.EditspTipo);
        btnFormEditar = (Button) findViewById(R.id.btnFormEditar);
        btnFormEliminar = (Button) findViewById(R.id.btnFormEliminar);
        obtenerLista();
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(DetallePagos.this,android.R.layout.simple_spinner_item,lstActividad);
        EditspTipo.setAdapter(adaptador);




        EditspTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0){
                    EditformTipo.setText(lstActividad.get(position-1));
                }else{
                    EditformTipo.setText(String.valueOf(Edobj.getTipo()));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final Bundle objeto = getIntent().getExtras();
        Edobj = new Pago();
        if (objeto != null){
            Edobj = (Pago) objeto.getSerializable("pago");
        }

        EditformCodigo.setText(String.valueOf( Edobj.getCodigo()));
        EditformDescripcion.setText(String.valueOf( Edobj.getDescripcion()));
        EditformMonto.setText(String.valueOf( Edobj.getMonto()));
        EditformTipo.setText(String.valueOf( Edobj.getTipo()));




        btnFormEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (EditformCodigo.getText().toString().isEmpty() || EditformDescripcion.getText().toString().isEmpty()
                        || EditformMonto.getText().toString().isEmpty() || EditformTipo.getText().toString().isEmpty() ){
                    Toast.makeText(DetallePagos.this,"Ningun campo debe estar incompleto",Toast.LENGTH_SHORT).show();
                }else{
                    Pago obj = new Pago();
                    obj.setIdusuario(Edobj.getIdusuario());
                    obj.setIdpago(Edobj.getIdpago());
                    obj.setCodigo(EditformCodigo.getText().toString());
                    obj.setDescripcion(EditformDescripcion.getText().toString());
                    obj.setMonto(Double.valueOf(EditformMonto.getText().toString()));
                    obj.setTipo(EditformTipo.getText().toString());
                    CrudPago crud = new CrudPago(DetallePagos.this);
                    crud.Update_pago(obj);
                    Toast.makeText(DetallePagos.this,"Datos actualizados exitosamente",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DetallePagos.this,MovimientosActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        btnFormEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CrudPago crud = new CrudPago(DetallePagos.this);
                crud.Eliminar_pago(Edobj);
                Toast.makeText(DetallePagos.this,"Datos eliminados exitosamente",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DetallePagos.this,MovimientosActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void obtenerLista() {
        lstActividad = new ArrayList<String>();
        lstActividad.add("Seleccione");
        lstActividad.add("PAGO");
        lstActividad.add("MOVIMIENTO");
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
