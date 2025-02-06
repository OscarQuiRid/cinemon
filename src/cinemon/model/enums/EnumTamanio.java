package cinemon.model.enums;

public enum EnumTamanio {
    Pequeño(0),
    Mediano(1.5),
    Grande(3);
    double precio;
    EnumTamanio(double precio){
        this.precio = precio;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
}
