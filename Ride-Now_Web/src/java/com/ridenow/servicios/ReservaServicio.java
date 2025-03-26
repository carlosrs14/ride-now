/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ridenow.servicios;

import com.ridenow.modelos.Reserva;
import com.ridenow.repositorios.ReservasRespositorio;

/**
 *
 * @author crinc
 */
public class ReservaServicio {
    private ReservasRespositorio reservaRepositorio;

    public ReservaServicio() {
        reservaRepositorio = new ReservasRespositorio();
    }
    
    public boolean registrarReserva(Reserva reserva) {
        if (reserva != null) {
            return reservaRepositorio.saveReserva(reserva);
        } else {
            return false;
        }
    }
}
