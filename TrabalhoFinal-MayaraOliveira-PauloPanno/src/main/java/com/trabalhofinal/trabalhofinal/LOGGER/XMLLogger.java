package com.trabalhofinal.trabalhofinal.LOGGER;

import com.thoughtworks.xstream.XStream;
import com.trabalhofinal.trabalhofinal.MODEL.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class XMLLogger implements ILogger {

    private File logFile;
    private final String logFilePath = "logs/log.xml";

    public XMLLogger() {
        criaArquivo();
    }

    @Override
    public void criaArquivo() {
        File logDirectory = new File("logs/");

        if (!logDirectory.exists()) {
            logDirectory.mkdirs();
        }

        logFile = new File(logFilePath);

        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Erro ao criar arquivo de Log!" + e.getMessage());
            }
        }
    }

    @Override
    public void logUsuario(Log log) {
        try {
            XStream stream = new XStream();
            stream.alias("UsuarioCRUD", Log.class);

            FileWriter writer = new FileWriter(logFile, true);
            writer.write(stream.toXML(log) + "\n");
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException("Erro ao gravar log! " + e.getMessage());
        }
    }

    @Override
    public void logErro(Log log) {
        try {
            XStream stream = new XStream();
            stream.alias("Falha", Log.class);

            FileWriter writer = new FileWriter(logFile, true);
            writer.write(stream.toXML(log) + "\n");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao gravar log! " + e.getMessage());
        }
    }

}
