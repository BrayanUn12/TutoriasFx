package co.edu.uptc.model;

import java.util.ArrayList;
import java.util.List;

import co.edu.uptc.controller.Dia;

public class Estudent {
    private int id;
    private int codigo;
    private String firstName;
    private String lastName;
    private List<Dia> calendarios;

    public Estudent() {
    }
    
    public Estudent(int id, int codigo, String firstName, String lastName, ArrayList<?> calendarios) {
        this.id = id;
        this.codigo = codigo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.calendarios = (ArrayList<Dia>) calendarios;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
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
    @Override
    public String toString() {
        return "Estudent'\n [id=" + id + "\ncodigo=" + codigo + "\nfirstName=" + firstName + "\nlastName=" + lastName
                + "\ncalendarios=" + calendarios + "]" + "\n";
    }


}
