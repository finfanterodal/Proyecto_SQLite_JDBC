package libreria.jdbc;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import libreria.dto.GeneroDTO;
import libreria.dto.LibroDTO;
import static libreria.jdbc.Conexion.createNewDatabas;

/**
 *
 * @author Fran
 */
public class LibroDaoJDBC {

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
            JOptionPane.showMessageDialog(null, "Registros insertados: " + rows, "Succed", JOptionPane.INFORMATION_MESSAGE);
        } finally {
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        return rows;
    }

    public int updateLibro(LibroDTO libro) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        sql_UPDATE = "UPDATE libros SET autor = ? , "
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
            //Libros   

            stmt.setString(1, libro.getAutor());
            stmt.setString(2, libro.getTitulo());
            stmt.setInt(3, libro.getIdGenero());
            stmt.setInt(4, libro.getIsbn());
            JOptionPane.showMessageDialog(null, "Ejecutado correctamente.", "Succed", JOptionPane.INFORMATION_MESSAGE);
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

    public int deleteLibro(int valorIsbn) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        sql_DELETE = "DELETE FROM libros WHERE isbn = ?";
        try {
            if (this.userConn != null) {
                conn = this.userConn;
            } else {
                conn = Conexion.getConnection();
            }
            stmt = conn.prepareStatement(sql_DELETE);
            stmt.setInt(1, valorIsbn);
            JOptionPane.showMessageDialog(null, "Ejecutado correctamente.", "Succed", JOptionPane.INFORMATION_MESSAGE);
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

    public void selectLibro(int valorSelect, String valor) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String vconsulta = "";

        try {
            if (this.userConn != null) {
                conn = this.userConn;
            } else {
                conn = Conexion.getConnection();
            }
            switch ( valorSelect) {
                case 0://Libros con Isbn
                    sql_SELECT = "SELECT isbn,autor,titulo,idGenero FROM libros WHERE isbn = ?";
                    stmt = conn.prepareStatement(sql_SELECT);
                    stmt.setInt(1, Integer.parseInt(valor));
                    break;
                case 1://Libros con Autor
                    sql_SELECT = "SELECT isbn,autor,titulo,idGenero FROM libros WHERE autor = ?";
                    stmt = conn.prepareStatement(sql_SELECT);
                    stmt.setString(1, valor);
                    break;
                case 2://Libros con Titulo
                    sql_SELECT = "SELECT isbn,autor,titulo,idGenero FROM libros WHERE titulo = ?";
                    stmt = conn.prepareStatement(sql_SELECT);
                    stmt.setString(1, valor);
                    break;
                case 3://Libros con idGenero
                    sql_SELECT = "SELECT isbn,autor,titulo,idGenero FROM libros WHERE idGenero = ?";
                    stmt = conn.prepareStatement(sql_SELECT);
                    stmt.setInt(1, Integer.parseInt(valor));
                    break;
                case 4://Libros con Genero
                    sql_SELECT = "SELECT isbn,autor,titulo,idGenero FROM libros WHERE idGenero IN(SELECT idGenero FROM generos WHERE genero = ?)";
                    stmt = conn.prepareStatement(sql_SELECT);
                    stmt.setString(1, valor);
                    break;
                case 5://Selecciona el génerto de un libros dado su isbn
                    sql_SELECT = "SELECT idGenero,genero FROM generos WHERE idGenero IN(SELECT idGenero FROM libros WHERE isbn = ?)";
                    stmt = conn.prepareStatement(sql_SELECT);
                    stmt.setInt(1, Integer.parseInt(valor));
                    break;
            }
            rs = stmt.executeQuery();
            // loop through the result set
            while (rs.next()) {
                if (valorSelect != 5) {
                    LibroDTO libro = new LibroDTO(rs.getInt("isbn"), rs.getString("autor"), rs.getString("titulo"), rs.getInt("idGenero"));
                    vconsulta = vconsulta + "\n" + libro.toString();
                } else {
                    GeneroDTO gen = new GeneroDTO(rs.getInt("idGenero"), rs.getString("genero"));
                    vconsulta = vconsulta + "\n" + gen.toString();
                }
            }
            JOptionPane.showMessageDialog(null, vconsulta, "Succed", JOptionPane.INFORMATION_MESSAGE);
            while (rs.next()) {

            }
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
    }

    /**
     *
     * Hace un select de la base de datos de todos los libros y los carga en
     * este Array borrando los datos que ya contenía.
     *
     */
    public ArrayList<LibroDTO> refreshArrayLibro() throws SQLException {
        ArrayList<LibroDTO> libros = new ArrayList<LibroDTO>();
        sql_SELECT = "SELECT isbn,autor,titulo,idGenero FROM libros";
        //Borramos arrayList
        libros.clear();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            if (this.userConn != null) {
                conn = this.userConn;
            } else {
                conn = Conexion.getConnection();
            }
            stmt = conn.prepareStatement(sql_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                LibroDTO libro = new LibroDTO(rs.getInt("isbn"), rs.getString("autor"), rs.getString("titulo"), rs.getInt("idGenero"));
                libros.add(libro);
            }
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        return libros;
    }

    /**
     * Hace un select de la base de datos de todos los generos y los carga en
     * este Array borrando los datos que ya contenía.
     *
     */
    public ArrayList<GeneroDTO> refreshArrayGenero() throws SQLException {
        ArrayList<GeneroDTO> generos = new ArrayList<GeneroDTO>();
        sql_SELECT = "SELECT idGenero,genero FROM generos";
        generos.clear();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            if (this.userConn != null) {
                conn = this.userConn;
            } else {
                conn = Conexion.getConnection();
            }
            stmt = conn.prepareStatement(sql_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                GeneroDTO gen = new GeneroDTO(rs.getInt("idGenero"), rs.getString("genero"));
                generos.add(gen);
            }
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
        return generos;
    }

    /**
     * Creamos las tablas que utilizamos en nuestra base de datos.
     *
     *
     */
    public void crearTablas() {

        Connection conn = null;
        Statement stmt = null;

        String sql1 = "CREATE TABLE IF NOT EXISTS libros (\n"
                + " isbn integer PRIMARY KEY,\n"
                + " autor text NOT NULL,\n"
                + " titulo text NOT NULL,\n"
                + " idGenero integer NOT NULL REFERENCES generos (idGenero) \n"
                + ");";
        String sql2 = "CREATE TABLE IF NOT EXISTS generos (\n"
                + "	idGenero integer PRIMARY KEY,\n"
                + "	genero text NOT NULL \n"
                + ");";

        try {
            if (this.userConn != null) {
                conn = this.userConn;
            } else {
                conn = Conexion.getConnection();
            }
            stmt = conn.createStatement();

            // create new tables
            stmt.execute(sql1);
            stmt.execute(sql2);
            JOptionPane.showMessageDialog(null, "Tablas creadas correctamente.", "Succed", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            Logger.getLogger(LibroDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
    }

    /**
     * Añadimos datos de un fichero a la tabla de Generos.
     *
     *
     */
    public void cargarDatosInicialesGenero() {
        Connection conn = null;
        PreparedStatement stmt = null;
        Scanner sc = null;
        String sql1 = "INSERT INTO generos(idGenero,genero) VALUES(?,?)";
        try {
            if (this.userConn != null) {
                conn = this.userConn;
            } else {
                conn = Conexion.getConnection();
            }
            stmt = conn.prepareStatement(sql1);
            File fichero = new File("Generos.txt");
            sc = new Scanner(fichero);
            while (sc.hasNextLine()) {
                String[] gen = sc.nextLine().split(",");
                stmt.setInt(1, Integer.parseInt(gen[0]));
                stmt.setString(2, gen[1]);
                stmt.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(LibroDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LibroDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }
    }

    /**
     * Añadimos datos de un fichero a la tabla de Generos.
     *
     *
     */
    public void cargarDatosInicialesLibro() {
        Connection conn = null;
        PreparedStatement stmt = null;
        Scanner sc = null;
        String sql1 = "INSERT INTO libros(isbn,autor,titulo,idGenero) VALUES(?,?,?,?)";
        try {
            if (this.userConn != null) {
                conn = this.userConn;
            } else {
                conn = Conexion.getConnection();
            }
            stmt = conn.prepareStatement(sql1);
            File fichero = new File("Libros.txt");
            sc = new Scanner(fichero);
            //Libros   
            while (sc.hasNextLine()) {
                String[] gen = sc.nextLine().split(",");
                stmt.setInt(1, Integer.parseInt(gen[0]));
                stmt.setString(2, gen[1]);
                stmt.setString(3, gen[2]);
                stmt.setInt(4, Integer.parseInt(gen[3]));
                stmt.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(LibroDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LibroDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(stmt);
            if (this.userConn == null) {
                Conexion.close(conn);
            }
        }

    }

    public void init() {

        File fichero = new File("libreria.db");
        if (!fichero.exists()) {
            createNewDatabas();
            crearTablas();
            cargarDatosInicialesGenero();
            cargarDatosInicialesLibro();
        }
    }
}
