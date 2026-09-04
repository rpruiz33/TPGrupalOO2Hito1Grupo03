package datos;

import java.time.LocalDate;
import java.util.Objects;

public class Cajero extends Staff {
    private String turno;
    private int numeroDeCaja; 
    
    private double PlusOAdiciona;

    public Cajero() { }

	public Cajero(String turno, int numeroDeCaja, double PlusOAdiciona) {
		super();
		this.turno = turno;
		this.numeroDeCaja = numeroDeCaja;
		this.PlusOAdiciona= PlusOAdiciona;
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



	public double getPlusOAdiciona() {
		return PlusOAdiciona;
	}

	public void setPlusOAdiciona(double plusOAdiciona) {
		PlusOAdiciona = plusOAdiciona;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(PlusOAdiciona, numeroDeCaja, turno);
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
		return Double.doubleToLongBits(PlusOAdiciona) == Double.doubleToLongBits(other.PlusOAdiciona)
				&& numeroDeCaja == other.numeroDeCaja && Objects.equals(turno, other.turno);
	}

	@Override
	public String toString() {
		return "Cajero [turno=" + turno + ", numeroDeCaja=" + numeroDeCaja + ", PlusOAdiciona=" + PlusOAdiciona + "]";
	}

}
   