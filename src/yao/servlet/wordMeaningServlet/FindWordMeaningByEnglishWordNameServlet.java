package yao.servlet.wordMeaningServlet;

import yao.bean.WordMeaning;
import yao.service.WordMeaningService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class FindWordMeaningByEnglishWordNameServlet extends HttpServlet {
    private static final String LIST_WORDMEANINGBYENGLISHWORDNAME = "listWordMeaning.jsp";

    private WordMeaningService wordMeaningService;
    private List<WordMeaning> wordMeaningList;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        wordMeaningService = new WordMeaningService();
        String englishWordName=request.getParameter("englishWordName");
        HttpSession session = request.getSession();
        wordMeaningList = wordMeaningService.getWordMeaningByEnglishWordName(englishWordName);
        session.setAttribute("wordMeaningList",wordMeaningList);

        request.getRequestDispatcher(LIST_WORDMEANINGBYENGLISHWORDNAME).forward(request,response);
    }

}