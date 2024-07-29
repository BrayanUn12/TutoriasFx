package co.edu.uptc.model;

  

import java.util.ArrayList;

import java.util.List;

  

import co.edu.uptc.controller.Dia;

import co.edu.uptc.controller.Evento;;

  

public class Tutor {

  private int id;

  private String firstName;

  private String lastName;

  private List<Dia> calendarios;

  

  public Tutor() {

  }

  public Tutor(int id, String firstName, String lastName, ArrayList<?> calendarios) {

    this.id = id;

    this.firstName = firstName;

    this.lastName = lastName;

    this.calendarios = (ArrayList<Dia>)calendarios;

  }

  public int getId() {

    return id;

  }

  public void setId(int id) {

    this.id = id;

  }

  public String getFirstName() {

    return firstName;

  }

  public void setFirstName(String firstName) {

    this.firstName = firstName;

  }

  public String getLastName() {

    return lastName;

  }

  public void setLastName(String lastName) {

    this.lastName = lastName;

  }

  public List<Dia> getCalendarios() {

    return calendarios;

  }

  public void setCalendarios(List<Dia> calendarios) {

    this.calendarios = calendarios;

  }

  private List<Evento> eventos;

  

  public List<Evento> getEventos() {

    return eventos;

  }

  

  public void setEventos(List<Evento> eventos) {

    this.eventos = eventos;

  }

  @Override

  public String toString() {

    return "Tutor [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", calendarios="

        + calendarios + "]";

  }

}