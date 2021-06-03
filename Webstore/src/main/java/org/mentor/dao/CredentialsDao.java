package org.mentor.dao;

import org.mentor.model.Credential;

public interface CredentialsDao {
    Integer findIdByLogin(String login);
    Credential findById(Integer id);
    void updateCredential(Credential credential);
}
