package org.example;

import org.example.data.operations.BubbleSort;
import org.example.entities.Book;
import org.example.utils.CmdArguments;
import org.example.utils.InitialiseObjects;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<String, List<String>> cmd_params;
        try {
            cmd_params = CmdArguments.parseArqs(args);
        } catch (Exception e) {
            System.out.println(e.toString());
            return;
        }

        if(cmd_params.containsKey("help")) {
            System.out.print("Help menu:\n\n"
                    + "-help       -- ..\n\n");
            return;
        }
        //Book[] books_unsorted = InitialiseObjects.initArray(new Book[10]);
        Book[] books_unsorted = InitialiseObjects.initArray(new Book[10]);
        InitialiseObjects.generateBooks(books_unsorted);

        Book[] bubble_sort = books_unsorted.clone();
        Book[] merge_sort = books_unsorted.clone();

        System.out.println("\nИСХОДНЫЕ ДАННЫЕ:");
        for (Book book: books_unsorted) {
            System.out.println(book.toString());
        }

        BubbleSort.bubbleSort(bubble_sort);
        System.out.println("\nСОРТИРОВКА ПУЗЫРЬКОМ:");
        for (Book book: bubble_sort) {
            System.out.println(book.toString());
        }

        BubbleSort.bubbleSort(merge_sort);
        System.out.println("\nСОРТИРОВКА СЛИЯНИЕМ:");
        for (Book book: merge_sort) {
            System.out.println(book.toString());
        }

        System.out.println(books_unsorted.hashCode());
        System.out.println(bubble_sort.hashCode());
        System.out.println(merge_sort.hashCode());

        System.out.print("\nМАССИВЫ ОДИНАКОВЫ?:");
        if (Arrays.compare(bubble_sort, merge_sort)==0)
            System.out.print(" ДА!");
        else
            System.out.print(" НЕТ!");
    }
}
