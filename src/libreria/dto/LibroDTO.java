package libreria.dto;

/**
 *
 * @author Fran
 */
public class LibroDTO {

    private int isbn;
    private String titulo;
    private String autor;
    private int idGenero;

    public LibroDTO(int isbn) {
        this.isbn = isbn;
    }

    public LibroDTO(int isbn, String autor, String titulo, int idGenero) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.idGenero = idGenero;
    }

    public int getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getIdGenero() {
        return idGenero;
    }

    @Override
    public String toString() {
        return "Libro: " + " isbn: " + isbn + ", titulo: " + titulo + ", autor: " + autor + ", idGenero: " + idGenero;
    }
}
