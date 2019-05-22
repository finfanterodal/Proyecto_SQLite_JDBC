package libreria.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import libreria.dto.LibroDTO;

/**
 *
 * @author Fran
 */
public class LibroDaoJDBC implements LibroDaoInterface{
    
    
    //Constructor vacío
    public LibroDaoJDBC() {
    }

    //Utilizar la misma conexion para todas las acciones
    public LibroDaoJDBC(Connection userConn) {
        this.userConn = userConn;
    }

    private Connection userConn;

    private String sql_INSERT;
    private String sql_UPDATE;
    private String sql_DELETE;
    private String sql_SELECT;

    @Override
    public int insertLibro(LibroDTO libro) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        sql_INSERT = "INSERT INTO libros(isbn,autor,titulo,idGenero) VALUES(?,?,?,?)";
        try {
            if (this.userConn != null) {
                conn = this.userConn;
            } else {
                conn = Conexion.getConnection();
            }
            stmt = conn.prepareStatement(sql_INSERT);
            int index = 1;
            stmt.setInt(1, libro.getIsbn());
            stmt.setString(2, libro.getAutor());
            stmt.setString(3, libro.getTitulo());
            stmt.setInt(4, libro.getIdGenero());
            JOptionPane.showMessageDialog(null, "Ejecutado correctamente.", "Succed", JOptionPane.INFORMATION_MESSAGE);
            rows = stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registros añadidos: " + rows, "Succed", JOptionPane.INFORMATION_MESSAGE);
        } finally {
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        return rows;
    }

    @Override
    public int updateLibro(LibroDTO libro) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        String sql_UPDATE = "UPDATE libros SET autor = ? , "
                + "titulo = ?,"
                + "idGenero = ?"
                + "WHERE isbn = ?";
        try {
            if (this.userConn != null) {
                conn = this.userConn;
            } else {
                conn = Conexion.getConnection();
            }
            stmt = conn.prepareStatement(sql_UPDATE);
            int index = 1;
            //Libros   
            stmt.setInt(1, libro.getIsbn());
            stmt.setString(2, libro.getAutor());
            stmt.setString(3, libro.getTitulo());
            stmt.setInt(4, libro.getIdGenero());
            rows = stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registros Actualizados: " + rows, "Succed", JOptionPane.INFORMATION_MESSAGE);
        } finally {
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        return rows;
    }

    @Override
    public int deleteLibro(LibroDTO libro) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        String sql_DELETE = "DELETE FROM libros WHERE isbn = ?";
        try {
            if (this.userConn != null) {
                conn = this.userConn;
            } else {
                conn = Conexion.getConnection();
            }
            stmt = conn.prepareStatement(sql_DELETE);
            stmt.setInt(1, libro.getIdGenero());
            rows = stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registros Borrados: " + rows, "Succed", JOptionPane.INFORMATION_MESSAGE);
        } finally {
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        return rows;
    }

    @Override
    public List<LibroDTO> selectLibro(int consulta,jTextField valor) throws SQLException {
    }

}
