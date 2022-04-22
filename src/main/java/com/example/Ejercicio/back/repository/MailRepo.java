package com.example.Ejercicio.back.repository;

import com.example.Ejercicio.back.model.Mail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailRepo extends JpaRepository<Mail, Long> {
}
