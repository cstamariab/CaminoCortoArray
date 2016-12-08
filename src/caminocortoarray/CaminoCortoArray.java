/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caminocortoarray;

import java.nio.Buffer;
import java.util.Scanner;

/**
 *
 * @author Christian
 */
public class CaminoCortoArray {

    /**
     * @param args the command line arguments
     */
    int dimensiones[]; 
    public static void Iniciar(){
           
            String X = "";
            String Y = "";
            Scanner entradaEscaner = new Scanner (System.in); //Creación de un objeto Scanner
            System.out.println ("Por favor introduzca Dimensiones  X:");
            X = entradaEscaner.nextLine (); //Invocamos un método sobre un objeto Scanner
             System.out.println ("Por favor introduzca Dimensiones Y:");
            Y = entradaEscaner.nextLine (); //Invocamos un método sobre un objeto Scanner
           int dim_X = Integer.parseInt(X);
           int dim_Y = Integer.parseInt(Y);
           int dimensiones[] = new int[]{dim_X,dim_Y};            
            IA ia = new IA(dimensiones);            
            ia.inicia_recorrido();
           
            ia.Imprimir_Mejores_Caminos();       
    }
    public static void main(String[] args) {
         Iniciar();
    }
    
}
