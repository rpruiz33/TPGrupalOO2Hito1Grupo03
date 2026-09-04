package negocio;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dao.FoodTrackDao;
import dao.UnidadVentaDao;
import datos.FoodTrack;
import datos.Pedido;
import datos.Plato;
import datos.Staff;
import datos.UnidadVenta;

public class FoodTrackABM {

    private static FoodTrackABM instancia;
    private FoodTrackDao dao = FoodTrackDao.getIntancia();
    private UnidadVentaDao unidadVentaDao = UnidadVentaDao.getIntancia();

    protected FoodTrackABM() {}

    public static FoodTrackABM getInstancia() {
        if (instancia == null) {
            instancia = new FoodTrackABM();
        }
        return instancia;
    }

    public FoodTrack traer(long idUnidadVenta) {
        return dao.traerFoodTrack(idUnidadVenta);
    }

    
    public long agregar(String codigo, String nombreComercial, double superficieM2, Staff responsable,
            String patente, boolean requiereElectricidad) throws Exception {

    	if (codigo == null || codigo.length() != 10) {
    		throw new Exception("ERROR el codigo de unidad debe tener 10 caracteres");
    	}
    	
    	if (dao.traerFoodTrackPorCodigo(codigo) != null) {
    		throw new Exception("ERROR ya existe una unidad con codigo: " + codigo);
    	}

    	FoodTrack f = new FoodTrack(nombreComercial, codigo, superficieM2,
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

    public Set<FoodTrack> traer() {
        return dao.traerFoodTracks();
    }

    public Set<FoodTrack> traerPorElectricidad(boolean requiereElectricidad) {
        return dao.traerFoodTracksPorElectricidad(requiereElectricidad);
    }

    public void asignarStaff(long idUnidadVenta, Staff staff) throws Exception {
    
        UnidadVenta u = unidadVentaDao.traerUnidadVentaConStaff(idUnidadVenta);
        
        if (u == null) {
            u = unidadVentaDao.traer(idUnidadVenta);
        }
        
        if (u == null) {
            throw new RuntimeException("ERROR: No existe unidad con ID: " + idUnidadVenta);
        }
        
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