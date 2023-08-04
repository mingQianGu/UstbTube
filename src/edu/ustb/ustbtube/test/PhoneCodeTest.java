package edu.ustb.ustbtube.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ustb.ustbtube.service.LogService;
import edu.ustb.ustbtube.utils.CheckCodeUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

public class PhoneCodeTest {

    LogService logService = new LogService();

    @Test
    public void testPhoneCode() throws IOException {

        String code = CheckCodeUtil.getCode(6);
        // 接收短信的手机号
        String phone = "18256555071";
        // 调用发短信的方法
        String result =  logService.sendSms(phone, code);

        ObjectMapper objMapper = new ObjectMapper();
        Map<String, String> rst = objMapper.readValue(result, Map.class);

        System.out.println(result);
        System.out.println(rst);
        System.out.println(rst.get("Code"));
        System.out.println("OK".equals(rst.get("Code")));

    }

}
