package yao.servlet.meaningServlet;

import yao.bean.Meaning;
import yao.service.MeaningService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteMeaningServlet extends HttpServlet {
    private static final String LIST_MEANING = "listMeanings";

    private Meaning meaning;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        meaning = new Meaning();
        getParameters(request);
        MeaningService meaningService = new MeaningService();
        meaningService.deleteMeaning(meaning);
        request.getRequestDispatcher(LIST_MEANING).forward(request,response);
    }
    private void getParameters(HttpServletRequest request){
        meaning.setMeaningID(Integer.parseInt(request.getParameter("meaningID")));
        meaning.setMeaningName(request.getParameter("meaningName"));
    }
}
