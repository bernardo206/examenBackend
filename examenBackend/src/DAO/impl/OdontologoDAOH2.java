package DAO.impl;

import DAO.iDAO;
import models.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class OdontologoDAOH2 implements iDAO<Odontologo> {

    public static Logger logger = Logger.getLogger(OdontologoDAOH2.class);

    private String url;
    private String username;
    private String password;

    private final String TABLA = "Odontologo";

    public OdontologoDAOH2 (String url, String username, String password, String nombreDeLaTabla) {

        this.url = url;
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //1 Levantar el driver y Conectarnos
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(url, username, password);

            //2 Crear una sentencia
            preparedStatement = connection.prepareStatement("INSERT INTO odontologo VALUES(?,?,?)");
            preparedStatement.setLong(1, odontologo.getNumeroDeMatricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());

            //3 Ejecutar una sentencia SQL
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return odontologo;

    }

        @Override
    public List<Odontologo> listarTodos() {
            List<Odontologo> resultado = new ArrayList<>();

            //[1] Obtener y levantar el controlador
            try {
                Class.forName("org.h2.Driver");
            } catch (ClassNotFoundException ex) {
                logger.error(ex.getMessage());
            }
            //[2] Abrir la conexión a la base de datos, y usar esa conexión para crear un objeto tipo PreparedStatement
            try (Connection conn = DriverManager.getConnection(this.url, this.username, this.password);
                 Statement stmt = conn.createStatement()){

                //[2a] Ejecutar la sentencia SQL, procesar su respuesta
                ResultSet rs = stmt.executeQuery("SELECT * FROM " + TABLA + ";");

                while (rs.next()){
                Odontologo o = new Odontologo();

                o.setNumeroDeMatricula(rs.getInt(1));
                o.setApellido(rs.getString(2));
                o.setNombre(rs.getString(3));

                resultado.add(o);}
                } catch (SQLException ex) {
                    logger.error(ex.getMessage());
                }
                //[3] Retornar la lista de registros encontrados.
                return resultado;

            }

}
