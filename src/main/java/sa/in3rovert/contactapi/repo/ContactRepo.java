package sa.in3rovert.contactapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sa.in3rovert.contactapi.domain.Contact;

import java.util.Optional;

@Repository
public interface ContactRepo extends JpaRepository<Contact, String> {

    Optional<Contact> findById(String id);
    //Now let create a service that we can use in the controller ==> Service package
}