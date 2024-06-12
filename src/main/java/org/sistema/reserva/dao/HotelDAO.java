package org.sistema.reserva.dao;

import org.sistema.reserva.connection.Connect;

import org.sistema.reserva.entity.Hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HotelDAO {
    public Hotel infoHotel(){
        Connection con = Connect.getConnection();
        Hotel hotel = null;
        String sql = "SELECT * FROM hotel_info WHERE id = ?";
        try(PreparedStatement smt = con.prepareStatement(sql)){
            smt.setInt(1, 1);
            ResultSet result = smt.executeQuery();
            if(result.next()){
                hotel = new Hotel();
                hotel.setNome(result.getString("nome"));
                hotel.setLocalizacao(result.getString("localizacao"));
                hotel.setContato(result.getString("contato"));
                hotel.setDescricao(result.getString("descricao"));
                hotel.setClassificacao(result.getString("classificacao"));
            }else{
                return hotel;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage().toString());
        }
        return hotel;
    }
}
