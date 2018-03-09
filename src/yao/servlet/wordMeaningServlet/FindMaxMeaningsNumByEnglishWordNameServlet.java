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

public class FindMaxMeaningsNumByEnglishWordNameServlet extends HttpServlet {
    private static final String FINDWORDMEANINGS = "findWordMeanings.jsp";

    private WordMeaningService wordMeaningService;
    private String findMaxMeaningsNumByEnglishWordNameMsg = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        wordMeaningService = new WordMeaningService();
        String englishWordName=request.getParameter("englishWordName");
        HttpSession session = request.getSession();
        String maxMeanings = wordMeaningService.getMaxMeaningsNumByEnglishWordName(englishWordName);
        findMaxMeaningsNumByEnglishWordNameMsg=englishWordName+"的最大义项的语言和数量分比别为"+maxMeanings;
        session.setAttribute("findMaxMeaningsNumByEnglishWordNameMsg", findMaxMeaningsNumByEnglishWordNameMsg);
        request.getRequestDispatcher(FINDWORDMEANINGS).forward(request,response);
    }

}

