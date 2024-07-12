package co.edu.uptc.app;

import co.edu.uptc.controller.Dia;//Dia
import co.edu.uptc.controller.Evento;
import co.edu.uptc.controller.StudentControl;
import co.edu.uptc.model.Estudent;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<Estudent> estudiantes;
    private static StudentControl s1 = new StudentControl();

    public static void main(String[] args) {
        menu();
    }

    private static void menu() {
        estudiantes = new ArrayList<>();
        int option = 0;
        System.out.println("Bienvenido a Gestion de Tutorias \n " +
                "Ingrese su rol: \n " +
                "1. Estudiante\n " +
                "2. Tutor\n " +
                "3. Salir ");

        option = sc.nextInt();
        switch (option) {
            case 1:
                validationStudent();
                break;
            case 2:
            default:

        }
    }

    private static void validationStudent() {
        Type listType = new TypeToken<ArrayList<Estudent>>() {}.getType();
        estudiantes = (ArrayList<Estudent>) s1.deserializeObecjtoCollectionfromJson(listType);  

        System.out.println("------- Bienvenido Estudiante --------");
        System.out.println("Ingrese su Id: ");

        int id = sc.nextInt();

        boolean studentFound = false;
        for (Estudent e : estudiantes) {
            if (id == e.getId()) {
//                System.out.println(e.toString());
                menuStudent(e);
                studentFound = true;
                break;
            }
        }

        if (!studentFound) {
            System.out.println("No se encontró un estudiante con el Id ingresado.");
        }

    }

    private static void menuStudent(Estudent e) {
        System.out.println("Ingrese la opcion que desea realizar: \n" +
                "1. Agendar tutoria\n" +
                "2. Cancelar tutoria\n" +
                "3. Buscar tutoria por docente\n" +
                "4. Mostrar tutorias\n" +
                "5. Salir ");

        int option = sc.nextInt();
        switch (option) {
            case 1:
                programTutoring(e);
                break;
            case 2:
                deleteTutoring(e);
                break;
            case 3:
                findDocent(e);
                break;
            case 4:
                showTutorials(e);
                break;
            case 5:
                goOut();
                break;
            default:
                System.err.println("Invalid Option");
        }
    }

    private static void programTutoring(Estudent e) {
        System.out.println("-------- Agendamiento de tutorias --------\n");
        System.out.println("Tutorias disponibles: ");
        List<Evento> calendar = new ArrayList<>();
        int count = 1;
        for (Dia d : e.getCalendarios()) {
            System.out.println(count++ + " " + d.showEvents());
        }
        System.out.println("Ingrese el nombre de la materia a agendar tutoria: ");
        String nombre = sc.next();
        for (Dia d : e.getCalendarios()) {
            for (Evento s : d.getEventos()) {
                if (s.getNombre().equals(nombre)) {
                    d.setEventos(s1.inscribir(d.getEventos()));
                    if (s.isInscrito()) {
                        System.out.println("Tutoria Agregada");
                        String json = s1.serializeObecjtoCollectiontoJson(estudiantes);
                        s1.resetGuardarEstudiante(json);
                    }
                }
            }
        }
        menu();
    }

    private static void deleteTutoring(Estudent e) {
        System.out.println("-------- Cancelacion de tutorias --------\n");
    }

    private static void findDocent(Estudent e) {
        System.out.println("-------- Buscar docente --------\n");
    }

    private static void showTutorials(Estudent e) {
        System.out.println("-------- Tutorias Programadas --------\n");
        for (Dia d : e.getCalendarios()){
            for (Evento s : d.getEventos()){
                if (s.isInscrito() == true) {
                    System.out.println(d.showEvents());
                }
            }
        }
    }

    private static void goOut() {
        System.out.println("Saliendo del programa");
    }




}
