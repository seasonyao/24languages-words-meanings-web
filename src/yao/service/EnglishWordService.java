package yao.service;

import yao.bean.EnglishWord;
import yao.bean.Word;
import yao.bean.WordMeaning;
import yao.dao.EnglishWordDAO;
import yao.dao.WordDAO;
import yao.dao.WordMeaningDAO;

import java.util.List;

public class EnglishWordService
{
    private String msg;

    public EnglishWord getEnglishWord(int englishWordID){
        return new EnglishWordDAO().findEnglishWordByEnglishWordID(englishWordID);
    }

    public boolean addEnglishWord(EnglishWord englishWord){
        if(new EnglishWordDAO().findEnglishWordByEnglishWordID(englishWord.getEnglishWordID()) != null){
            msg = "英语单词已存在";
            return false;
        } else {
            return new EnglishWordDAO().addEnglishWord(englishWord);
        }
    }

    public boolean deleteEnglishWord(EnglishWord englishWord){
        WordDAO wordDAO=new WordDAO();
        if(wordDAO.existWordsByEnglishWord(englishWord)){
            for(Word word : wordDAO.findWordsByEnglishWord(englishWord)) {
                WordMeaningDAO wordMeaningDAO=new WordMeaningDAO();
                if(wordMeaningDAO.existWordMeaningsByWord(word)){
                    for(WordMeaning wordMeaning : wordMeaningDAO.findWordMeaningsByWord(word)) {
                        wordMeaningDAO.deleteWordMeaning(wordMeaning.getWordMeaningID());
                    }
                }
                wordDAO.deleteWord(word.getWordID());
            }
        }
        return new EnglishWordDAO().deleteEnglishWord(englishWord.getEnglishWordID());
    }

    public List<EnglishWord> getAllEnglishWord(){
        return new EnglishWordDAO().findAllEnglishWords();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
