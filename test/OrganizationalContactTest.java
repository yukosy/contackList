import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrganizationalContactTest {
    OrganizationalContact organization = new OrganizationalContact();

    @Before
    public void initOrganization() {
        organization.setName("Shop");
        organization.setAddress("1 street");
    }

    @Test
    public void setName() {
        String actual = "PetShop";
        organization.setName(actual);
        Assert.assertEquals(organization.getContactName(),actual);
    }

    @Test
    public void setAddress() {
        String actual = "2nd street";
        organization.setAddress(actual);
        Assert.assertEquals(organization.getAddress(), actual);
    }
}