/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.dao.TrabajadorDao;
import com.google.gson.annotations.Expose;
import com.helper.EncodingHelper;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Time;

/**
 *
 * @author a021792876p
 */
public class HistoricoBean {

    @Expose
    private int id;
    @Expose
    private Date fecha;
    @Expose
    private Time hora;
    @Expose
    private String estado;
    @Expose
    private String tarjeta;
    @Expose
    private Integer id_trabajador;
    @Expose
    private TrabajadorBean objTrabajador;
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    public Integer getId_trabajador() {
        return id_trabajador;
    }

    public void setId_trabajador(Integer id_trabajador) {
        this.id_trabajador = id_trabajador;
    }

    public TrabajadorBean getObjTrabajador() {
        return objTrabajador;
    }

    public void setObjTrabajador(TrabajadorBean objTrabajador) {
        this.objTrabajador = objTrabajador;
    }



    public HistoricoBean fill(ResultSet oResultSet, Connection oConnection, Integer expand) throws Exception {
        this.setId(oResultSet.getInt("id_historico"));
        this.setFecha(oResultSet.getDate("fecha"));
        this.setHora(oResultSet.getTime("hora"));
        this.setEstado(oResultSet.getString("estado"));
        this.setTarjeta(oResultSet.getString("tarjeta"));
        this.setId_trabajador(oResultSet.getInt("id_trabajador"));

        if (expand > 0) {
            TrabajadorDao oTrabajadorDao = new TrabajadorDao(oConnection, "trabajador");
            this.setObjTrabajador(oTrabajadorDao.get(oResultSet.getInt("id_trabajador"),expand - 1));
        }
        return this;
    }

    public String getColumns() {
        String strColumns = "";
        strColumns += "id_historico,";
        strColumns += "fecha,";
        strColumns += "hora,";
        strColumns += "estado,";
        strColumns += "tarjeta,";
        strColumns += "id_trabajador";



        return strColumns;
    }

    public String getValues() {
        String strColumns = "";
        strColumns += "null,";
        strColumns += fecha + ",";
        strColumns += hora + ",";
        strColumns += EncodingHelper.quotate(estado) + ",";
        strColumns += EncodingHelper.quotate(tarjeta)+ ",";
        strColumns += id_trabajador;
        return strColumns;
    }

    public String getPairs() {
        String strPairs = "";
        strPairs += "id_historico=" + id + ",";
        strPairs += "fecha=" + fecha + ",";
        strPairs += "hora=" + hora + ",";
        strPairs += "estado=" + EncodingHelper.quotate(estado) + ",";
        strPairs += "tarjeta=" + EncodingHelper.quotate(tarjeta) + ",";
        strPairs += "id_trabajador=" + id_trabajador;
        return strPairs;

    }
}
