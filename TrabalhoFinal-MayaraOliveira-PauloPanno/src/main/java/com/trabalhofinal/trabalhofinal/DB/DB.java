package com.trabalhofinal.trabalhofinal.DB;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

    private static final String URL = "jdbc:sqlite:SQL/dados.sqlite";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void criaDiretorio() {
        File diretorio = new File("SQL/");

        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }

    }

}
