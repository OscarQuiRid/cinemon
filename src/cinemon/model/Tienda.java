package cinemon.model;

import cinemon.model.enums.EnumTipoProducto;
import java.util.ArrayList;

public class Tienda{
    private int id;
    private ArrayList<Productos> productos;
    private EnumTipoProducto tipo;
    
    public Tienda(int id, ArrayList<Productos> productos,EnumTipoProducto tipo) {
        this.id = id;
        this.productos = productos;
        this.tipo=tipo;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public ArrayList<Productos> getProductos() {
        return productos;
    }
    public void setProductos(ArrayList<Productos> productos) {
        this.productos = productos;
    }
    public void addProducto(Productos producto){
        if (this.tipo == EnumTipoProducto.ALIMENTO){
            if (!(producto instanceof Alimento)){
                return;
            }
            
        }else  if (this.tipo == EnumTipoProducto.SOUVENIR){
            if (!(producto instanceof Souvenir)){
                return;
            }
        }
        this.productos.add(producto);
    }
   
    public EnumTipoProducto getTipo() {
        return tipo;
    }
    public void setTipo(EnumTipoProducto tipo) {
        this.tipo = tipo;
    }
    public void removeProducto(int id){
        Productos victima = this.productos.stream().filter( p -> p.getId() == id).toList().get(0);
        if (victima != null){
            this.productos.remove(victima);
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Id: " + id+"\n");
        sb.append("Tipo de productos: " + tipo.toString() + "\n");
        sb.append("Productos: \n");
        for (Productos s : productos){
            sb.append("\t" + s.toString()+"\n");
        }
        return sb.toString();
    }
}
