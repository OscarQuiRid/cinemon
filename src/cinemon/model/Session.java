package cinemon.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Date;

public class Session {
    private int id;
    private LocalDate horaInicio;
    private LocalDate horaFinal;
    private Proyecion pelicula;
    private LocalDate fecha;
    ArrayList<ArrayList<Butaca>> butacas;
    public Session(int id, LocalDate horaInicio, LocalDate horaFinal, Proyecion pelicula, LocalDate fecha, ArrayList<ArrayList<Butaca>> butacas) {
        this.id = id;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
        this.pelicula = pelicula;
        this.fecha = fecha;
        this.butacas = butacas;
    }
    public Session(int id, String horaInicio, String horaFinal, Proyecion pelicula, String fecha, ArrayList<ArrayList<Butaca>> butacas) {
       
        this.id = id;
        this.horaInicio = LocalDate.parse(horaInicio, DateTimeFormatter.ofPattern("dd/MM/yyyyHH:mm"));
        this.horaFinal = LocalDate.parse(horaFinal, DateTimeFormatter.ofPattern("dd/MM/yyyyHH:mm"));
        this.pelicula = pelicula;
        this.fecha = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.butacas = butacas;
    }
    public int getId() {
        return id;
    }
    public LocalDate getHoraInicio() {
        return horaInicio;
    }
    public void setHoraInicio(String horaInicio) {
        this.horaInicio = LocalDate.parse(horaInicio, DateTimeFormatter.ofPattern(fecha+"HH:mm"));
    }
    public void setHoraInicio(LocalDate horaInicio) {
        this.horaInicio = horaInicio;
    }
    public LocalDate getHoraFinal() {
        return horaFinal;
    }
    public void setHoraFinal(String horaFinal) {
        this.horaFinal = LocalDate.parse(horaFinal, DateTimeFormatter.ofPattern(fecha+"HH:mm"));
    }
    public void setHoraFinal(LocalDate horaFinal) {
        this.horaFinal = horaFinal;
    }
    public Proyecion getPelicula() {
        return pelicula;
    }
    public void setPelicula(Proyecion pelicula) {
        this.pelicula = pelicula;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public ArrayList<ArrayList<Butaca>> getButacas() {
        return butacas;
    }
    public void setButacas(ArrayList<ArrayList<Butaca>> butacas) {
        this.butacas = butacas;
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\tId: " + id+"\n");
        sb.append("\tHoraInicio " + horaInicio.toString()+"\n");
        sb.append("\tHoraFinal " + horaFinal.toString()+"\n");
        sb.append("Pelicula: \n");
        sb.append("\t\tPelicula " + pelicula.toString()+"\n");
        sb.append("\tFecha " + fecha.toString()+"\n");
        sb.append("\tNum Butacas: " +(butacas.size()* butacas.get(0).size())+"\n");
        return sb.toString();
    }
}