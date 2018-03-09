package yao.dao;

import yao.bean.Meaning;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class MeaningDAO
{
    private static final String ADD_MEANING = "insert into MEANING values (NULL,?)";
    private static final String FIND_MEANING_BY_MEANINGID = "select * from MEANING where MEANINGID = ?";
    private static final String FIND_MEANING_BY_MEANINGNAME = "select * from MEANING where MEANINGNAME = ?";
    //private static final String UPDATE_WORD = "update WORD set WORDNAME=?,ENGLISHWORDID=?,LANGUAGEID=? where WORDID=?";
    private static final String FIND_ALL_MEANING = "select * from MEANING";
    private static final String DELETE_MEANING = "delete from MEANING where MEANINGID = ?";



    public Meaning findMeaningByMeaningID(int meaningID) {
        Meaning result = null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(FIND_MEANING_BY_MEANINGID);
            pStatement.setInt(1, meaningID);
            ResultSet resultSet = pStatement.executeQuery();
            if (resultSet.next()) {
                result = new Meaning();
                result.setMeaningID(resultSet.getInt(1));
                result.setMeaningName(resultSet.getString(2));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Meaning findMeaningByMeaningName(String meaningName){

        Meaning meaning = new Meaning();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(FIND_MEANING_BY_MEANINGNAME);
            pStatement.setString(1, meaningName);
            ResultSet resultSet = pStatement.executeQuery();
            if(resultSet.next()){
                meaning.setMeaningID(resultSet.getInt(1));
                meaning.setMeaningName(resultSet.getString(2));
            }

            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return meaning;
    }
    public boolean existMeaningByMeaningName(String meaningName){
        Meaning meaning = new Meaning();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(FIND_MEANING_BY_MEANINGNAME);
            pStatement.setString(1, meaningName);
            ResultSet resultSet = pStatement.executeQuery();
            if(resultSet.next()){
                DBUtil.closeResultSet(resultSet);
                DBUtil.closePreparedStatement(pStatement);
                DBUtil.closeConnection(connection);
                return true;
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public List<Meaning> findAllMeanings(){
        List<Meaning> meaningsList = new ArrayList<Meaning>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(FIND_ALL_MEANING);
            ResultSet resultSet = pStatement.executeQuery();
            while(resultSet.next()){
                Meaning meaning = new Meaning();
                meaning.setMeaningID(resultSet.getInt(1));
                meaning.setMeaningName(resultSet.getString(2));
                meaningsList.add(meaning);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return meaningsList;
    }

    public boolean addMeaning(Meaning meaning){
        boolean result = false;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(ADD_MEANING);
            PreparedStatement pStatement1 = connection.prepareStatement(FIND_MEANING_BY_MEANINGNAME);
            pStatement.setString(1, meaning.getMeaningName());
            pStatement1.setString(1, meaning.getMeaningName());
            ResultSet resultSet = pStatement1.executeQuery();
            if(resultSet.next()){
                System.out.println("MEANING已经存在了");
                DBUtil.closePreparedStatement(pStatement);
                DBUtil.closeConnection(connection);
                return false;
            }
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
    public boolean deleteMeaning(int meaningID) {
        boolean result = false;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(DELETE_MEANING);
            pStatement.setInt(1,meaningID);
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
