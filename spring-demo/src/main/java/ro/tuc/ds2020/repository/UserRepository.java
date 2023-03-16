package ro.tuc.ds2020.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.tuc.ds2020.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{
    User findByUsername(String username);
}
