import java.sql.*;

public class SlackHubDBConnection {

    private Connection connection;
    private String query;
    private ResultSet resultSet;

    private void setQuery(String query) {
        this.query = query;
    }

    private void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    private void establishConnection() {
        final String endpointDatabase = "slackhubdb.clsddgo2hdf4.us-east-1.rds.amazonaws.com";
        final String username = "Basgolfer";
        final String password = "zachissexy";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + endpointDatabase, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery(String query, Boolean selectStatement) {
        setQuery(query);
        establishConnection();
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            if (selectStatement == true) {
                resultSet = statement.executeQuery(this.query);
            }
            else {
                statement.executeUpdate(this.query);
            }
            setResultSet(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public String printResultSet() {
        StringBuilder returnString = new StringBuilder();
        try {
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int numberOfColumns = resultSetMetaData.getColumnCount();
            while (resultSet.next()) {
                int counter = 1;
                    returnString.append(resultSetMetaData.getColumnName(counter));
                    returnString.append(" = ").append(resultSet.getInt(counter)).append("\n");
                    counter++;
                    for (int i = counter; i <= numberOfColumns; i++) {
                        returnString.append(resultSetMetaData.getColumnName(i));
                        returnString.append(" = ").append(resultSet.getString(i)).append("\n");
                    }
                    returnString.append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnString.toString();
    }
}