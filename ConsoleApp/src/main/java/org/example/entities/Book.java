package org.example.entities;

public class Book implements Comparable<Book> {
    private String author;
    private String title;

    public Book() {
        this.author = "";
        this.title = "";
    }

    public Book (String author, String title) {
        this.author = author;
        this.title = title;
    }

    public void setAuthor(String author){ this.author = author; }
    public void setTitle(String title){ this.author = title; }

    public String getAuthor() {
        return author;
    }
    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass()!=obj.getClass()) return false;
        if (obj instanceof Book) {
            Book book = (Book) obj;
            return ((title.equals(book.title)) && (author.equals(book.author)));

        }
        return false;
    }

    @Override
    public int hashCode() {
        final int shift = 31;
        int result = 1;
        result = shift * result + author.hashCode();
        result = shift * result + title.hashCode();
        return result;
    }

    @Override
    public int compareTo(Book o) {
        int result = this.author.compareTo(o.author);
        if (result == 0) {
            result = this.title.compareTo(o.title);
        }
        return result;
    }

    @Override
    public String toString() {
        return getAuthor() + ", \"" + getTitle() + "\"";
    }
}