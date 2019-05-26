package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable{

    public TableView MainTable;

    @FXML
    private TableColumn<Cars, Integer> IdCol;
    @FXML
    private TableColumn<Cars, Integer> YearCol;
    @FXML
    private TableColumn<Cars, String> ModelCol;
    @FXML
    private TableColumn<Cars, String> ProducerCol;
    @FXML
    private TableColumn<Cars, String> ClassCol;
    @FXML
    private TableColumn<Cars, String> TypeCol;

    @FXML
    private TextField IdTextField;
    @FXML
    private TextField YearTextField;
    @FXML
    private TextField ModelTextField;
    @FXML
    private TextField ProducerTextField;
    @FXML
    private TextField ClassTextField;
    @FXML
    private TextField TypeTextField;

    @FXML
    void test_db() {
        try {
            Bd_connection bd = Bd_connection.getInstance();
            ObservableList<Cars> t_car = bd.getAllProducts();

            MainTable.setItems(t_car);

//            OutputTA.clear();

//            for (Cars car: t_car){
//                System.out.println(car.toString());
////                OutputTA.appendText(car.toString() +"\n");
//
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addRow() {
        try {

            int year = Integer.parseInt(YearTextField.getText());
            String model = ModelTextField.getText();
            String producer = ProducerTextField.getText();
            String _class = ClassTextField.getText();
            String type = TypeTextField.getText();

            Cars test_car = new Cars(0, year, model, producer, _class, type);
            Bd_connection bd = Bd_connection.getInstance();

            bd.addNewRow(test_car);

            IdTextField.clear();
            test_db();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteRow() {
        try{

            Bd_connection bd = Bd_connection.getInstance();

            int Id = Integer.parseInt(IdTextField.getText());
            bd.deleteCar(Id);

            IdTextField.clear();
            test_db();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void updateRow() {
        try {

            int year = Integer.parseInt(YearTextField.getText());
            String model = ModelTextField.getText();
            String producer = ProducerTextField.getText();
            String _class = ClassTextField.getText();
            String type = TypeTextField.getText();

            Cars test_car = new Cars(0, year, model, producer, _class, type);
            int Id = Integer.parseInt(IdTextField.getText());

            Bd_connection bd = Bd_connection.getInstance();

            bd.updateCar(Id, test_car);

            IdTextField.clear();
            test_db();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        IdCol.setCellValueFactory(new PropertyValueFactory<Cars, Integer>("Id"));
        YearCol.setCellValueFactory(new PropertyValueFactory<Cars, Integer>("year"));

        ModelCol.setCellValueFactory(new PropertyValueFactory<Cars, String>("model"));
        ProducerCol.setCellValueFactory(new PropertyValueFactory<Cars, String>("producer"));
        ClassCol.setCellValueFactory(new PropertyValueFactory<Cars, String>("_class"));
        TypeCol.setCellValueFactory(new PropertyValueFactory<Cars, String>("type_c"));

        test_db();
    }
}
