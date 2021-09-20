//package kalyagina.hw2;
//
//import com.github.javafaker.Faker;
//import com.github.javafaker.Name;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.Locale;
//
//public class Sql {
//
//    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/databaseName";
//    private static final String USER = "root";
//    private static final String PASSWORD = "12345678";
//
////    private final String CREATE_CURATOR_TABLE_SQL =
////    "CREATE TABLE Curator(id int auto_increment primary key, " +
////            "fio varchar(30))";
//
////    private static final String CREATE_GROUPP_TABLE_SQL =
////            "CREATE TABLE Groupp(id int auto_increment primary key, " +
////                    "name varchar(50), " +
////                    "id_curator int, " +
////                    "FOREIGN KEY(id_curator) REFERENCES Curator(id))";
////
////    private static final String CREATE_STUDENT_TABLE_SQL =
////            "CREATE TABLE Student(id int auto_increment primary key, " +
////                    "fio varchar(100), " +
////                    "sex char(1), " +
////                    "id_groupp int, " +
////                    "FOREIGN KEY(id_groupp) REFERENCES Groupp(id))";
////
////    private static final String INSERT_INTO_CURATOR =
////            "INSERT INTO Curator(fio) VALUES(?)";
////
////      private static final String INSERT_INTO_GROUPP =
////            "INSERT INTO Groupp(name) VALUES(?)";
//
//    private static final String INSERT_INTO_STUDENT =
//            "INSERT INTO Student(fio, sex) VALUES(?), (?), (?)";
//
////    public void createCuratorTable(Connection connection) throws SQLException {
////        try (Statement statement = connection.createStatement()) {
////            statement.execute(CREATE_CURATOR_TABLE_SQL);
////        }
////    }
////
////    public  void createGrouppTable(Connection connection) throws SQLException{
////        try (Statement statement = connection.createStatement()) {
////            statement.execute(CREATE_GROUPP_TABLE_SQL);
////        }
////
////    }
////
////    public  void createStudentTable(Connection connection) throws SQLException{
////        try (Statement statement = connection.createStatement()) {
////            statement.execute(CREATE_STUDENT_TABLE_SQL);
////        }
////
////    }
////
////    public void insertDataIntoCuratorTable(Connection connection,  String fio) throws SQLException {
////        try (PreparedStatement statement = connection.prepareStatement(INSERT_INTO_CURATOR)) {
////            statement.setString(1, fio);
////            int insertedRowsNumber = statement.executeUpdate();
////            System.out.println("Inserted rows number: " + insertedRowsNumber);
////        }
////    }
//
////    public void insertDataIntoGrouppTable(Connection connection,  String name) throws SQLException {
////        try (PreparedStatement statement = connection.prepareStatement(INSERT_INTO_GROUPP)) {
////            statement.setString(1, name);
////            int insertedRowsNumber = statement.executeUpdate();
////            System.out.println("Inserted rows number: " + insertedRowsNumber);
////        }
////    }
//    public void insertDataIntoStudentTable(Connection connection, String fio, String sex, int idGroupp) throws SQLException {
//        try (PreparedStatement statement = connection.prepareStatement(INSERT_INTO_STUDENT)) {
//            statement.setString(1, fio);
//            statement.setString(2, sex);
//            statement.setInt(2, idGroupp);
//            int insertedRowsNumber = statement.executeUpdate();
//            System.out.println("Inserted rows number: " + insertedRowsNumber);
//        }
//    }
//    public static void main(String[] args) throws SQLException {
//        Sql sql = new Sql();
//        Faker faker = new Faker(new Locale("ru-RU"));
//        String sex;
//        if(Math.random() > 0.5)
//        {sex  = "М";}
//        else {sex = "Ж";}
//
//        try (Connection connection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD)) {
////          sql.createCuratorTable(connection);
////          sql.createGrouppTable(connection);
////          sql.createStudentTable(connection);
////            for (int i = 0; i < 4; i++) {
////                String fioCurator=faker.witcher().character();
////                sql.insertDataIntoCuratorTable(connection, fioCurator);
////            for (int y = 0; y < 3; y++) {
////                String name=faker.witcher().school();
////                sql.insertDataIntoGrouppTable(connection, name);
////            }
//                for (int x = 0; x < 15; x++) {
//                    String fioStudent=faker.witcher().school();
//                    int id=faker.idNumber().valid();
//                    sql.insertDataIntoStudentTable(connection, fioStudent, sex, id);
//
//        }
//    }
//}}