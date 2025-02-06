package cinemon.model;

public class Productos {
    private int id;
    private String nombre;
    private double precio;
    private int stock;
    
    public Productos(int id ,String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(int precio) {
        this.precio = precio;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append( "\tid: "+id+"\n");
        sb.append( "\tnombre: "+nombre+"\n");
        sb.append( "\tprecio: "+precio+"\n");
        sb.append( "\tstock: "+stock+"\n");
        return sb.toString();
    }

}
