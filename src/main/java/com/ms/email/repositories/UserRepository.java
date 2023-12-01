package com.ms.email.repositories;

import com.ms.email.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserModel, Long> {

    @Query(value = "SELECT sub.subscriberEmail FROM TB_SUBSCRIBERS sub")
    List<String> getEmailList();

}
