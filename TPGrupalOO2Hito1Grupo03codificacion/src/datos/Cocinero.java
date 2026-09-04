package datos;

import java.time.LocalDate;
import java.util.Objects;

public class Cocinero extends Staff {
    private String especialidad;
    private boolean libretaSanitaria;
    private double bonoPeligrosidad;

    public Cocinero() {  }


	

	public Cocinero(String nombre, String apellido, String dni, LocalDate fechaNacimiento, LocalDate fechaIngreso,
			double sueldoBase,String especialidad, boolean libretaSanitaria, double bonoPeligrosidad) {
		super(nombre, apellido, dni, fechaNacimiento, fechaIngreso, sueldoBase);
		this.especialidad = especialidad;
		this.libretaSanitaria = libretaSanitaria;
		this.bonoPeligrosidad = bonoPeligrosidad;
		
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public boolean isLibretaSanitaria() {
		return libretaSanitaria;
	}

	public void setLibretaSanitaria(boolean libretaSanitaria) {
		this.libretaSanitaria = libretaSanitaria;
	}

	public double getBonoPeligrosidad() {
		return bonoPeligrosidad;
	}

	public void setBonoPeligrosidad(double bonoPeligrosidad) {
		this.bonoPeligrosidad = bonoPeligrosidad;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(bonoPeligrosidad, especialidad, libretaSanitaria);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cocinero other = (Cocinero) obj;
		return Double.doubleToLongBits(bonoPeligrosidad) == Double.doubleToLongBits(other.bonoPeligrosidad)
				&& Objects.equals(especialidad, other.especialidad) && libretaSanitaria == other.libretaSanitaria;
	}

  
}