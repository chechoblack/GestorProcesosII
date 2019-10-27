/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import Vista.vGestor;
import algoritmosProcesador.FCFS;
import algoritmosProcesador.HRRN;
import algoritmosProcesador.Prioridad;
import algoritmosProcesador.SJF;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author ser
 */
public class CPU {
    private Nucleo nucleo;
    private JTable tabla;
    private JScrollPane scroll;
    private ArrayList<Proceso> listaProcesos=new ArrayList<>();
    ArrayList<Integer> ListaResultado = new ArrayList<>();
    private ArrayList AlgoritmoP= new ArrayList();
    private HRRN hrrn;
    
    public CPU(Nucleo nucleo,ArrayList AlgoritmoP,ArrayList<Proceso> listaProcesos,JTable tabla,JScrollPane scroll) {
        this.nucleo=nucleo;
        this.listaProcesos=listaProcesos;
        this.AlgoritmoP=AlgoritmoP;
        this.tabla=tabla;
        this.scroll=scroll;
        seleccionAlgoritmo();
    }
    private void seleccionAlgoritmo(){
       if(AlgoritmoP.get(0).equals("FCFS")){
            algoritmoFCFS();
       }
       if(AlgoritmoP.get(0).equals("HRRN")){
            algoritmoHRRN();
       }
       if(AlgoritmoP.get(0).equals("SJF")){
            algoritmoSJF();
       }
       if(AlgoritmoP.get(0).equals("Prioridad")){
           algoritmoPrioridad();
       }
    }
    private void algoritmoFCFS(){
        FCFS fcfs = new FCFS(listaProcesos);
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
//            System.out.println(fcfs.getListaResultados().get(x));
            ListaResultado.add(fcfs.getListaResultados().get(x));
        }
        pintarAlgoritmo();    
    }
    private void algoritmoSJF(){
        SJF sjf = new SJF(listaProcesos);
        sjf.metodoTotalSumaRafagas();
        int tiempo = 0;
        int tiempoFinalizacion = sjf.getTotalSumaRafagas();
        while(tiempo < tiempoFinalizacion){
          sjf.ElegirProcesosAEjecutar();
          sjf.Atender();
          tiempo = sjf.tiempoActual;
        }
        //printear la lista con resultados
        //ListaResultados es lo que queria ud
        for(int x = 0; x < sjf.ListaResultados.size(); x++){
          ListaResultado.add(sjf.ListaResultados.get(x));
        }
        pintarAlgoritmo();
    }
    private void algoritmoHRRN(){
        hrrn=new HRRN(listaProcesos);
        hrrn.metodoTotalSumaRafagas();
        int tiempo=0;
        int tiempoFinalizacion= hrrn.getTotalSumaRafagas();
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

            int CantidadIngresoProceso = hrrn.getListaResultado().get(hrrn.getListaResultado().size()-1).getRafaga();
            int Cont = 0;
            while(Cont < CantidadIngresoProceso){
             ListaResultado.add(hrrn.getListaResultado().get(hrrn.getListaResultado().size()-1).getNumeroProceso());

             Cont++;
            }

            tiempo = tiempo+ hrrn.getListaResultado().get(hrrn.getListaResultado().size()-1).getRafaga();
            hrrn.setTiempoActual(tiempo);
        }
        System.out.println(ListaResultado.size());
        pintarAlgoritmo();
    }
    
    private void algoritmoPrioridad(){
        Prioridad prioridad = new Prioridad(listaProcesos);
        prioridad.metodoTotalSumaRafagas();
        int tiempo = 0;
        int tiempoFinalizacion = prioridad.getTotalSumaRafagas();
        while(tiempo < tiempoFinalizacion){
          prioridad.ElegirProcesosAEjecutar();
          prioridad.AtenderProceso();
          tiempo = prioridad.tiempoActual;
        }
        //printear la lista con resultados
        //ListaResultados es lo que queria ud
        for(int x = 0; x < prioridad.ListaResultados.size(); x++){
          ListaResultado.add(prioridad.ListaResultados.get(x));
        }
        pintarAlgoritmo();
    }
    private void verFinal(int pro,int posProceso){
        for(Proceso proceso : listaProcesos){
            if(pro==proceso.getNumeroProceso()){
                if(posProceso==proceso.getFinales()){
                    proceso.setEstado(3);
//                    JOptionPane.showMessageDialog(null,"Finalizo proceso"+proceso.getNumeroProceso());
                }
            }
        }
    }
    private void pintarAlgoritmo(){
        Thread procces = new Thread() {
            public void run() {
                int posProceso=1;
                for(Integer pro : ListaResultado){
                    for(int i=0;i<nucleo.getProcesos().size();i++){
                        if(pro==nucleo.getProcesos().get(i).getNumeroProceso()){
                            celdas(tabla,posProceso,i,scroll,pro);
                            verFinal(pro,posProceso);
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
    private void celdas(JTable tabla, int colum,int fila,JScrollPane scroll,int pro){
        tabla.getColumnModel().getColumn(colum).setCellRenderer(new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
            //Cells are by default rendered as a JLabel.
            DefaultTableModel modelo = (DefaultTableModel)tabla.getModel();
            modelo.setValueAt("X", fila, colum);
            modelo.setValueAt("Proceso"+pro, fila, 0);
            JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
            return l;
          }
        });
        scroll.repaint();
    }
}
