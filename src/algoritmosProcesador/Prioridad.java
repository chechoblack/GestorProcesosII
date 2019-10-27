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
public class Prioridad {
     public ArrayList<Proceso> ListaProcesos; 
    public ArrayList<Integer> ListaResultados;
    public int totalSumaRafagas = 0;
    public int tiempoActual = 0;

    public Prioridad(ArrayList<Proceso> ListaProcesos){
        this.ListaProcesos = ListaProcesos;
        this.ListaResultados = new ArrayList<>();
    }

    public int retornarCantidadLlegada(){
         return ListaProcesos.get(0).getTiempoDeLlegada();
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

    public void AtenderProceso(){
        int Cont = 0;
        int Tamaño = ListaProcesos.size();
        int PosMejor = 0;
        int PrioridadMejor = 0;
        while(Cont < Tamaño){
          if(ListaProcesos.get(Cont).getAtendiendo()==1){
            if(PrioridadMejor==0){
             PrioridadMejor=ListaProcesos.get(Cont).getPrioridad();
             PosMejor = Cont;
            }
            if(PrioridadMejor>0){
              if(PrioridadMejor==ListaProcesos.get(Cont).getPrioridad()){

              }
              if(PrioridadMejor>ListaProcesos.get(Cont).getPrioridad()){
                PosMejor = Cont;
                PrioridadMejor = ListaProcesos.get(Cont).getPrioridad();
              }

            }

          }

         Cont++;
        }
        if(ListaProcesos.get(PosMejor).getRafaga()-1 ==0){
          ListaProcesos.get(PosMejor).setAtendido(1);
          ListaProcesos.get(PosMejor).setAtendiendo(0);
          ListaProcesos.get(PosMejor).setRafaga(ListaProcesos.get(PosMejor).getRafaga()-1);
          ListaResultados.add(ListaProcesos.get(PosMejor).getNumeroProceso());
          System.out.println("Proceso Atendido: "+ ListaProcesos.get(PosMejor).getNumeroProceso());
        }
        if(ListaProcesos.get(PosMejor).getRafaga()-1 > 0){
          ListaProcesos.get(PosMejor).setRafaga(ListaProcesos.get(PosMejor).getRafaga()-1);
          ListaResultados.add(ListaProcesos.get(PosMejor).getNumeroProceso());
          System.out.println("Proceso Atendido: "+ ListaProcesos.get(PosMejor).getNumeroProceso());

        }
        tiempoActual = tiempoActual + 1;
    }
}
