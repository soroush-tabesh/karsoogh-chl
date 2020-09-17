package judge.ecosys;

import java.util.ArrayList;
import java.util.List;

public class EcoSystem {
    int maxEpoch = 15;
    double epochLength = 0.01;
    List<Creature> creatures = new ArrayList<>();
    transient double[][] logs;

    private void init(double[] initCount) {
        if (initCount.length != creatures.size())
            throw new RuntimeException("Wrong argument");
        logs = new double[maxEpoch][creatures.size()];
        logs[0] = initCount;
        for (int i = 0; i < creatures.size(); i++) {
            creatures.get(i).init();
            creatures.get(i).count = initCount[i];
        }
    }

    public void simulate(double[] initCount) {
        init(initCount);
        for (int i = 1; i < maxEpoch; i++) {
            //System.out.println("EcoSystem.simulate");
            runNewEpoch();
            for (int j = 0; j < creatures.size(); j++) {
                logs[i][j] = creatures.get(j).count;
            }
        }
    }

    private void runNewEpoch() {
        double[] nCount = new double[creatures.size()];
        for (int i = 0; i < creatures.size(); i++) {
            nCount[i] = creatures.get(i).calculateDiff(this);
        }
        for (int i = 0; i < creatures.size(); i++) {
            creatures.get(i).count += nCount[i] * epochLength;
        }
    }
}
