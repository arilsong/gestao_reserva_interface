package org.sistema.reserva.dao;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.sistema.reserva.authentication.AuthManager;
import org.sistema.reserva.connection.Connect;
import org.sistema.reserva.entity.Cliente;
import org.sistema.reserva.entity.Quarto;
import org.sistema.reserva.entity.Reserva;
import org.sistema.reserva.validations.MessageFx;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {
    public void adicionarReserva(Reserva reserva, int num_quarto){
        MessageFx messageFx = new MessageFx();
        String sql = "INSERT INTO reserva (data_checkin, data_checkout, valor_total, cliente, quarto)  VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = null;

        try{
            ps = Connect.getConnection().prepareStatement(sql);
            ps.setDate(1,  new java.sql.Date(reserva.getDataCheckin().getTime()));
            ps.setDate(2,  new java.sql.Date(reserva.getDataCheckout().getTime()));
            ps.setDouble(3, reserva.getValorTotal());
            ps.setInt(4, AuthManager.getClienteLogado().getId());
            ps.setInt(5, num_quarto);
            ps.execute();
            ps.close();

            System.out.println("\nReserva feito com sucesso\n");
            messageFx.showSucess("Reserva feito com sucesso");
        }catch(SQLException e){
            System.out.println("Erro ao fazer reserva: " + e.getMessage().toString());
        }
    }

    public ObservableList<Reserva> lisarReservaCliente(){
        Connection con = Connect.getConnection();
        ObservableList<Reserva> listaReserva = FXCollections.observableArrayList();

        String sql = "SELECT reserva.id as id_reserva, reserva.*, acomodacao.* FROM reserva JOIN cliente ON cliente.id = reserva.cliente JOIN acomodacao ON acomodacao.num_quarto  = reserva.quarto WHERE cliente.id = ?";
        try(PreparedStatement smt = con.prepareStatement(sql)){
            smt.setInt(1, AuthManager.getClienteLogado().getId());
            ResultSet result = smt.executeQuery();
            while (result.next()){
                Reserva reserva = new Reserva();
                Quarto quarto = new Quarto();
                reserva.setId(result.getInt("id"));
                reserva.setDataCheckin(result.getDate("data_checkin"));
                reserva.setDataCheckout(result.getDate("data_checkout"));
                reserva.setEstado(result.getBoolean("estado"));
                reserva.setValorTotal(result.getDouble("valor_total"));

                quarto.setNum_quarto(result.getInt("num_quarto"));
                quarto.setTipo(result.getString("tipo"));
                quarto.setQtd_leitos(result.getInt("qtd_leitos"));
                quarto.setPreco(result.getDouble("preco"));
                quarto.setTamanho(result.getString("tamanho"));


                reserva.setQuartos(quarto);

                listaReserva.add(reserva);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage().toString());
        }
        return listaReserva;
    }

    public ObservableList<Reserva> listaReservas(){
        Connection con = Connect.getConnection();
        ObservableList<Reserva> lista = FXCollections.observableArrayList();
        String sql = "SELECT reserva.*, cliente.*, acomodacao.* FROM reserva JOIN cliente ON cliente.id = reserva.cliente join acomodacao on acomodacao.num_quarto  = reserva.quarto";

        try(PreparedStatement smt = con.prepareStatement(sql)){
            ResultSet result = smt.executeQuery();
            while (result.next()){
                Reserva reserva = new Reserva();
                Quarto quarto = new Quarto();
                Cliente cliente = new Cliente();

                reserva.setId(result.getInt("id"));
                reserva.setDataCheckin(result.getDate("data_checkin"));
                reserva.setDataCheckout(result.getDate("data_checkout"));
                reserva.setValorTotal(result.getDouble("valor_total"));

                quarto.setNum_quarto(result.getInt("num_quarto"));
                quarto.setTipo(result.getString("tipo"));

                cliente.setNome(result.getString("nome"));
                cliente.setSobreNome(result.getString("sobrenome"));
                cliente.setEmail(result.getString("email"));
                cliente.setTelefone(result.getString("telefone"));

                reserva.setQuarto(quarto);
                reserva.setCliente(cliente);

                lista.add(reserva);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage().toString());
        }
        return lista;
    }

    public Reserva umReserva(int id){
        Connection con = Connect.getConnection();
        Reserva reserva = null;
        String sql = "SELECT * FROM reservas WHERE id = ?";
        try(PreparedStatement smt = con.prepareStatement(sql)){
            smt.setInt(1, id);
            ResultSet result = smt.executeQuery();
            if(result.next()){
                reserva = new Reserva();
                reserva.setId(result.getInt("id"));
                reserva.setDataCheckin(result.getDate("dataCheckin"));
                reserva.setDataCheckout(result.getDate("dataCheckout"));
                reserva.setEstado(result.getBoolean("estado"));
                reserva.setValorTotal(result.getDouble("valorTotal"));
            }else{
                return reserva;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage().toString());
        }
        return reserva;
    }

    public void atualizarReserva(Reserva reserva,int idReserva){
        Connection con = Connect.getConnection();
        StringBuilder sql = new StringBuilder("UPDATE RESERVAS SET ");
        List<Object> params = new ArrayList<>();

        if(reserva.getDataCheckin() != null) {
            sql.append("dataCheckin = ?, ");
            params.add(reserva.getDataCheckin());
        }

        if (reserva.getDataCheckout() != null) {
            sql.append("dataCheckout = ?, ");
            params.add(reserva.getDataCheckout());
        }

        if (reserva.getValorTotal() != 0) {
            sql.append("valorTotal = ?, ");
            params.add(reserva.getValorTotal());
        }

        if (!params.isEmpty()) {
            sql.setLength(sql.length() - 2);
//            sql.append(" valorTotal = ?");
//            params.add(reserva.getValorTotal());
            sql.append(" WHERE id = ?");
            params.add(idReserva);

            try (PreparedStatement smt = con.prepareStatement(sql.toString())) {
                for (int i = 0; i < params.size(); i++) {
                    smt.setObject(i + 1, params.get(i));
                }

                smt.executeUpdate();
                System.out.println("\nDADOS DA RESERVA ATUALIZADO\n");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("\nNenhum dado para atualizar.\n");
        }
    }

    public void atualizarReserva1(Reserva reserva, int idReserva){
        String sql = "UPDATE RESERVAS SET dataCheckin = ?, dataCheckout = ? WHERE id = ?";

        PreparedStatement ps = null;

        try{
            ps = Connect.getConnection().prepareStatement(sql);
            ps.setDate(1, (Date) reserva.getDataCheckin());
            ps.setDate(2,(Date) reserva.getDataCheckout());
            ps.executeUpdate();
            ps.close();

            System.out.println("\nReserva atualizada com sucesso\n");
        }catch(SQLException e){
            System.out.println("Erro ao atualizar reserva");
            e.getMessage().toString();
        }
    }
    public void removerReserva(int id){
        Connection con = Connect.getConnection();
        String sql = "DELETE FROM RESERVA WHERE id = ? ";

        try(PreparedStatement smt = con.prepareStatement(sql)){
            smt.setInt(1, id);
            smt.executeUpdate();
            smt.close();
            System.out.println("\nRESERVA REMOVIDO COM SUCESSO\n");
        }catch (SQLException e){
            System.out.println(e.getMessage().toString());;
        }
    }

    //alternativa para ir busacar numero de acomodacao relacionado a reserva para poder calcular preco total de novo caso o utilizador mudar a data de chechOu
    public int buscarIdAcomodacao(int idReserva){
        Connection con = Connect.getConnection();
        int numeroQuarto = 0;
        String sql = "SELECT acomodacoes.numero FROM reservas JOIN clientes ON clientes.id = reservas.id_cliente join acomodacoes on acomodacoes.id = reservas.id_acomodacao WHERE reservas.id = ?";

        try(PreparedStatement smt = con.prepareStatement(sql)){
            smt.setInt(1, idReserva);
            ResultSet result = smt.executeQuery();
            if(result.next()){
                numeroQuarto = result.getInt("numero");
            }else{
                return numeroQuarto;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage().toString());
        }
        return numeroQuarto;
    }
}
