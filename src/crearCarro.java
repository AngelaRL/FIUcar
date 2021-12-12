import javax.swing.*;
import java.awt.*;

public class crearCarro extends JFrame{
    private JPanel panel;
    private JTextField vin;
    private JTextField fabricante;
    private JTextField modelo;
    private JTextField año;
    private JTextField precio;
    private manejadordedatos manejador;

    public crearCarro(){
        manejador = manejadordedatos.getInstancia();
        this.setSize(450, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Crear nuevo Carro");
        setLocationRelativeTo(null);
        panel();
        etiqueta();
        boton();
        texto();
    }

    private void panel(){
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.gray);
        this.getContentPane().add(panel);
    }

    private void etiqueta(){
        JLabel ins = new JLabel("Llene los siguientes campos con los datos correspondietes:");
        ins.setBounds(30, 30, 400, 20);
        ins.setForeground(Color.white);
        panel.add(ins);

        JLabel vni = new JLabel("VIN: ");
        vni.setBounds(50, 100, 100, 20);
        vni.setForeground(Color.white);
        panel.add(vni);

        JLabel fabri = new JLabel("Fabricante: ");
        fabri.setBounds(50, 150, 100, 20);
        fabri.setForeground(Color.white);
        panel.add(fabri);

        JLabel model = new JLabel("Modelo: ");
        model.setBounds(50, 200, 100, 20);
        model.setForeground(Color.white);
        panel.add(model);

        JLabel an = new JLabel("Año: ");
        an.setBounds(50, 250, 100, 20);
        an.setForeground(Color.white);
        panel.add(an);

        JLabel pre = new JLabel("Precio: ");
        pre.setBounds(50, 300, 100, 20);
        pre.setForeground(Color.white);
        panel.add(pre);
    }

    private void boton(){
        JButton ini = new JButton("Agregar");
        ini.setBounds(80, 400, 250, 25);

        JButton regresar = new JButton("Regresar");
        regresar.setBounds(80, 450, 250, 25);
        panel.add(regresar);


        panel.add(ini);
        panel.add(regresar);
    }

    private void texto(){
        vin = new JTextField();
        vin.setBounds(150, 100, 200, 25);
        panel.add(vin);

        fabricante = new JTextField();
        fabricante.setBounds(150, 150, 200, 25);
        panel.add(fabricante);

        modelo = new JTextField();
        modelo.setBounds(150, 200, 200, 25);
        panel.add(modelo);

        año = new JTextField();
        año.setBounds(150, 250, 200, 25);
        panel.add(año);

        precio = new JTextField();
        precio.setBounds(150, 300, 200, 25);
        panel.add(precio);
    }


}
