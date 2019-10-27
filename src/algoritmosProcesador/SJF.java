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
    private ArrayList<Proceso> ListaProcesos;
    private ArrayList<Integer> ListaResultados;

    public SJF(ArrayList<Proceso> ListaProcesos){
        this.ListaProcesos= ListaProcesos;
        this.ListaResultados = new ArrayList<>();
    }
    private void ordenar(){
        Collections.sort(ListaProcesos, new Comparator<Proceso>() {
            public int compare(Proceso o1, Proceso o2) {
                return new Integer(o1.getRafaga()).compareTo(new Integer(o2.getRafaga()));
            }
        });
    }
    
    public void ejecutar(){
        ordenar();
        algoritmoFCFS();
    }
    private void algoritmoFCFS(){
        FCFS fcfs = new FCFS(ListaProcesos);
        fcfs.metodoTotalSumaRafagas();
        int tiempo = 0;
        int tiempoFinalizacion = fcfs.getTotalSumaRafagas();
        if(fcfs.retornarCantidadLlegada() > 0){
            for(int x =0 ; x < fcfs.retornarCantidadLlegada(); x++){
                fcfs.getListaResultados().add(0);
            }
            fcfs.setTiempoActual(fcfs.retornarCantidadLlegada());
            tiempoFinalizacion = tiempoFinalizacion + fcfs.retornarCantidadLlegada();
        }
        while(tiempo < tiempoFinalizacion){
            fcfs.ElegirProcesosAEjecutar();
            fcfs.AtenderProcesos();
            tiempo = fcfs.getTiempoActual();
        }
        //printear la lista con resultados
        //ListaResultados es lo que queria ud
        for(int x = 0; x < fcfs.getListaResultados().size(); x++){
            ListaResultados.add(fcfs.getListaResultados().get(x));
        }
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
    
}
