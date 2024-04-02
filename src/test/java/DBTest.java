import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBTest {

    @Test
    public void testConn() throws Exception{
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "ourbook", "12341234");

    }
}
