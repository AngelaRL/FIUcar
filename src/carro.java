public class carro {
    String VIN;
    String fabricante;
    String modelo;
    int year;
    double precio;

    public carro(String VIN, String fabricante, String modelo, int year, double precio) {
        this.VIN = VIN;
        this.fabricante = fabricante;
        this.modelo = modelo;
        this.year = year;
        this.precio = precio;

    }

    public static String[] nameOfColumns = {
            "VIN",
            "Fabricante",
            "Modelo",
            "Año",
            "Precio",};

    public Object[] getAsRow() {
        return new Object[]{
                this.VIN,
                this.fabricante,
                this.modelo,
                this.year,
                this.precio,
        };
    }
}
