package com.trabalhofinal.trabalhofinal.LOGGER;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

import com.opencsv.CSVWriter;
import com.trabalhofinal.trabalhofinal.MODEL.Log;

public class CSVLogger implements ILogger {

    private File logFile;
    private final String logFilePath = "logs/log.csv";

    public CSVLogger() {
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
                CSVWriter writer = new CSVWriter(
                        new FileWriter(logFile),
                        ';',
                        CSVWriter.NO_QUOTE_CHARACTER,
                        CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                        CSVWriter.DEFAULT_LINE_END);

                String[] header = {"OPERACAO", "NOME", "DATA", "HORA", "USUARIO", "FALHA"};
                writer.writeNext(header);
                writer.close();

            } catch (IOException e) {
                throw new RuntimeException("Erro ao criar arquivo de log! " + e.getMessage());
            }
        }
    }

    @Override
    public void logUsuario(Log log) {
        try {
            String[] data = {
                log.getOperation(),
                log.getName(),
                log.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                log.getTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")),
                log.getUsername(),
                "-"
            };

            CSVWriter writer = new CSVWriter(
                    new FileWriter(logFile, true),
                    ';',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);

            writer.writeNext(data);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao gravar log! " + e.getMessage());
        }
    }

    @Override
    public void logErro(Log log) {
        try {
            String[] data = {
                log.getOperation(),
                log.getName(),
                log.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                log.getTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")),
                log.getUsername(),
                log.getException()
            };

            CSVWriter writer = new CSVWriter(
                    new FileWriter(logFile, true),
                    ';',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);

            writer.writeNext(data);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Erro ao gravar log! " + e.getMessage());
        }
    }
}
