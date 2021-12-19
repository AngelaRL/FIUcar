import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class crearPdf {
    private venta procesando;

    public crearPdf(venta datos) {
        procesando = datos;
    }

    public void creacion() throws IOException {
        Document doc = new Document();
        FileOutputStream ficheroPdf = null;
        try {
            ficheroPdf = new FileOutputStream("Factura"+procesando.nofactura+".pdf");
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
            doc.add(new Paragraph("FIUcar Dealership \n\n",
                    FontFactory.getFont("calibre",   // fuente
                            18,                            // tamaño
                            Font.BOLD,                   // estilo
                            BaseColor.BLUE)));             // color

            doc.add(new Paragraph("No. factura: "+procesando.nofactura+"\n Fecha: "+procesando.fecha+ "\n NIT Cliente: "+procesando.nitcliente+"\n Nombre Cliente: "+procesando.nombrecliente+"\n Producto:\n\n",
                    FontFactory.getFont("calibre",   // fuente
                            14,                            // tamaño
                            Font.BOLD,                   // estilo
                            BaseColor.BLACK)));             // color

        } catch (DocumentException documentException) {
            documentException.printStackTrace();
        }

        int numColumns = 4;                                 //numero de columnas que tendra la tabla en el pdf
        PdfPTable tabla = new PdfPTable(numColumns);
        tabla.addCell("VIN");                        //nombre de las columnas
        tabla.addCell("Fabricante");
        tabla.addCell("Modelo");
        tabla.addCell("Precio");
        for (int i = 0; i < procesando.ventacarro.length; i++) {
            if (procesando.ventacarro[i] != null) {
                tabla.addCell(procesando.ventacarro[i].VIN);
                tabla.addCell(procesando.ventacarro[i].fabricante);
                tabla.addCell(procesando.ventacarro[i].modelo);
                tabla.addCell(String.valueOf(procesando.ventacarro[i].precio));
            }
        }
        try {
            doc.add(tabla);
        } catch (DocumentException documentException) {
            documentException.printStackTrace();
        }
        try {

            doc.add(new Paragraph("Total Venta: "+procesando.total+"\n ",
                    FontFactory.getFont("calibre",   // fuente
                            14,                            // tamaño
                            Font.BOLD,                   // estilo
                            BaseColor.BLACK)));             // color

        } catch (DocumentException documentException) {
            documentException.printStackTrace();
        }


        doc.close();
        File path = new File ("Factura"+procesando.nofactura+".pdf");
        Desktop.getDesktop().open(path);
    }


}
