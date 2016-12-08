package caminocortoarray;

import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Christian
 */
public class Tabla {
    public int TABLERO[][];
    public int DIMENSIONES[];  
    
    public Tabla(int[] dimensiones) {
        this.DIMENSIONES = dimensiones ;
        this.TABLERO = new int[this.DIMENSIONES[0]][this.DIMENSIONES[1]];
        this.Rellenar(TABLERO); 
    }
    
    
    public final void Rellenar(int[][] param){
        Random rnd = new Random();
        for(int x=0;x < param.length;x++){
            for(int z=0;z < param[x].length;z++){
                param[x][z] = rnd.nextInt(10)+1;
            }
        }
    }
    @Override
    public String toString(){
        StringBuilder st = new StringBuilder();
        st.append("\nDimensiones: ")
                .append(this.DIMENSIONES[0])
                .append("x")
                .append(this.DIMENSIONES[1]);
        st.append("\nArea Total: ")
                .append(this.DIMENSIONES[0]*this.DIMENSIONES[1]);
        st.append("\n-----Array-----");
        for(int x=0;x < this.TABLERO.length;x++){
            st.append("\n{");
            for(int z=0;z < this.TABLERO[x].length;z++){
                st.append(" ")
                        .append(this.TABLERO[x][z])
                        .append(" ");
            }
            st.append("}");
        }
        st.append("\n-----Array-----\n"); 
        return st.toString();
    }
    public int[] getDimensiones(){
        return this.DIMENSIONES;
    }
    
    public int[][] getTabla(){
        return this.TABLERO;
    }
    
    public static void Mostrar(int param){ 
        System.out.println(param);
    }
}
