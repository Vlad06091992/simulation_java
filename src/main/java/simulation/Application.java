package simulation;

/**
 * Hello world!
 *
 */
public class Application {
    public static void main(String[] args) throws InterruptedException {
        Simulation simulation = new Simulation();
        simulation.run(20,20,500);
    }
}
