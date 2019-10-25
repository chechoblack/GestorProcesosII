/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

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
        if(AlgoritmoP.get(0).equals("HRRN")){
            hrrn=new HRRN(listaProcesos);
            hrrn.metodoTotalSumaRafagas();
            int tiempo=0;
            int tiempoFinalizacion= hrrn.getTotalSumaRafagas();
            System.out.println("------------------------");
            while(tiempo<=tiempoFinalizacion){
                hrrn.ElegirProcesoAEjecutar();
                hrrn.Funcionamiento();
                hrrn.ComprobarMejorDeLista();
                System.out.println("El mejor proceso actual es: "+hrrn.getListaResultado().get(hrrn.getListaResultado().size()-1).getNumeroProceso());
                System.out.println("Tiempo del proceso:" +hrrn.getListaResultado().get(hrrn.getListaResultado().size()-1).getRafaga());
                tiempo = tiempo+ hrrn.getListaResultado().get(hrrn.getListaResultado().size()-1).getRafaga();
                hrrn.setTiempoActual(tiempo);
                System.out.println("El tiempo actual es: "+tiempo);
                System.out.println("------------------------");
            }
//            pintarAlgoritmo();
        }
    }
    private void pintarAlgoritmo(){
        System.out.println("llamo");
        Thread procces = new Thread() {
            public void run() {
                int posProceso=0;
                for(Proceso pro : hrrn.getListaResultado()){
                    for(int i=0;i<nucleo.getProcesos().size();i++){
                        System.out.println("queda");
                        if(pro.getNumeroProceso()==nucleo.getProcesos().get(i).getNumeroProceso()){
                            celdas(tabla,i,posProceso);
                            System.out.println("pasa");
                            int inicio=(int) System.currentTimeMillis();
                            while((int) System.currentTimeMillis()-inicio<1000);
                        }
                    }
                }
            }
        };
        procces.start();
    }
    private void celdas(JTable tabla, int colum,int fila){
        for(int i=0;i<5;i++){
            TableColumn columna = tabla.getColumnModel().getColumn(colum);// selecciono la columna que me interesa de la tabla
            EditorCeldas TableCellRenderer = new EditorCeldas();
            TableCellRenderer.setColumns(colum); //se le da por parametro la columna que se quiere modificar
            TableCellRenderer.setRow(fila);//se le da por parametro la fila que se quiere modificar
            columna.setCellRenderer(TableCellRenderer); // le aplico la edicion
        }
    }
}
