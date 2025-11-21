package segurosvehiculares.main;

import segurosvehiculares.entities.CoberturaEnum;
import segurosvehiculares.entities.SeguroVehicular;
import segurosvehiculares.entities.Vehiculo;
import segurosvehiculares.service.VehiculoService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class AppMenu {

    private final VehiculoService vehiculoService;
    private final Scanner scanner = new Scanner(System.in);

    public AppMenu(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    public void mostrarMenu() {
        System.out.println("===== MENU VEHICULOS / SEGUROS =====");
        System.out.println("1. Crear vehículo con seguro");
        System.out.println("2. Listar vehículos");
        System.out.println("3. Buscar vehículo por ID");
        System.out.println("4. Buscar vehículo por dominio");
        System.out.println("5. Actualizar vehículo y seguro");
        System.out.println("6. Eliminar vehículo (baja lógica)");
        System.out.println("0. Salir");
        System.out.print("Opción: ");
    }

    public void ejecutar() {
    int opcion;
    do {
        mostrarMenu();
        opcion = leerEntero();

        switch (opcion) {
            case 1:
                crearVehiculoConSeguro();
                break;
            case 2:
                listarVehiculos();
                break;
            case 3:
                buscarPorId();
                break;
            case 4:
                buscarPorDominio();
                break;
            case 5:
                actualizarVehiculo();
                break;
            case 6:
                eliminarVehiculo();
                break;
            case 0:
                System.out.println("Saliendo de la aplicación...");
                break;
            default:
                System.out.println("Opción inválida.");
        }

        System.out.println();
    } while (opcion != 0);
}

    private void crearVehiculoConSeguro() {
        try {
            System.out.println("--- Datos del vehículo ---");
            System.out.print("Dominio: ");
            String dominio = scanner.nextLine().trim().toUpperCase();

            System.out.print("Marca: ");
            String marca = scanner.nextLine().trim();

            System.out.print("Modelo: ");
            String modelo = scanner.nextLine().trim();

            System.out.print("Año (enter para dejar vacío): ");
            String anioStr = scanner.nextLine().trim();
            Integer anio = anioStr.isEmpty() ? null : Integer.parseInt(anioStr);

            System.out.print("Nro de chasis (enter si no tiene): ");
            String nroChasis = scanner.nextLine().trim();
            if (nroChasis.isEmpty()) nroChasis = null;

            Vehiculo vehiculo = new Vehiculo();
            vehiculo.setDominio(dominio);
            vehiculo.setMarca(marca);
            vehiculo.setModelo(modelo);
            vehiculo.setAnio(anio);
            vehiculo.setNroChasis(nroChasis);
            vehiculo.setEliminado(false);

            System.out.println("--- Datos del seguro ---");
            System.out.print("Aseguradora: ");
            String aseguradora = scanner.nextLine().trim();

            System.out.print("Número de póliza (enter si no tiene): ");
            String nroPoliza = scanner.nextLine().trim();
            if (nroPoliza.isEmpty()) nroPoliza = null;

            CoberturaEnum cobertura = leerCobertura();

            System.out.print("Vencimiento (YYYY-MM-DD): ");
            LocalDate vencimiento = leerFecha();

            SeguroVehicular seguro = new SeguroVehicular();
            seguro.setAseguradora(aseguradora);
            seguro.setNroPoliza(nroPoliza);
            seguro.setCobertura(cobertura);
            seguro.setVencimiento(vencimiento);
            seguro.setEliminado(false);

            vehiculoService.crearVehiculoConSeguro(vehiculo, seguro);

            System.out.println("Vehículo y seguro guardados correctamente.");
        } catch (NumberFormatException e) {
            System.out.println("Formato numérico inválido.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error de validación: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error de base de datos: " + e.getMessage());
        }
    }

    private void listarVehiculos() {
        try {
            List<Vehiculo> vehiculos = vehiculoService.getAll();
            if (vehiculos.isEmpty()) {
                System.out.println("No hay vehículos cargados.");
            } else {
                vehiculos.forEach(System.out::println);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar vehículos: " + e.getMessage());
        }
    }

    private void buscarPorId() {
        System.out.print("ID de vehículo: ");
        long id = leerLong();
        try {
            Vehiculo v = vehiculoService.getById(id);
            if (v != null) System.out.println(v);
            else System.out.println("No se encontró vehículo con ese ID.");
        } catch (SQLException e) {
            System.out.println("Error al buscar vehículo: " + e.getMessage());
        }
    }

    private void buscarPorDominio() {
        System.out.print("Dominio: ");
        String dominio = scanner.nextLine().trim().toUpperCase();
        try {
            Vehiculo v = vehiculoService.buscarPorDominio(dominio);
            if (v != null) System.out.println(v);
            else System.out.println("No se encontró vehículo con ese dominio.");
        } catch (SQLException e) {
            System.out.println("Error al buscar vehículo: " + e.getMessage());
        }
    }

    private void actualizarVehiculo() {
        System.out.print("ID de vehículo a actualizar: ");
        long id = leerLong();

        try {
            Vehiculo v = vehiculoService.getById(id);
            if (v == null) {
                System.out.println("No existe un vehículo con ese ID.");
                return;
            }

            System.out.println("Datos actuales: " + v);

            System.out.print("Nuevo dominio (" + v.getDominio() + "): ");
            String dominio = scanner.nextLine().trim();
            if (!dominio.isEmpty()) v.setDominio(dominio.toUpperCase());

            System.out.print("Nueva marca (" + v.getMarca() + "): ");
            String marca = scanner.nextLine().trim();
            if (!marca.isEmpty()) v.setMarca(marca);

            System.out.print("Nuevo modelo (" + v.getModelo() + "): ");
            String modelo = scanner.nextLine().trim();
            if (!modelo.isEmpty()) v.setModelo(modelo);

            System.out.print("Nuevo año (" + v.getAnio() + ") (enter para dejar): ");
            String anioStr = scanner.nextLine().trim();
            if (!anioStr.isEmpty()) v.setAnio(Integer.parseInt(anioStr));

            System.out.print("Nuevo nro de chasis (" + v.getNroChasis() + "): ");
            String nroChasis = scanner.nextLine().trim();
            if (!nroChasis.isEmpty()) v.setNroChasis(nroChasis);

            vehiculoService.actualizar(v);
            System.out.println("Vehículo actualizado correctamente.");
        } catch (Exception e) {
            System.out.println("Error al actualizar vehículo: " + e.getMessage());
        }
    }

    private void eliminarVehiculo() {
        System.out.print("ID de vehículo a eliminar (baja lógica): ");
        long id = leerLong();
        try {
            vehiculoService.eliminar(id);
            System.out.println("Vehículo eliminado lógicamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar vehículo: " + e.getMessage());
        }
    }

    private int leerEntero() {
        while (true) {
            String linea = scanner.nextLine();
            try {
                return Integer.parseInt(linea.trim());
            } catch (NumberFormatException e) {
                System.out.print("Número inválido. Intente de nuevo: ");
            }
        }
    }

    private long leerLong() {
        while (true) {
            String linea = scanner.nextLine();
            try {
                return Long.parseLong(linea.trim());
            } catch (NumberFormatException e) {
                System.out.print("Número inválido. Intente de nuevo: ");
            }
        }
    }

    private LocalDate leerFecha() {
        while (true) {
            String linea = scanner.nextLine().trim();
            try {
                return LocalDate.parse(linea);
            } catch (Exception e) {
                System.out.print("Fecha inválida. Formato esperado YYYY-MM-DD: ");
            }
        }
    }

    private CoberturaEnum leerCobertura() {
        while (true) {
            System.out.println("Tipo de cobertura:");
            CoberturaEnum[] valores = CoberturaEnum.values();
            for (int i = 0; i < valores.length; i++) {
                System.out.println((i + 1) + " - " + valores[i].name());
            }
            System.out.print("Opción: ");

            int opcion = leerEntero();
            if (opcion >= 1 && opcion <= valores.length) {
                return valores[opcion - 1];
            }
            System.out.println("Opción inválida.");
        }
    }
}
