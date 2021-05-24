package org.mentor.domain;

public final class ConstantValues {
    public static final String REGEX_YES = "^[Yy]([Ee][Ss]){0,1}$";
    public static final String REGEX_NO = "^[Nn][Oo]{0,1}$";
    public static final String REGEX_QUIT = "^[Qq]([Uu][Ii][Tt]){0,1}$";
    public static final String REGEX_WORDS = "^[^\\s]*$";
    public static final String REGEX_QUESTIONS = "^(\n){0,1}[\\sа-яА-я0-9]*$";
    public static final String REGEX_QUESTIONS_WITH_CHOICE = "^(\n||[1-4]){0,1}$";
    public static final String REGEX_DATE = "yyyy-MM-dd HH:mm:ss";
}
