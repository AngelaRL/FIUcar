import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class actualizarCliente extends JFrame {
    private manejadordedatos manejador;
    private JPanel panel;
    private JTextField dpi;
    private JTextField nit;
    private JTextField nombre;
    private JTextField correo;
    private JComboBox lista;


    public actualizarCliente(cliente cliente) {
        manejador = manejadordedatos.getInstancia();
        this.setSize(450, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Actualizar datos cliente");
        setLocationRelativeTo(null);
        panel();
        etiqueta();
        boton();
        texto(cliente);
        box();
        lista.setSelectedItem(cliente.genero);
    }

    private void panel() {
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.gray);
        this.getContentPane().add(panel);
    }

    private void etiqueta() {
        JLabel ins = new JLabel("Realize los cambios deseados en los campos correspondientes ");
        ins.setBounds(30, 30, 400, 20);
        ins.setForeground(Color.white);
        panel.add(ins);

        JLabel cod = new JLabel("DPI: ");
        cod.setBounds(50, 100, 100, 20);
        cod.setForeground(Color.white);
        panel.add(cod);

        JLabel nom = new JLabel("NIT: ");
        nom.setBounds(50, 150, 100, 20);
        nom.setForeground(Color.white);
        panel.add(nom);

        JLabel nt = new JLabel("Nombre: ");
        nt.setBounds(50, 200, 100, 20);
        nt.setForeground(Color.white);
        panel.add(nt);

        JLabel cor = new JLabel("Correo: ");
        cor.setBounds(50, 250, 100, 20);
        cor.setForeground(Color.white);
        panel.add(cor);

        JLabel gen = new JLabel("Genero: ");
        gen.setBounds(50, 300, 100, 20);
        gen.setForeground(Color.white);
        panel.add(gen);
    }

    private void boton() {
        JButton ini = new JButton("Actualizar");
        ini.setBounds(80, 400, 250, 25);

        ini.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!dpi.getText().equals("") && !nit.getText().equals("") && !nombre.getText().equals("") && !correo.getText().equals("")) {      // en orden conforme a la clase cliente
                    if (manejador.verificarNumero(nit.getText())) {                                                                                // verificar que el dato sea un numero
                        if (!manejador.verificarNit(Integer.parseInt(nit.getText()))) {

                            manejador.modificarCliente(Integer.parseInt(dpi.getText()), Integer.parseInt(nit.getText()), nombre.getText(), lista.getSelectedItem().toString().charAt(0), correo.getText());

                            moduloAdmin ma = new moduloAdmin();
                            ma.setVisible(true);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(panel, "el nit ya existe");
                        }
                    } else {
                        JOptionPane.showMessageDialog(panel, "ingrese un numero");
                    }
                } else {
                    JOptionPane.showMessageDialog(panel, "Debe llenar todos los campos");
                }
            }
        });

        JButton regresar = new JButton("Regresar");
        regresar.setBounds(80, 450, 250, 25);

        regresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moduloAdmin adm = new moduloAdmin();
                adm.setVisible(true);
                dispose();
            }
        });

        panel.add(ini);
        panel.add(regresar);

    }

    private void texto(cliente cliente) {
        dpi = new JTextField(String.valueOf(cliente.dpi));
        dpi.setBounds(150, 100, 200, 25);
        panel.add(dpi);
        dpi.disable();

        nit = new JTextField(cliente.nit);
        nit.setBounds(150, 150, 200, 25);
        panel.add(nit);

        nombre = new JTextField(String.valueOf(cliente.nombre));
        nombre.setBounds(150, 200, 200, 25);
        panel.add(nombre);

        correo = new JTextField(String.valueOf(cliente.correo));
        correo.setBounds(150, 250, 200, 25);
        panel.add(correo);

    }

    private void box() {
        lista = new JComboBox();
        lista.addItem("M");
        lista.addItem("F");
        lista.setBounds(150, 300, 200, 25);
        panel.add(lista);
    }

}
