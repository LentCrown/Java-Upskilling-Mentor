package org.example.data.entities;

interface TableFormat {
    void openFile();
    void closeFile();
    String read();
}
