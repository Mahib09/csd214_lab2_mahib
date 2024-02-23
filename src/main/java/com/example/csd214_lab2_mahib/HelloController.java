package com.example.csd214_lab2_mahib;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private TextField fieldId;
    @FXML
    private TextField fieldName;
    @FXML
    private TextField fieldAuthor;
    @FXML
    private TextField fieldPublisher;
    @FXML
    private TextField fieldPrice;
    @FXML
    private TextField fieldQuantity;
    @FXML
    private Label lblMessage;
    @FXML
    private TableView<BooksInventoryRecords> tblView;
    @FXML
    private TableColumn<BooksInventoryRecords,Integer > tblColId;
    @FXML
    private TableColumn<BooksInventoryRecords, String> tblColName;
    @FXML
    private TableColumn<BooksInventoryRecords,String> tblColAuthor;
    @FXML
    private TableColumn<BooksInventoryRecords,String> tblColPublisher;
    @FXML
    private TableColumn<BooksInventoryRecords,Float > tblColPrice;
    @FXML
    private TableColumn<BooksInventoryRecords,Integer > tblColQuantity;
    ObservableList<BooksInventoryRecords> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblColId.setCellValueFactory(new PropertyValueFactory<BooksInventoryRecords,Integer>("bookid"));
        tblColName.setCellValueFactory(new PropertyValueFactory<BooksInventoryRecords,String>("bookName"));
        tblColAuthor.setCellValueFactory(new PropertyValueFactory<BooksInventoryRecords,String>("author"));
        tblColPublisher.setCellValueFactory(new PropertyValueFactory<BooksInventoryRecords,String>("publisher"));
        tblColPrice.setCellValueFactory(new PropertyValueFactory<BooksInventoryRecords,Float>("price"));
        tblColQuantity.setCellValueFactory(new PropertyValueFactory<BooksInventoryRecords,Integer>("quantity"));
        tblView.setItems(list);
    }
   String jdbcUrl = "jdbc:mysql://localhost:3306/csd214_mahib_lab2";
    String dbUser = "root";
    String dbPassword = "";

    @FXML protected void onReadButtonClicked() {
        populateTable();
    }
    @FXML protected void onInsertButtonClicked(){ insertData(); }
    @FXML protected void onUpdateButtonClicked() { updateData(); }
    @FXML protected void onDeleteButtonClicked(){ deleteData();}
    @FXML protected void onLoadButtonClicked(){loadData();}
    public void populateTable() {
        list.clear();
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String query = "SELECT * FROM books";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int tblId = resultSet.getInt("bookid");
                String tblName = resultSet.getString("bookName");
                String tblAuthor = resultSet.getString("author");
                String tblPublisher = resultSet.getString("publisher");
                Float tblPrice = resultSet.getFloat("price");
                int tblQuantity = resultSet.getInt("quantity");
                tblView.getItems().add(new BooksInventoryRecords(tblId,tblName,tblAuthor,tblPublisher,tblPrice,tblQuantity));
            }     } catch (SQLException e) {
            e.printStackTrace();     }
    }
    public void insertData(){
        if(fieldName.getText().isEmpty()){
            lblMessage.setText("Name Required to Insert");
        }else{
            try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
                String query = "INSERT INTO books ( bookName, author, publisher, price, quantity) VALUES ('"+fieldName.getText()+"','"+fieldAuthor.getText()+"','"+fieldPublisher.getText()+"','"+fieldPrice.getText()+"','"+fieldQuantity.getText()+"')";
                Statement statement = connection.createStatement();
                statement.executeUpdate(query);
                populateTable();
                clearForm();
            } catch (SQLException e) {
                e.printStackTrace();     }
        }

    }
    public void updateData(){
        if(fieldId.getText().isEmpty()){
            lblMessage.setText("Please Enter Id to Update");
        }else{
            try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
                String query = ("UPDATE books SET bookName='" + fieldName.getText() + "',author='" + fieldAuthor.getText() + "',publisher='" + fieldPublisher.getText() + "',price='" + fieldPrice.getText() + "',quantity='" + fieldQuantity.getText() + "'WHERE bookid='" + fieldId.getText() + "'");
                Statement statement = connection.createStatement();
                statement.executeUpdate(query);
                populateTable();
                clearForm();
            } catch (SQLException e) {
                e.printStackTrace();     }
        }

    }
    public void deleteData(){
        if(fieldId.getText().isEmpty()){
            lblMessage.setText("Please Enter Id to Delete");
        }else{ try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String query = ("DELETE FROM books WHERE bookid='"+fieldId.getText()+"'");
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            populateTable();
            clearForm();
        } catch (SQLException e) {
            e.printStackTrace();     }}

    }
    public void loadData(){
        if(fieldId.getText().isEmpty()){
            lblMessage.setText("Please Enter Id to Load");
        }else{try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String query = ("SELECT * FROM books WHERE bookid='"+fieldId.getText()+"'");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            fieldName.setText(resultSet.getString("bookName"));
            fieldAuthor.setText(resultSet.getString("author"));
            fieldPublisher.setText(resultSet.getString("publisher"));
            fieldPrice.setText(resultSet.getString("price"));
            fieldQuantity.setText(resultSet.getString("quantity"));
        } catch (SQLException e) {
            e.printStackTrace();     }}

    }
    public void clearForm(){
        fieldId.setText("");
        fieldName.setText("");
        fieldAuthor.setText("");
        fieldPublisher.setText("");
        fieldPrice.setText("");
        fieldQuantity.setText("");
        lblMessage.setText("");
    }
}

