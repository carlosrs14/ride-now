
package models;

import com.ridenow.models.Cliente;
import com.ridenow.models.PrestadorDeServicio;
import models.Denuncia;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.DAOs.DenunciaDAO;
import models.DTOs.DenunciaDTO;
import models.factory.DenunciaFactory;
import models.validacion.DenunciaValidador;


public class DenunciaServicio {
    private final DenunciaDAO repositorio;
    private final DenunciaValidador validador;
    private final DenunciaFactory denunciaFactory;
    
    public DenunciaServicio() {
        this.repositorio = new DenunciaDAO();
        this.validador = new DenunciaValidador();
        this.denunciaFactory = new DenunciaFactory();
    }
    
    public DenunciaDTO save(DenunciaDTO denunciaDTO) throws SQLException, ClassNotFoundException {
        //se crea la denuncia usando la fábrica
        Denuncia denuncia = denunciaFactory.crearDenuncia(denunciaDTO);
        
        //validamos la denuncia antes de guardar
        if (!validador.validarDenuncia(denuncia)) {
            return null;
        }
        
        denuncia = repositorio.create(denuncia);
        return denuncia != null ? modelToDto(denuncia) : null;
    }
    
    public DenunciaDTO get(DenunciaDTO denunciaDTO) throws SQLException, ClassNotFoundException {
        Denuncia denuncia = repositorio.get(denunciaDTO.getId());
        return denuncia != null ? modelToDto(denuncia) : null;
    }
    
    public List<DenunciaDTO> all() throws SQLException, ClassNotFoundException {
        List<Denuncia> lista = repositorio.getAll();
        List<DenunciaDTO> resultado = new ArrayList<>();
        for (Denuncia denuncia: lista) {
            resultado.add(modelToDto(denuncia));
        }
        return resultado;
    }
    
    public DenunciaDTO update(DenunciaDTO denunciaDTO) throws SQLException, ClassNotFoundException {
        Denuncia denuncia = dtoToModel(denunciaDTO);
        boolean actualizado = repositorio.update(denuncia);
        return actualizado ? modelToDto(denuncia) : null;
    }
    
    public DenunciaDTO delete(DenunciaDTO denunciaDTO) throws SQLException, ClassNotFoundException {
        boolean eliminado = repositorio.delete(denunciaDTO.getId());
        return eliminado ? denunciaDTO : null;
    }
    
    public List<DenunciaDTO> getDenunciasByCliente(int idCliente) throws SQLException, ClassNotFoundException {
        List<Denuncia> lista = repositorio.filterByOwner(idCliente);
        List<DenunciaDTO> resultado = new ArrayList<>();
        for (Denuncia denuncia: lista) {
            resultado.add(modelToDto(denuncia));
        }
        return resultado;
    }
    
    private Denuncia dtoToModel(DenunciaDTO dto) {
        return new Denuncia(
            dto.getId(),
            dto.getFecha(),
            dto.getDescripcion(),
            dto.getEstado(),
            new Cliente(dto.getIdCliente()),
            new PrestadorDeServicio(dto.getIdPrestadorDeServicio())
        );
    }
    
    private DenunciaDTO modelToDto(Denuncia denuncia) {
        return new DenunciaDTO(
            denuncia.getId(),
            denuncia.getFecha(),
            denuncia.getDescripcion(),
            denuncia.getEstado(),
            denuncia.getCliente().getId(),
            denuncia.getPrestadorDeServicio().getId()
        );
    }
} 