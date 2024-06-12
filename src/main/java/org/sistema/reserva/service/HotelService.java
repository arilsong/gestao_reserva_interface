package org.sistema.reserva.service;

import org.sistema.reserva.dao.HotelDAO;
import org.sistema.reserva.entity.Hotel;

public class HotelService {
    public void infoHotel(){
        HotelDAO hotelDAO = new HotelDAO();
        Hotel hotel = hotelDAO.infoHotel();


        System.out.println(
                hotel.getNome()+"\n" +
                hotel.getContato()+"\n" +
                hotel.getLocalizacao()+"\n" +
                hotel.getDescricao()+"\n" +
                hotel.getClassificacao()
        );
    }
}
