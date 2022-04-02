import com.contact.entity.PersonalContact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PersonalContactTest {
    PersonalContact person = new PersonalContact();

    @BeforeEach
    public void initPerson() {
        person.setName("Mike");
        person.setSurname("Stanton");
        person.setGender("M");
        person.setBirthDate("1990-10-05");
        person.setPhoneNumber("+7-921-322-16-91");
    }

    @Test
    public void editField() {
        String field = "name";
        String newValue = "Nik";
        person.editField(field, newValue);
        Assertions.assertEquals(person.getName(), "Nik");
    }
}