package by.twister.service;

import by.twister.dao.MessageDao;
import by.twister.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Override
    public List<Message> findAll() {
        List<Message> messages = messageDao.findAll();
        return messages;
    }

    @Override
    public Message findById(Integer id) {
        Message message = messageDao.findById(id);
        return message;
    }

    @Override
    public void add(Message message) {
        messageDao.add(message);
    }

    @Override
    public void update(Message message) {
        messageDao.update(message);
    }

    @Override
    public void delete(Integer id) {
        messageDao.delete(id);
    }
}
