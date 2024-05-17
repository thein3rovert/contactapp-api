# Full Stack ReactJS with Spring Boot Application

---
![ContactAPI(PKM ongoing).png](ReadmeAssets%2FContactAPI%28PKM%20ongoing%29.png)
This project is a full stack application developed using ReactJS for the frontend and Spring Boot for the backend. It focuses on managing contacts within the application and includes features for CRUD operations and photo uploading functionalities.
## Project Overview
This project involves the development of a full stack application using ReactJS with Spring Boot. The goal is to create a contact management system where users can manage their contacts, including adding, updating, and deleting contacts, as well as uploading and retrieving contact photo

The project includes the following key components:
1. Backend Development
2. Frontend Development
3. API Testing
4. Configuration and Settings.
## Technologies Used
```
- Front-end: HTML, CSS, React.js
- Back-end: Spring-Boot
- Database: Postgres
- Tools - Postman, PgAdmin
- Version Control: GIT
```
### Prerequisites

```
- Java JDK 21 and Above
- Spring Boot 3.1.5
- Maven
```

![[ContactApp-README-20240517100700634.png|378]]
![[ContactApp-README-20240517100714321.png|378]]
### Installation
1. Download or clone the project from the GitHub repository into your desired environment.
2. Open a terminal or command prompt
```bash
cd contactapi
```
3. Navigate to the project directory.
4. Open the Project in desired IDE (IntelliJ Advisable).
5. No special dependencies or package needs to be installed also no special configurations needs to be installed. Every dependencies and package are already handles by spring boot.
6. Make sure you have PostgreSQL server running. If you don't have PostgreSQL you can download from this [link](https://www.postgresql.org/download/)
7. Download Postman in other to test and interact with the API and PgAdmin I would recommend (PgAdmin4).
8. After that check the `application.yml` and make sure you change the `datasource` to your PostgreSQL credentials![[ContactApp-API-README-20240517111546810.png]]
   More about the configuration will be explained [[ContactAPI-README#Data Source Config|below]].

## Project Setup

To set up the project, follow these steps:

1. Clone the repositories.
2. Install necessary dependencies for both frontend and backend components.
3. Configure data sources and ensure proper CORS settings for seamless communication.
4. Run the application to start the backend server and frontend interface.

## Data Source Config
1. **`spring.datasource.url`:** Change the `localhost` to the IP address or hostname of the new database server.
2. **`spring.datasource.username`:** Update the username if needed.
3. **`spring.datasource.password`:** Update the password for the new database server.
4. **`server.port`:** If needed, change the port number where the application will run on the new computer.

Make sure to adjust these configurations accordingly to match the setup of your machine where you want to run the application.

## API Testing
API testing can be performed using tools like Postman to interact with the backend services. Ensure to test functionalities such as creating, updating, and deleting contacts, as well as uploading photos for contacts.
1. Open Postman and create a new request:
    - Launch Postman on your computer.
    - Click on the "New" button to create a new request.
      ![[ContactApp-API-README-20240517113449095.png|492]]
2. Enter the URL of the API endpoint you want to test:
- In the request URL field, enter the specific endpoint you want to test. For example:
```HTTP
GET: http://localhost:8080/contacts -- Used to get all contacts
GET: http://localhost:8080/contacts/014eca9c-0118-4274-951b-43843372c539 -- Used to get a contacts by id. 
POST:  http://localhost:8080/contacts -- Used to create a new contact
PUT: http://localhost:8080/contacts/photo --Used to Upload image desired contact
```
3. Select the appropriate HTTP method based on the functionality you want to test:
```HTTP
GET
POST
UPDATE
DELETE
```

- Getting all contacts
```
GET http://localhost:8080/contacts -- Used to get all contacts
```

```json
"content": [
        {
            "id": "8bacc747-de37-4596-957c-5561461bd17c",
            "name": "Adams Olaibi",
            "email": "adaolaibi@gmail.com",
            "title": "Child care and suport worker",
            "phone": "079 30166 090",
            "address": "London Uk ",
            "status": "Active",
            "photoUrl": "http://localhost:8080/contacts/image/8bacc747-de37-4596-957c-5561461bd17c.jpg?$updated_at=1714576007743"
        }
```

- Getting a contact
```
GET http://localhost:8080/contacts/8bacc747-de37-4596-957c-5561461bd17c -- Used to get a contacts by id.
```
- Updating a contact
```
POST http://localhost:8080/contacts/014eca9c-0118-4274-951b-43843372c539 -- Used to get a contacts by id.
```

## Code Structure

Overview of the project’s directory structure and key files.

The project's code follows a specific directory structure to maintain organization and modularity. Here is an overview of the main directories and their contents:

- `src/`: This directory contains the source code of the project.
- `domain`/: This directory contains a contact class.
#### Contact class
The `Contact` class is an entity class in your codebase. It represents a contact with attributes like `id`, `name`, `email`, `title`, `phone`, `address`, `status`, and `photoUrl`. It is annotated with `@Entity`, `@Getter`, `@Setter`, `@NoArgsConstructor`, `@AllArgsConstructor`, `@JsonInclude(NON_DEFAULT)`, and `@Table(name = "contacts")`. The `id` field is marked as the primary key with `@Id` and it has additional annotations like `@UuidGenerator` and `@Column(name = "id", unique = true, updatable = false)`. This class is designed to be managed by JPA for database operations.

```java
@Table(name = "contacts")  
public class Contact {  
@Id  
@UuidGenerator  
@Column(name = "id", unique = true, updatable = false)
```

The `id` field in the `Contact` class is designated as the primary key for the entity. It is annotated with `@Id` to indicate this role. Additionally, it has the `@UuidGenerator` annotation, which suggests that the `id` field is generated using a UUID (Universally Unique Identifier) generator.

Overall, the `id` field in the `Contact` class serves as a unique identifier for each contact entity and is generated using a UUID generator.

---

- `Repo`/: This directory contains a ContactRepo interface class.
#### ContactRepo Class

The `ContactRepo` class is an interface that extends `JpaRepository<Contact, String>`. This means that it inherits the CRUD (Create, Read, Update, Delete) operations provided by `JpaRepository` for the `Contact` entity, with the entity type being `Contact` and the type of the entity's primary key being `String`.

The main objective of the `ContactRepo` interface is to provide a way to interact with the database for the `Contact` entity. By extending `JpaRepository`, it automatically inherits methods for common database operations like saving, updating, deleting, and finding entities.
```java
@RestController  
@RequestMapping("/contacts")  
@RequiredArgsConstructor
```

##### Method
- `findById(String id)` : defined in the `ContactRepo` interface, which allows you to find a contact by its `id`. This method returns an `Optional<Contact>`, indicating that it may or may not find a contact with the given `id`.
  The `@Repository` annotation on the interface signifies that this interface is a Spring Data repository, allowing it to be automatically scanned and instantiated as a bean for database operations.
```java
@Repository  
public interface ContactRepo extends JpaRepository<Contact, String> {  
Optional<Contact> findById(String id);  
}
```
---

- `Service`/: This directory contains a Contact Services` service` class.
#### ContactService Class
This service class that handles the business logic related to managing contact entities in your application. Here is an overview of the class
 
```java
@Service  
@Transactional (rollbackOn = Exception.class)  
@Slf4j  
@RequiredArgsConstructor //Used for dependency injection
@postmapping
@getmapping
@ptmapping
```
- The class is annotated with `@Service`, indicating that it is a service bean in the Spring framework.
- It uses `@Transactional(rollbackOn = Exception.class)` to specify that transactions should be rolled back on any `Exception`.
- The `@Slf4j` annotation is used for logging.
- `@RequiredArgsConstructor` is used for constructor-based dependency injection.
  The main objective of the `Contactservice` class is to encapsulate the logic for interacting with the `ContactRepo` repository and provide methods for retrieving, creating, updating, and deleting contact entities. Here are some key methods in the class:

1. `getAllContacts(int page, int size)`: Retrieves all contacts paginated.
   2
```java
public Page<Contact> getAllContacts(int page , int size) {  
return contactRepo.findAll(PageRequest.of(page, size, Sort.by("name")));  
}
```
3. `getContact(String id)`: Retrieves a contact by its ID, throwing a `RuntimeException` if not found.
```java
public Contact getContact(String id) {  
return contactRepo.findById(id).orElseThrow(() -> new RuntimeException("Contact not Found"));  
}
```
5. `createContact(Contact contact)`: Saves a new contact to the database.
```java
public Contact createContact(Contact contact) {  
return contactRepo.save(contact);  
}
```
7. `uploadPhoto(String id, MultipartFile file)`: Uploads a photo for a contact, updates the contact's `photoUrl`, and returns the URL of the uploaded photo.
```java
public String uploadPhoto(String id, MultipartFile file) {  
log.info("Saving picture for user ID: {}",id);  
Contact contact = getContact(id);  
String photoUrl = photoFunction.apply(id,file);  
contact.setPhotoUrl(photoUrl);  
contactRepo.save(contact);  
return photoUrl;  
}
```

- `Resources`/: This directory contains a ContactResource ` resource` class.
#### ContactResource Class
The `ContactResource` class is a REST controller that defines various endpoints for interacting with contact-related resources in your application. Here is an overview of the class
```java
@RestController  
@RequestMapping("/contacts")  
@RequiredArgsConstructor
```

- Annotated with `@RestController`, indicating that this class is a controller that handles HTTP requests and returns responses as JSON/XML.
- Uses `@RequestMapping("/contacts")` to map all the endpoints in this class to the `/contacts` base URL.
- `@RequiredArgsConstructor` is used for constructor-based dependency injection
  Helps to provide RESTful endpoints for creating, retrieving, and updating contact entities by delegating the actual business logic to the Contactservice class.
##### EndPoints
1. `createContact(@RequestBody Contact contact)`: POST endpoint to create a new contact. It returns the created contact in a `ResponseEntity` with status code 201 (Created).
```java
@PostMapping  
public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {    
return ResponseEntity.created(URI.create("/contacts/userID")).body(contactService.createContact(contact));  
}
```
2. `getContacts(int page, int size)`: GET endpoint to retrieve a paginated list of contacts. It returns a `ResponseEntity` containing a page of contact objects.
```java
@GetMapping  
public ResponseEntity<Page<Contact>> getContacts(@RequestParam(value = "page", defaultValue = "0") int page,  
@RequestParam(value = "size", defaultValue = "10") int size) {  
return ResponseEntity.ok().body(contactService.getAllContacts(page, size));  
}
```
1. `getContact(String id)`: GET endpoint to retrieve a contact by its ID. It returns the contact in a `ResponseEntity` with status code 200 (OK).

```java
@GetMapping("/{id}")  
public ResponseEntity<Contact> getContact(@PathVariable(value = "id") String id) {  
return ResponseEntity.ok().body(contactService.getContact(id));  
}
```
3. `uploadPhoto(String id, MultipartFile file)`: PUT endpoint to upload a photo for a contact. It returns the uploaded photo URL in a `ResponseEntity`.
```java
@PutMapping("/photo")  
public ResponseEntity<String> uploadPhoto(@RequestParam("id") String id, @RequestParam("file")MultipartFile file) {  
return ResponseEntity.ok().body(contactService.uploadPhoto(id, file));  
}
```
5. `getPhoto(String filename)`: GET endpoint to retrieve the photo data with the specified filename. It returns the byte array containing the photo data.
```java
@GetMapping(path = "/image/{filename}", produces = {IMAGE_PNG_VALUE, IMAGE_JPEG_VALUE})  
public byte[] getPhoto(@PathVariable("filename") String filename) throws IOException {  
return Files.readAllBytes(Paths.get(PHOTO_DIRECTORY + filename));  
}
```

- `Config`/: This directory contains a CorsConfig class.
#### CorsConfig class

The `CorsConfig` class is a configuration class responsible for setting up Cross-Origin Resource Sharing (CORS) configuration in your application. Here is an overview of the class:

```java
@Configuration
@Bean
```
- Annotated with `@Configuration`, indicating that this class contributes to the Spring application context for setting up beans.
  The main objective of the `CorsConfig` class is to configure CORS settings to allow cross-origin requests from specified origins, headers, methods, and credentials.
##### Method
- Defines a method `corsFilter()` annotated with `@Bean` that returns a `CorsFilter` bean for handling CORS configuration.

```java
@Bean  
public CorsFilter corsFilter() {  
var urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();  
var corsConfiguration = new CorsConfiguration();
```
- Inside the `corsFilter()` method, it sets up a `CorsConfiguration` object with the following configurations:
    - `setAllowCredentials(true)`: Indicates that the browser should include any cookies in the request.
    - `setAllowedOrigins(List.of("http://localhost:3000", "http://192.168.0.10:3000"))`: Specifies the allowed origins for CORS requests.
    - `setAllowedHeaders(...)`: Defines the allowed headers for CORS requests.
    - `setExposedHeaders(...)`: Sets the headers that are exposed to the browser.
    - `setAllowedMethods(...)`: Specifies the allowed HTTP methods for CORS requests.
- Registers the `CorsConfiguration` with a `UrlBasedCorsConfigurationSource` using `registerCorsConfiguration("/**", corsConfiguration)`.
- Finally, it creates and returns a `CorsFilter` instance initialized with the `UrlBasedCorsConfigurationSource`.


## Frontend Development

The frontend components include headers, contact items, and contact lists for displaying and interacting with contact data. ReactJS is used for creating dynamic user interfaces that communicate with the backend services.

## Troubleshooting

If you encounter CORS errors or data display issues, refer to the provided solutions in the notes to resolve these issues effectively.

## Conclusion

This project showcases the integration of ReactJS with Spring Boot to develop a robust full stack application for contact management. It emphasizes best practices in backend and frontend development, offering a comprehensive solution for building modern web applications.

