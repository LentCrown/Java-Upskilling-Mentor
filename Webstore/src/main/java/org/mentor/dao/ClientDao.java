package org.mentor.dao;

import org.mentor.model.Client;

import java.util.List;

public interface ClientDao {
    List<Client> findAll();
    Client findById(Integer id);
}
