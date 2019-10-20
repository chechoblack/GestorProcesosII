/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author ser
 */
public class Archivo {
    /*************************************************variables*************************************************/
    private String archivo;
    private ArrayList<Proceso> listaProcesos = new ArrayList<>();
    private ArrayList<String>  texto = new ArrayList<>();
    /***********************************************fin variables*************************************************/
    
    public Archivo(String archivo) {
        this.archivo=archivo;
        leerTxt();
        obtenerDatos();
    }
    public void leerTxt() {
        //direccion del archivo
        try{
            BufferedReader bf = new BufferedReader(new FileReader(archivo));
            String temp = "";
            String bfRead;
            while((bfRead = bf.readLine()) != null){ 
                //haz el ciclo, mientras bfRead tiene datos
                texto.add(bfRead); //guardado el texto del archivo
            }
        }catch(Exception e){ 
            System.err.println("No se encontro archivo");
        }
    }
    
    public void obtenerDatos(){
        try{
            for(int i=0; i<texto.size();i++){
                String Linea = texto.get(i).trim();
                String [] palabra = Linea.trim().split(";");//convierte la linea en lista 
                if(palabra.length==5){
                    Proceso pro = new Proceso(i+1,Integer.parseInt(palabra[1].trim()),Integer.parseInt(palabra[2].trim()),
                            Integer.parseInt(palabra[3].trim()),Integer.parseInt(palabra[4].trim()),0,0);
                    listaProcesos.add(pro);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Archivo inconcistente, por favor revisar");
                    break;
                }
            }
        }catch(Exception e){
             JOptionPane.showMessageDialog(null,"Archivo inconcistente, por favor revisar");
        }
    }

    public ArrayList<Proceso> getListaProcesos() {
        return listaProcesos;
    }
    
}
