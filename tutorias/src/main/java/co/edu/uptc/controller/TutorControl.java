package co.edu.uptc.controller;

  

import java.time.LocalTime;

import java.time.format.DateTimeFormatter;

import java.util.ArrayList;

import java.util.List;

import java.io.FileReader;

import java.io.FileWriter;

import java.io.IOException;

import java.io.Reader;

import java.io.Writer;

import java.lang.reflect.Type;

  

import com.google.gson.Gson;

import com.google.gson.GsonBuilder;

import com.google.gson.JsonDeserializer;

import com.google.gson.JsonPrimitive;

import com.google.gson.JsonSerializer;

import com.google.gson.reflect.TypeToken;

  

import co.edu.uptc.model.Tutor;

import co.edu.uptc.util.Archivos;

import co.edu.uptc.util.LocalTimeAdapter;

  

public class TutorControl {

        public void guardarTutor(String json) {

        

            Archivos files = new Archivos();

            files.writeFile("Tutores", json);

        }

        

        public void resetGuardarTutor(String json) {

        

            Archivos files = new Archivos();

            files.resetWriteFile("Tutores", json);

        }

        

        public String verHorario(Tutor obejcto, int index) {

            return obejcto.getCalendarios().get(index).showEvents();

        }

        

        public String serializeObecjtoCollectiontoJson(

            ArrayList<?> list) {

            JsonSerializer<LocalTime> localTimeSerializer = (src, typeOfSrc,

                context) -> new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_TIME));

            Gson gson = new GsonBuilder().registerTypeAdapter(LocalTime.class, localTimeSerializer).setPrettyPrinting()

                .create();

            return gson.toJson(list);

        }

        

        public Object deserializeObecjtoCollectionfromJson(Type listType) {

            JsonDeserializer<LocalTime> localTimeDeserializer = (json, typeOfT, context) -> LocalTime

                .parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_TIME);

            Archivos files = new Archivos();

            Gson gson = new GsonBuilder().registerTypeAdapter(LocalTime.class, localTimeDeserializer).setPrettyPrinting()

                .create();

            return gson.fromJson(files.readJsonFile("ProyectoTutoFxNew\\ProyectoTutoFx\\tutorias\\src\\main\\java\\co\\edu\\uptc\\persistence\\Tutores.json"), listType);

        }

        

        public void borrarTutoria(Tutor tutor, Evento evento) {

            List<Dia> eventos = tutor.getCalendarios();

            eventos.remove(evento);

            tutor.setCalendarios(eventos);

        

        }

        private Tutor currentTutor;

        private List<Tutor> tutors;

        

        public TutorControl(){

            tutors = new ArrayList<>();

            tutors = loadTutorsFromJson();

        }

        

        public List<Tutor> loadTutorsFromJson() {

            Gson gson = new GsonBuilder()

            .registerTypeAdapter(LocalTime.class, new LocalTimeAdapter())

            .create();

        

            try (Reader reader = new FileReader("ProyectoTutoFxNew\\ProyectoTutoFx\\tutorias\\src\\main\\java\\co\\edu\\uptc\\persistence\\Tutores.json")) {

            Tutor[] tutorsArray = gson.fromJson(reader, Tutor[].class);

            return new ArrayList<>(List.of(tutorsArray));

            } catch (IOException e) {

            e.printStackTrace();

            return new ArrayList<>();

            }

        }

        

        public void saveTutorsToJson() {

        

            try (Writer writer = new FileWriter("ProyectoTutoFxNew\\ProyectoTutoFx\\tutorias\\src\\main\\java\\co\\edu\\uptc\\persistence\\Tutores.json")) {

            Gson gson = new GsonBuilder()

            .registerTypeAdapter(LocalTime.class, new LocalTimeAdapter())

            .setPrettyPrinting()

            .create();

            gson.toJson(tutors, writer);

            } catch (IOException e) {

            e.printStackTrace();

            }

        

        }

        

        public Tutor findTutor(int id){

            return tutors.stream().filter(t -> t.getId() == id).findFirst().isPresent() ?

            tutors.stream().filter(t -> t.getId() == id).findFirst().get() : null;

        }

        

        public Tutor getCurrentTutor() {

            return currentTutor;

        }

        

        public void setCurrentTutor(Tutor currentTutor) {

            this.currentTutor = currentTutor;

        }

        

        public List<Tutor> getTutors() {

            return tutors;

        }

        

        public void setTutors(List<Tutor> tutors) {

            this.tutors = tutors;

        }

  

}