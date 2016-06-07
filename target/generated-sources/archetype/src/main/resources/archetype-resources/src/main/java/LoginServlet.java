#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 25065 on 2016/6/6.
 */
public class LoginServlet extends HttpServlet {
    public LoginServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String username=req.getParameter("username");
        String password=req.getParameter("password");

        Subject subject= SecurityUtils.getSubject();
//        用户名，密码验证
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
       //可能是保存到session里面

        String emsg=null;
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            emsg="用户名出错!";
        }catch (IncorrectCredentialsException e) {
            emsg="用户密码出错!";
        }catch (AuthenticationException e) {
            emsg="其他异常："+e.getMessage();
        }

        if (emsg!=null){
            req.setAttribute("emsg",emsg);
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req,resp);
        }else {
            resp.sendRedirect(req.getContextPath()+"/");
        }
    }
}
