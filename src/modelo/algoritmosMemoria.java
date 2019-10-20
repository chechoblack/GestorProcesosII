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
public class algoritmosMemoria {
    /*************************************************variables***************************************************/
    private ArrayList<Proceso> procesos=new ArrayList<>();
    private ArrayList<String> procesosFracmentados=new ArrayList<>();
    private ArrayList<String> memoria=new ArrayList<>();
    private ArrayList<String> memoriaV=new ArrayList<>();
    private String tipo,tamañoP,tamañoS,tamañoM,tamañoVirtual;
    /***********************************************fin variable************************************************/
    
    /**
     * 
     * @param tipo
     * @param tamañoP
     * @param tamañoS
     * @param tamañoM
     * @param tamañoVirtual
     * @param procesos 
     */
    public algoritmosMemoria(String tipo,String tamañoP,String tamañoS,String tamañoM,String tamañoVirtual,ArrayList<Proceso> procesos) {
        this.procesos=procesos;
        this.tipo=tipo;
        this.tamañoP=tamañoP;
        this.tamañoS=tamañoS;
        this.tamañoM=tamañoM;
        this.tamañoVirtual=tamañoVirtual;
        crearMemoria();
        seleccionAlgoritmo();
    }
    private void crearMemoria(){
        for(int i=0;i<Integer.parseInt(tamañoM);i++){
            memoria.add("Libre");
        }
        for(int i=0;i<Integer.parseInt(tamañoVirtual);i++){
            memoriaV.add("Libre");
        }
    }
    private void fracmentacionProceso(){
        for(Proceso pro : procesos){
            int tamañoFracmento =pro.getTamañoKB()/Integer.parseInt(tamañoP);
            int cont=0;
            for(int i=0;i<tamañoFracmento;i++){
                for(int j=0;j<tamañoFracmento;j++){
                    procesosFracmentados.add("Proceso"+pro.getNumeroProceso()+"-"+cont);
                }
                cont++;
            }
        }
    }
    public void paginada(){
        fracmentacionProceso();
        int contadorTamaño=0;
        for(String fracmento : procesosFracmentados){
            if(contadorTamaño<memoria.size()/Integer.parseInt(tamañoP)){
                for(int i=0; i<memoria.size()/Integer.parseInt(tamañoP);i++){
                    if(memoria.get(i).equals("Libre")){
                        memoria.set(i, fracmento);
                        break;
                    }
                }
            }
            else if(contadorTamaño < memoriaV.size()/Integer.parseInt(tamañoP)){
                for(int i=0; i<memoriaV.size()/Integer.parseInt(tamañoP);i++){
                    if(memoriaV.get(i).equals("Libre")){
                        memoriaV.set(i, fracmento);
                        break;
                    }
                }
            }
            contadorTamaño++;
        }
    }
    public void dinamico(){
        int tamaño=0;
        int tamannoV=0;
        int contPosM=0;
        int contPosV=0;
        for(Proceso pro : procesos){
            tamaño+=pro.getTamañoKB();
            System.out.println(tamaño<=Integer.parseInt(tamañoM));
            if(tamaño<=Integer.parseInt(tamañoM)){
                for(int x=0;x<pro.getTamañoKB();x++){
                    memoria.set(contPosM, "Proceso"+pro.getNumeroProceso()+"-"+x);
                    contPosM+=1;
                }
            }
            else{
                tamaño-=pro.getTamañoKB();
                tamannoV+=pro.getTamañoKB();
                System.out.println(tamaño<=Integer.parseInt(tamañoVirtual));
                if(tamannoV<=Integer.parseInt(tamañoVirtual)){
                    for(int x=0;x<pro.getTamañoKB();x++){
                        memoriaV.set(contPosV, "Proceso"+pro.getNumeroProceso()+"-"+x);
                        contPosV+=1;
                    }
                }
            }
        }
    }
    private void seleccionAlgoritmo(){
        if(tipo.equals("Paginación")){
            paginada();
        }
        if(tipo.equals("Dinámica")){
            dinamico();
        }
    }
    public ArrayList<String> getProcesosFracmentados() {
        return procesosFracmentados;
    }

    public ArrayList<String> getMemoria() {
        return memoria;
    }

    public ArrayList<String> getMemoriaV() {
        return memoriaV;
    }
    
}
