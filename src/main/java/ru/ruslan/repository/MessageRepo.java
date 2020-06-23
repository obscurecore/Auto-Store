package ru.ruslan.repository;

import org.springframework.data.repository.CrudRepository;
import ru.ruslan.entity.Message;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Long> {

    List<Message> findByTag(String tag);

}
