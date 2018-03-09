package yao.servlet;

import yao.bean.Operator;
import yao.service.OperatorService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private Operator operator;
    private String msg;

    private static final String INDEX = "index.jsp";
    private static final String MAIN = "main.jsp";

    private OperatorService operatorService;
    public LoginServlet(){
        super();
        operatorService = new OperatorService();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String id = request.getParameter("operatorID");
        String password = request.getParameter("operatorPwd");
        operator = new Operator();
        operator.setId(id);
        operator.setPassword(password);

        HttpSession session = request.getSession();
        if(validation()){
            operator = operatorService.login(operator);
            if(operator == null){
                session.setAttribute("loginMsg","用户名或密码错误");
                response.sendRedirect(INDEX);
            }
            else
            {
                session.setAttribute("loginOperator",operator);
                request.getRequestDispatcher(MAIN).forward(request,response);
            }
        }
        else{
            session.setAttribute("loginMsg",msg);
            response.sendRedirect(INDEX);
        }

    }

    private boolean validation() {
        if(operator.getId() == null || operator.getId().trim().equals("")){
            msg = "用户名必填";
            return false;
        }
        if(operator.getPassword() == null || operator.getPassword().trim().equals("")){
            msg = "密码不能为空";
            return false;
        }
        return true;
    }
}
