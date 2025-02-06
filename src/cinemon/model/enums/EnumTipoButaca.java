package cinemon.model.enums;

public enum EnumTipoButaca {
    standar(0),
    vip (4),
    delux (3),
    reclinableExtra(2.5);
   
    
    double precio;

    private EnumTipoButaca(double precio) {
        this.precio = precio;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    

}
