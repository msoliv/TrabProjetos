package com.trabalhofinal.trabalhofinal.PRESENTER;

import com.trabalhofinal.trabalhofinal.DAO.NotificacaoDAO;
import com.trabalhofinal.trabalhofinal.LOGGER.CSVLogger;
import com.trabalhofinal.trabalhofinal.LOGGER.ILogger;
import com.trabalhofinal.trabalhofinal.LOGGER.JSONLogger;
import com.trabalhofinal.trabalhofinal.LOGGER.XMLLogger;
import com.trabalhofinal.trabalhofinal.MODEL.Admin;
import com.trabalhofinal.trabalhofinal.MODEL.ESTADO.EstadoLogin;
import com.trabalhofinal.trabalhofinal.MODEL.ESTADO.UsuarioDeslogado;
import com.trabalhofinal.trabalhofinal.MODEL.INTERFACE.IObserver;
import com.trabalhofinal.trabalhofinal.MODEL.Notificacao;
import com.trabalhofinal.trabalhofinal.MODEL.UsuarioAbs;
import com.trabalhofinal.trabalhofinal.VIEW.MainView;
import javax.swing.JInternalFrame;
import java.awt.event.ActionEvent;

public class MainPresenter implements IObserver {

    private final MainView view;
    private UsuarioAbs usuario;
    private EstadoLogin state;
    private ILogger logger;
    private final NotificacaoDAO nDao;

    public MainPresenter() {
        view = new MainView();
        logger = new CSVLogger();
        nDao = new NotificacaoDAO();
        setState(new UsuarioDeslogado(this));

        // Ações dos botões do menu
        view.getMnuListUsuario().addActionListener((ActionEvent ae) -> {
            new ListarUsuariosPresenter((Admin) usuario, view.getDesktop(), logger).adicionaObserver(this);
        });

        view.getMnuLogs().addActionListener((ActionEvent ae) -> {
            new ConfiguracoesPresenter(view.getDesktop(), logger).adicionaObserver(this);
        });

        view.getMnuAlterar().addActionListener((ActionEvent ae) -> {
            new CadastroPresenter(view.getDesktop(), Admin.class.isInstance(usuario), usuario, true, logger).adicionaObserver(this);
        });

        view.getBtnNotificacoes().addActionListener((ActionEvent ae) -> {
            new NotificacoesPresenter(view.getDesktop(), usuario, logger).adicionaObserver(this);
        });

        view.getMnuLogout().addActionListener((ActionEvent ae) -> {
            logout();
        });

        view.setVisible(true);
    }

    public void usuarioLayout() {
        view.getLblTipoUser().setText("Usuário");
        view.getLblUser().setText(usuario.getUsername());
        view.getBtnNotificacoes().setText("Notificações");
        view.getMnuUsuario().setVisible(true);
        view.getMnuConfiguracoes().setVisible(true);
        view.getBtnNotificacoes().setEnabled(true);
        atualizaNotificacao();
    }

    public void adminLayout() {
        view.getLblTipoUser().setText("Admin");
        view.getLblUser().setText(usuario.getUsername());
        view.getBtnNotificacoes().setText("Notificações");
        view.getMnuAdmin().setVisible(true);
        view.getMnuUsuario().setVisible(true);
        view.getMnuConfiguracoes().setVisible(true);
        view.getBtnNotificacoes().setEnabled(true);
        view.getBtnNotificacoes().setText("Notificações");
        atualizaNotificacao();
    }

    private void login() {
        new LoginPresenter(view.getDesktop(), logger).adicionaObserver(this);
    }

    private void atualizaNotificacao() {
        int qtd = nDao.contaNotificacaoNaoLida(usuario.getId());
        view.getBtnNotificacoes().setText(qtd + " Notificações");
    }

    private void atualiza() {
        atualizaNotificacao();
    }

    private void atualiza(UsuarioAbs usuario) {
        this.usuario = usuario;
        state.login(usuario);
    }

    private void atualiza(ILogger logger) {
        this.logger = logger;
    }

    private void fechaInternalFrames() {
        for (JInternalFrame j : view.getDesktop().getAllFrames()) {
            j.dispose();
        }
    }

    private void logout() {
        fechaInternalFrames();
        state.logout();
    }

    public ILogger getLogger() {
        return logger;
    }

    public void setState(EstadoLogin state) {
        this.state = state;
    }

    public MainView getView() {
        return view;
    }

    public UsuarioAbs getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioAbs usuario) {
        this.usuario = usuario;
    }

    @Override
    public void atualiza(Object obj) {
        if (obj instanceof UsuarioAbs) {
            atualiza((UsuarioAbs) obj);
        } else if (obj instanceof ILogger) {
            if (obj instanceof CSVLogger) {
                atualiza((CSVLogger) obj);
            } else if (obj instanceof JSONLogger) {
                atualiza((JSONLogger) obj);
            } else if (obj instanceof XMLLogger) {
                atualiza((XMLLogger) obj);
            }
        } else if (obj instanceof Notificacao) {
            atualiza();
        }
    }
}
