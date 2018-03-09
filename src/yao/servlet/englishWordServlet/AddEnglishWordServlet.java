package yao.servlet.englishWordServlet;

import yao.bean.EnglishWord;
import yao.service.EnglishWordService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class AddEnglishWordServlet extends HttpServlet {

    private static final String ADD_ENGLISHWORD_FORM = "add_englishwordandlanguage.jsp";
    private static final String LIST_ENGLISHWORD = "listEnglishWords";

    private String addEnglishWordMsg = "";

    private EnglishWord englishWord;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        englishWord = new EnglishWord();
        getParameters(request);
        HttpSession session = request.getSession();
        if(!validation()){
            session.setAttribute("addEnglishWordMsg", addEnglishWordMsg);
            request.getRequestDispatcher(ADD_ENGLISHWORD_FORM).forward(request,response);
        }
        else{
            EnglishWordService englishWordService = new EnglishWordService();
            if(englishWordService.addEnglishWord(englishWord)){
                request.getRequestDispatcher(LIST_ENGLISHWORD).forward(request,response);
            }
            else
            {
                session.setAttribute("addEnglishWordMsg", englishWordService.getMsg());
                request.getRequestDispatcher(ADD_ENGLISHWORD_FORM).forward(request, response);
            }
        }
    }

    private void getParameters(HttpServletRequest request){
        englishWord.setEnglishWordName(request.getParameter("englishWordName"));
    }

    private boolean validation(){
        if(englishWord.getEnglishWordName() == null || englishWord.getEnglishWordName().trim().equals("")){
            addEnglishWordMsg = "英文单词名称不能为空";
            return false;
        }
        return true;
    }
}
