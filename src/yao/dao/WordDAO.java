package yao.dao;

import yao.bean.EnglishWord;
import yao.bean.Language;
import yao.bean.Word;
import yao.dao.EnglishWordDAO;
import yao.dao.LanguageDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class WordDAO
{
    private static final String ADD_WORD = "insert into WORD values (NULL,?,?,?)";
    private static final String FIND_WORD_BY_WORDNAME = "select * from WORD where WORDNAME = ?";
    private static final String FIND_WORD_BY_WORDNAMEANDENGLISHWORDIDANDLANGUAGEID =
            "select * from WORD where WORDNAME = ? AND ENGLISHWORDID=? AND LANGUAGEID=?";
    private static final String FIND_WORD_BY_WORDID = "select * from WORD where WORDID = ?";
    private static final String FIND_WORD_BY_ENGLISHWORDID = "select * from WORD where ENGLISHWORDID = ?";
    private static final String FIND_WORD_BY_LANGUAGEID = "select * from WORD where LANGUAGEID = ?";
    private static final String UPDATE_WORD = "update WORD set WORDNAME=?,ENGLISHWORDID=?,LANGUAGEID=? where WORDID=?";
    private static final String FIND_ALL_WORD = "select * from WORD";
    private static final String DELETE_WORD = "delete from WORD where WORDID = ?";



    public Word findWordByWordID(int wordID) {
        Word result = null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(FIND_WORD_BY_WORDID);
            pStatement.setInt(1, wordID);
            ResultSet resultSet = pStatement.executeQuery();
            if (resultSet.next()) {
                result = new Word();
                result.setWordID(resultSet.getInt(1));
                result.setWordName(resultSet.getString(2));
                result.setEnglishwordID(resultSet.getInt(3));
                result.setLanguageID(resultSet.getInt(4));
                result.setEnglishWordName(new EnglishWordDAO().findEnglishWordByEnglishWordID(resultSet.getInt(3)).getEnglishWordName());
                result.setLanguageName(new LanguageDAO().findLanguageByLanguageID(resultSet.getInt(4)).getLanguageName());
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Word> findWordByWordName(String wordName){

        List<Word> wordList = new ArrayList<Word>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(FIND_WORD_BY_WORDNAME);
            pStatement.setString(1, wordName);
            ResultSet resultSet = pStatement.executeQuery();
            while(resultSet.next()){
                Word word = new Word();
                word.setWordID(resultSet.getInt(1));
                word.setWordName(resultSet.getString(2));
                word.setEnglishwordID(resultSet.getInt(3));
                word.setLanguageID(resultSet.getInt(4));
                word.setEnglishWordName(new EnglishWordDAO().findEnglishWordByEnglishWordID(resultSet.getInt(3)).getEnglishWordName());
                word.setLanguageName(new LanguageDAO().findLanguageByLanguageID(resultSet.getInt(4)).getLanguageName());
                wordList.add(word);
            }

            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return wordList;
    }

    public Word findWordByWordNameAndEnglishWordNameAndLanguageName(String wordName,String englishWordName,String languageName){
        Word word = new Word();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(FIND_WORD_BY_WORDNAMEANDENGLISHWORDIDANDLANGUAGEID);
            pStatement.setString(1, wordName);
            pStatement.setInt(2, new EnglishWordDAO().findEnglishWordByEnglishWordName(englishWordName).getEnglishWordID());
            pStatement.setInt(3, new LanguageDAO().findLanguageByLanguageName(languageName).getLanguageID());
            ResultSet resultSet = pStatement.executeQuery();
            if(resultSet.next()){
                word.setWordID(resultSet.getInt(1));
                word.setWordName(resultSet.getString(2));
                word.setEnglishwordID(resultSet.getInt(3));
                word.setLanguageID(resultSet.getInt(4));
                word.setEnglishWordName(new EnglishWordDAO().findEnglishWordByEnglishWordID(resultSet.getInt(3)).getEnglishWordName());
                word.setLanguageName(new LanguageDAO().findLanguageByLanguageID(resultSet.getInt(4)).getLanguageName());
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return word;
    }

    public boolean existWordByWordNameAndEnglishWordNameAndLanguageName(String wordName,String englishWordName,String languageName){
        Word word = new Word();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(FIND_WORD_BY_WORDNAMEANDENGLISHWORDIDANDLANGUAGEID);
            pStatement.setString(1, wordName);
            pStatement.setInt(2, new EnglishWordDAO().findEnglishWordByEnglishWordName(englishWordName).getEnglishWordID());
            pStatement.setInt(3, new LanguageDAO().findLanguageByLanguageName(languageName).getLanguageID());
            ResultSet resultSet = pStatement.executeQuery();
            if(resultSet.next()){
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

    public List<Word> findWordsByEnglishWord(EnglishWord englishWord){
        List<Word> wordList = new ArrayList<Word>();
        try {
            Connection connection = DBUtil.getConnection();
            EnglishWord newEnglishWord=new EnglishWord();
            newEnglishWord = new EnglishWordDAO().findEnglishWordByEnglishWordName(englishWord.getEnglishWordName());
            int englishWordID=newEnglishWord.getEnglishWordID();
            PreparedStatement pStatement = connection.prepareStatement(FIND_WORD_BY_ENGLISHWORDID);
            pStatement.setInt(1, englishWordID);
            ResultSet resultSet = pStatement.executeQuery();
            while(resultSet.next()){
                Word word = new Word();
                word.setWordID(resultSet.getInt(1));
                word.setWordName(resultSet.getString(2));
                word.setEnglishwordID(resultSet.getInt(3));
                word.setLanguageID(resultSet.getInt(4));
                word.setEnglishWordName(new EnglishWordDAO().findEnglishWordByEnglishWordID(resultSet.getInt(3)).getEnglishWordName());
                word.setLanguageName(new LanguageDAO().findLanguageByLanguageID(resultSet.getInt(4)).getLanguageName());
                wordList.add(word);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return wordList;
    }

    public boolean existWordsByEnglishWord(EnglishWord englishWord){
        try {
            Connection connection = DBUtil.getConnection();
            EnglishWord newEnglishWord=new EnglishWord();
            newEnglishWord = new EnglishWordDAO().findEnglishWordByEnglishWordName(englishWord.getEnglishWordName());
            int englishWordID=newEnglishWord.getEnglishWordID();
            PreparedStatement pStatement = connection.prepareStatement(FIND_WORD_BY_ENGLISHWORDID);
            pStatement.setInt(1, englishWordID);
            ResultSet resultSet = pStatement.executeQuery();
            if(resultSet.next()){
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

    public List<Word> findWordsByLanguage(Language language){
        List<Word> wordList = new ArrayList<Word>();
        try {
            Connection connection = DBUtil.getConnection();
            Language newLanguage=new Language();
            newLanguage = new LanguageDAO().findLanguageByLanguageName(language.getLanguageName());
            int languageID=newLanguage.getLanguageID();
            PreparedStatement pStatement = connection.prepareStatement(FIND_WORD_BY_LANGUAGEID);
            pStatement.setInt(1, languageID);
            ResultSet resultSet = pStatement.executeQuery();
            while(resultSet.next()){
                Word word = new Word();
                word.setWordID(resultSet.getInt(1));
                word.setWordName(new WordDAO().findWordByWordID(resultSet.getInt(2)).getWordName());
                word.setEnglishwordID(resultSet.getInt(3));
                word.setLanguageID(resultSet.getInt(4));
                word.setEnglishWordName(new EnglishWordDAO().findEnglishWordByEnglishWordID(resultSet.getInt(3)).getEnglishWordName());
                word.setLanguageName(new LanguageDAO().findLanguageByLanguageID(resultSet.getInt(4)).getLanguageName());
                wordList.add(word);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return wordList;
    }

    public boolean existWordsByLanguage(Language language){
        try {
            Connection connection = DBUtil.getConnection();
            Language newLanguage=new Language();
            newLanguage = new LanguageDAO().findLanguageByLanguageName(language.getLanguageName());
            int languageID=newLanguage.getLanguageID();
            PreparedStatement pStatement = connection.prepareStatement(FIND_WORD_BY_LANGUAGEID);
            pStatement.setInt(1, languageID);
            ResultSet resultSet = pStatement.executeQuery();
            if(resultSet.next()){
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

    public List<Word> findAllWords(){
        List<Word> wordList = new ArrayList<Word>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(FIND_ALL_WORD);
            ResultSet resultSet = pStatement.executeQuery();
            while(resultSet.next()){
                Word word = new Word();
                word.setWordID(resultSet.getInt(1));
                word.setWordName(resultSet.getString(2));
                word.setEnglishwordID(resultSet.getInt(3));
                word.setLanguageID(resultSet.getInt(4));
                word.setEnglishWordName(new EnglishWordDAO().findEnglishWordByEnglishWordID(resultSet.getInt(3)).getEnglishWordName());
                word.setLanguageName(new LanguageDAO().findLanguageByLanguageID(resultSet.getInt(4)).getLanguageName());
                wordList.add(word);
            }

            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return wordList;
    }

    public boolean addWord(Word word){
        boolean result = false;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(ADD_WORD);
            PreparedStatement pStatement1 = connection.prepareStatement(FIND_WORD_BY_WORDNAMEANDENGLISHWORDIDANDLANGUAGEID);
            pStatement.setString(1, word.getWordName());
            pStatement.setInt(2,new EnglishWordDAO().findEnglishWordByEnglishWordName(word.getEnglishWordName()).getEnglishWordID());
            pStatement.setInt(3,new LanguageDAO().findLanguageByLanguageName(word.getlanguageName()).getLanguageID());
            pStatement1.setString(1, word.getWordName());
            pStatement1.setInt(2,new EnglishWordDAO().findEnglishWordByEnglishWordName(word.getEnglishWordName()).getEnglishWordID());
            pStatement1.setInt(3,new LanguageDAO().findLanguageByLanguageName(word.getlanguageName()).getLanguageID());
            ResultSet resultSet = pStatement1.executeQuery();
            if(resultSet.next()){
                System.out.println("WORD已经存在了");
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

    public boolean updateWord(Word word){
        boolean result = false;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(UPDATE_WORD);
            pStatement.setString(1,word.getWordName());
            pStatement.setInt(2,word.getEnglishWordID());
            pStatement.setInt(3, word.getLanguageID());
            pStatement.setInt(4, word.getWordID());

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

    public boolean deleteWord(int wordID) {
        boolean result = false;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(DELETE_WORD);
            pStatement.setInt(1,wordID);

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
