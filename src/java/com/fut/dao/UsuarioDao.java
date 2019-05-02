/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.dao;

import com.fut.model.Usuario;
import com.fut.util.DaoUtil;
import com.fut.util.SqlAdminFutSal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yeison Urrea
 */
public class UsuarioDao {
    
    private PreparedStatement stmt;
    private Connection cx;
    private ResultSet rs;
    
    public boolean registrar(Usuario usu){
        boolean reg = false;
        try{
            cx = DaoUtil.ConectionDriveDB();
            
            //stmt = cxprepareStatement("INSERT INTO usuario (loginUsuario, passwordUsuario, rolUsuario) values(?, ?, ?)");
            stmt = cx.prepareStatement("INSERT INTO public.usuario(\"loginUsuario\",\"passwordUsuario\",\"rolUsuario\") VALUES (?, ?, ?);");
            stmt.setString(1, usu.getLoginUsuario());
            stmt.setString(2, usu.getPasswordUsuario());
            stmt.setString(3, usu.getRolUsuario());
            stmt.executeUpdate();
            reg = true;
        }catch(Exception e){
            System.err.println(e);
        }finally{
        DaoUtil.closeConection(cx, stmt, rs);
        }
        return reg;
    }
        
    public List<Usuario> listar() throws Exception{
            List<Usuario> lista;
            
            
            try{
                cx = DaoUtil.ConectionDriveDB();
                //stmt = cxprepareCall("SELECT idUsuario, loginUsuario, passwordUsuario, rolUsuario FROM usuario");
                stmt = cx.prepareStatement("SELECT \"idUsuario\", \"loginUsuario\", \"passwordUsuario\", \"rolUsuario\" FROM public.usuario");
                rs = stmt.executeQuery();
                lista = new ArrayList();
                while(rs.next()){
                    Usuario usu = new Usuario();
                    usu.setIdUsuario(rs.getInt("idUsuario"));
                    usu.setLoginUsuario(rs.getString("loginUsuario"));
                    usu.setPasswordUsuario(rs.getString("passwordUsuario"));
                    usu.setRolUsuario(rs.getString("rolUsuario"));
                    
                    lista.add(usu);
                
                }
            }catch(Exception e){
                throw e;
            }finally{
                DaoUtil.closeConection(cx, stmt, rs);
            }
        
        return lista;   
    }
    
    public Usuario leerID(Usuario usu){
        Usuario usus = null;
        
            try{
                cx = DaoUtil.ConectionDriveDB();
                if(cx !=null){
                //stmt = cxprepareStatement("SELECT idUsuario, loginUsuario, passwordUsuario, rolUsuario FROM usuario WHERE loginUsuario = ? and passwordUsuario = ?");
                stmt = cx.prepareStatement(SqlAdminFutSal.GET_LOGIN_USER);
                stmt.setString(1, usu.getLoginUsuario());
                stmt.setString(2, usu.getPasswordUsuario());
                rs = stmt.executeQuery();
                while(rs.next()){
                    usus = new Usuario();
                    usus.setIdUsuario(rs.getInt("idUsuario"));
                    usus.setLoginUsuario(rs.getString("loginUsuario"));
                    usus.setPasswordUsuario(rs.getString("passwordUsuario"));
                    usus.setRolUsuario(rs.getString("rolUsuario"));
                    
                }
                }
            }catch(SQLException e){
                 System.err.println(e);
            }finally{
                DaoUtil.closeConection(cx, stmt, rs);
            }   
            return usus;
    }
    
        public Usuario leerIDRegistro(Usuario usu){
        Usuario usus = null;
        
            try{
                cx = DaoUtil.ConectionDriveDB();
                //stmt = cxprepareStatement("SELECT idUsuario, loginUsuario, passwordUsuario, rolUsuario FROM usuario WHERE  loginUsuario = ?");
                stmt = cx.prepareStatement("SELECT \"idUsuario\", \"loginUsuario\", \"passwordUsuario\", \"rolUsuario\" FROM public.usuario WHERE  \"loginUsuario\" = ?");
                stmt.setString(1, usu.getLoginUsuario());
                
                rs = stmt.executeQuery();
                while(rs.next()){
                    usus = new Usuario();
                    usus.setIdUsuario(rs.getInt("idUsuario"));
                    usus.setLoginUsuario(rs.getString("loginUsuario"));
                    usus.setPasswordUsuario(rs.getString("passwordUsuario"));
                    usus.setRolUsuario(rs.getString("rolUsuario"));
                    
                }
                
            }catch(Exception e){
                System.out.println(e);
            }finally{
                DaoUtil.closeConection(cx, stmt, rs);
            }   
            return usus;
    }
    
    public void modificar(Usuario usu){
        
        try{
            cx = DaoUtil.ConectionDriveDB();
            stmt = cx.prepareStatement("UPDATE public.usuario SET  \"passwordUsuario\"=?");
            stmt.setString(1, usu.getPasswordUsuario());                      
            stmt.setInt(2, usu.getIdUsuario());          
            stmt.executeUpdate();
        }catch(Exception e){
            System.err.println(e);
        }finally{
        DaoUtil.closeConection(cx, stmt, rs);
        }
    }
    
    public void eliminar(Usuario usu) {
        
        try{
            cx = DaoUtil.ConectionDriveDB();
            stmt = cx.prepareStatement("DELETE FROM public.usuario  WHERE \"idUsuario\" = ?");
            stmt.setInt(1, usu.getIdUsuario());          
            stmt.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }finally{
        DaoUtil.closeConection(cx, stmt, rs);
        }
    }    
}
