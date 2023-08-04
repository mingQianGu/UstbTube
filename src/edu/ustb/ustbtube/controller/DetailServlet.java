package edu.ustb.ustbtube.controller;

import com.google.gson.Gson;
import edu.ustb.ustbtube.entity.Star;
import edu.ustb.ustbtube.entity.User;
import edu.ustb.ustbtube.entity.Video;
import edu.ustb.ustbtube.service.DetailService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "DetailServlet", value = "/detail")
public class DetailServlet extends HttpServlet {

    DetailService detailService = new DetailService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");

        String State = request.getParameter("state");

        if(State == null) {
            request.getRequestDispatcher("/detail.html").forward(request, response);
        } else if(State.equals("select")) {
            String av = request.getParameter("av");
            Video video = detailService.getVideoById(av);

            int result = detailService.updateVideoOne(video);

            Gson gson = new Gson();
            String json = gson.toJson(video);

            response.setContentType("application/json");
            if(video != null) {
                PrintWriter out = response.getWriter();
                out.print(json);
            } else {
                response.getWriter().print("false");
            }

        } else if(State.equals("about")) {
            String av = request.getParameter("av");
            Video video = detailService.getVideoById(av);

            ArrayList<Video> videos = new ArrayList<>();

            videos = detailService.getAbout(video);

            Gson gson = new Gson();
            String json = gson.toJson(videos);

            response.setContentType("application/json");
            if(video != null) {
                PrintWriter out = response.getWriter();
                out.print(json);
            } else {
                response.getWriter().print("false");
            }
        } else if(State.equals("userAbout")) {
            String av = request.getParameter("av");
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            if(user == null) {
                response.getWriter().print("false");
            } else {

                int result = detailService.getUserAbout(av, user.getUserId());

                Gson gson = new Gson();
                String json = gson.toJson(result);

                response.setContentType("application/json");

                PrintWriter out = response.getWriter();
                out.print(json);

            }
        } else if(State.equals("star")) {
            String av = request.getParameter("av");
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            if(user == null) {
                response.getWriter().print("false");
            } else {

                int result = detailService.changeStar(av, user.getUserId());

                Gson gson = new Gson();
                String json = gson.toJson(result);

                response.setContentType("application/json");

                PrintWriter out = response.getWriter();
                out.print(json);

            }
        } else if(State.equals("favorite")) {
            String av = request.getParameter("av");
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            if(user == null) {
                response.getWriter().print("false");
            } else {

                int result = detailService.changeFavorites(av, user.getUserId());

                Gson gson = new Gson();
                String json = gson.toJson(result);

                response.setContentType("application/json");

                PrintWriter out = response.getWriter();
                out.print(json);

            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
