package negocio;
import java.util.List;
import dao.ItemPedidoDao;
import dao.PedidoDao;
import datos.ItemPedido;
import datos.Pedido;
import datos.Plato;

public class ItemPedidoABM {

	ItemPedidoDao dao = ItemPedidoDao.getIntancia();
	PedidoDao pedidoDao = PedidoDao.getIntancia();

	public ItemPedido traer(long idItemPedido) {
		return dao.traerItemPedido(idItemPedido);
	}
	// Agrega el item al pedido (cascade="all-delete-orphan" en Pedido.items se encarga de persistirlo).
	public int agregar(Pedido pedido, Plato plato, long cantidad, double precioUnidad) throws Exception {
		ItemPedido item = new ItemPedido(0, plato, cantidad, precioUnidad);
		pedido.getItems().add(item);
		pedidoDao.actualizar(pedido);
		return item.getId() != 0 ? (int) item.getId() : 0;
	}
	public void modificar(ItemPedido i) throws Exception {
		dao.actualizar(i);
	}
	public void eliminar(long idItemPedido) throws Exception {
		ItemPedido i = dao.traerItemPedido(idItemPedido);
		if (i == null) throw new RuntimeException("ERROR: No existe item de pedido con ID: " + idItemPedido);
		dao.eliminar(i);
	}
	public List<ItemPedido> traer() {
		return dao.traerItemsPedido();
	}
}