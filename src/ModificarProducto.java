import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ModificarProducto extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox comboBox1;
    private JTextField FieldNombre;
    private JTextField FieldStock;
    private JTextField FieldPreu;
    private List<Producto> list = Database.getProducto();

    public ModificarProducto() throws Exception {
        for (int i = 0; i < list.size(); i++) {
            comboBox1.addItem(list.get(i).getNombre());
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

    private void onOK() throws Exception {
        // add your code here
        Producto p = new Producto();
        p.setNombre(FieldNombre.getText());
        p.setStock(Integer.parseInt(FieldStock.getText()));
        p.setPrecio((Double.parseDouble(FieldPreu.getText())));
        Database.updateProduct(p);
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        ModificarProducto dialog = null;
        try {
            dialog = new ModificarProducto();
        } catch (Exception e) {
            e.printStackTrace();
        }
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
