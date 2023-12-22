//package com.example.layeredarchitecture.dao.impl;
//
//import com.example.layeredarchitecture.dao.custom.CustomerDAO;
//import com.example.layeredarchitecture.dao.SQLUtil;
//import com.example.layeredarchitecture.db.DBConnection;
//import com.example.layeredarchitecture.model.CustomerDTO;
//
//import java.sql.*;
//import java.util.ArrayList;
//
//public class CustomerDAOImpl implements CustomerDAO {
//    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getDbConnection().getConnection();
//        Statement stm = connection.createStatement();
//        ResultSet rst = stm.executeQuery("SELECT * FROM customer");
//
//        ArrayList<CustomerDTO> getCus = new ArrayList<>();
//        while (rst.next()) {
//
//            getCus.add(
//                    new CustomerDTO(
//                            rst.getString("id"),
//                            rst.getString("name"),
//                            rst.getString("address")
//                    )
//            );
//        }
//return getCus;
//    }
//    public boolean save(CustomerDTO dto) throws SQLException, ClassNotFoundException {
////        Connection connection = DBConnection.getDbConnection().getConnection();
////        PreparedStatement pstm = connection.prepareStatement("INSERT INTO customer (id,name, address) VALUES (?,?,?)");
////        pstm.setString(1, dto.getId());
////        pstm.setString(2, dto.getName());
////        pstm.setString(3, dto.getAddress());
////        return pstm.executeUpdate()> 0;
//        return SQLUtil.execute("INSERT INTO customer (id,name, address) VALUES (?,?,?)",dto.getId(),dto.getName(),dto.getAddress());
//    }
//
//    public boolean update(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
////        Connection connection = DBConnection.getDbConnection().getConnection();
////        PreparedStatement pstm = connection.prepareStatement("UPDATE customer SET name=?, address=? WHERE id=?");
////        pstm.setString(1, customerDTO.getName());
////        pstm.setString(2, customerDTO.getAddress());
////        pstm.setString(3, customerDTO.getId());
//////      return pstm.executeUpdate()> 0;
//        return SQLUtil.execute("INSERT INTO customer (id,name, address) VALUES (?,?,?)",customerDTO.getId(),customerDTO.getName(),customerDTO.getAddress());
//    }
//    public boolean delete(String id) throws SQLException, ClassNotFoundException {
////        Connection connection = DBConnection.getDbConnection().getConnection();
////        PreparedStatement pstm = connection.prepareStatement("DELETE FROM customer WHERE id=?");
////        pstm.setString(1, id);
////        ////
//      //return pstm.executeUpdate()> 0;
//        return SQLUtil.execute("DELETE FROM customer WHERE id=?");
//
//    }
//    public boolean exits(String id) throws SQLException, ClassNotFoundException {
//      //  Connection connection = DBConnection.getDbConnection().getConnection();
//      //  PreparedStatement pstm = connection.prepareStatement("SELECT id FROM customer WHERE id=?");
//      //  pstm.setString(1, id);
//      //  return pstm.executeQuery().next();
//        ResultSet resultSet=SQLUtil.execute("SELECT oid FROM orders WHERE oid=?",id);
//        return resultSet.next();
//    }
//    @Override
//    public String search(String newValue) throws SQLException, ClassNotFoundException {
////        Connection connection = DBConnection.getDbConnection().getConnection();
////        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM customer WHERE id=?");
////        pstm.setString(1, newValue + "");
////        ResultSet rst = pstm.executeQuery();
////        rst.next();
////        CustomerDTO customerDTO = new CustomerDTO(newValue + "", rst.getString("name"), rst.getString("address"));
////        return customerDTO.getName();
//        return SQLUtil.execute("SELECT * FROM customer WHERE id=?");
//    }
//
//    @Override
//    public void customerNameAddress() throws SQLException, ClassNotFoundException {
//
//    }
//}
////        public String newId() throws SQLException, ClassNotFoundException {
////            Connection connection = DBConnection.getDbConnection().getConnection();
////            ResultSet rst = connection.createStatement().executeQuery("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
////            if (rst.next()) {
////                String id = rst.getString("id");
////                int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
////                return String.format("C00-%03d", newCustomerId);
////            } else {
////                return "C00-001";
//
package com.example.layeredarchitecture.dao.impl;

import com.example.layeredarchitecture.dao.SQLUtil;
import com.example.layeredarchitecture.dao.custom.CustomerDAO;
import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();*/
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer");

        ArrayList<CustomerDTO> getAllCustomer = new ArrayList<>();

        while (rst.next()) {
            CustomerDTO customerDTO = new CustomerDTO(rst.getString("id"), rst.getString("name"), rst.getString("address"));
            getAllCustomer.add(customerDTO);
        }

        return getAllCustomer;
    }
    @Override
    public boolean save(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO Customer (id,name, address) VALUES (?,?,?)");
        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getAddress());
        return pstm.executeUpdate() > 0;*/

        return SQLUtil.execute("INSERT INTO Customer (id,name, address) VALUES (?,?,?)",
                dto.getId(),dto.getName(),dto.getAddress());

    }
    @Override
    public boolean update(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE Customer SET name=?, address=? WHERE id=?");
        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getAddress());
        pstm.setString(3, dto.getId());
        return pstm.executeUpdate() > 0;*/

        return SQLUtil.execute("UPDATE Customer SET name=?, address=? WHERE id=?",
                dto.getName(),dto.getAddress(),dto.getId());
    }
    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM Customer WHERE id=?");
        pstm.setString(1, id);
        return pstm.executeUpdate() > 0;*/

        return SQLUtil.execute("DELETE FROM Customer WHERE id=?",
                id);
    }
    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT id FROM Customer WHERE id=?");
        pstm.setString(1, id);
        return pstm.executeQuery().next();*/

        ResultSet set = SQLUtil.execute("SELECT id FROM Customer WHERE id=?",
                id);
        return set.next();
    }
    @Override
    public String nextId() throws SQLException, ClassNotFoundException {
        // Connection connection = DBConnection.getDbConnection().getConnection();
        ResultSet rst = SQLUtil.execute("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }
    }
    @Override
    public CustomerDTO search(String newValue) throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Customer WHERE id=?");
        pstm.setString(1, newValue + "");
        ResultSet rst = pstm.executeQuery();*/

        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer WHERE id=?",newValue + "");

        rst.next();
        CustomerDTO customerDTO = new CustomerDTO(newValue + "", rst.getString("name"), rst.getString("address"));

        return customerDTO;
    }
}