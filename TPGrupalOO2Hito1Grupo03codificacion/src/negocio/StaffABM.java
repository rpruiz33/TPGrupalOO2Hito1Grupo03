package negocio;

import java.time.LocalDate;

import dao.StaffDao;
import datos.Staff;

public class StaffABM {

    private static StaffABM instancia;
    private StaffDao dao = StaffDao.getIntancia();

    protected StaffABM() {}

    public static StaffABM getInstancia() {
        if (instancia == null) {
            instancia = new StaffABM();
        }
        return instancia;
    }

    public Staff traer(long idStaff) {
        return dao.traerStaff(idStaff);
    }

    public Staff traer(String dni) {
        return dao.traerStaff(dni);
    }

    public void eliminar(long idStaff) throws Exception {
        Staff s = dao.traerStaff(idStaff);
        if (s == null) throw new RuntimeException("ERROR: No existe staff con ID: " + idStaff);
        dao.eliminar(s);
    }

    public int agregar(Staff s) throws Exception {
        if (dao.traerStaff(s.getDni()) != null) {
            throw new Exception("ERROR ya existe staff con DNI: " + s.getDni());
        }
        return dao.agregar(s);
    }

    public void modificar(Staff s) throws Exception {
        Staff existente = dao.traerStaff(s.getDni());
        if (existente != null && existente.getId() != s.getId()) {
            throw new Exception("ERROR ya existe staff con DNI: " + s.getDni());
        }
        dao.actualizar(s);
    }
}