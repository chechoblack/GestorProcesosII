/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmosMemoria;

import java.util.ArrayList;
import modelo.Proceso;

/**
 *
 * @author ser
 */
public class algoritmosMemoria {
    /*************************************************variables***************************************************/
    private ArrayList<Proceso> procesos=new ArrayList<>();
    private ArrayList<String> faltante=new ArrayList<>();
    private ArrayList<String> memoria=new ArrayList<>();
    private ArrayList<String> memoriaV=new ArrayList<>();
    private ArrayList<String> tamañoS=new ArrayList<>();
    private ArrayList<String> tamañoSV=new ArrayList<>();
    private String tipo,tamañoP,tamañoF,tamañoM,tamañoVirtual;
    /***********************************************fin variable************************************************/
    
    /**
     * 
     * @param tipo
     * @param tamañoP //tamaño de  frames
     * @param tamañoS 
     * @param tamañoSV
     * @param tamañoF
     * @param tamañoM
     * @param tamañoVirtual
     * @param procesos 
     */
    public algoritmosMemoria(String tipo,String tamañoP,ArrayList<String> tamañoS,ArrayList<String> tamañoSV,String tamañoF,
            String tamañoM,String tamañoVirtual,ArrayList<Proceso> procesos) {
        this.procesos=procesos;
        this.tipo=tipo;
        this.tamañoP=tamañoP;
        this.tamañoS=tamañoS;
        this.tamañoSV=tamañoSV;
        this.tamañoF=tamañoF;
        this.tamañoM=tamañoM;
        this.tamañoVirtual=tamañoVirtual;
        crearMemoria();
        seleccionAlgoritmo();
    }
    /**
     * 
     */
    private void crearMemoria(){
        for(int i=0;i<Integer.parseInt(tamañoM);i++){
            memoria.add("Libre");
        }
        for(int i=0;i<Integer.parseInt(tamañoVirtual);i++){
            memoriaV.add("Libre");
        }
    }
    /**
     * 
     */
    private void seleccionAlgoritmo(){
        if(tipo.equals("Paginación")){
            paginada();
        }
        if(tipo.equals("Dinámica")){
            dinamico();
        }
        if(tipo.equals("Fija")){
            fija();
        }
        if(tipo.equals("Segmentación")){
            segmentacion();
        }
    }
    /**
     * 
     */
    private ArrayList fragmentacionProceso(Proceso pro){
        ArrayList ListaRusltado = new ArrayList();
        int tamañoFracmento=pro.getTamañoKB()/Integer.parseInt(tamañoP);
        int cont=0;
        for(int i=0;i<tamañoFracmento;i++){
            ListaRusltado.add("Pagina-"+i);
            for(int j=0;j<Integer.parseInt(tamañoP);j++){
                ListaRusltado.add("Proceso-"+pro.getNumeroProceso());
                cont++;
            }
            
        }
        if(cont<pro.getTamañoKB()){
            ListaRusltado.add("Pagina-"+tamañoFracmento);
            for(int j=0;j<Integer.parseInt(tamañoP);j++){
                if(cont<pro.getTamañoKB()){
                   ListaRusltado.add("Proceso-"+pro.getNumeroProceso());
                   cont+=1;
               }else{
                   ListaRusltado.add("Libre");
               }
            }
        }
        return ListaRusltado;
    }
    /**
     * 
     */
    public void paginada(){
//        System.out.println("entra");
        ArrayList procesosFracmentados=new ArrayList<>();
        int CantidadFramesMemory=Integer.parseInt(tamañoM)/Integer.parseInt(tamañoP);
        int CantidadFramesMemoryV=Integer.parseInt(tamañoVirtual)/Integer.parseInt(tamañoP);
        int framesOcupados=0;
        int framesOcupadosV=0;
        int contPosM=0;
        int contPosV=0;
        for(Proceso pro :procesos ){
            boolean bandera=true;
            procesosFracmentados=fragmentacionProceso(pro);
            framesOcupados+=procesosFracmentados.size();
            System.out.println("ocupados= "+framesOcupados);
            System.out.println("disponibles= "+CantidadFramesMemory*Integer.parseInt(tamañoP));
            if(framesOcupados<(CantidadFramesMemory*Integer.parseInt(tamañoP))){
                bandera=false;
                pro.setEstado(1);
                for(int i=0;i<framesOcupados;i++){
                    memoria.add("Libre");
                }
                for(int x=0;x<procesosFracmentados.size();x++){
                    memoria.set(contPosM,String.valueOf(procesosFracmentados.get(x)));
                    contPosM+=1;
                }
            }
            else{
                framesOcupados-=procesosFracmentados.size()/Integer.parseInt(tamañoP);
                framesOcupadosV+=procesosFracmentados.size()/Integer.parseInt(tamañoP);
                if(framesOcupadosV<CantidadFramesMemoryV && bandera){
                    pro.setEstado(1);
                    for(int i=0;i<framesOcupadosV;i++){
                        memoriaV.add("Libre");
                    }
                    for(int x=0;x<procesosFracmentados.size();x++){
                        memoriaV.set(contPosV,String.valueOf(procesosFracmentados.get(x)));
                        contPosV+=1;
                    }
                }
                else{
                    framesOcupadosV-=procesosFracmentados.size();
                }
            }
        }
    }
    /**
     * 
     */
    public void dinamico(){
        int tamaño=0;
        int tamannoV=0;
        int contPosM=0;
        int contPosV=0;
        for(Proceso pro : procesos){
            tamaño+=pro.getTamañoKB();
            if(tamaño<=Integer.parseInt(tamañoM)){
                pro.setEstado(1);
                for(int x=0;x<pro.getTamañoKB();x++){
                    memoria.set(contPosM, "Proceso"+pro.getNumeroProceso()+"-"+x);
                    contPosM+=1;
                }
            }
            else{
                tamaño-=pro.getTamañoKB();
                tamannoV+=pro.getTamañoKB();
                if(tamannoV<=Integer.parseInt(tamañoVirtual)){
                    pro.setEstado(1);
                    for(int x=0;x<pro.getTamañoKB();x++){
                        memoriaV.set(contPosV, "Proceso"+pro.getNumeroProceso()+"-"+x);
                        contPosV+=1;
                    }
                }
            }
        }
    }
    /**
     * 
     */
    public void fragmentarFija(){
        int tamaño=Integer.parseInt(tamañoM)/Integer.parseInt(tamañoF);
        for(int x=0;x<tamaño;x++){
            memoria.add("Libre");
        }
        int posMemoria=0;
        for(int i=0; i<tamaño;i++){
            memoria.set(posMemoria, "bloque");
            posMemoria=posMemoria+1;
            for(int j=1;j<=Integer.parseInt(tamañoF);j++){
                posMemoria++;
                if(posMemoria<memoria.size()){
                    memoria.set(posMemoria, "Libre");
                }
            }
        }
        int tamañoV=Integer.parseInt(tamañoVirtual)/Integer.parseInt(tamañoF);
        for(int x=0;x<tamaño;x++){
            memoriaV.add("Libre");
        }
        int posMemoriaV=0;
        for(int i=0; i<tamañoV;i++){
            System.out.println(posMemoriaV);
            if(posMemoriaV<memoriaV.size()){
                memoriaV.set(posMemoriaV, "bloque");
                posMemoriaV=posMemoriaV+1;
            }
            for(int j=1;j<=Integer.parseInt(tamañoF);j++){
                posMemoriaV++;
                if(posMemoriaV<memoriaV.size()){
                    memoriaV.set(posMemoriaV, "Libre");
                }
            }
        }
    }
    /**
     * 
     */
    public void fija(){
        fragmentarFija();
        int posMemori=1;
        int posMemoriV=1;
        int suma=0;
        for(Proceso pro: procesos){
            boolean bandera=true;
            int cantidad=0;
            while(posMemori<memoria.size()){
                pro.setEstado(1);
                if(memoria.get(posMemori).equals("Libre")){
                    int diferencia=suma-posMemori;
                    if(diferencia<pro.getTamañoKB()){
                       if(cantidad<pro.getTamañoKB()){
                            memoria.set(posMemori, "Proceso-"+pro.getNumeroProceso());
                            posMemori++;
                            cantidad++;
                            bandera=false;
                       }
                       else{
                           posMemori++;
                           bandera=false;
                       }
                    }
                    else{
                        posMemori++;
                        memoria.set(posMemori, "Libre");
                    }
                }
                else if(memoria.get(posMemori).equals("bloque")){
                    if((pro.getTamañoKB()-Integer.parseInt(tamañoF))<0){
                        pro.setFaltante(0);
                    }else{
                        pro.setFaltante(pro.getTamañoKB()-Integer.parseInt(tamañoF));
                    }
                    posMemori++;
                    suma+=Integer.parseInt(tamañoF);
                    break;
                }
            }
            if(!bandera){
                if((pro.getTamañoKB()-Integer.parseInt(tamañoF))<0){
                    pro.setFaltante(0);
                }else{
                    pro.setFaltante(pro.getTamañoKB()-Integer.parseInt(tamañoF));
                }
            }
            while(posMemoriV<memoriaV.size() && bandera){
                pro.setEstado(1);
                if(memoriaV.get(posMemoriV).equals("Libre")){
                    int diferencia=suma-posMemoriV;
                    if(diferencia<pro.getTamañoKB()){
                       if(cantidad<pro.getTamañoKB()){
                            memoriaV.set(posMemoriV, "Proceso-"+pro.getNumeroProceso());
                            posMemoriV++;
                            cantidad++;
                       }
                       else{
                           posMemoriV++;
                       }
                    }
                    else{
                        posMemoriV++;
                        memoriaV.set(posMemoriV, "Libre");
                    }
                }
                else if(memoriaV.get(posMemoriV).equals("bloque")){
                    if((pro.getTamañoKB()-Integer.parseInt(tamañoF))<0){
                        pro.setFaltante(0);
                    }else{
                        pro.setFaltante(pro.getTamañoKB()-Integer.parseInt(tamañoF));
                    }
                    posMemoriV++;
                    break;
                }
            }
        }
    }
    /**
     * 
     * @param pro
     * @param pos 
     */
    public void ingresarProceso(Proceso pro,int pos){
        int inicio=0;
        pro.setEstado(1);
        for(int i=0;i<pos;i++){
            inicio+=Integer.parseInt(tamañoS.get(i));
        }
        memoria.set(inicio, "Segmento");
        for(int i=0;i<pro.getTamañoKB();i++){
            memoria.set(inicio+1, "Proceso-"+pro.getNumeroProceso());
            inicio++;
        }
    }/**
     * 
     * @param pro
     * @param pos 
     */
    public void ingresarProcesoV(Proceso pro,int pos){
        int inicio=0;
        pro.setEstado(1);
        for(int i=0;i<pos;i++){
            inicio+=Integer.parseInt(tamañoSV.get(i));
        }
        memoriaV.set(inicio, "Segmento");
        inicio=inicio+1;
        for(int i=0;i<=pro.getTamañoKB();i++){
            memoriaV.set(inicio, "Proceso-"+pro.getNumeroProceso());
            inicio++;
        }
    }
    /**
     * 
     */
    public void segmentacion(){
        int tamaño=tamañoS.size();
        for(int x=0;x<tamaño;x++){
            memoria.add("Libre");
        }
        int tamañoV=tamañoSV.size();
        for(int x=0;x<tamañoV;x++){
            memoriaV.add("Libre");
        }
        int posMemoria=0;
        int posMemoriaV=0;
        for(Proceso pro : procesos){
            boolean bandera=true;
            for(int i=0;i<tamañoS.size();i++){
                if(pro.getTamañoKB()<= Integer.parseInt(tamañoS.get(i)) && posMemoria<tamañoS.size()){
                    ingresarProceso( pro, posMemoria);
                    posMemoria+=1;
                    bandera=false;
                    break;
                } 
            }
            for(int i=0;i<tamañoSV.size();i++){
                if(pro.getTamañoKB()<= Integer.parseInt(tamañoSV.get(i))&& bandera && posMemoriaV<tamañoSV.size()){
                    ingresarProcesoV( pro, posMemoriaV);
                    posMemoriaV+=1;
                    break;
                } 
            }
        }
    }
    public ArrayList<String> getMemoria() {
        return memoria;
    }

    public ArrayList<String> getMemoriaV() {
        return memoriaV;
    }

    public ArrayList<String> getFaltante() {
        return faltante;
    }
    
}
