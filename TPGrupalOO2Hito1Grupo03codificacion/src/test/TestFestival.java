package test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dao.ResetBaseDao;
import datos.Cajero;
import datos.Cocinero;
import datos.Festival;
import datos.FoodTrack;
import datos.Pedido;
import datos.Plato;
import datos.PuestoDesarmable;
import datos.Staff;
import datos.UnidadVenta;
import negocio.CajeroABM;
import negocio.CocineroABM;
import negocio.FestivalABM;
import negocio.FoodTrackABM;
import negocio.ItemPedidoABM;
import negocio.PedidoABM;
import negocio.PlatoABM;
import negocio.PuestoDesarmableABM;
import negocio.UnidadVentaABM;

public class TestFestival {

    public static void main(String[] args) {
    	try {
    	    ResetBaseDao.resetearBase();
    	    CocineroABM cocineroABM = CocineroABM.getInstancia();
    	    CajeroABM cajeroABM = CajeroABM.getInstancia();
    	    PlatoABM platoABM = PlatoABM.getInstancia();
    	    FoodTrackABM foodTrackABM = FoodTrackABM.getInstancia();
    	    PuestoDesarmableABM puestoABM = PuestoDesarmableABM.getInstancia();
    	    FestivalABM festivalABM = FestivalABM.getInstancia();
    	    PedidoABM pedidoABM = PedidoABM.getInstancia();
    	    ItemPedidoABM itemPedidoABM = ItemPedidoABM.getInstancia();
    	    UnidadVentaABM unidadVentaABM = UnidadVentaABM.getInstancia();

    	    // --------- Staff ---------

            // ---------- Staff ----------
            long idCocinero1 = cocineroABM.agregar("Lucia", "Fernandez", "40111222",
                    LocalDate.of(1990, 4, 12), LocalDate.of(2023, 10, 1), 450000.0, "Parrilla");
            long idCocinero2 = cocineroABM.agregar("Martin", "Gomez", "68555666",
                    LocalDate.of(1985, 9, 3), LocalDate.of(2022, 3, 15), 470000.0, "Reposteria");
            long idCocinero3 = cocineroABM.agregar("Roberto", "Sanchez", "32999888",
                    LocalDate.of(1988, 11, 5), LocalDate.of(2021, 5, 10), 500000.0, "Comida Minuta");

            long idCajero1 = cajeroABM.agregar("Ana", "Lopez", "65222111",
                    LocalDate.of(1998, 1, 20), LocalDate.of(2024, 6, 1), 380000.0, "manana");
            long idCajero2 = cajeroABM.agregar("Diego", "Perez", "82444333",
                    LocalDate.of(1995, 7, 8), LocalDate.of(2023, 11, 20), 390000.0, "noche");
            long idCajero3 = cajeroABM.agregar("Sofia", "Ramirez", "71333444",
                    LocalDate.of(2000, 3, 14), LocalDate.of(2024, 2, 1), 370000.0, "tarde");

            Cocinero cocinero1 = cocineroABM.traer(idCocinero1);
            Cocinero cocinero2 = cocineroABM.traer(idCocinero2);
            Cocinero cocinero3 = cocineroABM.traer(idCocinero3);
            Cajero cajero1 = cajeroABM.traer(idCajero1);
            Cajero cajero2 = cajeroABM.traer(idCajero2);
            Cajero cajero3 = cajeroABM.traer(idCajero3);

            System.out.println("Staff creado: " + cocinero1 + " | " + cocinero2 + " | " + cocinero3 + " | "
                    + cajero1 + " | " + cajero2 + " | " + cajero3);

            // ---------- Platos ----------
            long idPlato1 = platoABM.agregar("Choripan", 3500.0, 1200.0);
            long idPlato2 = platoABM.agregar("Hamburguesa Completa", 6800.0, 2600.0);
            long idPlato3 = platoABM.agregar("Torta Frita", 2000.0, 600.0);
            long idPlato4 = platoABM.agregar("Papas Rusticas", 3000.0, 900.0);
            long idPlato5 = platoABM.agregar("Emponadas de Carne", 2500.0, 800.0);

            Plato plato1 = platoABM.traer(idPlato1);
            Plato plato2 = platoABM.traer(idPlato2);
            Plato plato3 = platoABM.traer(idPlato3);
            Plato plato4 = platoABM.traer(idPlato4);
            Plato plato5 = platoABM.traer(idPlato5);

            System.out.println("Platos creados: " + plato1 + " | " + plato2 + " | " + plato3 + " | " + plato4 + " | " + plato5);

            // ---------- Unidades de venta ----------
            long idFoodTrack1 = foodTrackABM.agregar("FT00000001", "El Rincon del Choripan", 18.5,
                    cocinero1, "AB123CD", true);
            long idPuesto1 = puestoABM.agregar("PD00000001", "Dulces de la Feria", 9.0,
                    cajero1, 2, 45);
            long idFoodTrack2 = foodTrackABM.agregar("FT00000002", "Papas Y Bebidas", 12.0,
                    cocinero3, "CD456EF", false);
            long idPuesto2 = puestoABM.agregar("PD00000002", "Puesto de Jugos Naturales", 15.0,
                    cajero3, 3, 30);
            long idPuesto3 = puestoABM.agregar("PD00000003", "Empanadas Artesanales", 8.0,
                    cajero2, 1, 25);

            // Asignaciones de Staff y Platos
            foodTrackABM.asignarStaff(idFoodTrack1, cocinero1);
            foodTrackABM.asignarStaff(idFoodTrack1, cajero2);
            foodTrackABM.ofrecerPlato(idFoodTrack1, plato1);
            foodTrackABM.ofrecerPlato(idFoodTrack1, plato2);

            puestoABM.asignarStaff(idPuesto1, cajero1);
            puestoABM.asignarStaff(idPuesto1, cocinero2);
            puestoABM.ofrecerPlato(idPuesto1, plato3);

            foodTrackABM.asignarStaff(idFoodTrack2, cocinero3);
            foodTrackABM.asignarStaff(idFoodTrack2, cajero3);
            foodTrackABM.ofrecerPlato(idFoodTrack2, plato4);

            puestoABM.asignarStaff(idPuesto2, cajero3);
            puestoABM.ofrecerPlato(idPuesto2, plato2);

            puestoABM.asignarStaff(idPuesto3, cajero2);
            puestoABM.ofrecerPlato(idPuesto3, plato5);

            FoodTrack foodTrack1 = foodTrackABM.traer(idFoodTrack1);
            PuestoDesarmable puesto1 = puestoABM.traer(idPuesto1);
            FoodTrack foodTrack2 = foodTrackABM.traer(idFoodTrack2);
            PuestoDesarmable puesto2 = puestoABM.traer(idPuesto2);
            PuestoDesarmable puesto3 = puestoABM.traer(idPuesto3);

            System.out.println("Unidades creadas: " + foodTrack1 + " | " + puesto1 + " | " + foodTrack2 + " | " + puesto2 + " | " + puesto3);

            // ---------- Festivales ----------
            Set<UnidadVenta> unidadesFestival1 = new HashSet<>();
            unidadesFestival1.add(foodTrack1);
            unidadesFestival1.add(puesto1);
            unidadesFestival1.add(puesto3);
            Set<Staff> staffFestival1 = new HashSet<>();
            staffFestival1.add(cajero1);
            staffFestival1.add(cajero2);
            staffFestival1.add(cocinero3);

            long idFestival1 = festivalABM.agregar("Mega Rock Festival", "Primavera",
                    LocalDate.of(2026, 9, 15), LocalDate.of(2026, 9, 20),
                    unidadesFestival1, staffFestival1);

            Set<UnidadVenta> unidadesFestival2 = new HashSet<>();
            unidadesFestival2.add(puesto2);
            unidadesFestival2.add(foodTrack2);
            Set<Staff> staffFestival2 = new HashSet<>();
            staffFestival2.add(cajero2);
            staffFestival2.add(cajero3);

            long idFestival2 = festivalABM.agregar("Feria de Verano", "Verano",
                    LocalDate.of(2026, 1, 10), LocalDate.of(2026, 1, 20),
                    unidadesFestival2, staffFestival2);

            Set<UnidadVenta> unidadesFestival3 = new HashSet<>();
            unidadesFestival3.add(foodTrack1);
            Set<Staff> staffFestival3 = new HashSet<>();
            staffFestival3.add(cocinero1);

            long idFestival3 = festivalABM.agregar("Festival Gastronomico", "Otono",
                    LocalDate.of(2026, 4, 5), LocalDate.of(2026, 4, 12),
                    unidadesFestival3, staffFestival3);

            Festival festival1 = festivalABM.traer(idFestival1);
            Festival festival2 = festivalABM.traer(idFestival2);
            Festival festival3 = festivalABM.traer(idFestival3);

            System.out.println("Festivales creados: " + festival1 + " | " + festival2 + " | " + festival3);

            // ---------- Pedidos + Items ----------
            long idPedido1 = pedidoABM.agregar(LocalDate.of(2026, 9, 16), festival1, foodTrack1);
            Pedido pedido1 = pedidoABM.traer(idPedido1);
            itemPedidoABM.agregar(pedido1, plato1, 3, plato1.getPrecioVenta());
            itemPedidoABM.agregar(pedido1, plato2, 1, plato2.getPrecioVenta());

            long idPedido2 = pedidoABM.agregar(LocalDate.of(2026, 1, 12), festival2, puesto1);
            Pedido pedido2 = pedidoABM.traer(idPedido2);
            itemPedidoABM.agregar(pedido2, plato3, 2, plato3.getPrecioVenta());

            System.out.println("Pedido 1 con items: " + pedidoABM.traerConItems(idPedido1));
            System.out.println("Pedido 2 con items: " + pedidoABM.traerConItems(idPedido2));

            // ---------- DATOS ADICIONALES ----------

            // ---------- Staff adicional ----------
            long idCocinero4 = cocineroABM.agregar("Valentina", "Torres", "39222111",
                    LocalDate.of(1992, 6, 18), LocalDate.of(2023, 8, 5), 460000.0, "Panaderia");
            long idCocinero5 = cocineroABM.agregar("Julian", "Diaz", "35777888",
                    LocalDate.of(1987, 2, 25), LocalDate.of(2021, 9, 12), 480000.0, "Vegetariana");
            long idCocinero6 = cocineroABM.agregar("Camila", "Herrera", "41666555",
                    LocalDate.of(1994, 10, 30), LocalDate.of(2024, 1, 15), 440000.0, "Postres");

            long idCajero4 = cajeroABM.agregar("Nicolas", "Molina", "38111999",
                    LocalDate.of(1996, 5, 22), LocalDate.of(2023, 4, 10), 385000.0, "manana");
            long idCajero5 = cajeroABM.agregar("Florencia", "Castro", "42888777",
                    LocalDate.of(1999, 9, 9), LocalDate.of(2024, 7, 1), 375000.0, "tarde");
            long idCajero6 = cajeroABM.agregar("Tomas", "Rivas", "37555444",
                    LocalDate.of(1993, 12, 3), LocalDate.of(2022, 10, 20), 395000.0, "noche");

            Cocinero cocinero4 = cocineroABM.traer(idCocinero4);
            Cocinero cocinero5 = cocineroABM.traer(idCocinero5);
            Cocinero cocinero6 = cocineroABM.traer(idCocinero6);
            Cajero cajero4 = cajeroABM.traer(idCajero4);
            Cajero cajero5 = cajeroABM.traer(idCajero5);
            Cajero cajero6 = cajeroABM.traer(idCajero6);

            System.out.println("Staff adicional creado: " + cocinero4 + " | " + cocinero5 + " | " + cocinero6 + " | "
                    + cajero4 + " | " + cajero5 + " | " + cajero6);

            // ---------- Platos adicionales ----------
            long idPlato6 = platoABM.agregar("Pizza Artesanal", 5500.0, 2000.0);
            long idPlato7 = platoABM.agregar("Milanesa Napolitana", 7200.0, 2800.0);
            long idPlato8 = platoABM.agregar("Ensalada Fresca", 2800.0, 900.0);
            long idPlato9 = platoABM.agregar("Alfajor Casero", 1800.0, 500.0);
            long idPlato10 = platoABM.agregar("Limonada Natural", 1500.0, 400.0);

            Plato plato6 = platoABM.traer(idPlato6);
            Plato plato7 = platoABM.traer(idPlato7);
            Plato plato8 = platoABM.traer(idPlato8);
            Plato plato9 = platoABM.traer(idPlato9);
            Plato plato10 = platoABM.traer(idPlato10);

            System.out.println("Platos adicionales creados: " + plato6 + " | " + plato7 + " | " + plato8 + " | " + plato9 + " | " + plato10);

            // ---------- Unidades de venta adicionales ----------
            long idFoodTrack3 = foodTrackABM.agregar("FT00000003", "La Pizzeria Rodante", 20.0,
                    cocinero4, "EF789GH", true);
            long idFoodTrack4 = foodTrackABM.agregar("FT00000004", "Milanesas Express", 16.0,
                    cocinero5, "GH012IJ", true);
            long idPuesto4 = puestoABM.agregar("PD00000004", "Rincon Saludable", 7.5,
                    cajero4, 2, 35);
            long idPuesto5 = puestoABM.agregar("PD00000005", "Dulces y Bebidas", 6.0,
                    cajero5, 1, 20);

            foodTrackABM.asignarStaff(idFoodTrack3, cocinero4);
            foodTrackABM.asignarStaff(idFoodTrack3, cajero4);
            foodTrackABM.asignarStaff(idFoodTrack3, cajero6);
            foodTrackABM.ofrecerPlato(idFoodTrack3, plato6);

            foodTrackABM.asignarStaff(idFoodTrack4, cocinero5);
            foodTrackABM.asignarStaff(idFoodTrack4, cajero5);
            foodTrackABM.ofrecerPlato(idFoodTrack4, plato7);
            foodTrackABM.ofrecerPlato(idFoodTrack4, plato8);

            puestoABM.asignarStaff(idPuesto4, cajero4);
            puestoABM.asignarStaff(idPuesto4, cocinero6);
            puestoABM.ofrecerPlato(idPuesto4, plato8);

            puestoABM.asignarStaff(idPuesto5, cajero5);
            puestoABM.asignarStaff(idPuesto5, cajero6);
            puestoABM.ofrecerPlato(idPuesto5, plato9);
            puestoABM.ofrecerPlato(idPuesto5, plato10);

            FoodTrack foodTrack3 = foodTrackABM.traer(idFoodTrack3);
            FoodTrack foodTrack4 = foodTrackABM.traer(idFoodTrack4);
            PuestoDesarmable puesto4 = puestoABM.traer(idPuesto4);
            PuestoDesarmable puesto5 = puestoABM.traer(idPuesto5);

            System.out.println("Unidades adicionales creadas: " + foodTrack3 + " | " + foodTrack4 + " | " + puesto4 + " | " + puesto5);

            // ---------- Festivales adicionales ----------
            Set<UnidadVenta> unidadesFestival4 = new HashSet<>();
            unidadesFestival4.add(foodTrack3);
            unidadesFestival4.add(puesto4);
            Set<Staff> staffFestival4 = new HashSet<>();
            staffFestival4.add(cocinero4);
            staffFestival4.add(cajero4);
            staffFestival4.add(cajero6);

            long idFestival4 = festivalABM.agregar("Festival de Invierno", "Invierno",
                    LocalDate.of(2026, 7, 1), LocalDate.of(2026, 7, 7),
                    unidadesFestival4, staffFestival4);

            Set<UnidadVenta> unidadesFestival5 = new HashSet<>();
            unidadesFestival5.add(foodTrack4);
            unidadesFestival5.add(puesto5);
            unidadesFestival5.add(puesto2);
            Set<Staff> staffFestival5 = new HashSet<>();
            staffFestival5.add(cocinero5);
            staffFestival5.add(cajero5);
            staffFestival5.add(cajero6);
            staffFestival5.add(cocinero6);

            long idFestival5 = festivalABM.agregar("Encuentro Gourmet", "Primavera",
                    LocalDate.of(2026, 10, 1), LocalDate.of(2026, 10, 5),
                    unidadesFestival5, staffFestival5);

            Festival festival4 = festivalABM.traer(idFestival4);
            Festival festival5 = festivalABM.traer(idFestival5);

            System.out.println("Festivales adicionales creados: " + festival4 + " | " + festival5);

            // ---------- Pedidos + Items adicionales ----------
            long idPedido3 = pedidoABM.agregar(LocalDate.of(2026, 9, 17), festival1, puesto3);
            Pedido pedido3 = pedidoABM.traer(idPedido3);
            itemPedidoABM.agregar(pedido3, plato5, 4, plato5.getPrecioVenta());

            long idPedido4 = pedidoABM.agregar(LocalDate.of(2026, 7, 2), festival4, foodTrack3);
            Pedido pedido4 = pedidoABM.traer(idPedido4);
            itemPedidoABM.agregar(pedido4, plato6, 2, plato6.getPrecioVenta());

            long idPedido5 = pedidoABM.agregar(LocalDate.of(2026, 7, 3), festival4, puesto4);
            Pedido pedido5 = pedidoABM.traer(idPedido5);
            itemPedidoABM.agregar(pedido5, plato8, 3, plato8.getPrecioVenta());

            long idPedido6 = pedidoABM.agregar(LocalDate.of(2026, 10, 2), festival5, foodTrack4);
            Pedido pedido6 = pedidoABM.traer(idPedido6);
            itemPedidoABM.agregar(pedido6, plato7, 1, plato7.getPrecioVenta());
            itemPedidoABM.agregar(pedido6, plato8, 2, plato8.getPrecioVenta());

            long idPedido7 = pedidoABM.agregar(LocalDate.of(2026, 10, 3), festival5, puesto5);
            Pedido pedido7 = pedidoABM.traer(idPedido7);
            itemPedidoABM.agregar(pedido7, plato9, 5, plato9.getPrecioVenta());
            itemPedidoABM.agregar(pedido7, plato10, 5, plato10.getPrecioVenta());

            long idPedido8 = pedidoABM.agregar(LocalDate.of(2026, 1, 15), festival2, foodTrack2);
            Pedido pedido8 = pedidoABM.traer(idPedido8);
            itemPedidoABM.agregar(pedido8, plato4, 2, plato4.getPrecioVenta());

            System.out.println("Pedido 3 con items: " + pedidoABM.traerConItems(idPedido3));
            System.out.println("Pedido 4 con items: " + pedidoABM.traerConItems(idPedido4));
            System.out.println("Pedido 5 con items: " + pedidoABM.traerConItems(idPedido5));
            System.out.println("Pedido 6 con items: " + pedidoABM.traerConItems(idPedido6));
            System.out.println("Pedido 7 con items: " + pedidoABM.traerConItems(idPedido7));
            System.out.println("Pedido 8 con items: " + pedidoABM.traerConItems(idPedido8));

            System.out.println("\nCARGA DE DATOS FINALIZADA CORRECTAMENTE\n");

            // =========================================================================
            // ---------- EJECUCIÓN DE CONSULTAS ----------
            // =========================================================================

            System.out.println("==============================================================");
            System.out.println("---------- ESTUDIANTE RUIZ PEREIRA, ROBERTO ANDRES -----------");
            System.out.println("==============================================================");

            System.out.println("\n--- CONSULTA 1: PUESTOS DESARMABLES FILTRADOS ---");
            Set<PuestoDesarmable> puestosFiltrados = puestoABM.traerPuestos(1, 60, 5.0, 1);
            System.out.println("Puestos encontrados: " + puestosFiltrados.size());
            for (PuestoDesarmable p : puestosFiltrados) {
                System.out.println("-> " + p.getNombreComercial()
                        + " | Carpas: " + p.getCantidadCarpas()
                        + " | Montaje: " + p.getTiempoMontajeMin() + " min"
                        + " | Superficie: " + p.getSuperficieM2() + " m2");
            }

            System.out.println("\n--- CONSULTA 2: UNIDADES DE VENTA POR MÍNIMO 1 DE STAFF DE FESTIVAL ---");
            Set<UnidadVenta> minStaff1 = festivalABM.traerUnidadesVentaPorStaffDeFestival(1);
            System.out.println("Unidades encontradas: " + minStaff1.size());
            for (UnidadVenta u : minStaff1) {
                System.out.println("-> " + u.getNombreComercial() + " | Código: " + u.getCodigo());
            }

            System.out.println("\n--- CONSULTA 3: UNIDADES DE VENTA POR MÍNIMO 2 DE STAFF DE FESTIVAL ---");
            Set<UnidadVenta> minStaff2 = festivalABM.traerUnidadesVentaPorStaffDeFestival(2);
            System.out.println("Unidades encontradas: " + minStaff2.size());
            for (UnidadVenta u : minStaff2) {
                System.out.println("-> " + u.getNombreComercial() + " | Código: " + u.getCodigo());
            }

            System.out.println("\n--- CONSULTA 4: UNIDADES DE VENTA POR MÍNIMO 3 DE STAFF DE FESTIVAL ---");
            Set<UnidadVenta> minStaff3 = festivalABM.traerUnidadesVentaPorStaffDeFestival(3);
            System.out.println("Unidades encontradas: " + minStaff3.size());
            for (UnidadVenta u : minStaff3) {
                System.out.println("-> " + u.getNombreComercial() + " | Código: " + u.getCodigo());
            }

            System.out.println("\n===========================================================");
            System.out.println("---------- ESTUDIANTE SALVATIERRA, FEDERICO MATIAS ----------");
            System.out.println("=============================================================");

            System.out.println("\n--- CONSULTA 1: COCINEROS ASIGNADOS A FESTIVAL POR RANGO DE FECHAS ---");
            LocalDate fechaInicioBusqueda = LocalDate.of(1900, 5, 1);
            LocalDate fechaFinBusqueda = LocalDate.of(2026, 9, 20);
            Long idFestival = idFestival1;

            Set<Cocinero> cocinerosPorFecha = cocineroABM.traerCocinerosPorFestivalYFechas(idFestival, fechaInicioBusqueda, fechaFinBusqueda);
            System.out.println("Cocineros encontrados: " + cocinerosPorFecha.size());
            for (Cocinero c : cocinerosPorFecha) {
                System.out.println("-> " + c.getNombre() + " " + c.getApellido() + " | Especialidad: " + c.getEspecialidad());
            }

            System.out.println("\n--- CONSULTA 2: UNIDADES DE VENTA FILTRADAS POR DATOS DE STAFF ---");
            String dniBusqueda = "68555666";
            LocalDate nacBusqueda = LocalDate.of(1985, 9, 3);
            LocalDate ingBusqueda = LocalDate.of(2022, 3, 15);

            Set<UnidadVenta> unidadesPorStaff = unidadVentaABM.traerUnidadesVentaPorDatosStaff(dniBusqueda, nacBusqueda, ingBusqueda);
            System.out.println("Unidades encontradas: " + unidadesPorStaff.size());
            for (UnidadVenta u : unidadesPorStaff) {
                System.out.println("-> " + u.getNombreComercial()
                        + " | Código: " + u.getCodigo()
                        + " | Superficie: " + u.getSuperficieM2() + " m2");
            }

            System.out.println("\n==============================================");
            System.out.println("---------- ESTUDIANTE SOLOAGA, LEONEL ----------");
            System.out.println("================================================");

            System.out.println("\n--- CONSULTA 1: PLATOS POR UNIDAD DE VENTA ---");
            long idUnidadVenta = idFoodTrack1;
            UnidadVenta unidad = unidadVentaABM.traerUnidadVentaConPlatos(idUnidadVenta);
            if (unidad == null) {
                System.out.println("No existe la unidad de venta con ID: " + idUnidadVenta);
            } else {
                System.out.println("Unidad: " + unidad.getNombreComercial() + " | Código: " + unidad.getCodigo());
                System.out.println("Platos encontrados: " + unidad.getPlatosOfrecidos().size());
                for (Plato p : unidad.getPlatosOfrecidos()) {
                    System.out.println("-> " + p.getNombre()
                            + " | Precio venta: $" + p.getPrecioVenta()
                            + " | Costo producción: $" + p.getCostoProduccion());
                }
            }

            System.out.println("\n--- CONSULTA 2: FOODTRACKS QUE REQUIEREN ELECTRICIDAD ---");
            List<FoodTrack> foodTracks = unidadVentaABM.traerFoodTracksConElectricidad(true);
            System.out.println("FoodTracks encontrados: " + foodTracks.size());
            for (FoodTrack f : foodTracks) {
                System.out.println("-> " + f.getNombreComercial()
                        + " | Código: " + f.getCodigo()
                        + " | Superficie: " + f.getSuperficieM2() + " m2"
                        + " | Patente: " + f.getPatente()
                        + " | Requiere electricidad: " + f.isRequiereElectricidad());
            }

            System.out.println("\n================================================");
            System.out.println("---------- ESTUDIANTE ANA BELEN VAZQUEZ ----------");
            System.out.println("==================================================");

            System.out.println("\n--- CONSULTA 1: FOODTRACKS CON SUPERFICIE MINIMA ---");
            List<FoodTrack> foodTracksSuperficie = unidadVentaABM.traerFoodTracksConSuperficieMinima(10.0);
            System.out.println("FoodTracks encontrados: " + foodTracksSuperficie.size());
            for (FoodTrack f : foodTracksSuperficie) {
                System.out.println("-> " + f.getNombreComercial()
                        + " | Código: " + f.getCodigo()
                        + " | Superficie: " + f.getSuperficieM2() + " m2"
                        + " | Patente: " + f.getPatente());
            }

            System.out.println("\n--- CONSULTA 2: UNIDADES DE VENTA CON MÍNIMO DE PEDIDOS ---");
            List<UnidadVenta> unidadesConPedidos = unidadVentaABM.traerUnidadesVentaConMinimoPedidos(1);
            System.out.println("Unidades encontradas: " + unidadesConPedidos.size());
            for (UnidadVenta u : unidadesConPedidos) {
                System.out.println("-> " + u.getNombreComercial()
                        + " | Código: " + u.getCodigo());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}