import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.revature.utils.ConnectionUtil;

public class ServiceTest {
	
	//Test whether I can get a connection
	@Test
	public void connectionTest() {
		boolean result = false;
		
		try(Connection conn = ConnectionUtil.getConnection()){
			result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = false;
		}
		
		assertTrue(result);
	}
}
