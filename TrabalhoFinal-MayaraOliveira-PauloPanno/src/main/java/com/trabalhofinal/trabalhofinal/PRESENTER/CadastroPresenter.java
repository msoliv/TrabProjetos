package com.trabalhofinal.trabalhofinal.PRESENTER;

import com.trabalhofinal.trabalhofinal.DAO.UsuarioDAO;
import com.trabalhofinal.trabalhofinal.LOGGER.ILogger;
import com.trabalhofinal.trabalhofinal.MODEL.Admin;
import com.trabalhofinal.trabalhofinal.MODEL.INTERFACE.IObservable;
import com.trabalhofinal.trabalhofinal.MODEL.INTERFACE.IObserver;
import com.trabalhofinal.trabalhofinal.MODEL.Log;
import com.trabalhofinal.trabalhofinal.MODEL.Usuario;
import com.trabalhofinal.trabalhofinal.MODEL.UsuarioAbs;
import com.trabalhofinal.trabalhofinal.VIEW.CadastroView;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

public class CadastroPresenter implements IObservable {

    private final CadastroView view;
    private final UsuarioDAO dao;
    private ILogger logger;
    private ArrayList<IObserver> observers;

    public CadastroPresenter(JDesktopPane desktop, boolean admin, ILogger logger) {
        view = new CadastroView();
        dao = new UsuarioDAO();
        this.logger = logger;
        observers = new ArrayList<>();

        view.getCkbAdmin().setVisible(admin);

        view.getBtnRemover().setVisible(false);

        view.getBtnFechar().addActionListener((ActionEvent ae) -> {
            view.dispose();
        });

        view.getBtnSalvar().addActionListener((ActionEvent ae) -> {
            salvar();
            view.dispose();
        });

        desktop.add(view);
        view.setVisible(true);
    }

    public CadastroPresenter(JDesktopPane desktop, boolean admin, UsuarioAbs usuario, boolean editar, ILogger logger) {
        view = new CadastroView();
        dao = new UsuarioDAO();
        this.logger = logger;
        observers = new ArrayList<>();

        view.getCkbAdmin().setVisible(admin);
        view.getCkbAdmin().setSelected(admin);
        view.getCkbAdmin().setEnabled(false);

        view.getBtnRemover().setVisible(false);
        view.getTxtNome().setText(usuario.getNome());
        view.getTxtUsername().setText(usuario.getUsername());

        view.getTxtSenha().setText(usuario.getSenha());
        view.getTxtSenhaRepeticao().setText(usuario.getSenha());

        view.getBtnFechar().addActionListener((ActionEvent ae) -> {
            view.dispose();
        });

        view.getBtnSalvar().addActionListener((ActionEvent ae) -> {
            atualizar(usuario);
            view.dispose();
        });

        desktop.add(view);
        view.setVisible(true);
    }

    public CadastroPresenter(JDesktopPane desktop, boolean admin, UsuarioAbs usuario, ILogger logger) {
        view = new CadastroView();
        dao = new UsuarioDAO();
        this.logger = logger;

        view.getCkbAdmin().setVisible(admin);
        view.getCkbAdmin().setSelected(admin);
        view.getCkbAdmin().setEnabled(false);

        view.getBtnSalvar().setText("Editar");

        view.getTxtNome().setText(usuario.getNome());
        view.getTxtUsername().setText(usuario.getUsername());

        view.getTxtSenha().setText(usuario.getSenha());
        view.getTxtSenhaRepeticao().setText(usuario.getSenha());

        view.getTxtNome().setEnabled(false);
        view.getTxtUsername().setEnabled(false);
        view.getTxtSenha().setEnabled(false);
        view.getTxtSenhaRepeticao().setEnabled(false);

        view.getBtnFechar().addActionListener((ActionEvent ae) -> {
            view.dispose();
        });

        view.getBtnSalvar().addActionListener((ActionEvent ae) -> {
            new CadastroPresenter(desktop, admin, usuario, true, logger);
            view.dispose();
        });

        view.getBtnRemover().addActionListener((ActionEvent ae) -> {
            remover(usuario);
            view.dispose();
        });

        desktop.add(view);
        view.setVisible(true);
    }

    private void remover(UsuarioAbs usuario) {
        try {
            dao.deletar(usuario.getId());
            logger.logUsuario(new Log("Remoção", usuario.getNome(), usuario.getUsername(), "-"));
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(view, "Erro ao deletar usuário!" + e.getMessage());
            logger.logErro(new Log("Remoção", usuario.getNome(), usuario.getUsername(), e.getMessage()));
        }
    }

    private void atualizar(UsuarioAbs usuario) {
        String nome = view.getTxtNome().getText();
        String username = view.getTxtUsername().getText();
        String senha = String.valueOf(view.getTxtSenha().getPassword());
        String senhaR = String.valueOf(view.getTxtSenhaRepeticao().getPassword());
        boolean admin = view.getCkbAdmin().isSelected();
        LocalDate data = LocalDate.now();

        if (!senha.equals(senhaR)) {
            JOptionPane.showMessageDialog(view, "Senhas não coincidem");
        } else {
            try {

                if (admin) {
                    dao.atualizar(new Admin(usuario.getId(), nome, username, senha, data));
                } else {
                    dao.atualizar(new Usuario(usuario.getId(), nome, username, senha, data, false));
                }

                logger.logUsuario(new Log("Atualização", nome, username, "-"));
                JOptionPane.showMessageDialog(view, "Usuário " + nome + " atualizado com sucesso! \n Logue novamente para alterar nickname");

            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(view, "Erro ao Atualizar usuário!" + e.getMessage());
                logger.logErro(new Log("Atualização", nome, username, e.getMessage()));
            }
        }

    }

    private void salvar() {
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

                if (admin) {
                    dao.inserir(new Admin(nome, username, senha, data));
                } else {
                    dao.inserir(new Usuario(nome, username, senha, data, false));
                }
                logger.logUsuario(new Log("Cadastro", nome, username, "-"));
                JOptionPane.showMessageDialog(view, "Usuário " + nome + " cadastrado com sucesso!");

            } catch (RuntimeException e) {
                JOptionPane.showMessageDialog(view, "Erro ao cadastrar usuário!" + e.getMessage());
                logger.logErro(new Log("Cadastro", nome, username, e.getMessage()));
            }
        }

    }

    public CadastroView getView() {
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
