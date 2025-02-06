package cinemon.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Proyecion {
    private int id;
    private int duracion;
    private String titulo;
    private String director;
    private String sipnosis;
    private String tema;
    private String recomendaciones;
    private String genero;
    public Proyecion(int id, int duracion, String titulo, String director, String sipnosis, String tema, String recomendaciones, String genero) {
        this.id = id;
        this.duracion = duracion;
        this.titulo = titulo;
        this.director = director;
        this.sipnosis = sipnosis;
        this.tema = tema;
        this.recomendaciones = recomendaciones;
        this.genero = genero;
    }
    public int getId() {
        return id;
    }
    public int getDuracion() {
        return duracion;
    }
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    public String getSipnosis() {
        return sipnosis;
    }
    public void setSipnosis(String sipnosis) {
        this.sipnosis = sipnosis;
    }
    public String getTema() {
        return tema;
    }
    public void setTema(String tema) {
        this.tema = tema;
    }
    public String getRecomendaciones() {
        return recomendaciones;
    }
    public void setRecomendaciones(String recomendaciones) {
        this.recomendaciones = recomendaciones;
    }
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: " + id+"\n");
        sb.append("duracion: "+ duracion+"\n");
        sb.append("titulo: "+ titulo+"\n");
        sb.append("director: "+ director+"\n");
        sb.append("sipnosis: "+ sipnosis+"\n");
        sb.append("tema: "+ tema+"\n");
        sb.append("recomendaciones: "+ recomendaciones+"\n");
        sb.append("genero: "+ genero+"\n");
        return sb.toString();
    }

    
}
