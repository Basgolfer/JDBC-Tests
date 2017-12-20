import org.junit.*;

public class SlackHubDBConnectionTest {

    SlackHubDBConnection slackHubDB;


    @Before
    public void setUp() {
        slackHubDB = new SlackHubDBConnection();
        slackHubDB.executeQuery("INSERT INTO SlackHubDB_Tests.UserTableTest (name, username, password)\n" +
                "values (\"Brian Sutton\", \"Basgolfer\", \"I love zach\");", false);
        slackHubDB.executeQuery("INSERT INTO SlackHubDB_Tests.UserTableTest (name, username, password)\n" +
                "values (\"Zachary Stimmel\", \"ZStimmel\", \"I am engaged\");", false);
        slackHubDB.executeQuery("INSERT INTO SlackHubDB_Tests.UserTableTest (name, username, password)\n" +
                "values (\"Jeff\", \"BBC\", \"I have a wife\");", false);
    }

    @After
    public void tearDown() {
        slackHubDB.executeQuery("TRUNCATE TABLE SlackHubDB_Tests.UserTableTest;", false);
    }

    @Test
    public void testExecuteQuery() throws Exception{
        slackHubDB.executeQuery("SELECT * FROM SlackHubDB_Tests.UserTableTest;", true);
        String expected = "userId = 1\nname = Brian Sutton\nusername = Basgolfer\npassword = I love zach\n\n";
        expected += "userId = 2\nname = Zachary Stimmel\nusername = ZStimmel\npassword = I am engaged\n\n";
        expected += "userId = 3\nname = Jeff\nusername = BBC\npassword = I have a wife\n\n";
        String actual = slackHubDB.printResultSet();
        Assert.assertEquals(expected, actual);
    }

}