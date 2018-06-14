public class Presupuesto {
    public int presupueto_id;
    private String CIF;
    private String DNI_Cliente;
    private String DNI_Trabajador;
    private String observaciones;
    private String Fecha;

    public void setPresupueto_id(int presupueto_id) {
        this.presupueto_id = presupueto_id;
    }

    public void setCIF(String CIF) {
        this.CIF = CIF;
    }

    public void setDNI_Cliente(String DNI_Cliente) {
        this.DNI_Cliente = DNI_Cliente;
    }

    public void setDNI_Trabajador(String DNI_Trabajador) {
        this.DNI_Trabajador = DNI_Trabajador;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public int getPresupueto_id() {
        return presupueto_id;
    }

    public String getCIF() {
        return CIF;
    }

    public String getDNI_Cliente() {
        return DNI_Cliente;
    }

    public String getDNI_Trabajador() {
        return DNI_Trabajador;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public String getFecha() {
        return Fecha;
    }
}
