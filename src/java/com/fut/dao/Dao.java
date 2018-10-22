/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
    
    public void ConectionDataBase() {
    try{
        Class.forName("org.postgresql.Driver");
       // cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/torneo?user=root&password=");
        cn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/torneo?user=postgres&password=password");
    }catch (ClassNotFoundException | SQLException e){
        System.out.println(e+"EXCEPTION CONEXION DB");
        
    }
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
