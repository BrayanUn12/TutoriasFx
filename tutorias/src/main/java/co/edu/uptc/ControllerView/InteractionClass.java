package co.edu.uptc.ControllerView;

import co.edu.uptc.model.Estudent;

public final class InteractionClass {

    private static InteractionClass instance;

    private Estudent student;


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

    public Estudent getStudent() {
        return student;
    }
    public static InteractionClass getInstance(){
        if(instance == null){
            instance = new InteractionClass();
        }
        return instance;
    }
}
