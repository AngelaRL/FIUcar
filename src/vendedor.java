import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.io.Serializable;


public class vendedor implements Serializable {
    int dpi;
    String nombre;
    int ventas;
    char genero;
    String correo;
    String password;

    public vendedor(int dpi, String nombre, int ventas, char genero, String correo, String password){
    this.dpi=dpi;
    this.nombre =nombre;
    this.ventas=ventas;
    this.genero=genero;
    this.correo = correo;
    this.password=password;

    }

    public static String[] nameOfColumns = {
            "DPI",
            "Nombre",
            "Ventas",
            "Genero",
            "Correo",
            "Password",};

    public Object[] getAsRow() {
        return new Object[]{
                this.dpi,
                this.nombre,
                this.ventas,
                this.genero,
                this.correo,
                this.password,
        };
    }
}
