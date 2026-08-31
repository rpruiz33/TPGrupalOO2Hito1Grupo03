package test;
import dao.UnidadVentaDao;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dao.ResetBaseDao;
import datos.Cajero;
import datos.Cocinero;
import datos.Festival;
import datos.FoodTrack;
import datos.ItemPedido;
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
            CocineroABM cocineroABM = new CocineroABM();
            CajeroABM cajeroABM = new CajeroABM();
            PlatoABM platoABM = new PlatoABM();
            FoodTrackABM foodTrackABM = new FoodTrackABM();
            PuestoDesarmableABM puestoABM = new PuestoDesarmableABM();
            FestivalABM festivalABM = new FestivalABM();
            PedidoABM pedidoABM = new PedidoABM();
            ItemPedidoABM itemPedidoABM = new ItemPedidoABM();
            UnidadVentaABM unidadVentaABM = new UnidadVentaABM();

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

            System.out.println("\nCARGA DE DATOS FINALIZADA CORRECTAMENTE\n");

            System.out.println("// =========================================================================");
            System.out.println("// ---------- ESTUDIANTE RUIZ PEREIRA, ROBERTO ANDRES  ----------");
            System.out.println("// =========================================================================");
            System.out.println();
            System.out.println("// =========================================================================");
            System.out.println("// ---------- PRUEBA 1: CONSULTA (traerPuestosComplejos) ----------");
            System.out.println("// =========================================================================");
            System.out.println("=== PRUEBA DE CONSULTA  EN PUESTODESARMABLE ===");

            Set<PuestoDesarmable> puestosFiltrados = puestoABM.traerPuestosComplejos(1, 60, 5.0, 1);

            System.out.println("Cantidad de puestos encontrados: " + puestosFiltrados.size());
            for (PuestoDesarmable p : puestosFiltrados) {
                System.out.println("-> " + p.getNombreComercial()
                        + " | Carpas: " + p.getCantidadCarpas()
                        + " | Montaje: " + p.getTiempoMontajeMin() + " min"
                        + " | Superficie: " + p.getSuperficieM2() + " m2");
            }

            // =========================================================================
            // ---------- PRUEBA 2: CONSULTA POR STAFF DE FESTIVAL --------------------
            // =========================================================================
            System.out.println("\n=== PRUEBA DE UNIDADES DE VENTA POR STAFF DE FESTIVAL ===");

            System.out.println("\n--- CONSULTA: MÍNIMO 1 DE STAFF ---");
            Set<UnidadVenta> minStaff1 = festivalABM.traerUnidadesVentaPorStaffDeFestival(1);
            System.out.println("Cantidad de unidades encontradas: " + minStaff1.size());
            for (UnidadVenta u : minStaff1) {
                System.out.println("-> " + u.getNombreComercial() + " | Codigo: " + u.getCodigo());
            }

            System.out.println("\n--- CONSULTA: MÍNIMO 2 DE STAFF ---");
            Set<UnidadVenta> minStaff2 = festivalABM.traerUnidadesVentaPorStaffDeFestival(2);
            System.out.println("Cantidad de unidades encontradas: " + minStaff2.size());
            for (UnidadVenta u : minStaff2) {
                System.out.println("-> " + u.getNombreComercial() + " | Codigo: " + u.getCodigo());
            }

            System.out.println("\n--- CONSULTA: MÍNIMO 3 DE STAFF ---");
            Set<UnidadVenta> minStaff3 = festivalABM.traerUnidadesVentaPorStaffDeFestival(3);
            System.out.println("Cantidad de unidades encontradas: " + minStaff3.size());
            for (UnidadVenta u : minStaff3) {
                System.out.println("-> " + u.getNombreComercial() + " | Codigo: " + u.getCodigo());
            }

            System.out.println("// =========================================================================");
            System.out.println("// ---------- ESTUDIANTE Salvatierra, Federico Matias  ----------");
            System.out.println("// =========================================================================");
            System.out.println();
            System.out.println("// =========================================================================");
            System.out.println("// ---------- PRUEBA 2: CONSULTA (Cocineros por eventos) ----------");
            System.out.println("// =========================================================================");
            System.out.println("=== PRUEBA DE CONSULTA  EN  CocineroDao ===");

            System.out.println("\n=== PRUEBA DE COCINEROS ASIGNADOS A UN FESTIVAL POR RANGO DE FECHAS ===");

            LocalDate fechaInicioBusqueda = LocalDate.of(1900, 5, 1);
            LocalDate fechaFinBusqueda = LocalDate.of(2026, 9, 20);
            Long idFestival = idFestival1; // Usa la variable de festival ya generada en la carga

            System.out.println("\n--- CONSULTA: FESTIVAL ID " + idFestival + " ENTRE " + fechaInicioBusqueda + " Y " + fechaFinBusqueda + " ---");

            Set<Cocinero> cocinerosPorFecha = cocineroABM.traerCocinerosPorFestivalYFechas(idFestival, fechaInicioBusqueda, fechaFinBusqueda);

            System.out.println("Cantidad de cocineros encontrados: " + cocinerosPorFecha.size());

            for (Cocinero c : cocinerosPorFecha) {
                System.out.println("-> " + c.getNombre() + " " + c.getApellido() + " | Especialidad: " + c.getEspecialidad());
            }

            System.out.println("\n=== PRUEBA DE UNIDADES DE VENTA FILTRADAS POR DNI, NACIMIENTO E INGRESO ===");

            String dniBusqueda = "68555666";
            LocalDate nacBusqueda = LocalDate.of(1985, 9, 3); // Fecha ajustada al valor insertado arriba (idCocinero2)
            LocalDate ingBusqueda = LocalDate.of(2022, 3, 15);

            System.out.println("\n--- CONSULTA: BUSCANDO UNIDADES DEL STAFF CON DNI " + dniBusqueda + " ---");
            Set<UnidadVenta> unidadesPorStaff = unidadVentaABM.traerUnidadesVentaPorDatosStaff(dniBusqueda, nacBusqueda, ingBusqueda);

            System.out.println("Cantidad de unidades encontradas: " + unidadesPorStaff.size());

            for (UnidadVenta u : unidadesPorStaff) {
                System.out.println("-> " + u.getNombreComercial()
                                 + " | Codigo: " + u.getCodigo()
                                 + " | Superficie: " + u.getSuperficieM2() + "m2");
            }
            System.out.println("\n\n// ===============================================================================");
            System.out.println("// ----------    ESTUDIANTE SOLOAGA LEONEL     ----------");
            System.out.println("// =====================================================================================");
            System.out.println("// =====================================================================================");
            System.out.println("// -- PRUEBA 3: CONSULTA (PLATOS POR UNIDAD DE VENTA Y FOODTRACK CON ELECTRICIDAD) --");
            System.out.println("// =====================================================================================\n");

            long idUnidadVenta = idFoodTrack1;
            UnidadVenta unidad = unidadVentaABM.traerUnidadVentaConPlatos(idUnidadVenta);
            if (unidad == null) {
                System.out.println("No existe la unidad de venta con ID: " + idUnidadVenta);
            } else {
                System.out.println("Unidad: " + unidad.getNombreComercial());
                System.out.println("Código: " + unidad.getCodigo());
                System.out.println("Cantidad de platos encontrados: " + unidad.getPlatosOfrecidos().size());
                for (Plato p : unidad.getPlatosOfrecidos()) {
                    System.out.println("-> " + p.getNombre()
                            + " | Precio venta: $" + p.getPrecioVenta()
                            + " | Costo producción: $" + p.getCostoProduccion());
                }
            }
           /* System.out.println("\n// =========================================================================");
            System.out.println("// ---------- PRUEBA 2: CONSULTA (FOODTRACKS CON ELECTRICIDAD) ----------");
            System.out.println("// =========================================================================");*/
            System.out.println("\n=== CONSULTA DE FOODTRACKS QUE REQUIEREN ELECTRICIDAD ===");
            List<FoodTrack> foodTracks =   unidadVentaABM.traerFoodTracksConElectricidad(true);
            System.out.println("\nCantidad de FoodTracks encontrados: " + foodTracks.size());
            for (FoodTrack f : foodTracks) {
                System.out.println("-> ID: " + f.getId()
                        + " | Código: " + f.getCodigo()
                        + " | Nombre: " + f.getNombreComercial()
                        + " | Superficie: " + f.getSuperficieM2() + "m2"
                        + " | Patente: " + f.getPatente()
                        + " | Requiere electricidad: " + f.isRequiereElectricidad());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

