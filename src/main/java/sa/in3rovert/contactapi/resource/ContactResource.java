package sa.in3rovert.contactapi.resource;

import sa.in3rovert.contactapi.domain.Contact;
import sa.in3rovert.contactapi.service.Contactservice;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;


import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;
import static sa.in3rovert.contactapi.constant.Constant.PHOTO_DIRECTORY;


@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor

public class ContactResource {
    private final Contactservice contactService;

    /**
     * Creates a new contact.
     *
     * @param  contact  the contact object containing the details of the contact to be created
     * @return          the created contact wrapped in a ResponseEntity with status code 201 (Created)
     */
    @PostMapping
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
        //return ResponseEntity.ok().body(contactService.createContact(contact));
        return ResponseEntity.created(URI.create("/contacts/userID")).body(contactService.createContact(contact));
    }

    /**
     * Retrieves a paginated list of contacts.
     *
     * @param  page  the page number (default: 0)
     * @param  size  the number of contacts per page (default: 10)
     * @return       a ResponseEntity containing a Page of Contact objects
     */
    @GetMapping
    public ResponseEntity<Page<Contact>> getContacts(@RequestParam(value = "page", defaultValue = "0") int page,
                                                     @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok().body(contactService.getAllContacts(page, size));
    }

    /**
     * Retrieves a contact by its ID.
     *
     * @param  id  the contact ID
     * @return     the contact wrapped in a ResponseEntity with status code 200 (OK)
     */
    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContact(@PathVariable(value = "id") String id) {
        return ResponseEntity.ok().body(contactService.getContact(id));
    }

    /**
     * Uploads a photo for a contact.
     *
     * @param  id    the ID of the contact
     * @param  file  the photo file to upload
     * @return       the response entity containing the uploaded photo URL
     */
    @PutMapping("/photo")
    public ResponseEntity<String> uploadPhoto(@RequestParam("id") String id, @RequestParam("file")MultipartFile file) {
        return ResponseEntity.ok().body(contactService.uploadPhoto(id, file));
    }

    /**
     * Retrieves the photo with the specified filename.
     *
     * @param  filename  the name of the photo file to retrieve
     * @return           the byte array containing the photo data
     * @throws IOException if an I/O error occurs while reading the photo file
     */
    @GetMapping(path = "/image/{filename}", produces = {IMAGE_PNG_VALUE, IMAGE_JPEG_VALUE})
    public byte[] getPhoto(@PathVariable("filename") String filename) throws IOException {
        return Files.readAllBytes(Paths.get(PHOTO_DIRECTORY + filename));
    }
}

