import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class GestionDeProductos {
    private JButton modificarProductoButton;
    private JButton borrarProductoButton;
    private JButton añadirProductoButton;
    private JPanel mainProducto;
    private JTable table1;
    private TableModel tm;
    private List<Producto> producto = Database.getProducto();

    public GestionDeProductos() throws Exception {
        añadirProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NuevoProducto np = new NuevoProducto();
                np.pack();
                np.setModal(true);
                np.setVisible(true);
                producto.add(np.getProducts().get(0));
                updateTable();
            }
        });
        borrarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedProd = table1.getSelectedRow();
                int id = producto.get(selectedProd).producto_id;
                try {
                    Database.deleteProduct(id);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                producto.remove(selectedProd);
                updateTable();
            }
        });
        tm = new AbstractTableModel() {
            @Override
            public int getRowCount() {
                return producto.size();
            }

            @Override
            public String getColumnName(int col) {
                switch (col) {
                    case 0:
                        return "Id_Producto";
                    case 1:
                        return "Nombre";
                    case 2:
                        return "Stock";
                    case 3:
                        return "Precio";
                }
                throw new RuntimeException("Error Titles");
            }

            @Override
            public int getColumnCount() {
                return 4;
            }

            @Override
            public Class getColumnClass(int IndexColumn) {
                return getValueAt(0, IndexColumn).getClass();
            }

            @Override
            public Object getValueAt(int i, int i1) {
                Producto p = producto.get(i);
                switch (i1) {
                    case 0:
                        return p.getProducto_id();
                    case 1:
                        return p.getNombre();
                    case 2:
                        return p.getStock();
                    case 3:
                        return p.getPrecio();
                }
                throw new RuntimeException("Error valors");
            }

        };
        table1.setModel(tm);

        modificarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ModificarProducto mp = null;
                try {
                    mp = new ModificarProducto();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                mp.pack();
                mp.setModal(true);
                mp.setVisible(true);
            }
        });
    }

    public JPanel getMainProducto() {
        return mainProducto;
    }

    private void updateTable() {
        ((AbstractTableModel) tm).fireTableDataChanged();
    }
}

