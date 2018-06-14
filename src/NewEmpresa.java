import javax.swing.*;
import java.awt.event.*;

public class NewEmpresa extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField FieldCIF;
    private JTextField FieldNombre;
    private JTextField FIeldDireccion;
    private JTextField FieldTelefono;

    public NewEmpresa() {
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
        NewEmpresa dialog = new NewEmpresa();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void onOK() throws Exception {
        // add your code here
        Empresa e1 = new Empresa();
        e1.setCIF(FieldCIF.getText());
        e1.setNombre(FieldNombre.getText());
        e1.setDireccion(FIeldDireccion.getText());
        e1.setTelefono(Integer.parseInt(FieldTelefono.getText()));
        Database.insertEmpresa(e1);
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
