/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.dao;

import com.fut.model.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DIANA G
 */
public class UsuarioDao extends Dao {
    public boolean registrar(Usuario usu) throws Exception{
        boolean reg = false;
        try{
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("INSERT INTO usuario (loginUsuario, passwordUsuario, rolUsuario) values(?, ?, ?)");
            st.setString(1, usu.getLoginUsuario());
            st.setString(2, usu.getPasswordUsuario());
            st.setString(3, usu.getRolUsuario());
            st.executeUpdate();
            reg = true;
        }catch(Exception e){
            throw e;
        }finally{
        this.Cerrar();
        }
        return reg;
    }
        
    public List<Usuario> listar() throws Exception{
            List<Usuario> lista;
            ResultSet rs;
            
            try{
                this.Conectar();
                PreparedStatement st = this.getCn().prepareCall("SELECT idUsuario, loginUsuario, passwordUsuario, rolUsuario FROM usuario");
                rs = st.executeQuery();
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
                this.Cerrar();
            }
        
        return lista;   
    }
    
    public Usuario leerID(Usuario usu) throws Exception{
        Usuario usus = null;
        ResultSet rs;
            try{
                this.Conectar();
                PreparedStatement st = this.getCn().prepareStatement("SELECT idUsuario, loginUsuario, passwordUsuario, rolUsuario FROM usuario WHERE loginUsuario = ? and passwordUsuario = ?");
                st.setString(1, usu.getLoginUsuario());
                st.setString(2, usu.getPasswordUsuario());
                rs = st.executeQuery();
                while(rs.next()){
                    usus = new Usuario();
                    usus.setIdUsuario(rs.getInt("idUsuario"));
                    usus.setLoginUsuario(rs.getString("loginUsuario"));
                    usus.setPasswordUsuario(rs.getString("passwordUsuario"));
                    usus.setRolUsuario(rs.getString("rolUsuario"));
                    
                }
                
            }catch(Exception e){
                throw e;
            }finally{
                this.Cerrar();
            }   
            return usus;
    }
    
        public Usuario leerIDRegistro(Usuario usu) throws Exception{
        Usuario usus = null;
        ResultSet rs;
            try{
                this.Conectar();
                PreparedStatement st = this.getCn().prepareStatement("SELECT idUsuario, loginUsuario, passwordUsuario, rolUsuario FROM usuario WHERE loginUsuario = ?");
                st.setString(1, usu.getLoginUsuario());
                
                rs = st.executeQuery();
                while(rs.next()){
                    usus = new Usuario();
                    usus.setIdUsuario(rs.getInt("idUsuario"));
                    usus.setLoginUsuario(rs.getString("loginUsuario"));
                    usus.setPasswordUsuario(rs.getString("passwordUsuario"));
                    usus.setRolUsuario(rs.getString("rolUsuario"));
                    
                }
                
            }catch(Exception e){
                throw e;
            }finally{
                this.Cerrar();
            }   
            return usus;
    }
    
    public void modificar(Usuario usu) throws Exception{
        
        try{
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("UPDATE usuario SET passwordUsuario = ? WHERE idUsuario = ?");
            st.setString(1, usu.getPasswordUsuario());                      
            st.setInt(2, usu.getIdUsuario());          
            st.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
        this.Cerrar();
        }
    }
    
    public void eliminar(Usuario usu) throws Exception{
        
        try{
            this.Conectar();
            PreparedStatement st = this.getCn().prepareStatement("DELETE FROM usuario  WHERE idUsuario = ?");
            st.setInt(1, usu.getIdUsuario());          
            st.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
        this.Cerrar();
        }
    }    
}
