package libreria.jdbc;

import java.sql.SQLException;
import java.util.List;
import libreria.dto.LibroDTO;

/**
 *
 * @author Fran
 */
public interface LibroDaoInterface {

    public int insertLibro(LibroDTO libro) throws SQLException;

    public int updateLibro(LibroDTO libro) throws SQLException;

    public int deleteLibro(LibroDTO libro) throws SQLException;

    public List<LibroDTO> selectLibro(int consulta, String valor) throws SQLException;
}
