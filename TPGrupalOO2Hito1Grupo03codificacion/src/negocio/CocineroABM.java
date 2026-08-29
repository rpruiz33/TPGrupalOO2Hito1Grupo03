package negocio;
import java.time.LocalDate;
import java.util.List;
import dao.CocineroDao;
import dao.StaffDao;
import datos.Cocinero;
import datos.Staff;

public class CocineroABM {

	CocineroDao dao = CocineroDao.getIntancia();
	StaffDao staffDao = StaffDao.getIntancia();

	public Cocinero traer(long idStaff) {
		return dao.traerCocinero(idStaff);
	}
	public int agregar(String nombre, String apellido, String dni, LocalDate fechaNacimiento,
			LocalDate fechaIngreso, double sueldoBase, String especialidad) throws Exception {
		if (staffDao.traerStaff(dni) != null) throw new Exception("ERROR ya existe staff con DNI: " + dni);
		Cocinero c = new Cocinero(null, nombre, apellido, dni, fechaNacimiento, fechaIngreso, sueldoBase, especialidad);
		return dao.agregar(c);
	}
	public void modificar(Cocinero c) throws Exception {
		Staff existente = staffDao.traerStaff(c.getDni());
		if (existente != null && existente.getId() != c.getId()) {
			throw new Exception("ERROR ya existe staff con DNI: " + c.getDni());
		}
		dao.actualizar(c);
	}
	public void eliminar(long idStaff) throws Exception {
		Cocinero c = dao.traerCocinero(idStaff);
		if (c == null) throw new RuntimeException("ERROR: No existe cocinero con ID: " + idStaff);
		dao.eliminar(c);
	}
	public List<Cocinero> traer() {
		return dao.traerCocineros();
	}
	public List<Cocinero> traerPorEspecialidad(String especialidad) {
		return dao.traerCocinerosPorEspecialidad(especialidad);
	}
}