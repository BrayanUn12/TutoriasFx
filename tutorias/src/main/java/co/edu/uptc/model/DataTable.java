package co.edu.uptc.model;

import co.edu.uptc.controller.Evento;
import javafx.scene.control.Button;

public class DataTable {
    private final String diaNombre;
    private final String nombreEvento;
    private final String descripcionEvento;
    private final String horaEvento;
    private final String horaInicialEvento;
    private final String horaFinalEvento;
    private String estadoEvento;
    private Button button;
    private Evento evento;

    public DataTable(String diaNombre, Evento evento ) {
        this.diaNombre = diaNombre;
        this.nombreEvento = evento.getNombre();
        this.descripcionEvento = evento.getDescripcion();
        this.horaEvento = evento.getHoraInicio() + "-" + evento.getHoraFinal();
        this.horaInicialEvento = evento.getHoraInicio().toString();
        this.horaFinalEvento = evento.getHoraFinal().toString();
        if (evento.isInscrito() == true){
            this.estadoEvento = "Agendada";
        } else {
            this.estadoEvento = "No agendada";
        }
        this.evento = evento;
    }


    public String getDiaNombre() {
        return diaNombre;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public String getDescripcionEvento() {
        return descripcionEvento;
    }

    public String getHoraEvento() {
        return horaEvento;
    }

    public String getEstadoEvento() {
        return estadoEvento;
    }

    public String getHoraInicialEvento() {
        return horaInicialEvento;
    }

    public String getHoraFinalEvento() {
        return horaFinalEvento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public void setEstadoEvento(String estadoEvento) {
        this.estadoEvento = estadoEvento;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }
    public Evento getEvento() {
        return evento;
    }
}
