/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import Vista.vGestor;
import algoritmosProcesador.FCFS;
import algoritmosProcesador.Feedback;
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
        if(AlgoritmoP.get(0).equals("Feedback")){
            algoritmoFeedback();
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
//            System.out.println("------------------------");
            if(sjf.retornarCantidadLlegada() > 0){
             for(int x =0 ; x < sjf.retornarCantidadLlegada(); x++){
               sjf.ListaResultados.add(0);
             }
             sjf.setTiempoActual(sjf.retornarCantidadLlegada());
             tiempoFinalizacion = tiempoFinalizacion + sjf.retornarCantidadLlegada();
             }
            while(tiempo < tiempoFinalizacion){
              sjf.ElegirProcesosAEjecutar();
              sjf.Atender();
              tiempo = sjf.tiempoActual;
//              System.out.println("El tiempo actual es: "+tiempo);
//              System.out.println("------------------------");
            }
            //printear la lista con resultados
            //ListaResultados es lo que queria ud
            for(int x = 0; x < sjf.ListaResultados.size(); x++){
              ListaResultado.add(sjf.ListaResultados.get(x));
            }
            
            /////////Datos de finalizacion
        ArrayList<ArrayList<Integer>> ListaConDatoFinalizacion = new ArrayList<>();
        ArrayList<Integer> Temporal;
        int DatoPos = 0;
        for(int p = 0; p < listaProcesos.size(); p ++){
            Temporal = new ArrayList<>();
            for(int x = 0; x < sjf.ListaResultados.size(); x++){
               if(sjf.ListaResultados.get(x)==listaProcesos.get(p).getNumeroProceso()){
                   
                   DatoPos=x+1;
               }
            }
            Temporal.add(listaProcesos.get(p).getNumeroProceso());          
            Temporal.add(listaProcesos.get(p).getTiempoDeLlegada());
            Temporal.add(DatoPos);
            ListaConDatoFinalizacion.add(Temporal);
        }
        for(int i=0;i<ListaConDatoFinalizacion.size();i++){
            for(int j = 0;j<ListaConDatoFinalizacion.size();j++){
                if(ListaConDatoFinalizacion.get(i).get(0)==listaProcesos.get(j).getNumeroProceso()){
                    listaProcesos.get(j).setFinales(ListaConDatoFinalizacion.get(i).get(2));
                }
            }
        }
        pintarAlgoritmo();
    }
    private void algoritmoHRRN(){
        HRRN hrrn = new HRRN(listaProcesos);
        hrrn.metodoTotalSumaRafagas();
        int tiempo=0;
        int tiempoFinalizacion= hrrn.getTotalSumaRafagas();
//        System.out.println("------------------------");
        
        if(hrrn.retornarCantidadLlegada() > 0){
         for(int x =0 ; x < hrrn.retornarCantidadLlegada(); x++){
           ListaResultado.add(0);
         }
         tiempo = tiempo + hrrn.retornarCantidadLlegada();
         tiempoFinalizacion = tiempoFinalizacion + hrrn.retornarCantidadLlegada();
        }
        
        while(tiempo<tiempoFinalizacion){
          hrrn.ElegirProcesosAEjecutar();
          hrrn.Funcionamiento();
          hrrn.ComprobarMejorDeLista();
//          System.out.println("El mejor proceso actual es: "+hrrn.ListaResultado.get(hrrn.ListaResultado.size()-1).getNumeroProceso());
          
          int CantidadIngresoProceso = hrrn.ListaResultado.get(hrrn.ListaResultado.size()-1).getRafaga();
          int Cont = 0;
          while(Cont < CantidadIngresoProceso){
           ListaResultado.add(hrrn.ListaResultado.get(hrrn.ListaResultado.size()-1).getNumeroProceso());
              
           Cont++;
          }
          
//          System.out.println("Tiempo del proceso:" +hrrn.ListaResultado.get(hrrn.ListaResultado.size()-1).getRafaga());
          tiempo = tiempo+ hrrn.ListaResultado.get(hrrn.ListaResultado.size()-1).getRafaga();
          hrrn.setTiempoActual(tiempo);
//          System.out.println("El tiempo actual es: "+tiempo);
//          System.out.println("------------------------");
        }
        for(int x = 0; x < ListaResultado.size()-1; x++){
           System.out.println(ListaResultado.get(x));
        }
        
//        System.out.println("------------------------------");
        /////////Datos de finalizacion
        ArrayList<ArrayList<Integer>> ListaConDatoFinalizacion = new ArrayList<>();
        ArrayList<Integer> Temporal;
        int DatoPos = 0;
        for(int p = 0; p < listaProcesos.size(); p ++){
            Temporal = new ArrayList<>();
            for(int x = 0; x < ListaResultado.size(); x++){
               if(ListaResultado.get(x)==listaProcesos.get(p).getNumeroProceso()){
                   
                   DatoPos=x+1;
               }
            }
            Temporal.add(listaProcesos.get(p).getNumeroProceso());          
            Temporal.add(listaProcesos.get(p).getTiempoDeLlegada());
            Temporal.add(DatoPos);
            ListaConDatoFinalizacion.add(Temporal);
        }
        for(int i=0;i<ListaConDatoFinalizacion.size();i++){
            for(int j = 0;j<ListaConDatoFinalizacion.size();j++){
                if(ListaConDatoFinalizacion.get(i).get(0)==listaProcesos.get(j).getNumeroProceso()){
                    listaProcesos.get(j).setFinales(ListaConDatoFinalizacion.get(i).get(2));
                }
            }
        }
        pintarAlgoritmo();
    }
    
    private void algoritmoPrioridad(){
         Prioridad prioridad = new Prioridad(listaProcesos);
            prioridad.metodoTotalSumaRafagas();
            int tiempo = 0;
            int tiempoFinalizacion = prioridad.getTotalSumaRafagas();
//            System.out.println("------------------------");
            if(prioridad.retornarCantidadLlegada() > 0){
             for(int x =0 ; x < prioridad.retornarCantidadLlegada(); x++){
               prioridad.ListaResultados.add(0);
             }
             prioridad.setTiempoActual(prioridad.retornarCantidadLlegada());
             tiempoFinalizacion = tiempoFinalizacion + prioridad.retornarCantidadLlegada();
             }
            while(tiempo < tiempoFinalizacion){
              prioridad.ElegirProcesosAEjecutar();
              prioridad.AtenderProceso();
              tiempo = prioridad.tiempoActual;
//              System.out.println("El tiempo actual es: "+tiempo);
//              System.out.println("------------------------");
            }
            //printear la lista con resultados
            //ListaResultados es lo que queria ud
            for(int x = 0; x < prioridad.ListaResultados.size(); x++){
              ListaResultado.add(prioridad.ListaResultados.get(x));
            }
               /////////Datos de finalizacion
        ArrayList<ArrayList<Integer>> ListaConDatoFinalizacion = new ArrayList<>();
        ArrayList<Integer> Temporal;
        int DatoPos = 0;
        for(int p = 0; p < listaProcesos.size(); p ++){
            Temporal = new ArrayList<>();
            for(int x = 0; x < prioridad.ListaResultados.size(); x++){
               if(prioridad.ListaResultados.get(x)==listaProcesos.get(p).getNumeroProceso()){
                   
                   DatoPos=x+1;
               }
            }
            Temporal.add(listaProcesos.get(p).getNumeroProceso());          
            Temporal.add(listaProcesos.get(p).getTiempoDeLlegada());
            Temporal.add(DatoPos);
            ListaConDatoFinalizacion.add(Temporal);
        }
        for(int i=0;i<ListaConDatoFinalizacion.size();i++){
            for(int j = 0;j<ListaConDatoFinalizacion.size();j++){
                if(ListaConDatoFinalizacion.get(i).get(0)==listaProcesos.get(j).getNumeroProceso()){
                    listaProcesos.get(j).setFinales(ListaConDatoFinalizacion.get(i).get(2));
                }
            }
        }
        pintarAlgoritmo();
    }
    private void algoritmoFeedback(){
        Feedback feed = new Feedback(listaProcesos,Integer.parseInt(AlgoritmoP.get(1).toString()));
          feed.metodoTotalSumaRafagas();
          int tiempo = 0;
          int tiempoFinalizacion= feed.getTotalSumaRafagas();
//          System.out.println("------------------------");
          if(feed.retornarCantidadLlegada() > 0){
             for(int x =0 ; x < feed.retornarCantidadLlegada(); x++){
               feed.getListaResultado().add(0);
             }
             feed.setTiempoActual(feed.retornarCantidadLlegada());
             tiempoFinalizacion = tiempoFinalizacion + feed.retornarCantidadLlegada();
          }
          while(tiempo<tiempoFinalizacion){
            feed.ElegirProcesosAEjecutar();
            feed.Atender();
            tiempo = feed.getTiempoActual();
            feed.setTiempoActual(tiempo);
//            System.out.println("TIEMPO ACTUAL: "+ tiempo);
//            System.out.println("------------------------");
          }
          for(int x = 0; x < feed.getListaResultado().size(); x++){
              ListaResultado.add(feed.getListaResultado().get(x));
          }
             /////////Datos de finalizacion
        ArrayList<ArrayList<Integer>> ListaConDatoFinalizacion = new ArrayList<>();
        ArrayList<Integer> Temporal;
        int DatoPos = 0;
        for(int p = 0; p < listaProcesos.size(); p ++){
            Temporal = new ArrayList<>();
            for(int x = 0; x < feed.getListaResultado().size(); x++){
               if(feed.getListaResultado().get(x)==listaProcesos.get(p).getNumeroProceso()){
                   
                   DatoPos=x+1;
               }
            }
            Temporal.add(listaProcesos.get(p).getNumeroProceso());          
            Temporal.add(listaProcesos.get(p).getTiempoDeLlegada());
            Temporal.add(DatoPos);
            ListaConDatoFinalizacion.add(Temporal);
        }
        for(int i=0;i<ListaConDatoFinalizacion.size();i++){
            for(int j = 0;j<ListaConDatoFinalizacion.size();j++){
                if(ListaConDatoFinalizacion.get(i).get(0)==listaProcesos.get(j).getNumeroProceso()){
                    listaProcesos.get(j).setFinales(ListaConDatoFinalizacion.get(i).get(2));
                }
            }
        }
        pintarAlgoritmo();
    }
    private void verFinal(int pro,int posProceso){
        for(Proceso proceso : listaProcesos){
            if(pro==proceso.getNumeroProceso()){
                if(posProceso==proceso.getFinales()){
                    proceso.setEstado(3);
                    JOptionPane.showMessageDialog(null,"Finalizo proceso"+proceso.getNumeroProceso());
                }
            }
        }
    }
    private void pintarAlgoritmo(){
        for(Integer entero:ListaResultado){
            System.out.println(entero);
        }
        Thread procces = new Thread() {
            public void run() {
                int posProceso=1;
                for(Integer pro : ListaResultado){
                    for(int i=0;i<nucleo.getProcesos().size();i++){
                        if(pro>0){
                            if(pro==nucleo.getProcesos().get(i).getNumeroProceso()){
                                celdas(tabla,posProceso,i,scroll,pro);
                                verFinal(pro,posProceso);
                                int inicio=(int) System.currentTimeMillis();
                                while((int) System.currentTimeMillis()-inicio<1000);
                                posProceso++;
                            }
                        }else{
                            posProceso++;
                            break;
                        }
                    }
                }
            }
        };
        procces.start();
    }
    private void celdas(JTable tabla, int colum,int fila,JScrollPane scroll,int pro){
        for(int i=1;i<80;i++){
            tabla.getColumnModel().getColumn(i).setCellRenderer(new DefaultTableCellRenderer() {
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
}
