/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.util;

import com.fut.model.Grupo;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author YeisonUrrea
 */
public class Util {

    /**
     * retorna un objeto capturado desde el context mediante el id
     * @param id
     * @return 
     */
    public static final Object getObjectOfContext(String id){
        Object obj = null;
        obj = (Object) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(id);
        return obj;
    }
    
    public static final void setObjectOfContext(String id, Object obj){
       FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(id, obj);        
    }
    
    /**
     * Coloca mensaje en la vista
     * @param severity
     * @param summary
     * @param details
     */
    public static final void setMessage(FacesMessage.Severity severity,String summary, String details){       
       FacesMessage message = new FacesMessage(severity, summary, details);
       FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    /**
     *retorna la ip del equipo
     * @return
     */
    public static final String getIpAddress() {
        String address = "";
        InetAddress ip;
        try {
            ip = InetAddress.getLocalHost();
            address = ip.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.err.println("Error IP Address");
        }
        return address;
    }

    /**
     *retorna la mac del equipo
     * @return
     */
    public static final String getMacAddress() {
        String address = "";
        InetAddress ip;
        try {
            ip = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            byte[] mac = network.getHardwareAddress();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
            address = sb.toString();
        } catch (UnknownHostException | SocketException e) {
            e.printStackTrace();
            System.err.println("Error MAC Address");
        }
        return address;
    }

    
    
    
    
    
    
    /**
     * DEFAULTPHOTO foto por defecto en base 64 para la imagen del jugador
     */
    public static final String DEFAULTPHOTO="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";
    
    
}
