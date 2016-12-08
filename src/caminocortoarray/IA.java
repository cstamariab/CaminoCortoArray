/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caminocortoarray;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;


/**
 *
 * @author Christian
 */
public final class IA extends Tabla{
    
     
    private int Menor = -1;
    private List<List> mejores_caminos;
    
    /**
     *
     * @param i
     */
    public IA(int i[]) {
        super(i);
        Imprimir_Matriz();
    }
 
 
    
    public void Imprimir_Matriz(){
        Msj(this.toString(), true);
    }
    
    public void inicia_recorrido() {
        mejores_caminos = new ArrayList<>();
        calcula_caminos(new int[]{0,0} , new int[]{0,0},0,new ArrayList<>(),"");
        Msj("FIN\n",true);
        
    }
    public void calcula_caminos(int posAnterior[], int posActual[], int total, List<int[]> Lista, String Cadena){
               
        if(ultima_casilla(posActual)){
            total += TABLERO[posActual[0]][posActual[1]]; 
            Lista.add(posActual);
            if(verificaLimitesArray(total) || esMovimientoDiagonal(posActual,posAnterior)){
               return;
            }else if(Menor == -1 || total <= Menor){               
                    if(Menor != -1 && total < Menor){
                        this.mejores_caminos.clear();
                        Msj("Nuevo mejor camino encontrado",true);
                    }else if(Menor != -1 && total == Menor){
                        Msj("Mejor camino acumulado",true);
                    }else{
                        Msj("Primer mejor camino",true);
                    }              
                Menor = total;
            }
           
            Msj(Cadena+"\nPosicion: X:"+posActual[0]+", Y:"+posActual[1]+""
                    + " Valor Casilla: "+TABLERO[posActual[0]][posActual[1]]
                    + " Total: "+total
                    +"\n TOTAL ACUMULADO: "+total
                    +"\n ::::::::::::::: ", true);
            Msj("\nCAMINO RECORRIDO:",false);
            int[] aux;
            for(int i=0,m=Lista.size() ; i <m ;i++){
                aux = Lista.get(i);
                Msj(" {"+aux[0]+","+aux[1]+"}",false);
            }
            Msj("\n ----------------------------- ",true);            
         
            this.mejores_caminos.add(Lista);           
            return; 
        }
        if(!Limite_M(posActual)){
            total += TABLERO[posActual[0]][posActual[1]]; 
          
            Cadena +=("\nPosicion: X:"+posActual[0]+",Y:"+posActual[1]+" Valor Casilla: " +
                        TABLERO[posActual[0]][posActual[1]])
                        + " Total: "+total;
            /** </COMENTARIOS/> **/
            if(verificaLimitesArray(total) || esMovimientoDiagonal(posActual,posAnterior)){
               return;
            } 
            List<int[]> ls = new ArrayList<>();
            ls.addAll(Lista);
            ls.add(posActual);
            //Lista.clear();
            //Lista = null;
            int S[] = new int[]{posActual[0],posActual[1]+1};
            int D[] = new int[]{posActual[0]+1,posActual[1]};
            calcula_caminos(posActual, D,total, ls, Cadena);
            calcula_caminos(posActual, S,total, ls,Cadena);
        }
    }    

    
    public static void Recorrer(int[][] param, Object clase, String funcion, boolean EnviarPos){
        for(int x=0;x < param.length;x++){
            for(int z=0;z < param[x].length;z++){
                if(EnviarPos)
                    IA.ToDo(clase, funcion,new Object[]{x,z}, (Object)param[x][z]);
                else{
                    IA.ToDo(clase, funcion,null, null);
                }
            }
        }
    }
    
    public static void ToDo(Object x,String mtd, Object[] args, Object ob){
        try {
            if(args.length > 0){
                Class[] a = new Class[2];
                a[0] = int[].class;
                a[1] = int.class;
                int[] z = new int[]{(int)args[0],(int)args[1]};
                x.getClass().getMethod(mtd, a).invoke(x, z , ob);
            }else{
                x.getClass().getMethod(mtd, (Class)null).invoke(x, (Object)null);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException ex) {
           
        }
    }
    
    public static boolean esMenor(int A, int B){
        return A < B;
    }

    @Override
    public String toString(){
        StringBuilder st = new StringBuilder();
        st.append(super.toString());
        return st.toString();
    }
    private void Msj(String s, boolean line){
            if(line)
                System.out.println(s);
            else
                System.out.print(s);
    }
    
    public void Imprimir_Mejores_Caminos(){
        if(this.mejores_caminos.size() <= 0){
            Msj("Sin caminos disponibles",true);
            return;
        } 
        
            Msj("Mejores caminos encontrados: "+mejores_caminos.size(),true);
            List<int[]> l;
            int aux[];
            Msj("Mejores caminos:",true);
            for(int m = this.mejores_caminos.size(), i=0;i<m;i++){
                Msj((i+1)+":",false);
                l = this.mejores_caminos.get(i);
                for(int c = l.size(), j=0;j<c;j++ ){
                    aux = l.get(j);
                    Msj(" {"+aux[0]+","+aux[1]+"}",false);
                }
                Msj("",true);
            }
        
    }
    private boolean ultima_casilla(int x[]){
        return ((x[0] == TABLERO.length-1) && (x[1] == TABLERO[x[0]].length-1));
    } 
    private boolean verificaLimitesArray(int acum){
        return (Menor != -1 && acum > Menor);
    }
    private boolean esMovimientoDiagonal(int[] pos1, int[] pos2){
        return (pos1[0] == pos2[0]+1 && pos1[1] == pos2[1]+1)
                || (pos1[0] == pos2[0]-1 && pos1[1] == pos2[1]-1)
                || (pos1[0] == pos2[0]-1 && pos1[1] == pos2[1]+1)
                || (pos1[0] == pos2[0]+1 && pos1[1] == pos2[1]-1);
    }
    private boolean Limite_M(int x[]){
      return ((x[0] < 0 || x[0] > TABLERO.length-1) || (x[1] < 0 || x[1] > TABLERO[x[0]].length-1)) ;
    }
}
