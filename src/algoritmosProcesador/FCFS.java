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
public class FCFS {
    private ArrayList<Proceso> ListaProcesos;
    private ArrayList<Integer> ListaResultados;
    private int totalSumaRafagas;
    private int tiempoActual;
    private int finales=0;
    public FCFS(ArrayList<Proceso> ListaProcesos){
        this.ListaProcesos= ListaProcesos;
        this.ListaResultados = new ArrayList<>();
        this.totalSumaRafagas=0;
        this.tiempoActual=0;
    }

    public ArrayList<Proceso> getListaProcesos() {
        return ListaProcesos;
    }

    public void setListaProcesos(ArrayList<Proceso> ListaProcesos) {
        this.ListaProcesos = ListaProcesos;
    }

    public ArrayList<Integer> getListaResultados() {
        return ListaResultados;
    }

    public void setListaResultados(ArrayList<Integer> ListaResultados) {
        this.ListaResultados = ListaResultados;
    }

    public int getTotalSumaRafagas() {
        return totalSumaRafagas;
    }

    public void setTotalSumaRafagas(int totalSumaRafagas) {
        this.totalSumaRafagas = totalSumaRafagas;
    }

    public int getTiempoActual() {
        return tiempoActual;
    }

    public void setTiempoActual(int tiempoActual) {
        this.tiempoActual = tiempoActual;
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
    public void ejecutarAlgoritmo(){
        metodoTotalSumaRafagas();
    }
    //metodo para seleccionar procesos a atender dependiendo de su rafaga
    public void ElegirProcesosAEjecutar(){
        for(Proceso pro :ListaProcesos){
            if(pro.getTiempoDeLlegada()<=tiempoActual && 
                pro.getAtendido()==0){
                pro.setAtendiendo(1);
            }
        }
    }
    
    public void AtenderProcesos(){
        int Tamaño = ListaProcesos.size();
        System.out.println("tamaño lista "+ListaResultados.size());
        for(Proceso pro: ListaProcesos){
            finales=pro.getRafaga();
            if(pro.getAtendiendo()==1){
                pro.setAtendido(1);
                pro.setAtendiendo(0);
                int CantidadIngresoProceso = pro.getRafaga();
                int Contador = 0;
                System.out.println("cantida procesos= "+CantidadIngresoProceso);
                while(Contador < CantidadIngresoProceso){
                    ListaResultados.add(pro.getNumeroProceso());
                    pro.setFinales(finales);
                    Contador++;
                    finales--;
                     System.out.println("tamaño lista2 "+ListaResultados.size());
                }
                tiempoActual = tiempoActual +pro.getRafaga();
                pro.setFinales(tiempoActual);
                break;
            }
        }
    }
}
