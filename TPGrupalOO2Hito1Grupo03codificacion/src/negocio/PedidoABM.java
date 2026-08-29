package negocio;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import dao.PedidoDao;
import datos.Festival;
import datos.Pedido;
import datos.UnidadVenta;

public class PedidoABM {

	PedidoDao dao = PedidoDao.getIntancia();

	public Pedido traer(long idPedido) {
		return dao.traerPedido(idPedido);
	}
	public int agregar(LocalDate fecha, Festival festival, UnidadVenta unidadVenta) throws Exception {
		Pedido p = new Pedido(null, new HashSet<>(), fecha, festival, unidadVenta);
		return dao.agregar(p);
	}
	public void modificar(Pedido p) throws Exception {
		dao.actualizar(p);
	}
	public void eliminar(long idPedido) throws Exception {
		Pedido p = dao.traerPedido(idPedido);
		if (p == null) throw new RuntimeException("ERROR: No existe pedido con ID: " + idPedido);
		dao.eliminar(p);
	}
	public List<Pedido> traer() {
		return dao.traerPedido();
	}
	public List<Pedido> traerPorFestival(long idFestival) {
		return dao.traerPedidoPorFestival(idFestival);
	}
	public List<Pedido> traerPorUnidadVenta(long idunidadVenta) {
		return dao.traerPedidoPorUnidadVenta(idunidadVenta);
	}
	public Pedido traerConItems(long idPedido) {
		return dao.traerPedidoYItems(idPedido);
	}
}