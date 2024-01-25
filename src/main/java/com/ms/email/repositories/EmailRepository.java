package com.ms.email.repositories;

import com.ms.email.models.EmailModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface EmailRepository extends JpaRepository<EmailModel, Long> {

    Optional<EmailModel> findByOwnerRef(String ownerRef);
    Optional<EmailModel> findByEmailFrom(String emailFrom);
    Optional<EmailModel> findByEmailTo(String emailTo);
    Optional<EmailModel> findBySubject(String subject);
    Optional<EmailModel> findByText(String text);
    Optional<EmailModel> findByStatusEmail(String statusEmail);

    //    Optional<EmailModel> findBySendDateEmail(LocalDateTime sendDateEmail);

}
