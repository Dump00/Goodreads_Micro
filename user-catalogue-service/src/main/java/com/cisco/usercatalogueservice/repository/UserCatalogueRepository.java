package com.cisco.usercatalogueservice.repository;

import com.cisco.usercatalogueservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCatalogueRepository extends JpaRepository<User, Long> {
}
