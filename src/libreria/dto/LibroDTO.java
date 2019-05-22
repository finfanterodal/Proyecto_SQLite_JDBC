package libreria.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fran
 */
public class LibroDTO {
    //Atributos
    private int isbn;
    private String titulo;
    private String autor;
    private int idGenero;

    /**
     *Constructor con un solo atributo.
     * @param isbn
     */
    public LibroDTO(int isbn) {
        this.isbn = isbn;
    }

    /**
     *Constructor con todos los atributos.
     * @param int isbn
     * @param String autor
     * @param String titulo
     * @param String idGenero
     */
    public LibroDTO(int isbn, String autor, String titulo, int idGenero) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.idGenero = idGenero;
    }

    /**
     * Getter del parámetro Isbn.
     * @return int isbn
     */
    public int getIsbn() {
        return isbn;
    }

    /**
     * Getter del parámetro titulo.
     * @return String titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Getter del parámetro autor.
     * @return  Stringautor
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Getter del parámetro Idgenero.
     * @return int idGenero
     */
    public int getIdGenero() {
        return idGenero;
    }

    @Override
    public String toString() {
        return "Libro: " + " isbn: " + isbn + ", titulo: " + titulo + ", autor: " + autor + ", idGenero: " + idGenero;
    }
}
