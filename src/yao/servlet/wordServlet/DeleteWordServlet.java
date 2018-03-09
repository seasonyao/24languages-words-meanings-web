package yao.servlet.wordServlet;

import yao.bean.Word;
import yao.service.WordService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteWordServlet extends HttpServlet {
    private static final String LIST_WORD = "listWords";

    private Word word;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        word = new Word();
        getParameters(request);
        WordService wordService = new WordService();
        wordService.deleteWord(word);
        request.getRequestDispatcher(LIST_WORD).forward(request,response);
    }
    private void getParameters(HttpServletRequest request){
        word.setWordID(Integer.parseInt(request.getParameter("wordID")));
        word.setWordName(request.getParameter("wordName"));
        word.setWordName(request.getParameter("englishWordName"));
        word.setWordName(request.getParameter("languageName"));
    }
}
