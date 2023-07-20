package com.trabalhofinal.trabalhofinal;

import com.trabalhofinal.trabalhofinal.DAO.NotificacaoDAO;
import com.trabalhofinal.trabalhofinal.DAO.UsuarioDAO;
import com.trabalhofinal.trabalhofinal.DB.DB;
import com.trabalhofinal.trabalhofinal.PRESENTER.MainPresenter;


public class Main {

    public static void main(String[] args) {
        DB.criaDiretorio();
        UsuarioDAO.criarTabelaUsuario();
        NotificacaoDAO.criarTabelaNotificacao();

        MainPresenter main = new MainPresenter();
    }
}
