package com.trabalhofinal.trabalhofinal.MODEL.ESTADO;

import com.trabalhofinal.trabalhofinal.MODEL.Admin;
import com.trabalhofinal.trabalhofinal.MODEL.UsuarioAbs;
import com.trabalhofinal.trabalhofinal.PRESENTER.LoginPresenter;
import com.trabalhofinal.trabalhofinal.PRESENTER.MainPresenter;


public class UsuarioDeslogado extends EstadoLogin {

    public UsuarioDeslogado(MainPresenter presenter) {
        super(presenter);
        presenter.setUsuario(null);
        presenter.getView().getLblUser().setText("");
        presenter.getView().getLblTipoUser().setText("");
        presenter.getView().getBtnNotificacoes().setText("Notificações");
        presenter.getView().getMnuAdmin().setVisible(false);
        presenter.getView().getMnuUsuario().setVisible(false);
        presenter.getView().getMnuConfiguracoes().setVisible(false);
        presenter.getView().getBtnNotificacoes().setEnabled(false);
        new LoginPresenter(presenter.getView().getDesktop(), presenter.getLogger()).adicionaObserver(presenter);
    }

    @Override
    public void login(UsuarioAbs usuario) {
        if (Admin.class.isInstance(usuario)) {
            this.getPresenter().setState(new AdminLogado(this.getPresenter()));
        } else {
            this.getPresenter().setState(new UsuarioLogado(this.getPresenter()));
        }
    }
}
