import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Simpleoptimize {

    public static void main(String[] args) {
        
        String machineCode = "MOV r1, 0\nMUL r2, r1, 7\nADD r3, r2, 5\n";

        System.out.println(" Machine Code:\n" + machineCode);

       
        String optimize = optimize(machineCode);

        System.out.println("\nOptimized Machine Code:\n" + optimize);
    }

    private static String optimize(String machineCode) {
        
        String x = "MOV\\s+(\\w+),\\s+0";
        Pattern regular = Pattern.compile(x);
        Matcher matcher = regular.matcher(machineCode);

       
        String optimize = matcher.replaceAll("");

        return optimize;
    }
}