package datos;

import java.util.Objects;

public class ItemPedido {

    
    private Plato plato;
    private long cantidad;
    private double precioUnidad;
    public ItemPedido() {
    }

    public ItemPedido( Plato plato, long cantidad, double precioUnidad) {
        
        this.plato = plato;
        this.cantidad = cantidad;
        this.precioUnidad = precioUnidad;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        ItemPedido that = (ItemPedido) o;

        return cantidad == that.cantidad
                && Double.compare(precioUnidad, that.precioUnidad) == 0
            
                && Objects.equals(plato, that.plato);
    }

    @Override
    public int hashCode() {
        return Objects.hash( plato, cantidad, precioUnidad);
    }

    

    @Override
	public String toString() {
		return "ItemPedido [id=" +  ", plato=" + plato + ", cantidad=" + cantidad + ", precioUnidad=" + precioUnidad
				+ "]";
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
}