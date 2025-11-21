package segurosvehiculares.service;

import segurosvehiculares.config.DatabaseConnection;
import segurosvehiculares.dao.SeguroVehicularDao;
import segurosvehiculares.dao.VehiculoDao;
import segurosvehiculares.entities.SeguroVehicular;
import segurosvehiculares.entities.Vehiculo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class VehiculoService implements GenericService<Vehiculo> {

    private final VehiculoDao vehiculoDao;
    private final SeguroVehicularDao seguroDao;

    public VehiculoService(VehiculoDao vehiculoDao, SeguroVehicularDao seguroDao) {
        this.vehiculoDao = vehiculoDao;
        this.seguroDao = seguroDao;
    }

    @Override
    public void insertar(Vehiculo vehiculo) throws SQLException {
        validarVehiculo(vehiculo);
        vehiculoDao.crear(vehiculo);
    }

    @Override
    public void actualizar(Vehiculo vehiculo) throws SQLException {
        if (vehiculo.getId() == null) {
            throw new IllegalArgumentException("Id de vehículo obligatorio para actualizar");
        }
        validarVehiculo(vehiculo);

        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            VehiculoDao vehiculoDaoTx = new VehiculoDao(conn);
            SeguroVehicularDao seguroDaoTx = new SeguroVehicularDao(conn);

            if (vehiculo.getSeguro() != null && vehiculo.getSeguro().getId() != null) {
                validarSeguro(vehiculo.getSeguro());
                seguroDaoTx.actualizar(vehiculo.getSeguro());
            }

            vehiculoDaoTx.actualizar(vehiculo);

            conn.commit();
        } catch (SQLException e) {
            if (conn != null) conn.rollback();
            throw e;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException ignored) {}
            }
        }
    }

    @Override
    public void eliminar(long id) throws SQLException {
        vehiculoDao.eliminar(id);
    }

    @Override
    public Vehiculo getById(long id) throws SQLException {
        return vehiculoDao.leer(id);
    }

    @Override
    public List<Vehiculo> getAll() throws SQLException {
        return vehiculoDao.leerTodos();
    }

    public Vehiculo buscarPorDominio(String dominio) throws SQLException {
        return vehiculoDao.buscarPorDominio(dominio);
    }

    // TRANSACCIÓN: crea seguro + vehículo juntos
    public void crearVehiculoConSeguro(Vehiculo vehiculo,
                                       SeguroVehicular seguro) throws SQLException {

        validarVehiculo(vehiculo);
        validarSeguro(seguro);

        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            SeguroVehicularDao seguroDaoTx = new SeguroVehicularDao(conn);
            VehiculoDao vehiculoDaoTx = new VehiculoDao(conn);

            
            vehiculo.setEliminado(false);
            vehiculoDaoTx.crear(vehiculo);
            
            seguro.setVehiculoId(vehiculo.getId());
            
            seguro.setEliminado(false);
            seguroDaoTx.crear(seguro);
            
            vehiculo.setSeguro(seguro);
            
            conn.commit();
        } catch (SQLException e) {
            if (conn != null) conn.rollback();
            throw e;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException ignored) {}
            }
        }
    }

    private void validarVehiculo(Vehiculo v) {
        if (v == null) throw new IllegalArgumentException("El vehículo no puede ser nulo");
        if (v.getDominio() == null || v.getDominio().isBlank())
            throw new IllegalArgumentException("El dominio es obligatorio");
        if (v.getMarca() == null || v.getMarca().isBlank())
            throw new IllegalArgumentException("La marca es obligatoria");
        if (v.getModelo() == null || v.getModelo().isBlank())
            throw new IllegalArgumentException("El modelo es obligatorio");
    }

    private void validarSeguro(SeguroVehicular s) {
        if (s == null) throw new IllegalArgumentException("El seguro no puede ser nulo");
        if (s.getAseguradora() == null || s.getAseguradora().isBlank())
            throw new IllegalArgumentException("La aseguradora es obligatoria");
        if (s.getCobertura() == null)
            throw new IllegalArgumentException("La cobertura es obligatoria");
        if (s.getVencimiento() == null)
            throw new IllegalArgumentException("La fecha de vencimiento es obligatoria");
    }
}
