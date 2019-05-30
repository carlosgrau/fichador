/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.google.gson.annotations.Expose;
import com.helper.EncodingHelper;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author a021792876p
 */
public class TrabajadorBean {

    @Expose
    private int id;
    @Expose
    private String nombre;
    @Expose
    private String tarjeta;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }


    public TrabajadorBean fill(ResultSet oResultSet, Connection oConnection, Integer expand) throws Exception {
        this.setId(oResultSet.getInt("codigo"));
        this.setNombre(oResultSet.getString("nombre"));
        this.setTarjeta(oResultSet.getString("tarjeta"));
        return this;
    }

    public String getColumns() {
        String strColumns = "";
        strColumns += "codigo,";
        strColumns += "nombre,";
        strColumns += "tarjeta";


        return strColumns;
    }

    public String getValues() {
        String strColumns = "";
        strColumns += "null,";
        strColumns += EncodingHelper.quotate(nombre) + ",";
        strColumns += EncodingHelper.quotate(tarjeta);
        return strColumns;
    }

    public String getPairs() {
        String strPairs = "";
        strPairs += "codigo=" + id + ",";
        strPairs += "nombre=" + EncodingHelper.quotate(nombre) + ",";
        strPairs += "tarjeta=" + EncodingHelper.quotate(tarjeta);
        return strPairs;

    }
}
