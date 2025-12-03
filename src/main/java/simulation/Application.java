package simulation;

/**
 * Hello world!
 *
 */
public class Application {
    public static void main(String[] args) throws InterruptedException {
        Simulation simulation = new Simulation();
        simulation.run(10,10,700);
        System.out.println("Hello World");
    }
}
