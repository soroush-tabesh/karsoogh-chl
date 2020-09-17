package judge.ecosys;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Creature {
    int id;
    String name = "";
    String equation = "0.0";
    transient double count;
    private transient Matcher matcher;
    private final static ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");

    public void init(){
        Pattern p = Pattern.compile("\\{(\\d)}");
        matcher = p.matcher(equation);
    }

    public double calculateDiff(EcoSystem ecoSystem) {
        String expression = matcher.replaceAll(matchResult ->
                "" + ecoSystem.creatures.get(Integer.parseInt(matchResult.group(1))).count);
        return eval(expression);
    }

    public double eval(String exp){
        try {
            return (Double) engine.eval(exp);
        } catch (ScriptException e) {
            e.printStackTrace();
            return 0;
        }
    }

}

