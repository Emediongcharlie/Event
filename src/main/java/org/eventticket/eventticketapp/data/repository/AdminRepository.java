package org.eventticket.eventticketapp.data.repository;
import org.eventticket.eventticketapp.data.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
     Optional<Admin> findByUsername(String username);
}


