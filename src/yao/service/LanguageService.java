package yao.service;

import yao.bean.Language;
import yao.bean.Word;
import yao.bean.WordMeaning;
import yao.dao.LanguageDAO;
import yao.dao.WordDAO;
import yao.dao.WordMeaningDAO;

import java.util.List;

public class LanguageService
{
    private String msg;

    public Language getLanguage(int languageID){
        return new LanguageDAO().findLanguageByLanguageID(languageID);
    }


    public boolean addLanguage(Language language){
        if(new LanguageDAO().findLanguageByLanguageID(language.getLanguageID()) != null){
            msg = "语言已存在";
            return false;
        } else {
            return new LanguageDAO().addLanguage(language);
        }
    }

    public boolean deleteLanguage(Language language){
        WordDAO wordDAO=new WordDAO();
        if(wordDAO.existWordsByLanguage(language)){
            for(Word word : wordDAO.findWordsByLanguage(language)) {
                WordMeaningDAO wordMeaningDAO=new WordMeaningDAO();
                if(wordMeaningDAO.existWordMeaningsByWord(word)){
                    for(WordMeaning wordMeaning : wordMeaningDAO.findWordMeaningsByWord(word)) {
                        wordMeaningDAO.deleteWordMeaning(wordMeaning.getWordMeaningID());
                    }
                }
                wordDAO.deleteWord(word.getWordID());
            }
        }
        return new LanguageDAO().deleteLanguage(language.getLanguageID());
    }

    public List<Language> getAllLanguage(){
        return new LanguageDAO().findAllLanguages();
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
