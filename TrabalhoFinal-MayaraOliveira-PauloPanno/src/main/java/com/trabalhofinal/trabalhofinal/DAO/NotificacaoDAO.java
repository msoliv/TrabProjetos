package com.trabalhofinal.trabalhofinal.DAO;

import com.trabalhofinal.trabalhofinal.DB.DB;
import com.trabalhofinal.trabalhofinal.MODEL.Notificacao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class NotificacaoDAO {

    public static void criarTabelaNotificacao() {
        String query = "CREATE TABLE IF NOT EXISTS notificacao ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "destinatario INTEGER NOT NULL REFERENCES usuario (id) ON DELETE CASCADE, "
                + "remetente INTEGER NOT NULL REFERENCES usuario (id) ON DELETE CASCADE, "
                + "mensagem VARCHAR NOT NULL, "
                + "lida INTEGER DEFAULT 0, "
                + "data DATE NOT NULL)";

        try (Connection conn = DB.connect(); Statement stmt = conn.createStatement()) {

            stmt.execute(query);

        } catch (SQLException e) {
            throw new RuntimeException("Erro:" + e.getMessage());
        }
    }

    public void inserirNotificacao(Notificacao notificacao) {
        String query = "INSERT INTO notificacao(destinatario, remetente, mensagem, data) "
                + "VALUES(?, ?, ?, ?)";

        try (Connection conn = DB.connect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, notificacao.getDestinatario());
            stmt.setInt(2, notificacao.getRemetente());
            stmt.setString(3, notificacao.getMensagem());
            stmt.setDate(4, Date.valueOf(notificacao.getDataEnvio()));

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro:" + e.getMessage());
        }
    }

    public ArrayList<Notificacao> getNotificacoes(int destinatario) {
        String query = "SELECT * FROM notificacao "
                + "WHERE destinatario = ? "
                + "ORDER BY lida ASC";

        try (Connection conn = DB.connect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, destinatario);

            ArrayList<Notificacao> lista = new ArrayList<>();

            try (ResultSet res = stmt.executeQuery()) {
                while (res.next()) {
                    int id = res.getInt("id");
                    int des = res.getInt("destinatario");
                    int rem = res.getInt("remetente");
                    String msg = res.getString("mensagem");
                    boolean lida = res.getInt("lida") == 1;
                    LocalDate data = res.getDate("data").toLocalDate();

                    lista.add(new Notificacao(id, des, rem, msg, lida, data));
                }
            }

            return lista;
        } catch (SQLException e) {
            throw new RuntimeException("Erro: " + e.getMessage());
        }
    }

    public Notificacao getById(int id) {
        String query = "SELECT * FROM notificacao "
                + "WHERE id = ?";

        try (Connection conn = DB.connect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            try (ResultSet res = stmt.executeQuery()) {
                if (res.next()) {
                    return new Notificacao(
                            res.getInt("id"),
                            res.getInt("destinatario"),
                            res.getInt("remetente"),
                            res.getString("mensagem"),
                            res.getInt("lida") == 1,
                            res.getDate("data").toLocalDate()
                    );
                }
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erro:" + e.getMessage());
        }
    }

    public int contaNotificacao(int id) {
        String query = "SELECT COUNT(1) AS num FROM notificacao "
                + "WHERE destinatario = ?";

        try (Connection conn = DB.connect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            int num = 0;

            try (ResultSet res = stmt.executeQuery()) {
                if (res.next()) {
                    num = res.getInt("num");
                }
            }

            return num;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao contar notificações!" + e.getMessage());
        }
    }

    public int contaNotificacaoLida(int id) {
        String query = "SELECT COUNT(1) AS num FROM notificacao "
                + "WHERE destinatario = ? AND lida = 1";

        try (Connection conn = DB.connect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            int num = 0;

            try (ResultSet res = stmt.executeQuery()) {
                if (res.next()) {
                    num = res.getInt("num");
                }
            }

            return num;
        } catch (SQLException e) {
            throw new RuntimeException("Erro:" + e.getMessage());
        }
    }

    public int contaNotificacaoNaoLida(int id) {
        String query = "SELECT COUNT(1) AS num FROM notificacao "
                + "WHERE destinatario = ? AND lida = 0";

        try (Connection conn = DB.connect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            int num = 0;

            try (ResultSet res = stmt.executeQuery()) {
                if (res.next()) {
                    num = res.getInt("num");
                }
            }

            return num;
        } catch (SQLException e) {
            throw new RuntimeException("Erro:" + e.getMessage());
        }
    }

    public void marcarComoLida(int id) {
        String query = "UPDATE notificacao "
                + "SET lida = 1 "
                + "WHERE id = ?";

        try (Connection conn = DB.connect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro:" + e.getMessage());
        }
    }

}
