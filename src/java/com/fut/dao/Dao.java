/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Yeison
 */
public class Dao {
    private Connection cn;

    public Connection getCn() {
        return cn;
    }

    public void setCn(Connection cn) {
        this.cn = cn;
    }
    
    public void Conectar() {
    try{
        Class.forName("com.mysql.jdbc.Driver");
        cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/torneo?user=root&password=");
    }catch (Exception e){
        System.out.println("EXCEPTION CONEXION DB");
    }
    }
    
    public void Cerrar() throws Exception{
      try{
        if(cn != null){
            if(cn.isClosed()==false){
            cn.close();
            }
        }
    }catch (Exception e){
        throw e;
    }
    }    
}
