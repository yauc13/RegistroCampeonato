/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fut.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author YeisonUrrea
 */
public class TablaEquipos {
    private String Nombre;
    private int puntos;
    private int DF;
    private int GF;
    private int GC;
    private int PJ;
    private int PG;
    private int PE;
    private int PP;
    private Double prom;
    
    
    public int[] calcularPuntosPartido(Partido par, int idEquipo){
        int[] datosEquipo = new int[8];
        
        if(par.getIdEquipoA()==idEquipo){
            if(par.getGolA()>par.getGolB()){
                datosEquipo = llenarvector(3,par.getGolA()-par.getGolB(),par.getGolA(),par.getGolB(),1,1,0,0);                
            }
            if(par.getGolA()==par.getGolB()){
                datosEquipo = llenarvector(1,par.getGolA()-par.getGolB(),par.getGolA(),par.getGolB(),1,0,1,0);
            }
            if(par.getGolA()<par.getGolB()){
                datosEquipo = llenarvector(0,par.getGolA()-par.getGolB(),par.getGolA(),par.getGolB(),1,0,0,1);
            }
        }else if(par.getIdEquipoB()==idEquipo){
            if(par.getGolA()>par.getGolB()){
                datosEquipo = llenarvector(0,par.getGolB()-par.getGolA(),par.getGolB(),par.getGolA(),1,0,0,1);
            }
            if(par.getGolA()==par.getGolB()){
                datosEquipo = llenarvector(1,par.getGolB()-par.getGolA(),par.getGolB(),par.getGolA(),1,0,1,0);
            }
            if(par.getGolA()<par.getGolB()){
                datosEquipo = llenarvector(3,par.getGolB()-par.getGolA(),par.getGolB(),par.getGolA(),1,1,0,0);
            }
        }
        
        return datosEquipo;
    }
    
    private int[] llenarvector(int puntos,int DG,int GF,int GC,int PJ,int PG,int PE,int PP){
        int [] vector = new int[8];
        vector[0] = puntos;
        vector[1] = DG;
        vector[2] = GF;
        vector[3] = GC;
        vector[4] = PJ;
        vector[5] = PG;
        vector[6] = PE;
        vector[7] = PP;

        return vector;
    }
    
        public void ordenarTabla(List<TablaEquipos> tabla){
        //Collections.sort(tabla);
        Collections.sort(tabla, new Comparator<TablaEquipos>() {
                @Override
                public int compare(TablaEquipos b, TablaEquipos a) {
                    //int resultado = a.getApellido1().compareTo(b.getApellido1());
                    int resultado = a.puntos-b.puntos;
                    if (resultado != 0 ) {
                        return resultado;
                    }
                    resultado = a.DF-b.DF;
                    if (resultado != 0 ) {
                        return resultado;
                    }
                    resultado = a.GF-b.GF;
                    if (resultado != 0 ) {
                        return resultado;
                    } else {                        
                        return (int) (a.prom-b.prom);
                   }
                }
            });
    }
    
    

    

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getDF() {
        return DF;
    }

    public void setDF(int DF) {
        this.DF = DF;
    }

    public int getGF() {
        return GF;
    }

    public void setGF(int GF) {
        this.GF = GF;
    }

    public int getGC() {
        return GC;
    }

    public void setGC(int GC) {
        this.GC = GC;
    }

    public int getPG() {
        return PG;
    }

    public void setPG(int PG) {
        this.PG = PG;
    }

    public int getPE() {
        return PE;
    }

    public void setPE(int PE) {
        this.PE = PE;
    }

    public int getPP() {
        return PP;
    }

    public void setPP(int PP) {
        this.PP = PP;
    }

    public Double getProm() {
        return prom;
    }

    public void setProm(Double prom) {
        this.prom = prom;
    }

    

    public int getPJ() {
        return PJ;
    }

    public void setPJ(int PJ) {
        this.PJ = PJ;
    }

  
    
    
    
    
}
