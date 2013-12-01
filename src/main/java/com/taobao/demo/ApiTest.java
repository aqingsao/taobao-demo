package com.taobao.demo;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.UserSellerGetRequest;
import com.taobao.api.response.UserSellerGetResponse;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

public class ApiTest {
    private static final String SANDBOX_URL = "http://gw.api.tbsandbox.com/router/rest";//沙箱环境调用地址
    //正式环境需要设置为:http://gw.api.taobao.com/router/rest
    private static final String appkey = "1021691695";
    private static final String appSecret = "sandbox8a248b13ae4d23749d1dd91f7";
    private static final String sessionkey = "610160869568c2b914ee99939619dc9087b4dd9540186593611769289"; //如 沙箱测试帐号sandbox_c_1授权后得到的sessionkey
    private static final Logger LOGGER = Logger.getLogger(ApiTest.class);

    public static void testUserGet() {
        TaobaoClient client = new DefaultTaobaoClient(SANDBOX_URL, appkey, appSecret);//实例化TopClient类
        UserSellerGetRequest req = new UserSellerGetRequest();//实例化具体API对应的Request类
        req.setFields("nick,user_id,type");
//        req.setNick("sandbox_c_1");
        UserSellerGetResponse response;
        try {
            response = client.execute(req, sessionkey); //执行API请求并打印结果
            System.out.println("result:" + response.getBody());
        } catch (ApiException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        ApiTest.testUserGet();
    }

}