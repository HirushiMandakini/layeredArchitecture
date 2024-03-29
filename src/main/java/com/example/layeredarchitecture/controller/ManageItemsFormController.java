//package com.example.layeredarchitecture.controller;
//
//import com.example.layeredarchitecture.dao.custom.ItemDAO;
//import com.example.layeredarchitecture.dao.impl.ItemDAOImpl;
//import com.example.layeredarchitecture.db.DBConnection;
//import com.example.layeredarchitecture.model.ItemDTO;
//import com.example.layeredarchitecture.view.tdm.ItemTM;
//import com.jfoenix.controls.JFXButton;
//import javafx.application.Platform;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextField;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.AnchorPane;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.net.URL;
//import java.sql.*;
//import java.util.ArrayList;
//
//
//public class ManageItemsFormController {
//    public AnchorPane root;
//    public TextField txtCode;
//    public TextField txtDescription;
//    public TextField txtQtyOnHand;
//    public JFXButton btnDelete;
//    public JFXButton btnSave;
//    public TableView<ItemTM> tblItems;
//    public TextField txtUnitPrice;
//    public JFXButton btnAddNewItem;
//
//    ItemDAO itemDAO = new ItemDAOImpl();
//
//    public void initialize() {
//        tblItems.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
//        tblItems.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
//        tblItems.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
//        tblItems.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
//
//        initUI();
//
//        tblItems.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//            btnDelete.setDisable(newValue == null);
//            btnSave.setText(newValue != null ? "Update" : "Save");
//            btnSave.setDisable(newValue == null);
//
//            if (newValue != null) {
//                txtCode.setText(newValue.getCode());
//                txtDescription.setText(newValue.getDescription());
//                txtUnitPrice.setText(newValue.getUnitPrice().setScale(2).toString());
//                txtQtyOnHand.setText(newValue.getQtyOnHand() + "");
//
//                txtCode.setDisable(false);
//                txtDescription.setDisable(false);
//                txtUnitPrice.setDisable(false);
//                txtQtyOnHand.setDisable(false);
//            }
//        });
//
//        txtQtyOnHand.setOnAction(event -> btnSave.fire());
//        loadAllItems();
//    }
//    private void loadAllItems() {
//        tblItems.getItems().clear();
//        /*Get all customers*/
//        try {
//            // CustomerDAOImpl customerDAO = new CustomerDAOImpl();
//            //
//            /*interface*/
//            ItemDAO itemDAO = new ItemDAOImpl();
//            ArrayList<ItemDTO> allItem=itemDAO.getAllItem();
//            for (ItemDTO itemDTO:allItem) {
//                tblItems.getItems().add(new ItemTM(itemDTO.getCode(), itemDTO.getDescription(), itemDTO.getUnitPrice(), itemDTO.getQtyOnHand()));
//            }
//
//        } catch (SQLException e) {
//            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
//        } catch (ClassNotFoundException e) {
//            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
//        }
//    }
//    private void initUI() {
//        txtCode.clear();
//        txtDescription.clear();
//        txtUnitPrice.clear();
//        txtQtyOnHand.clear();
//        txtCode.setDisable(true);
//        txtDescription.setDisable(true);
//        txtUnitPrice.setDisable(true);
//        txtQtyOnHand.setDisable(true);
//        txtCode.setEditable(false);
//        btnSave.setDisable(true);
//        btnDelete.setDisable(true);
//    }
//
//    @FXML
//    private void navigateToHome(MouseEvent event) throws IOException {
//        URL resource = this.getClass().getResource("/com/example/layeredarchitecture/main-form.fxml");
//        Parent root = FXMLLoader.load(resource);
//        Scene scene = new Scene(root);
//        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
//        primaryStage.setScene(scene);
//        primaryStage.centerOnScreen();
//        Platform.runLater(() -> primaryStage.sizeToScene());
//    }
//
//    public void btnAddNew_OnAction(ActionEvent actionEvent) {
//        txtCode.setDisable(false);
//        txtDescription.setDisable(false);
//        txtUnitPrice.setDisable(false);
//        txtQtyOnHand.setDisable(false);
//        txtCode.clear();
//        txtCode.setText(generateNewId());
//        txtDescription.clear();
//        txtUnitPrice.clear();
//        txtQtyOnHand.clear();
//        txtDescription.requestFocus();
//        btnSave.setDisable(false);
//        btnSave.setText("Save");
//        tblItems.getSelectionModel().clearSelection();
//    }
//
//    public void btnDelete_OnAction(ActionEvent actionEvent) {
//        /*Delete Customer*/
//        String code = tblItems.getSelectionModel().getSelectedItem().getCode();
//        try {
//            if (!existItem(code)) {
//                new Alert(Alert.AlertType.ERROR, "There is no such customer associated with the code " + code).show();
//            }
//            //            Connection connection = DBConnection.getDbConnection().getConnection();
////            PreparedStatement pstm = connection.prepareStatement("DELETE FROM Customer WHERE id=?");
////            pstm.setString(1, id);
////            pstm.executeUpdate();
//
//            // CustomerDAO customerDAO=new CustomerDAOImpl();
//            itemDAO.deleteItem(code);
//
//            tblItems.getItems().remove(tblItems.getSelectionModel().getSelectedItem());
//            tblItems.getSelectionModel().clearSelection();
//            initUI();
//
//        } catch (SQLException e) {
//            new Alert(Alert.AlertType.ERROR, "Failed to delete the customer " + code).show();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public void btnSave_OnAction(ActionEvent actionEvent) {
//        String code = txtCode.getText();
//        String description = txtDescription.getText();
//
//        if (!description.matches("[A-Za-z0-9 ]+")) {
//            new Alert(Alert.AlertType.ERROR, "Invalid description").show();
//            txtDescription.requestFocus();
//            return;
//        } else if (!txtUnitPrice.getText().matches("^[0-9]+[.]?[0-9]*$")) {
//            new Alert(Alert.AlertType.ERROR, "Invalid unit price").show();
//            txtUnitPrice.requestFocus();
//            return;
//        } else if (!txtQtyOnHand.getText().matches("^\\d+$")) {
//            new Alert(Alert.AlertType.ERROR, "Invalid qty on hand").show();
//            txtQtyOnHand.requestFocus();
//            return;
//        }
//
//        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());
//        BigDecimal unitPrice = new BigDecimal(txtUnitPrice.getText()).setScale(2);
//
//
//        if (btnSave.getText().equalsIgnoreCase("save")) {
//            try {
//                if (existItem(code)) {
//                    new Alert(Alert.AlertType.ERROR, code + " already exists").show();
//                }
//                ////////////////////Save Item////////////////////////////////////////////////////
//
//                ItemDTO itemDTO = new ItemDTO(code, description, unitPrice, qtyOnHand);
//                itemDAO.saveItem(itemDTO);
//                tblItems.getItems().add(new ItemTM(code, description, unitPrice, qtyOnHand));
//
//            } catch (SQLException e) {
//                new Alert(Alert.AlertType.ERROR, "Failed to save the customer " + e.getMessage()).show();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//
//        } else {
//            try {
//
//                if (!existItem(code)) {
//                    new Alert(Alert.AlertType.ERROR, "There is no such item associated with the id " + code).show();
//                }
//                /*Update Item*/
//                ItemDTO itemDTO = new ItemDTO(code, description, unitPrice, qtyOnHand);
//                itemDAO.updateItem(itemDTO);
//
//            } catch (SQLException e) {
//                new Alert(Alert.AlertType.ERROR, "Failed to update the customer " + code + e.getMessage()).show();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//            ItemTM selectedItem = tblItems.getSelectionModel().getSelectedItem();
//            selectedItem.setCode(code);
//            selectedItem.setDescription(description);
//            selectedItem.setQtyOnHand(qtyOnHand);
//            selectedItem.setUnitPrice(unitPrice);
//            tblItems.refresh();
//        }
//        btnAddNewItem.fire();
//    }
//
////    private boolean existItem(String code) throws SQLException, ClassNotFoundException {
////        boolean isExists = itemDAO.existItem(code);
////        return isExists;
////    }
//boolean existItem(String code) throws SQLException, ClassNotFoundException {
//
//    return itemDAO.existItem(code);
//
//}
//  private String generateNewId() {
//      try {
//          Connection connection = DBConnection.getDbConnection().getConnection();
//          ResultSet rst = connection.createStatement().executeQuery("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
//          if (rst.next()) {
//              String id = rst.getString("code");
//              int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
//              return String.format("I00-%03d", newItemId);
//          } else {
//              return "I00-001";
//          }
//      } catch (SQLException e) {
//          new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
//      } catch (ClassNotFoundException e) {
//          e.printStackTrace();
//      }
//      return "I00-001";
//  }
//}
package com.example.layeredarchitecture.controller;


import com.example.layeredarchitecture.dao.custom.ItemDAO;
import com.example.layeredarchitecture.dao.impl.ItemDAOImpl;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.view.tdm.ItemTM;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;


public class ManageItemsFormController {
    public AnchorPane root;
    public TextField txtCode;
    public TextField txtDescription;
    public TextField txtQtyOnHand;
    public JFXButton btnDelete;
    public JFXButton btnSave;
    public TableView<ItemTM> tblItems;
    public TextField txtUnitPrice;
    public JFXButton btnAddNewItem;
    ItemDAO itemDAO = new ItemDAOImpl();

    public void initialize() {
        tblItems.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
        tblItems.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblItems.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        tblItems.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

        initUI();

        tblItems.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);
            btnSave.setText(newValue != null ? "Update" : "Save");
            btnSave.setDisable(newValue == null);

            if (newValue != null) {
                txtCode.setText(newValue.getCode());
                txtDescription.setText(newValue.getDescription());
                txtUnitPrice.setText(newValue.getUnitPrice().setScale(2).toString());
                txtQtyOnHand.setText(newValue.getQtyOnHand() + "");

                txtCode.setDisable(false);
                txtDescription.setDisable(false);
                txtUnitPrice.setDisable(false);
                txtQtyOnHand.setDisable(false);
            }
        });

        txtQtyOnHand.setOnAction(event -> btnSave.fire());
        loadAllItems();
    }

    private void loadAllItems() {
        tblItems.getItems().clear();
        try {


            ArrayList<ItemDTO> allItem = itemDAO.getAll();

            for (ItemDTO i : allItem) {
                tblItems.getItems().add(new ItemTM(i.getCode(), i.getDescription(), i.getUnitPrice(), i.getQtyOnHand()));
            }

            /*Get all items*/
            /*Connection connection = DBConnection.getDbConnection().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM Item");*/
            /*while (rst.next()) {
                tblItems.getItems().add(new ItemTM(rst.getString("code"), rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand")));
            }*/


        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initUI() {
        txtCode.clear();
        txtDescription.clear();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();
        txtCode.setDisable(true);
        txtDescription.setDisable(true);
        txtUnitPrice.setDisable(true);
        txtQtyOnHand.setDisable(true);
        txtCode.setEditable(false);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
    }

    @FXML
    private void navigateToHome(MouseEvent event) throws IOException {
        URL resource = this.getClass().getResource("/com/example/layeredarchitecture/main-form.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        Platform.runLater(() -> primaryStage.sizeToScene());
    }

    public void btnAddNew_OnAction(ActionEvent actionEvent) {
        txtCode.setDisable(false);
        txtDescription.setDisable(false);
        txtUnitPrice.setDisable(false);
        txtQtyOnHand.setDisable(false);
        txtCode.clear();
        txtCode.setText(generateNewId());
        txtDescription.clear();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();
        txtDescription.requestFocus();
        btnSave.setDisable(false);
        btnSave.setText("Save");
        tblItems.getSelectionModel().clearSelection();
    }

    public void btnDelete_OnAction(ActionEvent actionEvent) {
        /*Delete Item*/
        String code = tblItems.getSelectionModel().getSelectedItem().getCode();
        try {

            if (!itemDAO.exist(code)) {
                new Alert(Alert.AlertType.ERROR, "There is no such item associated with the id " + code).show();
            }
            /*Connection connection = DBConnection.getDbConnection().getConnection();
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM Item WHERE code=?");
            pstm.setString(1, code);
            pstm.executeUpdate();*/


            boolean isDelete = itemDAO.delete(code);

            if (isDelete){
                tblItems.getItems().remove(tblItems.getSelectionModel().getSelectedItem());
                tblItems.getSelectionModel().clearSelection();
            }


            initUI();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the item " + code).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnSave_OnAction(ActionEvent actionEvent) {
        String code = txtCode.getText();
        String description = txtDescription.getText();

        if (!description.matches("[A-Za-z0-9 ]+")) {
            new Alert(Alert.AlertType.ERROR, "Invalid description").show();
            txtDescription.requestFocus();
            return;
        } else if (!txtUnitPrice.getText().matches("^[0-9]+[.]?[0-9]*$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid unit price").show();
            txtUnitPrice.requestFocus();
            return;
        } else if (!txtQtyOnHand.getText().matches("^\\d+$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid qty on hand").show();
            txtQtyOnHand.requestFocus();
            return;
        }

        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());
        BigDecimal unitPrice = new BigDecimal(txtUnitPrice.getText()).setScale(2);


        if (btnSave.getText().equalsIgnoreCase("save")) {
            try {

                if (itemDAO.exist(code)) {
                    new Alert(Alert.AlertType.ERROR, code + " already exists").show();
                }
                //Save Item
                /*Connection connection = DBConnection.getDbConnection().getConnection();
                PreparedStatement pstm = connection.prepareStatement("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)");
                pstm.setString(1, code);
                pstm.setString(2, description);
                pstm.setBigDecimal(3, unitPrice);
                pstm.setInt(4, qtyOnHand);
                pstm.executeUpdate();*/


                boolean isSaved = itemDAO.save(new ItemDTO(code, description, unitPrice, qtyOnHand));

                if (isSaved){
                    tblItems.getItems().add(new ItemTM(code, description, unitPrice, qtyOnHand));
                }


            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {

                if (!itemDAO.exist(code)) {
                    new Alert(Alert.AlertType.ERROR, "There is no such item associated with the id " + code).show();
                }
                /*Update Item*/
                /*Connection connection = DBConnection.getDbConnection().getConnection();
                PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?");
                pstm.setString(1, description);
                pstm.setBigDecimal(2, unitPrice);
                pstm.setInt(3, qtyOnHand);
                pstm.setString(4, code);
                pstm.executeUpdate();*/

                boolean isUpdated = itemDAO.update(new ItemDTO(code, description, unitPrice, qtyOnHand));

                if (isUpdated){
                    new Alert(Alert.AlertType.INFORMATION, "Item updated successfully").show();
                    ItemTM selectedItem = tblItems.getSelectionModel().getSelectedItem();
                    selectedItem.setDescription(description);
                    selectedItem.setQtyOnHand(qtyOnHand);
                    selectedItem.setUnitPrice(unitPrice);
                }

                /*ItemTM selectedItem = tblItems.getSelectionModel().getSelectedItem();
                selectedItem.setDescription(description);
                selectedItem.setQtyOnHand(qtyOnHand);
                selectedItem.setUnitPrice(unitPrice);*/

                tblItems.refresh();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        btnAddNewItem.fire();
    }


    /*private boolean existItem(String code) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT code FROM Item WHERE code=?");
        pstm.setString(1, code);
        return pstm.executeQuery().next();
    }*/


    private String generateNewId() {
        try {
            return itemDAO.nextId();

            /*Connection connection = DBConnection.getDbConnection().getConnection();
            ResultSet rst = connection.createStatement().executeQuery("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
            if (rst.next()) {
                String id = rst.getString("code");
                int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
                return String.format("I00-%03d", newItemId);
            } else {
                return "I00-001";
            }*/

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "I00-001";
    }
}