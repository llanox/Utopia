/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MapDashboardPanel.java
 *
 *
 */
package co.edu.udea.ludens.applet.gui;

import javax.swing.JPanel;
import javax.swing.JTable;

/**
 *
 * @author juanga
 */
public class MapDashboardPanel extends javax.swing.JPanel {

    /** Creates new form MapDashboardPanel */
    public MapDashboardPanel() {
        initComponents();
        tbFactors.setDefaultRenderer(String.class, new LabelRenderer());
        tbMaterials.setDefaultRenderer(String.class, new LabelRenderer());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        map = new javax.swing.JPanel();
        lbMateriales = new javax.swing.JLabel();
        lbFactores = new javax.swing.JLabel();
        lbIndicators = new javax.swing.JLabel();
        pnlIndicators = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbFactors = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbMaterials = new javax.swing.JTable();

        setBackground(java.awt.Color.white);
        setLayout(null);

        map.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.orange));

        javax.swing.GroupLayout mapLayout = new javax.swing.GroupLayout(map);
        map.setLayout(mapLayout);
        mapLayout.setHorizontalGroup(
            mapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 768, Short.MAX_VALUE)
        );
        mapLayout.setVerticalGroup(
            mapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 368, Short.MAX_VALUE)
        );

        add(map);
        map.setBounds(20, 120, 770, 370);

        lbMateriales.setBackground(java.awt.Color.orange);
        lbMateriales.setFont(new java.awt.Font("Arial", 1, 12));
        lbMateriales.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbMateriales.setText("Materiales");
        lbMateriales.setBorder(new javax.swing.border.LineBorder(java.awt.Color.orange, 1, true));
        add(lbMateriales);
        lbMateriales.setBounds(90, 610, 630, 20);

        lbFactores.setBackground(java.awt.Color.orange);
        lbFactores.setFont(new java.awt.Font("Arial", 1, 12));
        lbFactores.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbFactores.setText("Factores");
        lbFactores.setBorder(new javax.swing.border.LineBorder(java.awt.Color.orange, 1, true));
        add(lbFactores);
        lbFactores.setBounds(90, 490, 630, 20);

        lbIndicators.setBackground(java.awt.Color.orange);
        lbIndicators.setFont(new java.awt.Font("Arial", 1, 12));
        lbIndicators.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbIndicators.setText("Otros Indicadores");
        lbIndicators.setBorder(new javax.swing.border.LineBorder(java.awt.Color.orange, 1, true));
        add(lbIndicators);
        lbIndicators.setBounds(20, 60, 770, 20);

        pnlIndicators.setBackground(java.awt.Color.lightGray);
        pnlIndicators.setBorder(new javax.swing.border.LineBorder(java.awt.Color.orange, 1, true));
        pnlIndicators.setLayout(new java.awt.GridLayout(0, 1, 5, 5));
        add(pnlIndicators);
        pnlIndicators.setBounds(20, 80, 770, 40);

        tbFactors.setBorder(new javax.swing.border.LineBorder(java.awt.Color.orange, 1, true));
        tbFactors.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        tbFactors.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbFactors);

        add(jScrollPane1);
        jScrollPane1.setBounds(90, 510, 630, 100);

        tbMaterials.setBorder(new javax.swing.border.LineBorder(java.awt.Color.orange, 1, true));
        tbMaterials.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        tbMaterials.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tbMaterials);

        add(jScrollPane2);
        jScrollPane2.setBounds(90, 630, 630, 100);
    }// </editor-fold>//GEN-END:initComponents

    public JPanel getMap() {
        return map;
    }

  

    public JPanel getPnlIndicators() {
        return pnlIndicators;
    }

    public void setPnlIndicators(JPanel pnlIndicators) {
        this.pnlIndicators = pnlIndicators;
    }



    public void setTbFactors(JTable tbFactors) {
        this.tbFactors = tbFactors;
    }

    public void setTbMaterials(JTable tbMaterials) {
        this.tbMaterials = tbMaterials;
    }

    public JTable getTbMaterials() {
        return tbMaterials;
    }

    public JTable getTbFactors() {
        return tbFactors;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbFactores;
    private javax.swing.JLabel lbIndicators;
    private javax.swing.JLabel lbMateriales;
    private javax.swing.JPanel map;
    private javax.swing.JPanel pnlIndicators;
    private javax.swing.JTable tbFactors;
    private javax.swing.JTable tbMaterials;
    // End of variables declaration//GEN-END:variables
}
