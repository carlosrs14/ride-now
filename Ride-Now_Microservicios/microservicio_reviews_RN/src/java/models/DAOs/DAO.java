/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package models.DAOs;


import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author xlancet
 * @param <G>
 */
public interface DAO<G> {
    G create(G g) throws SQLException, ClassNotFoundException;
    G get(int id) throws SQLException, ClassNotFoundException;
    List<G> getAll() throws SQLException, ClassNotFoundException;
    boolean update(G g) throws SQLException, ClassNotFoundException;
    boolean delete(int id) throws SQLException, ClassNotFoundException;
    List<G> filterByOwner(int id) throws SQLException, ClassNotFoundException;
}
