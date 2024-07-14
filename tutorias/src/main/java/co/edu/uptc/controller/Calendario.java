package co.edu.uptc.controller;

import java.util.HashMap;
import java.util.Map;

public class Calendario {

    private Map<String, Dia> dias;

    public Calendario() {
        dias = new HashMap<>();
        dias.put("Lunes", new Dia("Lunes"));
        dias.put("Martes", new Dia("Martes"));
        dias.put("Miercoles", new Dia("Miercoles"));
        dias.put("Jueves", new Dia("Jueves"));
        dias.put("Viernes", new Dia("Viernes"));
    }

    public boolean addEventoCalendar(String dia, Evento evento) {
        Dia day = dias.get(dia);
        if (day == null) {
            day = new Dia(dia);
            dias.put(dia, day);
        }
        return day.addEvento(evento);
    }

    public String showEventsDay(String dia, int index) {
        Dia day = dias.get(dia);
        if (day != null) {
            return day.showEvents();
        } else {
            return null;
        }
    }

}
