package yao.servlet.englishWordServlet;

import yao.bean.EnglishWord;
import yao.service.EnglishWordService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ListEnglishWordsServlet extends HttpServlet {
    private static final String LIST_ENGLISHWORDS = "listEnglishWord.jsp";

    private EnglishWordService englishWordService;
    private List<EnglishWord> englishWordList;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        englishWordService = new EnglishWordService();

        HttpSession session = request.getSession();
        englishWordList = englishWordService.getAllEnglishWord();
        session.setAttribute("englishWordList",englishWordList);

        request.getRequestDispatcher(LIST_ENGLISHWORDS).forward(request,response);
    }
}
