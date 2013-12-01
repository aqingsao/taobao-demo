package com.taobao.demo;

import com.taobao.api.*;
import com.taobao.api.request.*;
import org.apache.log4j.Logger;

public class ApiTest {
    private static final String SANDBOX_URL = "http://gw.api.tbsandbox.com/router/rest";//沙箱环境调用地址
    //正式环境需要设置为:http://gw.api.taobao.com/router/rest
    private static final String appkey = "1021691695";
    private static final String appSecret = "sandbox8a248b13ae4d23749d1dd91f7";
    private static final String sessionkey = "610160869568c2b914ee99939619dc9087b4dd9540186593611769289"; //如 沙箱测试帐号sandbox_c_1授权后得到的sessionkey
    private static final Logger LOGGER = Logger.getLogger(ApiTest.class);
    private TaobaoClient client;

    private ApiTest() {
        client = new DefaultTaobaoClient(SANDBOX_URL, appkey, appSecret, "json");
    }

    public void userSellerGetRequest() {
        UserSellerGetRequest req = new UserSellerGetRequest();//实例化具体API对应的Request类
        req.setFields("user_id,nick,sex,seller_credit,type,has_more_pic,item_img_num,item_img_size,prop_img_num,prop_img_size,auto_repost,promoted_type,status,alipay_bind,consumer_protection,avatar,liangpin,sign_food_seller_promise,has_shop,is_lightning_consignment,has_sub_stock,is_golden_seller,vip_info,magazine_subscribe,vertical_market,online_gaming");

        request(req);
    }

    public void userBuyerGetRequest() {
        UserBuyerGetRequest req = new UserBuyerGetRequest();//实例化具体API对应的Request类
        req.setFields("num_iid,avatar");

        request(req);
    }

    public void itemcatsGetRequest(){
        ItemcatsGetRequest req = new ItemcatsGetRequest();
        req.setParentCid(0L);
        request(req);
    }

    public void itemsPropsGet(){
        ItempropsGetRequest req=new ItempropsGetRequest();
        req.setFields("pid,name,must,multi,prop_values");
        req.setCid(44343L);
        req.setPid(3232L);
        req.setParentPid(4834L);
        req.setIsKeyProp(true);
        req.setIsSaleProp(true);
        req.setIsColorProp(true);
        req.setIsEnumProp(true);
        req.setIsInputProp(true);
        req.setIsItemProp(true);
        req.setChildPath("3932:13221;2113:2345");
        req.setType(1L);
        req.setAttrKeys("item_must_image");

        request(req);
    }
    private TaobaoResponse request(TaobaoRequest req) {
        try {
            TaobaoResponse response = client.execute(req, sessionkey);
            LOGGER.info(response.getBody());

            return response; //执行API请求并打印结果
        } catch (ApiException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private void tradeAmountGetRequest() {
        TradeAmountGetRequest req=new TradeAmountGetRequest();
        req.setTid(123456L);
        req.setFields("tid,oid,alipay_no,total_fee,post_fee");
        request(req);
    }

    public static void main(String[] args) {
        ApiTest apiTest = new ApiTest();
        apiTest.userSellerGetRequest();
        apiTest.userBuyerGetRequest();
        apiTest.itemcatsGetRequest();
        apiTest.itemsPropsGet();
        apiTest.tradeAmountGetRequest();
    }

}