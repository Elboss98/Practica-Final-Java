import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private static CardLayout cl = new CardLayout();
    private static CardLayout cl2 = new CardLayout();
    static JFrame jf = new JFrame();
    static JPanel jp = new JPanel();

    public static void main(String[] args) throws Exception {
        //Iniciam la conexió amb la base de dades
        Database.initDatabase();

        jf.setLayout(new BorderLayout());
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setJMenuBar(doMenu());
        //Definim l'objecte que s'obrirá quan executem es programa
        NuevoPresupuesto p = new NuevoPresupuesto();
        cl.addLayoutComponent(p.getMainPresupuesto(), "mainPresupuesto");
        jp.setLayout(cl);
        jp.add(p.getMainPresupuesto());
        cl.show(jp, "mainPresupuesto");
        jf.setContentPane(jp);
        jf.pack();
        jf.setVisible(true);


    }
    //Funció que creara es menú
    static JMenuBar doMenu() {
        JMenuBar jmb = new JMenuBar();
        JMenu menuFile = new JMenu("Menu");
        //Cream totes les opcions del menú
        JMenuItem open = new JMenuItem("Crear Presupuestos");
        JMenuItem newOpt = new JMenuItem("Gestion de Productos");
        JMenuItem newa = new JMenuItem("Presupuestos");
        JMenuItem openOpt = new JMenuItem("Cerrar sesion");

        //Anyadim les opcions al menú
        menuFile.add(open);
        menuFile.add(newOpt);
        menuFile.add(newa);
        menuFile.add(openOpt);

        //Funció quan pitjam la primera opcio, on obrirá la pantalla seleccionada
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cl.show(jp, "mainPresupuesto");
                jf.setContentPane(jp);
                jf.pack();
                jf.setVisible(true);
            }
        });
        //Funció que obrirar la pantalla de la segona opció
        newOpt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jf.setJMenuBar(doMenu());

                GestionDeProductos gdp = null;
                try {
                    gdp = new GestionDeProductos();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                JPanel card2 = new JPanel();
                card2.setLayout(cl2);
                card2.add(gdp.getMainProducto(), "mainProducto");

                cl2.show(card2, "mainProducto");
                jf.setContentPane(card2);
                jf.pack();
                jf.setVisible(true);
            }
        });
        newa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jf.setJMenuBar(doMenu());

                AllPresupuestos ap = null;
                try {
                    ap = new AllPresupuestos();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                JPanel card2 = new JPanel();
                card2.setLayout(cl2);
                card2.add(ap.getMainPrep(), "mainPrep");

                cl2.show(card2, "mainPrep");
                jf.setContentPane(card2);
                jf.pack();
                jf.setVisible(true);
            }
        });
        jmb.add(menuFile);
        return jmb;
    }

}

