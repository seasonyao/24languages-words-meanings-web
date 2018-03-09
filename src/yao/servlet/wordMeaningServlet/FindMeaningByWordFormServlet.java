package yao.servlet.wordMeaningServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FindMeaningByWordFormServlet extends HttpServlet {
    private static final String FIND_WORD_MEANINGS_BY_WORDNAME_FORM = "findWordMeanings.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        request.getRequestDispatcher(FIND_WORD_MEANINGS_BY_WORDNAME_FORM).forward(request,response);
    }
}
