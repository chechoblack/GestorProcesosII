/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import algoritmosProcesador.FCFS;
import algoritmosProcesador.HRRN;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

/**
 *
 * @author ser
 */
public class CPU {
    private Nucleo nucleo;
    private JTable tabla;
    private ArrayList<Proceso> listaProcesos=new ArrayList<>();
    ArrayList<Integer> ListaResultado = new ArrayList<>();
    private ArrayList AlgoritmoP= new ArrayList();
    private HRRN hrrn;
    
    public CPU(Nucleo nucleo,ArrayList AlgoritmoP,ArrayList<Proceso> listaProcesos,JTable tabla) {
        this.nucleo=nucleo;
        this.listaProcesos=listaProcesos;
        this.AlgoritmoP=AlgoritmoP;
        this.tabla=tabla;
        seleccionAlgoritmo();
    }
    private void seleccionAlgoritmo(){
       if(AlgoritmoP.get(0).equals("FCFS")){
            algoritmoFCFS();
       }
       if(AlgoritmoP.get(0).equals("HRRN")){
            algoritmoHRRN();
       }
    }
    private void algoritmoFCFS(){
        FCFS fcfs = new FCFS(listaProcesos);
        fcfs.metodoTotalSumaRafagas();
        int tiempo = 0;
        int tiempoFinalizacion = fcfs.getTotalSumaRafagas();
        System.out.println("------------------------");
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
            System.out.println("El tiempo actual es: "+tiempo);
            System.out.println("------------------------");
        }
        //printear la lista con resultados
        //ListaResultados es lo que queria ud
        for(int x = 0; x < fcfs.getListaResultados().size(); x++){
            ListaResultado.add(fcfs.getListaResultados().get(x));
        }
        for(int i=0;i<ListaResultado.size();i++){
            System.out.println(ListaResultado.get(i));
        }
            
    }
    private void algoritmoHRRN(){
        System.out.println(listaProcesos.size());
        hrrn=new HRRN(listaProcesos);
        hrrn.metodoTotalSumaRafagas();
        int tiempo=0;
        int tiempoFinalizacion= hrrn.getTotalSumaRafagas();
        System.out.println("------------------------");
        if(hrrn.retornarCantidadLlegada() > 0){
            for(int x =0 ; x < hrrn.retornarCantidadLlegada(); x++){
                ListaResultado.add(0);
            }
            tiempo = tiempo + hrrn.retornarCantidadLlegada();
            tiempoFinalizacion = tiempoFinalizacion + hrrn.retornarCantidadLlegada();
        }
        while(tiempo<=tiempoFinalizacion){
            hrrn.ElegirProcesoAEjecutar();
            hrrn.Funcionamiento();
            hrrn.ComprobarMejorDeLista();
//                System.out.println("El mejor proceso actual es: "+hrrn.getListaResultado().get(hrrn.getListaResultado().size()-1).getNumeroProceso());

            int CantidadIngresoProceso = hrrn.getListaResultado().get(hrrn.getListaResultado().size()-1).getRafaga();
            int Cont = 0;
            while(Cont < CantidadIngresoProceso){
             ListaResultado.add(hrrn.getListaResultado().get(hrrn.getListaResultado().size()-1).getNumeroProceso());

             Cont++;
            }

            System.out.println("Tiempo del proceso:" +hrrn.getListaResultado().get(hrrn.getListaResultado().size()-1).getRafaga());
            tiempo = tiempo+ hrrn.getListaResultado().get(hrrn.getListaResultado().size()-1).getRafaga();
            hrrn.setTiempoActual(tiempo);
            System.out.println("El tiempo actual es: "+tiempo);
            System.out.println("------------------------");
        }
        System.out.println(ListaResultado.size());
        for(int x = 0; x < ListaResultado.size()-1; x++){
            System.out.println(ListaResultado.get(x));
        }
//            pintarAlgoritmo();
    }
    
    private void pintarAlgoritmo(){
//        System.out.println("llamo");
        Thread procces = new Thread() {
            public void run() {
                int posProceso=1;
                for(Integer pro : ListaResultado){
                    for(int i=0;i<nucleo.getProcesos().size();i++){
//                        System.out.println(pro+"="+nucleo.getProcesos().get(i).getNumeroProceso());
//                        System.out.println(pro==nucleo.getProcesos().get(i).getNumeroProceso());
                        if(pro==nucleo.getProcesos().get(i).getNumeroProceso()){
//                            System.out.println("fila= "+i);
//                            System.out.println("Columna= "+posProceso);
                            celdas(tabla,posProceso,i);
//                            System.out.println("pasa");
                            int inicio=(int) System.currentTimeMillis();
                            while((int) System.currentTimeMillis()-inicio<1000);
                            posProceso++;
                        }
                    }
                }
            }
        };
        procces.start();
    }
    private void celdas(JTable tabla, int colum,int fila){
        System.out.println("fila= "+fila);
        System.out.println("Columna= "+colum);
        System.out.println();
        for(int i=0;i<ListaResultado.size();i++){
            TableColumn columna = tabla.getColumnModel().getColumn(colum);// selecciono la columna que me interesa de la tabla
            EditorCeldas TableCellRenderer = new EditorCeldas();
            TableCellRenderer.setColumns(colum); //se le da por parametro la columna que se quiere modificar
            TableCellRenderer.setRow(fila);//se le da por parametro la fila que se quiere modificar
            columna.setCellRenderer(TableCellRenderer); // le aplico la edicion
        }
    }
}
