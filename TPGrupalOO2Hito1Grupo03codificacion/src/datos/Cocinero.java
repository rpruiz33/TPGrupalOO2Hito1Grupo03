package datos;

import java.time.LocalDate;
import java.util.Objects;

public class Cocinero extends Staff {
    private String especialidad;

    public Cocinero() {  }

    public Cocinero(Long id, String nombre, String apellido, String dni, LocalDate fechaNacimiento,
                     LocalDate fechaIngreso, double sueldoBase, String especialidad) {
        super(id, nombre, apellido, dni, fechaNacimiento, fechaIngreso, sueldoBase);
        this.especialidad = especialidad;
    }
    
    

    public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cocinero cocinero = (Cocinero) o;
        return Objects.equals(especialidad, cocinero.especialidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), especialidad);
    }

    @Override
    public String toString() {
        return "Cocinero{" + "especialidad='" + especialidad + '\'' +
                "} " + super.toString();
    }
}