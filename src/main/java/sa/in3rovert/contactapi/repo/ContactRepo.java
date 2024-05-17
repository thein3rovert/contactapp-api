package sa.in3rovert.contactapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sa.in3rovert.contactapi.domain.Contact;

import java.util.Optional;

/**
 * @author Samad Olaibi (Thein3rovert)
 * @version 1.0
 * @license thein3rovert,  (<a href="https://github.com/thein3rovert">thein3rovert</a>)
 * @email danielolaibi@gmail.com
 */
@Repository
public interface ContactRepo extends JpaRepository<Contact, String> {

    Optional<Contact> findById(String id);
    //Now let create a service that we can use in the controller ==> Service package
}