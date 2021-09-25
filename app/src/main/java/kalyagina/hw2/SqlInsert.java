package kalyagina.hw2;

import com.github.javafaker.Faker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlInsert {

    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/databaseName";
    private static final String USER = "root";
    private static final String PASSWORD = "12345678";

    private static final String INSERT_INTO_CURATOR =
            "INSERT INTO Curator(fio) VALUES(?)";

    private static final String INSERT_INTO_GROUP =
            "INSERT INTO Groupp(name, id_curator) VALUES(?, ?)";

    private static final String INSERT_INTO_STUDENT =
            "INSERT INTO Student(fio, sex, id_group) VALUES(?, ?, ?)";

    public static void main(String[] args) throws SQLException {
        SqlInsert sql = new SqlInsert();
        Faker faker = new Faker();
        String sex;
        int idGroup;
        int idCurator;

        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD)) {
            for (int i = 0; i < 3; i++) {
                String fioCurator = faker.witcher().monster();
                sql.insertDataIntoCuratorTable(connection, fioCurator);
            }

            for (int y = 0; y < 3; y++) {
                String name = faker.witcher().school();

                if (Math.random() < 0.1) {
                    idCurator = 1;
                } else if (Math.random() > 0.1) {
                    idCurator = 2;
                } else if (Math.random() == 0.1) {
                    idCurator = 3;
                } else {
                    idCurator = 4;
                }
                sql.insertDataIntoGrouppTable(connection, name, idCurator);
            }

            for (int x = 0; x < 15; x++) {
                String fioStudent = faker.witcher().character();

                if (Math.random() > 0.5) {
                    sex = "М";
                } else {
                    sex = "Ж";
                }

                if (Math.random() > 0.5) {
                    idGroup = 1;
                } else if (Math.random() < 0.5) {
                    idGroup = 2;
                } else {
                    idGroup = 3;
                }
                sql.insertDataIntoStudentTable(connection, fioStudent, sex, idGroup);

            }
        }
    }

    public void insertDataIntoCuratorTable(Connection connection, String fio) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_INTO_CURATOR)) {
            statement.setString(1, fio);
            int insertedRowsNumber = statement.executeUpdate();
            System.out.println("Inserted rows number: " + insertedRowsNumber);
        }
    }

    public void insertDataIntoGrouppTable(Connection connection, String name, int idCurator) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_INTO_GROUP)) {
            statement.setString(1, name);
            statement.setInt(2, idCurator);
            int insertedRowsNumber = statement.executeUpdate();
            System.out.println("Inserted rows number: " + insertedRowsNumber);
        }
    }

    public void insertDataIntoStudentTable(Connection connection, String fio, String sex, int idGroup) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_INTO_STUDENT)) {
            statement.setString(1, fio);
            statement.setString(2, sex);
            statement.setInt(3, idGroup);
            int insertedRowsNumber = statement.executeUpdate();
            System.out.println("Inserted rows number: " + insertedRowsNumber);
        }
    }
}