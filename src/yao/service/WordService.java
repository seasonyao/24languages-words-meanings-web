package yao.service;

import yao.bean.Word;
import yao.bean.EnglishWord;
import yao.bean.Language;
import yao.bean.WordMeaning;
import yao.dao.EnglishWordDAO;
import yao.dao.LanguageDAO;
import yao.dao.WordDAO;
import yao.dao.WordMeaningDAO;

import java.util.List;

public class WordService
{
    private String msg;
    public boolean addWord(Word word){
        EnglishWordDAO englishWordDAO=new EnglishWordDAO();
        LanguageDAO languageDAO=new LanguageDAO();
        if(!englishWordDAO.existEnglishWordByEnglishWordName(word.getEnglishWordName())){
            EnglishWord englishWord=new EnglishWord();
            englishWord.setEnglishWordName(word.getEnglishWordName());
            new EnglishWordDAO().addEnglishWord(englishWord);
        }

        if(!languageDAO.existLanguageByLanguageName(word.getlanguageName())){
            Language language=new Language();
            language.setLanguageName(word.getlanguageName());
            new LanguageDAO().addLanguage(language);
        }

        if(new WordDAO().findWordByWordID(word.getWordID()) != null){
            msg = "单词已存在";
            return false;
        } else {
            return new WordDAO().addWord(word);
        }
    }

    public boolean edit(Word word){
        return new WordDAO().updateWord(word);
    }

    public Word getWord(int wordID){
        return new WordDAO().findWordByWordID(wordID);
    }

    public boolean deleteWord(Word word){
        WordMeaningDAO wordMeaningDAO=new WordMeaningDAO();

        if(wordMeaningDAO.existWordMeaningsByWord(word)){
            for(WordMeaning wordMeaning : wordMeaningDAO.findWordMeaningsByWord(word)) {
                wordMeaningDAO.deleteWordMeaning(wordMeaning.getWordMeaningID());
            }
        }

        return new WordDAO().deleteWord(word.getWordID());
    }

    public List<Word> getAllWord(){
        return new WordDAO().findAllWords();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
