
import com.ridenow.models.Cliente;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import repositorios.UsuarioRepositorio;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author xlancet
 */
public class prueba {
    public static void main(String[] args) {
        // pruebaSave();
        // pruebaLogin();
    }
    
    public static void pruebaLogin() throws ClassNotFoundException {
        UsuarioRepositorio repositorio = new UsuarioRepositorio();
        String email = "nombre1@gmail.com";
        String password = "123456";
        try {
            if (repositorio.login(email, password) != null) {
                System.out.println("login exitoso");
            } else {
                System.out.println("incorrecto");
            }
        } catch (SQLException ex) {
            Logger.getLogger(prueba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void pruebaSave() throws ClassNotFoundException {
        UsuarioRepositorio repositorio = new UsuarioRepositorio();
        Cliente cliente = new Cliente(0, 7, 7, 2000, "nombre", "apellido", "nombre1@gmail.com", "3000000009", "123456");
        try {
            if (repositorio.saveCliente(cliente)) {
                System.out.println("guardar exitoso");
            } else {
                System.out.println("incorrecto");
            }
        } catch (SQLException ex) {
            Logger.getLogger(prueba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
