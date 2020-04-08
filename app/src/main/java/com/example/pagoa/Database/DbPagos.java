package com.example.pagoa.Database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DbPagos extends SQLiteOpenHelper {
    public DbPagos(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS pago (" +
                "  idpago integer PRIMARY KEY  AUTOINCREMENT NOT NULL ," +
                "  codigo text NOT NULL," +
                "  descripcion text NOT NULL," +
                "monto DECIMAL(10,5) NOT NULL," +
                "estado text not null," +
                "idusuario integer not null," +
                "id_base integer," +
                "tipo text not null," +
                "FOREIGN KEY(idusuario) REFERENCES usuario(idusuario)" +
                ")");
        db.execSQL("CREATE TABLE IF NOT EXISTS usuario (" +
                "  idusuario integer PRIMARY KEY  AUTOINCREMENT NOT NULL ," +
                "  usuario text NOT NULL," +
                "  clave text NOT NULL," +
                "nombre text NOT NULL," +
                "apellido text not null," +
                "correo text not null," +
                "cedula text not null," +
                "estado text not null," +
                "id_base integer" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
