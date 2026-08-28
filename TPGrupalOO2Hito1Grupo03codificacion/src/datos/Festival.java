package datos;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Festival {
    private String nombre;
    private String temporada;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Set<UnidadVenta> unidadesHabilitadas;
    private Set<Staff> staffGeneral;

    public Festival() {
        this.unidadesHabilitadas = new HashSet<>();
        this.staffGeneral = new HashSet<>();
    }

    public Festival(String nombre, String temporada, LocalDate fechaInicio, LocalDate fechaFin,
                    Set<UnidadVenta> unidadesHabilitadas, Set<Staff> staffGeneral) {
        this.nombre = nombre;
        this.temporada = temporada;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.unidadesHabilitadas = unidadesHabilitadas;
        this.staffGeneral = staffGeneral;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Festival festival = (Festival) o;
        return Objects.equals(nombre, festival.nombre) &&
                Objects.equals(temporada, festival.temporada) &&
                Objects.equals(fechaInicio, festival.fechaInicio) &&
                Objects.equals(fechaFin, festival.fechaFin) &&
                Objects.equals(unidadesHabilitadas, festival.unidadesHabilitadas) &&
                Objects.equals(staffGeneral, festival.staffGeneral);
    }

    
    
    public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTemporada() {
		return temporada;
	}

	public void setTemporada(String temporada) {
		this.temporada = temporada;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}



	public Set<UnidadVenta> getUnidadesHabilitadas() {
		return unidadesHabilitadas;
	}

	public void setUnidadesHabilitadas(Set<UnidadVenta> unidadesHabilitadas) {
		this.unidadesHabilitadas = unidadesHabilitadas;
	}

	public Set<Staff> getStaffGeneral() {
		return staffGeneral;
	}

	public void setStaffGeneral(Set<Staff> staffGeneral) {
		this.staffGeneral = staffGeneral;
	}

	@Override
    public int hashCode() {
        return Objects.hash(nombre, temporada, fechaInicio, fechaFin,unidadesHabilitadas, staffGeneral);
    }

    @Override
    public String toString() {
        return "Festival{" +
                "nombre='" + nombre + '\'' +
                ", temporada='" + temporada + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", unidadesHabilitadas=" + unidadesHabilitadas +
                ", staffGeneral=" + staffGeneral +
                '}';
    }
}