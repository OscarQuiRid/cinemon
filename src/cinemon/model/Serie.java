package cinemon.model;

import java.time.LocalDate;

public class Serie extends Proyecion{
    private int temporadas,episodios;

    public Serie(int id, int duracion, String titulo, String director, String sipnosis, String tema, String recomendaciones, String genero, int temporadas,
            int episodios) {
        super(id, duracion, titulo, director, sipnosis, tema, recomendaciones, genero);
        this.temporadas = temporadas;
        this.episodios = episodios;
    }

    public int getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(int temporadas) {
        this.temporadas = temporadas;
    }

    public int getEpisodios() {
        return episodios;
    }

    public void setEpisodios(int episodios) {
        this.episodios = episodios;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("\t Temporadas: " + temporadas + "\n" );
        sb.append("\t Episodio: " + episodios + "\n" );
        return sb.toString();

    }
}
