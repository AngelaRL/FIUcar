import org.omg.CORBA.PUBLIC_MEMBER;

import java.io.File;
import java.io.IOException;

public class manejadordedatos {
    private static manejadordedatos instancia;
    public carro[] carros;
    public vendedor[] vendores;
    public cliente[] clientes;
    public venta[] ventas;
    int contadorCarros;
    int contadorVendedores;
    int contadorClientes;
    int contadorVentas;
    String nombreUsuario;


    public manejadordedatos() {
        carros = new carro[50];
        vendores = new vendedor[50];
        clientes = new cliente[75];
        ventas = new venta[100];
        contadorCarros = 0;
        contadorVendedores = 0;
        contadorClientes = 0;
        contadorVentas = 0;
        carga();
        contarIngresados();
    }

    public void contarIngresados() {
        for (int i = 0; i < carros.length; i++) {
            if (carros[i] != null) {
                contadorCarros++;
            }
        }
        for (int i = 0; i < vendores.length; i++) {
            if (vendores[i] != null) {
                contadorVendedores++;
            }
        }
        for (int i = 0; i < clientes.length; i++) {
            if (clientes[i] != null) {
                contadorClientes++;
            }
        }
        for (int i = 0; i < ventas.length; i++) {
            if (ventas[i] != null) {
                contadorVentas++;
            }
        }

    }

    public static manejadordedatos getInstancia() {
        if (instancia == null) {
            instancia = new manejadordedatos();
        }
        return instancia;
    }

    public void carga() {
        File directorioFile = new File("carros.bin");
        File directorioFile2 = new File("vendedores.bin");
        File directorioFile3 = new File("clientes.bin");
        File directorioFile4 = new File("venta.bin");
        if (directorioFile.exists() && directorioFile2.exists() && directorioFile3.exists() && directorioFile4.exists()) {
            carros = (carro[]) serializar.deserialize("carros.bin");
            vendores = (vendedor[]) serializar.deserialize("vendedores.bin");
            clientes = (cliente[]) serializar.deserialize("clientes.bin");
            ventas = (venta[]) serializar.deserialize("venta.bin");
        }
    }

    public boolean loginUsuario(String correo, String contraseña) {         // para verificar que tipo de usuario es
        boolean verificar = false;
        if ("admin@ipc1.com".equals(correo) && "admin".equals(contraseña)) {
            verificar = true;
            return verificar;
        } else {
            for (int i = 0; i < contadorVendedores; i++) {
                if (vendores[i].correo.equals(correo) && vendores[i].password.equals(contraseña)) {
                    nombreUsuario = vendores[i].nombre;
                    verificar = true;
                    return verificar;
                }
            }
        }
        return verificar;
    }


    public boolean tipoUsuario(String correo) {                              //para saber si es el admin o si es un vendedor
        boolean verificar = false;
        if ("admin@ipc1.com".equals(correo)) {
            verificar = true;
            return verificar;
        }
        return verificar;
    }


    public boolean espacioCarroVacio() {                                 //para saber si el arreglo esta vicio o no
        boolean vacio = true;
        for (int i = 0; i < contadorCarros; i++) {
            if (carros[i] != null) {
                vacio = false;
                return vacio;
            }
        }
        return vacio;
    }

    public boolean espacioVendedorVacio() {
        boolean vacio = true;
        for (int i = 0; i < contadorVendedores; i++) {
            if (vendores[i] != null) {
                vacio = false;
                return vacio;
            }
        }
        return vacio;
    }

    public boolean espacioClienteVacio() {
        boolean vacio = true;
        for (int i = 0; i < contadorClientes; i++) {
            if (clientes[i] != null) {
                vacio = false;
                return vacio;
            }
        }
        return vacio;
    }

    public carro buscarCarro(String vin) {                             // para buscar los datos del vendedor
        for (int i = 0; i < contadorCarros; i++) {
            if (carros[i] != null) {
                if ((carros[i].VIN).equals(vin)) {
                    return new carro(carros[i].VIN, carros[i].fabricante, carros[i].modelo, carros[i].year, (double) carros[i].precio);
                }
            }
        }
        return null;
    }

    public vendedor buscarVendedor(int dpi) {                             // para buscar los datos del vendedor
        for (int i = 0; i < contadorVendedores; i++) {
            if (vendores[i] != null) {
                if (vendores[i].dpi == dpi) {
                    return new vendedor(vendores[i].dpi, vendores[i].nombre, vendores[i].ventas, vendores[i].genero, vendores[i].correo, vendores[i].password);
                }
            }
        }
        return null;
    }

    public cliente buscarCliente(int dpi) {                             // para buscar los datos del cliente
        for (int i = 0; i < contadorClientes; i++) {
            if (clientes[i] != null) {
                if (clientes[i].dpi == dpi) {
                    return new cliente(clientes[i].dpi, clientes[i].nit, clientes[i].nombre, clientes[i].genero, clientes[i].correo);
                }
            }
        }
        return null;
    }

    public boolean verificarNumero(String numero) {          //saber si es numero o no
        try {
            Integer.parseInt(numero);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public boolean verificarCarro(String vin) {              // verificar si no existe ya el num de dpi
        boolean verificar = false;
        for (int i = 0; i < carros.length; i++) {
            if (carros[i] != null) {
                if (carros[i].VIN.equals(vin)) {
                    verificar = true;
                    return verificar;
                }
            }
        }
        return verificar;
    }


    public boolean verificarVendedor(int dpi) {              // verificar si no existe ya el num de dpi
        boolean verificar = false;
        for (int i = 0; i < contadorVendedores; i++) {
            if (vendores[i] != null) {
                if (vendores[i].dpi == dpi) {
                    verificar = true;
                    return verificar;
                }
            }
        }
        return verificar;
    }


    public boolean verificarCliente(int dpi) {              // verificar si no existe ya el num de dpi
        boolean verificar = false;
        for (int i = 0; i < contadorClientes; i++) {
            if (clientes[i] != null) {
                if (clientes[i].dpi == dpi) {
                    verificar = true;
                    return verificar;
                }
            }
        }
        return verificar;
    }

    public boolean verificarNit(int nit) {               // verificar que no existe el nit
        boolean verificar = false;
        for (int i = 0; i < contadorClientes; i++) {
            if (clientes[i] != null) {
                if (clientes[i].nit == nit) {
                    verificar = true;
                    return verificar;
                }
            }
        }
        return verificar;
    }

    public void agregarCarro(carro nuevo) {
        contadorCarros++;
        for (int i = 0; i < carros.length; i++) {
            if (carros[i] == null) {
                carros[i] = nuevo;
                reordenarDatos();
                break;
            }
        }

    }


    public void agregarVendedor(vendedor nuevo) {
        contadorVendedores++;
        for (int i = 0; i < contadorVendedores; i++) {
            if (i != vendores.length) {
                if (vendores[i] == null) {
                    vendores[i] = nuevo;
                    break;
                }
            }
        }
    }


    public void agregarCliente(cliente nuevo) {
        contadorClientes++;
        for (int i = 0; i < contadorClientes; i++) {
            if (clientes[i] == null) {
                clientes[i] = nuevo;
                break;
            }
        }
    }

    public void modificarCarro(String VIN, String fabricante, String modelo, int year, int precio) {      //para editar los datos del cliente
        for (int i = 0; i < contadorCarros; i++) {
            if (carros[i] != null) {
                if (carros[i].VIN == VIN) {                    //realiza la busqueda por medio del dpi
                    carros[i].fabricante = fabricante;
                    carros[i].modelo = modelo;
                    carros[i].year = year;
                    carros[i].precio = precio;

                }
            }
        }
    }

    public void modificarVendedor(int dpi, String nombre, int ventas, char genero, String correo, String password) {      //para editar los datos del cliente
        for (int i = 0; i < contadorVendedores; i++) {
            if (vendores[i] != null) {
                if (vendores[i].dpi == dpi) {                    //realiza la busqueda por medio del dpi
                    vendores[i].nombre = nombre;
                    vendores[i].ventas = ventas;
                    vendores[i].genero = genero;
                    vendores[i].correo = correo;
                    vendores[i].password = password;

                }
            }
        }
    }

    public void modificarCliente(int dpi, int nit, String nombre, char genero, String correo) {      //para editar los datos del cliente
        for (int i = 0; i < contadorClientes; i++) {
            if (clientes[i] != null) {
                if (clientes[i].dpi == dpi) {
                    clientes[i].nit = nit;
                    clientes[i].nombre = nombre;
                    clientes[i].genero = genero;
                    clientes[i].correo = correo;

                }
            }
        }
    }


    public void eliminarCarro(int vin) {
        for (int i = 0; i < contadorCarros; i++) {
            if (carros[i] != null) {
                if (Integer.parseInt(carros[i].VIN) == vin) {
                    carros[i] = null;
                    contadorCarros--;
                    break;
                }
            }
        }
    }

    public void eliminarVendedor(int dpi) {
        for (int i = 0; i < contadorVendedores; i++) {
            if (vendores[i] != null) {
                if (vendores[i].dpi == dpi) {
                    vendores[i] = null;
                    contadorVendedores--;
                    break;
                }
            }
        }
    }

    public void eliminarCliente(int dpi) {
        for (int i = 0; i < contadorClientes; i++) {
            if (clientes[i] != null) {
                if (clientes[i].dpi == dpi) {
                    clientes[i] = null;
                    contadorClientes--;
                    break;
                }
            }
        }
    }

    public boolean agregarventaCarro(String nombreCliente, carro[] carrosc, String fecha) {
        boolean agregar = false;
        if (contadorVentas + 1 < 100) {
            agregar = true;
            for (int i = 0; i < contadorClientes; i++) {
                if (nombreCliente.equals(clientes[i].nombre)) {
                    venta aux = new venta(contadorVentas + 1, clientes[i].nit, clientes[i].nombre, carrosc, fecha);
                    ventas[contadorVentas] = aux;
                    try {
                        new crearPdf(aux).creacion();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    contadorVentas++;
                }
            }

        }
        return agregar;
    }

    public void reordenarDatos() {
        for (int i = 0; i < contadorCarros; i++) {
            for (int j = 0; j < contadorCarros; j++) {
                if ((j + 1) < carros.length && carros[j + 1] != null) {
                    if (carros[j + 1].precio > carros[j].precio) {
                        carro aux = new carro(carros[j].VIN, carros[j].fabricante, carros[j].modelo, carros[j].year, carros[j].precio);
                        carros[j] = new carro(carros[j + 1].VIN, carros[j + 1].fabricante, carros[j + 1].modelo, carros[j + 1].year, carros[j + 1].precio);
                        carros[j + 1] = new carro(aux.VIN, aux.fabricante, aux.modelo, aux.year, aux.precio);
                    }
                }
            }
        }
    }

}
