package judge.road;

import java.util.ArrayList;

public class Main {
    static private int n, start, end;
    static private ArrayList<Integer>[] graphs;
    static private Calculator[][] calculators;
    static private int[][] carNumbers;
    static private ArrayList<Integer>[] ways;
    public static void main(String[] args) {
        init(args);

        for(int i = 0; i < n; i++){
            ways[i].addAll(getWay());
            for(int j = 0; j < ways[i].size() - 1; j++){
                carNumbers[ways[i].get(j)][ways[i].get(j + 1)]++;
            }
        }

        while(true){
            double tmp = getAverageCost();
            for(int i = 0; i < n; i++){
                optimize(i);
            }
            if(tmp == getAverageCost()){
                break;
            }
        }
        System.out.println(getAverageCost());
    }

    private static void optimize(int v) {
        for(int i = 0; i < ways[v].size() - 1; i++){
            carNumbers[ways[v].get(i)][ways[v].get(i + 1)]--;
        }
        ways[v].clear();
        ways[v].addAll(getWay());
        for(int i = 0; i < ways[v].size() - 1; i++){
            carNumbers[ways[v].get(i)][ways[v].get(i + 1)]++;
        }
    }

    private static double getAverageCost() {
        double sum = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                sum += calculators[i][j].calculate(carNumbers[i][j]);
            }
        }
        return sum/n;
    }

    private static void init(String[] args) {

    }

    private static ArrayList<Integer> getWay(){
        double[] dis = new double[n];
        boolean[] mark = new boolean[n];
        int[] parent = new int[n];
        for(int i = 0; i < n; i++){
            dis[i] = Double.MAX_VALUE;
            mark[i] = false;
        }
        dis[start] = 0;
        mark[start] = true;
        parent[start] = -1;
        for(int v: graphs[start]){
            if(dis[v] > dis[start] + calculators[start][v].calculate(carNumbers[start][v])){
                parent[v] = start;
                dis[v] = dis[start] + calculators[start][v].calculate(carNumbers[start][v]);
            }
        }
        while(true){
            boolean check = false;
            int lastIn = 0;
            double lastCost = Double.MAX_VALUE;
            for(int i = 0; i < n; i++){
                if(!mark[i] && lastCost > dis[i]){
                    check = true;
                    lastCost = dis[i];
                    lastIn = i;
                }
            }
            if(check){
                mark[lastIn] = true;
                for(int v: graphs[lastIn]){
                    if(dis[v] > dis[lastIn] + calculators[lastIn][v].calculate(carNumbers[lastIn][v])){
                        parent[v] = lastIn;
                        dis[v] = dis[lastIn] + calculators[lastIn][v].calculate(carNumbers[lastIn][v]);
                    }
                }
            }
            else break;
        }
        ArrayList<Integer> answer = new ArrayList<>();
        int lastIn = end;
        while(lastIn != -1){
            answer.add(lastIn);
            lastIn = parent[lastIn];
        }
        ArrayList<Integer> finalAnswer = new ArrayList<>();
        for(int i = answer.size(); i >= 0; i--){
            finalAnswer.add(answer.get(i));
        }
        return finalAnswer;
    }
}
