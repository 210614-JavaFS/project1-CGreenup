import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.data.UserDAOImpl;
import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.utils.ConnectionUtil;

public class ServiceTest {
	static Logger log = LoggerFactory.getLogger(ServiceTest.class);
	
	
	@BeforeAll
	public static void denoteBeginningInLog() {
		log.info("==================================================");
		log.info("Beginning testing");
	}
	
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
	
	@Test
	public void getUserTest() {
		UserDAOImpl impl = new UserDAOImpl();
		
		User user1 = impl.getUser("JuneAdmin");
		log.info(user1.toString());
		
		User user2 = impl.getUser("example@email.com");
		log.info(user2.toString());
		
		assertEquals(user1, user2);
	}
	
	@Test
	public void loginTest() {
		UserService.getUserService();
		
		User user = new User();
		user.setUsername("JuneAdmin");
		user.setPassword("pass");
		
		UserService.login(user);
		
		log.info("LOGIN TEST: " + user.toString());
	}
	
	@AfterAll
	public static void denoteEndOfTesting() {
		log.info("All tests have been conducted");
		log.info("==================================================");
	}
	
}
