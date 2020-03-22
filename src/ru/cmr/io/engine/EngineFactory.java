package ru.cmr.io.engine;

/**
 * PATTERN: Factory
 * makes Engine instances.
 */
public class EngineFactory {
    public static Engine createSimple(){
        Engine engine = new Engine();
        return engine;
    }
}
