package segurosvehiculares.main;

import segurosvehiculares.dao.SeguroVehicularDao;
import segurosvehiculares.dao.VehiculoDao;
import segurosvehiculares.service.VehiculoService;

public class MainApp {

    public static void main(String[] args) {
        System.setProperty("file.encoding", "UTF-8");

        VehiculoDao vehiculoDao = new VehiculoDao();
        SeguroVehicularDao seguroDao = new SeguroVehicularDao();

        VehiculoService vehiculoService =
                new VehiculoService(vehiculoDao, seguroDao);

        AppMenu menu = new AppMenu(vehiculoService);
        menu.ejecutar();
    }
}
