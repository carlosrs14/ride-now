/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import com.ridenow.models.Cliente;
import java.sql.SQLException;
import DAOs.UsuarioDAO;
import DTOs.UsuarioDTO;
import com.ridenow.models.Administrador;
import com.ridenow.models.PrestadorDeServicio;
import com.ridenow.models.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xlancet
 */
public class UsuarioServicio {
    private UsuarioDAO repositorio;
    
    public UsuarioServicio() {
        repositorio = new UsuarioDAO();
    }
    
    public UsuarioDTO save(UsuarioDTO usuarioDTO) throws SQLException, ClassNotFoundException {
        Usuario usuario = dtoToModel(usuarioDTO);      
        usuario = repositorio.create(usuario);
        return usuario != null? modelToDto(usuario): null;
    }
    
    public UsuarioDTO login(String email, String password) throws SQLException, ClassNotFoundException {
        Usuario userLogged;
      userLogged = repositorio.login(email, password);
        return userLogged != null? modelToDto(userLogged): null;
    }
    
    public UsuarioDTO get(UsuarioDTO usuarioDTO) throws SQLException, ClassNotFoundException {
        Usuario usuario = repositorio.get(usuarioDTO.getId());
        return usuario != null? modelToDto(usuario): null;
    }
    
    public List<UsuarioDTO> all() throws SQLException, ClassNotFoundException {
        List<Usuario> lista = repositorio.getAll();
        List<UsuarioDTO> resultado = new ArrayList<>();
        for (Usuario usuario: lista) {
            resultado.add(modelToDto(usuario));
        }
        return resultado;
    }
    
    public UsuarioDTO update(UsuarioDTO usuarioDTO) throws SQLException, ClassNotFoundException {
        Usuario usuario = dtoToModel(usuarioDTO);
        boolean actualizado = repositorio.update(usuario);
        return actualizado? modelToDto(usuario): null;
    }
    
    public UsuarioDTO delete(UsuarioDTO usuarioDTO) throws SQLException, ClassNotFoundException {
        boolean eliminado = repositorio.delete(usuarioDTO.getId());
        return eliminado? usuarioDTO: null;
    }
    
    private Usuario dtoToModel(UsuarioDTO dto) {
        Usuario usuario;
        switch (dto.getTipo()) {
            case "cliente" -> usuario = new Cliente(dto.getId());
            case "prestadordeservicio" -> {
                usuario = new PrestadorDeServicio(dto.getId());
                ((PrestadorDeServicio) usuario).setEstrellas(0);
            }
            case "admin" -> usuario = new Administrador(dto.getId());
            default -> throw new IllegalArgumentException("Tipo de usuario no válido: " + dto.getTipo());
        }
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setTipo(dto.getTipo());
        usuario.setTelefono(dto.getTelefono());
        usuario.setCorreo(dto.getCorreo());
        usuario.setPassword(dto.getPassword());
        usuario.setFechaNacimiento(dto.getFechaNacimiento());
        return usuario;
    }
    
    private UsuarioDTO modelToDto(Usuario usuario) {
        return new UsuarioDTO(
            usuario.getId(),
            usuario.getFechaNacimiento(),
            usuario.getNombre(),
            usuario.getApellido(),
            usuario.getTipo(),
            usuario.getTelefono(),
            usuario.getCorreo(),
            usuario.getPassword()
        );
    }
}
