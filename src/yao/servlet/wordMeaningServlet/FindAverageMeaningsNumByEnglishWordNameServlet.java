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

public class FindAverageMeaningsNumByEnglishWordNameServlet extends HttpServlet {
    private static final String FINDWORDMEANINGS = "findWordMeanings.jsp";

    private WordMeaningService wordMeaningService;
    private String findAverageMeaningsNumByEnglishWordNameMsg = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        wordMeaningService = new WordMeaningService();
        String englishWordName=request.getParameter("englishWordName");
        HttpSession session = request.getSession();
        double averageMeaningsNum = wordMeaningService.getAverageMeaningsNumByEnglishWordName(englishWordName);
        findAverageMeaningsNumByEnglishWordNameMsg=englishWordName+"的平均meanings数为"+averageMeaningsNum;
        session.setAttribute("findAverageMeaningsNumByEnglishWordNameMsg", findAverageMeaningsNumByEnglishWordNameMsg);
        request.getRequestDispatcher(FINDWORDMEANINGS).forward(request,response);
    }

}

