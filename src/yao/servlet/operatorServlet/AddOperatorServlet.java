package yao.servlet.operatorServlet;

import yao.bean.Operator;
import yao.service.OperatorService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddOperatorServlet extends HttpServlet {

    private static final String ADD_OPERATOR_FORM = "addOperator.jsp";
    //private static final String Login = "index.jsp";
    private static final String LIST_OPERATOR = "listOperators";

    private String addOperatorMsg = "";

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
        if(!validation()){
            session.setAttribute("addOperatorMsg", addOperatorMsg);
            request.getRequestDispatcher(ADD_OPERATOR_FORM).forward(request,response);
        }
        else{
            OperatorService operatorService = new OperatorService();
            if(operatorService.addOperator(operator)){
                //List<Operator> operatorList = operatorService.getAllOperators();
                request.getRequestDispatcher(LIST_OPERATOR).forward(request,response);
            }
            else
            {
                session.setAttribute("addOperatorMsg", operatorService.getMsg());
                request.getRequestDispatcher(ADD_OPERATOR_FORM).forward(request, response);
            }
        }
    }

    private void getParameters(HttpServletRequest request){
        operator.setId(request.getParameter("id"));
        operator.setName(request.getParameter("name"));
        operator.setPassword(request.getParameter("password"));
        confirmPassword = request.getParameter("confirmPassword");
        operator.setIsAdmin(request.getParameter("isAdmin").equals("1"));
    }

    private boolean validation(){
        if(operator.getId() == null || operator.getId().trim().equals("")){
            addOperatorMsg = "用户名必填";
            return false;
        }
        if(operator.getId().length() > 8){
            addOperatorMsg = "用户名长度不能超过8";
            return false;
        }
        if(operator.getName() == null || operator.getName().trim().equals("")){
            addOperatorMsg = "名称不能为空";
            return false;
        }
        if(operator.getPassword() == null || operator.getPassword().trim().equals("")){
            addOperatorMsg = "密码不能为空";
            return false;
        }
        if(!operator.getPassword().equals(confirmPassword)){
            addOperatorMsg = "两次密码不一致";
            return false;
        }
        return true;
    }
}
