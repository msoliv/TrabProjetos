package com.trabalhofinal.trabalhofinal.MODEL.ESTADO;

import com.trabalhofinal.trabalhofinal.MODEL.UsuarioAbs;
import com.trabalhofinal.trabalhofinal.PRESENTER.MainPresenter;


public class EstadoLogin {

    private MainPresenter presenter;

    public EstadoLogin(MainPresenter presenter) {
        this.presenter = presenter;
    }

    public void login(UsuarioAbs usuario) {
        throw new RuntimeException("Erro no login");
    }

    public void logout() {
        throw new RuntimeException("Erro no logout");
    }

    public MainPresenter getPresenter() {
        return presenter;
    }

    public void setPresenter(MainPresenter presenter) {
        this.presenter = presenter;
    }
}
