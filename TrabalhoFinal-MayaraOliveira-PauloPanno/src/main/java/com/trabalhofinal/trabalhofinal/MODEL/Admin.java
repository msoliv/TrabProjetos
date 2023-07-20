package com.trabalhofinal.trabalhofinal.MODEL;

import java.time.LocalDate;


public class Admin extends UsuarioAbs {

    public Admin(int id, String nome, String username, String senha, LocalDate dataCadastro) {
        super(id, nome, username, senha, dataCadastro);
    }

    public Admin(String nome, String username, String senha, LocalDate dataCadastro) {
        super(-1, nome, username, senha, dataCadastro);
    }
}
