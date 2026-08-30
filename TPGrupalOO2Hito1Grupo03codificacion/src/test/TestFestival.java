package test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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


public class TestFestival {

	public static void main(String[] args) {
		try {
			CocineroABM cocineroABM = new CocineroABM();
			CajeroABM cajeroABM = new CajeroABM();
			PlatoABM platoABM = new PlatoABM();
			FoodTrackABM foodTrackABM = new FoodTrackABM();
			PuestoDesarmableABM puestoABM = new PuestoDesarmableABM();
			FestivalABM festivalABM = new FestivalABM();
			PedidoABM pedidoABM = new PedidoABM();
			ItemPedidoABM itemPedidoABM = new ItemPedidoABM();

			// ---------- Staff ----------
			int idCocinero1 = cocineroABM.agregar("Lucia", "Fernandez", "30111222",
					LocalDate.of(1990, 4, 12), LocalDate.of(2023, 10, 1), 450000.0, "Parrilla");
			int idCocinero2 = cocineroABM.agregar("Martin", "Gomez", "28555666",
					LocalDate.of(1985, 9, 3), LocalDate.of(2022, 3, 15), 470000.0, "Reposteria");
			int idCajero1 = cajeroABM.agregar("Ana", "Lopez", "35222111",
					LocalDate.of(1998, 1, 20), LocalDate.of(2024, 6, 1), 380000.0, "manana");
			int idCajero2 = cajeroABM.agregar("Diego", "Perez", "32444333",
					LocalDate.of(1995, 7, 8), LocalDate.of(2023, 11, 20), 390000.0, "noche");

			Cocinero cocinero1 = cocineroABM.traer(idCocinero1);
			Cocinero cocinero2 = cocineroABM.traer(idCocinero2);
			Cajero cajero1 = cajeroABM.traer(idCajero1);
			Cajero cajero2 = cajeroABM.traer(idCajero2);
			System.out.println("Staff creado: " + cocinero1 + " | " + cocinero2 + " | " + cajero1 + " | " + cajero2);

			// ---------- Platos ----------
			int idPlato1 = platoABM.agregar("Choripan", 3500.0, 1200.0);
			int idPlato2 = platoABM.agregar("Hamburguesa Completa", 6800.0, 2600.0);
			int idPlato3 = platoABM.agregar("Torta Frita", 2000.0, 600.0);

			Plato plato1 = platoABM.traer(idPlato1);
			Plato plato2 = platoABM.traer(idPlato2);
			Plato plato3 = platoABM.traer(idPlato3);
			System.out.println("Platos creados: " + plato1 + " | " + plato2 + " | " + plato3);

			// ---------- Unidades de venta ----------
			int idFoodTrack = foodTrackABM.agregar("FT00000001", "El Rincon del Choripan", 18.5,
					cocinero1, "AB123CD", true);
			int idPuesto = puestoABM.agregar("PD00000001", "Dulces de la Feria", 9.0,
					cajero1, 2, 45);

			foodTrackABM.asignarStaff(idFoodTrack, cocinero1);
			foodTrackABM.asignarStaff(idFoodTrack, cajero2);
			foodTrackABM.ofrecerPlato(idFoodTrack, plato1);
			foodTrackABM.ofrecerPlato(idFoodTrack, plato2);

			puestoABM.asignarStaff(idPuesto, cajero1);
			puestoABM.asignarStaff(idPuesto, cocinero2);
			puestoABM.ofrecerPlato(idPuesto, plato3);

			FoodTrack foodTrack = foodTrackABM.traer(idFoodTrack);
			PuestoDesarmable puesto = puestoABM.traer(idPuesto);
			System.out.println("Unidades creadas: " + foodTrack + " | " + puesto);

			// ---------- Festivales ----------
			Set<UnidadVenta> unidadesFestival1 = new HashSet<>();
			unidadesFestival1.add(foodTrack);
			unidadesFestival1.add(puesto);
			Set<Staff> staffDirectoFestival1 = new HashSet<>();
			staffDirectoFestival1.add(cajero2);

			int idFestival1 = festivalABM.agregar("Feria de Verano", "Verano",
					LocalDate.of(2026, 1, 10), LocalDate.of(2026, 1, 20),
					unidadesFestival1, staffDirectoFestival1);

			Set<UnidadVenta> unidadesFestival2 = new HashSet<>();
			unidadesFestival2.add(foodTrack);
			int idFestival2 = festivalABM.agregar("Festival de Otono", "Otono",
					LocalDate.of(2026, 4, 5), LocalDate.of(2026, 4, 12),
					unidadesFestival2, new HashSet<>());

			Festival festival1 = festivalABM.traer((long) idFestival1);
			Festival festival2 = festivalABM.traer((long) idFestival2);
			System.out.println("Festivales creados: " + festival1 + " | " + festival2);

			// ---------- Pedidos + Items ----------
			int idPedido1 = pedidoABM.agregar(LocalDate.of(2026, 1, 11), festival1, foodTrack);
			Pedido pedido1 = pedidoABM.traer(idPedido1);
			itemPedidoABM.agregar(pedido1, plato1, 3, plato1.getPrecioVenta());
			itemPedidoABM.agregar(pedido1, plato2, 1, plato2.getPrecioVenta());

			int idPedido2 = pedidoABM.agregar(LocalDate.of(2026, 1, 12), festival1, puesto);
			Pedido pedido2 = pedidoABM.traer(idPedido2);
			itemPedidoABM.agregar(pedido2, plato3, 5, plato3.getPrecioVenta());

			System.out.println("Pedidos creados: " + pedidoABM.traerConItems(idPedido1));
			System.out.println("Pedidos creados: " + pedidoABM.traerConItems(idPedido2));

			System.out.println("CARGA DE DATOS FINALIZADA CORRECTAMENTE");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}