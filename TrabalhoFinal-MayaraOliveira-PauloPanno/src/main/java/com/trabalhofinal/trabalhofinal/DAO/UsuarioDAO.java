package com.trabalhofinal.trabalhofinal.DAO;

import com.trabalhofinal.trabalhofinal.DB.DB;
import com.trabalhofinal.trabalhofinal.MODEL.Admin;
import com.trabalhofinal.trabalhofinal.MODEL.Usuario;
import com.trabalhofinal.trabalhofinal.MODEL.UsuarioAbs;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;


public class UsuarioDAO {

    public static void criarTabelaUsuario() {
        String query = "CREATE TABLE IF NOT EXISTS usuario ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nome VARCHAR NOT NULL,"
                + "username VARCHAR NOT NULL UNIQUE,"
                + "senha VARCHAR NOT NULL,"
                + "cadastro DATE NOT NULL,"
                + "admin INTEGER DEFAULT 0,"
                + "autorizado INTEGER DEFAULT 0)";

        try (Connection conn = DB.connect(); Statement stmt = conn.createStatement()) {

            stmt.execute(query);

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar tabela usuario! " + e.getMessage());
        }
    }

    public static int contaUsuarios() {
        String query = "SELECT COUNT(1) AS num FROM usuario";

        try (Connection conn = DB.connect(); Statement stmt = conn.createStatement()) {

            int num = 0;

            try (ResultSet res = stmt.executeQuery(query)) {
                if (res.next()) {
                    num = res.getInt("num");
                }
            }

            return num;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao acessar database! " + e.getMessage());
        }
    }

    public boolean verificaUsername(String username) {
        String query = "SELECT COUNT(1) AS num FROM usuario WHERE username = ?";

        try (Connection conn = DB.connect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);

            int num = 0;

            try (ResultSet res = stmt.executeQuery()) {
                if (res.next()) {
                    num = res.getInt("num");
                }
            }

            return num != 0;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar nome de usuário! " + e.getMessage());
        }
    }

    public void atualizar(UsuarioAbs usuario) {
        String query = "UPDATE usuario "
                + "SET nome = ?,"
                + "username = ?,"
                + "senha = ? "
                + "WHERE id = ?";

        try (Connection conn = DB.connect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getUsername());
            stmt.setString(3, usuario.getSenha());
            stmt.setInt(4, usuario.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar usuário! " + e.getMessage());
        }
    }

    public void deletar(int id) {
        String query = "DELETE FROM usuario WHERE id = ?";

        try (Connection conn = DB.connect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar! " + e.getMessage());
        }
    }

    public void inserir(UsuarioAbs novo) {
        String query = "INSERT INTO usuario (nome, username, senha, cadastro, admin, autorizado) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DB.connect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            int aux = 0;
            if (Admin.class.isInstance(novo)) {
                aux = 1;
            }

            stmt.setString(1, novo.getNome());
            stmt.setString(2, novo.getUsername());
            stmt.setString(3, novo.getSenha());
            stmt.setDate(4, Date.valueOf(novo.getDataCadastro()));
            stmt.setInt(5, aux);
            stmt.setInt(6, aux);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar usuário! " + e.getMessage());
        }
    }

    public UsuarioAbs login(String username, String senha) {
        String query = "SELECT * FROM usuario WHERE LOWER(username) = LOWER(?) AND senha = ?";

        try (Connection conn = DB.connect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, senha);

            try (ResultSet res = stmt.executeQuery()) {
                if (res.next()) {
                    int id = res.getInt("id");
                    String nome = res.getString("nome");
                    username = res.getString("username");
                    senha = res.getString("senha");
                    LocalDate data = res.getDate("cadastro").toLocalDate();
                    boolean admin = res.getInt("admin") == 1;
                    boolean autorizado = res.getInt("autorizado") == 1;

                    if (admin) {
                        return new Admin(id, nome, username, senha, data);
                    } else {
                        return new Usuario(id, nome, username, senha, data, autorizado);
                    }
                }
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao fazer login! " + e.getMessage());
        }
    }

    public static boolean isAutorizado(int id) {
        String query = "SELECT autorizado AS aut FROM usuario WHERE id = ?";

        try (Connection conn = DB.connect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            int autorizacao = 0;

            try (ResultSet res = stmt.executeQuery()) {
                if (res.next()) {
                    autorizacao = res.getInt("aut");
                }
            }

            return autorizacao == 1;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar autorização! " + e.getMessage());
        }
    }

    public UsuarioAbs findById(int id) {
        String query = "SELECT * FROM usuario WHERE id = ?";

        try (Connection conn = DB.connect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            try (ResultSet res = stmt.executeQuery()) {
                if (res.next()) {
                    int id1 = res.getInt("id");
                    String nome = res.getString("nome");
                    String username = res.getString("username");
                    String senha = res.getString("senha");
                    LocalDate data = res.getDate("cadastro").toLocalDate();
                    boolean admin = res.getInt("admin") == 1;
                    boolean autorizado = res.getInt("autorizado") == 1;

                    if (admin) {
                        return new Admin(id1, nome, username, senha, data);
                    } else {
                        return new Usuario(id1, nome, username, senha, data, autorizado);
                    }
                }
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao recuperar usuários! " + e.getMessage());
        }
    }

    public static boolean isAutorizado(String username) {
        String query = "SELECT autorizado AS aut FROM usuario WHERE LOWER(username) = LOWER(?)";

        try (Connection conn = DB.connect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);

            int autorizacao = 0;

            try (ResultSet res = stmt.executeQuery()) {
                if (res.next()) {
                    autorizacao = res.getInt("aut");
                }
            }

            return autorizacao == 1;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar autorização! " + e.getMessage());
        }
    }

    public void autorizar(int id) {
        String query = "UPDATE usuario SET autorizado = 1 WHERE id = ?";

        try (Connection conn = DB.connect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao autorizar!" + e.getMessage());
        }
    }

    public void autorizar(String username) {
        String query = "UPDATE usuario SET autorizado = 1 WHERE LOWER(username) = LOWER(?)";

        try (Connection conn = DB.connect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao autorizar!" + e.getMessage());
        }
    }

    public ArrayList<UsuarioAbs> listarUsuarios() {
        String query = "SELECT * FROM usuario";

        try (Connection conn = DB.connect(); Statement stmt = conn.createStatement(); ResultSet res = stmt.executeQuery(query)) {

            ArrayList<UsuarioAbs> lista = new ArrayList<>();

            while (res.next()) {
                UsuarioAbs usuario;

                int id = res.getInt("id");
                String nome = res.getString("nome");
                String username = res.getString("username");
                String senha = res.getString("senha");
                LocalDate data = res.getDate("cadastro").toLocalDate();
                boolean admin = res.getInt("admin") == 1;
                boolean autorizado = res.getInt("autorizado") == 1;

                if (admin) {
                    usuario = new Admin(id, nome, username, senha, data);
                } else {
                    usuario = new Usuario(id, nome, username, senha, data, autorizado);
                }

                lista.add(usuario);

            }

            if (lista.isEmpty()) {
                return null;
            }

            return lista;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao recuperar usuários." + e.getMessage());
        }
    }

    public ArrayList<UsuarioAbs> procura(String texto) {
        String query = "SELECT * FROM usuario "
                + "WHERE CAST(id AS VARCHAR) LIKE ? OR "
                + "nome LIKE ? OR "
                + "username LIKE ? "
                + "ORDER BY nome";

        try (Connection conn = DB.connect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, "%" + texto + "%");
            stmt.setString(2, "%" + texto + "%");
            stmt.setString(3, "%" + texto + "%");

            try (ResultSet res = stmt.executeQuery()) {
                ArrayList<UsuarioAbs> lista = new ArrayList<>();

                while (res.next()) {
                    int id = res.getInt("id");
                    String nome = res.getString("nome");
                    String username = res.getString("username");
                    String senha = res.getString("senha");
                    LocalDate data = res.getDate("cadastro").toLocalDate();
                    boolean admin = res.getInt("admin") == 1;
                    boolean autorizado = res.getInt("autorizado") == 1;

                    if (admin) {
                        lista.add(new Admin(id, nome, username, senha, data));
                    } else {
                        lista.add(new Usuario(id, nome, username, senha, data, autorizado));
                    }
                }

                return lista;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuários!" + e.getMessage());
        }
    }

    public static String getUsernameById(int id) {
        String query = "SELECT username FROM usuario WHERE id = ?";

        try (Connection conn = DB.connect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            try (ResultSet res = stmt.executeQuery()) {
                if (res.next()) {
                    return res.getString("username");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuário!" + e.getMessage());
        }

        return null;
    }

    public static int getIdByUsername(String username) {
        String query = "SELECT id FROM usuario WHERE LOWER(username) = LOWER(?)";

        try (Connection conn = DB.connect(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);

            try (ResultSet res = stmt.executeQuery()) {
                if (res.next()) {
                    return res.getInt("id");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuário!" + e.getMessage());
        }

        return 0;
    }

}
