package datos;

import java.util.Objects;

public class ItemPedido {
    private long id;
    private Plato plato;
    private long cantidad;
    private double precioUnidad;

    public ItemPedido() { }

    public ItemPedido(long id, Plato plato, long cantidad, double precioUnidad) {
        this.id = id;
        this.plato = plato;
        this.cantidad = cantidad;
        this.precioUnidad = precioUnidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedido that = (ItemPedido) o;
        return id == that.id &&
                cantidad == that.cantidad &&
                Double.compare(precioUnidad, that.precioUnidad) == 0 &&
                Objects.equals(plato, that.plato);
    }
    
    

    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Plato getPlato() {
		return plato;
	}

	public void setPlato(Plato plato) {
		this.plato = plato;
	}

	public long getCantidad() {
		return cantidad;
	}

	public void setCantidad(long cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecioUnidad() {
		return precioUnidad;
	}

	public void setPrecioUnidad(double precioUnidad) {
		this.precioUnidad = precioUnidad;
	}

	@Override
    public int hashCode() {
        return Objects.hash(id, plato, cantidad, precioUnidad);
    }

    @Override
    public String toString() {
        return "ItemPedido{" + "id=" + id + ", plato=" + plato + ", cantidad=" + cantidad + ", precioUnidad=" + precioUnidad + '}';
    }
}