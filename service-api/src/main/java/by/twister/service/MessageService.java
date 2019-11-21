package by.twister.service;

import by.twister.model.Message;

import java.util.List;

public interface MessageService {

    List<Message> findAll();

    Message findById(Integer id);

    void add(Message message);

    void update(Message message);

    void delete(Integer id);
}
