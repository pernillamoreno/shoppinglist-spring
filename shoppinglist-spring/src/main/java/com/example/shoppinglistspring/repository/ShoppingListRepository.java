package com.example.shoppinglistspring.repository;



import com.example.shoppinglistspring.db.MysqlDatabase;
import com.example.shoppinglistspring.model.ShoppingItem;
import com.example.shoppinglistspring.model.ShoppingList;

import java.sql.*;
import java.util.List;

public class ShoppingListRepository {

    private MysqlDatabase db;

    public ShoppingListRepository() {
        db = MysqlDatabase.getInstance();
    }


    public ShoppingList getShoppingList(String username) {
        Connection conn = db.getConnection();
        ShoppingList list = new ShoppingList(username);
        String sql = "" +
                "SELECT * FROM shoppingLists " +
                "JOIN listOwners " +
                "ON shoppingLists.ownerId=listOwners.id " +
                "WHERE listOwners.name = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if(!rs.next()) { return null; } //guardian clause

            do {
                ShoppingItem item = new ShoppingItem();
                item.setName(rs.getString("itemName"));
                item.setQuantity(rs.getInt("quantity"));

                list.getItemList().add(item);
            } while(rs.next());


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public void addItem(String username, ShoppingItem item) {
        Connection conn = db.getConnection();
        String sql = "SELECT id FROM listOwners WHERE name=?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();
            rs.next(); // Gå till första raden i query svaret

            int ownerId = rs.getInt("id");

            sql = "INSERT INTO shoppingLists (ownerId, itemName, quantity)" +
                    "VALUES (?,?,?)";

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, ownerId);
           // pstmt.setString(2, item.getName());
            //pstmt.setInt(3, item.getQuantity());

            pstmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createNew(String username) {
        Connection conn = db.getConnection();
        String sql = "INSERT INTO listOwners (name) VALUES (?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, username);
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}