package datos;

import java.util.Objects;

public class Plato {
    private Long id;
    private String nombre;
    private double precioVenta;
    private double costoProduccion;

    public Plato() { }

    public Plato(Long id, String nombre, double precioVenta, double costoProduccion) {
        this.id = id;
        this.nombre = nombre;
        this.precioVenta = precioVenta;
        this.costoProduccion = costoProduccion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plato plato = (Plato) o;
        return Double.compare(precioVenta, plato.precioVenta) == 0 &&
                Double.compare(costoProduccion, plato.costoProduccion) == 0 &&
                Objects.equals(id, plato.id) &&
                Objects.equals(nombre, plato.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, precioVenta, costoProduccion);
    }

    @Override
    public String toString() {
        return "Plato{" + "id=" + id + ", nombre='" + nombre + '\'' + ", precioVenta=" + precioVenta + ", costoProduccion=" + costoProduccion +
                '}';
    }
}