import ru.cmr.io.Window;
import ru.cmr.io.engine.Engine;

public class Starter {
    public static void main(String[] args) {
        //todo: instanciate new thread in order to do another tasks.
        Window.init();
        Window.start();// this make current thread(probably Main thread) to be OpenGL addicted -> necessary to start new Thread if I want do something more
    }
}
