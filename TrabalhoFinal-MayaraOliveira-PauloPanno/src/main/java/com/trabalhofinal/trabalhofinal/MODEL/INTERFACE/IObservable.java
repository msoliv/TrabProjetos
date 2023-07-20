package com.trabalhofinal.trabalhofinal.MODEL.INTERFACE;


public interface IObservable {

    public void adicionaObserver(IObserver observer);

    public void removeObserver(IObserver observer);

    public void notificaObserver(Object obj);
}
