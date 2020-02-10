package com.tianzhi.shop520.util;


public class BaseConstant {
    public static final String  Privatekey = "nMYKTjV164m3Adun0gIruKVKlhnYBTQd";
    public  static final String VERSIONNUMBER ="/v1";
//    //测试环境
//    public static final String TestUrl = "http://47.96.179.192:8083/user/app/api";
////    推荐模块
//    public static final String  RECOMMENDURL = "http://47.96.179.192:8084/recommend/app/api";
//    /*商品模块*/
//    public static final String GoodsUrl = "http://47.96.181.35:8083/item/app/api";
//    /*订单*/
//    public static  final  String OrderUrl = "http://47.96.181.35:8082/order/app/api";

//正式环境
    public static final String  TestUrl = "http://120.55.43.176:8082/user/app/api";
    /*推荐模块*/
    public static final String  RECOMMENDURL = "http://120.55.43.176:8084/recommend/app/api";
//    /*商品模块*/
    public static final String GoodsUrl = "http://39.108.129.215:8082/item/app/api";
////    /*订单*/
    public static  final  String OrderUrl = "http://39.108.129.215:8084/order/app/api";
    /*版本更新*/
    public static final String APPVERISON = "/appVerison";
    /*登录*/
    public static final String Login = "/login";
    /*获得验证码*/
    public static final String GETTELCODE = "/getTelCode/phone/type";
    /*注册*/
    public static final String REGIST = "/regist";
    /*找回密码*/
    public static final String FINDPASSWORD = "/findPassWord";
    /*修改密码*/
    public static final String UPDATEPASSWORD ="/updatePassWord";
    /*修改用户资料*/
   public static final String UPDATEUSER = "/updateUser";
    /*上传头像*/
    public static final String UPLOADHEAD = "/uploadHead";
    /*查询商品分类目录*/
    public static final String LISTCATEGORY = "/listCategory";
    /*根据分类查询商品信息*/
    public static final String QUERYITEMBYCATEGORY = "/queryItemByCategory";
    /*根据商品id查询商品详情信息*/
    public static final String QUERYITEMDETAILBYID = "/queryItemDetailById";
    /*分页查询展示商品*/
    public static final String GOODLIST = "/list";
    /*支付宝接口*/
    public static  final String ZFBAPPALIPAY = "/zfbAPPaliPay";
    /*微信app支付*/
    public static  final String WECHATAPPPAY = "/wechatAppPay";
    /*删除图片*/
    public static final String DELETEFILE = "/deleteFile";
    /*获取用户信息*/
    public static final String INSTANTUPDATEUSER = "/instantUpdateUser";
    /*上传二维码图片*/
    public static  final String UPDATEQRCODE = "/updateQRCode";
    /*我的推荐*/
    public static  final String GETRECOMMEND = "/getRecommend/userId/userToken";
    /*爱心值消费记录*/
    public static final String GETCONSUMPTIONLIST = "/consumptionList/userId/userToken";
/*随机推荐商品*/
    public static  final String RECOMMENDITEM = "/recommendItem";
    /*添加购物车*/
    public static  final String ADDCART = "/addCart";
    /*购物车信息*/
    public static  final String LISTCART="/listCart";
    /*购物车商品数量修改*/
    public static final String UPDATECART = "/updateCart";
    /*购物车删除*/
    public static  final  String DELETECART = "/deleteCart";
    /*收货地址查询*/
    public static  final String LISTRECEIVERINFO="/listReceiverinfo";
    /*添加或者修改收货人地址*/
    public static  final String CREATERECEIVERINFO = "/createReceiverInfo";

    /*订单状态分页查询订单列表信息*/
    public static  final String QUERYORDERBYUSERID="/queryOrderByUserId";
    /*支付宝登录授权信息回调*/
    public static  final String ALIPAYLOGINAUTHNOTIFY="/alipayLoginAuthNotify";

    /*商城首页轮播图*/
    public static final String ITEMSHOW ="/itemShow";
    /*App首页广告*/
    public static final String APPINDEX ="/appIndex";
/*确认订单*/
public static final String VALIDATEORDER  = "/validateOrder";
    /*生成订单*/
    public static final String CREATEORDER = "/createOrder";
    /*取消订单*/
    public static final String ALERTORDERSTATUS = "/alertOrderStatus";
    /*订单id查询订单详细信息*/
    public static final String QUERYORDERBYID = "/queryOrderById";

    /*爱心值提取*/
    public static final  String WITHDRAWALS="/withdrawals";
    /*授权*/
    public static final  String ALIPAYLOGINAUTH="/alipayLoginAuth";
    /*回显用户的收货人信息在编辑页面type=default
删除该条收货地址数据 type=delete
*/
    public static  final String OPTRECIVERINFO = "/optReciverInfo";
    /*商品模糊查询*/
    public static  final String QUERYITEMBYITEMTITLE = "/queryItemByItemTitle";
    /*订单物流查询*/
    public static final String ORDERLOGISTICS = "/orderLogistics/{com}/{no}";

///*会员升级套餐*/
//public static final String Itemid = "/id";



}
