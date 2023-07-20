package com.trabalhofinal.trabalhofinal.LOGGER;

import com.trabalhofinal.trabalhofinal.MODEL.Log;


public interface ILogger {

    public void criaArquivo();

    public void logUsuario(Log log);

    public void logErro(Log log);
}
