package org.mentor.util;

import dnl.utils.text.table.TextTable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class IOUtils {
    private static Scanner scanner = new Scanner(System.in);

    public static boolean readAnswer(String field_name){
        String userInput;
        boolean update;
        System.out.println("Update '" + field_name + "' field?");
        while (true) {
            System.out.print(field_name + "answer[y/n(default)]> ");
            userInput = scanner.next();
            if(userInput.matches("^[\\sYy]$")){
                update = true;
                break;
            }
            else if (userInput.matches("^[Nn]$")) {
                update = false;
                break;
            }
            System.out.println("Wrong answer, try again..");
        }
        return update;
    }

    public static Integer readInteger(String field_name, String regex){
        String userInput;
        while (true) {
            System.out.print(field_name + "> ");
            userInput = scanner.next();
            if (userInput.matches(regex)) {
                break;
            }
            System.out.println("Wrong " + field_name + " format, try again..");
        }
        return Integer.parseInt(userInput);
    }

    public static Double readDouble(String field_name, String regex) {
        String userInput;
        while (true) {
            System.out.print(field_name + "> ");
            userInput = scanner.next();
            if (userInput.matches(regex)) {
                break;
            }
            System.out.println("Wrong " + field_name + " format, try again..");
        }
        return Double.parseDouble(userInput);
    }

    public static String readWords(String field_name, String regex){
        String userInput;
        while (true) {
            System.out.print(field_name + "> ");
            userInput = scanner.next();
            if (userInput.matches(regex)) {
                break;
            }
            System.out.println("Wrong " + field_name + " format, try again..");
        }
        return userInput;
    }

    public static <T> void displayQueryTable(List<T> selectAll, String[] rowNames){
        int listSize = selectAll.size();
        int rowNumbers = rowNames.length;
        Object[][] data = new Object[listSize][rowNumbers];
        for(int row=0;row<listSize;row++){
            String[] orders = selectAll.get(row).toString().split("\\s+");
            System.arraycopy(orders, 0, data[row], 0, rowNumbers);
        }
        TextTable tt = new TextTable(rowNames, data);
        tt.setAddRowNumbering(true);
        tt.setSort(1);
        tt.printTable();
    }

    public static <T> void displayQueryChoice(List<T> selectAll){
        List<String> descList = new ArrayList<>();
        if (selectAll.isEmpty()) return;
        for (T row: selectAll)
            descList.add(row.toString());
        for (String desc: descList)
            System.out.println(desc);
    }
}
