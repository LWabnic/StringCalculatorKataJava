package kata;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class StringCalculator {
    private static final String DEFAULT_DELIMITER = ",|\n";
    private String delimiter = DEFAULT_DELIMITER;
    private String numbers;

    public int add(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        parseInput(input);
        return sumNumbers();
    }

    private void parseInput(String input) {
        if (input.startsWith("//")) {
            Pattern pattern = Pattern.compile("//(.*?)\n(.*)");
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                delimiter = parseDelimiter(matcher.group(1));
                numbers = matcher.group(2);
            }
        } else {
            delimiter = DEFAULT_DELIMITER;
            numbers = input;
        }
    }

    private String parseDelimiter(String input) {
        if (input.startsWith("[")) {
            return Arrays.stream(input.split("\\[|\\]"))
                    .filter(s -> !s.isEmpty())
                    .map(Pattern::quote)
                    .collect(Collectors.joining("|"));
        } else {
            return Pattern.quote(input);
        }
    }

    private int sumNumbers() {
        List<Integer> numberList = Arrays.stream(numbers.split(delimiter))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<Integer> negativeNumbers = numberList.stream()
                .filter(n -> n < 0)
                .collect(Collectors.toList());
        if (!negativeNumbers.isEmpty()) {
            String negativeNumsStr = negativeNumbers.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(","));
            throw new IllegalArgumentException("Negatives not allowed: " + negativeNumsStr);
        }
        return numberList.stream()
                .filter(n -> n <= 1000)
                .mapToInt(Integer::intValue)
                .sum();
    }
}
