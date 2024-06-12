package org.sistema.reserva.service;

import org.sistema.reserva.dao.HotelDAO;
import org.sistema.reserva.entity.Hotel;

public class HotelService {
    public Hotel infoHotel(){
        HotelDAO hotelDAO = new HotelDAO();
        Hotel hotel = hotelDAO.infoHotel();
        return hotel;
    }
}
