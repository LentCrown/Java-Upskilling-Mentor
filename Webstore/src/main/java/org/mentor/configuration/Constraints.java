package org.mentor.configuration;

import org.springframework.stereotype.Component;

@Component
public final class Constraints {
    public static final String REGEX_PRICE = "^[0-9]+.?[0-9]{0,2}$";
    public static final String REGEX_NUMBERS = "^[0-9]+$";
    public static final String REGEX_WORD_SINGLE = "^[^\\s]*$";
    public static final String REGEX_SENTENCE = "^[\\sa-zA-Zа-яА-я0-9]*$";
    public static final String REGEX_PHONE = "^\\+[1-9]{1}[0-9]{10}$";
}
