package com.iyuriy.notification.repositories;

import com.iyuriy.notification.common.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByChatId(Long chatId);

    void deleteUserByChatId(Long chatId);
}