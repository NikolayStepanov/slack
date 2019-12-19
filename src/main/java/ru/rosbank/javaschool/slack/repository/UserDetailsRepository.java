package ru.rosbank.javaschool.slack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rosbank.javaschool.slack.domain.User;


public interface UserDetailsRepository extends JpaRepository<User, String> {
}
