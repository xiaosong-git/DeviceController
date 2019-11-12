package com.xs.dc.bean.compose;

/**
 * 把所有的表列在这边，方便维护
 *
 * @Date 2016/7/25 17:13
 * @Author linyb
 */
public class TableList {
    /**
     * 在库
     */
    public static final String QRCODE_CSSTATUS_STOCK = "stock";
    /**
     * 生成图片
     */
    public static final String QRCODE_CSSTATUS_IMAGE = "image";
    /**
     * 下发
     */
    public static final String QRCODE_CSSTATUS_DOWNLOAD = "download";
    /**
     * 已注册
     */
    public static final String QRCODE_CSSTATUS_REGISTER = "register";
    /**
     * 作废
     */
    public static final String QRCODE_CSSTATUS_INVALID = "invalid";


    /**
     * 正常
     */
    public static final String USER_CSSTATUS_NORMAL = "normal";
    /**
     * 冻结
     */
    public static final String USER_CSSTATUS_FREEZE = "freeze";
    /**
     * 删除
     */
    public static final String USER_CSSTATUS_DEL = "del";

    /**
     * 正常
     */
    public static final String CARD_CSTATUS_NORMAL = "normal";
    /**
     * 冻结
     */
    public static final String CARD_CSTATUS_DISABLE = "disable";

    //表示 tbl_key中的密钥的正常状态
    public static final String KEY_STATUS_NORMAL = "normal";

    /**
     * 账户类型
     **/
    public static final String ACCOUNT_TYPE_BALANCE = "balance";//余额账户
    public static final String ACCOUNT_TYPE_FREEZE = "freeze";//冻结余额账户

    // 用户表
    public static final String USER = "tbl_user";
    // 用户账户表
    public static final String USER_ACCOUNT = "tbl_user_account";
    // 二维码
    public static final String QRCODE = "tbl_qrcode";
    //手机验证码表
    public static final String CODE = " tbl_code ";
    // 业务参数
    public static final String PARAM = " tbl_params ";
    // 系统参数
    public static final String SYS_PARAM = " t_system_param ";
    // 公告
    public static final String NOTICE = " tbl_notice ";
    // 用户已推送的最大公告
    public static final String USER_NOTICE = " tbl_user_notice ";
    // 首页广告轮播图
    public static final String AD_BANNER = " tbl_ad_banner ";
    //字典表
    public static final String DICT_ITEM = "  t_dict_item ";
    //密钥表
    public static final String KEY = " tbl_key";
    //机构表
    public static final String ORG = " t_org";
    //新闻表
    public static final String NEWS = " tbl_news";
    //通讯录数据
    public static final String USER_FRIEND = " tbl_user_friend";
    //访问记录表
    public static final String VISITOR_RECORD = "tbl_visitor_record";
    //公司名称
    public static final String COMPANY = "tbl_company";
    //上位机
    public static final String POSP = "tbl_posp";
    //上位机
    public static final String VISITOR_RECORD_MAXID = "tbl_visitor_record_maxId";
    //访问通过记录表
    public static final String VISITOR_ACCESS_RECORD = "t_visit_access_record";
}