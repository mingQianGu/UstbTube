package edu.ustb.ustbtube.controller;

import com.google.gson.GsonBuilder;
import edu.ustb.ustbtube.entity.User;
import edu.ustb.ustbtube.entity.Video;
import edu.ustb.ustbtube.service.SubmitService;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "InfoServlet", value = "/info")
public class InfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        String state = request.getParameter("state");
        if(state == null) {
            request.getRequestDispatcher("/info.html").forward(request,response);
        } else if(state.equals("select")) {
            SubmitService submitService = new SubmitService();
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            String userId = user.getUserId();

            List<Video> list = submitService.SelectVideoByUserId(userId);
            String json = new GsonBuilder().create().toJson(list);
            response.getWriter().write(json);
        }



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
