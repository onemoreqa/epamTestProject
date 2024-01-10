package helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.RandomString;

import java.util.Locale;
import java.util.Random;

public class GenerationTools {

private static final Logger logger = LogManager.getLogger(GenerationTools.class);

    public static final String SET_OF_VALID_NUMBERS = "3489";

    private GenerationTools() {
        throw new IllegalStateException("Utility class");
    }

    public static String getRandomPhone() {
        String createdPhone = getRandomStringFromSet(1, RandomString.DIGITS) +
                getRandomStringFromSet(1, SET_OF_VALID_NUMBERS) +
                getRandomStringFromSet(9, RandomString.DIGITS);
        logger.info("Сгенерирован номер телефона: {}", createdPhone);
        return createdPhone;
    }

    private static String getRandomStringFromSet(int length, String random) {
        return new RandomString(length, new Random(), random).nextString();
    }

}
