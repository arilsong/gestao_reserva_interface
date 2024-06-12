package org.sistema.reserva.service;

import javafx.collections.ObservableList;
import org.sistema.reserva.dao.ReservaDAO;
import org.sistema.reserva.entity.Cliente;
import org.sistema.reserva.entity.Quarto;
import org.sistema.reserva.entity.Reserva;


import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static java.util.Date.*;

public class ReservaService {
    ReservaDAO reservaDAO = new ReservaDAO();
    Map<Integer, Quarto> quartosMap;

    public void adicionarReserva(LocalDate LocaldataCheckin, LocalDate LocaldataCheckout, double valorTotal, int num_quarto){
        Reserva reserva = new Reserva();

        // Convertendo LocalDate para Date
        Date dataCheckin = Date.valueOf(LocaldataCheckin);
        Date dataCheckout = Date.valueOf(LocaldataCheckout);

        reserva.setDataCheckin(dataCheckin);
        reserva.setDataCheckout(dataCheckout);
        reserva.setValorTotal(valorTotal);

        reservaDAO.adicionarReserva(reserva,num_quarto);
    }


    public ObservableList<Reserva> listarReservaCliente(){
        ObservableList<Reserva> lista = reservaDAO.lisarReservaCliente();
        return lista;

    }

    public ObservableList<Reserva> listaReservas(){
        ObservableList<Reserva> lista = reservaDAO.listaReservas();
        return lista;
    }

    public Reserva umReserva(int id){
        return reservaDAO.umReserva(id);
    }

    public void atualizarReserva(Date dataCheckin, Date dataCheckout, int idreserva, double precoTotal){
        Reserva reserva = new Reserva();

        reserva.setDataCheckin(dataCheckin);
        reserva.setDataCheckout(dataCheckout);
        reserva.setValorTotal(precoTotal);

        reservaDAO.atualizarReserva(reserva, idreserva);
    }


    public void removerReseerva(int id){
        reservaDAO.removerReserva(id);
    }

    public int buscarIdAcomodacao(int idReserva){
        int numeroQuarto = reservaDAO.buscarIdAcomodacao(idReserva);
        return numeroQuarto;
    }

}
