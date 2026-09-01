package negocio;

import java.time.LocalDate;
import java.util.List;

import dao.CajeroDao;
import dao.StaffDao;
import datos.Cajero;
import datos.Staff;

public class CajeroABM {

    private static CajeroABM instancia;
    private CajeroDao dao = CajeroDao.getIntancia();
    private StaffDao staffDao = StaffDao.getIntancia();

    protected CajeroABM() {}

    public static CajeroABM getInstancia() {
        if (instancia == null) {
            instancia = new CajeroABM();
        }
        return instancia;
    }

    public Cajero traer(long idStaff) {
        return dao.traerCajero(idStaff);
    }

    public int agregar(String nombre, String apellido, String dni, LocalDate fechaNacimiento,
            LocalDate fechaIngreso, double sueldoBase, String turno) throws Exception {
        if (staffDao.traerStaff(dni) != null) throw new Exception("ERROR ya existe staff con DNI: " + dni);
        Cajero c = new Cajero(null, nombre, apellido, dni, fechaNacimiento, fechaIngreso, sueldoBase, turno);
        return dao.agregar(c);
    }

    public void modificar(Cajero c) throws Exception {
        Staff existente = staffDao.traerStaff(c.getDni());
        if (existente != null && existente.getId() != c.getId()) {
            throw new Exception("ERROR ya existe staff con DNI: " + c.getDni());
        }
        dao.actualizar(c);
    }

    public void eliminar(long idStaff) throws Exception {
        Cajero c = dao.traerCajero(idStaff);
        if (c == null) throw new RuntimeException("ERROR: No existe cajero con ID: " + idStaff);
        dao.eliminar(c);
    }

    public List<Cajero> traer() {
        return dao.traerCajeros();
    }

    public List<Cajero> traerPorTurno(String turno) {
        return dao.traerCajerosPorTurno(turno);
    }
}