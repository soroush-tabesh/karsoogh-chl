package judge.ecosys;

import com.google.gson.GsonBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException {
        String s = Files.readString(Path.of(Main.class.getResource("ecoSystem.json").toURI()));
        new Main().run(new GsonBuilder().create().fromJson(s, EcoSystem.class));
    }

    EcoSystem ecoSystem;

    private void run(EcoSystem ecoSystem) {
        this.ecoSystem = ecoSystem;
        ecoSystem.simulate(new double[]{0.3, 1.5,0.8});

        XYChart chart = new XYChartBuilder().width(600).height(500).title("Ecosys").xAxisTitle("X").yAxisTitle("Y").build();

        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);
        chart.getStyler().setChartTitleVisible(false);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.OutsideE);
        chart.getStyler().setMarkerSize(2);

        double[] days = new double[ecoSystem.maxEpoch];
        for (int i = 0; i < days.length; i++) {
            days[i] = i;
        }

        for (int i = 0; i < ecoSystem.creatures.size(); i++) {
            chart.addSeries(ecoSystem.creatures.get(i).name, days, getCol(ecoSystem.logs, i));
        }

        new SwingWrapper(chart).displayChart();
    }

    private double[] getCol(double[][] ar, int col) {
        double[] res = new double[ar.length];
        for (int i = 0; i < ar.length; i++) {
            res[i] = ar[i][col];
        }
        return res;
    }

}
