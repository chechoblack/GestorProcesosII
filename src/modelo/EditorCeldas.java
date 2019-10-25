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
        if((row == Row) && (column == Columns)){
            setBackground(Color.red); // Una condicion arbitraria solo para pintar el JLabel que esta en la celda.
            //setText(String.valueOf(value)); // Se agrega el valor que viene por defecto en la celda
        }
//        if((row != Row)&& (column==Columns)){
//            setBackground(Color.white); // Una condicion arbitraria solo para pintar el JLabel que esta en la celda.
//            //setText(String.valueOf(value)); // Se agrega el valor que viene por defecto en la celda
//        }
        if(table.getValueAt(Row,Columns).equals("Nuevo")){
            setBackground(Color.BLUE);
            setText(String.valueOf(value)); // Se agrega el valor que viene por defecto en la celda
        }
        if(table.getValueAt(Row,Columns).equals("Espera")){
            setBackground(Color.YELLOW);
            setText(String.valueOf(value)); // Se agrega el valor que viene por defecto en la celda
        }
        if(table.getValueAt(Row,Columns).equals("Ejecucion")){
            setBackground(Color.GREEN);
            setText(String.valueOf(value)); // Se agrega el valor que viene por defecto en la celda
        }
        if(table.getValueAt(Row,Columns).equals("bloque")){
            setBackground(Color.RED);
            setText(String.valueOf(value)); // Se agrega el valor que viene por defecto en la celda
        }
        return this;
    }
}


