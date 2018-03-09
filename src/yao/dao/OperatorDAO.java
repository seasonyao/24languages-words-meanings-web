package yao.dao;

import yao.bean.Operator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class OperatorDAO
{
    private static final String ADD_OPERATOR = "insert into T_OPERATOR values (?,?,?,?)";
    private static final String FIND_OPERATOR_BY_ID_AND_PWD = "select * from T_OPERATOR where OPERATOR_ID = ? and OPERATOR_PWD = ?";
    private static final String FIND_OPERATOR_BY_ID = "select * from T_OPERATOR where OPERATOR_ID = ?";
    private static final String UPDATE_OPERATOR = "update T_OPERATOR set OPERATOR_NAME=?,OPERATOR_PWD=?,IS_ADMIN=? where OPERATOR_ID=?";
    private static final String FIND_ALL_OPERATORS = "select * from T_OPERATOR";
    private static final String DELETE_OPERATOR = "delete from T_OPERATOR where OPERATOR_ID = ?";

    public Operator findOperatorByIDAndPWD(Operator operator){
        Operator result = null;
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(FIND_OPERATOR_BY_ID_AND_PWD);
            pStatement.setString(1, operator.getId());
            pStatement.setString(2,operator.getPassword());
            ResultSet resultSet = pStatement.executeQuery();
            if(resultSet.next()){
                result = new Operator();
                result.setId(resultSet.getString(1));
                result.setName(resultSet.getString(2));
                result.setPassword(resultSet.getString(3));
                result.setIsAdmin(resultSet.getInt(4)==1?true:false);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public Operator findOperatorByID(String operatorID) {
        Operator result = null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(FIND_OPERATOR_BY_ID);
            pStatement.setString(1, operatorID);
            ResultSet resultSet = pStatement.executeQuery();
            if (resultSet.next()) {
                result = new Operator();
                result.setId(resultSet.getString(1));
                result.setName(resultSet.getString(2));
                result.setPassword(resultSet.getString(3));
                result.setIsAdmin(resultSet.getInt(4) == 1 ? true : false);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Operator> findAllOperators(){
        List<Operator> operatorList = new ArrayList<Operator>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(FIND_ALL_OPERATORS);
            ResultSet resultSet = pStatement.executeQuery();
            while(resultSet.next()){
                Operator operator = new Operator();
                operator.setId(resultSet.getString(1));
                operator.setName(resultSet.getString(2));
                operator.setPassword(resultSet.getString(3));
                operator.setIsAdmin(resultSet.getInt(4) == 1 ? true : false);
                operatorList.add(operator);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return operatorList;
    }

    public boolean addOperator(Operator operator){
        boolean result = false;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(ADD_OPERATOR);
            pStatement.setString(1, operator.getId());
            pStatement.setString(2, operator.getName());
            pStatement.setString(3, operator.getPassword());
            pStatement.setInt(4, operator.isAdmin() ? 1 : 0);

            if(pStatement.executeUpdate() == 1){
                result = true;
            }
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public boolean updateOperator(Operator operator){
        boolean result = false;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(UPDATE_OPERATOR);
            pStatement.setString(1,operator.getName());
            pStatement.setString(2,operator.getPassword());
            pStatement.setInt(3, operator.isAdmin()==true ? 1 : 0);
            pStatement.setString(4, operator.getId());

            if(pStatement.executeUpdate() == 1){
                result = true;
            }

            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    public boolean deleteOperator(String operatorID) {
        boolean result = false;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(DELETE_OPERATOR);
            pStatement.setString(1,operatorID);

            if(pStatement.executeUpdate() == 1){
                result = true;
            }
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
