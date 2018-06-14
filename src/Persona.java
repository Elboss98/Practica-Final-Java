public class Persona {
    private String dni;
    private String email;
    public String pass;
    private String nombre;
    private String apellidos;
    private String telefono;

    Persona(String DNI, String dni) {
        this.dni = DNI;
        this.email = email;
        this.pass = pass;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
    }

    public Persona() {
        this.setDni(getDni());
        this.setEmail(getEmail());
        this.setPass(getPass());
        this.setNombre(getNombre());
        this.setApellidos(getApellidos());
        this.setTelefono(getTelefono());
    }

    public String getDni() {
        return this.dni;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPass() {
        return this.pass;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String toString() {
        return String.format(dni, email, pass, nombre, apellidos, telefono);
    }
}