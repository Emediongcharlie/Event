package org.eventticket.eventticketapp.data.repository;

import org.eventticket.eventticketapp.data.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdminRepository  extends JpaRepository<Admin, Integer> {

//    Admin findByUsername(String username);

}
