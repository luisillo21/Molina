package com.example.pagoa.CrudPagos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pagoa.Database.DbPagos;
import com.example.pagoa.model.Pago;

import java.util.ArrayList;

public class CrudPago {
    private DbPagos admin;
    private SQLiteDatabase db;

    public CrudPago(Context context) {
        this.admin = new DbPagos(context, "pago", null, 1);
        this.db = this.admin.getWritableDatabase();
    }

    public void guardar_pago(Pago pago) {
        ContentValues values = new ContentValues();
        values.put("codigo", pago.getCodigo());
        values.put("descripcion", pago.getDescripcion());
        values.put("monto", pago.getMonto());
        values.put("estado", "ACTIVO");
        values.put("tipo", pago.getTipo());
        values.put("idusuario", pago.getIdusuario());
        this.db.insert("pago", null, values);
        this.db.close();
    }

    public void Update_pago(Pago pago) {
        ContentValues values = new ContentValues();
        values.put("codigo", pago.getCodigo());
        values.put("descripcion", pago.getDescripcion());
        values.put("monto", pago.getMonto());
        values.put("tipo", pago.getTipo());
        values.put("idusuario", pago.getIdusuario());
        this.db.update("pago", values, "idpago=" + pago.getIdpago(), null);
        this.db.close();
    }

    public void Eliminar_pago(Pago pago) {
        ContentValues values = new ContentValues();
        values.put("estado", "INACTIVO");
        this.db.update("pago", values, "idpago=" + pago.getIdpago(), null);
        this.db.close();
    }

    public ArrayList<Pago> listar_todo(int id) {
        Cursor row = this.db.rawQuery("SELECT * FROM pago where estado = 'ACTIVO' and idusuario="+id,null );
        ArrayList<Pago> lista = new ArrayList<>();
        if (row.moveToFirst()) {
            do {
                Pago pago = new Pago();
                pago.setIdpago(row.getInt(0));
                pago.setCodigo(row.getString(1));
                pago.setDescripcion(row.getString(2));
                pago.setMonto(row.getDouble(3));
                pago.setEstado(row.getString(4));
                pago.setIdusuario(row.getInt(5));
                pago.setTipo(row.getString(7));
                lista.add(pago);
            } while (row.moveToNext());
        }
        this.db.close();
        return lista;
    }
}