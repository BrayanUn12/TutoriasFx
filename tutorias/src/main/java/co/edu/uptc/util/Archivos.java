package co.edu.uptc.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Archivos {
    private File file;
    private PrintWriter pw;
    private BufferedReader br;
    public static final String filePath = "src/main/java/co/edu/uptc/persistence/";
    public static final String fileExtension = ".json";

    public boolean createFile(String fileName) {
        file = new File(fileName);
        try {
            // pw = new PrintWriter(filePath + file + fileExtension);// Cuando se quiere
            // sobreescribir el archivo
            pw = new PrintWriter(new FileWriter(filePath + file + fileExtension, true));// Sin sobreescribir,
            // adicionando en una nueva línea
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
            // e.printStackTrace(System.out);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean writeFile(String fileName, String json) {
        file = new File(fileName);
        try {
            // pw = new PrintWriter(filePath + file + fileExtension);// Cuando se quiere
            // sobreescribir el archivo
            pw = new PrintWriter(new FileWriter(filePath + file + fileExtension, true));// Sin sobreescribir,
            pw.println(json);
            pw.close();// Ojo recordar siempre cerra el archivo después de realizar cualquier operación
                       // // adicionando en una nueva
                       // línea
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
            // e.printStackTrace(System.out);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean resetWriteFile(String fileName, String json) {
        file = new File(fileName);
        try {
            // pw = new PrintWriter(filePath + file + fileExtension);// Cuando se quiere
            // sobreescribir el archivo
            pw = new PrintWriter(new FileWriter(filePath + file + fileExtension));// Sin sobreescribir,
            pw.println(json);
            pw.close();// Ojo recordar siempre cerra el archivo después de realizar cualquier operación
                       // // adicionando en una nueva
                       // línea
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
            // e.printStackTrace(System.out);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String readJsonFile(String path) {
        StringBuilder sb = new StringBuilder();
        try {
            File file = new File(path);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado en la ruta: " + e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return sb.toString();
    }
}
