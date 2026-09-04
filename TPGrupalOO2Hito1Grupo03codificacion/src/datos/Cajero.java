package datos;

import java.time.LocalDate;
import java.util.Objects;

public class Cajero extends Staff {
    private String turno;
    private int numeroDeCaja; 
    
    private double falloDeCaja ;

    public Cajero() { }

	public Cajero(String turno, int numeroDeCaja, double falloDeCaja) {
		super();
		this.turno = turno;
		this.numeroDeCaja = numeroDeCaja;
		this.falloDeCaja = falloDeCaja;
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

	public double getFalloDeCaja() {
		return falloDeCaja;
	}

	public void setFalloDeCaja(double falloDeCaja) {
		this.falloDeCaja = falloDeCaja;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(falloDeCaja, numeroDeCaja, turno);
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
		return Double.doubleToLongBits(falloDeCaja) == Double.doubleToLongBits(other.falloDeCaja)
				&& numeroDeCaja == other.numeroDeCaja && Objects.equals(turno, other.turno);
	}

	@Override
	public String toString() {
		return "Cajero [turno=" + turno + ", numeroDeCaja=" + numeroDeCaja + ", falloDeCaja=" + falloDeCaja + "]";
	}

}
   