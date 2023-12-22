////package com.example.layeredarchitecture.dao;
////
////import com.example.layeredarchitecture.db.DBConnection;
////import com.example.layeredarchitecture.model.OrderDetailDTO;
////
////import java.sql.*;
////import java.time.LocalDate;
////import java.util.List;
////
////public class OrderDAOImpl implements OrderDAO {
////
////    private ItemDAO itemDAO=new ItemDAOImpl();
////    private OrderDetailDAO orderDetailsDAO=new OrderDetailDAOImpl();
////
////    @Override
////    public String generateNextNewOrderId() throws SQLException, ClassNotFoundException {
////        Connection connection = DBConnection.getDbConnection().getConnection();
////        Statement stm = connection.createStatement();
////        ResultSet rst = stm.executeQuery("SELECT oid FROM `orders` ORDER BY oid DESC LIMIT 1;");
////
////        return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("oid").replace("OID-", "")) + 1)) : "OID-001";
////    }
////
////    @Override
////    public boolean saveOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException {
////        Connection connection = null;
////
////        connection = DBConnection.getDbConnection().getConnection();
////        PreparedStatement stm = connection.prepareStatement("SELECT oid FROM `Orders` WHERE oid=?");
////        stm.setString(1, orderId);
////        /*if order id already exist*/
////        if (stm.executeQuery().next()) {
////
////        }
////
////        connection.setAutoCommit(false);
////        stm = connection.prepareStatement("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)");
////        stm.setString(1, orderId);
////        stm.setDate(2, Date.valueOf(orderDate));
////        stm.setString(3, customerId);
////
////        if (stm.executeUpdate() != 1) {
////            connection.rollback();
////            connection.setAutoCommit(true);
////            return false;
////        }
////
////
////        for (OrderDetailDTO detail : orderDetails) {
////            boolean isSaveOrderDetail = orderDetailsDAO.saveOrderDetails(orderDetails, orderId);
////
////            if (!isSaveOrderDetail) {
////                connection.rollback();
////                connection.setAutoCommit(true);
////                return false;
////            }
////
////            //Search & Update Item
//////            ItemDTO item = itemDAO.findItems(detail.getItemCode());
//////            item.setQtyOnHand(item.getQtyOnHand() - detail.getQty());
//////
//////            boolean isUpdateItem = itemDAO.updateItem(item);
//////
//////            if (!isUpdateItem) {
//////                connection.rollback();
//////                connection.setAutoCommit(true);
//////                return false;
//////            }
//////        }
//////        connection.commit();
//////        connection.setAutoCommit(true);
//////        return true;
//////    }
////        }return false;
////    }}
//package com.example.layeredarchitecture.dao.impl;
//
//import com.example.layeredarchitecture.dao.custom.OrderDAO;
//import com.example.layeredarchitecture.db.DBConnection;
//import com.example.layeredarchitecture.model.OrderDTO;
//
//import java.sql.*;
//
//public class OrderDAOImpl implements OrderDAO {
//    @Override
//    public String generateOID() throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getDbConnection().getConnection();
//        Statement stm = connection.createStatement();
//        ResultSet rst = stm.executeQuery("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");
//        return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("oid").replace("OID-", "")) + 1)) : "OID-001";
//    }
//
//
//    @Override
//    public boolean existOrder(String orderId) throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getDbConnection().getConnection();
//        PreparedStatement stm = connection.prepareStatement("SELECT oid FROM `Orders` WHERE oid=?");
//        stm.setString(1, orderId);
//        return stm.executeQuery().next();
//    }
//
//
//    @Override
//    public boolean saveOrder(OrderDTO dto) throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getDbConnection().getConnection();
//        PreparedStatement stm = connection.prepareStatement("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)");
//        stm.setString(1, dto.getOrderId());
//        stm.setDate(2, Date.valueOf(dto.getOrderDate()));
//        stm.setString(3, dto.getCustomerId());
//        return stm.executeUpdate()>0;
//    }
//}
package com.example.layeredarchitecture.dao.impl;

import com.example.layeredarchitecture.dao.SQLUtil;
import com.example.layeredarchitecture.dao.custom.OrderDAO;
import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.model.OrderDTO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public String nextId() throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();*/

        ResultSet rst = SQLUtil.execute("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");

        return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("oid").replace("OID-", "")) + 1)) : "OID-001";
    }

    @Override
    public OrderDTO search(String newValue) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Orders WHERE oid=?",newValue + "");

        rst.next();
        OrderDTO orderDTO = new OrderDTO(newValue + "", rst.getString("date"), rst.getString("customerID"));

        return orderDTO;
    }

    @Override
    public ArrayList<OrderDTO> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Orders");

        ArrayList<OrderDTO> getAllOrders = new ArrayList<>();

        while (rst.next()) {
            OrderDTO orderDTO = new OrderDTO(rst.getString("oid"), rst.getString("date"), rst.getString("customerID"));
            getAllOrders.add(orderDTO);
        }

        return getAllOrders;
    }

    @Override
    public boolean save(OrderDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String orderId ) throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stm = connection.prepareStatement("SELECT oid FROM `Orders` WHERE oid=?");
        stm.setString(1, orderId);*/

        ResultSet rst = SQLUtil.execute("SELECT oid FROM `Orders` WHERE oid=?",orderId);
        return rst.next();



    }
    @Override
    public boolean save(String orderId, LocalDate orderDate, String customerId) throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stm = connection.prepareStatement("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)");
        stm.setString(1, orderId);
        stm.setDate(2, Date.valueOf(orderDate));
        stm.setString(3, customerId);

        return stm.executeUpdate() > 0;*/

        return SQLUtil.execute("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)",
                orderId,orderDate,customerId);

    }
}