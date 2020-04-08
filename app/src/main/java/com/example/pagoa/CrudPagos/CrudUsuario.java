package com.example.pagoa.CrudPagos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.pagoa.Database.DbPagos;
import com.example.pagoa.model.Usuario;

import java.util.ArrayList;

public class CrudUsuario {
    private DbPagos admin;
    private SQLiteDatabase db;

    public CrudUsuario(Context context) {
        this.admin = new DbPagos(context,"pago",null,1);
        this.db = this.admin.getWritableDatabase();
    }

    public void guardar_usuario(Usuario usuario){
        ContentValues valores = new ContentValues();
        valores.put("usuario",usuario.getUsuario());
        valores.put("clave",usuario.getClave());
        valores.put("nombre",usuario.getNombre());
        valores.put("apellido",usuario.getApellido());
        valores.put("cedula",usuario.getCedula());
        valores.put("correo",usuario.getCorreo());
        valores.put("estado","ACTIVO");
        valores.put("id_base",0);
        this.db.insert("usuario",null,valores);
        this.db.close();
    }

    public void update_usuario(Usuario usuario)
    {
        ContentValues valores = new ContentValues();
        valores.put("usuario",usuario.getUsuario());
        valores.put("clave",usuario.getClave());
        valores.put("nombre",usuario.getNombre());
        valores.put("apellido",usuario.getApellido());
        valores.put("cedula",usuario.getCedula());
        valores.put("correo",usuario.getCorreo());
        valores.put("estado","ACTIVO");
        this.db.update("usuario",valores,"idusuario= "+usuario.getIdusuario(),null);
        this.db.close();
    }
    public void Eliminar_usuario(int id){
        ContentValues valores = new ContentValues();
        valores.put("estado","INACTIVO");
        this.db.update("usuario",valores,"idusuario="+id,null);
        this.db.close();
    }
    public ArrayList<Usuario> traer_todo(){
        Cursor cursor = this.db.rawQuery("Select * from usuario where estado = 'ACTIVO'",null);
        ArrayList<Usuario> usuario = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                Usuario obj = new Usuario();
                obj.setIdusuario(cursor.getInt(0));
                obj.setUsuario(cursor.getString(1));
                obj.setClave(cursor.getString(2));
                obj.setNombre(cursor.getString(3));
                obj.setApellido(cursor.getString(4));
                obj.setCorreo(cursor.getString(5));
                obj.setCedula(cursor.getString(6));
                usuario.add(obj);
            }while (cursor.moveToNext());
        }
        this.db.close();
        return usuario;
    }


    public Usuario login(String usuario,String clave){
        Cursor cursor = this.db.rawQuery("Select * from usuario where estado = 'ACTIVO' and usuario='"+usuario+"' and clave='"+clave+"'",null);
        Usuario obj = new Usuario();
        if(cursor.moveToFirst()){
                obj.setIdusuario(cursor.getInt(0));
                obj.setUsuario(cursor.getString(1));
                obj.setClave(cursor.getString(2));
                obj.setNombre(cursor.getString(3));
                obj.setApellido(cursor.getString(4));
                obj.setCorreo(cursor.getString(5));
                obj.setCedula(cursor.getString(6));
        }else{
            obj.setUsuario("incorrecto");
            obj.setClave("incorrecto");
        }
        this.db.close();
        return obj;
    }



}
