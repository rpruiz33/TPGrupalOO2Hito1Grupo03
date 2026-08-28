package datos;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Staff {
    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private LocalDate fechaNacimiento;
    private LocalDate fechaIngreso;
    private double sueldoBase;

    public Staff() { }

    public Staff(Long id, String nombre, String apellido, String dni, LocalDate fechaNacimiento,
                 LocalDate fechaIngreso, double sueldoBase) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaIngreso = fechaIngreso;
        this.sueldoBase = sueldoBase;
    }

    
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public double getSueldoBase() {
		return sueldoBase;
	}

	public void setSueldoBase(double sueldoBase) {
		this.sueldoBase = sueldoBase;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Staff staff = (Staff) o;
        return Double.compare(sueldoBase, staff.sueldoBase) == 0 &&
                Objects.equals(id, staff.id) &&
                Objects.equals(nombre, staff.nombre) &&
                Objects.equals(apellido, staff.apellido) &&
                Objects.equals(dni, staff.dni) &&
                Objects.equals(fechaNacimiento, staff.fechaNacimiento) &&
                Objects.equals(fechaIngreso, staff.fechaIngreso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellido, dni, fechaNacimiento, fechaIngreso, sueldoBase);
    }

    @Override
    public String toString() {
        return "Staff{" + "id=" + id + ", nombre='" + nombre + '\'' + ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' + ", fechaNacimiento=" + fechaNacimiento +
                ", fechaIngreso=" + fechaIngreso + ", sueldoBase=" + sueldoBase +
                '}';
    }
}