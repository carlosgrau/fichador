/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.bean.HistoricoBean;
import com.bean.TrabajadorBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
     public ArrayList<HistoricoBean> getpage(int iRpp, int iPage,int trabajador) throws Exception {
        String strSQL = "SELECT * FROM " + ob + " WHERE id_Trabajador = ? AND fecha = CURDATE() ";
        ArrayList<HistoricoBean> alHistoricoBean;
        if (iRpp > 0 && iRpp < 100000 && iPage > 0 && iPage < 100000000) {
            strSQL += " LIMIT " + (iPage - 1) * iRpp + ", " + iRpp;
            ResultSet oResultSet = null;
            PreparedStatement oPreparedStatement = null;
            try {
                oPreparedStatement = oConnection.prepareStatement(strSQL);
                oPreparedStatement.setInt(1, trabajador);
                oResultSet = oPreparedStatement.executeQuery();
                alHistoricoBean = new ArrayList<HistoricoBean>();
                while (oResultSet.next()) {
                    HistoricoBean oHistoricoBean = new HistoricoBean();
                    oHistoricoBean = new HistoricoBean();
                    oHistoricoBean.fill(oResultSet, oConnection,1);
                    alHistoricoBean.add(oHistoricoBean);
                }
            } catch (SQLException e) {
                throw new Exception("Error en Dao getpage de " + ob + "------" + e, e);
            } finally {
                if (oResultSet != null) {
                    oResultSet.close();
                }
                if (oPreparedStatement != null) {
                    oPreparedStatement.close();
                }
            }
        } else {
            throw new Exception("Error en Dao getpage de " + ob);
        }
        //oConnection.close();
        return alHistoricoBean;

    }
}
