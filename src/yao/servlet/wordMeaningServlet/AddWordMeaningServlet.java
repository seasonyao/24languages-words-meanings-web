package yao.servlet.wordMeaningServlet;

import yao.bean.WordMeaning;
import yao.service.WordMeaningService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddWordMeaningServlet extends HttpServlet {

    private static final String ADD_WORDMEANING_FORM = "add_wordmeaning.jsp";
    private static final String LIST_WORDMEANING = "listWordMeanings";

    private String addWordMeaningMsg = "";
    private WordMeaning wordMeaning1;
    private WordMeaning wordMeaning2;
    private WordMeaning wordMeaning3;
    private WordMeaning wordMeaning4;
    private WordMeaning wordMeaning5;
    int flag2=0;
    int flag3=0;
    int flag4=0;
    int flag5=0;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        wordMeaning1 = new WordMeaning();
        wordMeaning2 = new WordMeaning();
        wordMeaning3 = new WordMeaning();
        wordMeaning4 = new WordMeaning();
        wordMeaning5 = new WordMeaning();
        getParameters(request);
        HttpSession session = request.getSession();
        if(!validation()){
            session.setAttribute("addWordMeaningMsg", addWordMeaningMsg);
            request.getRequestDispatcher(ADD_WORDMEANING_FORM).forward(request,response);
        }
        else{
            WordMeaningService wordMeaningService = new WordMeaningService();
            boolean flag=wordMeaningService.addWordMeaning(wordMeaning1);
            if(flag2==1){
                flag=flag&&wordMeaningService.addWordMeaning(wordMeaning2);
            }
            if(flag3==1){
                flag=flag&&wordMeaningService.addWordMeaning(wordMeaning3);
            }
            if(flag4==1){
                flag=flag&&wordMeaningService.addWordMeaning(wordMeaning4);
            }
            if(flag5==1){
                flag=flag&&wordMeaningService.addWordMeaning(wordMeaning5);
            }

            System.out.println(wordMeaning1.getMeaningName());
            System.out.println(flag2);
            System.out.println(wordMeaning2.getMeaningName());
            System.out.println(flag3);
            System.out.println(wordMeaning3.getMeaningName());
            System.out.println(flag4);
            System.out.println(wordMeaning4.getMeaningName());
            System.out.println(flag5);
            System.out.println(wordMeaning5.getMeaningName());
            if(flag){
                //request.getRequestDispatcher(LIST_WORDMEANING).forward(request,response);
                request.getRequestDispatcher(ADD_WORDMEANING_FORM).forward(request,response);
            }
            else
            {
                session.setAttribute("addWordMeaningMsg", wordMeaningService.getMsg());
                request.getRequestDispatcher(ADD_WORDMEANING_FORM).forward(request, response);
            }
        }
    }

    private void getParameters(HttpServletRequest request){
        wordMeaning1.setWordName(request.getParameter("wordName"));
        wordMeaning1.setMeaningName(request.getParameter("meaningName1"));
        wordMeaning1.setEnglishWordName(request.getParameter("englishWordName"));
        wordMeaning1.setLanguageName(request.getParameter("languageName"));
        if(!(request.getParameter("meaningName2")== null||request.getParameter("meaningName2").trim().equals(""))){
            flag2=1;
            wordMeaning2.setWordName(request.getParameter("wordName"));
            wordMeaning2.setMeaningName(request.getParameter("meaningName2"));
            wordMeaning2.setEnglishWordName(request.getParameter("englishWordName"));
            wordMeaning2.setLanguageName(request.getParameter("languageName"));
        }
        if(!(request.getParameter("meaningName3")== null||request.getParameter("meaningName3").trim().equals(""))){
            if (flag2 == 0) {
                flag2=1;
                wordMeaning2.setWordName(request.getParameter("wordName"));
                wordMeaning2.setMeaningName(request.getParameter("meaningName3"));
                wordMeaning2.setEnglishWordName(request.getParameter("englishWordName"));
                wordMeaning2.setLanguageName(request.getParameter("languageName"));
            }
            else {
                flag3 = 1;
                wordMeaning3.setWordName(request.getParameter("wordName"));
                wordMeaning3.setMeaningName(request.getParameter("meaningName3"));
                wordMeaning3.setEnglishWordName(request.getParameter("englishWordName"));
                wordMeaning3.setLanguageName(request.getParameter("languageName"));
            }
        }
        if(!(request.getParameter("meaningName4")== null||request.getParameter("meaningName4").trim().equals(""))){
            if (flag2 == 0) {
                flag2=1;
                wordMeaning2.setWordName(request.getParameter("wordName"));
                wordMeaning2.setMeaningName(request.getParameter("meaningName4"));
                wordMeaning2.setEnglishWordName(request.getParameter("englishWordName"));
                wordMeaning2.setLanguageName(request.getParameter("languageName"));
            }
            else {
                if(flag3==0){
                    flag3 = 1;
                    wordMeaning3.setWordName(request.getParameter("wordName"));
                    wordMeaning3.setMeaningName(request.getParameter("meaningName4"));
                    wordMeaning3.setEnglishWordName(request.getParameter("englishWordName"));
                    wordMeaning3.setLanguageName(request.getParameter("languageName"));
                }
                else {
                    flag4 = 1;
                    wordMeaning4.setWordName(request.getParameter("wordName"));
                    wordMeaning4.setMeaningName(request.getParameter("meaningName4"));
                    wordMeaning4.setEnglishWordName(request.getParameter("englishWordName"));
                    wordMeaning4.setLanguageName(request.getParameter("languageName"));
                }
            }
        }
        if(!(request.getParameter("meaningName5")== null||request.getParameter("meaningName5").trim().equals(""))){
            if (flag2 == 0) {
                flag2=1;
                wordMeaning2.setWordName(request.getParameter("wordName"));
                wordMeaning2.setMeaningName(request.getParameter("meaningName5"));
                wordMeaning2.setEnglishWordName(request.getParameter("englishWordName"));
                wordMeaning2.setLanguageName(request.getParameter("languageName"));
            }
            else {
                if(flag3==0){
                    flag3 = 1;
                    wordMeaning3.setWordName(request.getParameter("wordName"));
                    wordMeaning3.setMeaningName(request.getParameter("meaningName5"));
                    wordMeaning3.setEnglishWordName(request.getParameter("englishWordName"));
                    wordMeaning3.setLanguageName(request.getParameter("languageName"));
                }
                else {
                    if(flag4==0) {
                        flag4 = 1;
                        wordMeaning4.setWordName(request.getParameter("wordName"));
                        wordMeaning4.setMeaningName(request.getParameter("meaningName5"));
                        wordMeaning4.setEnglishWordName(request.getParameter("englishWordName"));
                        wordMeaning4.setLanguageName(request.getParameter("languageName"));
                    }
                    else{
                        flag5=1;
                        wordMeaning5.setWordName(request.getParameter("wordName"));
                        wordMeaning5.setMeaningName(request.getParameter("meaningName5"));
                        wordMeaning5.setEnglishWordName(request.getParameter("englishWordName"));
                        wordMeaning5.setLanguageName(request.getParameter("languageName"));
                    }
                }
            }
        }
    }


    private boolean validation(){
        if(wordMeaning1.getWordName() == null || wordMeaning1.getWordName().trim().equals("")){
            addWordMeaningMsg = "对应单词名称必填";
            return false;
        }
        if(wordMeaning1.getEnglishWordName() == null || wordMeaning1.getEnglishWordName().trim().equals("")){
            addWordMeaningMsg = "对应英文单词名称必填";
            return false;
        }
        if(wordMeaning1.getlanguageName() == null || wordMeaning1.getlanguageName().trim().equals("")){
            addWordMeaningMsg = "对应语言必填";
            return false;
        }
        if(wordMeaning1.getMeaningName() == null || wordMeaning1.getMeaningName().trim().equals("")){
            addWordMeaningMsg = "对应义项必填";
            return false;
        }
        return true;
    }
}
