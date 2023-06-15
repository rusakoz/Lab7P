package org.server.DataBase;

import java.sql.*;

public class DatabaseHandler extends Config{
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException{
        String connection = "jdbc:postgresql://" + dbHost + ":"
                + dbPort + "/" + dbName;

        dbConnection = DriverManager.getConnection(connection, dbUsername, dbPass);

        Class.forName("org.postgresql.Driver");

        return dbConnection;
    }

    public void insert(String name){
        String insert = "INSERT INTO " + Const.COLLECTION_TABLE + "(" + Const.COLLECTION_NAME + ")" + " VALUES(?)";

        PreparedStatement prSt = null;
        try {
            prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, name);
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet select(){
        ResultSet resultSet = null;

        String select = "SELECT *"  + " FROM " + Const.COLLECTION_TABLE;

        PreparedStatement prSt = null;
        try {
            prSt = getDbConnection().prepareStatement(select);
            resultSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }
}
