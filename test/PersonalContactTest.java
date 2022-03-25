import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PersonalContactTest {
    PersonalContact person = new PersonalContact();

    @Before
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
        Assert.assertEquals(person.getName(), "Nik");

    }

    @Test
    public void setName() {
        person.setName("Nik");
        Assert.assertEquals(person.getName(), "Nik");
    }

    @Test
    public void setSurname() {
        person.setSurname("Smith");
        Assert.assertEquals(person.getSurname(), "Smith");
    }

    @Test
    public void setBirthDate() {
        person.setBirthDate("1999-10-05");
        Assert.assertEquals(person.getBirthDate(), "1999-10-05");
    }

    @Test
    public void setGender() {
        person.setGender("F");
        Assert.assertEquals(person.getGender(), "F");
    }
}