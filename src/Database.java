import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://172.16.12.206/Presupuesto";
    ;
    static final String USER = "root";
    static final String PASS = "test";
    static Connection conn = null;

    public static void initDatabase() {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    static List<Empresa> getEmpresa() throws Exception {
        Statement stmtEmpresa;
        stmtEmpresa = Database.conn.createStatement();
        ResultSet rsEmpresa = stmtEmpresa.executeQuery("SELECT * from empresa");
        List<Empresa> listEmpresa = new ArrayList<>();

        while (rsEmpresa.next()) {
            Empresa e = new Empresa();
            String nombre = rsEmpresa.getString("Nombre");
            e.setNombre(nombre);
            String CIF = rsEmpresa.getString("CIF");
            e.setCIF(CIF);
            listEmpresa.add(e);
        }
        rsEmpresa.close();
        stmtEmpresa.close();

        return listEmpresa;
    }

    static List<Persona> getCliente() throws Exception {
        Statement stmtCliente;
        stmtCliente = Database.conn.createStatement();
        ResultSet rsCliente = stmtCliente.executeQuery("SELECT * from persona where departamento=0");
        List<Persona> listCliente = new ArrayList<>();

        while (rsCliente.next()) {
            Persona p = new Persona();
            String nombre = rsCliente.getString("Nombre");
            p.setNombre(nombre);
            String dni = rsCliente.getString("DNI");
            p.setDni(dni);
            listCliente.add(p);
        }
        rsCliente.close();
        stmtCliente.close();
        System.out.println();

        return listCliente;
    }

    static Persona[] getTrabajador() throws Exception {
        Statement stmtTrabajador;
        stmtTrabajador = Database.conn.createStatement();
        ResultSet rsTrabajador = stmtTrabajador.executeQuery("SELECT * from persona where departamento!=0");
        List<Persona> listTrabajador = new ArrayList<>();

        while (rsTrabajador.next()) {
            Persona p = new Persona();
            String nombre = rsTrabajador.getString("Nombre");
            p.setNombre(nombre);
            String dni = rsTrabajador.getString("DNI");
            p.setDni(dni);
            listTrabajador.add(p);
        }
        rsTrabajador.close();
        stmtTrabajador.close();

        Persona[] resultTrabajador = new Persona[listTrabajador.size()];
        listTrabajador.toArray(resultTrabajador);
        return resultTrabajador;
    }

    static List<Producto> getProducto() throws Exception {
        Statement stmtProducto;
        stmtProducto = Database.conn.createStatement();
        ResultSet rsProducto = stmtProducto.executeQuery("SELECT * from producto");
        List<Producto> listProducto = new ArrayList<>();

        while (rsProducto.next()) {
            Producto p = new Producto();
            String nombre = rsProducto.getString("Nombre");
            double precio = rsProducto.getDouble("preu");
            int stock = rsProducto.getInt("stock");
            int id = rsProducto.getInt("producto_id");
            p.setNombre(nombre);
            p.setPrecio(precio);
            p.setStock(stock);
            p.setProducto_id(id);
            listProducto.add(p);
        }
        rsProducto.close();
        stmtProducto.close();
        return listProducto;
    }

    static List<Presupuesto> getPresupuesto() throws Exception {
        Statement stmtPresupuesto;
        stmtPresupuesto = Database.conn.createStatement();
        ResultSet rsPresupuesto = stmtPresupuesto.executeQuery("SELECT * from presupuesto");
        List<Presupuesto> listPresupuesto = new ArrayList<>();

        while (rsPresupuesto.next()) {
            Presupuesto p = new Presupuesto();
            int id = rsPresupuesto.getInt("presupuesto_id");
            String CIF = rsPresupuesto.getString("CIF");
            String dni_cliente = rsPresupuesto.getString("DNI_cliente");
            String dni_trabajador = rsPresupuesto.getString("DNI_trabajador");
            String observacion = rsPresupuesto.getString("observacion");
            String fecha = rsPresupuesto.getString("datachange");
            p.setPresupueto_id(id);
            p.setCIF(CIF);
            p.setDNI_Cliente(dni_cliente);
            p.setDNI_Trabajador(dni_trabajador);
            p.setObservaciones(observacion);
            p.setFecha(fecha);
            listPresupuesto.add(p);
        }
        rsPresupuesto.close();
        stmtPresupuesto.close();
        return listPresupuesto;
    }


    static void insertEmpresa(Empresa empresa) throws Exception {
        Statement statement = conn.createStatement();
        statement.executeUpdate("INSERT INTO empresa values('" + empresa.getCIF() + "','" + empresa.getNombre() + "','" + empresa.getDireccion() + "','" + empresa.getTelefono() + "')");
        getEmpresa().add(empresa);
        statement.close();
    }

    static void insertClient(Persona cliente) throws Exception {
        Statement statement = conn.createStatement();
        statement.executeUpdate("INSERT INTO persona values('" + cliente.getDni() + "','" + cliente.getEmail() + "','" + cliente.getPass() + "','" + cliente.getNombre() + "','" + cliente.getApellidos() + "','" + 0 + "','" + cliente.getTelefono() + "')");
        statement.executeUpdate("INSERT INTO cliente values('" + cliente.getDni() + "')");
        getCliente().add(cliente);
        statement.close();
    }

    static void updateProduct(Producto p) throws Exception {
        Statement statement = conn.createStatement();
        statement.executeUpdate("Update producto set nombre='" + p.getNombre() + "',set stock='" + p.getStock() + "',set preu='" + p.getPrecio() + "' where producto_id='" + p.getProducto_id() + "'");
        statement.close();
    }

    static void insertProducto(Producto prod) throws Exception {
        Statement statement = conn.createStatement();
        statement.executeUpdate("INSERT INTO producto (nombre,stock,preu) values('" + prod.getNombre() + "','" + prod.getStock() + "','" + prod.getPrecio() + "')");
        getProducto().add(prod);
        statement.close();
    }

    static void insertPresupuesto(Empresa emp, Persona cliente, Persona trabajador, String observaciones) throws SQLException {
        Statement statement = conn.createStatement();
        statement.executeUpdate("INSERT INTO presupuesto (CIF,DNI_cliente,DNI_trabajador,observacion) values('" + emp.getCIF() + "','" + cliente.getDni() + "','" + trabajador.getDni() + "','" + observaciones + "')");
        try {
            AllPresupuestos p = new AllPresupuestos();
            p.updateTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
        statement.close();
    }

    static int getMaxNum() throws Exception {
        Statement stmt;
        stmt = Database.conn.createStatement();
        ResultSet rs = stmt.executeQuery("select max(presupuesto_id) from presupuesto");
        int num = 0;
        while (rs.next()) {
            num = rs.getInt("max(presupuesto_id)");
        }
        rs.close();
        stmt.close();
        return num;
    }

    static void insertIncluye(Producto prod, int num) throws SQLException {
        Statement statement = conn.createStatement();
        statement.executeUpdate("INSERT INTO incluye (presupuesto_id,id_producto) values('" + num + "','" + prod.getProducto_id() + "')");
        statement.close();
    }

    static void deleteProduct(int i) throws SQLException {
        Statement statement = conn.createStatement();
        statement.executeUpdate("DELETE FROM producto WHERE producto_id='" + i + "'");
        statement.close();
    }

    static void deletePresupuesto(int i) throws SQLException {
        Statement statement = conn.createStatement();
        statement.executeUpdate("DELETE FROM incluye WHERE presupuesto_id='"+i+"'");
        statement.executeUpdate("DELETE FROM presupuesto WHERE presupuesto_id='"+ i +"'");
        statement.close();
    }


    static boolean existeContraseña(Persona p) throws SQLException {
        Statement statement = Database.conn.createStatement();
        ResultSet rsContra = statement.executeQuery("SELECT pass,DNI from persona");
        List<Persona> listContra = new ArrayList<>();

        while (rsContra.next()) {
            String pass = rsContra.getString("pass");
            String dni = rsContra.getString("DNI");
            listContra.add(new Persona(pass, dni));
        }
        rsContra.close();
        statement.close();
        Persona[] resultPersona = new Persona[listContra.size()];
        listContra.toArray(resultPersona);
        for (int i = 0; i < resultPersona.length; i++) {
            if (p.pass == resultPersona[i].getPass()) {
                return true;
            }
        }
        return false;
    }


}
