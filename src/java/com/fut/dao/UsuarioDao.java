/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.dao;

import com.fut.model.Usuario;
import com.fut.util.SqlAdminFutSal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            this.ConectionDataBase();
            //PreparedStatement st = this.getCn().prepareStatement("INSERT INTO usuario (loginUsuario, passwordUsuario, rolUsuario) values(?, ?, ?)");
            PreparedStatement st = this.getCn().prepareStatement("INSERT INTO public.usuario(\"loginUsuario\",\"passwordUsuario\",\"rolUsuario\") VALUES (?, ?, ?);");
            st.setString(1, usu.getLoginUsuario());
            st.setString(2, usu.getPasswordUsuario());
            st.setString(3, usu.getRolUsuario());
            st.executeUpdate();
            reg = true;
        }catch(Exception e){
            throw e;
        }finally{
        this.CloseConection();
        }
        return reg;
    }
        
    public List<Usuario> listar() throws Exception{
            List<Usuario> lista;
            ResultSet rs;
            
            try{
                this.ConectionDataBase();
                //PreparedStatement st = this.getCn().prepareCall("SELECT idUsuario, loginUsuario, passwordUsuario, rolUsuario FROM usuario");
                PreparedStatement st = this.getCn().prepareCall("SELECT \"idUsuario\", \"loginUsuario\", \"passwordUsuario\", \"rolUsuario\" FROM public.usuario");
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
                this.CloseConection();
            }
        
        return lista;   
    }
    
    public Usuario leerID(Usuario usu){
        Usuario usus = null;
        ResultSet rs;
            try{
                this.ConectionDataBase();
                if(this.cn !=null){
                //PreparedStatement st = this.getCn().prepareStatement("SELECT idUsuario, loginUsuario, passwordUsuario, rolUsuario FROM usuario WHERE loginUsuario = ? and passwordUsuario = ?");
                PreparedStatement st = this.getCn().prepareStatement(SqlAdminFutSal.GET_LOGIN_USER);
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
                }
            }catch(SQLException e){
                 System.err.println(e);
            }finally{
                this.CloseConection();
            }   
            return usus;
    }
    
        public Usuario leerIDRegistro(Usuario usu) throws Exception{
        Usuario usus = null;
        ResultSet rs;
            try{
                this.ConectionDataBase();
                //PreparedStatement st = this.getCn().prepareStatement("SELECT idUsuario, loginUsuario, passwordUsuario, rolUsuario FROM usuario WHERE  loginUsuario = ?");
                PreparedStatement st = this.getCn().prepareStatement("SELECT \"idUsuario\", \"loginUsuario\", \"passwordUsuario\", \"rolUsuario\" FROM public.usuario WHERE  \"loginUsuario\" = ?");
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
                this.CloseConection();
            }   
            return usus;
    }
    
    public void modificar(Usuario usu) throws Exception{
        
        try{
            this.ConectionDataBase();
            PreparedStatement st = this.getCn().prepareStatement("UPDATE public.usuario SET  \"passwordUsuario\"=?");
            st.setString(1, usu.getPasswordUsuario());                      
            st.setInt(2, usu.getIdUsuario());          
            st.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
        this.CloseConection();
        }
    }
    
    public void eliminar(Usuario usu) throws Exception{
        
        try{
            this.ConectionDataBase();
            PreparedStatement st = this.getCn().prepareStatement("DELETE FROM public.usuario  WHERE \"idUsuario\" = ?");
            st.setInt(1, usu.getIdUsuario());          
            st.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
        this.CloseConection();
        }
    }    
}
