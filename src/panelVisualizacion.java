import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class panelVisualizacion extends JPanel {
    private JFrame ventana;
    private manejadordedatos manejador;
    private JTable tabla;
    private JScrollPane scroll;
    private DefaultTableModel modelo;
    private JLabel nombreus;

    public panelVisualizacion(JFrame ventana) {
        manejador = manejadordedatos.getInstancia();
        this.ventana = ventana;
        setLayout(null);
        setBackground(Color.red.darker());
        agregartabla();
        etiqueta();
        boton();

    }

    private void etiqueta() {
        nombreus = new JLabel("Facturas del Vendedor " + manejador.nombreUsuario);
        nombreus.setBounds(250, 20, 450, 20);

        add(nombreus);
    }

    private void boton() {
        JButton ver = new JButton("Visualizar Factura");
        ver.setBounds(550, 350, 250, 30);

        ver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tabla.getSelectedRow() > -1) {
                    File path = new File("Factura" + modelo.getValueAt(tabla.getSelectedRow(), 0) + ".pdf");
                    try {
                        Desktop.getDesktop().open(path);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });

        add(ver);
    }

    private void agregartabla() {
        if (manejador.ventas[0] == null) {
            modelo = new DefaultTableModel(null, venta.nameOfColumns);
            tabla = new JTable(modelo);
            tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            scroll = new JScrollPane(tabla);
            scroll.setBounds(30, 55, 500, 500);
            add(scroll);
        } else {
            modelo = new DefaultTableModel(null, venta.nameOfColumns);
            for (int i = 0; i < manejador.contadorVentas; i++) {
                if (manejador.ventas[i] != null) {
                    Object[] aux = new Object[5];

                    aux[0] = manejador.ventas[i].nofactura;
                    aux[1] = manejador.ventas[i].nitcliente;
                    aux[2] = manejador.ventas[i].nombrecliente;
                    aux[3] = manejador.ventas[i].fecha;
                    aux[4] = manejador.ventas[i].total;
                    modelo.addRow(aux);

                }
            }

            tabla = new JTable(modelo);
            tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            scroll = new JScrollPane(tabla);
            scroll.setBounds(30, 55, 500, 500);
            add(scroll);
        }
    }

    public void actualizarTabla() {
        modelo.setRowCount(0);

        for (int i = 0; i < manejador.contadorVentas; i++) {
            if (manejador.ventas[i] != null) {
                Object[] aux = new Object[5];

                aux[0] = manejador.ventas[i].nofactura;
                aux[1] = manejador.ventas[i].nitcliente;
                aux[2] = manejador.ventas[i].nombrecliente;
                aux[3] = manejador.ventas[i].fecha;
                aux[4] = manejador.ventas[i].total;
                modelo.addRow(aux);

            }
        }


    }


}
