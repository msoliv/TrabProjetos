package com.trabalhofinal.trabalhofinal.MODEL.ESTADO;

import com.trabalhofinal.trabalhofinal.PRESENTER.MainPresenter;


public class UsuarioLogado extends EstadoLogin {

    public UsuarioLogado(MainPresenter presenter) {
        super(presenter);
        presenter.usuarioLayout();

    }

    @Override
    public void logout() {
        this.getPresenter().setState(new UsuarioDeslogado(this.getPresenter()));
    }
}
