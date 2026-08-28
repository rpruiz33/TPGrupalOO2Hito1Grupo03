package datos;

import java.time.LocalDate;
import java.util.Objects;

public class Cajero extends Staff {
    private String turno;

    public Cajero() { }

    public Cajero(Long id, String nombre, String apellido, String dni, LocalDate fechaNacimiento,
                  LocalDate fechaIngreso, double sueldoBase, String turno) {
        super(id, nombre, apellido, dni, fechaNacimiento, fechaIngreso, sueldoBase);
        this.turno = turno;
    }
    
    

    public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cajero cajero = (Cajero) o;
        return Objects.equals(turno, cajero.turno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), turno);
    }

    @Override
    public String toString() {
        return "Cajero{" + "turno='" + turno + '\'' +
                "} " + super.toString();
    }
}