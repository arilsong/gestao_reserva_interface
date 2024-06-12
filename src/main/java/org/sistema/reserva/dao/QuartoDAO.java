package org.sistema.reserva.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.sistema.reserva.connection.Connect;
import org.sistema.reserva.entity.Quarto;
import org.sistema.reserva.validations.MessageFx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuartoDAO {
    public void adcionarQuarto(Quarto quarto){
        String sql = "INSERT INTO acomodacao (num_quarto, nome, tipo, qtd_leitos, preco, tamanho, descricao) VALUES (?, ?, ?, ?, ?, ?, ?)";
        MessageFx messageFx = new MessageFx();
        PreparedStatement ps = null;

        try{
            ps = Connect.getConnection().prepareStatement(sql);
            ps.setInt(1, quarto.getNum_quarto());
            ps.setString(2, quarto.getNome());
            ps.setString(3, quarto.getTipo());
            ps.setInt(4, quarto.getQtd_leitos());
            ps.setDouble(5, quarto.getPreco());
            ps.setString(6, quarto.getTamanho());
            ps.setString(7, quarto.getDescricao());


            ps.execute();
            ps.close();

            System.out.println("\nQuarto Adicionado\n");
            messageFx.showSucess("Quarto adicionado");

        }catch(SQLException e){
            if (e.getMessage().contains("PRIMARY")) {
                messageFx.showError("O quarto já existente");
            }else{
                System.out.println("Erro ao adicionar quarto, Erro: " + e.getMessage().toString());
            }
        }
    }

    public ObservableList<Quarto> listarQuarto(){
        Connection con = Connect.getConnection();
        ObservableList<Quarto> lista = FXCollections.observableArrayList();
//        List<Quarto> lista = new ArrayList<>();
        String sql = "SELECT * FROM acomodacao";
        try(PreparedStatement smt = con.prepareStatement(sql)){
            ResultSet result = smt.executeQuery();
            while (result.next()){
                Quarto quarto = new Quarto();
                quarto.setNum_quarto(result.getInt("num_quarto"));
                quarto.setNome(result.getString("nome"));
                quarto.setTipo(result.getString("tipo"));
                quarto.setQtd_leitos(result.getInt("qtd_leitos"));
                quarto.setPreco(result.getDouble("preco"));
                quarto.setTamanho(result.getString("tamanho"));
                quarto.setEstado(result.getBoolean("estado"));
                quarto.setDescricao(result.getString("descricao"));

                lista.add(quarto);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage().toString());
        }
        return lista;
    }

    public Quarto umQuarto(int numeroQuarto){
        Connection con = Connect.getConnection();
        Quarto quarto = null;
        String sql = "SELECT * FROM acomodacao WHERE num_quarto = ?";
        try(PreparedStatement smt = con.prepareStatement(sql)){
            smt.setInt(1, numeroQuarto);
            ResultSet result = smt.executeQuery();
            if(result.next()){
                quarto = new Quarto();
                quarto.setNum_quarto(result.getInt("num_quarto"));
                quarto.setNome(result.getString("nome"));
                quarto.setTipo(result.getString("tipo"));
                quarto.setQtd_leitos(result.getInt("qtd_leitos"));
                quarto.setPreco(result.getDouble("preco"));
                quarto.setTamanho(result.getString("tamanho"));
                quarto.setEstado(result.getBoolean("estado"));
                quarto.setDescricao(result.getString("descricao"));
            }else{
                return quarto;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage().toString());
        }
        return quarto;
    }

    public void actualizarQuarto(Quarto quarto, int num_quarto){
        MessageFx messageFx = new MessageFx();
        Connection con = Connect.getConnection();
        String sql = "UPDATE acomodacao SET num_quarto = ?, nome = ?, tipo = ?, qtd_leitos = ?, preco = ?, tamanho = ?, descricao = ? WHERE num_quarto = ?";

        try(PreparedStatement smt = con.prepareStatement(sql)){
            smt.setInt(1, quarto.getNum_quarto());
            smt.setString(2, quarto.getNome());
            smt.setString(3, quarto.getTipo());
            smt.setInt(4, quarto.getQtd_leitos());
            smt.setDouble(5, quarto.getPreco());
            smt.setString(6, quarto.getTamanho());
            smt.setString(7, quarto.getDescricao());
            smt.setInt(8,  quarto.getNum_quarto());


            smt.executeUpdate();
            smt.close();
            messageFx.showSucess("Acomodacao atualizado");
            System.out.println("\nDADOS DO QUARTO ATUALIZDO\n");
        }catch (SQLException e){
            System.out.println(e.getMessage().toString());
            messageFx.showError("Erro ao atualizar acomodacao");
        }
    }

    public void actualizarEstadoQuarto(boolean estado, int num_quarto){
        Connection con = Connect.getConnection();
        String sql = "UPDATE acomodacao SET estado = ? WHERE num_quarto = ?";

        try(PreparedStatement smt = con.prepareStatement(sql)){
            smt.setBoolean(1, estado);
            smt.setDouble(2, num_quarto);

            smt.executeUpdate();
            smt.close();
            System.out.println("\nDADOS DO QUARTO ATUALIZDO\n");
        }catch (SQLException e){
            System.out.println(e.getMessage().toString());
        }

    }

    public void removerQuarto(int num_quarto){
        MessageFx messageFx = new MessageFx();
        Connection con = Connect.getConnection();
        String sql = "DELETE FROM acomodacao WHERE num_quarto = ? ";

        try(PreparedStatement smt = con.prepareStatement(sql)){
            smt.setInt(1, num_quarto);
            smt.executeUpdate();
            smt.close();
            messageFx.showSucess("Acomodacao removido");
            System.out.println("\nQUARTO REMOVIDO COM SUCESSO\n");
        }catch (SQLException e){
            System.out.println(e.getMessage().toString());
            messageFx.showError("Erro ao remover acomodacao");
        }
    }

}
