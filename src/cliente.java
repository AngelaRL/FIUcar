public class cliente {
    int dpi;
    int nit;
    String nombre;
    char genero;
    String correo;

    public cliente(int dpi, int nit, String nombre, char genero, String correo){
    this.dpi=dpi;
    this.nit=nit;
    this.nombre=nombre;
    this.genero=genero;
    this.correo=correo;

    }

    public static String[] nameOfColumns = {
            "DPI",
            "NIT",
            "Nombre",
            "Genero",
            "Correo",};

    public Object[] getAsRow() {
        return new Object[]{
                this.dpi,
                this.nit,
                this.nombre,
                this.genero,
                this.correo,
        };
    }
}
