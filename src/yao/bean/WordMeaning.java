package yao.bean;


public class WordMeaning
{
    private int wordMeaningID;
    private int wordID;
    private int meaningID;
    private String wordname;
    private String meaningName;
    private String englishwordName;
    private String languageName;


    public WordMeaning()
    {

    }

    public int getWordMeaningID() {
        return wordMeaningID;
    }

    public void setWordMeaningID(int wordMeaningID) {
        this.wordMeaningID = wordMeaningID;
    }

    public int getWordID() {
        return wordID;
    }

    public void setWordID(int wordID) {
        this.wordID = wordID;
    }

    public String getWordName() {
        return wordname;
    }

    public void setWordName(String wordName) {
        this.wordname = wordName;
    }

    public int getMeaningID() {
        return meaningID;
    }

    public void setMeaningID(int meaningID) {
        this.meaningID = meaningID;
    }
    public String getMeaningName() {
        return meaningName;
    }

    public void setMeaningName(String meaningName) {
        this.meaningName = meaningName;
    }

    public String getEnglishWordName() {
        return englishwordName;
    }

    public void setEnglishWordName(String englishWordName) {
        this.englishwordName = englishWordName;
    }

    public String getlanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

}
