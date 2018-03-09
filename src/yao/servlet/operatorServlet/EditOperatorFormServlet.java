package yao.servlet.operatorServlet;

import yao.bean.Operator;
import yao.service.OperatorService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class EditOperatorFormServlet extends HttpServlet {
    private static final String EDIT_OPERATOR_FORM= "editOperator.jsp";

    private String operatorID;
    private Operator editOperator;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        operatorID = request.getParameter("operatorID");
        editOperator = new OperatorService().getOperator(operatorID);
        HttpSession session = request.getSession();
        session.setAttribute("editOperator" , editOperator);
        request.getRequestDispatcher(EDIT_OPERATOR_FORM).forward(request,response);
    }
}
