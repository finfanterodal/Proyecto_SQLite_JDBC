package javajdbc_sqlite;

import java.io.File;
import libreria.jdbc.LibroDaoJDBC;
import libreriaUI.MenuTablas;

/**
 *
 * @author Fran
 */
public class JavaJDBC_SQLite {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LibroDaoJDBC li = new LibroDaoJDBC();
        li.init();

        MenuTablas menu = new MenuTablas();
        menu.setVisible(true);
    }

}
