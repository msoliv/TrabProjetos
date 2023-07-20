package com.trabalhofinal.trabalhofinal.LOGGER;

import com.trabalhofinal.trabalhofinal.MODEL.Log;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import org.json.simple.JSONObject;

public class JSONLogger implements ILogger {

    private File logFile;
    private final String logFilePath = "logs/log.json";

    public JSONLogger() {
        criaArquivo();
    }

    @Override
    public void criaArquivo() {
        File logDirectory = new File("logs/");

        if (!logDirectory.exists()) {
            logDirectory.mkdirs();
        }

        logFile = new File(logFilePath);
        try {
            logFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao criar arquivo de log! " + e.getMessage());
        }
    }

    @Override
    public void logUsuario(Log log) {
        HashMap<String, Object> logMap = new HashMap<>();

        logMap.put("OPERACAO", log.getOperation());
        logMap.put("NOME", log.getName());
        logMap.put("DATA", log.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        logMap.put("HORA", log.getTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logMap.put("USUARIO", log.getUsername());

        JSONObject jsonObject = new JSONObject(logMap);

        try {
            FileWriter writer = new FileWriter(logFile, true);

            writer.write(jsonObject.toJSONString() + ",\n");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao gravar log! " + e.getMessage());
        }
    }

    @Override
    public void logErro(Log log) {
        HashMap<String, Object> logMap = new HashMap<>();

        logMap.put("EXCECAO", log.getException());
        logMap.put("OPERACAO", log.getOperation());
        logMap.put("NOME", log.getName());
        logMap.put("DATA", log.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        logMap.put("HORA", log.getTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        logMap.put("USUARIO", log.getUsername());

        JSONObject jsonObject = new JSONObject(logMap);

        try {
            FileWriter writer = new FileWriter(logFile, true);

            writer.write(jsonObject.toJSONString() + "\n");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao gravar log: " + e.getMessage());
        }
    }

}
