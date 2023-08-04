package edu.ustb.ustbtube.controller;

import com.google.gson.Gson;
import edu.ustb.ustbtube.entity.Video;
import edu.ustb.ustbtube.service.IndexService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "IndexServlet", value = "/index")
public class IndexServlet extends HttpServlet {

    IndexService indexService = new IndexService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");

        String State = request.getParameter("state");


        if(State == null) {
            request.getRequestDispatcher("/index.html").forward(request, response);
        }else if(State.equals("hottest")) {

            Video hottest = indexService.getHottest();

            Gson gson = new Gson();
            String json = gson.toJson(hottest);

            response.setContentType("application/json");
            if(hottest != null) {
                PrintWriter out = response.getWriter();
                out.print(json);
            } else {
                response.getWriter().print("false");
            }

        } else if(State.equals("guess")) {

            String Count = request.getParameter("count");
            int count = Integer.parseInt(Count);
            ArrayList<Video> guesses = indexService.getGuess(count);

            Gson gson = new Gson();
            String json = gson.toJson(guesses);

            response.setContentType("application/json");
            if(guesses != null) {
                PrintWriter out = response.getWriter();
                out.print(json);
            } else {
                response.getWriter().print("false");
            }
        } else if(State.equals("new")) {

            ArrayList<Video> news = indexService.getNew();

            Gson gson = new Gson();
            String json = gson.toJson(news);

            response.setContentType("application/json");
            if(news != null) {
                PrintWriter out = response.getWriter();
                out.print(json);
            } else {
                response.getWriter().print("false");
            }
        } else if(State.equals("topNews")) {
            ArrayList<Video> topNews = indexService.getTopNews();

            Gson gson = new Gson();
            String json = gson.toJson(topNews);

            response.setContentType("application/json");
            if(topNews != null) {
                PrintWriter out = response.getWriter();
                out.print(json);
            } else {
                response.getWriter().print("false");
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
