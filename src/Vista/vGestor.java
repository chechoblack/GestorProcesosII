/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import static java.util.Locale.filter;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import modelo.Archivo;
import modelo.Memoria;
import modelo.Proceso;
import modelo.algoritmosMemoria;
import org.omg.CORBA.portable.ValueFactory;

/**
 *
 * @author ser
 */
public class vGestor extends javax.swing.JFrame {

    /**
     * Creates new form vGestor
     */
    /******************************** variables**************************************************/
    private Archivo nuevo;
    private FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos cpu", "txt");
    //
    DefaultTableModel memory;
    DefaultTableModel memoryVirtual;
    DefaultTableModel tablaProcesos;
    String [][] data={};
    String [][] dataVirtual={};
    String titulos[] = {"Instruccion","Archivo"};
    String titulosTablaP[] = {"Nombre","Estado","Faltante"};
    //
    ArrayList<String> listaSegmento = new ArrayList<>();
    ArrayList algoritmoP = new ArrayList<>();
    ArrayList algoritmoM = new ArrayList<>();
    ArrayList<Memoria> infMemoria = new ArrayList<>();
    /*******************************fin variables************************************************/
    public vGestor() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
    }
    public vGestor(ArrayList algoritmoP,ArrayList algoritmoM,ArrayList<Memoria> memoria,ArrayList<String> listaSegmento) {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.algoritmoM=algoritmoM;
        this.algoritmoP=algoritmoP;
        this.infMemoria=memoria;
        this.listaSegmento=listaSegmento;
        memory=new DefaultTableModel(data,titulos);
        tlbMemory.setModel(memory);
        
        memoryVirtual=new DefaultTableModel(dataVirtual,titulos);
        tlbMemoryVirtual.setModel(memoryVirtual);
        
        tablaProcesos=new DefaultTableModel(data,titulosTablaP);
        tblProcesos.setModel(tablaProcesos);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtCantidadArchivos = new javax.swing.JTextField();
        btnCargar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblProcesos = new javax.swing.JTable();
        btnEjecutar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNucleo2 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tlbMemoryVirtual = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tlbMemory = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblNucleo1 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Archivos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 18))); // NOI18N

        btnCargar.setText("Cargar");
        btnCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarActionPerformed(evt);
            }
        });

        tblProcesos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nombre", "Estado", "Faltante"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblProcesos);

        btnEjecutar.setText("Ejecutar");
        btnEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEjecutarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtCantidadArchivos)
                        .addGap(18, 18, 18)
                        .addComponent(btnCargar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnEjecutar)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCantidadArchivos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCargar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(btnEjecutar)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nucleo 2", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 18))); // NOI18N

        tblNucleo2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Procesos", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNucleo2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(tblNucleo2);
        if (tblNucleo2.getColumnModel().getColumnCount() > 0) {
            tblNucleo2.getColumnModel().getColumn(1).setResizable(false);
            tblNucleo2.getColumnModel().getColumn(1).setPreferredWidth(25);
            tblNucleo2.getColumnModel().getColumn(2).setResizable(false);
            tblNucleo2.getColumnModel().getColumn(2).setPreferredWidth(25);
            tblNucleo2.getColumnModel().getColumn(3).setResizable(false);
            tblNucleo2.getColumnModel().getColumn(3).setPreferredWidth(25);
            tblNucleo2.getColumnModel().getColumn(4).setResizable(false);
            tblNucleo2.getColumnModel().getColumn(4).setPreferredWidth(25);
            tblNucleo2.getColumnModel().getColumn(5).setResizable(false);
            tblNucleo2.getColumnModel().getColumn(5).setPreferredWidth(25);
            tblNucleo2.getColumnModel().getColumn(6).setResizable(false);
            tblNucleo2.getColumnModel().getColumn(6).setPreferredWidth(25);
            tblNucleo2.getColumnModel().getColumn(7).setResizable(false);
            tblNucleo2.getColumnModel().getColumn(7).setPreferredWidth(25);
            tblNucleo2.getColumnModel().getColumn(8).setResizable(false);
            tblNucleo2.getColumnModel().getColumn(8).setPreferredWidth(25);
            tblNucleo2.getColumnModel().getColumn(9).setResizable(false);
            tblNucleo2.getColumnModel().getColumn(9).setPreferredWidth(25);
            tblNucleo2.getColumnModel().getColumn(10).setResizable(false);
            tblNucleo2.getColumnModel().getColumn(10).setPreferredWidth(25);
            tblNucleo2.getColumnModel().getColumn(11).setResizable(false);
            tblNucleo2.getColumnModel().getColumn(11).setPreferredWidth(25);
            tblNucleo2.getColumnModel().getColumn(12).setResizable(false);
            tblNucleo2.getColumnModel().getColumn(12).setPreferredWidth(25);
            tblNucleo2.getColumnModel().getColumn(13).setResizable(false);
            tblNucleo2.getColumnModel().getColumn(13).setPreferredWidth(25);
            tblNucleo2.getColumnModel().getColumn(14).setResizable(false);
            tblNucleo2.getColumnModel().getColumn(14).setPreferredWidth(25);
            tblNucleo2.getColumnModel().getColumn(15).setResizable(false);
            tblNucleo2.getColumnModel().getColumn(15).setPreferredWidth(25);
            tblNucleo2.getColumnModel().getColumn(16).setResizable(false);
            tblNucleo2.getColumnModel().getColumn(16).setPreferredWidth(25);
            tblNucleo2.getColumnModel().getColumn(17).setResizable(false);
            tblNucleo2.getColumnModel().getColumn(17).setPreferredWidth(25);
            tblNucleo2.getColumnModel().getColumn(18).setResizable(false);
            tblNucleo2.getColumnModel().getColumn(18).setPreferredWidth(25);
            tblNucleo2.getColumnModel().getColumn(19).setResizable(false);
            tblNucleo2.getColumnModel().getColumn(19).setPreferredWidth(25);
            tblNucleo2.getColumnModel().getColumn(20).setResizable(false);
            tblNucleo2.getColumnModel().getColumn(20).setPreferredWidth(25);
            tblNucleo2.getColumnModel().getColumn(21).setResizable(false);
            tblNucleo2.getColumnModel().getColumn(21).setPreferredWidth(25);
            tblNucleo2.getColumnModel().getColumn(22).setResizable(false);
            tblNucleo2.getColumnModel().getColumn(22).setPreferredWidth(25);
            tblNucleo2.getColumnModel().getColumn(23).setResizable(false);
            tblNucleo2.getColumnModel().getColumn(23).setPreferredWidth(25);
            tblNucleo2.getColumnModel().getColumn(24).setResizable(false);
            tblNucleo2.getColumnModel().getColumn(24).setPreferredWidth(25);
            tblNucleo2.getColumnModel().getColumn(25).setResizable(false);
            tblNucleo2.getColumnModel().getColumn(25).setPreferredWidth(25);
            tblNucleo2.getColumnModel().getColumn(26).setResizable(false);
            tblNucleo2.getColumnModel().getColumn(26).setPreferredWidth(25);
            tblNucleo2.getColumnModel().getColumn(27).setResizable(false);
            tblNucleo2.getColumnModel().getColumn(27).setPreferredWidth(25);
            tblNucleo2.getColumnModel().getColumn(28).setResizable(false);
            tblNucleo2.getColumnModel().getColumn(28).setPreferredWidth(25);
            tblNucleo2.getColumnModel().getColumn(29).setResizable(false);
            tblNucleo2.getColumnModel().getColumn(29).setPreferredWidth(25);
            tblNucleo2.getColumnModel().getColumn(30).setResizable(false);
            tblNucleo2.getColumnModel().getColumn(30).setPreferredWidth(25);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Disco Duro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 18))); // NOI18N

        tlbMemoryVirtual.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Instruccion", "Archivo"
            }
        ));
        jScrollPane9.setViewportView(tlbMemoryVirtual);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Memoria", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 18))); // NOI18N

        tlbMemory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Proceso", "Estado"
            }
        ));
        jScrollPane7.setViewportView(tlbMemory);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nucleo 1", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 18))); // NOI18N

        tblNucleo1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Procesos", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNucleo1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane3.setViewportView(tblNucleo1);
        if (tblNucleo1.getColumnModel().getColumnCount() > 0) {
            tblNucleo1.getColumnModel().getColumn(1).setResizable(false);
            tblNucleo1.getColumnModel().getColumn(1).setPreferredWidth(25);
            tblNucleo1.getColumnModel().getColumn(2).setResizable(false);
            tblNucleo1.getColumnModel().getColumn(2).setPreferredWidth(25);
            tblNucleo1.getColumnModel().getColumn(3).setResizable(false);
            tblNucleo1.getColumnModel().getColumn(3).setPreferredWidth(25);
            tblNucleo1.getColumnModel().getColumn(4).setResizable(false);
            tblNucleo1.getColumnModel().getColumn(4).setPreferredWidth(25);
            tblNucleo1.getColumnModel().getColumn(5).setResizable(false);
            tblNucleo1.getColumnModel().getColumn(5).setPreferredWidth(25);
            tblNucleo1.getColumnModel().getColumn(6).setResizable(false);
            tblNucleo1.getColumnModel().getColumn(6).setPreferredWidth(25);
            tblNucleo1.getColumnModel().getColumn(7).setResizable(false);
            tblNucleo1.getColumnModel().getColumn(7).setPreferredWidth(25);
            tblNucleo1.getColumnModel().getColumn(8).setResizable(false);
            tblNucleo1.getColumnModel().getColumn(8).setPreferredWidth(25);
            tblNucleo1.getColumnModel().getColumn(9).setResizable(false);
            tblNucleo1.getColumnModel().getColumn(9).setPreferredWidth(25);
            tblNucleo1.getColumnModel().getColumn(10).setResizable(false);
            tblNucleo1.getColumnModel().getColumn(10).setPreferredWidth(25);
            tblNucleo1.getColumnModel().getColumn(11).setResizable(false);
            tblNucleo1.getColumnModel().getColumn(11).setPreferredWidth(25);
            tblNucleo1.getColumnModel().getColumn(12).setResizable(false);
            tblNucleo1.getColumnModel().getColumn(12).setPreferredWidth(25);
            tblNucleo1.getColumnModel().getColumn(13).setResizable(false);
            tblNucleo1.getColumnModel().getColumn(13).setPreferredWidth(25);
            tblNucleo1.getColumnModel().getColumn(14).setResizable(false);
            tblNucleo1.getColumnModel().getColumn(14).setPreferredWidth(25);
            tblNucleo1.getColumnModel().getColumn(15).setResizable(false);
            tblNucleo1.getColumnModel().getColumn(15).setPreferredWidth(25);
            tblNucleo1.getColumnModel().getColumn(16).setResizable(false);
            tblNucleo1.getColumnModel().getColumn(16).setPreferredWidth(25);
            tblNucleo1.getColumnModel().getColumn(17).setResizable(false);
            tblNucleo1.getColumnModel().getColumn(17).setPreferredWidth(25);
            tblNucleo1.getColumnModel().getColumn(18).setResizable(false);
            tblNucleo1.getColumnModel().getColumn(18).setPreferredWidth(25);
            tblNucleo1.getColumnModel().getColumn(19).setResizable(false);
            tblNucleo1.getColumnModel().getColumn(19).setPreferredWidth(25);
            tblNucleo1.getColumnModel().getColumn(20).setResizable(false);
            tblNucleo1.getColumnModel().getColumn(20).setPreferredWidth(25);
            tblNucleo1.getColumnModel().getColumn(21).setResizable(false);
            tblNucleo1.getColumnModel().getColumn(21).setPreferredWidth(25);
            tblNucleo1.getColumnModel().getColumn(22).setResizable(false);
            tblNucleo1.getColumnModel().getColumn(22).setPreferredWidth(25);
            tblNucleo1.getColumnModel().getColumn(23).setResizable(false);
            tblNucleo1.getColumnModel().getColumn(23).setPreferredWidth(25);
            tblNucleo1.getColumnModel().getColumn(24).setResizable(false);
            tblNucleo1.getColumnModel().getColumn(24).setPreferredWidth(25);
            tblNucleo1.getColumnModel().getColumn(25).setResizable(false);
            tblNucleo1.getColumnModel().getColumn(25).setPreferredWidth(25);
            tblNucleo1.getColumnModel().getColumn(26).setResizable(false);
            tblNucleo1.getColumnModel().getColumn(26).setPreferredWidth(25);
            tblNucleo1.getColumnModel().getColumn(27).setResizable(false);
            tblNucleo1.getColumnModel().getColumn(27).setPreferredWidth(25);
            tblNucleo1.getColumnModel().getColumn(28).setResizable(false);
            tblNucleo1.getColumnModel().getColumn(28).setPreferredWidth(25);
            tblNucleo1.getColumnModel().getColumn(29).setResizable(false);
            tblNucleo1.getColumnModel().getColumn(29).setPreferredWidth(25);
            tblNucleo1.getColumnModel().getColumn(30).setResizable(false);
            tblNucleo1.getColumnModel().getColumn(30).setPreferredWidth(25);
        }

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resultado Procesos Nucleo 2", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 18))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 393, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 295, Short.MAX_VALUE)
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resultados Procesos Nucleo 1", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 18)))); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 295, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarActionPerformed
        // TODO add your handling code here:
        JFileChooser filechooser = new JFileChooser();
        filechooser.setFileFilter(filter);
        int opcion=filechooser.showOpenDialog(this);
        if(opcion == JFileChooser.APPROVE_OPTION){
            //obtener ruta url del archivo
            String urlArchivo = filechooser.getSelectedFile().getPath();
            String nombre = filechooser.getSelectedFile().getName();
            txtCantidadArchivos.setText(urlArchivo);
            nuevo = new Archivo(urlArchivo);
            tablaProcesos();
            cargarMemoria();
        }
    }//GEN-LAST:event_btnCargarActionPerformed
    private void cargarMemoria(){
        algoritmosMemoria memorias = new algoritmosMemoria(algoritmoM.get(0).toString(),algoritmoM.get(1).toString(),algoritmoM.get(2).toString(), infMemoria.get(0).getValor(),infMemoria.get(2).getValor(), nuevo.getListaProcesos());
        for(String pro : memorias.getMemoria()){
            String datos[]={pro,"0"};
            memory.addRow(datos);
        }
        for(String pro : memorias.getMemoriaV()){
            String datos[]={pro,"0"};
            memoryVirtual.addRow(datos);
        }
    }
    private void tablaProcesos(){
        for(Proceso pro : nuevo.getListaProcesos()){
            String datos[]={"Proceso"+pro.getNumeroProceso(),String.valueOf(pro.getAtendido()),"0"};
            tablaProcesos.addRow(datos);
        }
    }
    private void btnEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEjecutarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEjecutarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(vGestor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vGestor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vGestor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vGestor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vGestor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCargar;
    private javax.swing.JButton btnEjecutar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable tblNucleo1;
    private javax.swing.JTable tblNucleo2;
    private javax.swing.JTable tblProcesos;
    private javax.swing.JTable tlbMemory;
    private javax.swing.JTable tlbMemoryVirtual;
    private javax.swing.JTextField txtCantidadArchivos;
    // End of variables declaration//GEN-END:variables
}
