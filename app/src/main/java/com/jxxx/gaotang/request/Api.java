package com.jxxx.gaotang.request;

import android.content.Context;
import android.os.Handler;

import com.jxxx.gaotang.conpoment.constants.ConstValues;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/9/9.
 */

public class Api {

    private HttpRequest mHttpRequest;
    private Handler mHandler;
    private Context context;

    public Api(Handler handler, Context context) {
        mHttpRequest = new HttpRequest(handler, context);
        this.mHandler = handler;
    }


    /**
     * 获取验证码
     *
     * @param what
     * @param mobile
     */
    public void getMobileCode(int what, String mobile, String captchaId, String captcha, String verifyType) {
        String url = ConstValues.BASE_URL + "api/v1/user/verify/getMobileCode?mobile=" + mobile
                + "&captchaId=" + captchaId
                + "&captcha=" + captcha
                + "&verifyType=" + verifyType;
        mHttpRequest.getData(url, what, false);
    }

    /**
     * 登录
     *
     * @param what
     * @param mobile
     * @param password
     * @param clientType
     */
    public void login(int what, String mobile, String password, String clientType,String registrationId) {
        String url = ConstValues.BASE_URL + "api/v1/user/verify/login";
        HashMap<String, String> paramsTreeMap = new HashMap<>();
        paramsTreeMap.put("mobile", mobile);
        paramsTreeMap.put("password", password);
        paramsTreeMap.put("clientType", clientType);
        paramsTreeMap.put("registrationId", registrationId);
        mHttpRequest.postUpJson(url, what, "login", paramsTreeMap, false);
    }

    public void codeLogin(int what, String mobile, String verify, String clientType,String registrationId) {
        String url = ConstValues.BASE_URL + "api/v1/user/verify/login";
        HashMap<String, String> paramsTreeMap = new HashMap<>();
        paramsTreeMap.put("mobile", mobile);
        paramsTreeMap.put("verify", verify);
        paramsTreeMap.put("clientType", clientType);
        paramsTreeMap.put("registrationId", registrationId);
        mHttpRequest.postUpJson(url, what, "login", paramsTreeMap, false);
    }

    /**
     * 生成图形验证码
     */
    public void generateCaptcha(int what) {
        String url = ConstValues.BASE_URL + "api/v1/user/verify/generateCaptcha";
        mHttpRequest.getData(url, what, false);
    }

    /**
     * 获取图形验证码
     *
     * @param what
     * @param captchaId
     */
    public void captcha(int what, String captchaId) {
        String url = ConstValues.BASE_URL + "api/v1/user/verify/captcha/" + captchaId;
        mHttpRequest.getData(url, what, false);
    }

    /**
     * 注册
     *
     * @param what
     * @param mobile
     * @param verify
     * @param password
     * @param clientType
     * @param inviteCode
     */
    public void regist(int what, String mobile, String verify, String password, String clientType, String inviteCode,String registrationId) {
        String url = ConstValues.BASE_URL + "api/v1/user/verify/register";
        HashMap<String, String> paramsTreeMap = new HashMap<>();
        paramsTreeMap.put("mobile", mobile);
        paramsTreeMap.put("verify", verify);
        paramsTreeMap.put("password", password);
        paramsTreeMap.put("clientType", clientType);
        paramsTreeMap.put("inviteCode", inviteCode);
        paramsTreeMap.put("registrationId", registrationId);
        mHttpRequest.postUpJson(url, what, "regist", paramsTreeMap, false);
    }


    /**
     * 忘记密码
     * @param what
     * @param mobile
     * @param verify
     * @param password
     * @param clientType
     * @param registrationId
     */
    public void forgetPwd(int what, String mobile,String verify, String password, String clientType,String registrationId) {
        String url = ConstValues.BASE_URL + "api/v1/user/verify/forget";
        HashMap<String, String> paramsTreeMap = new HashMap<>();
        paramsTreeMap.put("mobile", mobile);
        paramsTreeMap.put("verify", verify);
        paramsTreeMap.put("password", password);
        paramsTreeMap.put("clientType", clientType);
        paramsTreeMap.put("registrationId", registrationId);
        mHttpRequest.postUpJson(url, what, "forgetPwd", paramsTreeMap, false);
    }


    /**
     * 修改密码
     * @param what
     * @param password
     */
    public void changePassword(int what, String password) {
        String url = ConstValues.BASE_URL + "api/v1/user/changePassword";
        HashMap<String, String> paramsTreeMap = new HashMap<>();
        paramsTreeMap.put("password", password);
        mHttpRequest.postUpJsonToken(url, what, "changePassword", paramsTreeMap, false);
    }

    /**
     * 以租代购首页
     *
     * @param what
     */
    public void getLeaseHome(int what) {
        String url = ConstValues.BASE_URL + "api/v1/home/leaseHome";
        mHttpRequest.getData(url, what, false);
    }

    /**
     * 商品列表页
     *
     * @param what
     * @param productType
     */
    public void getProductQuery(int what, String productType, String orders, String keyword
            , int page, int pageSize, String sellPrice, String sellPrices, String brandId,String classId,String parentClassId) {
        String url = ConstValues.BASE_URL + "api/v1/cash/product/query?productType=" + productType
                + "&orders=" + orders
                + "&keyword=" + keyword
                + "&page=" + page
                + "&pageSize=" + pageSize
                + "&sellPrice=" + sellPrice
                + "&sellPrices=" + sellPrices
                + "&brandId=" + brandId
                + "&classId=" + classId
                + "&parentClassId=" + parentClassId;
        mHttpRequest.getData(url, what, false);
    }

    /**
     * 商品品牌
     *
     * @param what
     */
    public void getBrandQuery(int what,int page,int pageSize) {
        String url = ConstValues.BASE_URL + "api/v1/brand/query?page="+page+"&pageSize="+pageSize;
        mHttpRequest.getData(url, what, false);
    }


    /**
     * 消费卡商品列表
     *
     * @param what
     */
    public void getConsumptionProductQuery(int what, int page, int pageSize) {
        String url = ConstValues.BASE_URL + "api/v1/consumptionProduct/query?page=" + page + "&pageSize=" + pageSize;
        mHttpRequest.getData(url, what, false);
    }


    /**
     * 图片上传
     *
     * @param what
     * @param file
     */
    public void getFiles(int what, File file) {
        String url = ConstValues.BASE_URL + "api/v1/files";
        HashMap<String, String> paramsTreeMap = new HashMap<>();
        List<File> fileList = new ArrayList<>();
        fileList.add(file);
        mHttpRequest.postFile(url, what, "upFile", paramsTreeMap, true, fileList);
    }

    /**
     * 添加银行卡
     *
     * @param what
     * @param mobile
     * @param realName
     * @param accountNo
     * @param accountType
     * @param remark
     */
    public void getCardAdd(int what, String mobile, String realName, String accountNo, String accountType, String remark, JSONArray imgUrl) {
        String url = ConstValues.BASE_URL + "api/v1/user/card/add";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("mobile", mobile);
            jsonObject.put("realName", realName);
            jsonObject.put("accountNo", accountNo);
            jsonObject.put("accountType", accountType);
            jsonObject.put("remark", remark);
            jsonObject.put("imgUrl", imgUrl);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mHttpRequest.postJsonToken(url, what, "getCardAdd", jsonObject);
    }

    /**
     * 购物车列表
     *
     * @param what
     */
    public void getShoppingCartQuery(int what) {
        String url = ConstValues.BASE_URL + "api/v1/user/shoppingCart/query";
        mHttpRequest.getTokenData(url, what, false);
    }

    /**
     * 购物车更新
     *
     * @param what
     * @param itemId
     * @param quantity
     * @param skuId
     * @param productId
     */
    public void shoppingCartUpdate(int what, String itemId, String quantity, String skuId, String productId) {
        String url = ConstValues.BASE_URL + "api/v1/user/shoppingCart/update";
        HashMap<String, String> paramsTreeMap = new HashMap<>();
        paramsTreeMap.put("itemId", itemId);
        paramsTreeMap.put("quantity", quantity);
        paramsTreeMap.put("skuId", skuId);
        paramsTreeMap.put("productId", productId);
        mHttpRequest.postUpJsonToken(url, what, "shoppingCartUpdate", paramsTreeMap, false);
    }

    /**
     * 购物车删除
     *
     * @param what
     * @param itemId
     */
    public void shoppingCartDelect(int what, String itemId) {
        String url = ConstValues.BASE_URL + "api/v1/user/shoppingCart/delItem";
        HashMap<String, String> paramsTreeMap = new HashMap<>();
        paramsTreeMap.put("itemId", itemId);
        mHttpRequest.postUpJsonToken(url, what, "shoppingCartDelect", paramsTreeMap, false);
    }


    /**
     * 收货地址列表
     *
     * @param what
     * @param page
     * @param pageSize
     */
    public void getAddressManager(int what, int page, int pageSize) {
        String url = ConstValues.BASE_URL + "api/v1/user/address/query?page=" + page + "&pageSize=" + pageSize;
        mHttpRequest.getTokenData(url, what, false);
    }

    /**
     * 添加收货地址
     *
     * @param what
     * @param districtId
     * @param address
     * @param acceptName
     * @param mobile
     * @param isDefault
     */
    public void getAddressAdd(int what, String districtId, String address, String acceptName, String mobile, String isDefault) {
        String url = ConstValues.BASE_URL + "api/v1/user/address/add";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("districtId", districtId);
            jsonObject.put("address", address);
            jsonObject.put("acceptName", acceptName);
            jsonObject.put("mobile", mobile);
            jsonObject.put("isDefault", isDefault);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mHttpRequest.postJsonToken(url, what, "getAddressAdd", jsonObject);
    }

    /**
     * 更新地址
     *
     * @param what
     * @param districtId
     * @param address
     * @param acceptName
     * @param mobile
     * @param isDefault
     * @param id
     */
    public void getAddressUpdate(int what, String districtId, String address, String acceptName, String mobile, String isDefault, String id) {
        String url = ConstValues.BASE_URL + "api/v1/user/address/update";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("districtId", districtId);
            jsonObject.put("address", address);
            jsonObject.put("acceptName", acceptName);
            jsonObject.put("mobile", mobile);
            jsonObject.put("isDefault", isDefault);
            jsonObject.put("id", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mHttpRequest.postJsonToken(url, what, "getAddressUpdate", jsonObject);
    }


    /**
     * 设置默认地址
     *
     * @param what
     * @param id
     */
    public void getAddressDef(int what, String id) {
        String url = ConstValues.BASE_URL + "api/v1/user/address/default";
        HashMap<String, String> paramsTreeMap = new HashMap<>();
        paramsTreeMap.put("id", id);
        mHttpRequest.postUpJsonToken(url, what, "getAddressDef", paramsTreeMap, false);
    }

    /**
     * 删除地址
     *
     * @param what
     * @param id
     */
    public void getAddressDelect(int what, String id) {
        String url = ConstValues.BASE_URL + "api/v1/user/address/delete";
        HashMap<String, String> paramsTreeMap = new HashMap<>();
        paramsTreeMap.put("id", id);
        mHttpRequest.postUpJsonToken(url, what, "getAddressDelect", paramsTreeMap, false);
    }

    /**
     * 获取用户默认地址
     *
     * @param what
     */
    public void getUserAddressDef(int what) {
        String url = ConstValues.BASE_URL + "api/v1/user/address/getDefault";
        mHttpRequest.getTokenData(url, what, false);
    }

    /**
     * 现金商城添加商品
     *
     * @param what
     * @param addressId
     * @param orderType
     * @param itemList
     */
    public void getCashAddOrder(int what, String addressId, String orderType,String couponId, JSONArray itemList,String idNumber) {
        String url = ConstValues.BASE_URL + "api/v1/user/cashOrder/addOrder";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("addressId", addressId);
            jsonObject.put("orderType", orderType);
            jsonObject.put("couponId", couponId);
            jsonObject.put("itemList", itemList);
            jsonObject.put("idNumber", idNumber);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        mHttpRequest.postJsonToken(url, what, "getAddressUpdate", jsonObject);
    }


    public void getIntegralAddOrder(int what, String addressId, JSONArray itemList) {
        String url = ConstValues.BASE_URL + "api/v1/user/cashOrder/integralExchange";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("addressId", addressId);
            jsonObject.put("itemList", itemList);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        mHttpRequest.postJsonToken(url, what, "getIntegralAddOrder", jsonObject);
    }

    /**
     * 订单列表
     *
     * @param what
     * @param status
     * @param orderType
     * @param page
     * @param pageSize
     */
    public void getCashOrderQuery(int what, String status, String orderType, int page, int pageSize) {
        String url = ConstValues.BASE_URL + "api/v1/user/cashOrder/query?status=" + status
                + "&orderType=" + orderType
                + "&page=" + page
                + "&pageSize=" + pageSize;
        mHttpRequest.getTokenData(url, what, false);
    }

    public void getCashOrderRefundQuery(int what, String statusType, String orderType, int page, int pageSize) {
        String url = ConstValues.BASE_URL + "api/v1/user/cashOrder/query?statusType=" + statusType
                + "&orderType=" + orderType
                + "&page=" + page
                + "&pageSize=" + pageSize;
        mHttpRequest.getTokenData(url, what, false);
    }

    /**
     * 现金商城+积分商城
     *
     * @param what
     * @param productType
     * @param userId
     */
    public void getCashHome(int what, String productType, String userId) {
        String url = ConstValues.BASE_URL + "api/v1/home/cashHome?productType=" + productType + "&userId=" + userId;
        mHttpRequest.getData(url, what, false);
    }

    /**
     * 商品详情
     *
     * @param what
     * @param id
     */
    public void getProductDetail(int what, String id) {
        String url = ConstValues.BASE_URL + "api/v1/cash/product/details?id=" + id;
        mHttpRequest.getData(url, what, false);
    }


    /**
     * 添加购物车
     *
     * @param what
     * @param productId
     * @param quantity
     * @param skuId
     */
    public void getShopCartAdd(int what, String productId, int quantity, String skuId) {
        String url = ConstValues.BASE_URL + "api/v1/user/shoppingCart/add";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("productId", productId);
            jsonObject.put("quantity", quantity);
            jsonObject.put("skuId", skuId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mHttpRequest.postJsonToken(url, what, "getShopCartAdd", jsonObject);
    }

    /**
     * 添加收藏
     *
     * @param what
     * @param productId
     * @param type
     */
    public void getAddCollection(int what, String productId, String type) {
        String url = ConstValues.BASE_URL + "api/v1/user/collection/addCollection";
        HashMap<String, String> paramsTreeMap = new HashMap<>();
        paramsTreeMap.put("productId", productId);
        paramsTreeMap.put("type", type);
        mHttpRequest.postUpJsonToken(url, what, "getAddCollection", paramsTreeMap, false);
    }

    /**
     * 是否收藏
     *
     * @param what
     * @param productId
     * @param type
     */
    public void getIsCollection(int what, String productId, String type) {
        String url = ConstValues.BASE_URL + "api/v1/user/collection/getIsCollection?productId=" + productId + "&type=" + type;
        mHttpRequest.getTokenData(url, what, false);
    }

    /**
     * 取消收藏
     *
     * @param what
     * @param productId
     * @param type
     */
    public void getCancelCollection(int what, String productId, String type) {
        String url = ConstValues.BASE_URL + "api/v1/user/collection/cancelCollection";
        HashMap<String, String> paramsTreeMap = new HashMap<>();
        paramsTreeMap.put("productId", productId);
        paramsTreeMap.put("type", type);
        mHttpRequest.postUpJsonToken(url, what, "getAddCollection", paramsTreeMap, false);
    }

    /**
     * 收藏列表
     *
     * @param what
     * @param page
     * @param pageSize
     */
    public void getCollectionQuery(int what, int page, int pageSize) {
        String url = ConstValues.BASE_URL + "api/v1/user/collection/query?page=" + page
                + "&pageSize=" + pageSize;
        mHttpRequest.getTokenData(url, what, false);
    }

    /**
     * 订单详情
     *
     * @param what
     * @param orderId
     */
    public void getOrderDetail(int what, String orderId) {
        String url = ConstValues.BASE_URL + "api/v1/user/cashOrder/details?orderId=" + orderId;
        mHttpRequest.getTokenData(url, what, false);
    }

    /**
     * 租赁商品详情
     *
     * @param what
     * @param id
     */
    public void getLeaseProductDetail(int what, String id) {
        String url = ConstValues.BASE_URL + "api/v1/leaseProduct/details?id=" + id;
        mHttpRequest.getData(url, what, false);
    }

    /**
     * 油卡商品详情
     *
     * @param what
     * @param id
     */
    public void getConsumptionProductDetail(int what, String id) {
        String url = ConstValues.BASE_URL + "api/v1/consumptionProduct/details?id=" + id;
        mHttpRequest.getData(url, what, false);
    }

    /**
     * 首页
     *
     * @param what
     */
    public void getHome(int what) {
        String url = ConstValues.BASE_URL + "api/v1/home/generalHome";
        mHttpRequest.getData(url, what, false);
    }

    /**
     * 我的消费卡
     *
     * @param what
     */
    public void getConsumerCardQuery(int what, int page, int pageSize) {
        String url = ConstValues.BASE_URL + "api/v1/user/consumerCard/query?page=" + page
                + "&pageSize=" + pageSize;
        mHttpRequest.getTokenData(url, what, false);
    }

    /**
     * 添加消费卡
     *
     * @param what
     * @param status
     * @param cardNo
     * @param mobile
     * @param cardType
     */
    public void getConsumerCardAdd(int what, String status, String cardNo, String mobile, String cardType) {
        String url = ConstValues.BASE_URL + "api/v1/user/consumerCard/addConsumerCard";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("status", status);
            jsonObject.put("cardNo", cardNo);
            jsonObject.put("mobile", mobile);
            jsonObject.put("cardType", cardType);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mHttpRequest.postJsonToken(url, what, "getConsumerCardAdd", jsonObject);
    }

    /**
     * 删除消费卡
     *
     * @param what
     * @param id
     */
    public void getDelConsumer(int what, String id) {
        String url = ConstValues.BASE_URL + "api/v1/user/consumerCard/delConsumer";
        HashMap<String, String> paramsTreeMap = new HashMap<>();
        paramsTreeMap.put("id", id);
        mHttpRequest.postUpJsonToken(url, what, "getDelConsumer", paramsTreeMap, false);
    }

    /**
     * 获取用户默认消费卡
     *
     * @param what
     */
    public void getConsumerCardDef(int what) {
        String url = ConstValues.BASE_URL + "api/v1/user/consumerCard/getDefault";
        mHttpRequest.getTokenData(url, what, false);
    }

    /**
     * 商品推荐列表页
     *
     * @param what
     * @param productType
     */
    public void getHomeProductQuery(int what, String productType, String isSell, String isRec
            , int page, int pageSize) {
        String url = ConstValues.BASE_URL + "api/v1/cash/product/query?productType=" + productType
                + "&isSell=" + isSell
                + "&isRec=" + isRec
                + "&page=" + page
                + "&pageSize=" + pageSize;
        mHttpRequest.getData(url, what, false);
    }

    /**
     * 邀请达人榜
     *
     * @param what
     */
    public void getDaren(int what) {
        String url = ConstValues.BASE_URL + "api/v1/user/getDaren";
        mHttpRequest.getTokenData(url, what, false);
    }

    /**
     * 邀请人数
     *
     * @param what
     */
    public void getInvitation(int what) {
        String url = ConstValues.BASE_URL + "api/v1/user/getInvitation";
        mHttpRequest.getTokenData(url, what, false);
    }

    /**
     * 我的
     *
     * @param what
     */
    public void getUserInfo(int what) {
        String url = ConstValues.BASE_URL + "api/v1/user/get";
        mHttpRequest.getTokenData(url, what, false);
    }

    /**
     * 余额
     *
     * @param what
     */
    public void getBalance(int what) {
        String url = ConstValues.BASE_URL + "api/v1/user/account/getBalance";
        mHttpRequest.getTokenData(url, what, false);
    }

    /**
     * 轮播图列表
     *
     * @param what
     */
    public void getBannerQuery(int what, String type) {
        String url = ConstValues.BASE_URL + "api/v1/banner/query?type=" + type;
        mHttpRequest.getData(url, what, false);
    }

    /**
     * 商品分类列表
     *
     * @param what
     * @param parentId
     */
    public void getCategoryParent(int what, String parentId,String type,int page,int pageSize) {
        String url = ConstValues.BASE_URL + "api/v1/cashClass/query?parentId=" + parentId+"&type="+type+"&page="+page+"&pageSize="+pageSize;
        mHttpRequest.getData(url, what, false);
    }

    /**
     * 我的积分
     *
     * @param what
     */
    public void getIntegral(int what) {
        String url = ConstValues.BASE_URL + "api/v1/user/account/getIntegral";
        mHttpRequest.getTokenData(url, what, false);
    }

    /**
     * 签到列表
     *
     * @param what
     */
    public void getSignQuery(int what) {
        String url = ConstValues.BASE_URL + "api/v1/user/sign/query";
        mHttpRequest.getTokenData(url, what, false);
    }

    /**
     * 签到
     *
     * @param what
     */
    public void getSignIn(int what) {
        String url = ConstValues.BASE_URL + "api/v1/user/sign/signIn";
        HashMap<String, String> paramsTreeMap = new HashMap<>();
        mHttpRequest.postUpJsonToken(url, what, "getSignIn", paramsTreeMap, false);
    }


    /**
     * 我的分销
     *
     * @param what
     */
    public void getDistribution(int what, String type, int page, int pageSize) {
        String url = ConstValues.BASE_URL + "api/v1/user/getDistribution?type=" + type + "&page=" + page + "&pageSize=" + pageSize;
        mHttpRequest.getTokenData(url, what, false);
    }

    /**
     * 我的会员
     *
     * @param what
     */
    public void getRankLevel(int what) {
        String url = ConstValues.BASE_URL + "api/v1/user/rank";
        mHttpRequest.getTokenData(url, what, false);
    }


    /**
     * 优惠券
     *
     * @param what
     */
    public void getCouponQuery(int what, String isAvailable, int page, int pageSize) {
        String url = ConstValues.BASE_URL + "api/v1/user/userCoupon/query?isAvailable=" + isAvailable + "&page=" + page
                + "&pageSize=" + pageSize;
        mHttpRequest.getTokenData(url, what, false);
    }

    public void getCouponQuery(int what, String isAvailable, String status, String couponType,String amountMoney) {
        String url = ConstValues.BASE_URL + "api/v1/user/userCoupon/query?isAvailable=" + isAvailable
                + "&status=" + status
                + "&couponType=" + couponType
                + "&amountMoney=" + amountMoney;

        mHttpRequest.getTokenData(url, what, false);
    }


    /**
     * 账户列表
     *
     * @param what
     * @param accountType
     * @param inOrOut
     * @param associationType
     * @param page
     * @param pageSize
     */
    public void getAccountListLog(int what, String accountType, String inOrOut, String associationType, int page, int pageSize) {
        String url = ConstValues.BASE_URL + "api/v1/user/account/listLog?accountType=" + accountType
                + "&inOrOut=" + inOrOut
                + "&associationType=" + associationType
                + "&page=" + page
                + "&pageSize=" + pageSize;
        mHttpRequest.getTokenData(url, what, false);
    }


    /**
     * 租赁商品列表
     *
     * @param what
     * @param page
     * @param pageSize
     */
    public void getLeaseProductQuery(int what, int page, int pageSize) {
        String url = ConstValues.BASE_URL + "api/v1/leaseProduct/query?page=" + page + "&pageSize=" + pageSize;
        mHttpRequest.getData(url, what, false);
    }

    /**
     * 帮助中心
     *
     * @param what
     */
    public void getAgreementQuery(int what,String type) {
        String url = ConstValues.BASE_URL + "api/v1/agreement/query?type="+type;
        mHttpRequest.getData(url, what, false);
    }

    /**
     * 常见问题
     * @param what
     */
    public void getQuestionQuery(int what) {
        String url = ConstValues.BASE_URL + "api/v1/commonProblem/query";
        mHttpRequest.getData(url, what, false);
    }

    /**
     * 会员先享次数
     *
     * @param what
     */
    public void getFrequency(int what) {
        String url = ConstValues.BASE_URL + "api/v1/user/consumptionOrder/getFrequency";
        mHttpRequest.getTokenData(url, what, false);
    }

    /**
     * 消费卡下单
     *
     * @param what
     * @param productId
     * @param cardId
     * @param isDeposit
     * @param severalIssues
     * @param remark
     */
    public void getConsumptionOrderAdd(int what, String productId, String cardId, String isDeposit, String severalIssues, String remark) {
        String url = ConstValues.BASE_URL + "api/v1/user/consumptionOrder/addOrder";
        HashMap<String, String> paramsTreeMap = new HashMap<>();
        paramsTreeMap.put("productId", productId);
        paramsTreeMap.put("cardId", cardId);
        paramsTreeMap.put("isDeposit", isDeposit);
        paramsTreeMap.put("severalIssues", severalIssues);
        paramsTreeMap.put("remark", remark);
        mHttpRequest.postUpJsonToken(url, what, "getConsumptionOrderAdd", paramsTreeMap, false);
    }


    public void getConsumptionOrderQuery(int what, String status, int page, int pageSize) {
        String url = ConstValues.BASE_URL + "api/v1/user/consumptionOrder/query?status=" + status
                + "&page=" + page
                + "&pageSize=" + pageSize;
        mHttpRequest.getTokenData(url, what, false);
    }

    /**
     * 消费卡详情返现单列表
     *
     * @param what
     * @param orderId
     * @param page
     * @param pageSize
     */
    public void getConsumptionOrderlogQuery(int what, String orderId, int page, int pageSize) {
        String url = ConstValues.BASE_URL + "api/v1/user/consumptionOrder/logQuery?orderId=" + orderId
                + "&page=" + page
                + "&pageSize=" + pageSize;
        mHttpRequest.getTokenData(url, what, false);
    }

    /**
     * 消费卡订单详情
     *
     * @param what
     * @param orderId
     */
    public void getConsumptionOrderDetail(int what, String orderId) {
        String url = ConstValues.BASE_URL + "api/v1/user/consumptionOrder/details?orderId=" + orderId;
        mHttpRequest.getTokenData(url, what, false);
    }


    /**
     * 支付尾款
     *
     * @param what
     * @param orderId
     */
    public void getConsumptionContinue(int what, String orderId) {
        String url = ConstValues.BASE_URL + "api/v1/user/consumptionOrder/continue";
        HashMap<String, String> paramsTreeMap = new HashMap<>();
        paramsTreeMap.put("orderId", orderId);
        mHttpRequest.postUpJsonToken(url, what, "getConsumptionContinue", paramsTreeMap, false);
    }

    /**
     * 申请退款
     *
     * @param what
     * @param orderId
     */
    public void getConsumptionDeactivate(int what, String orderId) {
        String url = ConstValues.BASE_URL + "api/v1/user/consumptionOrder/deactivate";
        HashMap<String, String> paramsTreeMap = new HashMap<>();
        paramsTreeMap.put("orderId", orderId);
        mHttpRequest.postUpJsonToken(url, what, "getConsumptionDeactivate", paramsTreeMap, false);
    }

    /**
     * 删除消费卡订单
     *
     * @param what
     * @param orderId
     */
    public void getConsumptionDel(int what, String orderId) {
        String url = ConstValues.BASE_URL + "api/v1/user/consumptionOrder/del";
        HashMap<String, String> paramsTreeMap = new HashMap<>();
        paramsTreeMap.put("orderId", orderId);
        mHttpRequest.postUpJsonToken(url, what, "getConsumptionDel", paramsTreeMap, false);
    }

    /**
     * 取消消费卡订单
     *
     * @param what
     * @param orderId
     */
    public void getConsumptionCancel(int what, String orderId) {
        String url = ConstValues.BASE_URL + "api/v1/user/consumptionOrder/cancel";
        HashMap<String, String> paramsTreeMap = new HashMap<>();
        paramsTreeMap.put("orderId", orderId);
        mHttpRequest.postUpJsonToken(url, what, "getConsumptionCancel", paramsTreeMap, false);
    }

    /**
     * 限时抢购时间列表
     *
     * @param what
     */
    public void getLimitedTimeQuery(int what) {
        String url = ConstValues.BASE_URL + "api/v1/couponActivity/query";
        mHttpRequest.getData(url, what, false);
    }

    /**
     * 限时抢购商品列表
     *
     * @param what
     * @param startTime
     * @param endTime
     * @param page
     * @param pageSize
     */
    public void getLimitedMallQuery(int what, String startTime, String endTime, int page, int pageSize) {
        String url = ConstValues.BASE_URL + "api/v1/coupon/product/query?startTime=" + startTime
                + "&endTime=" + endTime
                + "&page=" + page
                + "&pageSize=" + pageSize;
        mHttpRequest.getData(url, what, false);
    }

    /**
     * 订单取消
     *
     * @param what
     * @param orderId
     */
    public void getOrderCancel(int what, String orderId) {
        String url = ConstValues.BASE_URL + "api/v1/user/cashOrder/cancelOrder";
        HashMap<String, String> paramsTreeMap = new HashMap<>();
        paramsTreeMap.put("orderId", orderId);
        mHttpRequest.postUpJsonToken(url, what, "getOrderCancel", paramsTreeMap, false);
    }


    /**
     * 订单删除
     *
     * @param what
     * @param orderId
     */
    public void getOrderDel(int what, String orderId) {
        String url = ConstValues.BASE_URL + "api/v1/user/cashOrder/delOrder";
        HashMap<String, String> paramsTreeMap = new HashMap<>();
        paramsTreeMap.put("orderId", orderId);
        mHttpRequest.postUpJsonToken(url, what, "getOrderDel", paramsTreeMap, false);
    }


    /**
     * 订单确认
     *
     * @param what
     * @param orderId
     */
    public void getOrderConfirm(int what, String orderId) {
        String url = ConstValues.BASE_URL + "api/v1/user/cashOrder/confirmOrder";
        HashMap<String, String> paramsTreeMap = new HashMap<>();
        paramsTreeMap.put("orderId", orderId);
        mHttpRequest.postUpJsonToken(url, what, "getOrderConfirm", paramsTreeMap, false);
    }


    /**
     * 普通商品支付
     *
     * @param what
     * @param orderId
     * @param orderType
     * @param wxPayType
     * @param payType
     */
    public void getOrderPayInfo(int what, String orderId, String orderType, String wxPayType, String payType) {
        String url = ConstValues.BASE_URL + "api/v1/user/orderPayInfo";
        HashMap<String, String> paramsTreeMap = new HashMap<>();
        paramsTreeMap.put("orderId", orderId);
        paramsTreeMap.put("orderType", orderType);
        paramsTreeMap.put("wxPayType", wxPayType);
        paramsTreeMap.put("payType", payType);
        mHttpRequest.postUpJsonToken(url, what, "getOrderPayInfo", paramsTreeMap, false);
    }

    /**
     * 消费卡商品支付
     *
     * @param what
     * @param orderId
     * @param orderType
     * @param wxPayType
     * @param payType
     */
    public void getConsumptiOnorderPay(int what, String orderId, String orderType, String wxPayType, String payType) {
        String url = ConstValues.BASE_URL + "api/v1/user/consumptiOnorderPay";
        HashMap<String, String> paramsTreeMap = new HashMap<>();
        paramsTreeMap.put("orderId", orderId);
        paramsTreeMap.put("orderType", orderType);
        paramsTreeMap.put("wxPayType", wxPayType);
        paramsTreeMap.put("payType", payType);
        mHttpRequest.postUpJsonToken(url, what, "getOrderPayInfo", paramsTreeMap, false);
    }

    /**
     * 租赁商品支付
     *
     * @param what
     * @param orderId
     * @param orderType
     * @param wxPayType
     * @param payType
     * @param orderNo
     */
    public void getLeaseOrderPayInfo(int what, String orderId, String orderType, String wxPayType, String payType, String orderNo) {
        String url = ConstValues.BASE_URL + "api/v1/user/leaseOrderPayInfo";
        HashMap<String, String> paramsTreeMap = new HashMap<>();
        paramsTreeMap.put("orderId", orderId);
        paramsTreeMap.put("orderType", orderType);
        paramsTreeMap.put("wxPayType", wxPayType);
        paramsTreeMap.put("payType", payType);
        paramsTreeMap.put("orderNo", orderNo);
        mHttpRequest.postUpJsonToken(url, what, "getLeaseOrderPayInfo", paramsTreeMap, false);
    }

    /**
     * 普通商品提醒发货
     *
     * @param what
     * @param orderId
     */
    public void getCashOrderRemind(int what, String orderId) {
        String url = ConstValues.BASE_URL + "api/v1/user/cashOrder/reminderShipment";
        HashMap<String, String> paramsTreeMap = new HashMap<>();
        paramsTreeMap.put("orderId", orderId);
        mHttpRequest.postUpJsonToken(url, what, "getCashOrderRemind", paramsTreeMap, false);
    }

    /**
     * 限时抢购详情
     *
     * @param what
     * @param id
     */
    public void getLimitShopDetail(int what, String id) {
        String url = ConstValues.BASE_URL + "api/v1/coupon/product/details?id=" + id;
        mHttpRequest.getData(url, what, false);
    }


    /**
     * 邀请分明细
     *
     * @param what
     * @param page
     * @param pageSize
     */
    public void getRecommendedValues(int what, int page, int pageSize) {
        String url = ConstValues.BASE_URL + "api/v1/user/recommendedValues?page=" + page + "&pageSize=" + pageSize;
        mHttpRequest.getTokenData(url, what, false);
    }


    /**
     * 我的总邀请值
     *
     * @param what
     */
    public void getRecommendedValues(int what) {
        String url = ConstValues.BASE_URL + "api/v1/user/getSumRecommendedValues";
        mHttpRequest.getTokenData(url, what, false);
    }


    /**
     * 限时抢购添加订单
     *
     * @param what
     * @param addressId
     * @param orderType
     * @param itemList
     */
    public void getLimitAddOrder(int what, String addressId, String orderType, JSONArray itemList,String idNumber) {
        String url = ConstValues.BASE_URL + "api/v1/user/cashOrder/addRealityOrder";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("addressId", addressId);
            jsonObject.put("orderType", orderType);
            jsonObject.put("itemList", itemList);
            jsonObject.put("idNumber", idNumber);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        mHttpRequest.postJsonToken(url, what, "getLimitAddOrder", jsonObject);
    }


    /**
     * 租赁提交订单
     *
     * @param what
     * @param productId
     * @param addressId
     * @param skuId
     * @param rentCreations
     * @param couponId
     */
    public void getLeaseOrderAdd(int what, String productId, String addressId, String skuId, String rentCreations, String couponId) {
        String url = ConstValues.BASE_URL + "api/v1/user/leaseOrder/add";
        HashMap<String, String> paramsTreeMap = new HashMap<>();
        paramsTreeMap.put("productId", productId);
        paramsTreeMap.put("addressId", addressId);
        paramsTreeMap.put("skuId", skuId);
        paramsTreeMap.put("rentCreations", rentCreations);
        paramsTreeMap.put("couponId", couponId);
        mHttpRequest.postUpJsonToken(url, what, "getLeaseOrderAdd", paramsTreeMap, false);
    }


    /**
     * 押金方式
     *
     * @param what
     * @param orderId
     * @param isDeposit
     */
    public void updatIsDeposit(int what, String orderId, String isDeposit) {
        String url = ConstValues.BASE_URL + "api/v1/user/leaseOrder/updatIsDeposit";
        HashMap<String, String> paramsTreeMap = new HashMap<>();
        paramsTreeMap.put("orderId", orderId);
        paramsTreeMap.put("isDeposit", isDeposit);
        mHttpRequest.postUpJsonToken(url, what, "updatIsDeposit", paramsTreeMap, false);
    }

    /**
     * 租赁订单列表
     *
     * @param what
     * @param type
     * @param page
     * @param pageSize
     */
    public void getLeaseOrderQuery(int what, String type, int page, int pageSize) {
        String url = ConstValues.BASE_URL + "api/v1/user/leaseOrder/query?type=" + type + "&page=" + page + "&pageSize=" + pageSize;
        mHttpRequest.getTokenData(url, what, false);
    }

    /**
     * 租赁商品列表
     *
     * @param what
     * @param keyword
     * @param page
     * @param pageSize
     */
    public void getLeaseProductQuery(int what, String orders, String keyword
            , int page, int pageSize, String sellPrice, String sellPrices, String brandId, String isSell, String isRec,String classId,String parentClassId) {
        String url = ConstValues.BASE_URL + "api/v1/leaseProduct/query?orders=" + orders
                + "&keyword=" + keyword
                + "&page=" + page
                + "&pageSize=" + pageSize
                + "&sellPrice=" + sellPrice
                + "&sellPrices=" + sellPrices
                + "&brandId=" + brandId + "&isSell=" + isSell + "&isRec=" + isRec+"&classId="+classId+"&parentClassId="+parentClassId;
        mHttpRequest.getTokenData(url, what, false);
    }

    /**
     * 租赁订单详情
     *
     * @param what
     * @param id
     */
    public void getLeaseOrderDetail(int what, String id) {
        String url = ConstValues.BASE_URL + "api/v1/user/leaseOrder/details?id=" + id;
        mHttpRequest.getTokenData(url, what, false);
    }

    /**
     * 租赁订单每月还款
     *
     * @param what
     * @param orderId
     * @param page
     * @param pageSize
     */
    public void getLeaseOrderLogQuery(int what, String orderId, int page, int pageSize) {
        String url = ConstValues.BASE_URL + "api/v1/user/leaseOrder/logQuery?orderId=" + orderId + "&page=" + page
                + "&pageSize=" + pageSize;
        mHttpRequest.getTokenData(url, what, false);
    }


    /**
     * 是否实名认证
     * @param what
     */
    public void getIsAttestati(int what) {
        String url = ConstValues.BASE_URL + "api/v1/user/getIsAttestati";
        mHttpRequest.getTokenData(url, what, false);
    }

    /**
     * 实名认证获取token
     *
     * @param what
     */
    public void authentication(int what) {
        String url = ConstValues.BASE_URL + "api/v1/user/authentication";
        HashMap<String, String> paramsTreeMap = new HashMap<>();
        mHttpRequest.postUpJsonToken(url, what, "authentication", paramsTreeMap, false);
    }

    /**
     * 实名认证结果查询
     * @param what
     * @param verifyToken
     * @param bizId
     */
    public void authenticationResult(int what,String verifyToken,String bizId) {
        String url = ConstValues.BASE_URL + "api/v1/user/authenticationResult";
        HashMap<String, String> paramsTreeMap = new HashMap<>();
        paramsTreeMap.put("verifyToken", verifyToken);
        paramsTreeMap.put("bizId", bizId);
        mHttpRequest.postUpJsonToken(url, what, "authenticationResult", paramsTreeMap, false);
    }

    /**
     * 租赁提醒发货
     * @param what
     * @param orderId
     */
    public void reminderShipment(int what,String orderId) {
        String url = ConstValues.BASE_URL + "api/v1/user/leaseOrder/reminderShipment";
        HashMap<String, String> paramsTreeMap = new HashMap<>();
        paramsTreeMap.put("orderId", orderId);
        mHttpRequest.postUpJsonToken(url, what, "reminderShipment", paramsTreeMap, false);
    }


    /**
     * 租赁确认收货
     * @param what
     * @param orderId
     */
    public void confirmReceipt(int what,String orderId) {
        String url = ConstValues.BASE_URL + "api/v1/user/leaseOrder/confirmReceipt";
        HashMap<String, String> paramsTreeMap = new HashMap<>();
        paramsTreeMap.put("orderId", orderId);
        mHttpRequest.postUpJsonToken(url, what, "confirmReceipt", paramsTreeMap, false);
    }

    /**
     * 租赁续租
     * @param what
     * @param orderId
     */
    public void renewal(int what,String orderId) {
        String url = ConstValues.BASE_URL + "api/v1/user/leaseOrder/renewal";
        HashMap<String, String> paramsTreeMap = new HashMap<>();
        paramsTreeMap.put("orderId", orderId);
        mHttpRequest.postUpJsonToken(url, what, "renewal", paramsTreeMap, false);
    }


    /**
     * 租赁删除订单
     * @param what
     * @param orderId
     */
    public void deleteOrder(int what,String orderId) {
        String url = ConstValues.BASE_URL + "api/v1/user/leaseOrder/deleteOrder";
        HashMap<String, String> paramsTreeMap = new HashMap<>();
        paramsTreeMap.put("orderId", orderId);
        mHttpRequest.postUpJsonToken(url, what, "deleteOrder", paramsTreeMap, false);
    }

    /**
     * 申请维修
     * @param what
     * @param orderId
     * @param addressId
     * @param imgUrl
     * @param imgUrl1
     * @param imgUrl2
     * @param describe
     */
    public void repairOrder(int what,String orderId,String addressId,String imgUrl,String imgUrl1,String imgUrl2,String describe) {
        String url = ConstValues.BASE_URL + "api/v1/user/leaseOrder/repairOrder";
        HashMap<String, String> paramsTreeMap = new HashMap<>();
        paramsTreeMap.put("orderId", orderId);
        paramsTreeMap.put("addressId", addressId);
        paramsTreeMap.put("imgUrl", imgUrl);
        paramsTreeMap.put("imgUrl1", imgUrl1);
        paramsTreeMap.put("imgUrl2", imgUrl2);
        paramsTreeMap.put("describe", describe);
        mHttpRequest.postUpJsonToken(url, what, "repairOrder", paramsTreeMap, false);
    }


    /**
     * 申请还机
     * @param what
     * @param orderId
     * @param addressId
     * @param takeTime
     */
    public void returnMachine(int what,String orderId,String addressId,String takeTime) {
        String url = ConstValues.BASE_URL + "api/v1/user/leaseOrder/returnMachine";
        HashMap<String, String> paramsTreeMap = new HashMap<>();
        paramsTreeMap.put("orderId", orderId);
        paramsTreeMap.put("addressId", addressId);
        paramsTreeMap.put("takeTime", takeTime);
        mHttpRequest.postUpJsonToken(url, what, "returnMachine", paramsTreeMap, false);
    }

    /**
     * 押金使用途径
     * @param what
     */
    public void getChannel(int what) {
        String url = ConstValues.BASE_URL + "api/v1/user/getChannel";
        mHttpRequest.getTokenData(url, what, false);
    }


    /**
     * 买断金额
     * @param what
     * @param orderId
     */
    public void getBuyoutAmount(int what,String orderId) {
        String url = ConstValues.BASE_URL + "api/v1/user/leaseOrder/getBuyoutAmount?orderId="+orderId;
        mHttpRequest.getTokenData(url, what, false);
    }


    /**
     * 还款金额
     * @param what
     * @param orderLogId
     */
    public void getRepaymentAmount(int what,String orderLogId) {
        String url = ConstValues.BASE_URL + "api/v1/user/leaseOrder/repaymentAmount";
        HashMap<String, String> paramsTreeMap = new HashMap<>();
        paramsTreeMap.put("orderLogId", orderLogId);
        mHttpRequest.postUpJsonToken(url, what, "getRepaymentAmount", paramsTreeMap, false);
    }


    /**
     * 支付回调
     * @param what
     * @param orderId
     * @param wxPayType
     * @param payType
     * @param orderType
     * @param orderNo
     * @param orderTypes
     * @param consumptionType
     */
    public void getPayResults(int what,String orderId,String wxPayType,String payType,String orderType,String orderNo,String orderTypes,String consumptionType) {
        String url = ConstValues.BASE_URL + "api/v1/user/orderPay?orderId="+orderId
                +"&wxPayType="+wxPayType
                +"&payType="+payType
                +"&orderType="+orderType
                +"&orderNo="+orderNo
                +"&orderTypes="+orderTypes
                +"&consumptionType="+consumptionType;
        mHttpRequest.getTokenData(url, what, false);
    }

    /**
     * 退货换货
     * @param what
     * @param orderId
     * @param orderType
     * @param orderLogId
     * @param refundType
     * @param Reason
     * @param Explain
     * @param refundImgList
     */
    public void getExchangeReturnGoods(int what, String orderId, String orderType,String orderLogId,String refundType,String Reason,String Explain, JSONArray refundImgList) {
        String url = ConstValues.BASE_URL + "api/v1/user/cashOrder/exchangeReturnGoods ";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("orderId", orderId);
            jsonObject.put("orderType", orderType);
            jsonObject.put("orderLogId", orderLogId);
            jsonObject.put("refundType", refundType);
            jsonObject.put("Reason", Reason);
            jsonObject.put("Explain", Explain);
            jsonObject.put("refundImgList", refundImgList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mHttpRequest.postJsonToken(url, what, "getExchangeReturnGoods", jsonObject);
    }


    /**
     * 物流查询
     * @param what
     * @param orderId
     * @param orderType
     */
    public void getInquiryLogistics(int what,String orderId,String orderType) {
        String url = ConstValues.BASE_URL + "api/v1/user/leaseOrder/getInquiryLogistics?orderId="+orderId+"&orderType="+orderType;
        mHttpRequest.getTokenData(url, what, false);
    }


    /**
     * 售后详情
     * @param what
     * @param orderId
     * @param orderType
     * @param orderLogId
     */
    public void getRefundDetail(int what
            ,String orderId
            ,String orderType
            ,String orderLogId) {
        String url = ConstValues.BASE_URL + "api/v1/user/leaseOrder/getRefund?orderId="+orderId
                +"&orderType="+orderType+"&orderLogId="+orderLogId;
        mHttpRequest.getTokenData(url, what, false);
    }

    /**
     * 消息列表
     * @param what
     * @param page
     * @param pageSize
     */
    public void getMsgList(int what
            ,int page
            ,int pageSize) {
        String url = ConstValues.BASE_URL + "api/v1/user/information/query?page="+page
                +"&pageSize="+pageSize;
        mHttpRequest.getTokenData(url, what, false);
    }

    /**
     * 消息详情
     * @param what
     * @param id
     */
    public void getMsgDetail(int what
            ,String id) {
        String url = ConstValues.BASE_URL + "api/v1/user/information/details?id="+id;
        mHttpRequest.getTokenData(url, what, false);
    }

    /**
     * 未读条数
     * @param what
     */
    public void getUnread(int what) {
        String url = ConstValues.BASE_URL + "api/v1/user/information/unread";
        mHttpRequest.getTokenData(url, what, false);
    }


    /**
     * 修改个人信息
     * @param what
     * @param nickName
     * @param avatar
     * @param gender
     */
    public void getUserUpdate(int what, String nickName, String avatar,String gender) {
        String url = ConstValues.BASE_URL + "api/v1/user/update";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("nickName", nickName);
            jsonObject.put("avatar", avatar);
            jsonObject.put("gender", gender);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mHttpRequest.postJsonToken(url, what, "getUserUpdate", jsonObject);
    }


    /**
     * 提现列表
     * @param what
     * @param page
     * @param pageSize
     */
    public void getCashApplyQuery(int what,int page,int pageSize) {
        String url = ConstValues.BASE_URL + "api/v1/user/cashApply?page="+page+"&pageSize="+pageSize;
        mHttpRequest.getTokenData(url, what, false);
    }

    /**
     * 用户提现
     * @param what
     * @param applyAmount
     * @param accountJson
     */
    public void getCashApply(int what,String applyAmount,String accountJson,String realName) {
        String url = ConstValues.BASE_URL + "api/v1/user/cashApply/apply";
        HashMap<String, String> paramsTreeMap = new HashMap<>();
        paramsTreeMap.put("applyAmount", applyAmount);
        paramsTreeMap.put("accountJson", accountJson);
        paramsTreeMap.put("realName", realName);
        mHttpRequest.postUpJsonToken(url, what, "getCashApply", paramsTreeMap, false);
    }

    /**
     * 取消提现
     * @param what
     * @param id
     */
    public void getCashApplyCancel(int what,String id) {
        String url = ConstValues.BASE_URL + "api/v1/user/cashApply/abolish";
        HashMap<String, String> paramsTreeMap = new HashMap<>();
        paramsTreeMap.put("id", id);
        mHttpRequest.postUpJsonToken(url, what, "getCashApply", paramsTreeMap, false);
    }

    /**
     * 最低可提现金额
     * @param what
     */
    public void getMinimumAmount(int what) {
        String url = ConstValues.BASE_URL + "api/v1/user/cashApply/minimumAmount";
        mHttpRequest.getTokenData(url, what, false);
    }


    /**
     * 花呗预授权
     * @param what
     * @param orderId
     */
    public void gateway(int what,String orderId) {
        String url = ConstValues.BASE_URL + "api/v1/user/leaseOrder/gateway?orderId="+orderId;
        mHttpRequest.getTokenData(url, what, false);
    }

    /**
     * 重新获取信用评分可抵扣金额
     * @param what
     */
    public void getAssessment(int what) {
        String url = ConstValues.BASE_URL + "api/v1/user/getAssessment";
        mHttpRequest.getTokenData(url, what, false);
    }

    /**
     * 支付宝预授权回调
     * @param what
     * @param orderId
     */
    public void getPreLicensing(int what,String orderId) {
        String url = ConstValues.BASE_URL + "api/v1/user/preLicensing?orderId="+orderId;
        mHttpRequest.getTokenData(url, what, false);
    }

    /**
     * 优惠券条数
     * @param what
     * @param status
     * @param isAvailable
     * @param couponType
     * @param amountMoney
     */
    public void getCouponCount(int what,String status,String isAvailable,String couponType,String amountMoney) {
        String url = ConstValues.BASE_URL + "api/v1/user/userCoupon/queryCount?status="+status+"&isAvailable="+isAvailable+"&couponType="+couponType+"&amountMoney="+amountMoney;
        mHttpRequest.getTokenData(url, what, false);
    }

    /**
     * M购订单条数
     * @param what
     */
    public void getOrderNumber(int what) {
        String url = ConstValues.BASE_URL + "api/v1/user/cashOrder/orderNumber";
        mHttpRequest.getTokenData(url, what, false);
    }

    /**
     * 云客服路径
     * @param what
     * @param type
     */
    public void getReportPrepared(int what,String type) {
        String url = ConstValues.BASE_URL + "api/v1/user/reportPrepared?type="+type;
        mHttpRequest.getTokenData(url, what, false);
    }
}
