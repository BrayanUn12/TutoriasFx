package co.edu.uptc.controller;

  

import java.time.LocalTime;

import java.time.format.DateTimeFormatter;

  

public class Evento {

    private String dia;

    private String nombre;

    private String descripcion;

    private LocalTime horaInicio;

    private LocalTime horaFinal;

    private boolean inscrito;

  

    public Evento(String nombre, String descripcion, LocalTime horaInicio,LocalTime horaFinal,boolean inscrito) {

      this.nombre = nombre;

      this.descripcion = descripcion;

      this.horaInicio = horaInicio;

      this.horaFinal=horaFinal;

      this.inscrito=inscrito;

  

    }

  

    public String getNombre() {

      return nombre;

    }

  

    public void setNombre(String nombre) {

      this.nombre = nombre;

    }

  

    public String getDescripcion() {

      return descripcion;

    }

  

    public void setDescripcion(String descripcion) {

      this.descripcion = descripcion;

    }

  

    public LocalTime getHora() {

      return horaInicio;

    }

  

    public void setHora(int hora,int minuto) {

      this.horaInicio = LocalTime.of(hora, minuto);

    }

  

    public boolean isInscrito() {

      return inscrito;

    }

  

    public void setInscrito(boolean inscrito) {

      this.inscrito = inscrito;

    }

  

    public LocalTime getHoraInicio() {

      return horaInicio;

    }

  

    public void setHoraInicio(LocalTime horaInicio) {

      this.horaInicio = horaInicio;

    }

  

    public LocalTime getHoraFinal() {

      return horaFinal;

    }

  

    public void setHoraFinal(int hora,int minuto) {

      this.horaFinal = LocalTime.of(hora, minuto);

    }

  

    @Override

    public String toString() {

      return "Evento [\nnombre= " + nombre + ", \ndescripcion=" + descripcion + ", \nhoraInicio=" + horaInicio

          + ", \nhoraFinal=" + horaFinal + ", \ninscrito=" + inscrito + "]";

    }

  
  

    public String getDia() {

      return dia;

    }

  
  

    public void setDia(String dia) {

      this.dia = dia;

    }

  
  

    public void setHoraFinal(LocalTime horaFinal) {

      this.horaFinal = horaFinal;

    }

  

    public String getHoraInicioFormatted() {

    return horaInicio.format(DateTimeFormatter.ofPattern("HH:mm"));

    }

  

    public String getHoraFinalFormatted() {

      return horaFinal.format(DateTimeFormatter.ofPattern("HH:mm"));

    }

  
  

}