package yao.dao;

import java.sql.*;

public class DBUtil
{
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/translator_test";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "qwer123";

    public static Connection getConnection() throws Exception{
        Connection connection = null;
        Class.forName(DRIVER);
        connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        return connection;
    }

    public static void closeConnection(Connection connection) throws Exception{
        if(connection != null){
            connection.close();
        }
    }

    public static void closeStatement(Statement statement) throws Exception{
        if(statement != null){
            statement.close();
        }
    }

    public static void closePreparedStatement(PreparedStatement pStatement) throws Exception{
        if(pStatement != null){
            pStatement.close();
        }
    }

    public static void closeResultSet(ResultSet resultSet) throws Exception{
        if(resultSet != null){
            resultSet.close();
        }
    }

}
