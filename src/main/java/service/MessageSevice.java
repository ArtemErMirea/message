package service;

import model.entities.Message;
import org.springframework.stereotype.Service;

public interface MessageSevice {
    Message getMessageById(Long id);
    void saveMessage(Message message);
    void deleteMessage(Long id);
}
