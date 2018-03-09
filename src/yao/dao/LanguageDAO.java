package yao.dao;

import yao.bean.Language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class LanguageDAO
{
    private static final String ADD_LANGUAGE = "insert into LANGUAGE values (NULL,?)";
    private static final String FIND_LANGUAGE_BY_LANGUAGEID = "select * from LANGUAGE where LANGUAGEID = ?";
    private static final String FIND_LANGUAGE_BY_LANGUAGENAME = "select * from LANGUAGE where LANGUAGENAME = ?";
    //private static final String UPDATE_WORD = "update WORD set WORDNAME=?,ENGLISHWORDID=?,LANGUAGEID=? where WORDID=?";
    private static final String FIND_ALL_LANGUAGE = "select * from LANGUAGE";
    private static final String DELETE_LANGUAGE = "delete from LANGUAGE where LANGUAGEID = ?";



    public Language findLanguageByLanguageID(int languageID) {
        Language result = null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(FIND_LANGUAGE_BY_LANGUAGEID);
            pStatement.setInt(1, languageID);
            ResultSet resultSet = pStatement.executeQuery();
            if (resultSet.next()) {
                result = new Language();
                result.setLanguageID(resultSet.getInt(1));
                result.setLanguageName(resultSet.getString(2));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Language findLanguageByLanguageName(String languageName){

        Language language = new Language();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(FIND_LANGUAGE_BY_LANGUAGENAME);
            pStatement.setString(1, languageName);
            ResultSet resultSet = pStatement.executeQuery();
            if(resultSet.next()){
                language.setLanguageID(resultSet.getInt(1));
                language.setLanguageName(resultSet.getString(2));
            }

            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return language;
    }

    public boolean existLanguageByLanguageName(String languageName){

        Language language = new Language();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(FIND_LANGUAGE_BY_LANGUAGENAME);
            pStatement.setString(1, languageName);
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

    public List<Language> findAllLanguages(){
        List<Language> languagesList = new ArrayList<Language>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(FIND_ALL_LANGUAGE);
            ResultSet resultSet = pStatement.executeQuery();
            while(resultSet.next()){
                Language language = new Language();
                language.setLanguageID(resultSet.getInt(1));
                language.setLanguageName(resultSet.getString(2));
                languagesList.add(language);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return languagesList;
    }

    public boolean addLanguage(Language language){
        boolean result = false;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(ADD_LANGUAGE);
            PreparedStatement pStatement1 = connection.prepareStatement(FIND_LANGUAGE_BY_LANGUAGENAME);
            pStatement.setString(1, language.getLanguageName());
            pStatement1.setString(1, language.getLanguageName());
            ResultSet resultSet = pStatement1.executeQuery();
            if(resultSet.next()){
                System.out.println("LANGUAGE已经存在了");
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

    public boolean deleteLanguage(int languageID) {
        boolean result = false;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(DELETE_LANGUAGE);
            pStatement.setInt(1,languageID);

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
