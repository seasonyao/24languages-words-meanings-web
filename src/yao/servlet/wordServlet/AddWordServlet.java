package yao.servlet.wordServlet;

import yao.bean.Word;
import yao.service.WordService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddWordServlet extends HttpServlet {

    private static final String ADD_WORD_FORM = "add_word.jsp";
    private static final String LIST_WORD = "listWords";

    private String addWordMsg = "";

    private Word word;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        word = new Word();
        getParameters(request);
        HttpSession session = request.getSession();
        if(!validation()){
            session.setAttribute("addWordMsg", addWordMsg);
            request.getRequestDispatcher(ADD_WORD_FORM).forward(request,response);
        }
        else{
            WordService wordService = new WordService();
            if(wordService.addWord(word)){
                //List<Operator> operatorList = operatorService.getAllOperators();
                request.getRequestDispatcher(LIST_WORD).forward(request,response);
            }
            else
            {
                session.setAttribute("addWordMsg", wordService.getMsg());
                request.getRequestDispatcher(ADD_WORD_FORM).forward(request, response);
            }
        }
    }

    private void getParameters(HttpServletRequest request){
        word.setWordName(request.getParameter("wordName"));
        word.setEnglishWordName(request.getParameter("englishWordName"));
        word.setLanguageName(request.getParameter("languageName"));
    }

    private boolean validation(){
        if(word.getWordName() == null || word.getWordName().trim().equals("")){
            addWordMsg = "单词名称必填";
            return false;
        }
        if(word.getEnglishWordName() == null || word.getEnglishWordName().trim().equals("")){
            addWordMsg = "对应英文单词名称必填";
            return false;
        }
        if(word.getlanguageName() == null || word.getlanguageName().trim().equals("")){
            addWordMsg = "对应语言必填";
            return false;
        }
        return true;
    }
}
