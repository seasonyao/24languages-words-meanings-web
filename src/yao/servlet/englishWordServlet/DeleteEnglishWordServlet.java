package yao.servlet.englishWordServlet;

import yao.bean.EnglishWord;
import yao.service.EnglishWordService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteEnglishWordServlet extends HttpServlet {
    private static final String LIST_ENGLISHWORD = "listEnglishWords";

    private EnglishWord englishWord;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        englishWord = new EnglishWord();
        getParameters(request);
        EnglishWordService englishWordService = new EnglishWordService();
        englishWordService.deleteEnglishWord(englishWord);
        request.getRequestDispatcher(LIST_ENGLISHWORD).forward(request,response);
    }
    private void getParameters(HttpServletRequest request){
        englishWord.setEnglishWordID(Integer.parseInt(request.getParameter("englishWordID")));
        englishWord.setEnglishWordName(request.getParameter("englishWordName"));
    }
}
