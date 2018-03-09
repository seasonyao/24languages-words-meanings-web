package yao.servlet.wordServlet;

import yao.bean.Word;
import yao.service.WordService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ListWordsServlet extends HttpServlet {
    private static final String LIST_WORDS = "listWord.jsp";

    private WordService wordService;
    private List<Word> wordList;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        wordService = new WordService();

        HttpSession session = request.getSession();
        wordList = wordService.getAllWord();
        session.setAttribute("wordList",wordList);

        request.getRequestDispatcher(LIST_WORDS).forward(request,response);
    }
}
