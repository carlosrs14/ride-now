package models.estados;

import models.Denuncia;

/* patrón State
interfaz para definir los estados de una denuncia 

 */
public interface EstadoDenuncia {
    void procesar(Denuncia denuncia);
    void rechazar(Denuncia denuncia);
    void resolver(Denuncia denuncia);
    String getEstado();
} 