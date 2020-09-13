package judge;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1)
            return;
        switch (args[0]) {
            case "1":
                judge.prot1.Main.main(Arrays.copyOfRange(args, 1, args.length));
                break;
            case "2":
                judge.road.Main.main(Arrays.copyOfRange(args, 1, args.length));
                break;
            case "3":
                judge.prot2.Main.main(Arrays.copyOfRange(args, 1, args.length));
                break;
            default:
        }
    }
}
