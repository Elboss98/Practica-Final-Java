import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class NewClient extends JDialog {
    private JPanel Nuevocliente;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField FieldNombre;
    private JTextField FieldApellidos;
    private JTextField FieldEmail;
    private JTextField FieldDireccion;
    private JTextField FieldDNI;
    private JTextField FieldTelefono;

    public NewClient() {
        setContentPane(Nuevocliente);
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
        Nuevocliente.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public static void main(String[] args) {
        NewClient dialog = new NewClient();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    public JPanel getNuevocliente() {
        return Nuevocliente;
    }

    private void onOK() throws Exception {
        // add your code here
        char[] caracteres;
        caracteres = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        String pass = "";
        for (int i = 0; i < 10; i++) {
            pass += caracteres[new Random().nextInt(62)];
        }
        Persona p1 = new Persona();
        p1.setDni(FieldDNI.getText());
        p1.setEmail(FieldEmail.getText());
        p1.setPass(pass);
        p1.setNombre(FieldNombre.getText());
        p1.setApellidos(FieldApellidos.getText());
        p1.setTelefono(FieldTelefono.getText());
        dispose();
        while (Database.existeContraseña(p1)) {
            pass = "";
            for (int i = 0; i < 10; i++) {
                pass += caracteres[new Random().nextInt(62)];
            }
            p1.setPass(pass);
        }
        if (!Database.existeContraseña(p1)) {
            Database.insertClient(p1);
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
