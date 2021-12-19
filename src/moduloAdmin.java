import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class moduloAdmin extends JFrame {
    panelCarro panelcarro;
    panelVendedor panelvendedor;
    panelCliente panelcliente;
   // panelConfiguracion panelConfiguracion;
    private JTabbedPane pestañas;
    private manejadordedatos manejador;

    public moduloAdmin() {
        manejador = manejadordedatos.getInstancia();
        this.setSize(850, 700);
        this.setLayout(null);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.gray);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("ADMINISTRACION");
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
                serializar.serialize("carros.bin", manejador.carros);
                serializar.serialize("vendedores.bin", manejador.vendores);
                serializar.serialize("clientes.bin", manejador.clientes);
                serializar.serialize("venta.bin", manejador.ventas);

            }
        });
        pestañas = new JTabbedPane();
        panelcarro = new panelCarro(this);
        panelvendedor = new panelVendedor(this);
        panelcliente = new panelCliente(this);
     //   panelConfiguracion = new panelConfiguracion(this);


        pestañas.add("Carros", panelcarro);
        pestañas.add("Vendedores", panelvendedor);
        pestañas.add("Clientes", panelcliente);
      //  pestañas.add("Configuracion",panelConfiguracion);
        pestañas.setBounds(0, 30, 850, 650);
        add(pestañas);

    }

}
