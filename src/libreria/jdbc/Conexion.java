package libreria.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Fran
 */
public class Conexion {

    //private static final String JDBC_DRIVER = "org.sqlite.JDBC";
    private static final String JDBC_URL = "jdbc:sqlite:libreria.db";
    private static Driver driver = null;

    //Carga del driver y conexión

    /**
     * Método static que devuelve un objeto de tipo Conection y lanza una excepción 
     * por si no es posible establecer la conexión que es recogida por los métodos de la clase LibroDaoJDBC que lo llaman.
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL);
    }

    //Close objeto ResulSet

    /**
     *
     * @param rs
     */
    public static void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
  //Cerramos el objeto PreparedStatement

    /**
     *
     * @param stmt
     */
    public static void close(PreparedStatement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
    //Cerramos la conexión

    /**
     *
     * @param conn
     */
    public static void close(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    /**
     * Crear una nueva base de datos.
     *
     */
    public static void createNewDatabas() {

        String url = "jdbc:sqlite:libreria.db";

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
                conn.close();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
