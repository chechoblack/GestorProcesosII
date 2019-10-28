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
public class HRRN {
    
   public ArrayList<Proceso> ListaProcesos;
    public ArrayList<Proceso> ListaResultado;
    public ArrayList<String> ListaActual;
    public int tiempoActual = 0;
    public int totalSumaRafagas = 0;

    public HRRN(ArrayList<Proceso> ListaProcesos) {
        this.ListaProcesos = ListaProcesos;
        this.ListaResultado = new ArrayList<>();
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
    
    //metodo para retornar el total de la suma de las rafagas de los procesos
    public int getTotalSumaRafagas(){
        return totalSumaRafagas;
    }
    
    //setear el tiempo actual del algoritmo  
    public void setTiempoActual(int tiempoActual){
        this.tiempoActual = tiempoActual;
    }
    
    //metodo para recorrer la lista con procesos y asginar su promedio
    public void Funcionamiento(){ 
        int Tamaño= ListaProcesos.size();
        int i = 0;
        while (i < Tamaño) {
           Formula(ListaProcesos.get(i),i);
           System.out.println("Proceso: "+ListaProcesos.get(i).getNumeroProceso());
           System.out.println("Promedio: "+ListaProcesos.get(i).getPromedio());
          i++;
        }
    }
    
    //metodo para cambiar el dato de promedio
    public void Formula(Proceso Proceso, int pos){
     float FormulaR = (((float)tiempoActual-Proceso.getTiempoDeLlegada())+Proceso.getRafaga())/Proceso.getRafaga();
     ListaProcesos.get(pos).setPromedio(FormulaR);
    }
    
    //metodo para seleccionar procesos a atender dependiendo de su rafaga
    public void ElegirProcesosAEjecutar(){
       int con = 0;
       int Tamaño= ListaProcesos.size();
       while(con < Tamaño){
        if(ListaProcesos.get(con).getTiempoDeLlegada()<=tiempoActual && 
                ListaProcesos.get(con).getAtendido()==0){
         ListaProcesos.get(con).setAtendiendo(1);
         System.out.println("Proceso elegido: "+ListaProcesos.get(con).getNumeroProceso());
        }
       con++;
       }
    }
    
    //metodo para saber cual es el proceso con mejor promedio de la lista y guardarlo en resultados
    public void ComprobarMejorDeLista(){
      int Tamaño= ListaProcesos.size();
        int i = 0;
        int NumeroMejorProceso = 0;
        float promedioAltoActual=0;
        while (i < Tamaño) {
           if(NumeroMejorProceso==0 && 
                   ListaProcesos.get(i).getAtendiendo()==1 && 
                   ListaProcesos.get(i).getAtendido()==0){
            promedioAltoActual= ListaProcesos.get(i).getPromedio();
            NumeroMejorProceso=i;
           }
           else{
            if(ListaProcesos.get(i).getPromedio() > promedioAltoActual && 
                   ListaProcesos.get(i).getAtendiendo()==1 && 
                    ListaProcesos.get(i).getAtendido()==0){
             promedioAltoActual= ListaProcesos.get(i).getPromedio();
             NumeroMejorProceso=i;
            }
           }
        i++;
        }
        ListaProcesos.get(NumeroMejorProceso).setAtendido(1);
        ListaProcesos.get(NumeroMejorProceso).setAtendiendo(0);
        ListaResultado.add(ListaProcesos.get(NumeroMejorProceso));
    }

    public ArrayList<Proceso> getListaProcesos() {
        return ListaProcesos;
    }

    public void setListaProcesos(ArrayList<Proceso> ListaProcesos) {
        this.ListaProcesos = ListaProcesos;
    }

    public ArrayList<Proceso> getListaResultado() {
        return ListaResultado;
    }

    public void setListaResultado(ArrayList<Proceso> ListaResultado) {
        this.ListaResultado = ListaResultado;
    }

    public ArrayList<String> getListaActual() {
        return ListaActual;
    }

    public void setListaActual(ArrayList<String> ListaActual) {
        this.ListaActual = ListaActual;
    }
    
}
