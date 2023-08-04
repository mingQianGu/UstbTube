package edu.ustb.ustbtube.service;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import edu.ustb.ustbtube.dao.UserMapper;
import edu.ustb.ustbtube.dao.VideoMapper;
import edu.ustb.ustbtube.entity.User;
import edu.ustb.ustbtube.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

public class LogService {


    public String logIn(String account, String passWord) {
        SqlSession session = null;
        String result = "";
        try {
            session = MybatisUtil.getSession();
            UserMapper userMapper = session.getMapper(UserMapper.class);

            User user = userMapper.getUserByUserId(account);
            if(user == null) {
                result = "AccountError";
            } else if(user.getPassWord().equals(passWord)) {
                result = "true";
            } else {
                result = "PassWordError";
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }

        return result;
    }

    public User setUser(User user) {
        SqlSession session = null;
        User user1 = new User();
        try {
            session = MybatisUtil.getSession();
            UserMapper userMapper = session.getMapper(UserMapper.class);

            user1 = userMapper.getUserByUserId(user.getUserId());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }

        return user1;
    }

    public String sendSms(String phone, String code){
        String result = null;


        // AccessKey ID 和 AccessKey Secret
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "", "");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "");//签名名称
        request.putQueryParameter("TemplateCode", ""); //模版CODE
        request.putQueryParameter("TemplateParam", "{\"code\":\"" + code + "\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            result = response.getData();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }

        return result;

    }

    public User getUserByPhone(String phone) {
        SqlSession session = null;
        User user = new User();
        try {
            session = MybatisUtil.getSession();
            UserMapper userMapper = session.getMapper(UserMapper.class);

            user = userMapper.getUserByPhone(phone);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }

        return user;
    }

    public int checkNickName(String nickName) {
        SqlSession session = null;
        int result = -1;
        try {
            session = MybatisUtil.getSession();
            UserMapper userMapper = session.getMapper(UserMapper.class);

            result = userMapper.checkNickName(nickName);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }

        return result;
    }

    public int insertUser(User user) {
        SqlSession session = null;
        int result = -1;
        try {
            session = MybatisUtil.getSession();
            UserMapper userMapper = session.getMapper(UserMapper.class);

            result = userMapper.insertUser(user);
            session.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }

        return result;
    }

}
