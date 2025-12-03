package simulation;

/**
 * Hello world!
 *
 */
public class Application {
    public static void main(String[] args) throws InterruptedException {
        Simulation simulation = new Simulation();
        simulation.run(5,5,500);
        System.out.println("Hello World");
    }
}
