import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class actualizarVendedor extends JFrame {
    private manejadordedatos manejador;
    private JPanel panel;
    private JTextField dpi;
    private JTextField nombre;
    private JTextField ventas;
    private JTextField correo;
    private JTextField password;
    private JComboBox lista;

    public actualizarVendedor(vendedor vendedor) {
        manejador = manejadordedatos.getInstancia();
        this.setSize(450, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Actualizar datos vendedor");
        setLocationRelativeTo(null);
        panel();
        etiqueta();
        boton();
        texto(vendedor);
        box();
        lista.setSelectedItem(vendedor.genero);
    }

    private void panel() {
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.gray);
        this.getContentPane().add(panel);
    }

    private void etiqueta() {
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

    private void boton() {
        JButton ini = new JButton("Actualizar");
        ini.setBounds(80, 400, 250, 25);

        ini.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!dpi.getText().equals("") && !nombre.getText().equals("") && !ventas.getText().equals("") && !correo.getText().equals("") && !password.getText().equals("")){
                    if (manejador.verificarNumero(dpi.getText()) && manejador.verificarNumero(ventas.getText()) ){

                        manejador.modificarVendedor(Integer.parseInt(dpi.getText()), nombre.getText(), Integer.parseInt(ventas.getText()),lista.getSelectedItem().toString().charAt(0), correo.getText(), password.getText());

                        moduloAdmin ma = new moduloAdmin();
                        ma.setVisible(true);
                        dispose();

                    }else {
                        JOptionPane.showMessageDialog(panel, "Ingrese numeros en las casillas correspondientes");
                    }
                }else {
                    JOptionPane.showMessageDialog(panel, "debe llenar todos los campos");
                }
            }
        });


        JButton regresar = new JButton("Regresar");
        regresar.setBounds(80, 450, 250, 25);

        regresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moduloAdmin ma = new moduloAdmin();
                ma.setVisible(true);
                dispose();
            }
        });


        panel.add(ini);
        panel.add(regresar);

    }

    private void texto(vendedor vendedor) {
        dpi = new JTextField(String.valueOf(vendedor.dpi));
        dpi.setBounds(150, 100, 200, 25);
        panel.add(dpi);
        dpi.disable();                                                //para que no se pueda editar

        nombre = new JTextField(vendedor.nombre);
        nombre.setBounds(150, 150, 200, 25);
        panel.add(nombre);

        ventas = new JTextField(String.valueOf(vendedor.ventas));
        ventas.setBounds(150, 200, 200, 25);
        panel.add(ventas);

        correo = new JTextField(String.valueOf(vendedor.correo));
        correo.setBounds(150, 300, 200, 25);
        panel.add(ventas);

        password = new JTextField(vendedor.password);
        password.setBounds(150, 330, 200, 25);
        panel.add(password);


    }

    private void box() {
        lista = new JComboBox();
        lista.addItem("M");
        lista.addItem("F");
        lista.setBounds(150, 250, 200, 25);
        panel.add(lista);
    }
}
