import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class moduloVendedor extends JFrame {
    panelVentas panelventas;
    panelVisualizacion panelVisualizacion;
    private JTabbedPane pestañas;
    private manejadordedatos manejador;

    public moduloVendedor() {
        manejador = manejadordedatos.getInstancia();
        this.setSize(850, 700);
        this.setLayout(null);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.gray);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("VENTAS");
        setLocationRelativeTo(null);
        pestañas = new JTabbedPane();
        iniciarComponentes();

    }

    private void iniciarComponentes() {
        JButton salir = new JButton("Cerrar sesion");
        salir.setBounds(700, 0, 150, 25);
        salir.setBackground(Color.orange);
        this.add(salir);

        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autenticacion auten = new autenticacion();
                auten.setVisible(true);
                dispose();
            }
        });
        pestañas = new JTabbedPane();
        panelVisualizacion = new panelVisualizacion(this);
        panelventas = new panelVentas(this,panelVisualizacion);



        pestañas.add("Ventas", panelventas);
        pestañas.add("Visualizacion", panelVisualizacion);
        pestañas.setBounds(0, 30, 850, 650);
        add(pestañas);

    }
}
