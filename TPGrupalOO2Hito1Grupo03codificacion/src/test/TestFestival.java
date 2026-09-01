package test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dao.ResetBaseDao;
import datos.*;
import negocio.*;

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

            // 1. COCINEROS
            long[] idsC = {
                cocineroABM.agregar("Lucia", "Fernandez", "40111222", LocalDate.of(1990, 4, 12), LocalDate.of(2023, 10, 1), 450000.0, "Parrilla"),
                cocineroABM.agregar("Martin", "Gomez", "68555666", LocalDate.of(1985, 9, 3), LocalDate.of(2022, 3, 15), 470000.0, "Reposteria"),
                cocineroABM.agregar("Roberto", "Sanchez", "32999888", LocalDate.of(1988, 11, 5), LocalDate.of(2021, 5, 10), 500000.0, "Comida Minuta"),
                cocineroABM.agregar("Valentina", "Torres", "39222111", LocalDate.of(1992, 6, 18), LocalDate.of(2023, 8, 5), 460000.0, "Panaderia"),
                cocineroABM.agregar("Julian", "Diaz", "35777888", LocalDate.of(1987, 2, 25), LocalDate.of(2021, 9, 12), 480000.0, "Vegetariana"),
                cocineroABM.agregar("Camila", "Herrera", "41666555", LocalDate.of(1994, 10, 30), LocalDate.of(2024, 1, 15), 440000.0, "Postres")
            };
            Cocinero[] c = new Cocinero[6];
            for (int i = 0; i < 6; i++) c[i] = cocineroABM.traer(idsC[i]);
            System.out.println("Cocineros creados: " + Arrays.toString(c));

            // 2. CAJEROS
            long[] idsCaj = {
                cajeroABM.agregar("Ana", "Lopez", "65222111", LocalDate.of(1998, 1, 20), LocalDate.of(2024, 6, 1), 380000.0, "manana"),
                cajeroABM.agregar("Diego", "Perez", "82444333", LocalDate.of(1995, 7, 8), LocalDate.of(2023, 11, 20), 390000.0, "noche"),
                cajeroABM.agregar("Sofia", "Ramirez", "71333444", LocalDate.of(2000, 3, 14), LocalDate.of(2024, 2, 1), 370000.0, "tarde"),
                cajeroABM.agregar("Nicolas", "Molina", "38111999", LocalDate.of(1996, 5, 22), LocalDate.of(2023, 4, 10), 385000.0, "manana"),
                cajeroABM.agregar("Florencia", "Castro", "42888777", LocalDate.of(1999, 9, 9), LocalDate.of(2024, 7, 1), 375000.0, "tarde"),
                cajeroABM.agregar("Tomas", "Rivas", "37555444", LocalDate.of(1993, 12, 3), LocalDate.of(2022, 10, 20), 395000.0, "noche")
            };
            Cajero[] caj = new Cajero[6];
            for (int i = 0; i < 6; i++) caj[i] = cajeroABM.traer(idsCaj[i]);
            System.out.println("Cajeros creados: " + Arrays.toString(caj));

            // 3. PLATOS
            long[] idsP = {
                platoABM.agregar("Choripan", 3500.0, 1200.0), platoABM.agregar("Hamburguesa Completa", 6800.0, 2600.0),
                platoABM.agregar("Torta Frita", 2000.0, 600.0), platoABM.agregar("Papas Rusticas", 3000.0, 900.0),
                platoABM.agregar("Emponadas de Carne", 2500.0, 800.0), platoABM.agregar("Pizza Artesanal", 5500.0, 2000.0),
                platoABM.agregar("Milanesa Napolitana", 7200.0, 2800.0), platoABM.agregar("Ensalada Fresca", 2800.0, 900.0),
                platoABM.agregar("Alfajor Casero", 1800.0, 500.0), platoABM.agregar("Limonada Natural", 1500.0, 400.0)
            };
            Plato[] p = new Plato[10];
            for (int i = 0; i < 10; i++) p[i] = platoABM.traer(idsP[i]);
            System.out.println("Platos creados: " + Arrays.toString(p));

            // 4. FOODTRACKS
            long[] idsFT = {
                foodTrackABM.agregar("FT00000001", "El Rincon del Choripan", 18.5, c[0], "AB123CD", true),
                foodTrackABM.agregar("FT00000002", "Papas Y Bebidas", 12.0, c[2], "CD456EF", false),
                foodTrackABM.agregar("FT00000003", "La Pizzeria Rodante", 20.0, c[3], "EF789GH", true),
                foodTrackABM.agregar("FT00000004", "Milanesas Express", 16.0, c[4], "GH012IJ", true)
            };

            foodTrackABM.asignarStaff(idsFT[0], c[0]); foodTrackABM.asignarStaff(idsFT[0], caj[1]);
            foodTrackABM.ofrecerPlato(idsFT[0], p[0]); foodTrackABM.ofrecerPlato(idsFT[0], p[1]);

            foodTrackABM.asignarStaff(idsFT[1], c[2]); foodTrackABM.asignarStaff(idsFT[1], caj[2]);
            foodTrackABM.ofrecerPlato(idsFT[1], p[3]);

            foodTrackABM.asignarStaff(idsFT[2], c[3]); foodTrackABM.asignarStaff(idsFT[2], caj[3]); foodTrackABM.asignarStaff(idsFT[2], caj[5]);
            foodTrackABM.ofrecerPlato(idsFT[2], p[5]);

            foodTrackABM.asignarStaff(idsFT[3], c[4]); foodTrackABM.asignarStaff(idsFT[3], caj[4]);
            foodTrackABM.ofrecerPlato(idsFT[3], p[6]); foodTrackABM.ofrecerPlato(idsFT[3], p[7]);

            FoodTrack[] ft = new FoodTrack[4];
            for (int i = 0; i < 4; i++) ft[i] = foodTrackABM.traer(idsFT[i]);
            System.out.println("FoodTracks creados: " + Arrays.toString(ft));

            // 5. PUESTOS DESARMABLES
            long[] idsPD = {
                puestoABM.agregar("PD00000001", "Dulces de la Feria", 9.0, caj[0], 2, 45),
                puestoABM.agregar("PD00000002", "Puesto de Jugos Naturales", 15.0, caj[2], 3, 30),
                puestoABM.agregar("PD00000003", "Empanadas Artesanales", 8.0, caj[1], 1, 25),
                puestoABM.agregar("PD00000004", "Rincon Saludable", 7.5, caj[3], 2, 35),
                puestoABM.agregar("PD00000005", "Dulces y Bebidas", 6.0, caj[4], 1, 20)
            };

            puestoABM.asignarStaff(idsPD[0], caj[0]); puestoABM.asignarStaff(idsPD[0], c[1]); puestoABM.ofrecerPlato(idsPD[0], p[2]);
            puestoABM.asignarStaff(idsPD[1], caj[2]); puestoABM.ofrecerPlato(idsPD[1], p[1]);
            puestoABM.asignarStaff(idsPD[2], caj[1]); puestoABM.ofrecerPlato(idsPD[2], p[4]);
            puestoABM.asignarStaff(idsPD[3], caj[3]); puestoABM.asignarStaff(idsPD[3], c[5]); puestoABM.ofrecerPlato(idsPD[3], p[7]);
            puestoABM.asignarStaff(idsPD[4], caj[4]); puestoABM.asignarStaff(idsPD[4], caj[5]); puestoABM.ofrecerPlato(idsPD[4], p[8]); puestoABM.ofrecerPlato(idsPD[4], p[9]);

            PuestoDesarmable[] pd = new PuestoDesarmable[5];
            for (int i = 0; i < 5; i++) pd[i] = puestoABM.traer(idsPD[i]);
            System.out.println("Puestos creados: " + Arrays.toString(pd));

            // 6. FESTIVALES
            long idF1 = festivalABM.agregar("Mega Rock Festival", "Primavera", LocalDate.of(2026, 9, 15), LocalDate.of(2026, 9, 20), new HashSet<>(Arrays.asList(ft[0], pd[0], pd[2])), new HashSet<>(Arrays.asList(caj[0], caj[1], c[2])));
            long idF2 = festivalABM.agregar("Feria de Verano", "Verano", LocalDate.of(2026, 1, 10), LocalDate.of(2026, 1, 20), new HashSet<>(Arrays.asList(pd[1], ft[1])), new HashSet<>(Arrays.asList(caj[1], caj[2])));
            long idF3 = festivalABM.agregar("Festival Gastronomico", "Otono", LocalDate.of(2026, 4, 5), LocalDate.of(2026, 4, 12), new HashSet<>(List.of(ft[0])), new HashSet<>(List.of(c[0])));
            long idF4 = festivalABM.agregar("Festival de Invierno", "Invierno", LocalDate.of(2026, 7, 1), LocalDate.of(2026, 7, 7), new HashSet<>(Arrays.asList(ft[2], pd[3])), new HashSet<>(Arrays.asList(c[3], caj[3], caj[5])));
            long idF5 = festivalABM.agregar("Encuentro Gourmet", "Primavera", LocalDate.of(2026, 10, 1), LocalDate.of(2026, 10, 5), new HashSet<>(Arrays.asList(ft[3], pd[4], pd[1])), new HashSet<>(Arrays.asList(c[4], caj[4], caj[5], c[5])));

            Festival f1 = festivalABM.traer(idF1), f2 = festivalABM.traer(idF2), f4 = festivalABM.traer(idF4), f5 = festivalABM.traer(idF5);
            System.out.println("Festivales creados: " + f1 + " | " + f2 + " | " + festivalABM.traer(idF3) + " | " + f4 + " | " + f5);

            // 7. PEDIDOS E ITEMS
            long[] idsPed = {
                pedidoABM.agregar(LocalDate.of(2026, 9, 16), f1, ft[0]),
                pedidoABM.agregar(LocalDate.of(2026, 1, 12), f2, pd[0]),
                pedidoABM.agregar(LocalDate.of(2026, 9, 17), f1, pd[2]),
                pedidoABM.agregar(LocalDate.of(2026, 7, 2), f4, ft[2]),
                pedidoABM.agregar(LocalDate.of(2026, 7, 3), f4, pd[3]),
                pedidoABM.agregar(LocalDate.of(2026, 10, 2), f5, ft[3]),
                pedidoABM.agregar(LocalDate.of(2026, 10, 3), f5, pd[4]),
                pedidoABM.agregar(LocalDate.of(2026, 1, 15), f2, ft[1])
            };

            Pedido[] ped = new Pedido[8];
            for (int i = 0; i < 8; i++) ped[i] = pedidoABM.traer(idsPed[i]);

            itemPedidoABM.agregar(ped[0], p[0], 3, p[0].getPrecioVenta()); itemPedidoABM.agregar(ped[0], p[1], 1, p[1].getPrecioVenta());
            itemPedidoABM.agregar(ped[1], p[2], 2, p[2].getPrecioVenta());
            itemPedidoABM.agregar(ped[2], p[4], 4, p[4].getPrecioVenta());
            itemPedidoABM.agregar(ped[3], p[5], 2, p[5].getPrecioVenta());
            itemPedidoABM.agregar(ped[4], p[7], 3, p[7].getPrecioVenta());
            itemPedidoABM.agregar(ped[5], p[6], 1, p[6].getPrecioVenta()); itemPedidoABM.agregar(ped[5], p[7], 2, p[7].getPrecioVenta());
            itemPedidoABM.agregar(ped[6], p[8], 5, p[8].getPrecioVenta()); itemPedidoABM.agregar(ped[6], p[9], 5, p[9].getPrecioVenta());
            itemPedidoABM.agregar(ped[7], p[3], 2, p[3].getPrecioVenta());

            for (int i = 0; i < 8; i++) {
                System.out.println("Pedido " + (i + 1) + " con items: " + pedidoABM.traerConItems(idsPed[i]));
            }

            System.out.println("\nCARGA DE DATOS FINALIZADA CORRECTAMENTE\n");

            // CONSULTAS
            System.out.println("==============================================================");
            System.out.println("---------- ESTUDIANTE RUIZ PEREIRA, ROBERTO ANDRES -----------");
            System.out.println("==============================================================");

            System.out.println("\n--- CONSULTA 1: PUESTOS DESARMABLES FILTRADOS ---");
            Set<PuestoDesarmable> puestosFiltrados = puestoABM.traerPuestos(1, 60, 5.0, 1);
            System.out.println("Puestos encontrados: " + puestosFiltrados.size());
            for (PuestoDesarmable pdItem : puestosFiltrados) {
                System.out.println("-> " + pdItem.getNombreComercial() + " | Carpas: " + pdItem.getCantidadCarpas() + " | Montaje: " + pdItem.getTiempoMontajeMin() + " min | Superficie: " + pdItem.getSuperficieM2() + " m2");
            }

            System.out.println("\n--- CONSULTA 2: UNIDADES DE VENTA POR MÍNIMO 1 DE STAFF DE FESTIVAL ---");
            Set<UnidadVenta> minStaff1 = festivalABM.traerUnidadesVentaPorStaffDeFestival(1);
            System.out.println("Unidades encontradas: " + minStaff1.size());
            for (UnidadVenta u : minStaff1) System.out.println("-> " + u.getNombreComercial() + " | Código: " + u.getCodigo());

            System.out.println("\n--- CONSULTA 3: UNIDADES DE VENTA POR MÍNIMO 2 DE STAFF DE FESTIVAL ---");
            Set<UnidadVenta> minStaff2 = festivalABM.traerUnidadesVentaPorStaffDeFestival(2);
            System.out.println("Unidades encontradas: " + minStaff2.size());
            for (UnidadVenta u : minStaff2) System.out.println("-> " + u.getNombreComercial() + " | Código: " + u.getCodigo());

            System.out.println("\n--- CONSULTA 4: UNIDADES DE VENTA POR MÍNIMO 3 DE STAFF DE FESTIVAL ---");
            Set<UnidadVenta> minStaff3 = festivalABM.traerUnidadesVentaPorStaffDeFestival(3);
            System.out.println("Unidades encontradas: " + minStaff3.size());
            for (UnidadVenta u : minStaff3) System.out.println("-> " + u.getNombreComercial() + " | Código: " + u.getCodigo());

            System.out.println("\n===========================================================");
            System.out.println("---------- ESTUDIANTE SALVATIERRA, FEDERICO MATIAS ----------");
            System.out.println("=============================================================");

            System.out.println("\n--- CONSULTA 1: COCINEROS ASIGNADOS A FESTIVAL POR RANGO DE FECHAS ---");
            Set<Cocinero> cocinerosPorFecha = cocineroABM.traerCocinerosPorFestivalYFechas(idF1, LocalDate.of(1900, 5, 1), LocalDate.of(2026, 9, 20));
            System.out.println("Cocineros encontrados: " + cocinerosPorFecha.size());
            for (Cocinero cocItem : cocinerosPorFecha) System.out.println("-> " + cocItem.getNombre() + " " + cocItem.getApellido() + " | Especialidad: " + cocItem.getEspecialidad());

            System.out.println("\n--- CONSULTA 2: UNIDADES DE VENTA FILTRADAS POR DATOS DE STAFF ---");
            Set<UnidadVenta> unidadesPorStaff = unidadVentaABM.traerUnidadesVentaPorDatosStaff("68555666", LocalDate.of(1985, 9, 3), LocalDate.of(2022, 3, 15));
            System.out.println("Unidades encontradas: " + unidadesPorStaff.size());
            for (UnidadVenta u : unidadesPorStaff) System.out.println("-> " + u.getNombreComercial() + " | Código: " + u.getCodigo() + " | Superficie: " + u.getSuperficieM2() + " m2");

            System.out.println("\n==============================================");
            System.out.println("---------- ESTUDIANTE SOLOAGA, LEONEL ----------");
            System.out.println("================================================");

            System.out.println("\n--- CONSULTA 1: PLATOS POR UNIDAD DE VENTA ---");
            UnidadVenta unidad = unidadVentaABM.traerUnidadVentaConPlatos(idsFT[0]);
            if (unidad == null) {
                System.out.println("No existe la unidad de venta con ID: " + idsFT[0]);
            } else {
                System.out.println("Unidad: " + unidad.getNombreComercial() + " | Código: " + unidad.getCodigo());
                System.out.println("Platos encontrados: " + unidad.getPlatosOfrecidos().size());
                for (Plato plItem : unidad.getPlatosOfrecidos()) System.out.println("-> " + plItem.getNombre() + " | Precio venta: $" + plItem.getPrecioVenta() + " | Costo producción: $" + plItem.getCostoProduccion());
            }

            System.out.println("\n--- CONSULTA 2: FOODTRACKS QUE REQUIEREN ELECTRICIDAD ---");
            List<FoodTrack> foodTracks = unidadVentaABM.traerFoodTracksConElectricidad(true);
            System.out.println("FoodTracks encontrados: " + foodTracks.size());
            for (FoodTrack fItem : foodTracks) System.out.println("-> " + fItem.getNombreComercial() + " | Código: " + fItem.getCodigo() + " | Superficie: " + fItem.getSuperficieM2() + " m2 | Patente: " + fItem.getPatente() + " | Requiere electricidad: " + fItem.isRequiereElectricidad());

            System.out.println("\n================================================");
            System.out.println("---------- ESTUDIANTE ANA BELEN VAZQUEZ ----------");
            System.out.println("==================================================");

            System.out.println("\n--- CONSULTA 1: FOODTRACKS CON SUPERFICIE MINIMA ---");
            List<FoodTrack> foodTracksSuperficie = unidadVentaABM.traerFoodTracksConSuperficieMinima(10.0);
            System.out.println("FoodTracks encontrados: " + foodTracksSuperficie.size());
            for (FoodTrack fItem : foodTracksSuperficie) System.out.println("-> " + fItem.getNombreComercial() + " | Código: " + fItem.getCodigo() + " | Superficie: " + fItem.getSuperficieM2() + " m2 | Patente: " + fItem.getPatente());

            System.out.println("\n--- CONSULTA 2: UNIDADES DE VENTA CON MÍNIMO DE PEDIDOS ---");
            List<UnidadVenta> unidadesConPedidos = unidadVentaABM.traerUnidadesVentaConMinimoPedidos(1);
            System.out.println("Unidades encontradas: " + unidadesConPedidos.size());
            for (UnidadVenta u : unidadesConPedidos) System.out.println("-> " + u.getNombreComercial() + " | Código: " + u.getCodigo());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}