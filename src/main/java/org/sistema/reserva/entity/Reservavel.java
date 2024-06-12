package org.sistema.reserva.entity;

import java.time.LocalDate;


public interface Reservavel {
    void reservar(int num, LocalDate dataCheckin, LocalDate dataCheckout);
    void cancelarReserva(int idReserva);
}
