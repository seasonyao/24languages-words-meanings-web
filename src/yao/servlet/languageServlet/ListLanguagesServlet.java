package yao.servlet.languageServlet;

import yao.bean.Language;
import yao.service.LanguageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ListLanguagesServlet extends HttpServlet {
    private static final String LIST_LANGUAGES = "listLanguage.jsp";

    private LanguageService languageService;
    private List<Language> languageList;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        languageService = new LanguageService();

        HttpSession session = request.getSession();
        languageList = languageService.getAllLanguage();
        session.setAttribute("languageList",languageList);

        request.getRequestDispatcher(LIST_LANGUAGES).forward(request,response);
    }
}
