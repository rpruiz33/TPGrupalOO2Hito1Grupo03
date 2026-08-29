package datos;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class UnidadVenta {

    private Long id;
    private String codigo;
    private String nombreComercial;
    private double superficieM2;

    private Set<Pedido> pedidos = new HashSet<>();
    private Set<Plato> platosOfrecidos = new HashSet<>();
    private Set<Staff> staffPuesto = new HashSet<>();

    private Staff responsable;

    public UnidadVenta() {
    }

    public UnidadVenta(Long id, String codigo, String nombreComercial, double superficieM2,
                        Set<Pedido> pedidos, Set<Plato> platosOfrecidos,
                        Set<Staff> staffPuesto, Staff responsable) {

        this.id = id;
        this.codigo = codigo;
        this.nombreComercial = nombreComercial;
        this.superficieM2 = superficieM2;
        this.pedidos = pedidos;
        this.platosOfrecidos = platosOfrecidos;
        this.staffPuesto = staffPuesto;
        this.responsable = responsable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public double getSuperficieM2() {
        return superficieM2;
    }

    public void setSuperficieM2(double superficieM2) {
        this.superficieM2 = superficieM2;
    }

    public Set<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Set<Plato> getPlatosOfrecidos() {
        return platosOfrecidos;
    }

    public void setPlatosOfrecidos(Set<Plato> platosOfrecidos) {
        this.platosOfrecidos = platosOfrecidos;
    }

    public Set<Staff> getStaffPuesto() {
        return staffPuesto;
    }

    public void setStaffPuesto(Set<Staff> staffPuesto) {
        this.staffPuesto = staffPuesto;
    }

    public Staff getResponsable() {
        return responsable;
    }

    public void setResponsable(Staff responsable) {
        this.responsable = responsable;
    }

    public void asignarStaff(Staff staff) {
        staffPuesto.add(staff);
    }

    public void removerStaff(Staff staff) {
        staffPuesto.remove(staff);
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        UnidadVenta that = (UnidadVenta) o;

        return Double.compare(superficieM2, that.superficieM2) == 0
                && Objects.equals(id, that.id)
                && Objects.equals(codigo, that.codigo)
                && Objects.equals(nombreComercial, that.nombreComercial)
                && Objects.equals(pedidos, that.pedidos)
                && Objects.equals(platosOfrecidos, that.platosOfrecidos)
                && Objects.equals(staffPuesto, that.staffPuesto)
                && Objects.equals(responsable, that.responsable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codigo, nombreComercial, superficieM2,
                pedidos, platosOfrecidos, staffPuesto, responsable);
    }

    @Override
    public String toString() {
        return "UnidadVenta{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", nombreComercial='" + nombreComercial + '\'' +
                ", superficieM2=" + superficieM2 +
                ", pedidos=" + pedidos +
                ", platosOfrecidos=" + platosOfrecidos +
                ", staffPuesto=" + staffPuesto +
                ", responsable=" + responsable +
                '}';
    }
}