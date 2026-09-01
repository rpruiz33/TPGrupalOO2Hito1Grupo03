package negocio;

import java.util.List;

import dao.ItemPedidoDao;
import dao.PedidoDao;
import datos.ItemPedido;
import datos.Pedido;
import datos.Plato;

public class ItemPedidoABM {

    private static ItemPedidoABM instancia;
    private ItemPedidoDao dao = ItemPedidoDao.getIntancia();
    private PedidoDao pedidoDao = PedidoDao.getIntancia();

    protected ItemPedidoABM() {}

    public static ItemPedidoABM getInstancia() {
        if (instancia == null) {
            instancia = new ItemPedidoABM();
        }
        return instancia;
    }

    public ItemPedido traer(long idItemPedido) {
        return dao.traerItemPedido(idItemPedido);
    }

    public int agregar(Pedido pedido, Plato plato, long cantidad, double precioUnidad)
            throws Exception {

        ItemPedido item = new ItemPedido(plato, cantidad, precioUnidad);

        pedido.getItems().add(item);

        pedidoDao.actualizar(pedido);

        return item.getId() != null ? item.getId().intValue() : 0;
    }

    public void modificar(ItemPedido i) throws Exception {
        dao.actualizar(i);
    }

    public void eliminar(long idItemPedido) throws Exception {

        ItemPedido i = dao.traerItemPedido(idItemPedido);

        if (i == null) {
            throw new RuntimeException(
                    "ERROR: No existe item de pedido con ID: " + idItemPedido
            );
        }

        dao.eliminar(i);
    }

    public List<ItemPedido> traer() {
        return dao.traerItemsPedido();
    }
}