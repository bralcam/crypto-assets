package com.crypto.cryptoassets.common.domain;

import org.apache.logging.log4j.util.Strings;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DecimalStyle;
import java.util.Locale;


public class StringUtil {

    public static String convertJsonIntoString(BufferedReader bufferedReader) throws IOException {
        String resultJson = Strings.EMPTY;
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            resultJson = resultJson.concat(line);
        }
        return resultJson;
    }

    public static String printCurrentTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    public static String formatBigDecimalNumber(BigDecimal value) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat decimalFormat = new DecimalFormat("0.00", symbols);
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        return decimalFormat.format(value);
    }

}
