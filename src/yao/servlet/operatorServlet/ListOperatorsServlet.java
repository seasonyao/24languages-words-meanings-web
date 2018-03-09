package yao.servlet.operatorServlet;

import yao.bean.Operator;
import yao.service.OperatorService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


public class ListOperatorsServlet extends HttpServlet {
    private static final String LIST_OPERATORS = "listOperator.jsp";

    private OperatorService operatorService;
    private List<Operator> operatorList;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        operatorService = new OperatorService();

        HttpSession session = request.getSession();

//        operatorList = (List<Operator>)session.getAttribute("operatorList");
//
//        if(operatorList == null){
//            operatorList = operatorService.getAllOperators();
//            session.setAttribute("operatorList",operatorList);
//        }
        operatorList = operatorService.getAllOperators();
        session.setAttribute("operatorList",operatorList);

        request.getRequestDispatcher(LIST_OPERATORS).forward(request,response);
    }
}
