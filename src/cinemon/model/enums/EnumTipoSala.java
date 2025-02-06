package cinemon.model.enums;

public enum EnumTipoSala {
    STANDAR(7.0),
	DELUXE(8.5),
	_3D(10.0),
	IMERSIVA(12.0);
    double precio;

    private EnumTipoSala(double precio) {
        this.precio = precio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
}
