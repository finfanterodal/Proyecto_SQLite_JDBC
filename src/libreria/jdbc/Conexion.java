package libreria.jdbc;

import java.sql.Connection;
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

    private static final String JDBC_DRIVER = "D:\\NetBeansProjects\\SQLiteJDBC\\JavaJDBC_SQLite";
    private static final String JDBC_URL = "jdbc:jdbc:sqlite:libreria.db";
    private static Driver driver = null;

    //Carga del driver y conexión
    public static Connection getConnection() throws SQLException {
        if (driver == null) {
            try {
                Class jdbcDriverClass = Class.forName(JDBC_DRIVER);
                driver = (Driver) jdbcDriverClass.newInstance();
                DriverManager.registerDriver(driver);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Fallo al cargar el driver JDBC.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return DriverManager.getConnection(JDBC_URL);
    }

    //Close objeto ResulSet
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
    public static void close(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}
