package judge.ecosys;

public class Main {
    public static void main(String[] args) {
        new Main().run(args);
    }

    private int simulationRepeat = 1000;

    private void run(String[] args) {
        //if (init(args))
        System.out.println(simulateWithRepeat());
    }

    private double simulateWithRepeat() {
        double avg = 0;
        for (int i = 0; i < simulationRepeat; i++)
            avg += simulate();
        avg /= simulationRepeat;
        return avg;
    }

    private double simulate() {
        return 0;
    }
}
