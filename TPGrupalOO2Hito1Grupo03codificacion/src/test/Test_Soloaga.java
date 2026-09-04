package test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dao.ResetBaseDao;
import datos.*;
import negocio.*;

public class Test_Soloaga {

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
            		cocineroABM.agregar("Lucia", "Fernandez", "40111222", LocalDate.of(1990, 4, 12), LocalDate.of(2023, 10, 1), 450000.0, "Parrilla", true, 25000.0),
            		cocineroABM.agregar("Martin", "Gomez", "68555666", LocalDate.of(1985, 9, 3), LocalDate.of(2022, 3, 15), 470000.0, "Reposteria", true, 15000.0),
            		cocineroABM.agregar("Roberto", "Sanchez", "32999888", LocalDate.of(1988, 11, 5), LocalDate.of(2021, 5, 10), 500000.0, "Comida Minuta", false, 20000.0),
            		cocineroABM.agregar("Valentina", "Torres", "39222111", LocalDate.of(1992, 6, 18), LocalDate.of(2023, 8, 5), 460000.0, "Panaderia", true, 18000.0),
            		cocineroABM.agregar("Julian", "Diaz", "35777888", LocalDate.of(1987, 2, 25), LocalDate.of(2021, 9, 12), 480000.0, "Vegetariana", false, 12000.0),
            		cocineroABM.agregar("Camila", "Herrera", "41666555", LocalDate.of(1994, 10, 30), LocalDate.of(2024, 1, 15), 440000.0, "Postres", true, 15000.0)
            };
            Cocinero[] c = new Cocinero[6];
            for (int i = 0; i < 6; i++) c[i] = cocineroABM.traer(idsC[i]);

            System.out.println("--- Cocineros Creados ---");
            for (Cocinero coc : c) {
                System.out.println("  • " + coc.getNombre() + " " + coc.getApellido() + " | DNI: " + coc.getDni() + " | Especialidad: " + coc.getEspecialidad());
            }

            // 2. CAJEROS
            long[] idsCaj = {
            		cajeroABM.agregar("Lucia", "Fernandez", "40111222", LocalDate.of(1990, 4, 12), LocalDate.of(2023, 10, 1), 450000.0, "Mañana", 1, 15000.0),
            		cajeroABM.agregar("Martin", "Gomez", "68555666", LocalDate.of(1985, 9, 3), LocalDate.of(2022, 3, 15), 470000.0, "Tarde", 2, 18000.0),
            		cajeroABM.agregar("Roberto", "Sanchez", "32999888", LocalDate.of(1988, 11, 5), LocalDate.of(2021, 5, 10), 500000.0, "Mañana", 3, 20000.0),
            		cajeroABM.agregar("Valentina", "Torres", "39222111", LocalDate.of(1992, 6, 18), LocalDate.of(2023, 8, 5), 460000.0, "Noche", 1, 15000.0),
            		cajeroABM.agregar("Julian", "Diaz", "35777888", LocalDate.of(1987, 2, 25), LocalDate.of(2021, 9, 12), 480000.0, "Tarde", 4, 18000.0),
            		cajeroABM.agregar("Camila", "Herrera", "41666555", LocalDate.of(1994, 10, 30), LocalDate.of(2024, 1, 15), 440000.0, "Noche", 2, 12000.0)
            };
            Cajero[] caj = new Cajero[6];
            for (int i = 0; i < 6; i++) caj[i] = cajeroABM.traer(idsCaj[i]);

            System.out.println("\n--- Cajeros Creados ---");
            for (Cajero cajero : caj) {
                System.out.println("  • " + cajero.getNombre() + " " + cajero.getApellido() + " | DNI: " + cajero.getDni() + " | Turno: " + cajero.getTurno());
            }

            // 3. PLATOS
            long[] idsP = {
                platoABM.agregar("Choripan", 3500.0, 2000),
                platoABM.agregar("Hamburguesa Completa", 6800.0, 2000),
                platoABM.agregar("Torta Frita", 2000.0, 2000),
                platoABM.agregar("Papas Rusticas", 3000.0, 2000),
                platoABM.agregar("Empanadas de Carne", 2500.0, 2000),
                platoABM.agregar("Pizza Artesanal", 5500.0, 2000),
                platoABM.agregar("Milanesa Napolitana", 7200.0, 2000),
                platoABM.agregar("Ensalada Fresca", 2800.0, 2000),
                platoABM.agregar("Alfajor Casero", 1800.0, 2000),
                platoABM.agregar("Limonada Natural", 1500.0, 2000)
            };
            Plato[] p = new Plato[10];
            for (int i = 0; i < 10; i++) p[i] = platoABM.traer(idsP[i]);

            System.out.println("\n--- Platos Creados ---");
            for (Plato plato : p) {
                System.out.println("  • " + plato.getNombre() + " | Precio: $" + plato.getPrecioVenta());
            }

            // 4. FOODTRACKS
            long[] idsFT = {
                foodTrackABM.agregar("FT00000001", "El Rincon del Choripan", 18.5, c[0], "AB123CD", true),
                foodTrackABM.agregar("FT00000002", "Papas Y Bebidas", 12.0, c[2], "CD456EF", false),
                foodTrackABM.agregar("FT00000003", "La Pizzeria Rodante", 20.0, c[3], "EF789GH", true),
                foodTrackABM.agregar("FT00000004", "Milanesas Express", 16.0, c[4], "GH012IJ", true)
            };

            foodTrackABM.asignarStaff(idsFT[0], c[0]); 
            foodTrackABM.asignarStaff(idsFT[0], caj[1]);
            foodTrackABM.ofrecerPlato(idsFT[0], p[0]); 
            foodTrackABM.ofrecerPlato(idsFT[0], p[1]);

            foodTrackABM.asignarStaff(idsFT[1], c[2]); 
            foodTrackABM.asignarStaff(idsFT[1], caj[2]);
            foodTrackABM.ofrecerPlato(idsFT[1], p[3]);

            foodTrackABM.asignarStaff(idsFT[2], c[3]); 
            foodTrackABM.asignarStaff(idsFT[2], caj[3]); 
            foodTrackABM.asignarStaff(idsFT[2], caj[5]);
            foodTrackABM.ofrecerPlato(idsFT[2], p[5]);

            foodTrackABM.asignarStaff(idsFT[3], c[4]);
            foodTrackABM.asignarStaff(idsFT[3], caj[4]);
            foodTrackABM.ofrecerPlato(idsFT[3], p[6]);
            foodTrackABM.ofrecerPlato(idsFT[3], p[7]);

            FoodTrack[] ft = new FoodTrack[4];
            for (int i = 0; i < 4; i++) ft[i] = foodTrackABM.traer(idsFT[i]);

            System.out.println("\n--- FoodTracks Creados ---");
            for (FoodTrack foodTrack : ft) {
                System.out.println("  • " + foodTrack.getNombreComercial() + " [" + foodTrack.getCodigo() + "] | Patente: " + foodTrack.getPatente() + " | Elec: " + (foodTrack.isRequiereElectricidad() ? "Sí" : "No"));
            }

            // 5. PUESTOS DESARMABLES
            long[] idsPD = {
                puestoABM.agregar("PD00000001", "Dulces de la Feria", 9.0, caj[0], 2, 45),
                puestoABM.agregar("PD00000002", "Puesto de Jugos Naturales", 15.0, caj[2], 3, 30),
                puestoABM.agregar("PD00000003", "Empanadas Artesanales", 8.0, caj[1], 1, 25),
                puestoABM.agregar("PD00000004", "Rincon Saludable", 7.5, caj[3], 2, 35),
                puestoABM.agregar("PD00000005", "Dulces y Bebidas", 6.0, caj[4], 1, 20)
            };

            puestoABM.asignarStaff(idsPD[0], caj[0]);
            puestoABM.asignarStaff(idsPD[0], c[1]);
            puestoABM.ofrecerPlato(idsPD[0], p[2]);

            puestoABM.asignarStaff(idsPD[1], caj[2]);
            puestoABM.ofrecerPlato(idsPD[1], p[1]);

            puestoABM.asignarStaff(idsPD[2], caj[1]);
            puestoABM.ofrecerPlato(idsPD[2], p[4]);

            puestoABM.asignarStaff(idsPD[3], caj[3]); 
            puestoABM.asignarStaff(idsPD[3], c[5]);
            puestoABM.ofrecerPlato(idsPD[3], p[7]);

            puestoABM.asignarStaff(idsPD[4], caj[4]); 
            puestoABM.asignarStaff(idsPD[4], caj[5]);
            puestoABM.ofrecerPlato(idsPD[4], p[8]); 
            puestoABM.ofrecerPlato(idsPD[4], p[9]);

            PuestoDesarmable[] pd = new PuestoDesarmable[5];
            for (int i = 0; i < 5; i++) pd[i] = puestoABM.traer(idsPD[i]);

            System.out.println("\n--- Puestos Desarmables Creados ---");
            for (PuestoDesarmable puesto : pd) {
                System.out.println("  • " + puesto.getNombreComercial() + " [" + puesto.getCodigo() + "] | Carpas: " + puesto.getCantidadCarpas() + " | Tiempo Montaje: " + puesto.getTiempoMontajeMin() + " min");
            }

            // 6. FESTIVALES (Usando unicamente HashSet)
            Set<UnidadVenta> uF1 = new HashSet<>();
            uF1.add(ft[0]);
            uF1.add(pd[0]);
            uF1.add(pd[2]);

            Set<Staff> sF1 = new HashSet<>();
            sF1.add(caj[0]);
            sF1.add(caj[1]);
            sF1.add(c[2]);

            long idF1 = festivalABM.agregar("Mega Rock Festival", "Primavera", LocalDate.of(2026, 9, 15), LocalDate.of(2026, 9, 20), uF1, sF1);

            Set<UnidadVenta> uF2 = new HashSet<>();
            uF2.add(pd[1]);
            uF2.add(ft[1]);

            Set<Staff> sF2 = new HashSet<>();
            sF2.add(caj[1]);
            sF2.add(caj[2]);

            long idF2 = festivalABM.agregar("Feria de Verano", "Verano", LocalDate.of(2026, 1, 10), LocalDate.of(2026, 1, 20), uF2, sF2);

            Set<UnidadVenta> uF3 = new HashSet<>();
            uF3.add(ft[0]);

            Set<Staff> sF3 = new HashSet<>();
            sF3.add(c[0]);

            long idF3 = festivalABM.agregar("Festival Gastronomico", "Otono", LocalDate.of(2026, 4, 5), LocalDate.of(2026, 4, 12), uF3, sF3);

            Set<UnidadVenta> uF4 = new HashSet<>();
            uF4.add(ft[2]);
            uF4.add(pd[3]);

            Set<Staff> sF4 = new HashSet<>();
            sF4.add(c[3]);
            sF4.add(caj[3]);
            sF4.add(caj[5]);

            long idF4 = festivalABM.agregar("Festival de Invierno", "Invierno", LocalDate.of(2026, 7, 1), LocalDate.of(2026, 7, 7), uF4, sF4);

            Set<UnidadVenta> uF5 = new HashSet<>();
            uF5.add(ft[3]);
            uF5.add(pd[4]);
            uF5.add(pd[1]);

            Set<Staff> sF5 = new HashSet<>();
            sF5.add(c[4]);
            sF5.add(caj[4]);
            sF5.add(caj[5]);
            sF5.add(c[5]);

            long idF5 = festivalABM.agregar("Encuentro Gourmet", "Primavera", LocalDate.of(2026, 10, 1), LocalDate.of(2026, 10, 5), uF5, sF5);

            Festival f1 = festivalABM.traer(idF1), f2 = festivalABM.traer(idF2), f4 = festivalABM.traer(idF4), f5 = festivalABM.traer(idF5);

            System.out.println("\n--- Festivales Creados ---");
            Festival[] listaFestivales = {f1, f2, festivalABM.traer(idF3), f4, f5};
            for (Festival fest : listaFestivales) {
                System.out.println("  • " + fest.getNombre() + " (" + fest.getTemporada()+ ") | Desde: " + fest.getFechaInicio() + " Hasta: " + fest.getFechaFin());
            }

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

            itemPedidoABM.agregar(ped[0], p[0], 3, p[0].getPrecioVenta());
            itemPedidoABM.agregar(ped[0], p[1], 1, p[1].getPrecioVenta());
            itemPedidoABM.agregar(ped[1], p[2], 2, p[2].getPrecioVenta());
            itemPedidoABM.agregar(ped[2], p[4], 4, p[4].getPrecioVenta());
            itemPedidoABM.agregar(ped[3], p[5], 2, p[5].getPrecioVenta());
            itemPedidoABM.agregar(ped[4], p[7], 3, p[7].getPrecioVenta());
            itemPedidoABM.agregar(ped[5], p[6], 1, p[6].getPrecioVenta()); 
            itemPedidoABM.agregar(ped[5], p[7], 2, p[7].getPrecioVenta());
            itemPedidoABM.agregar(ped[6], p[8], 5, p[8].getPrecioVenta()); 
            itemPedidoABM.agregar(ped[6], p[9], 5, p[9].getPrecioVenta());
            itemPedidoABM.agregar(ped[7], p[3], 2, p[3].getPrecioVenta());

            System.out.println("\n--- Pedidos e Items Registrados ---");
            for (int i = 0; i < 8; i++) {
                System.out.println("Pedido " + (i + 1) + " con items: " + pedidoABM.traerConItems(idsPed[i]));
            }

            System.out.println("\nCARGA DE DATOS FINALIZADA CORRECTAMENTE\n");

           
            System.out.println("\n==============================================");
            System.out.println("---------- ESTUDIANTE SOLOAGA, LEONEL ----------");
            System.out.println("================================================");

            System.out.println("\n--- CONSULTA 1: PLATOS POR UNIDAD DE VENTA ---");
            UnidadVenta unidad = unidadVentaABM.traerUnidadVentaConPlatos(idsFT[0]);
            if (unidad == null) {
                System.out.println("No existe la unidad de venta con ID: " + idsFT[0]);
            } else {
                System.out.println("Unidad: " + unidad.getNombreComercial() + " [" + unidad.getCodigo() + "]");
                System.out.println("Platos encontrados: " + unidad.getPlatosOfrecidos().size());
                for (Plato plItem : unidad.getPlatosOfrecidos()) {
                    System.out.println("-> " + plItem.getNombre() + " | Precio: $" + plItem.getPrecioVenta());
                }
            }

            System.out.println("\n--- CONSULTA 2: FOODTRACKS QUE REQUIEREN ELECTRICIDAD ---");
            Set<FoodTrack> foodTracks = unidadVentaABM.traerFoodTracksConElectricidad(true);
            System.out.println("FoodTracks encontrados: " + foodTracks.size());
            for (FoodTrack fItem : foodTracks) {
                System.out.println("-> " + fItem.getNombreComercial() + " [" + fItem.getCodigo() + "] | Superficie: " + fItem.getSuperficieM2() + " m2 | Patente: " + fItem.getPatente() + " | Requiere electricidad: " + fItem.isRequiereElectricidad());
            }

          
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
