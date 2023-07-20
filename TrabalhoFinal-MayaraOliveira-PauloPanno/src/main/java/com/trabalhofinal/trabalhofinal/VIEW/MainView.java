
package com.trabalhofinal.trabalhofinal.VIEW;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;


public class MainView extends javax.swing.JFrame {


    public MainView() {
        initComponents();
        mnuAdmin.setVisible(false);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktop = new javax.swing.JDesktopPane();
        lblUser = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblTipoUser = new javax.swing.JLabel();
        btnNotificacoes = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuUsuario = new javax.swing.JMenu();
        mnuAlterar = new javax.swing.JMenuItem();
        mnuLogout = new javax.swing.JMenuItem();
        mnuConfiguracoes = new javax.swing.JMenu();
        mnuLogs = new javax.swing.JMenuItem();
        mnuAdmin = new javax.swing.JMenu();
        mnuListUsuario = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        javax.swing.GroupLayout desktopLayout = new javax.swing.GroupLayout(desktop);
        desktop.setLayout(desktopLayout);
        desktopLayout.setHorizontalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        desktopLayout.setVerticalGroup(
            desktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 636, Short.MAX_VALUE)
        );

        jLabel3.setText("(");

        btnNotificacoes.setText("Notificações");

        jLabel4.setText(")");

        jLabel5.setText("Olá, ");

        mnuUsuario.setText("Usuario");

        mnuAlterar.setText("Alterar Informações");
        mnuUsuario.add(mnuAlterar);

        mnuLogout.setText("Logout");
        mnuUsuario.add(mnuLogout);

        jMenuBar1.add(mnuUsuario);

        mnuConfiguracoes.setText("Opções");

        mnuLogs.setText("Tipo de Log");
        mnuConfiguracoes.add(mnuLogs);

        jMenuBar1.add(mnuConfiguracoes);

        mnuAdmin.setText("Opções Administrador");

        mnuListUsuario.setText("Listar Usuarios");
        mnuAdmin.add(mnuListUsuario);

        jMenuBar1.add(mnuAdmin);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 482, Short.MAX_VALUE)
                        .addComponent(btnNotificacoes, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(205, 205, 205)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblUser, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTipoUser, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(desktop)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnNotificacoes)
                    .addComponent(lblUser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTipoUser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(desktop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JButton getBtnNotificacoes() {
        return btnNotificacoes;
    }

    public void setBtnNotificacoes(JButton btnNotificacoes) {
        this.btnNotificacoes = btnNotificacoes;
    }

    public JDesktopPane getDesktop() {
        return desktop;
    }

    public void setDesktop(JDesktopPane desktop) {
        this.desktop = desktop;
    }

    public JLabel getLblTipoUser() {
        return lblTipoUser;
    }

    public void setLblTipoUser(JLabel lblTipoUser) {
        this.lblTipoUser = lblTipoUser;
    }

    public JLabel getLblUser() {
        return lblUser;
    }

    public void setLblUser(JLabel lblUser) {
        this.lblUser = lblUser;
    }

    public JMenu getMnuAdmin() {
        return mnuAdmin;
    }

    public void setMnuAdmin(JMenu mnuAdmin) {
        this.mnuAdmin = mnuAdmin;
    }

    public JMenuItem getMnuAlterar() {
        return mnuAlterar;
    }

    public void setMnuAlterar(JMenuItem mnuAlterar) {
        this.mnuAlterar = mnuAlterar;
    }

    public JMenu getMnuConfiguracoes() {
        return mnuConfiguracoes;
    }

    public void setMnuConfiguracoes(JMenu mnuConfiguracoes) {
        this.mnuConfiguracoes = mnuConfiguracoes;
    }

    public JMenuItem getMnuListUsuario() {
        return mnuListUsuario;
    }

    public void setMnuListUsuario(JMenuItem mnuListUsuario) {
        this.mnuListUsuario = mnuListUsuario;
    }

    public JMenuItem getMnuLogout() {
        return mnuLogout;
    }

    public void setMnuLogout(JMenuItem mnuLogout) {
        this.mnuLogout = mnuLogout;
    }

    public JMenuItem getMnuLogs() {
        return mnuLogs;
    }

    public void setMnuLogs(JMenuItem mnuLogs) {
        this.mnuLogs = mnuLogs;
    }

    public JMenu getMnuUsuario() {
        return mnuUsuario;
    }

    public void setMnuUsuario(JMenu mnuUsuario) {
        this.mnuUsuario = mnuUsuario;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNotificacoes;
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel lblTipoUser;
    private javax.swing.JLabel lblUser;
    private javax.swing.JMenu mnuAdmin;
    private javax.swing.JMenuItem mnuAlterar;
    private javax.swing.JMenu mnuConfiguracoes;
    private javax.swing.JMenuItem mnuListUsuario;
    private javax.swing.JMenuItem mnuLogout;
    private javax.swing.JMenuItem mnuLogs;
    private javax.swing.JMenu mnuUsuario;
    // End of variables declaration//GEN-END:variables
}
