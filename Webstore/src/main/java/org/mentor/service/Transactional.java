package org.mentor.service;

public interface Transactional {
    void begin();
    void commit();
    void rollback();
    void breakConnection();
}
