import javax.swing.*;
import java.awt.*;

public class crearVendedor extends JFrame {
    private manejadordedatos manejador;
    private JPanel panel;
    private JTextField dpi;
    private JTextField nombre;
    private JTextField ventas;
    private JTextField correo;
    private JTextField password;
    private JComboBox lista;

    public crearVendedor(){
        manejador = manejadordedatos.getInstancia();
        this.setSize(450, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Actualizar datos cliente");
        setLocationRelativeTo(null);
        panel();
        etiqueta();
        boton();
        texto();
        box();

    }

    private void panel(){
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.gray);
        this.getContentPane().add(panel);
    }

    private void etiqueta(){
        JLabel ins = new JLabel("Llene los siguientes campos con los datos correspondietes: ");
        ins.setBounds(30, 30, 400, 20);
        ins.setForeground(Color.white);
        panel.add(ins);

        JLabel dp= new JLabel("DPI: ");
        dp.setBounds(50, 100, 100, 20);
        dp.setForeground(Color.white);
        panel.add(dp);

        JLabel nom = new JLabel("Nombre: ");
        nom.setBounds(50, 150, 100, 20);
        nom.setForeground(Color.white);
        panel.add(nom);

        JLabel vent = new JLabel("Ventas: ");
        vent.setBounds(50, 200, 100, 20);
        vent.setForeground(Color.white);
        panel.add(vent);

        JLabel gen = new JLabel("Genero: ");
        gen.setBounds(50, 250, 100, 20);
        gen.setForeground(Color.white);
        panel.add(gen);

        JLabel cor = new JLabel("Correo: ");
        cor.setBounds(50, 300, 100, 20);
        cor.setForeground(Color.white);
        panel.add(cor);

        JLabel cont = new JLabel("Contraseña: ");
        cor.setBounds(50, 330, 100, 20);
        cor.setForeground(Color.white);
        panel.add(cor);
    }

    private void boton(){
        JButton ini = new JButton("Agregar");
        ini.setBounds(80, 400, 250, 25);


        JButton regresar = new JButton("Regresar");
        regresar.setBounds(80, 450, 250, 25);


        panel.add(ini);
        panel.add(regresar);
    }
    private void texto(){
        dpi = new JTextField();
        dpi.setBounds(150, 100, 200, 25);
        panel.add(dpi);                                              //para que no se pueda editar

        nombre = new JTextField();
        nombre.setBounds(150, 150, 200, 25);
        panel.add(nombre);

        ventas = new JTextField();
        ventas.setBounds(150, 200, 200, 25);
        panel.add(ventas);

        correo = new JTextField();
        ventas.setBounds(150, 300, 200, 25);
        panel.add(ventas);

        password = new JTextField();
        password.setBounds(150, 330, 200, 25);
        panel.add(password);

    }

    private void box(){
        lista = new JComboBox();
        lista.addItem("M");
        lista.addItem("F");
        lista.setBounds(150, 250, 200, 25);
        panel.add(lista);
    }


}
