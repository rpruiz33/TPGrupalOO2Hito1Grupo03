package negocio;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import dao.CocineroDao;
import dao.StaffDao;
import datos.Cocinero;
import datos.Staff;

public class CocineroABM {

    private static CocineroABM instancia;
    private CocineroDao dao = CocineroDao.getIntancia();
    private StaffDao staffDao = StaffDao.getIntancia();

    protected CocineroABM() {}

    public static CocineroABM getInstancia() {
        if (instancia == null) {
            instancia = new CocineroABM();
        }
        return instancia;
    }

    public Cocinero traer(long idStaff) {
        return dao.traerCocinero(idStaff);
    }

    public int agregar(String nombre, String apellido, String dni, LocalDate fechaNacimiento,
            LocalDate fechaIngreso, double sueldoBase, String especialidad, boolean libretaSanitaria, double bonoPeligrosidad) throws Exception {
        if (staffDao.traerStaff(dni) != null) throw new Exception("ERROR ya existe staff con DNI: " + dni);
        Cocinero c = new Cocinero(nombre, apellido, dni, fechaNacimiento, fechaIngreso, sueldoBase, especialidad, libretaSanitaria,  bonoPeligrosidad);
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

    public Set<Cocinero> traerCocinerosPorFestivalYFechas(Long idFestival, LocalDate fechaInicio, LocalDate fechaFin) {
        return dao.traerCocinerosPorFestivalYFechas(idFestival, fechaInicio, fechaFin);
    }
}