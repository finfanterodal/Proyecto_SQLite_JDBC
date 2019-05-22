package libreria.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fran
 */
public class GeneroDTO {

    private int idGenero;
    private String genero;
    //

    public GeneroDTO(int idGenero, String genero) {
        this.idGenero = idGenero;
        this.genero = genero;
    }

    public GeneroDTO() {
    }
    //

    public int getIdGenero() {
        return idGenero;
    }

    public String getGenero() {
        return genero;
    }

    @Override
    public String toString() {
        return "Genero: " + "idGenero:" + idGenero + ", genero: " + genero;
    }
}
