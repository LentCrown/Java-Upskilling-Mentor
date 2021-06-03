package org.mentor.util;

import org.springframework.stereotype.Service;
import java.util.Scanner;

@Service
public class IOUtils {
    private static final Scanner scanner = new Scanner(System.in);

    public static String readYesNo(String field_name){
        while(true){
            System.out.print(field_name + ">");
            String answer = scanner.next();
            if(answer.matches("Yy")) return answer;
            System.out.println("Wrong answer, try again");
        }
    }

    public static Integer readInteger(String field_name){
        while(true){
            System.out.print(field_name + ">");
            String answer = scanner.next();
            if(answer.matches("Yy")) return Integer.parseInt(answer);
            System.out.println("Wrong answer, try again");
        }
    }

    public static Double readDouble(String field_name) {
        while(true){
            System.out.print(field_name + ">");
            String answer = scanner.next();
            if(answer.matches("Yy")) return Double.parseDouble(answer);
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
