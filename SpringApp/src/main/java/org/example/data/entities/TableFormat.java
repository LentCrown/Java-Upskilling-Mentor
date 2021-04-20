package org.example.data.entities;

interface TableFormat {
    void open();
    void close();
    void reOpen();
    String read();
}
