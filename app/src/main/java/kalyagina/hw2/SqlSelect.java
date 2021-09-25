package kalyagina.hw2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlSelect {

    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/databaseName";
    private static final String USER = "root";
    private static final String PASSWORD = "12345678";

    private static final String GET_COUNT_OF_STUDENTS_SQL =
            "SELECT COUNT(id)" +
                    " FROM Student";

    private static final String GET_ALL_STUDENTS_SQL =
            "SELECT s.id, s.fio, s.sex, s.id_group, g.name, c.fio " +
                    " FROM Student as s JOIN Groupp as g on s.id_group=g.id " +
                    " JOIN Curator as c ON c.id=g.id_curator";

    private static final String GET_GROUP_WITH_CURATOR_SQL =
            "SELECT g.id, name, fio" +
                    " FROM Groupp as g JOIN Curator as c ON g.id_curator=c.id";

    public static void main(String[] args) throws SQLException {
        SqlSelect sql = new SqlSelect();

        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD)) {

            sql.printCountOfStudents(connection);
            sql.printAllStudents(connection);
            sql.printGroupWithCurator(connection);

        }
    }

    public void printCountOfStudents(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(GET_COUNT_OF_STUDENTS_SQL)) {
            while (resultSet.next()) {
                int count = resultSet.getInt(1);
                System.out.println(count);
            }
        }
    }

    public void printAllStudents(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(GET_ALL_STUDENTS_SQL)) {
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String fio = resultSet.getString(2);
                String sex = resultSet.getString(3);
                int idGroup = resultSet.getInt(4);
                String nameGroup = resultSet.getString(3);
                String fioCurator = resultSet.getString(3);

                String row = String.format("ID: %s, FIO: %s, SEX: %s, IDGROUP: %s, NAMEGROUP: %s, FIOCURATOR: %s", id, fio, sex, idGroup, nameGroup, fioCurator);
                System.out.println(row);

            }
        }
    }

    public void printGroupWithCurator(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(GET_GROUP_WITH_CURATOR_SQL)) {
            while (resultSet.next()) {
                int idGroup = resultSet.getInt(1);
                String name = resultSet.getString("name");
                String fio = resultSet.getString(3);

                String row = String.format("ID: %s, NAME: %s, FIO: %s", idGroup, name, fio);
                System.out.println(row);
            }
        }
    }
}