package ru.rosbank.javaschool.slack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rosbank.javaschool.slack.domain.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
