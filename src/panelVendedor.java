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

public class panelVendedor extends JPanel {
    private JFrame ventana;
    private manejadordedatos manejador;
    private JTable tabla;
    private JScrollPane scroll;
    private DefaultTableModel modelo;
    private JPanel panel2;

    public panelVendedor(JFrame ventana) {
        manejador = manejadordedatos.getInstancia();
        this.ventana = ventana;
        setLayout(null);
        setBackground(Color.red.darker());
        boton();
        agregartabla();
        panel();

    }

    private void panel() {
        panel2 = new JPanel();
        panel2.setBounds(570, 250, 200, 200);
        this.add(panel2);

        int conM = 0;
        int conF = 0;
        for (int i = 0; i < manejador.contadorVendedores; i++) {
            if (String.valueOf(manejador.vendores[i].genero).equalsIgnoreCase("m")) {
                conM++;
            } else
                conF++;
        }

        DefaultPieDataset datos = new DefaultPieDataset();
        datos.setValue("Masculino", conM);
        datos.setValue("Femenino", conF);

        JFreeChart graficapai = ChartFactory.createPieChart(
                "Genero de Vendedores",       //titutlo
                datos,                             //datos
                true,                       //nobre de las categorias
                true,                       //herramientas
                false                          //generacion de url
        );

        ChartPanel panel = new ChartPanel(graficapai);
        panel.setMouseWheelEnabled(true);
        panel.setPreferredSize(new Dimension(200, 200));

        panel2.add(panel, BorderLayout.NORTH);
        panel2.revalidate();


    }

    private void boton() {
        JButton crear = new JButton("Crear");
        crear.setBounds(570, 100, 100, 25);

        crear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (manejador.contadorVendedores < 50) {
                    crearVendedor cv = new crearVendedor();
                    cv.setVisible(true);
                    panel();
                    ventana.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "NO se puede agregar mas vendedores");
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
                        vendedor[] ven = gson.fromJson(texto, vendedor[].class);        //Lo convierte a arreglo tipo vendedor

                        for (vendedor recorrer : ven) {                         //recorrer, recorre cada una de los objetos dentro del arreglo vendedor

                            if (!manejador.verificarVendedor(recorrer.dpi)) {
                                if (manejador.contadorVendedores < 50) {
                                    manejador.agregarVendedor(new vendedor(recorrer.dpi, recorrer.nombre, recorrer.ventas, recorrer.genero, recorrer.correo, recorrer.password));
                                }
                            }

                        }

                        actualizarTabla();
                        panel();


                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(panelVendedor.class.getName()).log(Level.SEVERE, null, ex);
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
                    actualizarVendedor ac = new actualizarVendedor(manejadordedatos.getInstancia().buscarVendedor(Integer.parseInt(modelo.getValueAt(tabla.getSelectedRow(), 0).toString())));
                    ac.setVisible(true);
                    panel();
                    ventana.dispose();
                } else {
                    JOptionPane.showMessageDialog(ventana, "no a seleccionado ningun dato de la lista");
                }

            }
        });

        JButton eliminiar = new JButton("Eliminar");
        eliminiar.setBounds(675, 135, 125, 25);

        eliminiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tabla.getSelectedRow() >= 0) {
                    manejador.eliminarVendedor(Integer.parseInt(modelo.getValueAt(tabla.getSelectedRow(), 0).toString()));
                    modelo.removeRow(tabla.getSelectedRow());
                } else {
                    JOptionPane.showMessageDialog(ventana, "Seleccione elemento de la lista o la lista esta vacia");
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
                    ficheroPdf = new FileOutputStream("Vendedores.pdf");
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
                    doc.add(new Paragraph("Lista de Vendedores \n",
                            FontFactory.getFont("calibre",   // fuente
                                    14,                            // tamaño
                                    Font.BOLD,                   // estilo
                                    BaseColor.BLACK)));             // color

                } catch (DocumentException documentException) {
                    documentException.printStackTrace();
                }

                int numColumns = 6;                                 //numero de columnas que tendra la tabla en el pdf
                PdfPTable tabla = new PdfPTable(numColumns);
                tabla.addCell("DPI");                        //nombre de las columnas
                tabla.addCell("Nombre");
                tabla.addCell("Ventas");
                tabla.addCell("Genero");
                tabla.addCell("Correo");
                tabla.addCell("password");
                for (int i = 0; i < manejador.vendores.length; i++) {
                    if (manejador.vendores[i] != null) {
                        tabla.addCell(String.valueOf(manejador.vendores[i].dpi));
                        tabla.addCell((manejador.vendores[i].nombre));
                        tabla.addCell(String.valueOf(manejador.vendores[i].ventas));
                        tabla.addCell(String.valueOf(manejador.vendores[i].genero));
                        tabla.addCell((manejador.vendores[i].correo));
                        tabla.addCell(manejador.vendores[i].password);
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
        if (manejador.espacioVendedorVacio()) {
            modelo = new DefaultTableModel(null, vendedor.nameOfColumns);
            tabla = new JTable(modelo);
            tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            scroll = new JScrollPane(tabla);
            scroll.setBounds(20, 55, 500, 500);
            add(scroll);
        } else {
            modelo = new DefaultTableModel(null, vendedor.nameOfColumns);
            for (int i = 0; i < manejador.contadorVendedores; i++) {
                if (manejador.vendores[i] != null) {
                    modelo.addRow(manejador.vendores[i].getAsRow());
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
        //while (manejador.contadorVendedores < manejadordedatos.getInstancia().vendores.length) {
        for (int i = 0; i < manejador.contadorVendedores; i++) {
            modelo.addRow(manejador.vendores[i].getAsRow());
        }
        tabla.repaint();
        //}
    }
}
