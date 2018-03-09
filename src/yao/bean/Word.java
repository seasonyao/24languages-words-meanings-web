package yao.bean;


public class Word
{
    private int wordID;
    private String wordname;
    private int englishwordID;
    private String englishwordName;
    private int languageID;
    private String languageName;
    public Word()
    {

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

    public int getEnglishWordID() {
        return englishwordID;
    }

    public void setEnglishwordID(int englishwordID) {
        this.englishwordID = englishwordID;
    }
    public String getEnglishWordName() {
        return englishwordName;
    }

    public void setEnglishWordName(String englishwordName) {
        this.englishwordName = englishwordName;
    }
    public int getLanguageID() {
        return languageID;
    }

    public void setLanguageID(int languageID) {
        this.languageID = languageID;
    }

    public String getlanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }
}
