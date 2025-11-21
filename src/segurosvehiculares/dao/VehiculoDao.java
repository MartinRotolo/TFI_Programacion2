package segurosvehiculares.dao;

import segurosvehiculares.config.DatabaseConnection;
import segurosvehiculares.entities.Vehiculo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehiculoDao implements GenericDao<Vehiculo> {

    private Connection connection;

    public VehiculoDao() {
        try {
            this.connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Error obteniendo conexión", e);
        }
    }

    public VehiculoDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void crear(Vehiculo v) throws SQLException {
   
        String sql = "INSERT INTO vehiculo " +
                "(dominio, marca, modelo, anio, nro_chasis, eliminado) " + 
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(
                sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, v.getDominio());
            ps.setString(2, v.getMarca());
            ps.setString(3, v.getModelo());
            ps.setObject(4, v.getAnio());
            ps.setString(5, v.getNroChasis());
            ps.setBoolean(6, Boolean.TRUE.equals(v.getEliminado()));
            


            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    v.setId(rs.getLong(1));
                }
            }
        }
    }
    @Override
    public Vehiculo leer(long id) throws SQLException {
        String sql = "SELECT * FROM vehiculo WHERE id = ? AND eliminado = FALSE";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapVehiculo(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Vehiculo> leerTodos() throws SQLException {
        List<Vehiculo> lista = new ArrayList<>();
        String sql = "SELECT * FROM vehiculo WHERE eliminado = FALSE";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapVehiculo(rs));
            }
        }
        return lista;
    }

  @Override
    public void actualizar(Vehiculo v) throws SQLException {

        String sql = "UPDATE vehiculo SET " +
                "dominio = ?, marca = ?, modelo = ?, anio = ?, " +
                "nro_chasis = ?, eliminado = ? " +
                "WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, v.getDominio());
            ps.setString(2, v.getMarca());
            ps.setString(3, v.getModelo());
            ps.setObject(4, v.getAnio());
            ps.setString(5, v.getNroChasis());
            ps.setBoolean(6, Boolean.TRUE.equals(v.getEliminado()));
            ps.setLong(7, v.getId()); // 

            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(long id) throws SQLException {
        String sql = "UPDATE vehiculo SET eliminado = TRUE WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

    public Vehiculo buscarPorDominio(String dominio) throws SQLException {
        String sql = "SELECT * FROM vehiculo " +
                     "WHERE dominio = ? AND eliminado = FALSE";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, dominio);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapVehiculo(rs);
                }
            }
        }
        return null;
    }

    private Vehiculo mapVehiculo(ResultSet rs) throws SQLException {
        Vehiculo v = new Vehiculo();

        v.setId(rs.getLong("id"));
        v.setDominio(rs.getString("dominio"));
        v.setMarca(rs.getString("marca"));
        v.setModelo(rs.getString("modelo"));
        v.setAnio((Integer) rs.getObject("anio"));
        v.setNroChasis(rs.getString("nro_chasis"));
        v.setEliminado(rs.getBoolean("eliminado"));

        return v;
    }
}
