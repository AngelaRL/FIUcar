import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class panelConfiguracion extends JPanel {
    private JFrame ventana;
    private manejadordedatos manejador;
    private JTable tabla;
    private JScrollPane scroll;
    private DefaultTableModel modelo;
    private JPanel panel4;
    private String mision;
    private String vision;
    private String Nombre;
    private String Slogan;

    public panelConfiguracion(JFrame ventana){
        manejador = manejadordedatos.getInstancia();
        this.ventana = ventana;
        setLayout(null);
        setBackground(Color.red.darker());
        panel();
      //  texto(configuracion);
    }

    private void panel(){
        panel4 = new JPanel();
        panel4.setBounds(570, 250, 200, 200);
        this.add(panel4);
    }

    private void texto(configuracion configuracion){
        JTextField nom = new JTextField();

        nom.setBounds(150, 100, 200, 25);
        panel4.add(nom);

        JTextField sl = new JTextField();
        sl.setBounds(150, 100, 200, 25);
        panel4.add(sl);

        JTextField mis = new JTextField();
        mis.setBounds(150, 100, 200, 150);
        panel4.add(mis);

        JTextField vi = new JTextField();
        vi.setBounds(150, 100, 200, 25);
        panel4.add(vi);

    }


}
