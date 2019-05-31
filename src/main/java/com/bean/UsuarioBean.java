/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.constant.ConnectionConstants;
import com.google.gson.annotations.Expose;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author a021792876p
 */
public class UsuarioBean {

    

    @Expose
    private int id;
    @Expose
    private String usuario;
    @Expose
    private String login;
    @Expose
    private String pass;
    @Expose
    private String loginCli;
    @Expose
    private String passCli;
    @Expose
    private String hostCli;
    @Expose
    private Integer portCli;
    @Expose
    private String databaseCli;
    @Expose
    private Integer trabajador;
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getLoginCli() {
        return loginCli;
    }

    public void setLoginCli(String loginCli) {
        this.loginCli = loginCli;
    }

    public String getPassCli() {
        return passCli;
    }

    public void setPassCli(String passCli) {
        this.passCli = passCli;
    }

    public String getHostCli() {
        return hostCli;
    }

    public void setHostCli(String hostCli) {
        this.hostCli = hostCli;
    }

    public Integer getPortCli() {
        return portCli;
    }

    public void setPortCli(Integer portCli) {
        this.portCli = portCli;
    }

    public String getDatabaseCli() {
        return databaseCli;
    }

    public void setDatabaseCli(String databaseCli) {
        this.databaseCli = databaseCli;
    }

    public Integer getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Integer trabajador) {
        this.trabajador = trabajador;
    }

    public UsuarioBean fill(ResultSet oResultSet, Connection oConnection) throws Exception {
        this.setId(oResultSet.getInt("id"));
        this.setLogin(oResultSet.getString("login"));
        this.setPass(oResultSet.getString("pass"));
        this.setUsuario(oResultSet.getString("usuario"));
        this.setLoginCli(oResultSet.getString("logincli"));
        this.setPassCli(oResultSet.getString("passcli"));
        this.setHostCli(oResultSet.getString("hostcli"));
        this.setPortCli(oResultSet.getInt("portcli"));
        this.setDatabaseCli(oResultSet.getString("databasecli"));
        this.setTrabajador(oResultSet.getInt("trabajador"));
        return this;
    }

    public String newConnectionClient() {
        return "jdbc:mysql://" + this.getHostCli() + ":" + this.getPortCli() + "/"
                + this.getDatabaseCli();
    }


    @Override
    public String toString() {
        return "UsuarioBean{ id=" + id + ", usuario=" + usuario + ", login=" + login + ", pass=" + pass + ", loginCli=" + loginCli + ", passCli=" + passCli + ", hostCli=" + hostCli + ", portCli=" + portCli + ", databaseCli=" + databaseCli + '}';
    }
}