package yao.service;

import yao.bean.Meaning;
import yao.bean.WordMeaning;
import yao.dao.MeaningDAO;
import yao.dao.WordMeaningDAO;

import java.util.List;

public class MeaningService
{

    private String msg;

    public Meaning getMeaning(int meaningID){
        return new MeaningDAO().findMeaningByMeaningID(meaningID);
    }

    public boolean addMeaning(Meaning meaning){
        if(new MeaningDAO().findMeaningByMeaningID(meaning.getMeaningID()) != null){
            msg = "义项已存在";
            return false;
        } else {
            return new MeaningDAO().addMeaning(meaning);
        }
    }

    public boolean deleteMeaning(Meaning meaning){
        WordMeaningDAO wordMeaningDAO=new WordMeaningDAO();

        if(wordMeaningDAO.existWordMeaningsByMeaning(meaning)){
            for(WordMeaning wordMeaning : wordMeaningDAO.findWordMeaningsByMeaning(meaning)) {
                wordMeaningDAO.deleteWordMeaning(wordMeaning.getWordMeaningID());
            }
        }
        return new MeaningDAO().deleteMeaning(meaning.getMeaningID());
    }

    public List<Meaning> getAllMeaning(){
        return new MeaningDAO().findAllMeanings();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
