package yao.servlet.meaningServlet;

import yao.bean.Meaning;
import yao.service.MeaningService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class AddMeaningServlet extends HttpServlet {

    private static final String ADD_MEANING_FORM = "add_meaning.jsp";
    private static final String LIST_MEANING = "listMeanings";

    private String addMeaningMsg = "";

    private Meaning meaning;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        meaning = new Meaning();
        getParameters(request);
        HttpSession session = request.getSession();
        if(!validation()){
            session.setAttribute("addMeaningMsg", addMeaningMsg);
            request.getRequestDispatcher(ADD_MEANING_FORM).forward(request,response);
        }
        else{
            MeaningService meaningService = new MeaningService();
            if(meaningService.addMeaning(meaning)){
                request.getRequestDispatcher(LIST_MEANING).forward(request,response);
            }
            else
            {
                session.setAttribute("addMeaningMsg", meaningService.getMsg());
                request.getRequestDispatcher(ADD_MEANING_FORM).forward(request, response);
            }
        }
    }

    private void getParameters(HttpServletRequest request){
        meaning.setMeaningName(request.getParameter("meaningName"));
    }

    private boolean validation(){
        if(meaning.getMeaningName() == null || meaning.getMeaningName().trim().equals("")){
            addMeaningMsg = "义项名称不能为空";
            return false;
        }
        return true;
    }
}
