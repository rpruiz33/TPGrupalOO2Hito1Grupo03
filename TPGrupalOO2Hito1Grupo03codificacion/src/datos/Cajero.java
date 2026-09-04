package datos;

import java.time.LocalDate;
import java.util.Objects;

public class Cajero extends Staff {
    private String turno;
    private int numeroDeCaja; 
    
    private double PlusOAdicional;

    public Cajero() { }


	
	

	public Cajero(String nombre, String apellido, String dni, LocalDate fechaNacimiento, LocalDate fechaIngreso,
			double sueldoBase, String turno, int numeroDeCaja, double PlusOAdicional) {
		super(nombre, apellido, dni, fechaNacimiento, fechaIngreso, sueldoBase);
		this.turno = turno;
		this.numeroDeCaja = numeroDeCaja;
		this.PlusOAdicional= PlusOAdicional;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public int getNumeroDeCaja() {
		return numeroDeCaja;
	}

	public void setNumeroDeCaja(int numeroDeCaja) {
		this.numeroDeCaja = numeroDeCaja;
	}



	public double getPlusOAdicional() {
		return PlusOAdicional;
	}

	public void setPlusOAdicional(double plusOAdiciona) {
		PlusOAdicional = plusOAdiciona;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(PlusOAdicional, numeroDeCaja, turno);
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
		Cajero other = (Cajero) obj;
		return Double.doubleToLongBits(PlusOAdicional) == Double.doubleToLongBits(other.PlusOAdicional)
				&& numeroDeCaja == other.numeroDeCaja && Objects.equals(turno, other.turno);
	}

	@Override
	public String toString() {
		return "Cajero [turno=" + turno + ", numeroDeCaja=" + numeroDeCaja + ", PlusOAdiciona=" + PlusOAdicional + "]";
	}

}
   