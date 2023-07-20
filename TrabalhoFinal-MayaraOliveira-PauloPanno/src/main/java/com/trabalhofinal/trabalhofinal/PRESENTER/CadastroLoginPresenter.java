package com.trabalhofinal.trabalhofinal.PRESENTER;

import com.trabalhofinal.trabalhofinal.DAO.UsuarioDAO;
import com.trabalhofinal.trabalhofinal.LOGGER.ILogger;
import com.trabalhofinal.trabalhofinal.MODEL.Admin;
import com.trabalhofinal.trabalhofinal.MODEL.INTERFACE.IObservable;
import com.trabalhofinal.trabalhofinal.MODEL.INTERFACE.IObserver;
import com.trabalhofinal.trabalhofinal.MODEL.Log;
import com.trabalhofinal.trabalhofinal.MODEL.Usuario;
import com.trabalhofinal.trabalhofinal.MODEL.UsuarioAbs;
import com.trabalhofinal.trabalhofinal.VIEW.CadastroLoginView;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

public class CadastroLoginPresenter implements IObservable {

    private final CadastroLoginView view;
    private final UsuarioDAO dao;
    private final ArrayList<IObserver> observers;
    private final ILogger logger;

    public CadastroLoginPresenter(JDesktopPane desktop, LoginPresenter login, boolean primeiro, ILogger logger) {
        view = new CadastroLoginView();
        dao = new UsuarioDAO();
        observers = new ArrayList<>();
        this.logger = logger;

        if (primeiro) {
            view.getCkbAdmin().setVisible(true);
            view.getCkbAdmin().setSelected(true);
            view.getCkbAdmin().setEnabled(false);
        } else {
            view.getCkbAdmin().setVisible(false);
        }

        view.getBtnFechar().addActionListener((ActionEvent ae) -> {
            view.dispose();
        });

        view.getBtnSalvar().addActionListener((ActionEvent ae) -> {
            salvar(login, desktop);
            view.dispose();
        });

        desktop.add(view);
        view.setVisible(true);
    }

    private void salvar(LoginPresenter login, JDesktopPane desktop) {
        String nome = view.getTxtNome().getText();
        String username = view.getTxtUsername().getText();
        String senha = String.valueOf(view.getTxtSenha().getPassword());
        String senhaR = String.valueOf(view.getTxtSenhaRepeticao().getPassword());
        boolean admin = view.getCkbAdmin().isSelected();
        LocalDate data = LocalDate.now();

        if (!senha.equals(senhaR)) {
            JOptionPane.showMessageDialog(view, "Senhas não coincidem");
        } else if (dao.verificaUsername(username)) {
            JOptionPane.showMessageDialog(view, "Nome de usuário existente.");
        } else {
            try {
                UsuarioAbs usuario;
                if (admin) {
                    usuario = new Admin(nome, username, senha, data);
                } else {
                    usuario = new Usuario(nome, username, senha, data, false);
                }
                dao.inserir(usuario);
                logger.logUsuario(new Log("Cadastro", nome, username, "-"));
                JOptionPane.showMessageDialog(view, "Usuário " + nome + " cadastrado com sucesso!");

                ArrayList<IObserver> temp = login.getObservers();
                login = new LoginPresenter(desktop, logger);

                for (IObserver o : temp) {
                    login.adicionaObserver(o);
                }

            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(view, "Erro ao cadastrar usuário! " + e.getMessage());
                logger.logErro(new Log("Cadstro", nome, username, e.getMessage()));
            }
        }

    }

    public CadastroLoginView getView() {
        return view;
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
