package org.sistema.reserva.entity;

//import org.gestao_reserva.screens.TelaHome;
//import org.gestao_reserva.service.QuartoService;
//import org.gestao_reserva.service.ReservaService;

import org.sistema.reserva.service.QuartoService;
import org.sistema.reserva.service.ReservaService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Quarto extends Acomodacao implements Reservavel {

    @Override
    public void reservar(int numQuarto,LocalDate dataCheckIn, LocalDate dataCheckOut) {
        QuartoService quartoService = new QuartoService();
        Quarto quarto = quartoService.umQuarto(numQuarto);
        ReservaService reservaService = new ReservaService();

        if(!quarto.isEstado()){
            double precoTotal = clcPrecoTotal(dataCheckIn, dataCheckOut, quarto.getPreco());
            reservaService.adicionarReserva(dataCheckIn, dataCheckOut, precoTotal, numQuarto);
            quartoService.actualizarEstadoQuarto(true, numQuarto);
        }else{
            System.out.println("quarto ja reservado");
        }
    }

    @Override
    public void cancelarReserva(int idReserva) {
//        QuartoService quartoService = new QuartoService();
//        ReservaService reservaService = new ReservaService();
//        int numQuarto = reservaService.buscarIdAcomodacao(idReserva);
//        System.out.println(numQuarto);
//        quartoService.actualizarEstadoQuarto(false, quartoService.umQuarto(numQuarto).getId());
//        reservaService.removerReseerva(idReserva);
    }

//    public void actualizarReserva(Date dataCheckIn, Date dataCheckOut, int idreserva){
//
//        ReservaService reservaService = new ReservaService();
//        QuartoService quartoService = new QuartoService();
//        int numQuarto = reservaService.buscarIdAcomodacao(idreserva);
//        //busacar quarto
//        Quarto quarto =  quartoService.umQuarto(numQuarto);
//        if(quarto == null){
//            System.out.println("quarto vazio");
//            TelaHome.telaHome();
//        }
//
//        if(dataCheckIn == null || dataCheckOut == null){
//            System.out.println("\nerro ao atualizar, ensira as datas");
//            TelaHome.telaHome();
//        }
//
//        double precoTotal = clcPrecoTotal(dataCheckIn, dataCheckOut, quarto.getPrecoBase());
//        reservaService.atualizarReserva(dataCheckIn, dataCheckOut, idreserva, precoTotal);
//
//    }

    public double clcPrecoTotal(LocalDate dataCheckin,LocalDate dataCheckout, double precoPorDia){
        long numeroDeDias = ChronoUnit.DAYS.between(dataCheckin, dataCheckout);
        System.out.println("numero de dias:   " + numeroDeDias);
        return numeroDeDias * precoPorDia;
    }
}
