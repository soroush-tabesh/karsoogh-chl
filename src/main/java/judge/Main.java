package judge;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        if (args.length < 1)
            return;
        switch (args[0]) {
            case "1":
                judge.prot1.Main.main(Arrays.copyOfRange(args, 1, args.length));
                break;
            case "2":
                judge.prot2.Main.main(Arrays.copyOfRange(args, 1, args.length));
                break;
            case "3":
                judge.prot3.Main.main(Arrays.copyOfRange(args, 1, args.length));
                break;
            case "4":
                judge.ecosys.Main.main(Arrays.copyOfRange(args, 1, args.length));
                break;
            default:
        }
    }
}
