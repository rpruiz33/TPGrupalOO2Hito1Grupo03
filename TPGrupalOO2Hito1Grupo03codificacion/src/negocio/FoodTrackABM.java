package negocio;

import java.util.HashSet;
import java.util.List;

import dao.FoodTrackDao;
import dao.UnidadVentaDao;
import datos.FoodTrack;
import datos.Pedido;
import datos.Plato;
import datos.Staff;
import datos.UnidadVenta;

public class FoodTrackABM {

    FoodTrackDao dao = FoodTrackDao.getIntancia();
    UnidadVentaDao unidadVentaDao = UnidadVentaDao.getIntancia();

    public FoodTrack traer(long idUnidadVenta) {
        return dao.traerFoodTrack(idUnidadVenta);
    }

    // CORREGIDO: Retorna long en lugar de int
    public long agregar(String codigo, String nombreComercial, double superficieM2, Staff responsable,
                        String patente, boolean requiereElectricidad) throws Exception {
        
        // CORREGIDO: Validar formato de código antes de ir a la DB
        if (codigo == null || codigo.length() != 10) {
            throw new Exception("ERROR el codigo de unidad debe tener 10 caracteres");
        }
        
        if (dao.traerFoodTrackPorCodigo(codigo) != null) {
            throw new Exception("ERROR ya existe una unidad con codigo: " + codigo);
        }

        FoodTrack f = new FoodTrack(null, codigo, nombreComercial, superficieM2,
                new HashSet<Pedido>(), new HashSet<Plato>(), new HashSet<Staff>(), responsable,
                patente, requiereElectricidad);
                
        return dao.agregar(f);
    }

    public void modificar(FoodTrack f) throws Exception {
        dao.actualizar(f);
    }

    public void eliminar(long idUnidadVenta) throws Exception {
        FoodTrack f = dao.traerFoodTrack(idUnidadVenta);
        if (f == null) throw new RuntimeException("ERROR: No existe food truck con ID: " + idUnidadVenta);
        dao.eliminar(f);
    }

    public List<FoodTrack> traer() {
        return dao.traerFoodTracks();
    }

    public List<FoodTrack> traerPorElectricidad(boolean requiereElectricidad) {
        return dao.traerFoodTracksPorElectricidad(requiereElectricidad);
    }

    public void asignarStaff(long idUnidadVenta, Staff staff) throws Exception {
        // 1. Intentar traer con fetch si tenés el método especializado
        UnidadVenta u = unidadVentaDao.traerUnidadVentaConStaff(idUnidadVenta);
        
        // 2. Si devuelve null (común cuando la colección está vacía), traer la entidad directamente
        if (u == null) {
            u = unidadVentaDao.traer(idUnidadVenta);
        }
        
        // 3. Validar si realmente no existe la unidad
        if (u == null) {
            throw new RuntimeException("ERROR: No existe unidad con ID: " + idUnidadVenta);
        }
        
        // 4. Asignar el staff y persistir los cambios
        u.asignarStaff(staff);
        unidadVentaDao.actualizar(u);
    }

    public void ofrecerPlato(long idUnidadVenta, Plato plato) throws Exception {
        FoodTrack f = dao.traerFoodTrack(idUnidadVenta);
        if (f == null) throw new RuntimeException("ERROR: No existe unidad con ID: " + idUnidadVenta);
        f.getPlatosOfrecidos().add(plato);
        unidadVentaDao.actualizar(f);
    }
}