import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class panelCliente extends JPanel {
    private JFrame ventana;
    private manejadordedatos manejador;
    private JTable tabla;
    private JScrollPane scroll;
    private DefaultTableModel modelo;
    private JPanel panel3;

    public panelCliente(JFrame ventana) {
        manejador = manejadordedatos.getInstancia();
        this.ventana = ventana;
        setLayout(null);
        setBackground(Color.red.darker());
        agregartabla();
        boton();
        panel();
       // if (manejador.clientes[0]!=null){
         //   panel();
       // }
    }


    private void panel() {
        panel3 = new JPanel();
        panel3.setBounds(570, 250, 200, 200);
        this.add(panel3);
    }

    private void boton() {
        JButton crear = new JButton("Crear");
        crear.setBounds(570, 100, 100, 25);

        crear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearCliente nc = new crearCliente();
                nc.setVisible(true);
                ventana.dispose();
            }
        });


        JButton cargam = new JButton("Carga Masiva");
        cargam.setBounds(675, 100, 125, 25);

        JButton actualizar = new JButton("Actualizar");
        actualizar.setBounds(570, 135, 100, 25);

        actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (tabla.getSelectedRow()>=0){
                    actualizarCliente ac = new actualizarCliente(manejadordedatos.getInstancia().buscarCliente(Integer.parseInt(modelo.getValueAt(tabla.getSelectedRow(), 0).toString())));
                    ac.setVisible(true);
                    ventana.dispose();
                }else {
                    JOptionPane.showMessageDialog(ventana,"seleccione algo de la tabla o no hay datos");
                }

            }
        });

        JButton eliminiar = new JButton("Eliminar");
        eliminiar.setBounds(675, 135, 125, 25);

        JButton expo = new JButton("Exportar a listado PDF");
        expo.setBounds(570, 170, 230, 25);


        add(crear);
        add(cargam);
        add(actualizar);
        add(eliminiar);
        add(expo);

    }

    private void agregartabla() {
        if (manejador.espacioClienteVacio()) {
            modelo = new DefaultTableModel(null, cliente.nameOfColumns);
            tabla = new JTable(modelo);
            tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            scroll = new JScrollPane(tabla);
            scroll.setBounds(20, 55, 500, 500);
            add(scroll);
        } else {
            modelo = new DefaultTableModel(null, cliente.nameOfColumns);
            for (int i = 0; i < manejador.contadorClientes; i++) {
                if (manejador.clientes[i] != null) {
                    modelo.addRow(manejador.clientes[i].getAsRow());
                }
            }

            tabla = new JTable(modelo);
            tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            scroll = new JScrollPane(tabla);
            scroll.setBounds(20, 55, 500, 500);
            add(scroll);
        }
    }

    public void actualizarTabla() {
        modelo.setRowCount(0);
        for (int i = 0; i < manejador.contadorClientes; i++) {
            modelo.addRow(manejador.clientes[i].getAsRow());
        }
        tabla.repaint();
    }


}
