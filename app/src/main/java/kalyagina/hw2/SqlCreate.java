package kalyagina.hw2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlCreate {

    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/databaseName";
    private static final String USER = "root";
    private static final String PASSWORD = "12345678";
    private static final String CREATE_GROUP_TABLE_SQL =
            "CREATE TABLE Groupp(id int auto_increment primary key, " + // Groupp с двумя "p", т.к. MySQL не понимает "Group
                    "name varchar(50), " +
                    "id_curator int, " +
                    "FOREIGN KEY(id_curator) REFERENCES Curator(id))";
    private static final String CREATE_STUDENT_TABLE_SQL =
            "CREATE TABLE Student(id int auto_increment primary key, " +
                    "fio varchar(100), " +
                    "sex char(1), " +
                    "id_group int, " +
                    "FOREIGN KEY(id_group) REFERENCES Groupp(id))";
    private final String CREATE_CURATOR_TABLE_SQL =
            "CREATE TABLE Curator(id int auto_increment primary key, " +
                    "fio varchar(30))";

    public static void main(String[] args) throws SQLException {
        SqlCreate sql = new SqlCreate();

        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD)) {
            sql.createCuratorTable(connection);
            sql.createGroupTable(connection);
            sql.createStudentTable(connection);
        }
    }

    public void createCuratorTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(CREATE_CURATOR_TABLE_SQL);
        }
    }

    public void createGroupTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(CREATE_GROUP_TABLE_SQL);
        }
    }

    public void createStudentTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(CREATE_STUDENT_TABLE_SQL);
        }
    }
}