package segurosvehiculares.dao;

import segurosvehiculares.config.DatabaseConnection;
import segurosvehiculares.entities.CoberturaEnum;
import segurosvehiculares.entities.SeguroVehicular;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeguroVehicularDao implements GenericDao<SeguroVehicular> {

    private Connection connection;

    public SeguroVehicularDao() {
        try {
            this.connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Error obteniendo conexión", e);
        }
    }

    public SeguroVehicularDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void crear(SeguroVehicular s) throws SQLException {
        String sql = "INSERT INTO seguro_vehicular " +
                "(aseguradora, nro_poliza, cobertura, vencimiento, eliminado, vehiculo_id) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(
                sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, s.getAseguradora());
            ps.setString(2, s.getNroPoliza());
            ps.setString(3, s.getCobertura().name());
            ps.setDate(4, Date.valueOf(s.getVencimiento()));
            ps.setBoolean(5, Boolean.TRUE.equals(s.getEliminado()));
            ps.setObject(6, s.getVehiculoId());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    s.setId(rs.getLong(1));
                }
            }
        }
    }

    @Override
    public SeguroVehicular leer(long id) throws SQLException {
        String sql = "SELECT * FROM seguro_vehicular " +
                     "WHERE id = ? AND eliminado = FALSE";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapSeguro(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<SeguroVehicular> leerTodos() throws SQLException {
        List<SeguroVehicular> lista = new ArrayList<>();
        String sql = "SELECT * FROM seguro_vehicular WHERE eliminado = FALSE";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapSeguro(rs));
            }
        }
        return lista;
    }

    @Override
    public void actualizar(SeguroVehicular s) throws SQLException {
        String sql = "UPDATE seguro_vehicular SET " +
                "aseguradora = ?, nro_poliza = ?, cobertura = ?, " +
                "vencimiento = ?, eliminado = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, s.getAseguradora());
            ps.setString(2, s.getNroPoliza());
            ps.setString(3, s.getCobertura().name());
            ps.setDate(4, Date.valueOf(s.getVencimiento()));
            ps.setBoolean(5, Boolean.TRUE.equals(s.getEliminado()));
            ps.setLong(6, s.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void eliminar(long id) throws SQLException {
        String sql = "UPDATE seguro_vehicular SET eliminado = TRUE WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

    private SeguroVehicular mapSeguro(ResultSet rs) throws SQLException {
        SeguroVehicular s = new SeguroVehicular();

        s.setId(rs.getLong("id"));
        s.setAseguradora(rs.getString("aseguradora"));
        s.setNroPoliza(rs.getString("nro_poliza"));
        s.setCobertura(CoberturaEnum.valueOf(rs.getString("cobertura")));
        s.setVencimiento(rs.getDate("vencimiento").toLocalDate());
        s.setEliminado(rs.getBoolean("eliminado"));

        return s;
    }
}
