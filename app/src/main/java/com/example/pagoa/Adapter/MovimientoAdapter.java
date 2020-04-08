package com.example.pagoa.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pagoa.R;
import com.example.pagoa.model.Pago;

import java.util.List;

public class MovimientoAdapter extends BaseAdapter {
    Context context;
    List<Pago> lstPagos;

    public MovimientoAdapter(Context context, List<Pago> lstPagos) {
        this.context = context;
        this.lstPagos = lstPagos;
    }

    @Override
    public int getCount() {
        return lstPagos.size();
    }

    @Override
    public Object getItem(int position) {
        return lstPagos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.item_pagos,null);
        TextView codigo = (TextView)v.findViewById(R.id.codigo_item);
        TextView descripcion = (TextView) v.findViewById(R.id.descripcion_item);
        TextView monto = (TextView) v.findViewById(R.id.monto_item);
        TextView tipo = (TextView) v.findViewById(R.id.tipo_item);

        codigo.setText(String.valueOf(lstPagos.get(position).getCodigo()));
        descripcion.setText(lstPagos.get(position).getDescripcion());
        monto.setText(String.valueOf(lstPagos.get(position).getMonto()));
        tipo.setText(lstPagos.get(position).getTipo());

        return v;
    }
}
