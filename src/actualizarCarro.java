import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class actualizarCarro extends JFrame {
    private JPanel panel;
    private JTextField vin;
    private JTextField fabricante;
    private JTextField modelo;
    private JTextField año;
    private JTextField precio;
    private manejadordedatos manejador;

    public actualizarCarro(carro carro) {
        manejador = manejadordedatos.getInstancia();
        this.setSize(450, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Actualizar datos carro");
        setLocationRelativeTo(null);
        panel();
        etiqueta();
        boton();
        texto(carro);
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

    private void boton() {
        JButton ini = new JButton("Actualizar");
        ini.setBounds(80, 400, 250, 25);

        ini.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!vin.getText().equals("") && !fabricante.getText().equals("") && !modelo.getText().equals("") && !año.getText().equals("") && !precio.getText().equals("")) {
                    if (manejador.verificarNumero(año.getText()) && manejador.verificarNumero(precio.getText())) {

                        manejador.modificarCarro(vin.getText(), fabricante.getText(), modelo.getText(), Integer.parseInt(año.getText()), Integer.parseInt(precio.getText()));

                        moduloAdmin ma = new moduloAdmin();
                        ma.setVisible(true);
                        dispose();

                    }
                } else {
                    JOptionPane.showMessageDialog(panel, "Debe llenar todos los campos");
                }
            }
        });

        JButton regresar = new JButton("Regresar");
        regresar.setBounds(80, 450, 250, 25);
        panel.add(regresar);

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

    private void texto(carro carro) {
        vin = new JTextField(String.valueOf(carro.VIN));
        vin.setBounds(150, 100, 200, 25);
        panel.add(vin);
        vin.disable();

        fabricante = new JTextField(carro.fabricante);
        fabricante.setBounds(150, 150, 200, 25);
        panel.add(fabricante);

        modelo = new JTextField(String.valueOf(carro.modelo));
        modelo.setBounds(150, 200, 200, 25);
        panel.add(modelo);

        año = new JTextField(String.valueOf(carro.year));
        año.setBounds(150, 250, 200, 25);
        panel.add(año);

        precio = new JTextField(String.valueOf(carro.precio));
        precio.setBounds(150, 300, 200, 25);
        panel.add(precio);
    }
}
