/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmosProcesador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import modelo.Proceso;

/**
 *
 * @author ser
 */
public class SJF {
    
    public ArrayList<Proceso> ListaProcesos; 
    public ArrayList<Integer> ListaResultados;
    public int totalSumaRafagas = 0;
    public int tiempoActual = 0;
     
    public SJF(ArrayList<Proceso> ListaProcesos){
       this.ListaProcesos = ListaProcesos;
       this.ListaResultados = new ArrayList<>();
    }
    
    //metodo para saber donde inicia el primer proceso
     public int retornarCantidadLlegada(){
      int Cont = 0;
      int Tamaño = ListaProcesos.size();
      int PosLlegadaMasBaja = 0;
      int LlegadaMasBaja = -1;
      while(Cont < Tamaño){
          if(LlegadaMasBaja >= 0){
            if(LlegadaMasBaja > ListaProcesos.get(Cont).getTiempoDeLlegada()){
              LlegadaMasBaja=ListaProcesos.get(Cont).getTiempoDeLlegada();
              PosLlegadaMasBaja=Cont;
            }
          }
          if(LlegadaMasBaja == -1){
           LlegadaMasBaja=ListaProcesos.get(0).getTiempoDeLlegada();
           PosLlegadaMasBaja=0;
          }
      Cont++;
      }
      return LlegadaMasBaja;
    }
    
    // metodo que suma todas las rafagas de los procesos
    public void metodoTotalSumaRafagas(){
    int Tamaño= ListaProcesos.size();
    int i = 0;
    while (i < Tamaño) {
       this.totalSumaRafagas = totalSumaRafagas + ListaProcesos.get(i).getRafaga();
       i++;
    }
    }
    
    //metodo para retornar el total de la suma de las rafagas de los procesos
    public int getTotalSumaRafagas(){
       return totalSumaRafagas;
    }
    
    //setear el tiempo actual del algoritmo  
    public void setTiempoActual(int tiempoActual){
       this.tiempoActual = tiempoActual;
    }
    
    //metodo para seleccionar procesos a atender dependiendo de su rafaga
    public void ElegirProcesosAEjecutar(){
       int con = 0;
       int Tamaño= ListaProcesos.size();
       while(con < Tamaño){
        if(ListaProcesos.get(con).getTiempoDeLlegada()<=tiempoActual && 
                ListaProcesos.get(con).getAtendido()==0){
         ListaProcesos.get(con).setAtendiendo(1);
         }
       con++;
       }
    }
    
    public void Atender(){
    int Cont = 0;
    int Tamaño = ListaProcesos.size();
    int PosMejor = 0;
    int RafagaMejor = 0;
    while(Cont < Tamaño){
      if(ListaProcesos.get(Cont).getAtendiendo()==1){
        if(RafagaMejor==0){
         RafagaMejor=ListaProcesos.get(Cont).getRafaga();
         PosMejor = Cont;
        }
        if(RafagaMejor>0){
          if(RafagaMejor==ListaProcesos.get(Cont).getRafaga()){
            
          }
          if(RafagaMejor>ListaProcesos.get(Cont).getRafaga()){
            PosMejor = Cont;
            RafagaMejor = ListaProcesos.get(Cont).getRafaga();
          }
                      
        }
        
      }
        
     Cont++;
    }
    int dato = ListaProcesos.get(PosMejor).getRafaga();
    if(dato-1 ==0){
      ListaProcesos.get(PosMejor).setAtendido(1);
      ListaProcesos.get(PosMejor).setAtendiendo(0);
      ListaProcesos.get(PosMejor).setRafaga(ListaProcesos.get(PosMejor).getRafaga()-1);
      ListaResultados.add(ListaProcesos.get(PosMejor).getNumeroProceso());
      System.out.println("Proceso Atendido: "+ ListaProcesos.get(PosMejor).getNumeroProceso());
    }
    if(dato-1 > 0){
      ListaProcesos.get(PosMejor).setRafaga(ListaProcesos.get(PosMejor).getRafaga()-1);
      ListaResultados.add(ListaProcesos.get(PosMejor).getNumeroProceso());
      System.out.println("Proceso Atendido: "+ ListaProcesos.get(PosMejor).getNumeroProceso());
    
    }
    
     tiempoActual = tiempoActual + 1;
    
    }
    
}