import ru.cmr.io.Window;

public class Starter {
    public static void main(String[] args) {
        //todo: instanciate new thread in order to do another tasks.
        Thread thread = new Thread(Starter::start);
        thread.start();

    }

    public static void start(){
        Window.init();
        Window.start();// this make current thread(probably Main thread) to be OpenGL addicted -> necessary to start new Thread if I want do something more
    }
}
