
package lexecal;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexecal {
    // Define token types using regular expressions
    private static final String KEYWORD_PATTERN = "\\b(?:if|else|while|for|int|float|return)\\b";
    private static final String IDENTIFIER_PATTERN = "[a-zA-Z]+";
    private static final String NUMBER_PATTERN = "\\d+";
    private static final String OPERATOR_PATTERN = "\\+|\\-|\\*|\\/|=";
    private static final String SPACE_PATTERN = "\\s+"; 
    private static final String SPECIACARACTER_PATTERN = "\\;|\\(|\\)|\\$|\\|\\.";


    // Combine all patterns
    private static final Pattern x = Pattern.compile(
            "(" + KEYWORD_PATTERN + ")"
                    + "|(" + IDENTIFIER_PATTERN + ")"
                    + "|(" + NUMBER_PATTERN + ")"
                    + "|(" + OPERATOR_PATTERN + ")"
                    + "|(" + SPACE_PATTERN + ")"
                    + "|(" + SPECIACARACTER_PATTERN + ")"
    );

    private static class Token {
        String type;
        String value;

        Token(String type, String value) {
            this.type = type;
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("(%s, %s)", type, value);
        }
    }

    // Lexer method to tokenize the input source code
    public static List<Token> tokenize(String input) {
        List<Token> tokens = new ArrayList<>();
        Matcher matcher = x.matcher(input);

        while (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                if (matcher.group(i) != null) {
                    String type = getGroupType(i);
                    tokens.add(new Token(type, matcher.group(i)));
                }
            }
        }

        return tokens;
    }

    // Helper method to determine the type of the matched group
    private static String getGroupType(int groupIndex) {
        switch (groupIndex) {
            case 1:
                return "KEYWORD";
            case 2:
                return "IDENTIFIER";
            case 3:
                return "NUMBER";
            case 4:
                return "OPERATOR";
            case 5:
                return "SPACE";
            case 6:
                return "SPECIACARACTER";
            default:
                return "UNKNOWN";
        }
    }

    public static void main(String[] args) {
        String sourceCode = "for (int i = 1; i <= 5; i++) {\n" +
"                               System.out.println(\"Iteration: \" + i);";
        List<Token> tokens = tokenize(sourceCode);

        for (Token token : tokens) {
            System.out.println(token);
        }
    }
}
