package com.heidan.enums;


/**
 * Created by SqMax on 2018/3/18.
 */
public enum ResultEnum {

    SUCCESS(0,"成功"),

    PARAM_ERROR(1,"参数不正确"),

    RESULT_NULL(2,"结果为空"),

    SHOP_NOT_EXIST(3,"店铺不存在，请重试"),

    PRODUCT_NOT_EXIT(10,"商品不存在"),

    PRODUCT_STOCK_ERROR(11,"商品库存不正确"),

    ORDER_NOT_EXIST(12,"订单不存在"),

    ORDERDETAIL_NOT_EXIST(13,"订单详情不存在"),

    ORDER_STATUS_ERROR(14,"订单状态不正确"),

    ORDER_UPDATE_FAIL(15,"订单更新失败"),

    ORDER_DETAIL_EMPTY(16,"订单详情为空"),

    ORDER_PAY_STATUS_ERROR(17,"订单支付状态不正确"),

    CART_EMPTY(18,"购物车不能为空"),

    ORDER_OWNER_ERROR(19,"该订单不属于当前用户"),

    WECHAT_MP_ERROR(20,"微信公众账号方面错误"),

    WXPAY_NOTIFY_MONEY_VERIFY(21,"微信支付异步通知金额校验不通过"),

    ORDER_CANCEL_SUCCESS(22,"订单取消成功"),

    ORDER_FINISH_SUCCESS(23,"订单完结成功"),

    PRODUCT_STATUS_ERROR(24,"商品状态不正确"),

    LOGIN_FAIL(25,"登录失败，登录信息不正确"),

    LOGOUT_SUCCESS(26,"登出成功"),

    USER_INEXIST(30,"用户不存在，请登录"),

    ROLE_FAIL(31,"您当前没有权限访问该接口，请联系管理员"),

    ROLE_PASS_FAIL(32,"权限认证失败，请联系管理员"),

    SHOP_INEXIST(40,"店铺主图为空"),

    TABLE_INEXIST(50,"桌号不存在或者已停止使用，请联系店员"),

    TABLE_LIST_NULL(51,"该店铺没有桌号或存在异常，请联系店员"),

    PRODUCT_INEXIST(60,"商品不存在"),

    CREATEORDER_FAIL(70,"下单失败"),

    QUEUE_NULL(80,"该店铺排队不存在"),

    QUEUE_FAIL(81,"选择用餐人数超限，请联系店员"),

    QUEUE_ORDER_FAIL(82,"排队取号失败，请联系店员"),

    QUEUE_ORDER_HAVED(83,"您已取过号，请耐心等待"),

    QUEUE_NUMBER_STATUS_ERROR(84,"该排队号已处理，请勿重试"),

    QUEUE_NOT_LASTEST(85,"该排队号非最新，请下拉刷新"),

    SYSTEM_ERROR(100,"系统错误，请联系管理员"),

    REGISTER_FAIL(110,"注册产品经理失败，请联系管理员"),

    REGISTER_HAVE(111,"您已经注册过产品经理，请勿重试！"),

    REGISTER_Error(112,"已注册，如有其他问题请联系管理员"),

    REGISTER_SHARE_FAIL(120,"您已是分享达人，请勿重复注册"),

    SHARE_USER_ERROR(121,"达人收益信息出错，请重试！"),

    WTTHDRAWFAIL(122,"提现金额超限，请重试"),

    WITHDRAWERROR(123,"提交提现记录失败，请联系管理员"),

    SHARE_USER_NOT_EXIST(124,"达人信息不存在"),

    ORDERNO_NULL(130,"订单号不存在，请确认订单号"),

    ORDER_CANNOT_CANCEL(131,"订单已取消或已确认，无法操作"),

    ORDER_NOT_CONFIRM(132,"订单未确认或已结账，请确认"),

    ORDER_NULL(133,"订单为空"),

    VERIFY_FAIL(134,"核销码不存在或订单已核销"),

    MERCHANT_NOT_SHOP(135,"您当前尚未绑定店铺，请联系管理员开通"),

    MERCHANT_OPEN_FAIL(136,"开通失败，请联系管理员"),

    MERCHANT_HAVE_OPEN(137,"该用户已开通该店铺，请勿重复开通"),

    GET_GOODS_POSTER(140,"生成产品海报出错，请联系联妹客服"),

    SHOP_UPDATE_FAIL(141,"商店修改失败"),

    MERCHANT_NOT_TABLE(142,"当前店铺未添加桌号"),

    ADD_TABLE_FAIL(143,"新增桌位失败"),

    DEL_TABLE_FAIL(144,"删除桌位失败"),

    UPDATE_TABLE_FAIL(145,"修改桌位失败"),

    UPDATE_GOODS_FAIL(146,"修改套餐失败"),

    NULL_MODEL(147,"没有对应的套餐规格"),

    ADD_MODEL_FAIL(148,"添加套餐规格失败"),

    DEL_MODEL_FAIL(149,"删除套餐规格失败"),

    UPD_MODEL_FAIL(150,"修改套餐规格失败"),

    NO_USER(151,"未查询到用户"),

    UPDATE_LEVEL_FAIL(152,"修改达人失败"),

    ALREADY_BIG(153,"用户已经是大达人"),

    ADD_MONEY_FAIL(154,"佣金添加失败"),

    UPD_ROLE(155,"修改权限失败"),

    CREATE_QRCODE_FAIL(156,"生成二维码失败，请联系技术人员"),

    GET_Merchant_Money_FAIL(157,"获取金库金额失败，请联系技术人员"),

    BANG_COMPANY(158,"您当前没有绑定公司"),

    NO_SHOP(159,"店铺不存在"),

    SmallTOBig(160,"当前用户非达人或不存在，无法升级大达人"),

    Focus_Fail(161,"关注店铺失败，请联系管理员"),

    ALREADY_MEMBERS(162,"您已经领取过储值卡了"),

    NO_MEMBERS(163,"您当前不是会员，请申请成为会员"),

    NO_ACTIVE(164,"当前未设置充值兑换积分"),

    HAVE_ROLE_REMIND(165,"该用户已开通管理员公众号提醒，请勿重复操作"),

    NO_ATTION(166,"该用户尚未关注联妹，请提醒该用户关注联妹公众号后再进行操作"),

    HAVE_MERCHANT_REMIND(167,"该用户已开通商户公众号提醒，请勿重复操作"),

    Withdraw_Have(168,"该提现已确认或失败，请确认！"),

    Withdraw_Fail(169,"提现失败，请联系技术人员"),

    CREATE_FAIF(170,"创建失败"),

    UPD_FAIL(171,"修改失败"),

    NO_GOODSM(172,"没有该套餐"),

    NO_GOODSU(173,"当前商铺没有该产品"),

    Have_Kanjia(174,"您已发起过该项砍价，请勿重试"),

    HAVE_PING(175,"您当前已经在拼团该商品"),

    PING_FAIL(176,"拼团失败"),

    PING_JIESHU(177,"该拼团活动已经结束"),

    PING_KUCUN_FAIL(178,"库存不够，拼团失败"),

    OUT_INTEGRAL(179,"积分不够，请充值");



    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
