package com.trabalhofinal.trabalhofinal.MODEL.ESTADO;

import com.trabalhofinal.trabalhofinal.PRESENTER.MainPresenter;


public class AdminLogado extends EstadoLogin {

    public AdminLogado(MainPresenter presenter) {
        super(presenter);
        presenter.adminLayout();

    }

    @Override
    public void logout() {
        this.getPresenter().setState(new UsuarioDeslogado(this.getPresenter()));
    }
}
