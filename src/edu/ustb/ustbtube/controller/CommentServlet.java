package edu.ustb.ustbtube.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import edu.ustb.ustbtube.dao.CommentMapper;
import edu.ustb.ustbtube.entity.Comment;
import edu.ustb.ustbtube.entity.User;
import edu.ustb.ustbtube.service.CommentService;
import edu.ustb.ustbtube.utils.CheckCodeUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "CommentServlet", value = "/comment")
public class CommentServlet extends HttpServlet {

    private CommentService commentService;

    @Override
    public void init() throws ServletException {
        super.init();
        // 在init()方法中初始化CommentService实例
        commentService = new CommentService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String videoId = request.getParameter("videoId");

        // 调用CommentService的方法获取此视频的所有评论
        List<Comment> comments = commentService.getAllComments(videoId);

        // 使用Jackson库将评论列表转换为JSON字符串
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(comments);


        // 设置响应的Content-Type为application/json
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // 将JSON字符串作为响应发送给客户端
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if(user == null) {
            response.getWriter().print("false");
        } else {
            String av = request.getParameter("av");
            String comment = request.getParameter("comment");
            String code = CheckCodeUtil.getCode(6);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            String time = formatter.format(date);

            if(comment.equals("")){
                Gson gson = new Gson();
                String json = gson.toJson(2);

                response.setContentType("application/json");

                response.getWriter().print(json);
            } else {
                Comment comment1 = new Comment();
                comment1.setId(code);
                comment1.setUserId(user.getUserId());
                comment1.setCreateTime(time);
                comment1.setText(comment);
                comment1.setVideoId(av);

                int result = commentService.addComment(comment1);
                Gson gson = new Gson();
                String json;
                if(result > 0) {
                    json = gson.toJson(1);

                }
                else{
                    json = gson.toJson(0);

                }
                response.setContentType("application/json");
                response.getWriter().print(json);
            }
        }

    }
}

