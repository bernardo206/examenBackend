package service;

import DAO.iDAO;
import models.Odontologo;

import java.util.List;

public class OdontologoService {

    private iDAO<Odontologo> OdontologoDao;

    public OdontologoService(iDAO<Odontologo> odontologoDao) {
        this.OdontologoDao = odontologoDao;
    }


    public iDAO<Odontologo> getOdontologoDao() {
        return OdontologoDao;
    }

    public void setOdontologoDao(iDAO<Odontologo> odontologoDao) {
        OdontologoDao = odontologoDao;
    }

    public Odontologo guardarOdontologo(Odontologo odontologo){
        OdontologoDao.guardar(odontologo);

        return odontologo;
    }

    public List<Odontologo> listarOdontologo() {
        return OdontologoDao.listarTodos();


    }


}
