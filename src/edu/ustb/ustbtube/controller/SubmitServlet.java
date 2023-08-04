package edu.ustb.ustbtube.controller;

import edu.ustb.ustbtube.entity.User;
import edu.ustb.ustbtube.entity.Video;
import edu.ustb.ustbtube.service.SubmitService;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

@WebServlet(name = "SubmitServlet", value = "/submit")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1024, // 2MB
        maxFileSize = 1024 * 1024 * 1024,    // 10MB
        maxRequestSize = 1024 * 1024 * 1024) // 50MB
public class SubmitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/submit.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // 从请求中获取文件和标题
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        Part filePart = request.getPart("mf");
        String title = request.getParameter("title");
        Part imgPart = request.getPart("img");
        String tag = request.getParameter("tags");
//        String username = request.getParameter("username");
        String introduction = request.getParameter("description");

        // 获取文件扩展名
        String fileName = filePart.getSubmittedFileName();
        String ext = fileName.substring(fileName.lastIndexOf('.') + 1);

        String imgName = imgPart.getSubmittedFileName();
        String eext = imgName.substring(imgName.lastIndexOf('.') + 1);

        // 定义允许的扩展名
        String[] arrExt = {"3gp", "rmvb", "flv", "wmv", "avi", "mkv", "mp4", "mp3", "wav","png","jpg"};

        if (!Arrays.asList(arrExt).contains(ext)||!Arrays.asList(arrExt).contains(eext)) {
            response.getWriter().write("-1"); // 返回错误状态码(-1)
        } else {
            // 生成新的文件名
            String filePreviewName = "pre_" + md5(String.valueOf(new Random().nextInt(9000) + 1000)) + System.currentTimeMillis() + "." + ext;
            String imgPreviewName = "pre_" + md5(String.valueOf(new Random().nextInt(9000) + 1000)) + System.currentTimeMillis() + "." + eext;
            // 将文件保存到预览目录
            String fileSavePath = "E:\\$$Information$$\\m_Java\\UstbTube\\web\\rsc\\" + "video/";
            String imgSavePath =  "E:\\$$Information$$\\m_Java\\UstbTube\\web\\rsc\\images\\" + "video/";
            File fileSaveDir = new File(fileSavePath);
            File imgSaveDir = new File(imgSavePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdirs();
            }
            if (!imgSaveDir.exists()) {
                imgSaveDir.mkdirs();
            }

            boolean hasWritePermission = fileSaveDir.canWrite();
            String filePreviewSrc = fileSavePath +File.separator + filePreviewName;
            String imgPreviewSrc = imgSavePath + File.separator + imgPreviewName;
            imgPart.write(imgPreviewSrc);
            try {
                filePart.write(filePreviewSrc);
            } catch (IOException e) {
                // 处理文件写入异常
                e.printStackTrace(); // 或者记录日志等其他操作
            }
            //D:\Java\code\UstbTube\out\artifacts\UstbTube_war_exploded\videos
            //项目路径D:\Java\code\UstbTube

            //存入数据库
            SubmitService submitService = new SubmitService();
            Video video = new Video();


            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            String userName = user.getUserId();
            video.setUserId(userName);
            video.setVideoTitle(title);
            video.setTag(tag);
            video.setPath("rsc/video/"+filePreviewName);
            video.setImg("rsc/images/video/"+imgPreviewName);
            video.setIntroduction(introduction);
            video.setReadTimes(0);
            video.setStar(0);
            video.setVideoId(mm4());
            video.setFavorites(0);
            video.setComment(0);
            Date date = new Date();
            Timestamp nousedate = new Timestamp(date.getTime());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = sdf.format(nousedate);

            video.setCreateTime(dateString);
            int result = submitService.insertVideo(video);

            // 返回预览文件名
            response.getWriter().write(filePreviewName);
        }
    }

    // 生成MD5哈希的方法
    private String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashText = no.toString(16);
            while (hashText.length() < 32) {
                hashText = "0" + hashText;
            }
            return hashText;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    public String mm4() {
        // 创建一个Random对象
        Random random = new Random();

        // 生成一个六位随机数
        int randomNumber = random.nextInt(900000) + 100000;

        return(String.valueOf(randomNumber));
    }
}
