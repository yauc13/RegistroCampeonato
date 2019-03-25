/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author YEISON URREA
 */
public class DaoUtil {
    
    private static final String DRIVE_MANAGE_POSTGRES = "jdbc:postgresql://localhost:5432/torneo?user=postgres&password=password";
    private static final String DATASOURCE = "adminfutsal/torneo";
    private static DataSource ds;
    
    public static Connection getConnection(){
        Connection conn = null;
        try {
            Context context = new InitialContext();
            ds = (DataSource)context.lookup(DATASOURCE);
            if (ds != null) {
                conn = ds.getConnection();
            }
        } catch(NamingException | SQLException e) {
            System.out.println("[DAO] "+e);
        }
        return conn;
    }
    
    
    public static void closeConection(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            if (connection != null) {
                connection.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Connection ConectionDataBase() {
        Connection cn = null;
    try{
        Class.forName("org.postgresql.Driver");
       // cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/torneo?user=root&password=");
        cn = DriverManager.getConnection(DRIVE_MANAGE_POSTGRES);
    }catch (ClassNotFoundException | SQLException e){
        System.out.println(e+"EXCEPTION CONEXION DB");
        Util.setMessage(FacesMessage.SEVERITY_FATAL, "Error", "no se pudo conectar a la base de datos");        
    }
    return cn;
    }
    
    public void CloseConection(Connection cn) {
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
