package segurosvehiculares.service;

import segurosvehiculares.dao.SeguroVehicularDao;
import segurosvehiculares.entities.SeguroVehicular;

import java.sql.SQLException;
import java.util.List;

public class SeguroVehicularService implements GenericService<SeguroVehicular> {

    private final SeguroVehicularDao seguroDao;

    public SeguroVehicularService(SeguroVehicularDao seguroDao) {
        this.seguroDao = seguroDao;
    }

    @Override
    public void insertar(SeguroVehicular seguro) throws SQLException {
        validarSeguro(seguro);
        seguroDao.crear(seguro);
    }

    @Override
    public void actualizar(SeguroVehicular seguro) throws SQLException {
        if (seguro.getId() == null) {
            throw new IllegalArgumentException("Id de seguro obligatorio para actualizar");
        }
        validarSeguro(seguro);
        seguroDao.actualizar(seguro);
    }

    @Override
    public void eliminar(long id) throws SQLException {
        seguroDao.eliminar(id);
    }

    @Override
    public SeguroVehicular getById(long id) throws SQLException {
        return seguroDao.leer(id);
    }

    @Override
    public List<SeguroVehicular> getAll() throws SQLException {
        return seguroDao.leerTodos();
    }

    private void validarSeguro(SeguroVehicular seguro) {
        if (seguro == null) {
            throw new IllegalArgumentException("El seguro no puede ser nulo");
        }
        if (seguro.getAseguradora() == null || seguro.getAseguradora().isBlank()) {
            throw new IllegalArgumentException("La aseguradora es obligatoria");
        }
        if (seguro.getCobertura() == null) {
            throw new IllegalArgumentException("La cobertura es obligatoria");
        }
        if (seguro.getVencimiento() == null) {
            throw new IllegalArgumentException("La fecha de vencimiento es obligatoria");
        }
    }
}
