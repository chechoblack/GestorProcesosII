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
    private int NumeroProceso;
    private int Rafaga;
    private int TiempoDeLlegada;
    private int Prioridad;
    private int TamañoKB;
    private int Atendido;
    private float Promedio;
    private int faltante;
    private int Atendiendo;
    public Proceso(int NumeroProceso, int Rafaga,
            int TiempoDeLlegada, int Prioridad, int TamañoKB,
            int Atendido, float Promedio,int Atendiendo,int faltante) {
        this.NumeroProceso = NumeroProceso;
        this.Rafaga = Rafaga;
        this.TiempoDeLlegada = TiempoDeLlegada;
        this.Prioridad = Prioridad;
        this.TamañoKB = TamañoKB;
        this.Atendido = Atendido;
        this.Promedio = Promedio;
        this.faltante = faltante;
        this.Atendiendo=Atendiendo;
        
    }

    public float getPromedio() {
        return Promedio;
    }

    public void setPromedio(float Promedio) {
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

    public int getFaltante() {
        return faltante;
    }

    public void setFaltante(int faltante) {
        this.faltante = faltante;
    }

    public int getAtendiendo() {
        return Atendiendo;
    }

    public void setAtendiendo(int Atendiendo) {
        this.Atendiendo = Atendiendo;
    }
    
}
