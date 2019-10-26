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
    
    private ArrayList<Proceso> ListaProceso;
    private ArrayList<Proceso> ListaResultado;
    private ArrayList<String> ListaActual;
    private int tiempoActual = 0;
    private int totalSumaRafagas = 0;

    public HRRN(ArrayList<Proceso> ListaProceso) {
        this.ListaProceso = ListaProceso;
        this.ListaResultado = new ArrayList<>();
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
    
    public int retornarCantidadLlegada(){
        return ListaProceso.get(0).getTiempoDeLlegada();
    }
    //metodo para recorrer la lista con procesos y asginar su promedio
    public void Funcionamiento(){ 
        int Tamaño= ListaProceso.size();
        int i = 0;
        while (i < Tamaño) {
           Formula(ListaProceso.get(i),i);
           System.out.println("Proceso: "+ListaProceso.get(i).getNumeroProceso());
//           System.out.println("Promedio: "+ListaProceso.get(i).getPromedio());
          i++;
        }
    }
    
    //metodo para cambiar el dato de promedio
    public void Formula(Proceso Proceso, int pos){
     float FormulaR = (((float)tiempoActual-Proceso.getTiempoDeLlegada())+Proceso.getRafaga())/Proceso.getRafaga();
     ListaProceso.get(pos).setPromedio(FormulaR);
    }
    
    //metodo para seleccionar procesos a atender dependiendo de su rafaga
    public void ElegirProcesoAEjecutar(){
       int con = 0;
       int Tamaño= ListaProceso.size();
       while(con < Tamaño){
        if(ListaProceso.get(con).getTiempoDeLlegada()<=tiempoActual && 
                ListaProceso.get(con).getAtendido()==0){
         ListaProceso.get(con).setAtendiendo(1);
         System.out.println("Proceso elegido: "+ListaProceso.get(con).getNumeroProceso());
        }
       con++;
       }
    }
    
    //metodo para saber cual es el proceso con mejor promedio de la lista y guardarlo en resultados
    public void ComprobarMejorDeLista(){
      int Tamaño= ListaProceso.size();
        int i = 0;
        int NumeroMejorProceso = 0;
        float promedioAltoActual=0;
        while (i < Tamaño) {
           if(NumeroMejorProceso==0 && 
                   ListaProceso.get(i).getAtendiendo()==1 && 
                   ListaProceso.get(i).getAtendido()==0){
            promedioAltoActual= ListaProceso.get(i).getPromedio();
            NumeroMejorProceso=i;
           }
           else{
            if(ListaProceso.get(i).getPromedio() > promedioAltoActual && 
                   ListaProceso.get(i).getAtendiendo()==1 && 
                    ListaProceso.get(i).getAtendido()==0){
             promedioAltoActual= ListaProceso.get(i).getPromedio();
             NumeroMejorProceso=i;
            }
           }
        i++;
        }
        ListaProceso.get(NumeroMejorProceso).setAtendido(1);
        ListaProceso.get(NumeroMejorProceso).setAtendiendo(0);
        ListaResultado.add(ListaProceso.get(NumeroMejorProceso));
    }

    public ArrayList<Proceso> getListaProceso() {
        return ListaProceso;
    }

    public void setListaProceso(ArrayList<Proceso> ListaProceso) {
        this.ListaProceso = ListaProceso;
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
