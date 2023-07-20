package com.trabalhofinal.trabalhofinal.PRESENTER;

import com.trabalhofinal.trabalhofinal.DAO.NotificacaoDAO;
import com.trabalhofinal.trabalhofinal.LOGGER.ILogger;
import com.trabalhofinal.trabalhofinal.MODEL.Admin;
import com.trabalhofinal.trabalhofinal.MODEL.INTERFACE.IObservable;
import com.trabalhofinal.trabalhofinal.MODEL.INTERFACE.IObserver;
import com.trabalhofinal.trabalhofinal.MODEL.Log;
import com.trabalhofinal.trabalhofinal.MODEL.Notificacao;
import com.trabalhofinal.trabalhofinal.VIEW.EnviarNotificacaoView;

import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.ArrayList;

public class EnviarNotificacaoPresenter implements IObservable {

    private final EnviarNotificacaoView view;
    private final ArrayList<IObserver> observers;
    private final ILogger logger;
    private final NotificacaoDAO dao;
    private final Admin admin;

    public EnviarNotificacaoPresenter(ILogger logger, JDesktopPane desktop, Admin admin,
            ArrayList<Integer> users) {
        view = new EnviarNotificacaoView();
        observers = new ArrayList<>();
        dao = new NotificacaoDAO();
        this.admin = admin;
        this.logger = logger;

        view.getBtnFechar().addActionListener((ActionEvent ae) -> {
            view.dispose();
        });

        view.getBtnEnviar().addActionListener((ActionEvent ae) -> {
            enviar(users);
        });

        desktop.add(view);
        view.setVisible(true);
    }

    private void enviar(ArrayList<Integer> users) {
        String msg = view.getTxtMensagem().getText();

        try {
            for (Integer id : users) {
                dao.inserirNotificacao(new Notificacao(id, admin.getId(), msg, false, LocalDate.now()));
            }

            JOptionPane.showMessageDialog(view, "Mensagem enviada com Sucesso!");

            logger.logUsuario(new Log("Envio de notificação", admin.getNome(), admin.getUsername(), "-"));
            notificaObserver(null);
            view.dispose();
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(view, e.getMessage());
            logger.logErro(new Log("Envio de notificação", admin.getNome(), admin.getUsername(), e.getMessage()));
        }
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
            o.atualiza(obj);
        }
    }

}
