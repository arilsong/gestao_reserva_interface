package org.sistema.reserva.service;

import javafx.collections.ObservableList;
import org.sistema.reserva.authentication.AuthManager;
import org.sistema.reserva.dao.FuncionarioDAO;
import org.sistema.reserva.entity.Funcionario;
import org.sistema.reserva.entity.Quarto;

import java.util.List;

public class FuncionarioService {
    FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    public void cadastrarFuncionario(String nome,
                                     String sobrenome,
                                     String nomeUsuario,
                                     String email,
                                     String senha,
                                     String telefone,
                                     String endereco,
                                     String cargo,
                                     String nivel_de_acesso){
        Funcionario funcionario = new Funcionario();

        funcionario.setNome(nome);
        funcionario.setSobreNome(sobrenome);
        funcionario.setNomeUsuario(nomeUsuario);
        funcionario.setEmail(email);
        funcionario.setSenha(senha);
        funcionario.setTelefone(telefone);
        funcionario.setEndereco(endereco);
        funcionario.setCargo(cargo);
        funcionario.setNivel_de_acesso(nivel_de_acesso);

        funcionarioDAO.cadastrarFuncionario(funcionario);
    }

    public ObservableList<Funcionario> listarFuncionarios(){
        ObservableList<Funcionario> funcionarios = funcionarioDAO.listarFuncionario();
        return funcionarios;
    }

    public void atualizarFuncionario(int id,
                                     String nome,
                                     String sobrenome,
                                     String nomeUsuario,
                                     String email,
                                     String senha,
                                     String telefone,
                                     String endereco,
                                     String cargo,
                                     String nivel_de_acesso){
        Funcionario funcionario = new Funcionario();

        funcionario.setNome(nome);
        funcionario.setSobreNome(sobrenome);
        funcionario.setNomeUsuario(nomeUsuario);
        funcionario.setEmail(email);
        funcionario.setSenha(senha);
        funcionario.setTelefone(telefone);
        funcionario.setEndereco(endereco);
        funcionario.setCargo(cargo);
        funcionario.setNivel_de_acesso(nivel_de_acesso);


        funcionarioDAO.actualizarFuncionario(funcionario, id);
    }

    public void removerFuncionario(int id){
        funcionarioDAO.removerFuncionario(id);
    }
}