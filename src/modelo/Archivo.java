/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author ser
 */
public class Archivo {
    /*************************************************variables*************************************************/
    private String archivo;
    private ArrayList<String>  texto = new ArrayList<>();
    /***********************************************fin variables*************************************************/
    
    public Archivo(String archivo) {
        this.archivo=archivo;
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
         for(int i=0; i<texto.size();i++){
            String Linea = texto.get(i).trim();
            String [] palabra = Linea.trim().split(" ");//convierte la linea en lista 
            for(int x=0;x<palabra.length-1;x++){
                System.out.println(palabra[x]+" "+palabra[x+1]);
            }
         }
    }
}
