package org.mentor.dao;

import org.mentor.repository.Client;

import java.util.List;

public interface ClientDao {
    List<Client> findAll();
    Client findById(Integer id);
}
