package cinemon.model;

import cinemon.model.enums.EnumTipoSala;
import java.util.ArrayList;

public class Sala {
    private int id;
    private EnumTipoSala tipoSala;
    private ArrayList<Session> sessiones;
    private double precio;
    public Sala(int id, EnumTipoSala tipoSala, ArrayList<Session> sessiones, double precio) {
        this.id = id;
        this.tipoSala = tipoSala;
        this.sessiones = sessiones;
        this.precio = precio;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public EnumTipoSala getTipoSala() {
        return tipoSala;
    }
    public void setTipoSala(EnumTipoSala tipoSala) {
        this.tipoSala = tipoSala;
    }
    public ArrayList<Session> getSessiones() {
        return sessiones;
    }
    public void setSessiones(ArrayList<Session> sessiones) {
        this.sessiones = sessiones;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public void nuevaSession (Session session){
        this.sessiones.add(session);
    }
    public void eliminarSession(int id){
        Session victima = this.sessiones.stream().filter( p -> p.getId() == id).toList().get(0);
        if (victima != null){
            this.sessiones.remove(victima);
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: " + id + "\n");
        sb.append("Tipo sala: " + tipoSala.toString() + "\n");
        sb.append("Sessiones: \n");
        for (Session s : sessiones){
            sb.append("\t" + s.toString()  + "\n");
        }
        return sb.toString();
    }

}
