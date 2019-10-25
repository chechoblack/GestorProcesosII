package algoritmosProcesador;

import java.util.ArrayList;
import modelo.Proceso;


public class HRRN {
    
    ArrayList<Proceso> ListaProcesos;
    ArrayList<Proceso> ListaResultado;
    ArrayList<String> ListaActual;
    int tiempoActual = 0;
    int totalSumaRafagas = 0;

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
    public void ElegirProcesoAEjecutar(){
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
        System.out.println("Tamaño ="+ListaResultado.size());
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
