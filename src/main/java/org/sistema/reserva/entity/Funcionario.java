package org.sistema.reserva.entity;

public class Funcionario extends Utilizadores{
    private String nomeUsuario;
    private String endereco;
    private String cargo;
    private String nivel_de_acesso;

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getNivel_de_acesso() {
        return nivel_de_acesso;
    }

    public void setNivel_de_acesso(String nivel_de_acesso) {
        this.nivel_de_acesso = nivel_de_acesso;
    }
}