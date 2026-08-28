package datos;

import java.util.Objects;
import java.util.Set;

public class FoodTrack extends UnidadVenta {
    private String patente;
    private boolean requiereElectricidad;

    public FoodTrack() { }

    public FoodTrack(Long id, String codigo, String nombreComercial, double superficieM2,
                      Set<Pedido> pedidos, Set<Plato> platosOfrecidos, Set<Staff> staffPuesto, Staff responsable,
                      String patente, boolean requiereElectricidad) {
        super(id, codigo, nombreComercial, superficieM2, pedidos, platosOfrecidos, staffPuesto, responsable);
        this.patente = patente;
        this.requiereElectricidad = requiereElectricidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FoodTrack foodtrack = (FoodTrack) o;
        return requiereElectricidad == foodtrack.requiereElectricidad &&
                Objects.equals(patente, foodtrack.patente);
    }

    
    
    public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public boolean isRequiereElectricidad() {
		return requiereElectricidad;
	}

	public void setRequiereElectricidad(boolean requiereElectricidad) {
		this.requiereElectricidad = requiereElectricidad;
	}

	@Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), patente, requiereElectricidad);
    }

    @Override
    public String toString() {
        return "Foodtrack{" + "patente='" + patente + '\'' + ", requiereElectricidad=" + requiereElectricidad + "} " + super.toString();
    }
}