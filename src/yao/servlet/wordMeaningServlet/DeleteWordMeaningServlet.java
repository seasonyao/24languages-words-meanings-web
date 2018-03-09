package yao.servlet.wordMeaningServlet;

import yao.bean.WordMeaning;
import yao.service.WordMeaningService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteWordMeaningServlet extends HttpServlet {
    private static final String LIST_WORDMEANING = "listWordMeanings";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        int wordMeaningID=Integer.parseInt(request.getParameter("wordMeaningID"));
        WordMeaningService wordMeaningService = new WordMeaningService();
        wordMeaningService.deleteWordMeaning(wordMeaningID);
        request.getRequestDispatcher(LIST_WORDMEANING).forward(request,response);
    }



//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        wordMeaning = new WordMeaning();
//        getParameters(request);
//        WordMeaningService wordMeaningService = new WordMeaningService();
//        wordMeaningService.addWordMeaning(wordMeaning);
//        request.getRequestDispatcher(LIST_WORDMEANING).forward(request,response);
//    }

//    private void getParameters(HttpServletRequest request){
//        wordMeaning.setWordName(request.getParameter("wordName"));
//        wordMeaning.setMeaningName(request.getParameter("meaningName"));
//        wordMeaning.setEnglishWordName(request.getParameter("englishWordName"));
//        wordMeaning.setLanguageName(request.getParameter("languageName"));
//    }
}
