package cinemon.model;

import java.util.ArrayList;

public class Cine {
    private int id;
    private String nombre;
    private ArrayList<String> direciones;
    private ArrayList<Sala> salas;
    private ArrayList<Tienda> tiendas;
    
    public Cine(int id, String nombre, ArrayList<String> direciones, ArrayList<Sala> salas,
            ArrayList<Tienda> tiendas) {
        this.id = id;
        this.nombre = nombre;
        this.direciones = direciones;
        this.salas = salas;
        this.tiendas = tiendas;
    }
    /**
     * @param id
     * @return null when room not exist whit id param or the room if exist
     */
    Sala getSalaForID(int id){
 
        try{
            Sala sal = this.salas.stream()
            .filter( sala -> sala.getId() == id ).toList().get(0);
            return sal;
        }catch( IndexOutOfBoundsException e){
            return null;
        }  
    }
    /** 
     * @param id
     * @return null when shop not exist whit id param or the shop if exist
     */
    Tienda getTiendaForID(int id){
 
        try{
            Tienda tie = this.tiendas.stream()
            .filter( tienda -> tienda.getId() == id ).toList().get(0);
            return tie;
        }catch( IndexOutOfBoundsException e){
            return null;
        }  
    }
    public void nuevaSala(Sala sala){
        this.salas.add(sala);
    }
    public void eliminarSala(Sala sala){
        this.salas.remove(sala);
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
    public ArrayList<String> getDireciones() {
        return direciones;
    }
    public void setDireciones(ArrayList<String> direciones) {
        this.direciones = direciones;
    }
    public ArrayList<Sala> getSalas() {
        return salas;
    }
    public void setSalas(ArrayList<Sala> salas) {
        this.salas = salas;
    }
    public ArrayList<Tienda> getTiendas() {
        return tiendas;
    }
    public void setTiendas(ArrayList<Tienda> tiendas) {
        this.tiendas = tiendas;
    }
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("Id: " + id+"\n");
        sb.append("nombre: " + nombre+"\n");
        for (String s : direciones){
            sb.append(s+", ");
        }
        sb.append("\n");
        sb.append("Salas: \n");
        for(Sala s : salas){
            sb.append("\t"+s.toString());
        }
        sb.append("\n");
        sb.append("Tiendas: \n");
        for(Tienda s : tiendas){
            sb.append("\t"+s.toString());
        }
        sb.append("\n");
        return sb.toString();
    }
}
