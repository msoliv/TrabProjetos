package com.trabalhofinal.trabalhofinal.PRESENTER;

import com.trabalhofinal.trabalhofinal.DAO.NotificacaoDAO;
import com.trabalhofinal.trabalhofinal.DAO.UsuarioDAO;
import com.trabalhofinal.trabalhofinal.LOGGER.ILogger;
import com.trabalhofinal.trabalhofinal.MODEL.INTERFACE.IObservable;
import com.trabalhofinal.trabalhofinal.MODEL.INTERFACE.IObserver;
import com.trabalhofinal.trabalhofinal.MODEL.Log;
import com.trabalhofinal.trabalhofinal.MODEL.Notificacao;
import com.trabalhofinal.trabalhofinal.MODEL.UsuarioAbs;
import com.trabalhofinal.trabalhofinal.VIEW.NotificacoesView;
import java.awt.event.ActionEvent;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class NotificacoesPresenter implements IObservable {

    private final NotificacoesView view;
    private final ArrayList<IObserver> observers;
    private final UsuarioAbs usuario;
    private final DefaultTableModel tmNotificacoes;
    private final NotificacaoDAO nDao;
    private final UsuarioDAO uDao;
    private ILogger logger;

    public NotificacoesPresenter(JDesktopPane desktop, UsuarioAbs usuario, ILogger logger) {
        view = new NotificacoesView();
        observers = new ArrayList<>();
        this.usuario = usuario;
        nDao = new NotificacaoDAO();
        uDao = new UsuarioDAO();
        this.logger = logger;

        tmNotificacoes = new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Remetente", "Notificação", "Lida"}) {
            @Override
            public boolean isCellEditable(final int row, final int column) {
                return false;
            }
        };
        view.getTblNotificacoes().setModel(tmNotificacoes);
        preencheTabela();

        view.getBtnFechar().addActionListener((ActionEvent ae) -> {
            view.dispose();
        });

        view.getBtnVisualizar().addActionListener((ActionEvent ae) -> {
            if (view.getTblNotificacoes().getSelectedRowCount() != 1) {
                JOptionPane.showMessageDialog(view, "Selecione pelo menos e exatamente UMA linha!");
            } else {
                Visualizar();
            }
        });

        desktop.add(view);
        view.setVisible(true);
    }

    private void limpaTabela() {
        tmNotificacoes.setRowCount(0);
        view.getTblNotificacoes().setModel(tmNotificacoes);
    }

    private void preencheTabela() {
        try {
            limpaTabela();
            ArrayList<Notificacao> lista = nDao.getNotificacoes(usuario.getId());
            if (!lista.isEmpty()) {
                for (Notificacao n : lista) {
                    String lida = n.isLida() ? "Sim" : "Não";
                    UsuarioAbs temp = uDao.findById(n.getRemetente());
                    tmNotificacoes.addRow(new Object[]{n.getId(), temp.getUsername(), n.getMensagem(), lida});
                }
            }
            view.getTblNotificacoes().setModel(tmNotificacoes);
        } catch (RuntimeException e) {
            logger.logErro(new Log("REduperação de Notificações", usuario.getNome(), usuario.getUsername(), e.getMessage()));
        }
    }

    private void Visualizar() {
        try {
            int id = Integer.parseInt(view.getTblNotificacoes().getValueAt(view.getTblNotificacoes().getSelectedRow(), 0).toString());
            Notificacao n = nDao.getById(id);
            UsuarioAbs remetente = uDao.findById(n.getRemetente());

            nDao.marcarComoLida(id);
            notificaObserver(n);
            logger.logUsuario(new Log("Visualização de notificação", usuario.getNome(), usuario.getUsername(), "-"));
            JOptionPane.showMessageDialog(view, "Mensagem:\n"
                    + n.getMensagem()
                    + "\nRemetente: "
                    + remetente.getUsername()
                    + "\nEnviada em: "
                    + n.getDataEnvio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        } catch (RuntimeException e) {
            logger.logErro(new Log("Visualização de Notificações", usuario.getNome(), usuario.getUsername(), e.getMessage()));
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
            o.atualiza((Notificacao) obj);
        }
    }

}
