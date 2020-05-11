package faber.codetest.nhat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import faber.codetest.nhat.entity.User;
import faber.codetest.nhat.repository.UserRepository;

@Service
public class UserService extends AbstractCrudService<User, Long> {

    @Autowired
    private UserRepository repository;

    @Override
    public JpaRepository<User, Long> getRepository() {
        return repository;
    }

    /**
     * authenticate and return user info as dto
     *
     * @param email
     * @param password
     * @return
     */
    public User authenticate(String email, String password) {
        //find user by email and compare password
        User user = repository.findByEmail(email);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }
}
