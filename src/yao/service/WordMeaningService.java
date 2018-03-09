package yao.service;


import yao.bean.*;
import yao.dao.*;

import java.util.*;

public class WordMeaningService
{
    private String msg;

    public WordMeaning getWordMeaning(int wordMeaningID){
        return new WordMeaningDAO().findWordMeaningByWordMeaningID(wordMeaningID);
    }


    public List<WordMeaning> getAllWordMeaning(){
        return new WordMeaningDAO().findAllWordMeanings();
    }

    public List<WordMeaning> getWordMeaningBywordName(String wordName){
        return new WordMeaningDAO().findWordMeaningsByWordName(wordName);
    }

    public List<WordMeaning> getWordMeaningByEnglishWordName(String englishWordName){
        return new WordMeaningDAO().findWordMeaningsByEnglishWordName(englishWordName);
    }

    public double getAverageMeaningsNumByEnglishWordName(String englishWordName){
        Set languageSet = new HashSet();
        EnglishWord englishWord=new EnglishWordDAO().findEnglishWordByEnglishWordName(englishWordName);
        WordDAO wordDAO=new WordDAO();
        int meaningNumCount=0;
        for(Word word : wordDAO.findWordsByEnglishWord(englishWord)) {
            WordMeaningDAO wordMeaningDAO=new WordMeaningDAO();
            for(WordMeaning wordMeaning : wordMeaningDAO.findWordMeaningsByWord(word)) {
                meaningNumCount++;
            }
            languageSet.add(word.getlanguageName());
        }
        double averageMeaningsNum;
        if(languageSet.size()==0){
            averageMeaningsNum = 0;
        }
        else {
            averageMeaningsNum = meaningNumCount / (double)languageSet.size();
        }
        return averageMeaningsNum;
    }

    public String getMaxMeaningsNumByEnglishWordName(String englishWordName){
        Map languageMap = new HashMap<String ,Integer>();

        EnglishWord englishWord=new EnglishWordDAO().findEnglishWordByEnglishWordName(englishWordName);
        WordDAO wordDAO=new WordDAO();
        for(Word word : wordDAO.findWordsByEnglishWord(englishWord)) {
            WordMeaningDAO wordMeaningDAO=new WordMeaningDAO();
            for (WordMeaning wordMeaning : wordMeaningDAO.findWordMeaningsByWord(word)) {
                if(languageMap.containsKey(word.getlanguageName())) {
                    languageMap.put(word.getlanguageName(), 1 + (int) languageMap.get(word.getlanguageName()));
                }
                else
                    languageMap.put(word.getlanguageName(), 1);
            }
        }

        if(languageMap.size()==0){
            return "无，0";
        }
        else {
            int maxValue = 0;
            String maxKey = null;
            Iterator it = languageMap.entrySet().iterator();
            for(int i=0;i<languageMap.size();i++){
                Map.Entry entry =(Map.Entry)it.next();
                int value = Integer.parseInt(entry.getValue().toString());
                if(value > maxValue){
                    maxValue = value;
                    maxKey = entry.getKey().toString();
                }
            }

            int maxValue1 = maxValue;
            String maxKey1 = "";
            Iterator it1 = languageMap.entrySet().iterator();
            for(int i=0;i<languageMap.size();i++){
                Map.Entry entry =(Map.Entry)it1.next();
                int value = Integer.parseInt(entry.getValue().toString());
                if(value == maxValue1){
                    maxKey1 = maxKey1+" "+entry.getKey().toString();
                }
            }
            return (""+maxKey1+"，"+maxValue1);
        }
    }

    public String getMinMeaningsNumByEnglishWordName(String englishWordName){
        Map languageMap = new HashMap<String ,Integer>();

        EnglishWord englishWord=new EnglishWordDAO().findEnglishWordByEnglishWordName(englishWordName);
        WordDAO wordDAO=new WordDAO();
        for(Word word : wordDAO.findWordsByEnglishWord(englishWord)) {
            WordMeaningDAO wordMeaningDAO=new WordMeaningDAO();
            for (WordMeaning wordMeaning : wordMeaningDAO.findWordMeaningsByWord(word)) {
                if(languageMap.containsKey(word.getlanguageName())) {
                    languageMap.put(word.getlanguageName(), 1 + (int) languageMap.get(word.getlanguageName()));
                }
                else
                    languageMap.put(word.getlanguageName(), 1);
            }
        }

        if(languageMap.size()==0){
            return "无，0";
        }
        else {
            int minValue = 1000;
            String minKey = "";
            Iterator it = languageMap.entrySet().iterator();
            for(int i=0;i<languageMap.size();i++){
                Map.Entry entry =(Map.Entry)it.next();
                int value = Integer.parseInt(entry.getValue().toString());
                if(value < minValue){
                    minValue = value;
                    minKey = entry.getKey().toString();
                }
            }
            int minValue1 = minValue;
            String minKey1 = "";
            Iterator it1 = languageMap.entrySet().iterator();
            for(int i=0;i<languageMap.size();i++){
                Map.Entry entry =(Map.Entry)it1.next();
                int value = Integer.parseInt(entry.getValue().toString());
                if(value == minValue1){
                    minKey1 = minKey1+" "+entry.getKey().toString();
                }
            }
            return (""+minKey1+"，"+minValue1);
        }
    }

    public List<WordMeaning> getWordMeaningByMeaningName(String meaningName){
        return new WordMeaningDAO().findWordMeaningsByMeaningName(meaningName);
    }

    public boolean addWordMeaning(WordMeaning wordMeaning){
        WordDAO wordDAO=new WordDAO();
        MeaningDAO meaningDAO=new MeaningDAO();
        EnglishWordDAO englishWordDAO=new EnglishWordDAO();
        LanguageDAO languageDAO=new LanguageDAO();
        if(!englishWordDAO.existEnglishWordByEnglishWordName(wordMeaning.getEnglishWordName())){
            EnglishWord englishWord=new EnglishWord();
            englishWord.setEnglishWordName(wordMeaning.getEnglishWordName());
            new EnglishWordDAO().addEnglishWord(englishWord);
        }

        if(!languageDAO.existLanguageByLanguageName(wordMeaning.getlanguageName())){
            Language language=new Language();
            language.setLanguageName(wordMeaning.getlanguageName());
            new LanguageDAO().addLanguage(language);
        }
        if(!wordDAO.existWordByWordNameAndEnglishWordNameAndLanguageName(wordMeaning.getWordName(),wordMeaning.getEnglishWordName(),wordMeaning.getlanguageName())){
            Word word=new Word();
            word.setWordName(wordMeaning.getWordName());
            word.setEnglishWordName(wordMeaning.getEnglishWordName());
            word.setLanguageName(wordMeaning.getlanguageName());
            new WordDAO().addWord(word);
        }
        if(!meaningDAO.existMeaningByMeaningName(wordMeaning.getMeaningName())){
            Meaning meaning=new Meaning();
            meaning.setMeaningName(wordMeaning.getMeaningName());
            new MeaningDAO().addMeaning(meaning);
        }

        if(new WordMeaningDAO().findWordMeaningByWordMeaningID(wordMeaning.getWordMeaningID()) != null){
            msg = "对应单词-义项已存在";
            return false;
        } else {
            return new WordMeaningDAO().addWordMeaning(wordMeaning);
        }
    }

    public boolean deleteWordMeaning(int wordMeaningID){
//        int wordMeaningID=new WordMeaningDAO().findWordMeaningsByWordAndMeaning(wordMeaning.getWordName(),
//                wordMeaning.getMeaningName(),wordMeaning.getEnglishWordName(),wordMeaning.getlanguageName()).getWordMeaningID();
        return new WordMeaningDAO().deleteWordMeaning(wordMeaningID);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
