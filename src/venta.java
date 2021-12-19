import java.io.Serializable;

public class venta implements Serializable {
    carro []ventacarro;

    int nofactura;
    int nitcliente;
    String nombrecliente;
    String fecha;
    double total;

    public venta(int nofactura, int nitcliente, String nombrecliente, carro []ventacarro,String fecha){
        this.nofactura = nofactura;
        this.nitcliente = nitcliente;
        this.nombrecliente= nombrecliente;
        this.fecha = fecha;
        this.ventacarro = ventacarro;

        for (int i=0; i<ventacarro.length; i++){
            if (ventacarro[i]!= null){
                this.total+=ventacarro[i].precio;
            }
        }
        System.out.println(this.total+ " total");
    }

    public static String[] nameOfColumns = {
            "No. Factura",
            "NIT cliente",
            "Nombre Cliente",
            "Fecha",
            "Total",};

    public Object[] getAsRow() {
        return new Object[]{
                this.ventacarro,
                this.nofactura,
                this.nitcliente,
                this.nombrecliente,
                this.fecha,
                this.total,
        };
    }

}
