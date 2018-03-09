package yao.servlet.languageServlet;

import yao.bean.Language;
import yao.service.LanguageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteLanguageServlet extends HttpServlet {
    private static final String LIST_LANGUAGE = "listLanguages";

    private Language language;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        language = new Language();
        getParameters(request);
        LanguageService languageService = new LanguageService();
        languageService.deleteLanguage(language);
        request.getRequestDispatcher(LIST_LANGUAGE).forward(request,response);
    }
    private void getParameters(HttpServletRequest request){
        language.setLanguageID(Integer.parseInt(request.getParameter("languageID")));
        language.setLanguageName(request.getParameter("languageName"));
    }
}
