package negocio;

import java.time.LocalDate;
import java.util.Set;

import dao.FestivalDao;
import datos.Festival;
import datos.Staff;
import datos.UnidadVenta;

public class FestivalABM {

    FestivalDao dao = FestivalDao.getIntancia();

    public Festival traer(long idFestival) {
        return dao.traerFestival(idFestival);
    }

    public Festival traer(String nombre) {
        return dao.traerFestival(nombre);
    }

    public int agregar(String nombre, String temporada, LocalDate fechaInicio, LocalDate fechaFin,
            Set<UnidadVenta> unidadesHabilitadas, Set<Staff> staffGenera) throws Exception {
        if (traer(nombre) != null) throw new Exception("ERROR ya existe festival con nombre: " + nombre);
        Festival f = new Festival(nombre, temporada, fechaInicio, fechaFin,
                 unidadesHabilitadas, staffGenera);
        return dao.agregar(f);
    }

    public void modificar(Festival f) throws Exception {
        Festival existente = traer(f.getNombre());
        if (existente != null && existente.getId() != f.getId()) {
            throw new Exception("ERROR ya existe festival con nombre: " + f.getNombre());
        }
        dao.actualizar(f);
    }

    public void eliminar(long idFestival) throws Exception {
        Festival f = dao.traerFestival(idFestival);
        if (f == null) throw new RuntimeException("ERROR: No existe festival con ID: " + idFestival);
        dao.eliminar(f);
    }

  
    public Set<UnidadVenta> traerUnidadesVentaPorStaffDeFestival(long minStaff) {
        return dao.traerUnidadesVentaPorStaffDeFestival(minStaff);
    }
}