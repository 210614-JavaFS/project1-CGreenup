import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class ServiceTest {
	Logger log = LoggerFactory.getLogger(ServiceTest.class);
	
	//Test whether I can get a connection
	@Test
	public void connectionTest() {
		boolean result;
		
		try(Connection conn = ConnectionUtil.getConnection()){
			result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = false;
		}
		
		assertTrue(result);
	}
	
	@Test
	public void encryptionTest() {
		//SHA-256 encryption of the string "hello"
		String encryption = "2cf24dba5fb0a30e26e83b2ac5b9e29e1b161e5c1fa7425e73043362938b9824";
		
		//My method which should generate a SHA-256 encryption
		String myEncryption = User.encryptPassword("hello");
		
		assertEquals(encryption, myEncryption);
	}
}
