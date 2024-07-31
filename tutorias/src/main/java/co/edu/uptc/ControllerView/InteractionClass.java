package co.edu.uptc.ControllerView;

public final class InteractionClass<T> {

    private static InteractionClass instance;

    private T object;

    private InteractionClass() {
    }

    private InteractionClass(T object) {
        this.object = object;
    }

    public static <T> InteractionClass<T> getInstance(T object) {
        if (instance == null) {
            instance = new InteractionClass<>(object);
        } else {
            instance.object = object;
        }
        return instance;
    }

    public static <T> InteractionClass<T> getInstance() {
        if (instance == null) {
            instance = new InteractionClass<>();
        }
        return instance;
    }

    public T getObject() {
        return object;
    }
}

