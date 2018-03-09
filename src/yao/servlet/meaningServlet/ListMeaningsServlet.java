package yao.servlet.meaningServlet;

import yao.bean.Meaning;
import yao.service.MeaningService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ListMeaningsServlet extends HttpServlet {
    private static final String LIST_MEANINGS = "listMeaning.jsp";

    private MeaningService meaningService;
    private List<Meaning> meaningList;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        meaningService = new MeaningService();

        HttpSession session = request.getSession();
        meaningList = meaningService.getAllMeaning();
        session.setAttribute("meaningList",meaningList);

        request.getRequestDispatcher(LIST_MEANINGS).forward(request,response);
    }
}
