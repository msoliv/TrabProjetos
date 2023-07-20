package com.trabalhofinal.trabalhofinal.PRESENTER;

import com.trabalhofinal.trabalhofinal.LOGGER.CSVLogger;
import com.trabalhofinal.trabalhofinal.LOGGER.ILogger;
import com.trabalhofinal.trabalhofinal.LOGGER.JSONLogger;
import com.trabalhofinal.trabalhofinal.LOGGER.XMLLogger;
import com.trabalhofinal.trabalhofinal.MODEL.INTERFACE.IObservable;
import com.trabalhofinal.trabalhofinal.MODEL.INTERFACE.IObserver;
import com.trabalhofinal.trabalhofinal.VIEW.TipoLog;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JDesktopPane;

public class ConfiguracoesPresenter implements IObservable {

    private final TipoLog view;
    private final ArrayList<IObserver> observers;

    public ConfiguracoesPresenter(JDesktopPane desktop, ILogger logger) {
        view = new TipoLog();
        observers = new ArrayList<>();

        if (CSVLogger.class.isInstance(logger)) {
            view.getRbtCSV().setSelected(true);
        } else if (JSONLogger.class.isInstance(logger)) {
            view.getRbtJSON().setSelected(true);
        } else {
            view.getRbtXML().setSelected(true);
        }

        view.getBtnFechar().addActionListener((ActionEvent ae) -> {
            view.dispose();
        });

        view.getBtnSalvar().addActionListener((ActionEvent ae) -> {
            aplicar();
            view.dispose();
        });

        desktop.add(view);
        view.setVisible(true);
    }

    private void aplicar() {
        if (view.getRbtCSV().isSelected()) {
            notificaObserver(new CSVLogger());
        } else if (view.getRbtJSON().isSelected()) {
            notificaObserver(new JSONLogger());
        } else {
            notificaObserver(new XMLLogger());
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
            if (CSVLogger.class.isInstance(obj)) {
                o.atualiza((CSVLogger) obj);
            } else if (JSONLogger.class.isInstance(obj)) {
                o.atualiza((JSONLogger) obj);
            } else if (XMLLogger.class.isInstance(obj)) {
                o.atualiza((XMLLogger) obj);
            }
        }
    }

}
