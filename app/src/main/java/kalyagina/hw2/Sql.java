package kalyagina.hw2;

import com.github.javafaker.Faker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Sql {

    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/databaseName";
    private static final String USER = "root";
    private static final String PASSWORD = "12345678";

    private static final String CREATE_CURATOR_TABLE_SQL =
            "CREATE TABLE Curator(id int auto_increment primary key, " +
                    "fio varchar(30))";

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


    private static final String INSERT_INTO_CURATOR =
            "INSERT INTO Curator(fio) VALUES(?)";


    private static final String INSERT_INTO_GROUP =
            "INSERT INTO Groupp(name, id_curator) VALUES(?, ?)";

    private static final String INSERT_INTO_STUDENT =
            "INSERT INTO Student(fio, sex, id_group) VALUES(?, ?, ?)";

    private static final String GET_ALL_STUDENTS_SQL =
            "SELECT s.id, s.fio, s.sex, s.id_group, g.name, c.fio " +
                    " FROM Student as s JOIN Groupp as g on s.id_group=g.id " +
                    " JOIN Curator as c ON c.id=g.id_curator";

    private static final String GET_COUNT_OF_STUDENTS_SQL =
            "SELECT COUNT(id) FROM Student";

    private static final String GET_WOMEN_STUDENTS_SQL =
            "SELECT id, fio, sex, id_group FROM Student where sex = ?";

    private static final String UPDATE_CURATOR_FOR_GROUP_SQL =
            "UPDATE Groupp SET id_curator = ? WHERE id = ?";

    private static final String GET_GROUP_WITH_CURATOR_SQL =
            "SELECT g.id, name, fio FROM Groupp as g JOIN Curator as c ON g.id_curator=c.id";

    private static final String GET_STUDENTS_FROM_SOME_GROUP =
            "SELECT * FROM Student WHERE id_group in" +
                    "(SELECT id FROM Groupp WHERE name=?)";

    public static void main(String[] args) throws SQLException {
        Sql sql = new Sql();
        Faker faker = new Faker();
        String sex;

        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD)) {
            sql.createCuratorTable(connection);
            sql.createGroupTable(connection);
            sql.createStudentTable(connection);

            for (int i = 0; i < 3; i++) {
                String fioCurator = faker.witcher().monster();
                sql.insertDataIntoCuratorTable(connection, fioCurator);
            }

            for (int y = 0; y < 4; y++) {
                String name = faker.witcher().school();
                int idCurator = (int) (Math.random() * 3 + 1);
                sql.insertDataIntoGroupTable(connection, name, idCurator);
            }
            for (int x = 0; x < 15; x++) {
                String fioStudent = faker.witcher().character();

                if (Math.random() > 0.5) {
                    sex = "М";
                } else {
                    sex = "Ж";
                }
                int idGroup = (int) (Math.random() * 4 + 1);

                sql.insertDataIntoStudentTable(connection, fioStudent, sex, idGroup);
            }
            sql.printAllStudents(connection);
            sql.printCountOfStudents(connection);
            sql.printWomenStudents(connection);
            sql.updateCuratorOfGroup(connection);
            sql.printGroupWithCurator(connection);
            sql.printStudentFromSomeGroup(connection);
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

    public void insertDataIntoCuratorTable(Connection connection, String fio) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_INTO_CURATOR)) {
            statement.setString(1, fio);
            int insertedRowsNumber = statement.executeUpdate();
            System.out.println("Inserted rows number: " + insertedRowsNumber);
        }
    }

    public void insertDataIntoGroupTable(Connection connection, String name, int idCurator) throws
            SQLException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_INTO_GROUP)) {
            statement.setString(1, name);
            statement.setInt(2, idCurator);
            int insertedRowsNumber = statement.executeUpdate();
            System.out.println("Inserted rows number: " + insertedRowsNumber);
        }
    }

    public void insertDataIntoStudentTable(Connection connection, String fio, String sex, int idGroup) throws
            SQLException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_INTO_STUDENT)) {
            statement.setString(1, fio);
            statement.setString(2, sex);
            statement.setInt(3, idGroup);
            int insertedRowsNumber = statement.executeUpdate();
            System.out.println("Inserted rows number: " + insertedRowsNumber);
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

    public void printCountOfStudents(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(GET_COUNT_OF_STUDENTS_SQL)) {
            while (resultSet.next()) {
                int count = resultSet.getInt(1);
                System.out.println(count);
            }
        }
    }

    public void printWomenStudents(Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(GET_WOMEN_STUDENTS_SQL)) {
            statement.setString(1, "Ж");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String fio = resultSet.getString(2);
                String sex = resultSet.getString(3);
                int idGroup = resultSet.getInt(4);

                String row = String.format("ID: %s, FIO: %s, SEX: %s, IDGROUP: %s", id, fio, sex, idGroup);
                System.out.println(row);
            }
        }
    }

    public void updateCuratorOfGroup(Connection connection) throws SQLException {

        try (PreparedStatement statement = connection.prepareStatement(UPDATE_CURATOR_FOR_GROUP_SQL)) {
            statement.setInt(1, 2);
            statement.setInt(2, 1);
            int updatedRowsNumber = statement.executeUpdate();
            System.out.println("Updated rows number: " + updatedRowsNumber);

            boolean autoCommit = connection.getAutoCommit();
            try {
                connection.setAutoCommit(false);
                statement.executeUpdate();
                statement.executeUpdate();
                connection.commit();
            } catch (SQLException exc) {
                exc.printStackTrace();
                connection.rollback();
            } finally {
                connection.setAutoCommit(autoCommit);
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

    public void printStudentFromSomeGroup(Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(GET_STUDENTS_FROM_SOME_GROUP)) {
            statement.setString(1, "Wolf");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String fio = resultSet.getString(2);
                    String sex = resultSet.getString(3);
                    int idGroup = resultSet.getInt(4);

                    String row = String.format("ID: %s, FIO: %s, SEX: %s, IDGROUP: %s", id, fio, sex, idGroup);
                    System.out.println(row);
                }
            }
        }
    }
}