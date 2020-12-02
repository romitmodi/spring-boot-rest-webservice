package learning.spring.boot.rest.webservices.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import learning.spring.boot.rest.webservices.user.jpa.model.User2;

public interface User2Repository extends JpaRepository<User2, Long> {

}
