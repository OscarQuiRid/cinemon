package cinemon.model;

import cinemon.model.enums.EnumTipoAlimento;
import cinemon.model.enums.EnumTamanio;
import cinemon.model.enums.EnumAlergeno;
import java.util.ArrayList;

public class Alimento extends Productos  {
    
    private EnumTipoAlimento tipo;
    private EnumTamanio tamanio;
    private boolean veganFrendly;
    private ArrayList<EnumAlergeno> alergenos;
    public Alimento(int id,String nombre, double precio, int stock, EnumTipoAlimento tipo, EnumTamanio tamanio, boolean veganFrendly,
            ArrayList<EnumAlergeno> alergenos) {
        super(id,nombre, precio, stock);
        this.tipo = tipo;
        this.tamanio = tamanio;
        this.veganFrendly = veganFrendly;
        this.alergenos = alergenos;
    }
    public EnumTipoAlimento getTipo() {
        return tipo;
    }
    public void setTipo(EnumTipoAlimento tipo) {
        this.tipo = tipo;
    }
    public EnumTamanio getTamanio() {
        return tamanio;
    }
    public void setTamanio(EnumTamanio tamanio) {
        this.tamanio = tamanio;
    }
    public boolean isVeganFrendly() {
        return veganFrendly;
    }
    public void setVeganFrendly(boolean veganFrendly) {
        this.veganFrendly = veganFrendly;
    }
    public ArrayList<EnumAlergeno> getAlergenos() {
        return alergenos;
    }
    public void setAlergenos(ArrayList<EnumAlergeno> alergenos) {
        this.alergenos = alergenos;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(super.toString());
        sb.append("\t tipo:  " + tipo + "\n");
        sb.append("\t tamaño: " + tamanio.toString() + "\n");
        sb.append("\t Vegano: " + (veganFrendly? "Si":"No") + "\n");
        sb.append("\t Alergenos: \n");
        for (EnumAlergeno a : alergenos){
            sb.append("\t\t"+ a.toString() + "\n");
        }

        return sb.toString();

    }
    
}
