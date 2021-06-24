package org.mentor.utils;

import org.mentor.configuration.Constraints;
import org.mentor.domain.Pack;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class InputUtils {
    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> enterUserInfo(){
        List<String> answers = new ArrayList<>();
        answers.add(readWord("name", Constraints.REGEX_WORD_SINGLE));
        answers.add(readWord("surname", Constraints.REGEX_WORD_SINGLE));
        answers.add(readWord("phone_number", Constraints.REGEX_WORD_SINGLE));
        return answers;
    }

    public static List<String> createProduct(){
        List<String> answers = new ArrayList<>();
        answers.add(readWord("description", Constraints.REGEX_WORD_SINGLE));
        answers.add(readWord("price", Constraints.REGEX_WORD_SINGLE));
        answers.add(readWord("stored", Constraints.REGEX_WORD_SINGLE));
        return answers;
    }

    public static List<String> enterUserCreds(){
        List<String> answers = new ArrayList<>();
        answers.add(readWord("login", Constraints.REGEX_WORD_SINGLE));
        answers.add(readWord("password", Constraints.REGEX_WORD_SINGLE));
        return answers;
    }

    public static List<Pack> selectProductsForOrder(){
        List<Pack> packList = new ArrayList<>();
        do {
            Pack pack = new Pack();
            pack.setProduct_id(readInteger("product_id"));
            pack.setAmount(readInteger("amount"));
            packList.add(pack);
        } while (readYesNo("Include another product"));
        return packList;
    }

    public static List<String> updateUserInfo() {
        List<String> answers = new ArrayList<>();
        if (readYesNo("Update name")) answers.add(readWord("name", Constraints.REGEX_WORD_SINGLE));
        else answers.add("");
        if (readYesNo("Update surname")) answers.add(readWord("surname", Constraints.REGEX_WORD_SINGLE));
        else answers.add("");
        if (readYesNo("Update phone_number")) answers.add(readWord("phone_number", Constraints.REGEX_WORD_SINGLE));
        else answers.add("");
        return answers;
    }

    public static List<String> updateUserCreds() {
        List<String> answers = new ArrayList<>();
        if (readYesNo("Update login")) answers.add(readWord("name", Constraints.REGEX_WORD_SINGLE));
        else answers.add("");
        if (readYesNo("Update password")) answers.add(readWord("surname", Constraints.REGEX_WORD_SINGLE));
        else answers.add("");
        return answers;
    }

    public static List<String> updateProduct() {
        List<String> answers = new ArrayList<>();
        if (readYesNo("Update description")) answers.add(readWord("description", Constraints.REGEX_WORD_SINGLE));
        else answers.add("");
        if (readYesNo("Update price")) answers.add(readWord("price", Constraints.REGEX_WORD_SINGLE));
        else answers.add("");
        if (readYesNo("Update stored")) answers.add(readWord("stored", Constraints.REGEX_WORD_SINGLE));
        else answers.add("");
        return answers;
    }

    public static boolean readYesNo(String question){
        System.out.print(question + "? answer[y/n]>");
        String answer = scanner.next();
        return answer.matches("y");
    }

    public static Integer readInteger(String field_name){
        while(true){
            System.out.print(field_name + ">");
            String answer = scanner.next();
            if(answer.matches(Constraints.REGEX_NUMBERS)) return Integer.parseInt(answer);
            System.out.println("Wrong answer, try again");
        }
    }

    public static Double readDouble(String field_name) {
        while(true){
            System.out.print(field_name + ">");
            String answer = scanner.next();
            if(answer.matches(Constraints.REGEX_PRICE)) return Double.parseDouble(answer);
            System.out.println("Wrong answer, try again");
        }
    }

    public static String readWord(String field_name, String wordRegex) {
        while (true) {
            System.out.print(field_name + ">");
            String answer = scanner.next();
            if (answer.matches(wordRegex)) return answer;
            System.out.println("Wrong answer, try again");
        }
    }
}
