## Contact API Application Documentation

#### Overview:
The Contact API application serves as the backend for the Contact List application that I am building. The frontend is developed using React.js, while the backend is powered by Spring Boot. The application structure is built using IntelliJ IDEA.

#### Application Structure:
The application is organized into several packages, each with specific responsibilities.

1. **Domain Package:**
    - **Contact Class:**
        - This class defines the entities of the Contact application.
        - Attributes include: `ID`, `name`, `email`, `title`, `phone number`, `email address`, `status`, and `photo URL`.
        - Annotations:
            - `@Entity`: Marks the class as a JPA entity.
            - `@Table`: Specifies the table in the database.
            - `@Id`, `@GeneratedValue`, `@Column`: Define the attributes and their mapping to the database columns.
2. **Repository Package:**
    - **ContactRepository Interface:**
        - Extends `JpaRepository<Contact, String>`.
        - Provides CRUD operations for Contact entities.
        - Method:
            - `findById(String id)`: Retrieves a contact by its unique ID.

3. **Resource Package:**
    - **ContactResource Class:**
        - This class is annotated with `@RestController`, indicating it's a Spring MVC controller.
        - Provides RESTful APIs for managing contacts.
        - Methods:
            - `createContact(Contact contact)`: Creates a new contact in the database and returns the contact and its ID.
            - `getAllContacts(Pageable pageable)`: Retrieves all contacts from the database with pagination support.
            - `getContactById(String id)`: Retrieves a single contact based on its ID.
            - `uploadProfilePicture(MultipartFile file, String contactId)`: Uploads a profile picture for a specific contact.

#### Detailed Class and Method Descriptions:
1. **Contact Class:**
    - **Attributes:**
        - `ID`: Unique identifier for each contact.
        - `name`: Name of the contact.
        - `email`: Email address of the contact.
        - `title`: Title of the contact.
        - `phoneNumber`: Phone number of the contact.
        - `emailAddress`: Email address of the contact.
        - `status`: Status of the contact.
        - `photoURL`: URL of the contact's photo.
    - **Annotations:**
        - `@Entity`: Defines the class as a JPA entity.
        - `@Table`: Specifies the table name in the database.
        - `@Id`: Marks the primary key.
        - `@GeneratedValue`: Specifies the generation strategy for the primary key.
        - `@Column`: Maps the attributes to the columns in the database table.

2. **ContactRepository Interface:**
    - Extends `JpaRepository<Contact, String>`.
    - **Methods:**
        - `findById(String id)`: Finds a contact by its unique ID. This method facilitates easy access to contact information across the application.

3. **ContactResource Class:**
    - **Annotations:**
        - `@RestController`: Indicates that this class is a REST controller.
    - **Methods:**
        - `createContact(Contact contact)`: Adds a new contact to the database and returns