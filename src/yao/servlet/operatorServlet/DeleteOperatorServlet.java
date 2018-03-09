package yao.servlet.operatorServlet;

import yao.service.OperatorService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteOperatorServlet extends HttpServlet {
    private static final String LIST_OPERATOR = "listOperators";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String thisID=request.getParameter("operatorID");
        OperatorService operatorService = new OperatorService();
        operatorService.delete(thisID);
        request.getRequestDispatcher(LIST_OPERATOR).forward(request,response);

    }


}
