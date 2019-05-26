package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.sqlite.JDBC;
import java.sql.*;

public class Bd_connection {

    private static final String CON_STR = "jdbc:sqlite:src/bd/test_db.db3";

    private static Bd_connection instance = null;

    public static synchronized Bd_connection getInstance() throws SQLException {
        if (instance == null)
            instance = new Bd_connection();
        return instance;
    }

    private Connection connection;

    private Bd_connection() throws SQLException {
        DriverManager.registerDriver(new JDBC());
        this.connection = DriverManager.getConnection(CON_STR);
    }

    public ObservableList<Cars> getAllProducts() {

        try (Statement statement = this.connection.createStatement()) {

            ObservableList<Cars> cars = FXCollections.observableArrayList();

            ResultSet resultSet = statement.executeQuery("SELECT Id, year, model, producer, class, type_c FROM Cars");

            while (resultSet.next()) {
                cars.add(new Cars(
                        resultSet.getInt("Id"),
                        resultSet.getInt("year"),
                        resultSet.getString("model"),
                        resultSet.getString("producer"),
                        resultSet.getString("class"),
                        resultSet.getString("type_c")
                ));
            }

            return cars;

        } catch (SQLException e) {
            e.printStackTrace();

            return FXCollections.emptyObservableList();
        }
    }

    public void addNewRow(Cars cars){
        try(
                PreparedStatement preparedStatement = this.connection.prepareStatement(
                        "INSERT INTO Cars('year', 'model', 'producer', 'class', 'type_c') VALUES(?, ?, ?, ?, ?)")
        ){

           preparedStatement.setObject(1, cars.year);
           preparedStatement.setObject(2, cars.model);
           preparedStatement.setObject(3, cars.producer);
           preparedStatement.setObject(4, cars._class);
           preparedStatement.setObject(5, cars.type_c);

           preparedStatement.execute();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteCar(int Id){
        try(PreparedStatement ps = this.connection.prepareStatement(
                "DELETE FROM Cars WHERE Id = ?"
        )){
            ps.setObject(1, Id);
            ps.execute();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateCar(int id, Cars cars){
        try(PreparedStatement ps = this.connection.prepareStatement(
                "UPDATE Cars " +
                    "SET " +
                        "year = ?, " +
                        "model = ?, " +
                        "producer = ?, " +
                        "class = ?, " +
                        "type_c = ? " +

                    "WHERE Id = ?"
        )){
            ps.setObject(1, cars.year);
            ps.setObject(2, cars.model);
            ps.setObject(3, cars.producer);
            ps.setObject(4, cars._class);
            ps.setObject(5, cars.type_c);
            ps.setObject(6, id);

            ps.execute();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
