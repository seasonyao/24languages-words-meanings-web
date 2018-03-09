package yao.servlet.languageServlet;

import yao.bean.Language;
import yao.service.LanguageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class AddLanguageServlet extends HttpServlet {

    private static final String ADD_LANGUAGE_FORM = "add_englishwordandlanguage.jsp";
    private static final String LIST_LANGUAGE = "listLanguages";

    private String addLanguageMsg = "";

    private Language language;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        language = new Language();
        getParameters(request);
        HttpSession session = request.getSession();
        if(!validation()){
            session.setAttribute("addLanguageMsg", addLanguageMsg);
            request.getRequestDispatcher(ADD_LANGUAGE_FORM).forward(request,response);
        }
        else{
            LanguageService languageService = new LanguageService();
            if(languageService.addLanguage(language)){
                request.getRequestDispatcher(LIST_LANGUAGE).forward(request,response);
            }
            else
            {
                session.setAttribute("addLanguageMsg", languageService.getMsg());
                request.getRequestDispatcher(ADD_LANGUAGE_FORM).forward(request, response);
            }
        }
    }

    private void getParameters(HttpServletRequest request){
        language.setLanguageName(request.getParameter("languageName"));
    }

    private boolean validation(){
        if(language.getLanguageName() == null || language.getLanguageName().trim().equals("")){
            addLanguageMsg = "语言名称不能为空";
            return false;
        }
        return true;
    }
}
