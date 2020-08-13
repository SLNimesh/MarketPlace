package com.nimeshnelanga;

import java.sql.*;

public class Database {
    private static ResultSet resultSet;
    private static Statement statement;

    static {
        statement = null;
        resultSet = null;
        try {
            String url = "jdbc:mysql://localhost:3306/marketplace?verifyServerCertificate=false&useSSL=true";
            Connection connection = DriverManager.getConnection(url, "root", "onionnetwork");
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getItemSeller(int barcode) {
        try{
            resultSet = statement.executeQuery("SELECT seller_id FROM items WHERE barcode = "+barcode);
            resultSet.next();
            return resultSet.getInt(1);
        }catch (SQLException e){

        }
       return -1;
    }

    public static boolean setItemPrice(int barcode,double price){
        try {
            if(price>0){
                statement.executeQuery("UPDATE stock SET item_price"+price+" WHERE item_code = "+barcode);
                return true;
            }
        }catch (SQLException e){

        }
        return false;
    }

    public static double findItemPRice(int barcode,int sellerID) {
        try{
            resultSet = statement.executeQuery("SELECT item_price FROM stock WHERE item_code = "+barcode+" AND seller_id="+sellerID);
            if(resultSet.next()) return resultSet.getDouble(1);
        }catch (SQLException e){

        }
        return -1;
    }


    public static void updateAvailability(int barcode){
        try{
            resultSet = statement.executeQuery("SELECT total_stock,in_cart FROM stock WHERE item_code = "+barcode);
            resultSet.next();
            int availability=resultSet.getInt(1)-resultSet.getInt(2);
            statement.executeQuery("UPDATE stock SET available_items ="+availability+"WHERE item_code ="+barcode);
        }catch (SQLException e){

        }
    }
    public static boolean changeStock(int barcode,int count){
        try{
            resultSet = statement.executeQuery("SELECT total_stock FROM stock WHERE item_code = "+barcode);
            resultSet.next();
            int total=resultSet.getInt(1)+count;
            if (total >= 0) {
                statement.executeQuery("UPDATE stock SET total_stock ="+total+" WHERE item_code = "+barcode);
                updateAvailability(barcode);
                return true;
            }
        }catch (SQLException e){

        }
        return false;
    }

    public static int checkAvailability(int barcode){
        try{
            resultSet = statement.executeQuery("SELECT available_items FROM stock WHERE item_code = "+barcode);
           if( resultSet.next()){
               return resultSet.getInt(1);
            }
        }catch (SQLException e){

        }
        return -1;
    }

    public static boolean updateInCart(int barcode,int amount){
        try {
            resultSet = statement.executeQuery("SELECT in_cart FROM stock WHERE item_code = "+barcode);
            resultSet.next();
            int inCart=resultSet.getInt(1)+amount;
            statement.executeQuery("UPDATE stock SET in_cart ="+inCart+" WHERE item_code = "+barcode);
            updateAvailability(barcode);
            return true;
        }catch (SQLException e){

        }
        return false;
    }

    public static String getName(int emp_id) throws SQLException {
        resultSet = statement.executeQuery("SELECT first_name FROM employee WHERE emp_id = " + emp_id);
        resultSet.next();
        String first_name = resultSet.getString("first_name");
        String last_name = resultSet.getString("last_name");
        System.out.println(first_name + " " + last_name);
        return (first_name + " " + last_name);
    }



    public static void DBtest() {

        Statement stmt = null;
        ResultSet rs = null;

        try {
            String url = "jdbc:mysql://localhost:3306/mockdatabase?verifyServerCertificate=false&useSSL=true";
            Connection conn = DriverManager.getConnection(url, "root", "onionnetwork");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT first_name,last_name FROM employee WHERE emp_id = 100");
            // or alternatively, if you don't know ahead of time that
            // the query will be a SELECT...
            rs.next();
            String name = rs.getString(2);
            System.out.println(name);

            // Now do something with the ResultSet ....
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) {
                } // ignore

                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                } // ignore

                stmt = null;
            }
        }
    }
}
