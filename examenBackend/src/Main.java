import DAO.iDAO;
import DAO.impl.OdontologoDAOH2;
import models.Odontologo;

public class Main {
    public static void main(String[] args) {

        iDAO<Odontologo> daoPaciente = new OdontologoDAOH2("jdbc:h2:~/test", "sa", "", "pacientes");

        System.out.println("- La tabla se generó con éxito");
    }
}