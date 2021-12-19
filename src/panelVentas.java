import com.sun.org.apache.xpath.internal.objects.XString;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class panelVentas extends JPanel {
    private JFrame ventana;
    private manejadordedatos manejador;
    private JTable tabla;
    private JScrollPane scroll;
    private DefaultTableModel modelo;
    private JComboBox lista;
    private JComboBox lista2;
    int contadorCarro;
    private carro[] aux;
    private JLabel fecha=null;
    private panelVisualizacion visualizacion;

    public panelVentas(JFrame ventana, panelVisualizacion panelVisualizacion) {
        manejador = manejadordedatos.getInstancia();
        this.ventana = ventana;
        contadorCarro = 0;
        aux = new carro[5];
        visualizacion= panelVisualizacion;
        setLayout(null);
        setBackground(Color.red.darker());
        box();
        etiqueta();
        lista.setSelectedItem(0);
        agregartabla();
        boton();
    }


    public void etiqueta() {
        JLabel list = new JLabel("Listado General ");
        list.setBounds(5, 0, 150, 25);
        add(list);

        JLabel fp = new JLabel("Cliente: ");
        fp.setBounds(50, 30, 100, 20);
        add(fp);

        JLabel fi = new JLabel("Carros VIN: ");
        fi.setBounds(50, 70, 75, 20);
        add(fi);

        JLabel fe = new JLabel("Fecha ");
        fe.setBounds(600, 30, 100, 20);
        add(fe);

        fecha = new JLabel(String.valueOf(LocalDate.now()));
        fecha.setBounds(700, 30, 100, 25);
        add(fecha);
    }

    public void boton() {
        JButton añadir = new JButton("Añadir ");
        añadir.setBounds(360, 70, 200, 30);

        añadir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (contadorCarro < 5) {
                    carro venta = manejador.buscarCarro(lista2.getSelectedItem().toString());
                    aux[contadorCarro] = venta;
                    contadorCarro++;

                    actualizarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Solo se pueden cargar 5 carros a la venta");
                }
            }


        });

        JButton vender = new JButton("Vender");
        vender.setBounds(400, 510, 200, 30);

        vender.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(fecha.getText());
                if (!manejador.agregarventaCarro(lista.getSelectedItem().toString(), aux,fecha.getText())) {
                    JOptionPane.showMessageDialog(null, "Se alcanzo el maximo de ventas");
                } else {
                    aux = new carro[5];
                    contadorCarro = 0;
                    modelo.setRowCount(0);

                    visualizacion.actualizarTabla();

                }
            }
        });

        add(añadir);
        add(vender);
    }


    private void box() {
        lista = new JComboBox();
        for (int i = 0; i < manejadordedatos.getInstancia().contadorClientes; i++) {
            lista.addItem(manejadordedatos.getInstancia().clientes[i].nombre);

        }
        lista.setBounds(150, 30, 200, 25);
        add(lista);

        lista2 = new JComboBox();
        for (int i = 0; i < manejadordedatos.getInstancia().contadorCarros; i++) {
            lista2.addItem(manejadordedatos.getInstancia().carros[i].VIN);


        }
        lista2.setBounds(150, 70, 200, 25);
        add(lista2);

    }

    private void agregartabla() {

        modelo = new DefaultTableModel(null, panelVentas.nameOfColumnsvista);
        tabla = new JTable(modelo);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scroll = new JScrollPane(tabla);
        scroll.setBounds(50, 180, 750, 300);
        add(scroll);

    }

    public void actualizarTabla() {
        modelo.setRowCount(0);
        for (int i = 0; i < contadorCarro; i++) {
            Object[] aux2 = new Object[4];
            aux2[0] = aux[i].VIN;
            aux2[1] = aux[i].fabricante;
            aux2[2] = aux[i].modelo;
            aux2[3] = aux[i].precio;
            modelo.addRow(aux2);

        }

    }

    public static String[] nameOfColumnsvista = {
            "VIN",
            "Fabricante",
            "Modelo",
            "Precio",};


}
