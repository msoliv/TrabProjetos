package com.trabalhofinal.trabalhofinal.PRESENTER;

import com.trabalhofinal.trabalhofinal.DAO.UsuarioDAO;
import com.trabalhofinal.trabalhofinal.LOGGER.ILogger;
import com.trabalhofinal.trabalhofinal.MODEL.INTERFACE.IObservable;
import com.trabalhofinal.trabalhofinal.MODEL.INTERFACE.IObserver;
import com.trabalhofinal.trabalhofinal.MODEL.UsuarioAbs;
import com.trabalhofinal.trabalhofinal.VIEW.LoginView;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

public class LoginPresenter implements IObservable {

    private final LoginView view;
    private final UsuarioDAO dao;
    private final ArrayList<IObserver> observers;
    private ILogger logger;

    public LoginPresenter(JDesktopPane desktop, ILogger logger) {
        view = new LoginView();
        dao = new UsuarioDAO();
        observers = new ArrayList<>();
        this.logger = logger;

        view.getBtnLogin().addActionListener(((ActionEvent ae) -> {
            login();
        }));

        view.getBtnCadastrar().addActionListener((ActionEvent ae) -> {
            cadastrar(desktop);
        });

        desktop.add(view);
        view.setVisible(true);
    }

    private void cadastrar(JDesktopPane desktop) {
        new CadastroLoginPresenter(desktop, this, UsuarioDAO.contaUsuarios() == 0, logger);

        view.dispose();

    }

    private void login() {

        String username = view.getTxtUsername().getText();
        String senha = String.valueOf(view.getTxtSenha().getPassword());

        if (username.isBlank() || username.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Nome de usuário inválido!");
        } else if (senha.isEmpty() || senha.isBlank()) {
            JOptionPane.showMessageDialog(view, "Senha inválida!");
        } else {

            try {
                UsuarioAbs usuario = dao.login(username, senha);

                if (usuario == null) {
                    JOptionPane.showMessageDialog(view, "Erro ao efetuar login!");
                } else if (UsuarioDAO.isAutorizado(usuario.getId())) {
                    notificaObserver((UsuarioAbs) usuario);
                    view.dispose();
                } else {
                    JOptionPane.showMessageDialog(view, "Usuário não autorizado!\n"
                            + "Aguardar autorização de Administrador!");
                }

            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(view, e.getMessage());
            }
        }

    }

    public ArrayList<IObserver> getObservers() {
        return observers;
    }

    @Override
    public void adicionaObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notificaObserver(Object obj) {
        for (IObserver o : observers) {
            o.atualiza((UsuarioAbs) obj);
        }
    }
}
