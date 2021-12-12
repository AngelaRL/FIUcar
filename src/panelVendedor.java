import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class panelVendedor extends JPanel {
    private JFrame ventana;
    private manejadordedatos manejador;
    private JTable tabla;
    private JScrollPane scroll;
    private DefaultTableModel modelo;
    private JPanel panel2;

    public panelVendedor(JFrame ventana){
        manejador = manejadordedatos.getInstancia();
        this.ventana=ventana;
        setLayout(null);
        setBackground(Color.red.darker());
        boton();
        agregartabla();
        panel();

    }
    private void panel(){
        panel2=new JPanel();
        panel2.setBounds(570,250,200,200);
        this.add(panel2);


    }

    private void boton(){
        JButton crear = new JButton("Crear");
        crear.setBounds(570, 100, 100, 25);

        JButton cargam = new JButton("Carga Masiva");
        cargam.setBounds(675, 100, 125, 25);


        JButton actualizar = new JButton("Actualizar");
        actualizar.setBounds(570, 135, 100, 25);

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

    private void agregartabla(){
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

    public void actualizarTabla(){
        modelo.setRowCount(0);
        for (int i=0; i<manejador.contadorCarros;i++){
            modelo.addRow(manejador.carros[i].getAsRow());
        }
    }
}
