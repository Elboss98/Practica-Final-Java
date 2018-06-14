import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class AllPresupuestos {
    private JLabel presupuestosLabel;
    private JPanel MainPrep;
    private JButton eliminarPresupuestpSeleccionadoButton;
    private JTable table1;
    private TableModel tm;
    private List<Presupuesto> list = Database.getPresupuesto();

    public AllPresupuestos() throws Exception {
        eliminarPresupuestpSeleccionadoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedPres = table1.getSelectedRow();
                int id = list.get(selectedPres).presupueto_id;
                try {
                    Database.deletePresupuesto(id);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                list.remove(selectedPres);
                updateTable();
            }
        });
        tm = new AbstractTableModel() {
            @Override
            public int getRowCount() {
                return list.size();
            }

            @Override
            public String getColumnName(int col) {
                switch (col) {
                    case 0:
                        return "Presupuesto_id";
                    case 1:
                        return "CIF";
                    case 2:
                        return "DNI_cliente";
                    case 3:
                        return "DNI_trabajador";
                    case 4:
                        return "Observaciones";
                    case 5:
                        return "Fecha";
                }
                throw new RuntimeException("Error Titles");
            }

            @Override
            public int getColumnCount() {
                return 6;
            }

            @Override
            public Class getColumnClass(int IndexColumn) {
                return getValueAt(0, IndexColumn).getClass();
            }

            @Override
            public Object getValueAt(int i, int i1) {
                Presupuesto p = list.get(i);
                switch (i1) {
                    case 0:
                        return p.getPresupueto_id();
                    case 1:
                        return p.getCIF();
                    case 2:
                        return p.getDNI_Cliente();
                    case 3:
                        return p.getDNI_Trabajador();
                    case 4:
                        return p.getObservaciones();
                    case 5:
                        return p.getFecha();
                }
                throw new RuntimeException("Error valors");
            }

        };
        table1.setModel(tm);
    }

    public JPanel getMainPrep() {
        return MainPrep;
    }

    void updateTable() {
        ((AbstractTableModel) tm).fireTableDataChanged();
    }
}
