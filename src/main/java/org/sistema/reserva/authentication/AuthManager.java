package org.sistema.reserva.authentication;

import org.sistema.reserva.dao.ClienteDAO;
import org.sistema.reserva.dao.FuncionarioDAO;
import org.sistema.reserva.entity.Cliente;
import org.sistema.reserva.entity.Funcionario;

public class AuthManager {
    private static Cliente clienteLogado;
    private static Funcionario funcionarioLogado;

    public static Cliente getClienteLogado() {
        return clienteLogado;
    }

    public static Funcionario getFuncionarioLogado() {
        return funcionarioLogado;
    }

    public boolean authenticateCliente(String emailInput, String senhaInput) {
        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = clienteDAO.umCliente(emailInput);
        if(cliente == null){
            return false;
        }
        String email = cliente.getEmail();
        String senha = cliente.getSenha();

        if(email.equals(emailInput) && senha.equals(senhaInput)){
            clienteLogado = cliente;
            return true;
        }
        return false;
    }

    public boolean authenticateFuncionario(String nomeUsuarioInput, String senhaInput) {
        FuncionarioDAO funcionarioDAO =  new FuncionarioDAO();
        Funcionario funcionario = funcionarioDAO.umFuncionario(nomeUsuarioInput);
        if(funcionario == null){
            return false;
        }
        String nomeUsuario = funcionario.getNomeUsuario();
        String senha = funcionario.getSenha();

        if(nomeUsuario.equals(nomeUsuarioInput) && senha.equals(senhaInput)){
            funcionarioLogado = funcionario;
            return true;
        }
        return false;
    }

    public void logoutCliente(){
        clienteLogado = null;
    }

    public void logoutFunc(){
        funcionarioLogado = null;
    }
}
