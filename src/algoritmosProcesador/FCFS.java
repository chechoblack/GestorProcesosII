package algoritmosProcesador;

import java.util.ArrayList;
import modelo.Proceso;

public class FCFS {
    
   private ArrayList<Proceso> ListaProceso;
   public ArrayList<Integer> ListaResultados;
   private int totalSumaRafagas = 0;
   public int tiempoActual = 0;
   
   public FCFS(ArrayList<Proceso> ListaProceso){
    this.ListaProceso= ListaProceso;
    this.ListaResultados = new ArrayList<>();
   }
   
   // metodo que suma todas las rafagas de los procesos
    public void metodoTotalSumaRafagas(){
        int Tamaño= ListaProceso.size();
        int i = 0;
        while (i < Tamaño) {
           this.totalSumaRafagas = totalSumaRafagas + ListaProceso.get(i).getRafaga();
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
    public void ElegirProcesoAEjecutar(){
        int con = 0;
        int Tamaño= ListaProceso.size();
        while(con < Tamaño){
            if(ListaProceso.get(con).getTiempoDeLlegada()<=tiempoActual && 
                ListaProceso.get(con).getAtendido()==0){
                ListaProceso.get(con).setAtendiendo(1);
            }
            con++;
        }
    }
    
    public void AtenderProceso(){
        int Cont = 0;
        int Tamaño = ListaProceso.size();
        System.out.println("Tamaño= "+Tamaño);
        while(Cont < Tamaño){
            System.out.println("Contador= "+Cont);
            if(ListaProceso.get(Cont).getAtendiendo()==1){
            System.out.println("Proceso Atendido: "+ListaProceso.get(Cont).getNumeroProceso());
            ListaProceso.get(Cont).setAtendido(1);
            ListaProceso.get(Cont).setAtendiendo(0);
                System.out.println("llega");
            System.out.println("Atendido= "+ListaProceso.get(Cont).getAtendido());
            System.out.println("Atendiendo= "+ListaProceso.get(Cont).getAtendiendo());
                System.out.println("pasa");
            int CantidadIngresoProceso = ListaProceso.get(Cont).getRafaga();
            int Contador = 0;
            while(Contador < CantidadIngresoProceso){
                System.out.println(ListaProceso.get(Cont).getNumeroProceso());
                ListaResultados.add(ListaProceso.get(Cont).getNumeroProceso());
                Contador++;
            }
            tiempoActual = tiempoActual + ListaProceso.get(Cont).getRafaga();
            break;
          }
          Cont++;
        }
    }

    public ArrayList<Proceso> getListaProceso() {
        return ListaProceso;
    }

    public void setListaProceso(ArrayList<Proceso> ListaProceso) {
        this.ListaProceso = ListaProceso;
    }

    public ArrayList<Integer> getListaResultados() {
        return ListaResultados;
    }

    public void setListaResultados(ArrayList<Integer> ListaResultados) {
        this.ListaResultados = ListaResultados;
    }

    public int getTiempoActual() {
        return tiempoActual;
    }
    
}
