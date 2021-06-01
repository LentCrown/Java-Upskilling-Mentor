package org.mentor.util;

import dnl.utils.text.table.TextTable;
import org.mentor.model.Client;
import org.mentor.model.Product;
import org.mentor.model.Receipt;
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
            if(userInput.matches("^[Yy]$")){
                update = true;
                break;
            }
            else if (userInput.matches("^[\\s\\nNn]$")) {
                update = false;
                break;
            }
            System.out.println("Wrong answer, try again..");
        }
        return update;
    }

    public static Integer readInteger(String field_name, String regex){
        String userInput;
        System.out.println("Choose " + field_name +":");
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
        System.out.println("Choose " + field_name +":");
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
        System.out.println("Choose " + field_name +":");
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

    public static <T> void displayQueryTable(List<String> rowLines, String[] rowNames){
        int listSize = rowLines.size();
        int rowNumbers = rowNames.length;
        Object[][] data = new Object[listSize][rowNumbers];
        for(int row=0;row<listSize;row++){
            String[] orders = rowLines.get(row).split("\\s+");
            System.arraycopy(orders, 0, data[row], 0, rowNumbers);
        }
        TextTable tt = new TextTable(rowNames, data);
        tt.setAddRowNumbering(true);
        tt.setSort(1);
        tt.printTable();
    }

    public static List<String> stringListReceipts(List<Receipt> receiptList){
        if (receiptList.isEmpty() || receiptList.equals(null)){ return null; }
        List<String> strings = new ArrayList<>();
        for (Receipt receipt:receiptList){
            strings.add(receipt.getId() + " " + receipt.getBought() + " " + receipt.getTotalPrice()
                    + " " + receipt.getClient().getId() + " " + receipt.getProduct().getId());
        }
        return strings;
    }

    public static List<String> stringListProducts(List<Product> productList){
        if (productList.isEmpty() || productList.equals(null)){ return null; }
        List<String> strings = new ArrayList<>();
        for (Product product:productList){
            strings.add(product.getId() + " " + product.getDesc() + " "
                    + product.getPrice() + " " + product.getStored());
        }
        return strings;
    }

    public static List<String> stringListClients(List<Client> clientList){
        if (clientList.isEmpty() || clientList.equals(null)){ return null; }
        List<String> strings = new ArrayList<>();
        for (Client client:clientList){
            strings.add(client.getId() + " " + client.getName() + " " +
                    client.getSurname() + " " + client.getPhone_number());
        }
        return strings;
    }
}
