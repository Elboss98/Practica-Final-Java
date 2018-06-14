public class Producto {
    private double cantidad;
    private double result;

    private String Nombre;
    private double stock;
    public double precio;
    public int producto_id;

    public Producto(String nombre, double precio) {
        this.Nombre = nombre;
        this.precio = precio;
    }

    public Producto(String nombre, double preu, int id) {

    }

    public Producto() {

    }

    public int getProducto_id() {
        return producto_id;
    }

    public String getNombre() {
        return Nombre;
    }

    public double getStock() {
        return stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setProducto_id(int producto_id) {
        this.producto_id = producto_id;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getCantidad() {
        return cantidad;
    }

    public double getResult() {
        return result;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public void setResult(double result) {
        this.result = result;
    }


    Producto(String Nombre, double preu, int stock, int id) {
        this.Nombre = Nombre;
    }

    public String toString() {
        return String.format(Nombre, cantidad, precio);
    }
}
