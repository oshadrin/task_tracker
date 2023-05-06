package util;

import java.util.Random;

public class GeneratorUtil {
    private final static Random random = new Random();
    public static Long generateLong() {
        return random.nextLong();
    }
}
