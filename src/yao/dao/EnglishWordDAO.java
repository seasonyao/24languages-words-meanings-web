package yao.dao;

import yao.bean.EnglishWord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class EnglishWordDAO
{
    private static final String ADD_ENGLISHWORD = "insert into ENGLISHWORD values (NULL,?)";
    private static final String FIND_ENGLISHWORD_BY_ENGLISHWORDID = "select * from ENGLISHWORD where ENGLISHWORDID = ?";
    private static final String FIND_ENGLISHWORD_BY_ENGLISHWORDNAME = "select * from ENGLISHWORD where ENGLISHWORDNAME = ?";
    //private static final String UPDATE_WORD = "update WORD set WORDNAME=?,ENGLISHWORDID=?,LANGUAGEID=? where WORDID=?";
    private static final String FIND_ALL_ENGLISHWORD = "select * from ENGLISHWORD";
    private static final String DELETE_ENGLISHWORD = "delete from ENGLISHWORD where ENGLISHWORDID = ?";



    public EnglishWord findEnglishWordByEnglishWordID(int englishWordID) {
        EnglishWord result = null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(FIND_ENGLISHWORD_BY_ENGLISHWORDID);
            pStatement.setInt(1, englishWordID);
            ResultSet resultSet = pStatement.executeQuery();
            if (resultSet.next()) {
                result = new EnglishWord();
                result.setEnglishWordID(resultSet.getInt(1));
                result.setEnglishWordName(resultSet.getString(2));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public EnglishWord findEnglishWordByEnglishWordName(String englishWordName){

        EnglishWord englishWord = new EnglishWord();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(FIND_ENGLISHWORD_BY_ENGLISHWORDNAME);
            pStatement.setString(1, englishWordName);
            ResultSet resultSet = pStatement.executeQuery();
            if(resultSet.next()){
                englishWord.setEnglishWordID(resultSet.getInt(1));
                englishWord.setEnglishWordName(resultSet.getString(2));
            }

            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return englishWord;
    }

    public boolean existEnglishWordByEnglishWordName(String englishWordName){

        EnglishWord englishWord = new EnglishWord();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(FIND_ENGLISHWORD_BY_ENGLISHWORDNAME);
            pStatement.setString(1, englishWordName);
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

    public List<EnglishWord> findAllEnglishWords(){
        List<EnglishWord> englishWordList = new ArrayList<EnglishWord>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(FIND_ALL_ENGLISHWORD);
            ResultSet resultSet = pStatement.executeQuery();
            while(resultSet.next()){
                EnglishWord englishWord = new EnglishWord();
                englishWord.setEnglishWordID(resultSet.getInt(1));
                englishWord.setEnglishWordName(resultSet.getString(2));
                englishWordList.add(englishWord);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return englishWordList;
    }

    public boolean addEnglishWord(EnglishWord englishWord){
        boolean result = false;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(ADD_ENGLISHWORD);
            PreparedStatement pStatement1 = connection.prepareStatement(FIND_ENGLISHWORD_BY_ENGLISHWORDNAME);
            pStatement.setString(1, englishWord.getEnglishWordName());
            pStatement1.setString(1, englishWord.getEnglishWordName());
            ResultSet resultSet = pStatement1.executeQuery();
            if(resultSet.next()){
                System.out.println("ENGLISHWORD已经存在了");
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

    public boolean deleteEnglishWord(int englishWordID) {
        boolean result = false;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(DELETE_ENGLISHWORD);
            pStatement.setInt(1,englishWordID);

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
