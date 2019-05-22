package libreria.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fran
 */
public class GeneroDTO {

    /*
    *Atributos.
     */
    private int idGenero;
    private String genero;
    //

    /**
     * Constructor con todos los parámetros.
     *
     * @param idGenero
     * @param genero
     */
    public GeneroDTO(int idGenero, String genero) {
        this.idGenero = idGenero;
        this.genero = genero;
    }

    /**
     * Constructor vacío.
     */
    public GeneroDTO() {
    }
    //

    /**
     *Getter del parámetro idGenero.
     * @return int idGenero
     */
    public int getIdGenero() {
        return idGenero;
    }

    /**
     * Getter del parámetro genero.
     * @return  String genero
     */
    public String getGenero() {
        return genero;
    }

    @Override
    public String toString() {
        return "Genero: " + "idGenero:" + idGenero + ", genero: " + genero;
    }
}
