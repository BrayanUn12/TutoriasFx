package co.edu.uptc.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.reflect.Type;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

import co.edu.uptc.model.Estudent;
import co.edu.uptc.model.Tutor;
import co.edu.uptc.util.Archivos;


public class StudentControl {

    public ArrayList<Evento> inscribir(ArrayList<Evento> eventos){
        for (Evento e : eventos) {
            e.setInscrito(true);
        }
        return eventos;
    }
    public ArrayList<Evento> Cancelar(int index,ArrayList<Evento> eventos){
        eventos.get(index).setInscrito(false);
        return eventos;
    }
    public ArrayList<Dia> buscarProfesor(String nombre,ArrayList<Tutor> tutor,int index){
        
        if(tutor.get(index).getFirstName().equals(nombre))
        {
            ArrayList<Dia> eventos =(ArrayList<Dia>)tutor.get(index).getCalendarios();
            return eventos;
        }else{
            return null;
        }
    }
    public void guardarEstudiante(String json){
        
        Archivos files = new Archivos();
        files.writeFile("Estudiantes", json);
    }
    public void resetGuardarEstudiante(String json){
        
        Archivos files = new Archivos();
        files.resetWriteFile("Estudiantes", json);
    }
    public String serializeObecjtoCollectiontoJson(
    ArrayList<?> list){
        JsonSerializer<LocalTime> localTimeSerializer = (src, typeOfSrc, context) -> new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_TIME));
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalTime.class, localTimeSerializer).setPrettyPrinting()
        .create();
        return gson.toJson(list);
    }
    public Object deserializeObecjtoCollectionfromJson( Type listType)
    {
        
        JsonDeserializer<LocalTime> localTimeDeserializer = (json, typeOfT, context) -> LocalTime.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_TIME);
        Archivos files = new Archivos();
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalTime.class, localTimeDeserializer).setPrettyPrinting()
        .create();
        return gson.fromJson(files.readJsonFile("demo//src//main//java//co//edu//uptc//persistence//Estudiantes.json"),listType);
    }

}
