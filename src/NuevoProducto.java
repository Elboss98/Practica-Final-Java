import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class NuevoProducto extends JDialog {
    List<Producto> Products = new ArrayList<>();
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField FieldNombre;
    private JTextField FieldPrecio;
    private JTextField FIeldStock;

    public NuevoProducto() {
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

    public static void main(String[] args) {
        NuevoProducto dialog = new NuevoProducto();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    List<Producto> getProducts() {
        return Products;
    }

    void onOK() throws Exception {
        // add your code here
        Producto p = new Producto();
        p.setNombre(FieldNombre.getText());
        p.setStock(Integer.parseInt(FIeldStock.getText()));
        p.setPrecio((Double.parseDouble(FieldPrecio.getText())));
        Products.add(p);
        Database.insertProducto(p);
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
