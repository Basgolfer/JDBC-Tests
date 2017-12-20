public class Main {
    public static void main(String[] args) {
        SlackHubDBConnection slackHubDB = new SlackHubDBConnection();
        slackHubDB.executeQuery("SELECT * FROM SlackHubDB.Users;", true);
        System.out.println(slackHubDB.printResultSet());
//        slackHubDB.executeQuery("SELECT * FROM SlackHubDB.Messages");
//        slackHubDB.printResultSet();
    }
}
