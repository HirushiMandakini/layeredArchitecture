//package com.example.layeredarchitecture.dao.impl;
//
//import com.example.layeredarchitecture.dao.custom.ItemDAO;
//import com.example.layeredarchitecture.dao.SQLUtil;
//import com.example.layeredarchitecture.db.DBConnection;
//import com.example.layeredarchitecture.model.ItemDTO;
//
//import java.sql.*;
//import java.util.ArrayList;
//
//public class ItemDAOImpl implements ItemDAO {
//    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getDbConnection().getConnection();
//        Statement stm = connection.createStatement();
//        ResultSet rst = stm.executeQuery("SELECT * FROM Item");
//
//        ArrayList<ItemDTO> getItem = new ArrayList<>();
//        while (rst.next()) {
//
//            getItem.add(
//                    new ItemDTO(
//                            rst.getString("code"),
//                            rst.getString("description"),
//                            rst.getBigDecimal("unitPrice"),
//                            rst.getInt("qtyOnHand")
//                    )
//            );
//        }
//        return getItem;
//    }
//
// //   @Override
// public boolean saveItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
//
////     Connection connection = DBConnection.getDbConnection().getConnection();
////        PreparedStatement pstm = connection.prepareStatement("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)");
////        pstm.setString(1, itemDTO.getCode());
////        pstm.setString(2, itemDTO.getDescription());
////        pstm.setBigDecimal(3, itemDTO.getUnitPrice());
////        pstm.setInt(4, itemDTO.getQtyOnHand());
////     pstm.executeUpdate();
//     return SQLUtil.execute("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)");
//
//    }
//
//    public boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
////        Connection connection = DBConnection.getDbConnection().getConnection();
////        PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?");
////        pstm.setString(1, itemDTO.getDescription());
////        pstm.setBigDecimal(2, itemDTO.getUnitPrice());
////        pstm.setInt(3, itemDTO.getQtyOnHand());
////        pstm.setString(4, itemDTO.getCode());
////        pstm.executeUpdate();
//        return SQLUtil.execute("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?");
//
//    }
//
//    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
//
////        Connection connection = DBConnection.getDbConnection().getConnection();
////        PreparedStatement pstm = connection.prepareStatement("DELETE FROM Item WHERE code=?");
////        pstm.setString(1, code);
////        pstm.executeUpdate();
//          return SQLUtil.execute("DELETE FROM Item WHERE code=?");
//
//    }
//
//    @Override
//    public boolean existItem(String code) throws SQLException, ClassNotFoundException {
//        return SQLUtil.execute("SELECT code FROM customer WHERE id=?");
//
//    }
//
//    @Override
//    public String generateNewId() throws SQLException, ClassNotFoundException {
//        return null;
//    }
//    @Override
//    public ItemDTO findItems(String newItemCode) throws SQLException, ClassNotFoundException {
////        Connection connection = DBConnection.getDbConnection().getConnection();
////        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Item WHERE code=?");
////        pstm.setString(1, newItemCode + "");
////        ResultSet rst = pstm.executeQuery();
////        rst.next();
////        ItemDTO item = new ItemDTO(newItemCode + "", rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));
////        return item;
//    }
//
////    public boolean exitsItem(String code) throws SQLException, ClassNotFoundException {
////        Connection connection = DBConnection.getDbConnection().getConnection();
////        PreparedStatement pstm = connection.prepareStatement("SELECT id FROM Customer WHERE id=?");
////        pstm.setString(1, code);
////        return pstm.executeQuery().next();
////    }
//}
package com.example.layeredarchitecture.dao.impl;

import com.example.layeredarchitecture.dao.SQLUtil;
import com.example.layeredarchitecture.dao.custom.ItemDAO;
import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.*;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public ArrayList<ItemDTO> getAll() throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();*/

        ResultSet rst = SQLUtil.execute("SELECT * FROM Item");

        ArrayList<ItemDTO> getallItem = new ArrayList<>();

        while (rst.next()) {
            ItemDTO itemDTO = new ItemDTO(rst.getString("code"), rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));
            getallItem.add(itemDTO);
        }

        return getallItem;
    }
    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {

        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM Item WHERE code=?");
        pstm.setString(1, code);
        return pstm.executeUpdate() > 0;*/

        return SQLUtil.execute("DELETE FROM Item WHERE code=?",code);
    }
    @Override
    public boolean save(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)");
        pstm.setString(1, itemDTO.getCode());
        pstm.setString(2, itemDTO.getDescription());
        pstm.setBigDecimal(3, itemDTO.getUnitPrice());
        pstm.setInt(4, itemDTO.getQtyOnHand());
        return pstm.executeUpdate() > 0;*/

        return SQLUtil.execute("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)",
                itemDTO.getCode(),itemDTO.getDescription(),itemDTO.getUnitPrice(),itemDTO.getQtyOnHand());
    }
    @Override
    public boolean update(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?");
        pstm.setString(1, itemDTO.getDescription());
        pstm.setBigDecimal(2, itemDTO.getUnitPrice());
        pstm.setInt(3, itemDTO.getQtyOnHand());
        pstm.setString(4, itemDTO.getCode());
        return pstm.executeUpdate() > 0;*/

        return SQLUtil.execute("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?",
                itemDTO.getDescription(),itemDTO.getUnitPrice(),itemDTO.getQtyOnHand(),itemDTO.getCode());
    }
    @Override
    public boolean exist(String code) throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT code FROM Item WHERE code=?");
        pstm.setString(1, code);
        return pstm.executeQuery().next();*/

        ResultSet set = SQLUtil.execute("SELECT code FROM Item WHERE code=?",code);
        return set.next();

    }
    @Override
    public String nextId() throws SQLException, ClassNotFoundException {
        //  Connection connection = DBConnection.getDbConnection().getConnection();
        ResultSet rst = SQLUtil.execute("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("code");
            int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newItemId);
        } else {
            return "I00-001";
        }
    }
    @Override
    public ItemDTO search(String newItemCode) throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Item WHERE code=?");
        pstm.setString(1, newItemCode + "");
        ResultSet rst = pstm.executeQuery();*/

        ResultSet rst = SQLUtil.execute("SELECT * FROM Item WHERE code=?",newItemCode + "");
        rst.next();
        ItemDTO item = new ItemDTO(newItemCode + "", rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));

        return item;
    }




}