/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmosProcesador;

import java.util.ArrayList;
import modelo.Proceso;

/**
 *
 * @author ser
 */
public class Feedback {
   ArrayList<Proceso> ListaProcesos;
    ArrayList<Integer> ListaResultado;
    ArrayList<Integer> ListaActual;
    int tiempoActual = 0;
    int totalSumaRafagas = 0; 
    int Quantum = 0;
    
    public Feedback(ArrayList<Proceso> ListaProcesos, int Quantum){
       this.ListaProcesos = ListaProcesos;
       this.ListaResultado = new ArrayList<>();
       this.ListaActual = new ArrayList<>();
       this.Quantum = Quantum;
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
    
    //metodo para retornar el quantum
    public int getQuantum(){
     return Quantum;
    }
    
    //setear el tiempo actual del algoritmo  
    public void setTiempoActual(int tiempoActual){
        this.tiempoActual = tiempoActual;
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
    
    public void ElegirProcesosAEjecutar(){
       int con = 0;
       int Tamaño= ListaProcesos.size();
       while(con < Tamaño){
         if(ListaProcesos.get(con).getTiempoDeLlegada()<=tiempoActual 
                 && ListaProcesos.get(con).getAtendido()==0){
             
            ListaProcesos.get(con).setAtendido(1);
            ListaActual.add(0, con);
          
         }
           
       con++;
       }
    }
    
    public void Atender(){
      int bandera = 0;
      int dato = ListaProcesos.get(ListaActual.get(0)).getRafaga();
      if(dato-Quantum>0){
       for(int x = 0; x < Quantum ; x++){
         ListaResultado.add(ListaProcesos.get(ListaActual.get(0)).getNumeroProceso());
        }
       System.out.println("Proceso Atendido: "+ListaProcesos.get(ListaActual.get(0)).getNumeroProceso());
        ListaProcesos.get(ListaActual.get(0)).setRafaga(ListaProcesos.get(ListaActual.get(0)).getRafaga()-Quantum);
        ListaActual.add(ListaActual.get(0));
        ListaActual.remove(0);
        bandera = 1;
      }
      if(dato-Quantum<=0 && 
              bandera ==0){
        for(int x = 0; x < Quantum ; x++){
         ListaResultado.add(ListaProcesos.get(ListaActual.get(0)).getNumeroProceso());
        }
        System.out.println("Proceso Atendido: "+ListaProcesos.get(ListaActual.get(0)).getNumeroProceso());
        ListaProcesos.get(ListaActual.get(0)).setRafaga(0);
        ListaActual.remove(0);
      }
      tiempoActual = tiempoActual + Quantum;
    }

    public ArrayList<Proceso> getListaProcesos() {
        return ListaProcesos;
    }

    public void setListaProcesos(ArrayList<Proceso> ListaProcesos) {
        this.ListaProcesos = ListaProcesos;
    }

    public ArrayList<Integer> getListaResultado() {
        return ListaResultado;
    }

    public void setListaResultado(ArrayList<Integer> ListaResultado) {
        this.ListaResultado = ListaResultado;
    }

    public ArrayList<Integer> getListaActual() {
        return ListaActual;
    }

    public void setListaActual(ArrayList<Integer> ListaActual) {
        this.ListaActual = ListaActual;
    }

    public int getTiempoActual() {
        return tiempoActual;
    }
    
}
