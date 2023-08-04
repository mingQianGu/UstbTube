package edu.ustb.ustbtube.controller;

import edu.ustb.ustbtube.entity.Video;
import edu.ustb.ustbtube.service.SearchService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchServlet", value = "/search")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        String searchQuery = request.getParameter("searchQuery");
        if (searchQuery == null || searchQuery.trim().isEmpty()) {
            response.sendRedirect("index.html");
            return;
        }
        // 调用Service层进行搜索
        SearchService searchService = new SearchService();
        List<Video> videoList = searchService.searchVideoByName(searchQuery);

        // 将搜索结果存入request属性中
        request.setAttribute("videoList", videoList);

        // 转发到显示搜索结果的页面
        RequestDispatcher dispatcher = request.getRequestDispatcher("/searchResults.jsp"); // 替换成您的搜索结果页面的路径
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request,response);
    }
}
