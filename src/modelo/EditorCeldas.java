/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author ser
 */
public class EditorCeldas extends JLabel implements TableCellRenderer{
    int Row,Columns;
    public void setRow(int Row){
        this.Row=Row;
    }
    public void setColumns(int Columns){
        this.Columns=Columns;
    }
    public EditorCeldas() {
        setOpaque(true); // Permite que se vea el color en la celda del JLabel
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//        System.out.println("Fila Cel= "+Row);
//        System.out.println("Colums Cel= "+Columns);}
        this.setOpaque(false);
        this.setBackground(Color.WHITE);
        this.setForeground(Color.BLACK);
        if((row == Row) && (column == Columns)){
            this.setOpaque(true);
            this.setBackground(Color.red); // Una condicion arbitraria solo para pintar el JLabel que esta en la celda.
            this.setText(String.valueOf("X")); // Se agrega el valor que viene por defecto en la celda
        }
        if(table.getValueAt(Row,Columns).equals("Nuevo")){
            this.setOpaque(true);
            this.setBackground(Color.BLUE);
            this.setText(String.valueOf(value)); // Se agrega el valor que viene por defecto en la celda
        }
        if(table.getValueAt(Row,Columns).equals("Espera")){
            this.setOpaque(true);
            this.setBackground(Color.YELLOW);
            this.setText(String.valueOf(value)); // Se agrega el valor que viene por defecto en la celda
        }
        if(table.getValueAt(Row,Columns).equals("Ejecucion")){
            this.setOpaque(true);
            this.setBackground(Color.GREEN);
            this.setText(String.valueOf(value)); // Se agrega el valor que viene por defecto en la celda
        }
        if(table.getValueAt(Row,Columns).equals("bloque")){
            this.setOpaque(true);
            this.setBackground(Color.RED);
            this.setText(String.valueOf(value)); // Se agrega el valor que viene por defecto en la celda
        }
        return this;
    }
}


