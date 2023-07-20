
package com.trabalhofinal.trabalhofinal.VIEW;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;


public class TipoLog extends javax.swing.JInternalFrame {


    public TipoLog() {
        initComponents();
        bgpLogs.add(rbtCSV);
        bgpLogs.add(rbtJSON);
        bgpLogs.add(rbtXML);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgpLogs = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        rbtCSV = new javax.swing.JRadioButton();
        rbtJSON = new javax.swing.JRadioButton();
        rbtXML = new javax.swing.JRadioButton();
        btnSalvar = new javax.swing.JButton();
        btnFechar = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N
        jLabel1.setText("Tipo de LOG");

        rbtCSV.setText("CSV");

        rbtJSON.setText("JSON");
        rbtJSON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtJSONActionPerformed(evt);
            }
        });

        rbtXML.setText("XML");

        btnSalvar.setText("Salvar");

        btnFechar.setText("Fechar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnFechar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalvar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rbtCSV)
                            .addComponent(rbtJSON)
                            .addComponent(rbtXML))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbtCSV)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbtXML)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbtJSON)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFechar)
                    .addComponent(btnSalvar))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbtJSONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtJSONActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbtJSONActionPerformed

    public ButtonGroup getBgpLogs() {
        return bgpLogs;
    }

    public void setBgpLogs(ButtonGroup bgpLogs) {
        this.bgpLogs = bgpLogs;
    }

    public JButton getBtnFechar() {
        return btnFechar;
    }

    public void setBtnFechar(JButton btnFechar) {
        this.btnFechar = btnFechar;
    }

    public JButton getBtnSalvar() {
        return btnSalvar;
    }

    public void setBtnSalvar(JButton btnSalvar) {
        this.btnSalvar = btnSalvar;
    }

    public JRadioButton getRbtCSV() {
        return rbtCSV;
    }

    public void setRbtCSV(JRadioButton rbtCSV) {
        this.rbtCSV = rbtCSV;
    }

    public JRadioButton getRbtJSON() {
        return rbtJSON;
    }

    public void setRbtJSON(JRadioButton rbtJSON) {
        this.rbtJSON = rbtJSON;
    }

    public JRadioButton getRbtXML() {
        return rbtXML;
    }

    public void setRbtXML(JRadioButton rbtXML) {
        this.rbtXML = rbtXML;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgpLogs;
    private javax.swing.JButton btnFechar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JRadioButton rbtCSV;
    private javax.swing.JRadioButton rbtJSON;
    private javax.swing.JRadioButton rbtXML;
    // End of variables declaration//GEN-END:variables
}
