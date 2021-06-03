package org.mentor.util;

import org.mentor.domain.Pack;
import org.mentor.model.Product;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IUser {
    private static final Scanner scanner = new Scanner(System.in);

    public static String[] enterUserCredentials(){
        String[] strings = new String[3];
        System.out.print("name>");
        String name = scanner.next();
        System.out.print("surname>");
        String surname = scanner.next();
        System.out.print("phone>");
        String phone = scanner.next();
        strings[0] = name;
        strings[1] = surname;
        strings[2] = phone;
        return strings;
    }

    public static String[] enterAuthCredentials(){
        String[] strings = new String[2];
        System.out.print("login>");
        String login = scanner.next();
        System.out.print("password>");
        String password = scanner.next();
        strings[0] = login;
        strings[1] = password;
        return strings;
    }
    public static String[] enterProductCredentials(){
        String[] strings = new String[3];
        System.out.print("description>");
        String description = scanner.next();
        System.out.print("price>");
        String price = scanner.next();
        System.out.print("stored>");
        String stored = scanner.next();
        strings[0] = description;
        strings[1] = price;
        strings[2] = stored;
        return strings;
    }

    public static List<Pack> enterOrderCredentials(List<Product> products){
        Scanner input = new Scanner(new InputStreamReader(System.in));
        List<Pack> packs = new ArrayList();
        for (Product product:products)
            System.out.println(product);
        System.out.println("Enter numbers of products you want to add in order, separated by space. Example:<1;2;3;4>");
        String[] stringArr = {"1", "2"};
        for (String s : stringArr) {
            Integer selectedIndex = Integer.parseInt(s);
            Integer amount = input.nextInt();
            packs.add(new Pack(products.get(selectedIndex), amount));
        }
        return packs;
    }
}
