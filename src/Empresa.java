public class Empresa {

    private String cif;
    private String nombre;
    private String direccion;
    private int telefono;

    public Empresa(String CIF) {
        this.setCIF(CIF);
    }

    public Empresa() {
        this.setCIF(getCIF());
        this.setNombre(getNombre());
        this.setDireccion(getDireccion());
        this.setTelefono(getTelefono());
    }
    public String toString() {
        return String.format(getCIF(), getNombre(), getDireccion(), getTelefono());
    }

    public String getCIF() {
        return cif;
    }

    public void setCIF(String CIF) {
        this.cif = CIF;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }
}
