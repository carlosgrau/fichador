/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.bean.TrabajadorBean;
import com.bean.ReplyBean;
import com.bean.UsuarioBean;
import com.connection.specificimplementation.HikariConnectionForUser;
import com.dao.TrabajadorDao;
import com.google.gson.Gson;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author a021792876p
 */
public class TrabajadorService {

    HttpServletRequest oRequest;
    String ob = null;
    String usuario, password, conexion;

    public TrabajadorService(HttpServletRequest oRequest) {
        super();
        this.oRequest = oRequest;
        ob = oRequest.getParameter("ob");
    }

    public ReplyBean get() throws Exception {
        ReplyBean oReplyBean;
        Connection oConnection = null;
        UsuarioBean oUsuarioBean = null;
        HikariConnectionForUser oHikariConectio = new HikariConnectionForUser();

        try {
            Integer id = Integer.parseInt(oRequest.getParameter("id"));
            oUsuarioBean = (UsuarioBean) oRequest.getSession().getAttribute("user");

            usuario = oUsuarioBean.getLoginCli();
            password = oUsuarioBean.getPassCli();
            conexion = oUsuarioBean.newConnectionClient();

            oConnection = (Connection) oHikariConectio.newConnectionParams(usuario, password, conexion);

            TrabajadorDao oTrabajadorDao = new TrabajadorDao(oConnection, ob);
            TrabajadorBean oTrabajadorBean = oTrabajadorDao.get(id, 1);
            Gson oGson = new Gson();
            oReplyBean = new ReplyBean(200, oGson.toJson(oTrabajadorBean));
        } catch (Exception ex) {
            throw new Exception("ERROR: Service level: get method: " + ob + " object", ex);
        } finally {
            if (oConnection != null) {
                oConnection.close();
            }
            oHikariConectio.disposeConnection();
        }

        return oReplyBean;

    }
}
