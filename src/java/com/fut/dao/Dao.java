/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Yeison
 */
public class Dao {
    Connection cn;

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }
    
    public Connection ConectionDataBase() {
        cn = null;
    try{
        Class.forName("org.postgresql.Driver");
       // cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/torneo?user=root&password=");
        cn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/torneo?user=postgres&password=password");
    }catch (ClassNotFoundException | SQLException e){
        System.out.println(e+"EXCEPTION CONEXION DB");
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "no se pudo conectar a la base de datos");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    return cn;
    }
    
    public void CloseConection() {
      try{
        if(cn != null){
            if(cn.isClosed()==false){
            cn.close();
            }
        }
    }catch (SQLException e){
          System.err.println(e);
    }
    }    
}
