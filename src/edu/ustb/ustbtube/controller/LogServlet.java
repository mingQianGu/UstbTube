package edu.ustb.ustbtube.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import edu.ustb.ustbtube.entity.User;
import edu.ustb.ustbtube.service.LogService;
import edu.ustb.ustbtube.utils.CheckCodeUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Random;

@WebServlet(name = "LogServlet", value = "/log")
public class LogServlet extends HttpServlet {

    LogService logService = new LogService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");

        String State = request.getParameter("state");

        if(State == null) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            request.getRequestDispatcher("/log.html").forward(request, response);
        } else if(State.equals("login")) {
            String account = request.getParameter("account");
            String passWord = request.getParameter("passWord");

            String result = logService.logIn(account, passWord);

            if(result.equals("true")) {

                User user = new User();
                user.setUserId(account);
                user.setPassWord(passWord);

                user = logService.setUser(user);
                request.getSession(true).setAttribute("user", user);

                response.sendRedirect("index");
            } else {
                response.sendRedirect("/UstbTube/log");
            }
        } else if(State.equals("sendCode")) {

            String phone = request.getParameter("phone");

            if(logService.getUserByPhone(phone) == null) {
                String code = CheckCodeUtil.getCode(6);
                String result = logService.sendSms(phone, code);
                ObjectMapper objMapper = new ObjectMapper();
                Map<String, String> rst = objMapper.readValue(result, Map.class);
                if("OK".equals(rst.get("Code"))){
                    request.getSession(true).setAttribute("smsCode", code);
                    response.getWriter().print(true);
                } else{
                    response.getWriter().print(false);
                }
            }

        } else if(State.equals("register")) {
            HttpSession session = request.getSession();
            String smsCode = (String) session.getAttribute("smsCode");

            String code = request.getParameter("code");
            if(code.equals(smsCode)){
                String nickName = request.getParameter("nickName");
                if(logService.checkNickName(nickName) == 0){
                    String phone = request.getParameter("phone");
                    String passWord = request.getParameter("passWord");
                    User user = new User();

                    Random random = new Random();
                    int id = random.nextInt(99999999);
                    String userId = id + "";

                    user.setUserId(userId);
                    user.setPassWord(passWord);
                    user.setNickName(nickName);
                    user.setPermission(0);
                    user.setPhone(phone);
                    user.setImg("rsc/images/head/user.png");

                    int result = logService.insertUser(user);

                    Gson gson = new Gson();
                    String json = gson.toJson(user);

                    response.setContentType("application/json");

                    response.getWriter().print(json);
                } else {
                    Gson gson = new Gson();
                    String json = gson.toJson("name");

                    response.setContentType("application/json");
                    response.getWriter().print(json);
                }
            }else {
                Gson gson = new Gson();
                String json = gson.toJson("code");

                response.setContentType("application/json");
                response.getWriter().print(json);
            }
        }
        else if(State.equals("get")) {

            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            Gson gson = new Gson();
            String json = gson.toJson(user);

            response.setContentType("application/json");
            if(user != null) {
                PrintWriter out = response.getWriter();
                out.print(json);
            } else {
                response.getWriter().print("false");
            }
        } else if(State.equals("exit")) {
            request.getSession(true).setAttribute("user", null);

            response.sendRedirect("index");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
