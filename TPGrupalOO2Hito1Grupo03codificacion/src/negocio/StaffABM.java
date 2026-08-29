package negocio;

import dao.StaffDao;
import datos.Staff;

public class StaffABM {
	

	StaffDao dao = StaffDao.getIntancia();

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

}