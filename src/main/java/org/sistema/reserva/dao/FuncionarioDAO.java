package org.sistema.reserva.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.sistema.reserva.authentication.AuthManager;
import org.sistema.reserva.connection.Connect;
import org.sistema.reserva.entity.Cliente;
import org.sistema.reserva.entity.Funcionario;
import org.sistema.reserva.entity.Quarto;
import org.sistema.reserva.service.FuncionarioService;
import org.sistema.reserva.validations.MessageFx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {
    public void cadastrarFuncionario(Funcionario funcionario){
        MessageFx messageFx = new MessageFx();
        String sql = "INSERT INTO FUNCIONARIO (nome, sobrenome,nome_usuario, email, senha, telefone, endereco, cargo, nivel_acesso) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = null;

        try{
            ps = Connect.getConnection().prepareStatement(sql);
            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getSobreNome());
            ps.setString(3, funcionario.getNomeUsuario());
            ps.setString(4, funcionario.getEmail());
            ps.setString(5, funcionario.getSenha());
            ps.setString(6, funcionario.getTelefone());
            ps.setString(7, funcionario.getEndereco());
            ps.setString(8, funcionario.getCargo());
            ps.setString(9, funcionario.getNivel_de_acesso());
            ps.execute();
            ps.close();

            System.out.println("\nFUNCIONARIO ADICIONADO COM SUCESSO\n");
            messageFx.showSucess("FUNCIONARIO ADICIONADO COM SUCESSO");
        }catch(SQLException e){
            messageFx.showError("Erro ao adicionar usuario");
            System.out.println("Erro ao adicionar usuario");
            System.out.println(e.getMessage().toString());
        }
    }

    public ObservableList<Funcionario> listarFuncionario(){
        Connection con = Connect.getConnection();
        ObservableList<Funcionario> lista = FXCollections.observableArrayList();
        String sql = "SELECT * FROM FUNCIONARIO";
        try(PreparedStatement smt = con.prepareStatement(sql)){
            ResultSet result = smt.executeQuery();
            while (result.next()){
                Funcionario funcionario = new Funcionario();
                funcionario.setId(result.getInt("id"));
                funcionario.setNome(result.getString("nome"));
                funcionario.setSobreNome(result.getString("sobrenome"));
                funcionario.setNomeUsuario(result.getString("nome_usuario"));
                funcionario.setEmail(result.getString("email"));
                funcionario.setSenha(result.getString("senha"));
                funcionario.setTelefone(result.getString("telefone"));
                funcionario.setCargo(result.getString("cargo"));
                funcionario.setNivel_de_acesso(result.getString("nivel_acesso"));
                funcionario.setEndereco(result.getString("endereco"));

                lista.add(funcionario);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage().toString());
        }
        return lista;
    }

    public Funcionario umFuncionario(String email){
        Connection con = Connect.getConnection();
        Funcionario funcionario = null;
        String sql = "SELECT * FROM FUNCIONARIO WHERE nome_usuario = ?";
        try(PreparedStatement smt = con.prepareStatement(sql)){
            smt.setString(1, email);
            ResultSet result = smt.executeQuery();
            if(result.next()){
                funcionario = new Funcionario();
                funcionario.setId(result.getInt("id"));
                funcionario.setNome(result.getString("nome"));
                funcionario.setSobreNome(result.getString("sobrenome"));
                funcionario.setNomeUsuario(result.getString("nome_usuario"));
                funcionario.setEmail(result.getString("email"));
                funcionario.setSenha(result.getString("senha"));
                funcionario.setTelefone(result.getString("telefone"));
                funcionario.setCargo(result.getString("cargo"));
                funcionario.setNivel_de_acesso(result.getString("nivel_acesso"));
            }else{
                return funcionario;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage().toString());
        }
        return funcionario;
    }

    public void actualizarFuncionario(Funcionario funcionario,int id) {
        MessageFx messageFx = new MessageFx();
        Connection con = Connect.getConnection();
        StringBuilder sql = new StringBuilder("UPDATE FUNCIONARIO SET ");
        List<Object> params = new ArrayList<>();

        if (funcionario.getNome() != null && !funcionario.getNome().isEmpty()) {
            sql.append("nome = ?, ");
            params.add(funcionario.getNome());
        }
        if (funcionario.getSobreNome() != null && !funcionario.getSobreNome().isEmpty()) {
            sql.append("sobrenome = ?, ");
            params.add(funcionario.getSobreNome());
        }
        if (funcionario.getNomeUsuario() != null && !funcionario.getNomeUsuario().isEmpty()) {
            sql.append("nome_usuario = ?, ");
            params.add(funcionario.getNomeUsuario());
        }
        if (funcionario.getEmail() != null && !funcionario.getEmail().isEmpty()) {
            sql.append("email = ?, ");
            params.add(funcionario.getEmail());
        }
        if (funcionario.getSenha() != null && !funcionario.getSenha().isEmpty()) {
            sql.append("senha = ?, ");
            params.add(funcionario.getSenha());
        }
        if (funcionario.getTelefone() != null && !funcionario.getTelefone().isEmpty()) {
            sql.append("telefone = ?, ");
            params.add(funcionario.getTelefone());
        }
        if (funcionario.getCargo() != null && !funcionario.getCargo().isEmpty()) {
            sql.append("cargo = ?, ");
            params.add(funcionario.getCargo());
        }
        if (funcionario.getNivel_de_acesso() != null && !funcionario.getNivel_de_acesso().isEmpty()) {
            sql.append("nivel_acesso = ?, ");
            params.add(funcionario.getNivel_de_acesso());
        }

        // Remove a última vírgula e espaço
        if (params.size() > 0) {
            sql.setLength(sql.length() - 2);
            sql.append(" WHERE id = ?");
            params.add(id);
            System.out.println(funcionario.getNomeUsuario());
            try (PreparedStatement smt = con.prepareStatement(sql.toString())) {
                for (int i = 0; i < params.size(); i++) {
                    smt.setObject(i + 1, params.get(i));
                }

                smt.executeUpdate();
                smt.close();
                System.out.println("\nDADOS DO FUNCIONARIO ATUALIZADO\n");
                messageFx.showSucess("DADOS DO FUNCIONARIO ATUALIZADO");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                messageFx.showError("Erro ao atualizar funcionario");
            }
        } else {
            System.out.println("\nNenhum dado para atualizar.\n");
        }
    }


    public void actualizarFuncionario1(Funcionario funcionario){
        Connection con = Connect.getConnection();
        String sql = "UPDATE FUNCIONARIO SET nome = ?, sobrenome = ?, nome_usuario = ?, email = ?, senha = ?, telefone = ?, cargo = ?, nivel_acesso = ? WHERE nome_usuario = ?";

        try(PreparedStatement smt = con.prepareStatement(sql)){
            smt.setString(1, funcionario.getNome());
            smt.setString(2, funcionario.getSobreNome());
            smt.setString(3, funcionario.getNomeUsuario());
            smt.setString(4, funcionario.getEmail());
            smt.setString(5, funcionario.getSenha());
            smt.setString(6, funcionario.getTelefone());
            smt.setString(7, funcionario.getCargo());
            smt.setString(8, funcionario.getNivel_de_acesso());
            smt.setString(9, funcionario.getNomeUsuario());

            smt.executeUpdate();
            smt.close();
            System.out.println("\nDADOS DO FUNCIONARIO ATUALIZDO\n");
        }catch (SQLException e){
            System.out.println(e.getMessage().toString());
        }
    }

    public void removerFuncionario(int id){
        MessageFx messageFx = new MessageFx();
        Connection con = Connect.getConnection();
        String sql = "DELETE FROM FUNCIONARIO WHERE id = ? ";

        try(PreparedStatement smt = con.prepareStatement(sql)){
            smt.setInt(1, id);
            smt.executeUpdate();
            smt.close();
            System.out.println("\nFUNCIONARIO REMOVIDO COM SUCESSO\n");
            messageFx.showSucess("FUNCIONARIO REMOVIDO COM SUCESSO");
        }catch (SQLException e){
            messageFx.showError("Erro ao remover funcionario");
            System.out.println(e.getMessage().toString());;
        }
    }
}