package co.edu.udea.ludens.applet.gui;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PanelElement.java
 *
 * Created on Mar 22, 2011, 12:51:17 PM
 */



/**
 *
 * @author juanga
 */
public class PanelElement extends javax.swing.JPanel {

    /** Creates new form PanelElement */
    public PanelElement() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lbIcon = new javax.swing.JLabel();
        lbResourceName = new javax.swing.JLabel();
        lbAskuplevel = new javax.swing.JLabel();
        pnlActual = new javax.swing.JPanel();
        pnlRequired = new javax.swing.JPanel();
        lbLevelProduction = new javax.swing.JLabel();

        setBackground(new java.awt.Color(254, 254, 254));

        jPanel1.setBackground(new java.awt.Color(254, 254, 254));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Elemento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12), new java.awt.Color(1, 1, 1))); // NOI18N

        jLabel2.setText("1");

        lbIcon.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbResourceName.setFont(new java.awt.Font("Arial", 1, 12));
        lbResourceName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbResourceName.setText("Agua");

        lbAskuplevel.setFont(new java.awt.Font("Arial", 1, 12));
        lbAskuplevel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAskuplevel.setText("¿Subir nivel?");

        pnlActual.setBackground(java.awt.Color.white);
        pnlActual.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Recursos Actuales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12))); // NOI18N

        javax.swing.GroupLayout pnlActualLayout = new javax.swing.GroupLayout(pnlActual);
        pnlActual.setLayout(pnlActualLayout);
        pnlActualLayout.setHorizontalGroup(
            pnlActualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        pnlActualLayout.setVerticalGroup(
            pnlActualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 137, Short.MAX_VALUE)
        );

        pnlRequired.setBackground(java.awt.Color.white);
        pnlRequired.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Recursos Requeridos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12))); // NOI18N

        javax.swing.GroupLayout pnlRequiredLayout = new javax.swing.GroupLayout(pnlRequired);
        pnlRequired.setLayout(pnlRequiredLayout);
        pnlRequiredLayout.setHorizontalGroup(
            pnlRequiredLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 148, Short.MAX_VALUE)
        );
        pnlRequiredLayout.setVerticalGroup(
            pnlRequiredLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 137, Short.MAX_VALUE)
        );

        lbLevelProduction.setText("Siguiente nivel en n segundos");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(lbResourceName))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbLevelProduction, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbAskuplevel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbIcon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(pnlActual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlRequired, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlRequired, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlActual, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbResourceName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(lbIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbAskuplevel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbLevelProduction)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private boolean active;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbAskuplevel;
    private javax.swing.JLabel lbIcon;
    private javax.swing.JLabel lbLevelProduction;
    private javax.swing.JLabel lbResourceName;
    private javax.swing.JPanel pnlActual;
    private javax.swing.JPanel pnlRequired;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the lbResourceName
     */
    public javax.swing.JLabel getLbResourceName() {
        return lbResourceName;
    }

    /**
     * @param lbResourceName the lbResourceName to set
     */
    public void setLbResourceName(javax.swing.JLabel lbResourceName) {
        this.lbResourceName = lbResourceName;
    }

    /**
     * @return the lbIcon
     */
    public javax.swing.JLabel getLbIcon() {
        return lbIcon;
    }

    /**
     * @param lbIcon the lbIcon to set
     */
    public void setLbIcon(javax.swing.JLabel lbIcon) {
        this.lbIcon = lbIcon;
    }

    public JPanel getPnlRequired(){
    return this.pnlRequired;
    }

    public JPanel getPnlActual(){
    return this.pnlActual;
    }

    public JLabel getLbAskuplevel(){
    return this.lbAskuplevel;
    }


    public JLabel getLbLevelProduction(){
    return this.lbLevelProduction;
    }

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

}
