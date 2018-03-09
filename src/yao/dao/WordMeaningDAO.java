package yao.dao;

import yao.bean.EnglishWord;
import yao.bean.Meaning;
import yao.bean.WordMeaning;
import yao.bean.Word;
import yao.dao.WordDAO;
import yao.dao.MeaningDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class WordMeaningDAO
{
    private static final String ADD_WORDMEANING = "insert into WORD_MEANING values (NULL,?,?)";
    private static final String FIND_WORDMEANING_BY_WORDMEANINGID = "select * from WORD_MEANING where WORD_MEANINGID = ?";
    private static final String FIND_WORDMEANING_BY_WORDID = "select * from WORD_MEANING where WORDID = ?";
    private static final String FIND_WORDMEANING_BY_MEANINGID = "select * from WORD_MEANING where MEANINGID = ?";
    private static final String FIND_WORDMEANING_BY_WORDIDandMEANINGID = "select * from WORD_MEANING where MEANINGID = ? AND WORDID=?";
    //private static final String UPDATE_WORD = "update WORD set WORDNAME=?,ENGLISHWORDID=?,LANGUAGEID=? where WORDID=?";
    private static final String FIND_ALL_WORDMEANING = "select * from WORD_MEANING";
    private static final String DELETE_WORDMEANING = "delete from WORD_MEANING where WORD_MEANINGID = ?";



    public WordMeaning findWordMeaningByWordMeaningID(int wordMeaningID) {
        WordMeaning result = null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(FIND_WORDMEANING_BY_WORDMEANINGID);
            pStatement.setInt(1, wordMeaningID);
            ResultSet resultSet = pStatement.executeQuery();
            if (resultSet.next()) {
                result = new WordMeaning();
                result.setWordMeaningID(resultSet.getInt(1));;
                result.setWordID(resultSet.getInt(2));
                result.setMeaningID(resultSet.getInt(3));
                result.setWordName(new WordDAO().findWordByWordID(resultSet.getInt(2)).getWordName());
                result.setMeaningName(new MeaningDAO().findMeaningByMeaningID(resultSet.getInt(3)).getMeaningName());
                result.setEnglishWordName(new WordDAO().findWordByWordID(resultSet.getInt(2)).getEnglishWordName());
                result.setLanguageName(new WordDAO().findWordByWordID(resultSet.getInt(2)).getlanguageName());
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<WordMeaning> findAllWordMeanings(){
        List<WordMeaning> wordMeaningList = new ArrayList<WordMeaning>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(FIND_ALL_WORDMEANING);
            ResultSet resultSet = pStatement.executeQuery();
            while(resultSet.next()){
                WordMeaning wordMeaning = new WordMeaning();
                wordMeaning.setWordMeaningID(resultSet.getInt(1));
                wordMeaning.setWordID(resultSet.getInt(2));
                wordMeaning.setMeaningID(resultSet.getInt(3));
                wordMeaning.setWordName(new WordDAO().findWordByWordID(resultSet.getInt(2)).getWordName());
                wordMeaning.setMeaningName(new MeaningDAO().findMeaningByMeaningID(resultSet.getInt(3)).getMeaningName());

                wordMeaning.setEnglishWordName(new WordDAO().findWordByWordID(resultSet.getInt(2)).getEnglishWordName());
                wordMeaning.setLanguageName(new WordDAO().findWordByWordID(resultSet.getInt(2)).getlanguageName());
                wordMeaningList.add(wordMeaning);
            }

            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return wordMeaningList;
    }

    public List<WordMeaning> findWordMeaningsByWordName(String wordName){
        List<WordMeaning> wordMeaningList = new ArrayList<WordMeaning>();
        try {
            for(Word word : new WordDAO().findWordByWordName(wordName)) {
                Connection connection = DBUtil.getConnection();
                int wordID=word.getWordID();
                PreparedStatement pStatement = connection.prepareStatement(FIND_WORDMEANING_BY_WORDID);
                pStatement.setInt(1, wordID);
                ResultSet resultSet = pStatement.executeQuery();
                while(resultSet.next()){
                    WordMeaning wordMeaning = new WordMeaning();
                    wordMeaning.setWordMeaningID(resultSet.getInt(1));
                    wordMeaning.setWordID(resultSet.getInt(2));
                    wordMeaning.setMeaningID(resultSet.getInt(3));
                    wordMeaning.setWordName(new WordDAO().findWordByWordID(resultSet.getInt(2)).getWordName());
                    wordMeaning.setMeaningName(new MeaningDAO().findMeaningByMeaningID(resultSet.getInt(3)).getMeaningName());
                    wordMeaning.setEnglishWordName(new WordDAO().findWordByWordID(resultSet.getInt(2)).getEnglishWordName());
                    wordMeaning.setLanguageName(new WordDAO().findWordByWordID(resultSet.getInt(2)).getlanguageName());
                    wordMeaningList.add(wordMeaning);
                }
                DBUtil.closeResultSet(resultSet);
                DBUtil.closePreparedStatement(pStatement);
                DBUtil.closeConnection(connection);
            }


        }catch (Exception e){
            e.printStackTrace();
        }
        return wordMeaningList;
    }

    public boolean existWordMeaningByWordName(String wordName){
        try {
            Connection connection = DBUtil.getConnection();
            for(Word word : new WordDAO().findWordByWordName(wordName)) {
                int wordID=word.getWordID();
                PreparedStatement pStatement = connection.prepareStatement(FIND_WORDMEANING_BY_WORDID);
                pStatement.setInt(1, wordID);
                ResultSet resultSet = pStatement.executeQuery();
                if(resultSet.next()){
                   return true;
                }
                DBUtil.closeResultSet(resultSet);
                DBUtil.closePreparedStatement(pStatement);
                DBUtil.closeConnection(connection);
            }


        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public List<WordMeaning> findWordMeaningsByWord(Word word){
        List<WordMeaning> wordMeaningList = new ArrayList<WordMeaning>();
        try {
            Connection connection = DBUtil.getConnection();
            Word newWord=new Word();
            newWord = new WordDAO().findWordByWordNameAndEnglishWordNameAndLanguageName(
                    word.getWordName(),word.getEnglishWordName(),word.getlanguageName());
            int wordID=newWord.getWordID();
            PreparedStatement pStatement = connection.prepareStatement(FIND_WORDMEANING_BY_WORDID);
            pStatement.setInt(1, wordID);
            ResultSet resultSet = pStatement.executeQuery();
            while(resultSet.next()){
                WordMeaning wordMeaning = new WordMeaning();
                wordMeaning.setWordMeaningID(resultSet.getInt(1));
                wordMeaning.setWordID(resultSet.getInt(2));
                wordMeaning.setMeaningID(resultSet.getInt(3));
                wordMeaning.setWordName(new WordDAO().findWordByWordID(resultSet.getInt(2)).getWordName());
                wordMeaning.setMeaningName(new MeaningDAO().findMeaningByMeaningID(resultSet.getInt(3)).getMeaningName());
                wordMeaning.setEnglishWordName(new WordDAO().findWordByWordID(resultSet.getInt(2)).getEnglishWordName());
                wordMeaning.setLanguageName(new WordDAO().findWordByWordID(resultSet.getInt(2)).getlanguageName());
                wordMeaningList.add(wordMeaning);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return wordMeaningList;
    }

    public boolean existWordMeaningsByWord(Word word){
        try {
            Connection connection = DBUtil.getConnection();
            Word newWord=new Word();
            newWord = new WordDAO().findWordByWordNameAndEnglishWordNameAndLanguageName(
                    word.getWordName(),word.getEnglishWordName(),word.getlanguageName());
            int wordID=newWord.getWordID();
            PreparedStatement pStatement = connection.prepareStatement(FIND_WORDMEANING_BY_WORDID);
            pStatement.setInt(1, wordID);
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

    public List<WordMeaning> findWordMeaningsByMeaning(Meaning meaning){
        List<WordMeaning> wordMeaningList = new ArrayList<WordMeaning>();
        try {
            Connection connection = DBUtil.getConnection();
            Meaning newMeaning=new Meaning();
            newMeaning = new MeaningDAO().findMeaningByMeaningName(meaning.getMeaningName());
            int meaningID=newMeaning.getMeaningID();
            PreparedStatement pStatement = connection.prepareStatement(FIND_WORDMEANING_BY_MEANINGID);
            pStatement.setInt(1, meaningID);
            ResultSet resultSet = pStatement.executeQuery();
            while(resultSet.next()){
                WordMeaning wordMeaning = new WordMeaning();
                wordMeaning.setWordMeaningID(resultSet.getInt(1));
                wordMeaning.setWordID(resultSet.getInt(2));
                wordMeaning.setMeaningID(resultSet.getInt(3));
                wordMeaning.setWordName(new WordDAO().findWordByWordID(resultSet.getInt(2)).getWordName());
                wordMeaning.setMeaningName(new MeaningDAO().findMeaningByMeaningID(resultSet.getInt(3)).getMeaningName());
                wordMeaning.setEnglishWordName(new WordDAO().findWordByWordID(resultSet.getInt(2)).getEnglishWordName());
                wordMeaning.setLanguageName(new WordDAO().findWordByWordID(resultSet.getInt(2)).getlanguageName());
                wordMeaningList.add(wordMeaning);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return wordMeaningList;
    }

    public boolean existWordMeaningsByMeaning(Meaning meaning){
        try {
            Connection connection = DBUtil.getConnection();
            Meaning newMeaning=new Meaning();
            newMeaning = new MeaningDAO().findMeaningByMeaningName(meaning.getMeaningName());
            int meaningID=newMeaning.getMeaningID();
            PreparedStatement pStatement = connection.prepareStatement(FIND_WORDMEANING_BY_MEANINGID);
            pStatement.setInt(1, meaningID);
            ResultSet resultSet = pStatement.executeQuery();
            while(resultSet.next()){
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

    public List<WordMeaning> findWordMeaningsByMeaningName(String meaningName){
        List<WordMeaning> wordMeaningList = new ArrayList<WordMeaning>();
        try {
            Connection connection = DBUtil.getConnection();
            Meaning meaning=new MeaningDAO().findMeaningByMeaningName(meaningName);
            int meaningID=meaning.getMeaningID();
            PreparedStatement pStatement = connection.prepareStatement(FIND_WORDMEANING_BY_MEANINGID);
            pStatement.setInt(1, meaningID);
            ResultSet resultSet = pStatement.executeQuery();
            while(resultSet.next()){
                WordMeaning wordMeaning = new WordMeaning();
                wordMeaning.setWordMeaningID(resultSet.getInt(1));
                wordMeaning.setWordID(resultSet.getInt(2));
                wordMeaning.setMeaningID(resultSet.getInt(3));
                wordMeaning.setWordName(new WordDAO().findWordByWordID(resultSet.getInt(2)).getWordName());
                wordMeaning.setMeaningName(new MeaningDAO().findMeaningByMeaningID(resultSet.getInt(3)).getMeaningName());
                wordMeaning.setEnglishWordName(new WordDAO().findWordByWordID(resultSet.getInt(2)).getEnglishWordName());
                wordMeaning.setLanguageName(new WordDAO().findWordByWordID(resultSet.getInt(2)).getlanguageName());
                wordMeaningList.add(wordMeaning);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);

        }catch (Exception e){
            e.printStackTrace();
        }
        return wordMeaningList;
    }

    public List<WordMeaning> findWordMeaningsByEnglishWordName(String englishWordName){
        List<WordMeaning> wordMeaningList = new ArrayList<WordMeaning>();
        try {
            EnglishWord englishWord=new EnglishWordDAO().findEnglishWordByEnglishWordName(englishWordName);
            for(Word word : new WordDAO().findWordsByEnglishWord(englishWord)) {
                Connection connection = DBUtil.getConnection();
                PreparedStatement pStatement = connection.prepareStatement(FIND_WORDMEANING_BY_WORDID);
                pStatement.setInt(1, word.getWordID());
                ResultSet resultSet = pStatement.executeQuery();
                while (resultSet.next()) {
                    WordMeaning wordMeaning = new WordMeaning();
                    wordMeaning.setWordMeaningID(resultSet.getInt(1));
                    wordMeaning.setWordID(resultSet.getInt(2));
                    wordMeaning.setMeaningID(resultSet.getInt(3));
                    wordMeaning.setWordName(new WordDAO().findWordByWordID(resultSet.getInt(2)).getWordName());
                    wordMeaning.setMeaningName(new MeaningDAO().findMeaningByMeaningID(resultSet.getInt(3)).getMeaningName());
                    wordMeaning.setEnglishWordName(new WordDAO().findWordByWordID(resultSet.getInt(2)).getEnglishWordName());
                    wordMeaning.setLanguageName(new WordDAO().findWordByWordID(resultSet.getInt(2)).getlanguageName());
                    wordMeaningList.add(wordMeaning);
                }
                DBUtil.closeResultSet(resultSet);
                DBUtil.closePreparedStatement(pStatement);
                DBUtil.closeConnection(connection);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return wordMeaningList;
    }

//    public WordMeaning findWordMeaningsByWordAndMeaning(String wordName,String meaningName,String englishWordName,String languageName){
//        WordMeaning wordMeaning = new WordMeaning();
//        try {
//            Connection connection = DBUtil.getConnection();
//            if (new MeaningDAO().existMeaningByMeaningName(meaningName)&&
//                    new WordDAO().existWordByWordName(wordName,englishWordName,languageName)) {
//                Meaning meaning = new MeaningDAO().findMeaningByMeaningName(meaningName);
//                for (Word word : new WordDAO().findWordByWordName(wordName)) {
//                    PreparedStatement pStatement = connection.prepareStatement(FIND_WORDMEANING_BY_WORDIDandMEANINGID);
//                    pStatement.setInt(1, word.getWordID());
//                    pStatement.setInt(2, meaning.getMeaningID());
//                    ResultSet resultSet = pStatement.executeQuery();
//                    if (resultSet.next()) {
//                        wordMeaning.setWordMeaningID(resultSet.getInt(1));
//                        wordMeaning.setWordID(resultSet.getInt(2));
//                        wordMeaning.setMeaningID(resultSet.getInt(3));
//                        wordMeaning.setWordName(new WordDAO().findWordByWordID(resultSet.getInt(2)).getWordName());
//                        wordMeaning.setMeaningName(new MeaningDAO().findMeaningByMeaningID(resultSet.getInt(3)).getMeaningName());
//                        wordMeaning.setEnglishWordName(new WordDAO().findWordByWordID(resultSet.getInt(2)).getEnglishWordName());
//                        wordMeaning.setLanguageName(new WordDAO().findWordByWordID(resultSet.getInt(2)).getlanguageName());
//                    }
//                    DBUtil.closeResultSet(resultSet);
//                    DBUtil.closePreparedStatement(pStatement);
//                    DBUtil.closeConnection(connection);
//                }
//            }
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//        return wordMeaning;
//    }


    public boolean addWordMeaning(WordMeaning wordMeaning){
        boolean result = false;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(FIND_WORDMEANING_BY_WORDIDandMEANINGID);
            int saveWordID=0;
            int saveMeaningID=0;
            for(Word word : new WordDAO().findWordByWordName(wordMeaning.getWordName())) {
                if(word.getEnglishWordName().equals(wordMeaning.getEnglishWordName())&&
                        word.getlanguageName().equals(wordMeaning.getlanguageName())) {
                    saveWordID=word.getWordID();
                    pStatement.setInt(2, word.getWordID());
                    break;
                }
            }
            saveMeaningID=new MeaningDAO().findMeaningByMeaningName(wordMeaning.getMeaningName()).getMeaningID();
            pStatement.setInt(1,new MeaningDAO().findMeaningByMeaningName(wordMeaning.getMeaningName()).getMeaningID());
            ResultSet resultSet = pStatement.executeQuery();
            if(resultSet.next()){
                System.out.println("WORDMEANING已经存在了");
                DBUtil.closePreparedStatement(pStatement);
                DBUtil.closeConnection(connection);
                return false;
            }
            PreparedStatement pStatement1 = connection.prepareStatement(ADD_WORDMEANING);
            pStatement1.setInt(1, saveWordID);
            pStatement1.setInt(2,saveMeaningID);
            if(pStatement1.executeUpdate() == 1){
                result = true;
            }
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public boolean deleteWordMeaning(int wordMeaningID) {
        boolean result = false;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(DELETE_WORDMEANING);
            pStatement.setInt(1,wordMeaningID);

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
