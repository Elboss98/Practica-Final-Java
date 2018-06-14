import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NuevoPresupuesto {
    NewLine nw = new NewLine();
    private JTextPane textPane1;
    private JPanel mainPresupuesto;
    private JButton cancelLaButton;
    private JButton guardarPressupostButton;
    private JButton novaLiniaButton;
    private JButton esborrarLiniaButton;
    private JButton nouClientButton;
    private JButton novaEmpresaButton;
    private JComboBox comboClient;
    private JComboBox comboEmpresa;
    private JComboBox comboTrabajador;
    private TableModel tm;
    private JTable table1;
    private JScrollPane scrollPane1;
    private List<Producto> producto = new ArrayList<>();
    int selectedProd;


    public NuevoPresupuesto() throws Exception {
        nouClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                NewClient nc = new NewClient();
                nc.pack();
                nc.setModal(true);
                nc.setVisible(true);
                List<Persona> clientes = null;
                try {
                    clientes = Database.getCliente();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                comboClient.removeAllItems();
                for (int i = 0; i < clientes.size(); i++) {
                    comboClient.addItem(clientes.get(i).getNombre());
                }
            }
        });
        novaLiniaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                NewLine nl = null;
                try {
                    nl = new NewLine();
                    nl.pack();
                    nl.setModal(true);
                    nl.setVisible(true);
                    producto.add(nl.onOK().get(0));
                    updateTable();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        novaEmpresaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

        List<Empresa> empreses = Database.getEmpresa();
        for (int i = 0; i < empreses.size(); i++) {
            comboEmpresa.addItem(empreses.get(i).getNombre());
        }
        List<Persona> clientes = Database.getCliente();
        for (int i = 0; i < clientes.size(); i++) {
            comboClient.addItem(clientes.get(i).getNombre());
        }
        Persona[] trabajadores = Database.getTrabajador();
        for (int i = 0; i < trabajadores.length; i++) {
            comboTrabajador.addItem(trabajadores[i].getNombre());
        }
        novaEmpresaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                NewEmpresa ne = new NewEmpresa();
                ne.pack();
                ne.setModal(true);
                ne.setVisible(true);
                List<Empresa> empreses = null;
                try {
                    empreses = Database.getEmpresa();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                comboEmpresa.removeAllItems();
                for (int i = 0; i < empreses.size(); i++) {
                    comboEmpresa.addItem(empreses.get(i).getNombre());
                }
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
                        return "Nombre";
                    case 1:
                        return "Cantidad";
                    case 2:
                        return "Precio";
                    case 3:
                        return "Result";
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
                        return p.getNombre();
                    case 1:
                        return p.getCantidad();
                    case 2:
                        return p.getPrecio();
                    case 3:
                        return p.getResult();
                }
                throw new RuntimeException("Error valors");
            }

        };
        table1.setModel(tm);
        esborrarLiniaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                selectedProd = table1.getSelectedRow();
                producto.remove(selectedProd);
                System.out.println(selectedProd);
                updateTable();
            }
        });
        guardarPressupostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = comboClient.getSelectedIndex();
                int j = comboTrabajador.getSelectedIndex();
                int k = comboEmpresa.getSelectedIndex();
                int num = 0;
                String DNIcliente = null;
                String DNItrabajador = null;
                String CIF = null;
                String text = textPane1.getText();
                try {
                    DNIcliente = Database.getCliente().get(i).getDni();
                    DNItrabajador = Database.getTrabajador()[j].getDni();
                    CIF = Database.getEmpresa().get(k).getCIF();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                Persona cliente = new Persona();
                Persona trabajador = new Persona();
                Empresa empresa = new Empresa();
                cliente.setDni(DNIcliente);
                trabajador.setDni(DNItrabajador);
                empresa.setCIF(CIF);
                try {
                    Database.insertPresupuesto(empresa, cliente, trabajador, text);
                    num = Database.getMaxNum();
                    for (int l = 0; l < producto.size(); l++) {
                        Database.insertIncluye(producto.get(i), num);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    public JPanel getMainPresupuesto() {
        return mainPresupuesto;
    }

    private void updateTable() {
        ((AbstractTableModel) tm).fireTableDataChanged();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

    }
}