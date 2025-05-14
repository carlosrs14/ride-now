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
    
    public UsuarioDTO saveUsuario(UsuarioDTO usuarioDTO) throws SQLException, ClassNotFoundException {
        Usuario usuario = dtoToModel(usuarioDTO);      
        usuario = repositorio.create(usuario);
        if (usuario == null) {
            return null;
        }
        usuarioDTO.setId(usuario.getId());
        return usuarioDTO;
    }
    
    public UsuarioDTO login(String email, String password) {
        Usuario userLogged = null;
        try {
            userLogged = repositorio.login(email, password);
        } catch (SQLException | ClassNotFoundException ex) {
            return null;
        }
        if (userLogged == null) return null;
        return modelToDto(userLogged);
    }
    public UsuarioDTO get(UsuarioDTO usuarioDTO) throws SQLException, ClassNotFoundException {
        Usuario usuario = repositorio.get(usuarioDTO.getId());
        return usuario != null ? modelToDto(usuario) : null;
    }
    
    public List<UsuarioDTO> all() throws SQLException, ClassNotFoundException {
        List<Usuario> usuarios = repositorio.getAll();
        List<UsuarioDTO> dtos = new ArrayList<>();
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);
            UsuarioDTO dto = modelToDto(usuario);
            dtos.add(dto);
        }
        return dtos;
    }
    
    public UsuarioDTO update(UsuarioDTO usuarioDTO) throws SQLException, ClassNotFoundException {
        Usuario usuario = dtoToModel(usuarioDTO);
        boolean actualizado = repositorio.update(usuario);
        return actualizado ? modelToDto(usuario) : null;
    }
    
    public UsuarioDTO delete(UsuarioDTO usuarioDTO) throws SQLException, ClassNotFoundException {
        boolean eliminado = repositorio.delete(usuarioDTO.getId());
        return eliminado ? usuarioDTO : null;
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
