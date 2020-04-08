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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pagoa.CrudPagos.CrudPago;
import com.example.pagoa.CrudPagos.CrudUsuario;
import com.example.pagoa.model.Pago;
import com.example.pagoa.model.Usuario;

import java.util.ArrayList;

public class FormularioActivity extends AppCompatActivity {
    EditText formCodigo,formDescripcion,formMonto;
    TextView formTipo;
    ArrayList<String> lstActividad;
    Spinner spTipo;
    Button btnFormPago;
    CrudPago crud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        spTipo = (Spinner) findViewById(R.id.spTipo);
        formCodigo = (EditText) findViewById(R.id.formCodigo);
        formDescripcion = (EditText) findViewById(R.id.formDescripcion);
        formMonto = (EditText) findViewById(R.id.formMonto);
        formTipo = (TextView) findViewById(R.id.formTipo);
        obtenerLista();
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(FormularioActivity.this,android.R.layout.simple_spinner_item,lstActividad);
        spTipo.setAdapter(adaptador);
        spTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0){
                    formTipo.setText(lstActividad.get(position-1));
                }else{
                    formTipo.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnFormPago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crud = new CrudPago(FormularioActivity.this);
                if (formCodigo.getText().toString().isEmpty() || formDescripcion.getText().toString().isEmpty()
                || formMonto.getText().toString().isEmpty() || formTipo.getText().toString().isEmpty() ){
                    Pago obj = new Pago();
                    obj.setCodigo(formCodigo.getText().toString());
                    obj.setDescripcion(formDescripcion.getText().toString());
                    obj.setMonto(Double.valueOf(formMonto.getText().toString()));
                    obj.setTipo(formTipo.getText().toString());
                    crud.guardar_pago(obj);
                    formMonto.setText("");
                    formDescripcion.setText("");
                    formTipo.setText("");
                    formCodigo.setText("");
                }else{
                    Toast.makeText(FormularioActivity.this,"Ningun campo debe estar incompleto",Toast.LENGTH_SHORT).show();
                }
            }
        });


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
                preferences = getSharedPreferences("usuario",MODE_PRIVATE);
                preferences.edit().clear().apply();
                Intent intent3 = new Intent(FormularioActivity.this,LoginActivity.class);
                startActivity(intent3);
                finish();
                return true;

            case R.id.itListaPagos:
                Intent intent9 = new Intent(FormularioActivity.this, MovimientosActivity.class);
                startActivity(intent9);
                finish();
                return true;

            case R.id.itFormularioPago:
                Intent intent7= new Intent(FormularioActivity.this, FormularioActivity.class);
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

    private void obtenerLista() {
        lstActividad = new ArrayList<String>();
        lstActividad.add("Seleccione");
        lstActividad.add("PAGO");
        lstActividad.add("MOVIMIENTO");

    }
}
