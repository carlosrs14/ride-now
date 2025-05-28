/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.DAOs;

/**
 *
 * @author Rossimar
 */
public class DAOFactory {
    
    public static VehiculoDAO getVehiculoDAO() {
        return new VehiculoDAO();
    }
    
    public static ViajeDAO getViajeDAO() {
        return new ViajeDAO();
    }
    
}
