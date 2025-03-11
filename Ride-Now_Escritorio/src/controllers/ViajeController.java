/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.sql.SQLException;
import java.util.List;
import models.Viaje;
import persistencia.ViajePersistenacia;

/**
 *
 * @author crinc
 */
public class ViajeController {
    public static List<Viaje> filtrarViajes(String origen, String destino) throws SQLException {
        return ViajePersistenacia.filtrarViajes(origen, destino);
    }
}
