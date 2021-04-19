package org.example.utils;

import org.example.entities.Book;

import java.util.Random;

public class InitialiseObjects {

    /**
     * @param array An array of objects which we need to initialize
     * @return Initialized array of objects
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] initArray(T[] array) {
        int size=array.length;
        for (int i=0; i<size; i++) array[i] = (T) new Object(); //TODO: solve ArrayStoreException
        return array;
    }

    /**
     * @param array An array of Books that we need to fill with random content
     */
    public static void generateBooks(Book[] array) {
        String[] titles = {
                "Два капитана",
                "Евгений Онегин",
                "Мастер и Маргарита",
                "Мертвые души",
                "Война и мир",
                "Левша",
                "Басни",
                "А зори здесь тихие",
                "Тихий Дон",
                "Каштанка"
        };
        String[] authors = {
                "Вениамин Каверин",
                "Александр Пушкин",
                "Михаил Булгаков",
                "Николай Гоголь",
                "Лев Толстой",
                "Николай Лесков",
                "Иван Крылов",
                "Борис Васильев",
                "Михаил Шолохов",
                "Антон Чехов"
        };
        Random random = new Random();
        for (Book book : array) {
            book.setAuthor(authors[random.nextInt(authors.length)]);
            book.setTitle(titles[random.nextInt(titles.length)]);
        }
    }

}
