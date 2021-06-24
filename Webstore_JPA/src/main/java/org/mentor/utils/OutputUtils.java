package org.mentor.utils;

import org.mentor.model.Order;
import org.mentor.model.SubOrder;

import java.util.List;

public class OutputUtils {
    private static final String[] productAttrib = {"ID", "DESCRIPTION", "PRICE_FOR_PIECE", "STORED"};
    private static final String[] orderAttrib = {"ID", "CLIENT_ID", "ORDER_TIME", "TOTAL_PRICE"};

    public static void displayOrder(Order order, List<SubOrder> orderedProducts){

        System.out.println(order);
    }
}
