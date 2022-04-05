import com.contact.entity.OrganizationalContact;
import com.contact.entity.PersonalContact;
import com.contact.entity.PhoneBook;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PhoneBookTest {
    PhoneBook phoneBook = new PhoneBook(new String[]{});

    @Before
    public void initPhoneBook() {
        phoneBook.init();
    }

    @Test
    public void addPerson() {
        phoneBook.addPerson("Mike","Stanton", "1990-12-12", "M", "+7-(921)-322-16-91");
        Assert.assertEquals(phoneBook.getAll().get(0).getClass(), PersonalContact.class);
    }

    @Test
    public void addOrganization() {
        phoneBook.addOrganization("Shop", "123 street", "12");
        Assert.assertEquals(phoneBook.getAll().get(0).getClass(), OrganizationalContact.class);
    }

    @Test
    public void remove() {

        phoneBook.addPerson("Mike","Stanton", "1990-12-12", "M", "+7-(921)-322-16-91");

        phoneBook.remove(phoneBook.getAll().get(0));

        Assert.assertEquals(phoneBook.getAll().size(), 0);
    }

    @Test
    public void count() {
        phoneBook.addPerson("Mike","Stanton", "1990-12-12", "M", "+7-(921)-322-16-91");

        Assert.assertEquals(phoneBook.count(), 1);
    }

    @Test
    public void getSearchResult() {
        phoneBook.addPerson("Mike","Stanton", "1990-12-12", "M", "+7-(921)-322-16-91");
        String query = "Mike";
        Assert.assertEquals(phoneBook.getSearchResult(query).size(), 1);
    }
}