import org.omg.CORBA.PUBLIC_MEMBER;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class crearCliente extends JFrame{
    private manejadordedatos manejador;
    private JPanel panel;
    private JTextField dpi;
    private JTextField nit;
    private JTextField nombre;
    private JTextField correo;
    private JComboBox lista;

    public crearCliente(){
        manejador = manejadordedatos.getInstancia();
        this.setSize(450, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Crear nuevo cliente");
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

    private void boton(){
        JButton ini = new JButton("Agregar");
        ini.setBounds(80, 400, 250, 25);

        ini.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!dpi.getText().equals("")&& !nit.getText().equals("")&& !nombre.getText().equals("") && !correo.getText().equals("")){          // verificar que todos los campos vayan llenos
                    if (manejador.verificarNumero(dpi.getText()) && manejador.verificarNumero(nit.getText())){                          //verificar si los datos ingresados son numeros y no letras
                        if (!manejador.verificarCliente(Integer.parseInt(dpi.getText())) && !manejador.verificarNit(Integer.parseInt(nit.getText()))) {                                                         //para verificar si el codigo o nit ya existe

                            manejador.agregarCliente(new cliente(Integer.parseInt(dpi.getText()), Integer.parseInt(nit.getText()), nombre.getText(), lista.getSelectedItem().toString().charAt(0),correo.getText()));           //agrega cliente a listado

                            dpi.setText("");
                            nit.setText("");
                            nombre.setText("");
                            correo.setText("");
                            lista.setSelectedIndex(0);

                        }else{
                            JOptionPane.showMessageDialog(panel, "el DPI o NIT ya existe");
                        }
                    }else{
                        JOptionPane.showMessageDialog(panel,"Ingrese un numero");
                    }
                }else{
                    JOptionPane.showMessageDialog(panel,"Debe de llenar todos los campos");
                }
            }
        });

        JButton regresar = new JButton("Regresar");
        regresar.setBounds(80, 450, 250, 25);
        panel.add(regresar);

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

    private void texto(){
        dpi = new JTextField();
        dpi.setBounds(150, 100, 200, 25);
        panel.add(dpi);

        nit = new JTextField();
        nit.setBounds(150, 150, 200, 25);
        panel.add(nit);

        nombre = new JTextField();
        nombre.setBounds(150, 200, 200, 25);
        panel.add(nombre);

        correo = new JTextField();
        correo.setBounds(150, 250, 200, 25);
        panel.add(correo);
    }

    private void box(){
        lista = new JComboBox();
        lista.addItem("M");
        lista.addItem("F");
        lista.setBounds(150, 300, 200, 25);
        panel.add(lista);
    }
}
