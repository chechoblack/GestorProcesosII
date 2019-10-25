/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author ser
 */
public class Nucleo {
    private int idNucleo=0;
    private ArrayList<Proceso> procesos = new ArrayList<>();
    private int cantidadProcesos=0;

    public Nucleo(int idNucleo) {
        this.idNucleo=idNucleo;
    }

    public int getIdNucleo() {
        return idNucleo;
    }

    public void setIdNucleo(int idNucleo) {
        this.idNucleo = idNucleo;
    }

    public ArrayList<Proceso> getProcesos() {
        return procesos;
    }
    public void setProceso(Proceso procesos) {
           this.procesos.add(procesos);
       }
    public void setProcesos(ArrayList<Proceso> procesos) {
        this.procesos = procesos;
    }

    public int getCantidadProcesos() {
        return procesos.size();
    }
}
