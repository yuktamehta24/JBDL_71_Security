package org.gfg.JBDL_71_Security.repository;

import org.gfg.JBDL_71_Security.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser, Integer> {

    MyUser findByUsername(String username);
}
