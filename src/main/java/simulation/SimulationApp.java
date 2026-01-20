package simulation;

/**
 * Hello world!
 *
 */
public class SimulationApp {
    public static void main(String[] args) throws InterruptedException {
        SimulationRunner simulationRunner = new SimulationRunner();
        simulationRunner.startSimulation(30,30,100);
    }
}
