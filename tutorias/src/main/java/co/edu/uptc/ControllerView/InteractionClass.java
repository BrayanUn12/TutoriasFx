package co.edu.uptc.ControllerView;

import co.edu.uptc.model.Estudent;
import co.edu.uptc.model.Tutor;

public final class InteractionClass {

    private static InteractionClass instance;

    private Estudent student;

    private Tutor tutor;


    public InteractionClass() {
    }

    private  InteractionClass(Estudent student) {
        this.student = student;
    }
    public static InteractionClass getInstance(Estudent student){
        if(instance == null){
            instance = new InteractionClass(student);
        }
        return instance;
    }

    private  InteractionClass(Tutor tutor) {
        this.tutor = tutor;
    }

    public static InteractionClass getInstance(Tutor tutor){
        if(instance == null){
            instance = new InteractionClass(tutor);
        }
        return instance;
    }
    public Estudent getStudent() {
        return student;
    }
    public static InteractionClass getInstance(){
        if(instance == null){
            instance = new InteractionClass();
        }
        return instance;
    }

    public Tutor getTutor(){
        return tutor;
    }
}
