package yao.servlet.operatorServlet;

import yao.bean.Operator;
import yao.service.OperatorService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EditOperatorServlet extends HttpServlet {

    private static final String EDIT_OPERATOR_FORM= "editOperator.jsp";
    private static final String LIST_OPERATOR = "listOperators";

    private String editOperatorMsg = "";

    private Operator operator;
    private String confirmPassword;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        operator = new Operator();
        getParameters(request);
        HttpSession session = request.getSession();
        Operator editOperator = (Operator)session.getAttribute("editOperator");
        operator.setId(editOperator.getId());

        if(!validation()){
            session.setAttribute("editOperatorMsg", editOperatorMsg);
            request.getRequestDispatcher(EDIT_OPERATOR_FORM).forward(request,response);
        }
        else{
            OperatorService operatorService = new OperatorService();
            if(operatorService.edit(operator)){
                request.getRequestDispatcher(LIST_OPERATOR).forward(request,response);
            }
            else
            {
                session.setAttribute("editOperatorMsg", "服务器繁忙，请稍候重试");
                request.getRequestDispatcher(EDIT_OPERATOR_FORM).forward(request, response);
            }
        }
    }

    private void getParameters(HttpServletRequest request){
        operator.setName(request.getParameter("name"));
        operator.setPassword(request.getParameter("password"));
        confirmPassword = request.getParameter("confirmPassword");
        operator.setIsAdmin(request.getParameter("isAdmin").equals("1"));
    }

    private boolean validation(){
        if(operator.getName() == null || operator.getName().trim().equals("")){
            editOperatorMsg = "名称不能为空";
            return false;
        }
        if(operator.getPassword() == null || operator.getPassword().trim().equals("")){
            editOperatorMsg = "密码不能为空";
            return false;
        }
        if(!operator.getPassword().equals(confirmPassword)){
            editOperatorMsg = "两次密码不一致";
            return false;
        }
        return true;
    }
}
