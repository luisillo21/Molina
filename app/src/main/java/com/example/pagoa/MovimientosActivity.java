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
import android.widget.ListView;

import com.example.pagoa.Adapter.MovimientoAdapter;
import com.example.pagoa.CrudPagos.CrudPago;
import com.example.pagoa.model.Pago;
import com.example.pagoa.model.Usuario;

import java.util.List;

public class MovimientosActivity extends AppCompatActivity {
    ListView listPagos;
    MovimientoAdapter adapter;
    List<Pago> lstPagos;
    CrudPago crud;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimientos);
        listPagos = (ListView)findViewById(R.id.listPagos);
        SharedPreferences preferences;
        preferences = getSharedPreferences("usuario", Context.MODE_PRIVATE);
        int id = preferences.getInt("idusuario",0);
        System.out.println("ID RECIBIDO:"+ id);
        lstPagos = crud.listar_todo(id);
        for(int i = 0;i<lstPagos.size();i++){
            System.out.println("Nombre: "+lstPagos.get(i).getDescripcion());
        }
        adapter = new MovimientoAdapter(MovimientosActivity.this,lstPagos);
        listPagos.setAdapter(adapter);
        listPagos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pago obj = lstPagos.get(position);
                Intent intent = new Intent(MovimientosActivity.this,DetallePagos.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("pago",obj);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
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
                Intent intent3 = new Intent(MovimientosActivity.this,LoginActivity.class);
                startActivity(intent3);
                finish();
                return true;

            case R.id.itListaPagos:
                Intent intent9 = new Intent(MovimientosActivity.this, MovimientosActivity.class);
                startActivity(intent9);
                finish();
                return true;

            case R.id.itFormularioPago:
                Intent intent7= new Intent(MovimientosActivity.this, FormularioActivity.class);
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
