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
    private ArrayList<String> procesosFracmentados=new ArrayList<>();
    private ArrayList<String> memoria=new ArrayList<>();
    private ArrayList<String> memoriaV=new ArrayList<>();
    private ArrayList<String> tamañoS=new ArrayList<>();
    private ArrayList<String> tamañoSV=new ArrayList<>();
    private String tipo,tamañoP,tamañoF,tamañoM,tamañoVirtual;
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
    private void crearMemoria(){
        for(int i=0;i<Integer.parseInt(tamañoM);i++){
            memoria.add("Libre");
        }
        for(int i=0;i<Integer.parseInt(tamañoVirtual);i++){
            memoriaV.add("Libre");
        }
    }
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
    private void fracmentacionProceso(){
        for(Proceso pro : procesos){
            int tamañoFracmento=pro.getTamañoKB()/Integer.parseInt(tamañoP);
            int cont=0;
            int numeroPagina=0;
            for(int i=0;i<tamañoFracmento;i++){
                procesosFracmentados.add("Pagina-"+numeroPagina);
                for(int j=0;j<tamañoFracmento;j++){
                    procesosFracmentados.add("Proceso-"+pro.getNumeroProceso());
                }
                cont++;
            }
            if(tamañoFracmento*Integer.parseInt(tamañoP)<=pro.getTamañoKB()){
                int faltante=pro.getTamañoKB()-(tamañoFracmento*Integer.parseInt(tamañoP));
                for(int x=0;x<faltante;x++){
                     procesosFracmentados.add("Libre");
                }
            }
            numeroPagina+=1;
        }
        for(int i=0;i<procesosFracmentados.size();i++){
            System.out.println(procesosFracmentados.get(i));
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
            if(tamaño<=Integer.parseInt(tamañoM)){
                for(int x=0;x<pro.getTamañoKB();x++){
                    memoria.set(contPosM, "Proceso"+pro.getNumeroProceso()+"-"+x);
                    contPosM+=1;
                }
            }
            else{
                tamaño-=pro.getTamañoKB();
                tamannoV+=pro.getTamañoKB();
                if(tamannoV<=Integer.parseInt(tamañoVirtual)){
                    for(int x=0;x<pro.getTamañoKB();x++){
                        memoriaV.set(contPosV, "Proceso"+pro.getNumeroProceso()+"-"+x);
                        contPosV+=1;
                    }
                }
            }
            pro.setEstado(1);
        }
    }
    public void fracmentarFija(){
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
    public void fija(){
        fracmentarFija();
        int posMemori=1;
        int posMemoriV=1;
        int suma=0;
        for(Proceso pro: procesos){
            boolean bandera=true;
            int cantidad=0;
            while(posMemori<memoria.size()){
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
    public void ingresarProceso(Proceso pro,int pos){
        int inicio=0;
        for(int i=0;i<pos;i++){
            inicio+=Integer.parseInt(tamañoS.get(i));
        }
        memoria.set(inicio, "Segmento");
        for(int i=0;i<pro.getTamañoKB();i++){
            memoria.set(inicio+1, "Proceso-"+pro.getNumeroProceso());
            inicio++;
        }
    }
    public void ingresarProcesoV(Proceso pro,int pos){
        int inicio=0;
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
    public ArrayList<String> getProcesosFracmentados() {
        return procesosFracmentados;
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
