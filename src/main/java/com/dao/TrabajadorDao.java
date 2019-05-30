/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;


import com.bean.TrabajadorBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author a021792876p
 */
public class TrabajadorDao {

    Connection oConnection;
    String ob = null;

    public TrabajadorDao(Connection oConnection, String ob) {
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

}
