import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class NewLine extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;

    JTextField FieldCantidad;
    JComboBox<Producto> ProductoBox;

    List<Producto> productos = Database.getProducto();

    List<Producto> Products = new ArrayList<>();

    public NewLine() throws Exception {
        ProductoBox.removeAllItems();
        for (Producto producto : productos) {
            ProductoBox.addItem(producto);
        }

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    onOK();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    List<Producto> onOK() throws Exception {
        // add your code here
        int i = ProductoBox.getSelectedIndex();
        Producto p = productos.get(i);
        p.setCantidad(Double.parseDouble(FieldCantidad.getText()));
        p.setPrecio(p.precio);
        p.setProducto_id(p.producto_id);
        double result = p.getCantidad() * p.getPrecio();
        p.setResult(result);
        Products.add(p);
        dispose();
        return Products;

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public void main(String[] args) throws Exception {
        NewLine dialog = new NewLine();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }


}
