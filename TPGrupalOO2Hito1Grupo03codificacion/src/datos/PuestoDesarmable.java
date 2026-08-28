package datos;

import java.util.Objects;
import java.util.Set;

public class PuestoDesarmable extends UnidadVenta {
    private int cantidadCarpas;
    private int tiempoMontajeMin;

    public PuestoDesarmable() { }

    public PuestoDesarmable(Long id, String codigo, String nombreComercial, double superficieM2,
                             Set<Pedido> pedidos, Set<Plato> platosOfrecidos, Set<Staff> staffPuesto, Staff responsable,
                             int cantidadCarpas, int tiempoMontajeMin) {
        super(id, codigo, nombreComercial, superficieM2, pedidos, platosOfrecidos, staffPuesto, responsable);
        this.cantidadCarpas = cantidadCarpas;
        this.tiempoMontajeMin = tiempoMontajeMin;
    }
    
    

    public int getCantidadCarpas() {
		return cantidadCarpas;
	}

	public void setCantidadCarpas(int cantidadCarpas) {
		this.cantidadCarpas = cantidadCarpas;
	}

	public int getTiempoMontajeMin() {
		return tiempoMontajeMin;
	}

	public void setTiempoMontajeMin(int tiempoMontajeMin) {
		this.tiempoMontajeMin = tiempoMontajeMin;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PuestoDesarmable that = (PuestoDesarmable) o;
        return cantidadCarpas == that.cantidadCarpas &&
                tiempoMontajeMin == that.tiempoMontajeMin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cantidadCarpas, tiempoMontajeMin);
    }

    @Override
    public String toString() {
        return "PuestoDesarmable{" + "cantidadCarpas=" + cantidadCarpas + ", tiempoMontajeMin=" + tiempoMontajeMin +
                "} " + super.toString();
    }
}