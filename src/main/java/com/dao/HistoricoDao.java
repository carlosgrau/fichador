/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.bean.HistoricoBean;
import com.bean.TrabajadorBean;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author a021792876p
 */
public class HistoricoDao {

    Connection oConnection;
    String ob = null;

    public HistoricoDao(Connection oConnection, String ob) {
        super();
        this.oConnection = oConnection;
        this.ob = ob;
    }

    public TrabajadorBean get(int id, Integer expand) throws Exception {
        String strSQL = "SELECT * FROM " + ob + " WHERE clicodigo = ? and id_ejercicio = ? ";
        TrabajadorBean oTrabajadorBean;
        ResultSet oResultSet = null;
        PreparedStatement oPreparedStatement = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oPreparedStatement.setInt(1, id);
            oResultSet = oPreparedStatement.executeQuery();
            if (oResultSet.next()) {
                oTrabajadorBean = new TrabajadorBean();
                oTrabajadorBean.fill(oResultSet, oConnection, expand);
            } else {
                oTrabajadorBean = null;
            }
        } catch (SQLException e) {
            throw new Exception("Error en Dao get de " + ob, e);
        } finally {
            if (oResultSet != null) {
                oResultSet.close();
            }
            if (oPreparedStatement != null) {
                oPreparedStatement.close();
            }
        }

        return oTrabajadorBean;
    }

    public HistoricoBean create(HistoricoBean oHistoricoBean) throws Exception {
        String strSQL = "INSERT INTO " + ob;
        strSQL += "(" + oHistoricoBean.getColumns() + ")";
        strSQL += " VALUES ";
        strSQL += "(" + oHistoricoBean.getValues() + ")";
        ResultSet oResultSet = null;
        PreparedStatement oPreparedStatement = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oPreparedStatement.executeUpdate();
            oResultSet = oPreparedStatement.getGeneratedKeys();
            if (oResultSet.next()) {
                oHistoricoBean.setId(oResultSet.getInt(1));
            } else {
                oHistoricoBean.setId(0);
            }
        } catch (SQLException e) {
            throw new Exception("Error en Dao create de " + ob, e);
        } finally {
            if (oResultSet != null) {
                oResultSet.close();
            }
            if (oPreparedStatement != null) {
                oPreparedStatement.close();
            }
        }
        return oHistoricoBean;
    }
}
