import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class moduloVendedor extends JFrame {
    panelnuevaVenta panelnuevaVenta;
    panelVentas panelVentas;
    private JTabbedPane pestañas;

    public moduloVendedor() {
        this.setSize(850, 700);
        this.setLayout(null);
        this.getContentPane().setBackground(Color.gray);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("MODULO VENDEDORES");
        setLocationRelativeTo(null);
        pestañas = new JTabbedPane();
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        JLabel saludo = new JLabel("Bienvenido ");
        saludo.setBounds(580, 30, 70, 20);
        this.add(saludo);

        JLabel nom = new JLabel();
        nom.setBounds(650, 30, 200, 20);
        this.add(nom);

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
        panelnuevaVenta = new panelnuevaVenta(this);
        panelVentas = new panelVentas(this);

        pestañas.add("Nueva venta", panelnuevaVenta);
        pestañas.add("Ventas", panelVentas);
        pestañas.setBounds(0, 30, 850, 650);
        add(pestañas);

    }

}
}
