/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author ser
 */
public class Proceso {
    int NumeroProceso;
    int Rafaga;
    int TiempoDeLlegada;
    int Prioridad;
    int TamañoKB;
    int Atendido;
    int Promedio;

    public Proceso(int NumeroProceso, int Rafaga, int TiempoDeLlegada, int Prioridad, int TamañoKB, int Atendido, int Promedio) {
        this.NumeroProceso = NumeroProceso;
        this.Rafaga = Rafaga;
        this.TiempoDeLlegada = TiempoDeLlegada;
        this.Prioridad = Prioridad;
        this.TamañoKB = TamañoKB;
        this.Atendido = Atendido;
        this.Promedio = Promedio;
    }

    public int getPromedio() {
        return Promedio;
    }

    public void setPromedio(int Promedio) {
        this.Promedio = Promedio;
    }

    public int getAtendido() {
        return Atendido;
    }

    public void setAtendido(int Atendido) {
        this.Atendido = Atendido;
    }

    public int getNumeroProceso() {
        return NumeroProceso;
    }

    public void setNumeroProceso(int NumeroProceso) {
        this.NumeroProceso = NumeroProceso;
    }

    public int getRafaga() {
        return Rafaga;
    }

    public void setRafaga(int Rafaga) {
        this.Rafaga = Rafaga;
    }

    public int getTiempoDeLlegada() {
        return TiempoDeLlegada;
    }

    public void setTiempoDeLlegada(int TiempoDeLlegada) {
        this.TiempoDeLlegada = TiempoDeLlegada;
    }

    public int getPrioridad() {
        return Prioridad;
    }

    public void setPrioridad(int Prioridad) {
        this.Prioridad = Prioridad;
    }

    public int getTamañoKB() {
        return TamañoKB;
    }

    public void setTamañoKB(int TamañoKB) {
        this.TamañoKB = TamañoKB;
    }
    
    
}
