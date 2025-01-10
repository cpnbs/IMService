package com.cp.im.constants;

/**
 * @Description redis-key
 * @Author wangyingjie
 * @Date 11:27 2020/4/20
 * @Modified
 */
public class RedisKey {

    /**
     * [KEY_SMS_CODE_]
     */
    public static final String KEY_SMS_CODE = "KEY_SMS_CODE_";

    /**
     * [KEY_TOKEN]
     */
    public static final String KEY_TOKEN = "KEY_TOKEN:";

    public static final String KEY_GAME_USER_LOGIN = "KEY_GAME_USER_LOGIN:";

    public static final String KEY_NETTY_USER_LOGIN = "KEY_NETTY_USER_LOGIN:";


    /**
     * [KEY_GEO]
     */
    public static final String KEY_GEO = "KEY_GEO";

    /**
     * [KEY_ONLINE]
     */
    public static final String KEY_ONLINE = "KEY_ONLINE";

    /**
     * 用戶游戲狀態
     */
    public static final String KEY_PLAYSTATUS = "KEY_PLAYSTATUS";

    /**
     * [签到奖励]
     */
    public static final String KEY_SIGN_REWARD = "KEY_SIGN:REWARD";

    /**
     * [日常任务]
     */
    public static final String KEY_TASK_CENTER = "KEY_TASK_CENTER:";

    /**
     * [每日任务记录]
     */
    public static final String KEY_TASK_DAY = "KEY_TASK_DAY:";

    /**
     * [每周任务记录]
     */
    public static final String KEY_TASK_WEEK = "KEY_TASK_WEEK:";

    /**
     * [任务活跃值]
     */
    public static final String KEY_TASK_ACTIVE = "KEY_TASK_ACTIVE:";

    /**
     * [通知-会员到期续费通知]
     */
    public static final String KEY_NOTICE_MEMBER_YES = "KEY_NOTICE:MEMBER:YES:";

    /**
     * [通知-非会员过期续费通知]
     */
    public static final String KEY_NOTICE_MEMBER_NOT = "KEY_NOTICE:MEMBER:NOT:";

    /**
     * [活动-开始通知]
     */
    public static final String KEY_NOTICE_ACTIVITY_START = "KEY_NOTICE:ACTIVITY:START:";

    /**
     * [活动-结束通知]
     */
    public static final String KEY_NOTICE_ACTIVITY_END = "KEY_NOTICE:ACTIVITY:END:";

    /**
     * 未读访客数量.
     */
    public static final String KEY_TIPS_UNREAD_VISITOR = "KEY_TIPS_UNREAD_VISITOR:";

    /**
     * [扭蛋机器人]
     */
    public static final String KEY_GASHAPON_ROBOT = "KEY_GASHAPON_ROBOT:";

    /**
     * 丹枫迎秋奖池[KEY_ACT0001_JACKPOT]
     */
    public static final String KEY_ACT0001_JACKPOT = "KEY_ACT0001:JACKPOT";

    /**
     * 丹枫迎秋用户[KEY_ACT0001_USER_JACKPOT]
     */
    public static final String KEY_ACT0001_USER_JACKPOT = "KEY_ACT0001:USER_JACKPOT";

    /**
     * 丹枫迎秋奖章[KEY_ACT0001_USER_CURRENCY]
     */
    public static final String KEY_ACT0001_USER_CURRENCY = "KEY_ACT0001:USER_CURRENCY";

    /**
     * 丹枫迎秋计数[KEY_ACT0001_USER_COUNT]
     */
    public static final String KEY_ACT0001_USER_COUNT = "KEY_ACT0001:USER_COUNT";

    /**
     * [KEY_USER_BASE] 房间禁言用户
     */
    public static final String KEY_VOICE_ROOM = "KEY_VOICE_ROOM:";

    /**
     * 踢出房间用户
     */
    public static final String KEY_KICK_ROOM = "KEY_KICK_ROOM:";

    /**
     * 头像框装饰
     */
    public static final String KEY_AVATARFRAME = "KEY_AVATARFRAME:";

    /**
     * 聊天框装饰
     */
    public static final String KEY_CHATFRAME = "KEY_CHATFRAME:";

    /**
     * 游戏装饰
     */
    public static final String KEY_GAME = "KEY_GAME:";

    /**
     * 皮肤
     */
    public static final String KEY_SKIN = "KEY_SKIN:";

    /**
     * 红包
     */
    public static final String KEY_RP = "KEY_RP:";

    /**
     * 红包信息.
     */
    public static final String KEY_RP_INFO = "KEY_RP_INFO:";
    /**
     * 红包过期时间
     */
    public static final long KEY_RP_TIME = 24 * 3600;

    /**
     * 礼物
     */
    public static final String KEY_GIFT = "KEY_GIFT:";
    public static final long KEY_GIFT_TIME = 24 * 3600;

    public static final String KEY_ACTIVITY = "KEY_ACTIVITY:";

    /**
     * 幸运礼物
     */
    public static final String KEY_GIFT_LUCKY = "KEY_GIFT_LUCKY";
    public static final String KEY_GIFT_LUCKY_BRONZE = "KEY_GIFT_LUCKY_BRONZE";//青铜
    public static final String KEY_GIFT_LUCKY_SILVER = "KEY_GIFT_LUCKY_SILVER";//白银
    public static final String KEY_GIFT_LUCKY_GOLD = "KEY_GIFT_LUCKY_GOLD";//黄金
    public static final String KEY_GIFT_LUCKY_DIAMOND = "KEY_GIFT_LUCKY_DIAMOND";//钻石 暂不开放


    public static final String KEY_GIFT_LUCKY_BAG = "KEY_GIFT_LUCKY_BAG";
    public static final String KEY_GIFT_LUCKY_BAG_189 = "KEY_GIFT_LUCKY_BAG_189";
    public static final String KEY_GIFT_LUCKY_BAG_190 = "KEY_GIFT_LUCKY_BAG_190";
    public static final String KEY_GIFT_LUCKY_BAG_191 = "KEY_GIFT_LUCKY_BAG_191";
    public static final String KEY_GIFT_LUCKY_BAG_192 = "KEY_GIFT_LUCKY_BAG_192";
    public static final String KEY_GIFT_LUCKY_BAG_193 = "KEY_GIFT_LUCKY_BAG_193";
    public static final String KEY_GIFT_LUCKY_BAG_194 = "KEY_GIFT_LUCKY_BAG_194";

    public static final String KEY_GIFT_LUCKY_MAGIC_BOX = "KEY_GIFT_LUCKY_MAGIC_BOX";
    public static final String KEY_GIFT_LUCKY_MAGIC_BOX_286 = "KEY_GIFT_LUCKY_MAGIC_BOX_286";
    public static final String KEY_GIFT_LUCKY_MAGIC_BOX_294 = "KEY_GIFT_LUCKY_MAGIC_BOX_294";

    public static final String KEY_GIFT_LUCKY_CONSTELLATION_BAG = "KEY_GIFT_LUCKY_CONSTELLATION_BAG";
    public static final String KEY_GIFT_LUCKY_CONSTELLATION_BAG_397 = "KEY_GIFT_LUCKY_CONSTELLATION_BAG_397";
    public static final String KEY_GIFT_LUCKY_CONSTELLATION_BAG_406 = "KEY_GIFT_LUCKY_CONSTELLATION_BAG_406";

//    public static final long KEY_GIFT_LUCKY_TIME = 7 * 24 * 3600;
    /**
     * 赠送礼物数量
     */
    public static final String KEY_GIFT_GIVE_NUM = "KEY_GIFT_GIVE_NUM:";

    /**
     * 表情
     */
    public static final String KEY_EM = "KEY_EM:";

    /**
     * 语音房排麦
     */
    public static final String KEY_ROOM_MIC = "KEY_ROOM_MIC:";

    /**
     * 接单语音排序
     */
    public static final String KEY_ROOM_ORDER_MIC = "KEY_ROOM_ORDER_MIC:";

    /**
     * 语音房开播状态
     */
    public static final String KEY_ROOM_STATE = "KEY_ROOM_STATE:";

    /**
     * 语音房用户
     */
    public static final String KEY_ROOM_USER = "KEY_ROOM_USER:";

    /**
     * 语音房登录
     */
    public static final String KEY_ROOM_LOGIN = "KEY_ROOM_LOGIN:";

    /**
     * 语音房登录
     */
    public static final String KEY_ROOM_TASK = "KEY_ROOM_TASK:";

    /**
     * 语音房开播平台记录
     */
    public static final String KEY_ROOM_BROADCAST = "KEY_ROOM_BROADCAST:";

    /**
     * 语音房当前播放音乐
     */
    public static final String KEY_ROOM_PLAY_MUSIC = "KEY_ROOM_PLAY_MUSIC:";

    /**
     * 语音房公屏状态
     */
    public static final String KEY_ROOM_SCREEN = "KEY_ROOM_SCREEN:";

    /**
     * 进入指定房间的机器人
     */
    public static final String ROBOT_ROOM = "ROBOT_ROOM:";

    /**
     * 进入指定房间的机器人
     */
    public static final String ROBOT_ROOM_IDS = "ROBOT_ROOM_IDS";

    /**
     * 语音房全局排行过滤.
     */
    public static final String KEY_VOICE_RANK_FILTER = "KEY_VOICE_RANK_FILTER";

    /**
     * PK
     */
    public static final String KEY_PK = "KEY_PK:";

    /**
     * 进入宠物游戏
     */
    public static final String KEY_PETS_ONLINE = "KEY_PETS:ONLINE";

    /**
     * 魅力排行榜
     */
    public static final String KEY_CHARM_DEDICATE = "KEY_CHARM_DEDICATE:";

    /**
     * 摩天轮抽奖排行榜
     */
    public static final String KEY_FERRISWHEEL_DEDICATE = "KEY_FERRISWHEEL_DEDICATE:";

    /**
     * 漂流瓶抽奖排行榜
     */
    public static final String KEY_DIRFT_BOTTLE_DEDICATE = "KEY_DIRFT_BOTTLE_DEDICATE:";

    /**
     * 中秋节
     */
    public static final String KEY_MID_DAUTUMN_FESTIVAL_DEDICATE = "KEY_MID_DAUTUMN_FESTIVAL_DEDICATE:";

    /**
     * 摩天轮活动
     */
    public static final String KET_FERRISWHEEL_ACTIVITY = "KET_FERRISWHEEL_ACTIVITY";

    /**
     * 漂流瓶活动
     */
    public static final String DRIFTBOTTLE_ACTIVITY = "KET_DRIFTBOTTLE_ACTIVITY";

    /**
     * 中秋节活动
     */
    public static final String MIDAUTUMN_ACTIVITY = "KEY_MIDAUTUMN_ACTIVITY";

    /**
     * 特殊礼物连续标记信息(hash).
     */
    public static final String KEY_GIFT_SIGN_EFFECTS = "KEY_GIFT_SIGN_EFFECTS";

    /**
     * 语音房赠送礼物连续标记(key + userId + giftId + continuousFlag).
     */
    public static final String KEY_VOICE_GIVE_GIFT_SIGN = "KEY_VOICE_GIVE_GIFT_SIGN:";

    /**
     * 语音房公屏消息数量.
     */
    public static final String KEY_VOICE_SEND_MSG_NUM = "KEY_VOICE_SEND_MSG_NUM:";

    /**
     * 语音房公屏消息限制.
     */
    public static final String KEY_VOICE_SEND_MSG_SIGN = "KEY_VOICE_SEND_MSG_SIGN:";

    /**
     * 用户对应礼物抽取次数
     */
    public static final String KEY_VOICE_FERRISWHELL_USER_GIFT_NUM = "KEY_VOICE_FERRISWHELL_USER_GIFT_NUM:";
    /**
     * 奖池总共抽取次数
     */
    public static final String KEY_VOICE_FERRISWHELL_TOTAL_NUM = "KEY_VOICE_FERRISWHELL_TOTAL_NUM";

    public static final String KEY_VOICE_FERRISWHELL_GIFT_NUM = "KEY_VOICE_FERRISWHELL_GIFT_NUM:";

    /**
     * 陪玩技能
     */
    public static final String PLAY_SKILLS = "KEY_PLAY_SKILLS";

    /**
     * 宝藏功能说明
     */
    public static final String TREASURE_ACTIVITY_EXPLAIN = "TREASURE_ACTIVITY_EXPLAIN";

    /**
     * 通话状态
     */
    public static final String CALL_STATUS = "CALL_STATUS:";

    /**
     * 新人奖励
     */
    public static final String NEW_TALENT_AWARD = "NEW_TALENT_AWARD:";

    /**
     * 派单要求
     */
    public static final String ORDER_REQUIRED = "ORDER_REQUIRED:";

    /**
     * 摩天轮奖励奖池额度
     */
    public static final String KEY_VOICE_FERRISWHELL_GIFT_QUOTA = "KEY_VOICE_FERRISWHELL_GIFT_QUOTA";

    /**
     * 摩天轮奖励奖池基础额度
     */
    public static final String KEY_VOICE_FERRISWHELL_GIFT_BASE_QUOTA = "KEY_VOICE_FERRISWHELL_GIFT_BASE_QUOTA";
    /**
     * 奖池奖励
     */
    public static final String KEY_VOICE_FERRISWHELL_REWARD_GIFT = "KEY_VOICE_FERRISWHELL_REWARD_GIFT";

    /**
     * 摩天轮抽奖全服通告
     */
    public static final String KEY_VOICE_FERRISWHELL_GIFT_NOTICE = "KEY_VOICE_FERRISWHELL_GIFT_NOTICE";

    /**
     * 摩天轮抽奖公屏通告
     */
    public static final String KEY_VOICE_FERRISWHELL__DRAW_GIFT_NOTICE = "KEY_VOICE_FERRISWHELL__DRAW_GIFT_NOTICE";


    /**
     * 漂流瓶抽奖全服通告
     */
    public static final String KEY_VOICE_DRIFT_BOTTLE_GIFT_NOTICE = "KEY_VOICE_DRIFT_BOTTLE_GIFT_NOTICE:";

    /**
     * 漂流瓶抽奖公屏通告
     */
    public static final String KEY_VOICE_DRIFT_BOTTLE_DRAW_GIFT_NOTICE = "KEY_VOICE_DRIFT_BOTTLE_DRAW_GIFT_NOTICE:";

    /**
     * 摩天轮抽奖次数
     */
    public static final String KEY_VOICE_FERRISWHELL_USER_NUM = "KEY_VOICE_FERRISWHELL_USER_NUM:";

    /**
     * 漂流瓶抽奖次数
     */
    public static final String KEY_VOICE_DRIFT_BOTTLE_USER_NUM = "KEY_VOICE_DRIFT_BOTTLE_USER_NUM:";

    /**
     * 漂流瓶总次数
     */
    public static final String KEY_VOICE_DRIFT_BOTTLE_NUM = "KEY_VOICE_DRIFT_BOTTLE_NUM:";

    /**
     * 魔法占卜换皮
     */
    public static final String KEY_VOICE_DRIFT_NEW_BOTTLE_NUM = "KEY_VOICE_DRIFT_NEW_BOTTLE_NUM:";

    /**
     * 用户抽奖总次数
     */
    public static final String KEY_VOICE_DRIFT_BOTTLE_USER_TOTAL_NUM = "KEY_VOICE_DRIFT_BOTTLE_USER_TOTAL_NUM:";

    /**
     * 漂流瓶抽奖概率
     */
    public static final String KEY_VOICE_DRIFT_BOTTLE_GIFT_PRO = "KEY_VOICE_DRIFT_BOTTLE_GIFT_PRO:";

    /**
     * 魔法占卜换皮
     */
    public static final String KEY_VOICE_DRIFT_BOTTLE_NEW_GIFT_PRO = "KEY_VOICE_DRIFT_BOTTLE_NEW_GIFT_PRO:";

    /**
     * 漂流瓶个人奖池
     */
    public static final String KEY_VOICE_DRIFT_BOTTLE_GIFT_USER_PRO = "KEY_VOICE_DRIFT_BOTTLE_GIFT_USER_PRO:";

    /**
     * 团队PK
     */
    public static final String KEY_VOICE_PK_ROOM_TEAM = "KEY_VOICE_PK_ROOM_TEAM:";

    /**
     * 支付限制
     */
    public static final String KEY_SWITCH_PAY = "KEY_SWITCH:PAY";

    /**
     * 背包礼物数量
     */
    public static final String KEY_VOICE_GIFT_CODE_NUM = "KEY_VOICE_GIFT_CODE_NUM:";

    /**
     * 中秋活动
     */
    public static final String AUTUMN_FESTIVAL = "AUTUMN_FESTIVAL:";

    /**
     * 用户抽奖次数
     */
    public static final String USER_NUM = "USER_NUM:";

    /**
     * 抽奖标准
     */
    public static final String DRAW_STANDARD = "DRAW_STANDARD:";

    /**
     * 抽奖时间
     */
    public static final String DRAW_TIME_OUT = "DRAW_TIME_OUT:";

    /**
     * 总抽奖次数
     */
    public static final String TOTAL_DRAW_NUM = "TOTAL_DRAW_NUM:";
    /**
     * 礼物概率
     */
    public static final String GIFT_PRO = "GIFT_PRO:";


    /**
     * 当前轮次
     */
    public static final String ROUNDS = "ROUNDS";

    /**
     * 支付宝PC支付
     */
    public static final String ALIPAY = "ALIPAY:";

    /**
     * 金额封锁.
     */
    public static final String KEY_AMOUNT_BLOCKADE = "KEY_AMOUNT_BLOCKADE";

    /**
     * 贵族标识.
     */
    public static final String KEY_MEMBER_INDEX = "KEY_MEMBER_INDEX";

    /**
     * 特权信息.
     */
    public static final String KEY_MEMBER_PRIVILEGE = "KEY_MEMBER_PRIVILEGE";

    /**
     * 设置密码提示.
     */
    public static final String KEY_PWD_TIPS = "KEY_PWD_TIPS";

    /**
     * 设置密码操作时间.
     */
    public static final String KEY_PWD_ACTION_LOCK = "KEY_PWD_ACTION_LOCK";

    /**
     * 账户乐钻余额
     */
    public static final String KEY_ACCOUT_HAPPYDIAMOND = "KEY_ACCOUT_HAPPYDIAMOND:";

    /**
     * 情侣活动阶段.
     */
    public static final String KEY_VALENTINES_STAGE = "KEY_VALENTINES_STAGE";

    /**
     * 系统配置
     */
    public static final String KEY_CONFIG_MAJLIS = "KEY_CONFIG_MAJLIS";


    /**
     * 用户语言
     */
    public static final String KEY_USER_LANG = "KEY_USER_LANG:";

    /**
     * [每日cp陪伴经验值]
     */
    public static final String KEY_RELATION_TODAY_STAT = "KEY_RELATION_TODAY_STAT:";

    /**
     * [音乐播放]
     */
    public static final String KEY_MUSIC_STATUS = "KEY_MUSIC_STATUS:";
    /**
     * 设备标识
     */
    public static final String KEY_DEVICE_ID = "KEY_DEVICE_ID:";
}
