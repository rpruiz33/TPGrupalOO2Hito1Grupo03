package datos;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Pedido {
    private Long id;
    private Set<ItemPedido> items;
    private LocalDate fecha;
    private Festival festival;
    private UnidadVenta unidadVenta;

    public Pedido() {}

    public Pedido(Long id, Set<ItemPedido> items, LocalDate fecha, Festival festival, UnidadVenta unidadVenta) {
        this.id = id;
        this.items = items;
        this.fecha = fecha;
        this.festival = festival;
        this.unidadVenta = unidadVenta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id) &&
                Objects.equals(items, pedido.items) &&
                Objects.equals(fecha, pedido.fecha) &&
                Objects.equals(festival, pedido.festival) &&
                Objects.equals(unidadVenta, pedido.unidadVenta);
    }
    
    

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<ItemPedido> getItems() {
		return items;
	}

	public void setItems(Set<ItemPedido> items) {
		this.items = items;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Festival getFestival() {
		return festival;
	}

	public void setFestival(Festival festival) {
		this.festival = festival;
	}

	public UnidadVenta getUnidadVenta() {
		return unidadVenta;
	}

	public void setUnidadVenta(UnidadVenta unidadVenta) {
		this.unidadVenta = unidadVenta;
	}

	@Override
    public int hashCode() {
        return Objects.hash(id, items, fecha, festival, unidadVenta);
    }

    @Override
    public String toString() {
        return "Pedido{" + "id=" + id + ", items=" + items + ", fecha=" + fecha + ", festival=" + festival +
                ", unidadVenta=" + unidadVenta + '}';
    }
}