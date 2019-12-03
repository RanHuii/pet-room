package petroom.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

public interface UserRepository extends Repository<User, Integer> {

    //Query user by its last Name
    @Transactional(readOnly = true)
    Collection<User> findByLastName(@Param("lastName") String lastName);

    //Query user by its id
    List<User> findUserById(@Param("id") Integer id);

    //Query user by its username
    User findUserByUsername(@Param("username") String username);

    @Query("select user from User user where user.username =:username")
    User findByUsername(@Param("username") String username);
    /**
    * Save a user to the database
    **/
    void save(User user);
}
