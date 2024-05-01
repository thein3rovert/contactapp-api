package sa.in3rovert.contactapi.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.internal.Function;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sa.in3rovert.contactapi.domain.Contact;
import sa.in3rovert.contactapi.repo.ContactRepo;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.function.BiFunction;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static sa.in3rovert.contactapi.constant.Constant.PHOTO_DIRECTORY;

@Service
@Transactional (rollbackOn = Exception.class)
@Slf4j
@RequiredArgsConstructor //Used for dependency injection

public class Contactservice {

    private final ContactRepo contactRepo;
    //Let define method that will be used

    /**
     * Retrieves all contacts paginated.
     *
     * @param page  the page number
     * @param size  the number of contacts per page
     * @return      the page of contacts
     */
    public Page<Contact> getAllContacts(int page , int size) {
        return contactRepo.findAll(PageRequest.of(page, size,  Sort.by("name")));
    }


    /**
     * Retrieves a contact by its ID.
     *
     * @param id  the contact ID
     * @return    the contact, or throws a RuntimeException if not found
     */
    public Contact getContact(String id) {
        return contactRepo.findById(id).orElseThrow(() -> new RuntimeException("Contact not Found"));
    }

    /**
     * Saves a new contact to the database.
     *
     * @param contact  the contact to be saved
     * @return         the saved contact
     */
    public Contact createContact(Contact contact) {
        return contactRepo.save(contact);
    }

    public void deletecontact(Contact contact) {
    }

    /**
     * Uploads a photo for a contact.
     *
     * @param id       the contact ID
     * @param file     the photo file to upload
     * @return         the URL of the uploaded photo
     */

    public String uploadPhoto(String id, MultipartFile file) {
        log.info("Saving picture for user ID: {}",id);
        Contact contact = getContact(id);
        String photoUrl = photoFunction.apply(id,file);
        contact.setPhotoUrl(photoUrl);
        contactRepo.save(contact);
        return photoUrl;
    }


    /**
     * Extracts the file extension from a filename.
     *
     * @param filename  the filename to extract the extension from
     * @return          the file extension, or ".png" if the filename does not contain a space
     */
    private final Function<String, String> fileExtension = filename -> Optional.of(filename).filter(name -> name.contains(" "))
            .map(name -> "." + name.substring(filename.lastIndexOf(".") + 1 )).orElse(".png");


    // This BiFunction takes a String id and a MultipartFile image as input and returns a String.
    // It combines the id with the original filename of the image after applying a file extension function.
    // It then tries to save the image file to a specified directory and returns the URI of the saved image.
    // If there's an exception during the process, it throws a RuntimeException with the message "Can't find image".
    // There is a TODO comment indicating that the location to save the image needs to be defined.

    public final BiFunction<String, MultipartFile, String> photoFunction = (id, image) -> {
        String filename = id + fileExtension.apply(image.getOriginalFilename());
        try {
            Path fileStorageLocation = Paths.get(PHOTO_DIRECTORY).toAbsolutePath().normalize();
            if(!Files.exists(fileStorageLocation)) {
                Files.createDirectories(fileStorageLocation); }
            Files.copy(image.getInputStream(), fileStorageLocation.resolve(filename), REPLACE_EXISTING);
        return ServletUriComponentsBuilder.
                fromCurrentContextPath()
                .path("/contacts/image/" + filename).toUriString();
        }catch (Exception exception) {
            throw new RuntimeException("Cant find image");
        }
        // Todo: Define where we want to save the image

    };
}
