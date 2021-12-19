import com.google.gson.Gson;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.Font;
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
    }


    private void panel() {

        panel3 = new JPanel();
        panel3.setBounds(570, 250, 200, 200);
        this.add(panel3);

        int conM = 0;
        int conF = 0;
        for (int i = 0; i < manejador.contadorClientes; i++) {
            if (String.valueOf(manejador.clientes[i].genero).equalsIgnoreCase("m")) {
                conM++;
            } else
                conF++;
        }

        DefaultPieDataset datos = new DefaultPieDataset();
        datos.setValue("Masculino", conM);
        datos.setValue("Femenino", conF);

        JFreeChart graficapai = ChartFactory.createPieChart(
                "Genero de Clientes",       //titutlo
                datos,                             //datos
                true,                       //nobre de las categorias
                true,                       //herramientas
                false                          //generacion de url
        );

        ChartPanel panel = new ChartPanel(graficapai);
        panel.setMouseWheelEnabled(true);
        panel.setPreferredSize(new Dimension(200, 200));

        panel3.add(panel, BorderLayout.NORTH);
        panel3.revalidate();
    }

    private void boton() {
        JButton crear = new JButton("Crear");
        crear.setBounds(570, 100, 100, 25);

        crear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (manejador.contadorClientes < 75) {
                    crearCliente nc = new crearCliente();
                    nc.setVisible(true);
                    panel();
                    ventana.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "NO se puede agregar mas clientes");
                }
            }
        });


        JButton cargam = new JButton("Carga Masiva");
        cargam.setBounds(675, 100, 125, 25);

        cargam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser archivo = new JFileChooser();
                FileNameExtensionFilter filtro = new FileNameExtensionFilter("archivos Json", "json", "JSON");
                archivo.setFileFilter(filtro);
                int valor = archivo.showOpenDialog(ventana);
                if (valor == JFileChooser.APPROVE_OPTION) {
                    File recibido = archivo.getSelectedFile();
                    try {
                        Scanner contenido = new Scanner(recibido);
                        String texto = "";
                        while (contenido.hasNext()) {
                            texto += contenido.nextLine() + "\n";
                        }

                        Gson gson = new Gson();
                        cliente[] cl = gson.fromJson(texto, cliente[].class);

                        for (cliente recorrer : cl) {
                            if (!manejador.verificarCliente(recorrer.dpi)) {
                                if (manejador.contadorClientes < 75) {
                                    manejador.agregarCliente(new cliente(recorrer.dpi, recorrer.nit, recorrer.nombre, recorrer.genero, recorrer.correo));
                                }
                            }
                        }
                        actualizarTabla();
                        panel();

                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(panelCliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        JButton actualizar = new JButton("Actualizar");
        actualizar.setBounds(570, 135, 100, 25);

        actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (tabla.getSelectedRow() >= 0) {
                    actualizarCliente ac = new actualizarCliente(manejadordedatos.getInstancia().buscarCliente(Integer.parseInt(modelo.getValueAt(tabla.getSelectedRow(), 0).toString())));
                    ac.setVisible(true);
                    panel();
                    ventana.dispose();
                } else {
                    JOptionPane.showMessageDialog(ventana, "seleccione algo de la tabla o no hay datos");
                }

            }
        });

        JButton eliminiar = new JButton("Eliminar");
        eliminiar.setBounds(675, 135, 125, 25);

        eliminiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tabla.getSelectedRow() >= 0) {
                    manejador.eliminarCliente(Integer.parseInt(modelo.getValueAt(tabla.getSelectedRow(), 0).toString()));
                    modelo.removeRow(tabla.getSelectedRow());
                } else {
                    JOptionPane.showMessageDialog(ventana, "Seleccione elemento de la lista, o la lista esta vacia");
                }
            }
        });


        JButton expo = new JButton("Exportar a listado PDF");
        expo.setBounds(570, 170, 230, 25);

        expo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Document doc = new Document();
                FileOutputStream ficheroPdf = null;
                try {
                    ficheroPdf = new FileOutputStream("Clientes.pdf");
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
                try {
                    PdfWriter.getInstance(doc, ficheroPdf).setInitialLeading(25);
                } catch (DocumentException documentException) {
                    documentException.printStackTrace();
                }
                doc.open();

                try {
                    doc.add(new Paragraph("Lista de Clientes \n",
                            FontFactory.getFont("calibre",   // fuente
                                    14,                            // tamaño
                                    Font.BOLD,                   // estilo
                                    BaseColor.BLACK)));             // color

                } catch (DocumentException documentException) {
                    documentException.printStackTrace();
                }

                int numColumns = 5;                                 //numero de columnas que tendra la tabla en el pdf
                PdfPTable tabla = new PdfPTable(numColumns);
                tabla.addCell("DPI");                        //nombre de las columnas
                tabla.addCell("NIT");
                tabla.addCell("Nombre");
                tabla.addCell("Genero");
                tabla.addCell("Correo");
                for (int i = 0; i < manejador.clientes.length; i++) {
                    if (manejador.clientes[i] != null) {
                        tabla.addCell(String.valueOf(manejador.clientes[i].dpi));
                        tabla.addCell(String.valueOf(manejador.clientes[i].nit));
                        tabla.addCell(String.valueOf(manejador.clientes[i].nombre));
                        tabla.addCell(String.valueOf(manejador.clientes[i].genero));
                        tabla.addCell(String.valueOf(manejador.clientes[i].correo));
                    }
                }
                try {
                    doc.add(tabla);
                } catch (DocumentException documentException) {
                    documentException.printStackTrace();
                }


                doc.close();
                JOptionPane.showMessageDialog(ventana, "El archivo PDF fue creado");


            }
        });

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
