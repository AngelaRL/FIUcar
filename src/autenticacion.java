import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class autenticacion extends JFrame {

    private JPanel panel;
    private JTextField correo;
    private JTextField contra;
    public manejadordedatos datos;


    public autenticacion() {

        datos = manejadordedatos.getInstancia();
        this.setSize(500, 250);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("AUTENTICACION");
        this.setLocationRelativeTo(null);
        panel();
        etiqueta();
        campo();
        boton();


    }

    private void panel() {
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.GRAY);
        this.getContentPane().add(panel);

    }

    private void etiqueta() {
        JLabel cod = new JLabel("Correo: ");
        cod.setBounds(50, 75, 100, 20);
        cod.setForeground(Color.white);
        panel.add(cod);

        JLabel cont = new JLabel("Contraseña:");
        cont.setBounds(50, 125, 100, 20);
        cont.setForeground(Color.white);
        panel.add(cont);

        JLabel msn = new JLabel("Ingrese los datos en las casillas correspondientes: ");
        msn.setBounds(40, 30, 300, 20);
        msn.setForeground(Color.white);
        panel.add(msn);

    }

    private void boton() {
        JButton ini = new JButton("Iniciar sesion");
        ini.setBounds(170, 170, 150, 25);

        ini.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (datos.loginUsuario(correo.getText(), contra.getText())) {
                    if (datos.tipoUsuario(correo.getText())) {

                        moduloAdmin moduloadmin = new moduloAdmin();
                        moduloadmin.setVisible(true);
                        dispose();
                    } else {
                        moduloVendedor moduloVendedor = new moduloVendedor();
                        moduloVendedor.setVisible(true);
                        dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(panel, "El usuario o la contraseña son incorrectos.");
                }

            }
        });

        panel.add(ini);
    }

    private void campo() {
        correo = new JTextField();
        correo.setBounds(150, 75, 200, 25);
        panel.add(correo);

        contra = new JPasswordField();                                  //para que no muestre el texto que se ingresa al campo
        contra.setBounds(150, 125, 200, 25);
        panel.add(contra);
    }

}
