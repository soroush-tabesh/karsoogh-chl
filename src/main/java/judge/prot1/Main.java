package judge.prot1;

import com.google.gson.GsonBuilder;
import umontreal.ssj.randvar.PoissonGen;
import umontreal.ssj.randvar.RandomVariateGenInt;
import umontreal.ssj.rng.MRG31k3p;

public class Main {
    /**
     * @param args - dailyReq, buyVal, sellVal, daysToSimulate, func(double array in json
     *             format)
     */
    public static void main(String[] args) {
        new Main().run(args);
    }

    private double dailyReq = 4;
    private double buyVal = 10;
    private double sellVal = 12;
    private int daysToSimulate = 100;
    private int simulationRepeat = 1000;
    private double[] func = new double[]{0, 0, 3, 0, 0};

    private RandomVariateGenInt dailyReqRV;

    private void run(String[] args) {
        //if (init(args))
        System.out.println(simulateWithRepeat());
    }

    private boolean init(String[] args) {
        try {
            dailyReq = Double.parseDouble(args[0]);
            buyVal = Double.parseDouble(args[1]);
            sellVal = Double.parseDouble(args[2]);
            daysToSimulate = Integer.parseInt(args[3]);
            simulationRepeat = Integer.parseInt(args[4]);
            func = new GsonBuilder().create().fromJson(args[5], double[].class);
            dailyReqRV = new PoissonGen(new MRG31k3p(), dailyReq);
        } catch (Exception e) {
            System.out.println(-1);
            return false;
        }
        return true;
    }

    private double simulateWithRepeat() {
        double avg = 0;
        for (int i = 0; i < simulationRepeat; i++)
            avg += simulate();
        avg /= simulationRepeat;
        return avg;
    }

    private double simulate() {
        for (double i : func)
            if (i < 0)
                return 0;

        double balance = 0;
        double fresh = 0;
        double old = 0;

        for (int i = 0; i < daysToSimulate; i++) {
            //day i morning
            if (i < daysToSimulate - 1) {
                fresh = func[Math.min((int) Math.round(old), func.length - 1)];
                balance -= fresh * buyVal;
            }

            double req = dailyReqRV.nextDouble();
            balance += Math.min(req, fresh + old) * sellVal;
            fresh = Math.max(0, fresh - Math.max(0, req - old));

            //day i end
            old = fresh;
            fresh = 0;
        }

        return balance / daysToSimulate;
    }
}
