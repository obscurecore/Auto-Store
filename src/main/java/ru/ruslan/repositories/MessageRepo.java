package ru.ruslan.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ruslan.models.Message;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Long> {
    List<Message> findByTag(String tag);
}
