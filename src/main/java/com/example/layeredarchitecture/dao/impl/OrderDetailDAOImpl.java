////package com.example.layeredarchitecture.dao;
////
////import com.example.layeredarchitecture.db.DBConnection;
////import com.example.layeredarchitecture.model.OrderDetailDTO;
////
////import java.sql.Connection;
////import java.sql.PreparedStatement;
////import java.sql.SQLException;
////import java.util.List;
////
////public class OrderDetailDAOImpl implements OrderDetailDAO {
////    @Override
////    public boolean saveOrderDetails(List<OrderDetailDTO> orderDetails, String orderId) throws SQLException, ClassNotFoundException {
////        Connection connection = DBConnection.getDbConnection().getConnection();
////        PreparedStatement stm;
////        stm = connection.prepareStatement("INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)");
////
////        for (OrderDetailDTO detail : orderDetails) {
////            stm.setString(1, orderId);
////            stm.setString(2, detail.getItemCode());
////            stm.setBigDecimal(3, detail.getUnitPrice());
////            stm.setInt(4, detail.getQty());
////        }
////
////        if (stm.executeUpdate() >0) {
////            return true;
////        }
////        return false;
////    }
////}
//package com.example.layeredarchitecture.dao.impl;
//
//import com.example.layeredarchitecture.dao.custom.OrderDetailDAO;
//import com.example.layeredarchitecture.db.DBConnection;
//import com.example.layeredarchitecture.model.OrderDetailDTO;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class OrderDetailDAOImpl implements OrderDetailDAO {
//    @Override
//    public boolean saveOrderDetails(OrderDetailDTO dto) throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getDbConnection().getConnection();
//        PreparedStatement stm = connection.prepareStatement("INSERT INTO orderdetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)");
//        stm.setString(1, dto.getOid());
//        stm.setString(2, dto.getItemCode());
//        stm.setBigDecimal(3, dto.getUnitPrice());
//        stm.setInt(4, dto.getQty());
//        return stm.executeUpdate()>0;
//    }
//}
package com.example.layeredarchitecture.dao.impl;

import com.example.layeredarchitecture.dao.SQLUtil;
import com.example.layeredarchitecture.dao.custom.OrderDetailDAO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Override
    public boolean save(String orderId, OrderDetailDTO orderDetails ) throws SQLException, ClassNotFoundException {
        /*Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stm = connection.prepareStatement("INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)");


            stm.setString(1, orderId);
            stm.setString(2, orderDetails.getItemCode());
            stm.setBigDecimal(3, orderDetails.getUnitPrice());
            stm.setInt(4, orderDetails.getQty());


        return stm.executeUpdate() > 0;*/

        return SQLUtil.execute("INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)",
                orderId,orderDetails.getItemCode(),orderDetails.getUnitPrice(),orderDetails.getQty());
    }

    @Override
    public ArrayList<OrderDetailDTO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(OrderDetailDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderDetailDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String nextId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public OrderDetailDTO search(String newValue) throws SQLException, ClassNotFoundException {
        return null;
    }
}