package com.cp.im.error;

import com.cp.im.result.BaseCodeMsg;
import com.cp.im.utils.MessageUtils;

/**
 * @author WangCaiWen
 * Created on 2020/3/19 9:27
 */
public class ErrorCode {

    /**
     * common
     */
    public static final int EXCEPTION_CODE = 201;

    public static final int ERROR_CODE = 201;

    public static final BaseCodeMsg DATA_ERROR = BaseCodeMsg.app(100, MessageUtils.getLocale("errorCode.DATA_ERROR"));

    public static final BaseCodeMsg NO_PERMISSION = BaseCodeMsg.app(101, MessageUtils.getLocale("errorCode.NO_PERMISSION"));
    public static final BaseCodeMsg NO_PERMISSION_VOICE = BaseCodeMsg.app(101, MessageUtils.getLocale("errorCode.NO_PERMISSION_VOICE"));
    public static final BaseCodeMsg NO_DATA = BaseCodeMsg.app(102, MessageUtils.getLocale("errorCode.NO_DATA"));
    public static final BaseCodeMsg ERROR_OPERATION = BaseCodeMsg.app(103, MessageUtils.getLocale("errorCode.ERROR_OPERATION"));

    public static final BaseCodeMsg NETWORK_ERROR = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.NETWORK_ERROR"));

    public static final BaseCodeMsg NOT_ENOUGH_DIAMOND = BaseCodeMsg.app(10000, MessageUtils.getLocale("errorCode.NOT_ENOUGH_DIAMOND"));
    public static final BaseCodeMsg NOT_ENOUGH_GOLD = BaseCodeMsg.app(10001, MessageUtils.getLocale("errorCode.NOT_ENOUGH_GOLD"));
    public static final BaseCodeMsg NOT_ENOUGH_BEANS_COIN = BaseCodeMsg.app(10009, MessageUtils.getLocale("errorCode.NOT_ENOUGH_BEANS_COIN"));
    public static final BaseCodeMsg NOT_ENOUGH_GASHAPON = BaseCodeMsg.app(10002, MessageUtils.getLocale("errorCode.NOT_ENOUGH_GASHAPON"));
    public static final BaseCodeMsg NOT_ENOUGH_CURRENCY_ACT0001 = BaseCodeMsg.app(10003, MessageUtils.getLocale("errorCode.NOT_ENOUGH_CURRENCY_ACT0001"));
    public static final BaseCodeMsg NOT_ENOUGH_TICKET = BaseCodeMsg.app(10004, MessageUtils.getLocale("errorCode.NOT_ENOUGH_TICKET"));
    public static final BaseCodeMsg NOT_ENOUGH_GIFT = BaseCodeMsg.app(10005, MessageUtils.getLocale("errorCode.NOT_ENOUGH_GIFT"));
    public static final BaseCodeMsg NOT_FAIL_GIFT = BaseCodeMsg.app(10005, MessageUtils.getLocale("errorCode.NOT_FAIL_GIFT"));
    public static final BaseCodeMsg PLAY_TO_EXAMINE = BaseCodeMsg.app(10006, MessageUtils.getLocale("errorCode.PLAY_TO_EXAMINE"));
    public static final BaseCodeMsg J16_NO_POPUP = BaseCodeMsg.app(10007, MessageUtils.getLocale("errorCode.J16_NO_POPUP"));

    /**
     * user
     */
    public static final BaseCodeMsg SMS_CODE_EXPIRED = BaseCodeMsg.app(1000, MessageUtils.getLocale("errorCode.SMS_CODE_EXPIRED"));
    public static final BaseCodeMsg SMS_CODE_DIFFERENCE = BaseCodeMsg.app(1001, MessageUtils.getLocale("errorCode.SMS_CODE_DIFFERENCE"));
    public static final BaseCodeMsg SMS_SEND_ERROR = BaseCodeMsg.app(1002, MessageUtils.getLocale("errorCode.SMS_SEND_ERROR"));

    public static final BaseCodeMsg LOGIN_EXPIRED = BaseCodeMsg.app(1010, MessageUtils.getLocale("errorCode.LOGIN_EXPIRED"));
    public static final BaseCodeMsg LOGIN_APPLE_FAIL = BaseCodeMsg.app(1011, MessageUtils.getLocale("errorCode.LOGIN_APPLE_FAIL"));

    public static final BaseCodeMsg ACCOUNT_EXISTS = BaseCodeMsg.app(1020, MessageUtils.getLocale("errorCode.ACCOUNT_EXISTS"));
    public static final BaseCodeMsg ACCOUNT_NOT_EXISTS = BaseCodeMsg.app(1021, MessageUtils.getLocale("errorCode.ACCOUNT_NOT_EXISTS"));
    public static final BaseCodeMsg ACCOUNT_BINDING = BaseCodeMsg.app(1022, MessageUtils.getLocale("errorCode.ACCOUNT_BINDING"));
    public static final BaseCodeMsg ACCOUNT_TO_UNBINDING = BaseCodeMsg.app(1023, MessageUtils.getLocale("errorCode.ACCOUNT_TO_UNBINDING"));
    public static final BaseCodeMsg ACCOUNT_LOGINOUT = BaseCodeMsg.app(1024, MessageUtils.getLocale("errorCode.ACCOUNT_LOGINOUT"));
    public static final BaseCodeMsg ACCOUNT_ILLEGAL = BaseCodeMsg.app(1025, MessageUtils.getLocale("errorCode.ACCOUNT_ILLEGAL"));
    public static final BaseCodeMsg ACCOUNT_EXISTS_INVITE = BaseCodeMsg.app(1026, MessageUtils.getLocale("errorCode.ACCOUNT_EXISTS_INVITE"));
    public static final BaseCodeMsg ACCOUNT_UNBINDING_WECHAT = BaseCodeMsg.app(1027, MessageUtils.getLocale("errorCode.ACCOUNT_UNBINDING_WECHAT"));
    public static final BaseCodeMsg ACCOUNT_PHONE_REGIST = BaseCodeMsg.app(1028, MessageUtils.getLocale("errorCode.ACCOUNT_PHONE_REGIST"));
    public static final BaseCodeMsg ACCOUNT_PHONE_BIND = BaseCodeMsg.app(1029, MessageUtils.getLocale("errorCode.ACCOUNT_PHONE_BIND"));
    public static final BaseCodeMsg ACCOUNT_PHONE_CONFIRM_BIND = BaseCodeMsg.app(1034, MessageUtils.getLocale("errorCode.ACCOUNT_PHONE_CONFIRM_BIND"));

    public static final BaseCodeMsg IDCARD_ILLEGAL = BaseCodeMsg.app(1030, MessageUtils.getLocale("errorCode.IDCARD_ILLEGAL"));
    public static final BaseCodeMsg IDCARD_AUTHENTICATION = BaseCodeMsg.app(1031, MessageUtils.getLocale("errorCode.IDCARD_AUTHENTICATION"));
    public static final BaseCodeMsg USER_NO_AUTHENTICATION = BaseCodeMsg.app(1032, MessageUtils.getLocale("errorCode.USER_NO_AUTHENTICATION"));
    public static final BaseCodeMsg IDCARD_NAME_ILLEGAL = BaseCodeMsg.app(1033, MessageUtils.getLocale("errorCode.IDCARD_NAME_ILLEGAL"));

    public static final BaseCodeMsg CONTENT_EMPTY = BaseCodeMsg.app(1040, MessageUtils.getLocale("errorCode.CONTENT_EMPTY"));
    public static final BaseCodeMsg CONTENT_SENSITIVE = BaseCodeMsg.app(1041, MessageUtils.getLocale("errorCode.CONTENT_SENSITIVE"));

    public static final BaseCodeMsg SIGN_YET = BaseCodeMsg.app(1050, MessageUtils.getLocale("errorCode.SIGN_YET"));
    public static final BaseCodeMsg SIGN_BACK_ERROR = BaseCodeMsg.app(1051, MessageUtils.getLocale("errorCode.SIGN_BACK_ERROR"));
    public static final BaseCodeMsg SIGN_RECORD_EXISTS = BaseCodeMsg.app(1052, MessageUtils.getLocale("errorCode.SIGN_RECORD_EXISTS"));

    public static final BaseCodeMsg FRIEND_IS_EXISTS = BaseCodeMsg.app(1060, MessageUtils.getLocale("errorCode.FRIEND_IS_EXISTS"));
    public static final BaseCodeMsg FRIEND_NOT_EXISTS = BaseCodeMsg.app(1060, MessageUtils.getLocale("errorCode.FRIEND_NOT_EXISTS"));
    public static final BaseCodeMsg BLACKLIST_IS_EXISTS = BaseCodeMsg.app(1061, MessageUtils.getLocale("errorCode.BLACKLIST_IS_EXISTS"));
    public static final BaseCodeMsg FRIEND_LIMIT = BaseCodeMsg.app(1062, MessageUtils.getLocale("errorCode.FRIEND_LIMIT"));

    public static final BaseCodeMsg REWARD_IS_GOT = BaseCodeMsg.app(1070, MessageUtils.getLocale("errorCode.REWARD_IS_GOT"));
    public static final BaseCodeMsg REWARD_NOT_EXISTS = BaseCodeMsg.app(1071, MessageUtils.getLocale("errorCode.REWARD_NOT_EXISTS"));
    public static final BaseCodeMsg REWARD_FAIL_GOT = BaseCodeMsg.app(1072, MessageUtils.getLocale("errorCode.REWARD_FAIL_GOT"));
    public static final BaseCodeMsg REWARD_NO_PERMISSION = BaseCodeMsg.app(1073, MessageUtils.getLocale("errorCode.REWARD_NO_PERMISSION"));

    public static final BaseCodeMsg EXP_GAME_TODAY_MAX = BaseCodeMsg.app(1074, MessageUtils.getLocale("errorCode.EXP_GAME_TODAY_MAX"));

    public static final BaseCodeMsg BADGE_WEAR_MAX = BaseCodeMsg.app(1080, MessageUtils.getLocale("errorCode.BADGE_WEAR_MAX"));

    public static final BaseCodeMsg MEMBER_EXPIRED = BaseCodeMsg.app(1090, MessageUtils.getLocale("errorCode.MEMBER_EXPIRED"));

    public static final BaseCodeMsg MONEY_ILLEGAL = BaseCodeMsg.app(1110, MessageUtils.getLocale("errorCode.MONEY_ILLEGAL"));
    public static final BaseCodeMsg MONEY_NOT_ENOUGT = BaseCodeMsg.app(1111, MessageUtils.getLocale("errorCode.MONEY_NOT_ENOUGT"));
    public static final BaseCodeMsg MONEY_TODAY_NOT_ENOUGT = BaseCodeMsg.app(1112, MessageUtils.getLocale("errorCode.MONEY_TODAY_NOT_ENOUGT"));

    public static final BaseCodeMsg ACTIVITY_NOT_START = BaseCodeMsg.app(1113, MessageUtils.getLocale("errorCode.ACTIVITY_NOT_START"));
    public static final BaseCodeMsg ACTIVITY_YET_END = BaseCodeMsg.app(1114, MessageUtils.getLocale("errorCode.ACTIVITY_YET_END"));
    public static final BaseCodeMsg ACTIVITY_NOT_EXISTS = BaseCodeMsg.app(1115, MessageUtils.getLocale("errorCode.ACTIVITY_NOT_EXISTS"));
    public static final BaseCodeMsg IS_UNLOCK = BaseCodeMsg.app(1116, MessageUtils.getLocale("errorCode.IS_UNLOCK"));
    public static final BaseCodeMsg AUTH_PLAYER_EXAMINE = BaseCodeMsg.app(1117, MessageUtils.getLocale("errorCode.AUTH_PLAYER_EXAMINE"));
    public static final BaseCodeMsg AUTH_PLAYER_REPEAT = BaseCodeMsg.app(1118, MessageUtils.getLocale("errorCode.AUTH_PLAYER_REPEAT"));
    public static final BaseCodeMsg NO_AUTH_PLAYER = BaseCodeMsg.app(1119, MessageUtils.getLocale("errorCode.NO_AUTH_PLAYER"));
    public static final BaseCodeMsg AUTH_PLAYER_FAIL = BaseCodeMsg.app(1120, MessageUtils.getLocale("errorCode.AUTH_PLAYER_FAIL"));
    public static final BaseCodeMsg AUTH_PLAYER_WAIT = BaseCodeMsg.app(1121, MessageUtils.getLocale("errorCode.AUTH_PLAYER_WAIT"));

    public static final BaseCodeMsg NOT_JOIN_GUILD = BaseCodeMsg.app(1122, MessageUtils.getLocale("errorCode.NOT_JOIN_GUILD"));
    public static final BaseCodeMsg EXISTS_GUILD = BaseCodeMsg.app(1123, MessageUtils.getLocale("errorCode.EXISTS_GUILD"));
    public static final BaseCodeMsg NOT_EXISTS_GUILD = BaseCodeMsg.app(1124, MessageUtils.getLocale("errorCode.NOT_EXISTS_GUILD"));

    public static final BaseCodeMsg EXPIRE_USER_CARD = BaseCodeMsg.app(1125, MessageUtils.getLocale("errorCode.EXPIRE_USER_CARD"));
    public static final BaseCodeMsg ILLEGAL_USER_CARD = BaseCodeMsg.app(1126, MessageUtils.getLocale("errorCode.ILLEGAL_USER_CARD"));
    public static final BaseCodeMsg ERROR_USER_CARD = BaseCodeMsg.app(1127, MessageUtils.getLocale("errorCode.ERROR_USER_CARD"));
    /**
     * voice
     */
    public static final BaseCodeMsg PASSWORD_DIFFERENCE = BaseCodeMsg.app(2000, MessageUtils.getLocale("errorCode.PASSWORD_DIFFERENCE"));

    public static final BaseCodeMsg ROOM_BANNED = BaseCodeMsg.app(2010, MessageUtils.getLocale("errorCode.ROOM_BANNED"));
    public static final BaseCodeMsg ROOM_END = BaseCodeMsg.app(2011, MessageUtils.getLocale("errorCode.ROOM_END"));
    public static final BaseCodeMsg NO_CHANCE_NUM = BaseCodeMsg.app(2012, MessageUtils.getLocale("errorCode.NO_CHANCE_NUM"));
    public static final BaseCodeMsg PK_CONDUCT = BaseCodeMsg.app(2013, MessageUtils.getLocale("errorCode.PK_CONDUCT"));
    public static final BaseCodeMsg NO_PK = BaseCodeMsg.app(2014, MessageUtils.getLocale("errorCode.NO_PK"));
    public static final BaseCodeMsg JUNIOR_NUM = BaseCodeMsg.app(2015, MessageUtils.getLocale("errorCode.JUNIOR_NUM"));
    public static final BaseCodeMsg SENIOR_NUM = BaseCodeMsg.app(2016, MessageUtils.getLocale("errorCode.SENIOR_NUM"));
    public static final BaseCodeMsg NOT_ENOUGH_PUMPKIN = BaseCodeMsg.app(2017, MessageUtils.getLocale("errorCode.NOT_ENOUGH_PUMPKIN"));
    public static final BaseCodeMsg NOT_ENOUGH_CANDY = BaseCodeMsg.app(2018, MessageUtils.getLocale("errorCode.NOT_ENOUGH_CANDY"));
    public static final BaseCodeMsg NOT_ENOUGH_LECOIN = BaseCodeMsg.app(2019, MessageUtils.getLocale("errorCode.NOT_ENOUGH_LECOIN"));
    public static final BaseCodeMsg NO_INTERSTELLAR_NUM = BaseCodeMsg.app(2020, MessageUtils.getLocale("errorCode.NO_INTERSTELLAR_NUM"));
    public static final BaseCodeMsg NOT_ENOUGH_GOLDEN_LIGHT_NUM = BaseCodeMsg.app(2021, MessageUtils.getLocale("errorCode.NOT_ENOUGH_GOLDEN_LIGHT_NUM"));
    public static final BaseCodeMsg NOT_ENOUGH_CHRISTAMS_NUM = BaseCodeMsg.app(2022, MessageUtils.getLocale("errorCode.NOT_ENOUGH_CHRISTAMS_NUM"));

    /**
     * post
     */
    public static final BaseCodeMsg PRAISE_YET = BaseCodeMsg.app(3000, MessageUtils.getLocale("errorCode.PRAISE_YET"));
    public static final BaseCodeMsg POST_DEL = BaseCodeMsg.app(3001, MessageUtils.getLocale("errorCode.POST_DEL"));
    public static final BaseCodeMsg COMMENT_DEL = BaseCodeMsg.app(3002, MessageUtils.getLocale("errorCode.COMMENT_DEL"));


    /**
     * home
     */
    public static final BaseCodeMsg HOME_PARAM_NULL = BaseCodeMsg.app(9000, MessageUtils.getLocale("errorCode.HOME_PARAM_NULL"));
    public static final BaseCodeMsg HOME_PARAM_ERROR = BaseCodeMsg.app(9000, MessageUtils.getLocale("errorCode.HOME_PARAM_ERROR"));

    /**
     * chat
     */
    public static final BaseCodeMsg CHAT_PARAM_NULL = BaseCodeMsg.app(9001, MessageUtils.getLocale("errorCode.CHAT_PARAM_NULL"));
    public static final BaseCodeMsg CHAT_PARAM_ERROR = BaseCodeMsg.app(9002, MessageUtils.getLocale("errorCode.CHAT_PARAM_ERROR"));
    public static final BaseCodeMsg CHAT_PARAM_EXIST = BaseCodeMsg.app(9003, MessageUtils.getLocale("errorCode.CHAT_PARAM_EXIST"));
    public static final BaseCodeMsg GROUP_CHAT_INVITE_NUM_NULL = BaseCodeMsg.app(9004, MessageUtils.getLocale("errorCode.GROUP_CHAT_INVITE_NUM_NULL"));
    public static final BaseCodeMsg GROUP_CHAT_INVITE_NUM_MAX = BaseCodeMsg.app(9005, MessageUtils.getLocale("errorCode.GROUP_CHAT_INVITE_NUM_MAX"));
    public static final BaseCodeMsg GROUP_CHAT_NUM_MAX_1 = BaseCodeMsg.app(9006, MessageUtils.getLocale("errorCode.GROUP_CHAT_NUM_MAX_1"));
    public static final BaseCodeMsg GROUP_CHAT_NUM_MAX_2 = BaseCodeMsg.app(9007, MessageUtils.getLocale("errorCode.GROUP_CHAT_NUM_MAX_2"));
    public static final BaseCodeMsg GROUP_CHAT_AUTHORITY = BaseCodeMsg.app(9008, MessageUtils.getLocale("errorCode.GROUP_CHAT_AUTHORITY"));
    public static final BaseCodeMsg GROUP_INFO_NAME = BaseCodeMsg.app(9009, MessageUtils.getLocale("errorCode.GROUP_INFO_NAME"));
    public static final BaseCodeMsg GROUP_CHAT_AUTHORITY_SET = BaseCodeMsg.app(9010, MessageUtils.getLocale("errorCode.GROUP_CHAT_AUTHORITY_SET"));
    public static final BaseCodeMsg GROUP_CHAT_AUTHORITY_NUM_1 = BaseCodeMsg.app(9011, MessageUtils.getLocale("errorCode.GROUP_CHAT_AUTHORITY_NUM_1"));
    public static final BaseCodeMsg GROUP_CHAT_AUTHORITY_NUM_2 = BaseCodeMsg.app(9012, MessageUtils.getLocale("errorCode.GROUP_CHAT_AUTHORITY_NUM_2"));
    public static final BaseCodeMsg GROUP_CHAT_AUTHORITY_NULL = BaseCodeMsg.app(9013, MessageUtils.getLocale("errorCode.GROUP_CHAT_AUTHORITY_NULL"));
    public static final BaseCodeMsg GROUP_CHAT_AUTHORITY_DELETE = BaseCodeMsg.app(9014, MessageUtils.getLocale("errorCode.GROUP_CHAT_AUTHORITY_DELETE"));
    public static final BaseCodeMsg GROUP_CHAT_DELETE_ERROR = BaseCodeMsg.app(9014, MessageUtils.getLocale("errorCode.GROUP_CHAT_DELETE_ERROR"));
    public static final BaseCodeMsg GROUP_CHAT_DELETE_USER = BaseCodeMsg.app(9015, MessageUtils.getLocale("errorCode.GROUP_CHAT_DELETE_USER"));
    public static final BaseCodeMsg GROUP_INFO_NOTICE = BaseCodeMsg.app(9016, MessageUtils.getLocale("errorCode.GROUP_INFO_NOTICE"));
    public static final BaseCodeMsg GROUP_ADMIN_LIMIT = BaseCodeMsg.app(9017, MessageUtils.getLocale("errorCode.GROUP_ADMIN_LIMIT"));
    public static final BaseCodeMsg GROUP_UPGRADE_FAILED = BaseCodeMsg.app(9018, MessageUtils.getLocale("errorCode.GROUP_UPGRADE_FAILED"));
    public static final BaseCodeMsg GROUP_INFO_NAME_LENGTH = BaseCodeMsg.app(9019, MessageUtils.getLocale("errorCode.GROUP_INFO_NAME_LENGTH"));
    public static final BaseCodeMsg GROUP_INFO_NOTICE_LENGTH = BaseCodeMsg.app(9020, MessageUtils.getLocale("errorCode.GROUP_INFO_NOTICE_LENGTH"));
    public static final BaseCodeMsg GROUP_INFO_INTRO_LENGTH = BaseCodeMsg.app(9021, MessageUtils.getLocale("errorCode.GROUP_INFO_INTRO_LENGTH"));
    public static final BaseCodeMsg GROUP_CHAT_ERROR = BaseCodeMsg.app(9022, MessageUtils.getLocale("errorCode.GROUP_CHAT_ERROR"));
    public static final BaseCodeMsg GROUP_DELETE_USER_ERROR = BaseCodeMsg.app(9023, MessageUtils.getLocale("errorCode.GROUP_DELETE_USER_ERROR"));
    public static final BaseCodeMsg GROUP_INVITE_USER_ERROR = BaseCodeMsg.app(9024, MessageUtils.getLocale("errorCode.GROUP_INVITE_USER_ERROR"));
    public static final BaseCodeMsg GROUP_SETTING_INFO_ERROR = BaseCodeMsg.app(9025, MessageUtils.getLocale("errorCode.GROUP_SETTING_INFO_ERROR"));

    /**
     * Questions
     */
    public static final BaseCodeMsg QUESTIONS_CODE = BaseCodeMsg.app(9110, MessageUtils.getLocale("errorCode.QUESTIONS_CODE"));
    public static final BaseCodeMsg QUESTIONS_PARAM = BaseCodeMsg.app(9111, MessageUtils.getLocale("errorCode.QUESTIONS_PARAM"));
    public static final BaseCodeMsg QUESTIONS_REPEAT = BaseCodeMsg.app(9112, MessageUtils.getLocale("errorCode.QUESTIONS_REPEAT"));
    public static final BaseCodeMsg QUESTIONS_SUBMIT = BaseCodeMsg.app(9113, MessageUtils.getLocale("errorCode.QUESTIONS_SUBMIT"));
    public static final BaseCodeMsg QUESTIONS_TYPE = BaseCodeMsg.app(9114, MessageUtils.getLocale("errorCode.QUESTIONS_TYPE"));
    public static final BaseCodeMsg QUESTIONS_LEGALITY = BaseCodeMsg.app(9115, MessageUtils.getLocale("errorCode.QUESTIONS_LEGALITY"));
    public static final BaseCodeMsg QUESTIONS_GUESS_WORD = BaseCodeMsg.app(9116, MessageUtils.getLocale("errorCode.QUESTIONS_GUESS_WORD"));
    public static final BaseCodeMsg QUESTIONS_GUESS_HINT = BaseCodeMsg.app(9117, MessageUtils.getLocale("errorCode.QUESTIONS_GUESS_HINT"));
    public static final BaseCodeMsg QUESTIONS_GUESS_WORD_COUNT = BaseCodeMsg.app(9118, MessageUtils.getLocale("errorCode.QUESTIONS_GUESS_WORD_COUNT"));
    public static final BaseCodeMsg QUESTIONS_SPY_WORD = BaseCodeMsg.app(9119, MessageUtils.getLocale("errorCode.QUESTIONS_SPY_WORD"));
    public static final BaseCodeMsg QUESTIONS_SPY_WORD_COUNT = BaseCodeMsg.app(9120, MessageUtils.getLocale("errorCode.QUESTIONS_SPY_WORD_COUNT"));
    public static final BaseCodeMsg QUESTIONS_STAND_WORD = BaseCodeMsg.app(9121, MessageUtils.getLocale("errorCode.QUESTIONS_STAND_WORD"));
    public static final BaseCodeMsg QUESTIONS_STAND_COUNT = BaseCodeMsg.app(9122, MessageUtils.getLocale("errorCode.QUESTIONS_STAND_COUNT"));
    public static final BaseCodeMsg QUESTIONS_STAND_WORD_COUNT = BaseCodeMsg.app(9123, MessageUtils.getLocale("errorCode.QUESTIONS_STAND_WORD_COUNT"));
    public static final BaseCodeMsg QUESTIONS_STAND_ANSWER = BaseCodeMsg.app(9124, MessageUtils.getLocale("errorCode.QUESTIONS_STAND_ANSWER"));
    public static final BaseCodeMsg QUESTIONS_STAND_ERROR = BaseCodeMsg.app(9125, MessageUtils.getLocale("errorCode.QUESTIONS_STAND_ERROR"));
    public static final BaseCodeMsg QUESTIONS_STAND_PROBLEM = BaseCodeMsg.app(9126, MessageUtils.getLocale("errorCode.QUESTIONS_STAND_PROBLEM"));

    /**
     * pets
     */
    public static final BaseCodeMsg NUM_NOT_ENOUGH = BaseCodeMsg.app(6001, MessageUtils.getLocale("errorCode.NUM_NOT_ENOUGH"));

    /**
     * 弹框提示code
     */
    public static final int BULLET_FRAME_CODE = 333;

    public static final BaseCodeMsg ROOM_NOT_EXIST = BaseCodeMsg.app(9200, MessageUtils.getLocale("errorCode.ROOM_NOT_EXIST"));

    public static final BaseCodeMsg FOOTPRINT_ERROR = BaseCodeMsg.app(9527, MessageUtils.getLocale("errorCode.FOOTPRINT_ERROR"));
    public static final BaseCodeMsg FOOTPRINT_SELECT_ERROR = BaseCodeMsg.app(9528, MessageUtils.getLocale("errorCode.FOOTPRINT_SELECT_ERROR"));

    /**
     * apply
     **/

    public static final BaseCodeMsg ERROR_SUBMIT = BaseCodeMsg.app(104, MessageUtils.getLocale("errorCode.ERROR_SUBMIT"));
    public static final BaseCodeMsg ERROR_FAST_SUBMIT = BaseCodeMsg.app(104, MessageUtils.getLocale("errorCode.ERROR_FAST_SUBMIT"));
    public static final BaseCodeMsg INPUT_SUCCESS_ID_CODE = BaseCodeMsg.app(1128, MessageUtils.getLocale("errorCode.INPUT_SUCCESS_ID_CODE"));
    public static final BaseCodeMsg INVALID_ACTIVITY_TIME = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.INVALID_ACTIVITY_TIME"));
    public static final BaseCodeMsg EXIST_EXCHANGE = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.EXIST_EXCHANGE"));
    public static final BaseCodeMsg INCOMPLETE_EXCHANGE_CONDITIONS = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.INCOMPLETE_EXCHANGE_CONDITIONS"));
    public static final BaseCodeMsg NONE_CONVERTIBLE = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.NONE_CONVERTIBLE"));
    public static final BaseCodeMsg MAX_LEVEL_100 = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.MAX_LEVEL_100"));
    public static final BaseCodeMsg ORDER_NOT_EXIST = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.ORDER_NOT_EXIST"));
    public static final BaseCodeMsg MAX_EXCHANGE_5 = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.MAX_EXCHANGE_5"));
    public static final BaseCodeMsg FAIL_TO_MEET_REQUIREMENTS = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.FAIL_TO_MEET_REQUIREMENTS"));
    public static final BaseCodeMsg NO_LOTTERY_PRICE_CONFIGURED = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.NO_LOTTERY_PRICE_CONFIGURED"));
    public static final BaseCodeMsg DURING_INACTIVE_TIME = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.DURING_INACTIVE_TIME"));
    public static final BaseCodeMsg ACCOUNT_IS_UNDER_PROTECTION = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.ACCOUNT_IS_UNDER_PROTECTION"));
    public static final BaseCodeMsg ACTIVITY_HAS_ENDED = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.ACTIVITY_HAS_ENDED"));
    public static final BaseCodeMsg ACTIVITY_NOT_STARTED = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.ACTIVITY_NOT_STARTED"));
    public static final BaseCodeMsg VOICE_TYPE_ADD_FAILED = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.VOICE_TYPE_ADD_FAILED"));
    public static final BaseCodeMsg VOICE_TYPE_NOT_EXIST = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.VOICE_TYPE_NOT_EXIST"));
    public static final BaseCodeMsg BACKGROUND_UPLOAD_FAILED = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.BACKGROUND_UPLOAD_FAILED"));
    public static final BaseCodeMsg IMAGE_URL_IS_NOT_EMPTY = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.IMAGE_URL_IS_NOT_EMPTY"));
    public static final BaseCodeMsg BACKGROUND_EXISTED = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.BACKGROUND_EXISTED"));
    public static final BaseCodeMsg BACKGROUND_PULL_FAILED = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.BACKGROUND_PULL_FAILED"));
    public static final BaseCodeMsg BACKGROUND_DELETE_FAILED = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.BACKGROUND_DELETE_FAILED"));
    public static final BaseCodeMsg DELETE_FAILED = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.DELETE_FAILED"));
    public static final BaseCodeMsg NOT_VOICE_PERMISSION = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.NOT_VOICE_PERMISSION"));
    public static final BaseCodeMsg CREATE_VOICE_ROOM = BaseCodeMsg.app(202, MessageUtils.getLocale("errorCode.CREATE_VOICE_ROOM"));
    public static final BaseCodeMsg NOT_PERMISSION_CREATE_VOICE_ROOM = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.NOT_PERMISSION_CREATE_VOICE_ROOM"));
    public static final BaseCodeMsg VOICE_ROOM_VALIDATE_8 = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.VOICE_ROOM_VALIDATE_8"));
    public static final BaseCodeMsg VOICE_ROOM_TYPE_NOT_EMPTY = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.VOICE_ROOM_TYPE_NOT_EMPTY"));
    public static final BaseCodeMsg CREATE_VOICE_ROOM_FAILED = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.CREATE_VOICE_ROOM_FAILED"));
    public static final BaseCodeMsg NOTICE_LENGTH_LIMIT_1500 = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.NOTICE_LENGTH_LIMIT_1500"));
    public static final BaseCodeMsg NOTICE_LENGTH_LIMIT_15 = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.NOTICE_LENGTH_LIMIT_15"));
    public static final BaseCodeMsg NOTICE_LENGTH_LIMIT_50 = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.NOTICE_LENGTH_LIMIT_50"));
    public static final BaseCodeMsg UNKNOWN_ROOM_TYPE = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.UNKNOWN_ROOM_TYPE"));
    public static final BaseCodeMsg USER_CAN_NOT_ENTER_ROOM = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.USER_CAN_NOT_ENTER_ROOM"));
    public static final BaseCodeMsg PLEASE_ENTER_PASSWORD = BaseCodeMsg.app(2000, MessageUtils.getLocale("errorCode.PLEASE_ENTER_PASSWORD"));
    public static final BaseCodeMsg PARTY_IS_OVER = BaseCodeMsg.app(102, MessageUtils.getLocale("errorCode.PARTY_IS_OVER"));
    public static final BaseCodeMsg TYPE_IS_NOT_EMPTY = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.TYPE_IS_NOT_EMPTY"));
    public static final BaseCodeMsg SEAT_HAS_USER = BaseCodeMsg.app(2020, MessageUtils.getLocale("errorCode.SEAT_HAS_USER"));
    public static final BaseCodeMsg NO_SUITABLE_LOCATION = BaseCodeMsg.app(2020, MessageUtils.getLocale("errorCode.NO_SUITABLE_LOCATION"));
    public static final BaseCodeMsg USER_HAS_LEAVE_ROOM = BaseCodeMsg.app(2020, MessageUtils.getLocale("errorCode.USER_HAS_LEAVE_ROOM"));
    public static final BaseCodeMsg USER_HAS_LEAVE_SEAT = BaseCodeMsg.app(2021, MessageUtils.getLocale("errorCode.USER_HAS_LEAVE_SEAT"));
    public static final BaseCodeMsg REQUIRED_PARAMETER = BaseCodeMsg.app(2021, MessageUtils.getLocale("errorCode.REQUIRED_PARAMETER"));
    public static final BaseCodeMsg WHEAT_ROW_MODE = BaseCodeMsg.app(4003, MessageUtils.getLocale("errorCode.WHEAT_ROW_MODE"));
    public static final BaseCodeMsg SEAT_IS_LOCKED = BaseCodeMsg.app(4003, MessageUtils.getLocale("errorCode.SEAT_IS_LOCKED"));
    public static final BaseCodeMsg ALREADY_ON_WHEAT = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.ALREADY_ON_WHEAT"));
    public static final BaseCodeMsg NOT_PERMISSION_OPERATION = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.NOT_PERMISSION_OPERATION"));
    public static final BaseCodeMsg NOT_PERMISSION = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.NOT_PERMISSION"));
    public static final BaseCodeMsg NOT_MANAGE_PERMISSION = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.NOT_MANAGE_PERMISSION"));
    public static final BaseCodeMsg OFFICIALLY_CAN_NOT_SET_ADMIN = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.OFFICIALLY_CAN_NOT_SET_ADMIN"));
    public static final BaseCodeMsg OWNER_CAN_NOT_SET_ADMIN = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.OWNER_CAN_NOT_SET_ADMIN"));
    public static final BaseCodeMsg OFFICIALLY_USER_CAN_NOT_SET_ADMIN = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.OFFICIALLY_USER_CAN_NOT_SET_ADMIN"));
    public static final BaseCodeMsg HAS_PERMISSION = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.HAS_PERMISSION"));
    public static final BaseCodeMsg ROOM_LENGTH_LIMIT_12 = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.ROOM_LENGTH_LIMIT_12"));
    public static final BaseCodeMsg NOTICE_LENGTH_LIMIT_20 = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.NOTICE_LENGTH_LIMIT_20"));
    public static final BaseCodeMsg BACKGROUND_NOT_EXIST = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.BACKGROUND_NOT_EXIST"));
    public static final BaseCodeMsg BACKGROUND_INVALID = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.BACKGROUND_INVALID"));
    public static final BaseCodeMsg BACKGROUND_ILLEGAL = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.BACKGROUND_ILLEGAL"));
    public static final BaseCodeMsg UNKNOWN_TYPE = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.UNKNOWN_TYPE"));
    public static final BaseCodeMsg FAILED_TO_OPEN_NOT_ACHIEVE_EMPEROR = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.FAILED_TO_OPEN_NOT_ACHIEVE_EMPEROR"));
    public static final BaseCodeMsg FAILED_TO_OPEN_NOT_ACHIEVE_EARL = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.FAILED_TO_OPEN_NOT_ACHIEVE_EARL"));
    public static final BaseCodeMsg FAILED_TO_OPEN_NOT_ACHIEVE_MARQUIS = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.FAILED_TO_OPEN_NOT_ACHIEVE_MARQUIS"));
    public static final BaseCodeMsg FAILED_TO_OPEN_NOT_ACHIEVE_DUKE = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.FAILED_TO_OPEN_NOT_ACHIEVE_DUKE"));
    public static final BaseCodeMsg PRIZE_IS_EMPTY = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.PRIZE_IS_EMPTY"));
    public static final BaseCodeMsg NO_CURRENT_EXCHANGE_GIFT = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.NO_CURRENT_EXCHANGE_GIFT"));
    public static final BaseCodeMsg NOT_ACCEPTABLE = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.NOT_ACCEPTABLE"));
    public static final BaseCodeMsg EXCHANGEABLE_21 = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.EXCHANGEABLE_21"));
    public static final BaseCodeMsg WITHOUT_EXPERIENCE_CARD = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.WITHOUT_EXPERIENCE_CARD"));
    public static final BaseCodeMsg NO_CHRISTMAS_DRESS_CERTIFICATE = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.NO_CHRISTMAS_DRESS_CERTIFICATE"));
    public static final BaseCodeMsg NOT_ENOUGH_CHRISTMAS_DRESS_VOUCHERS = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.NOT_ENOUGH_CHRISTMAS_DRESS_VOUCHERS"));
    public static final BaseCodeMsg PARAMETER_VERIFICATION_FAILED = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.PARAMETER_VERIFICATION_FAILED"));
    public static final BaseCodeMsg ORIGIN_PASSWORD_ERROR = BaseCodeMsg.app(2223, MessageUtils.getLocale("errorCode.ORIGIN_PASSWORD_ERROR"));
    public static final BaseCodeMsg ENTER_ORIGIN_PASSWORD = BaseCodeMsg.app(2223, MessageUtils.getLocale("errorCode.ENTER_ORIGIN_PASSWORD"));
    public static final BaseCodeMsg ENTER_NEW_PASSWORD = BaseCodeMsg.app(2224, MessageUtils.getLocale("errorCode.ENTER_NEW_PASSWORD"));
    public static final BaseCodeMsg OPEN_YOUTH_MODE = BaseCodeMsg.app(2224, MessageUtils.getLocale("errorCode.OPEN_YOUTH_MODE"));
    public static final BaseCodeMsg ENTER_PASSWORD = BaseCodeMsg.app(2224, MessageUtils.getLocale("errorCode.ENTER_PASSWORD"));
    public static final BaseCodeMsg PASSWORD_ERROR = BaseCodeMsg.app(2223, MessageUtils.getLocale("errorCode.PASSWORD_ERROR"));
    public static final BaseCodeMsg SYNTHESIS_CONDITIONS_ARE_NOT_MET = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.SYNTHESIS_CONDITIONS_ARE_NOT_MET"));
    public static final BaseCodeMsg UPPER_LIMIT_HAS_BEEN_REACHED = BaseCodeMsg.app(2223, MessageUtils.getLocale("errorCode.UPPER_LIMIT_HAS_BEEN_REACHED"));
    public static final BaseCodeMsg SERVER_IS_BUSY_514 = BaseCodeMsg.app(500, MessageUtils.getLocale("errorCode.SERVER_IS_BUSY_514"));
    public static final BaseCodeMsg SERVER_IS_BUSY_515 = BaseCodeMsg.app(500, MessageUtils.getLocale("errorCode.SERVER_IS_BUSY_515"));
    public static final BaseCodeMsg SERVER_IS_BUSY_516 = BaseCodeMsg.app(500, MessageUtils.getLocale("errorCode.SERVER_IS_BUSY_516"));
    public static final BaseCodeMsg SERVER_IS_BUSY_517 = BaseCodeMsg.app(500, MessageUtils.getLocale("errorCode.SERVER_IS_BUSY_517"));
    public static final BaseCodeMsg SERVER_IS_BUSY_518 = BaseCodeMsg.app(500, MessageUtils.getLocale("errorCode.SERVER_IS_BUSY_518"));
    public static final BaseCodeMsg SERVER_IS_BUSY_520 = BaseCodeMsg.app(500, MessageUtils.getLocale("errorCode.SERVER_IS_BUSY_520"));
    public static final BaseCodeMsg SERVER_IS_BUSY_530 = BaseCodeMsg.app(500, MessageUtils.getLocale("errorCode.SERVER_IS_BUSY_530"));
    public static final BaseCodeMsg REQUEST_IS_NOT_SUPPORTED_519 = BaseCodeMsg.app(500, MessageUtils.getLocale("errorCode.REQUEST_IS_NOT_SUPPORTED_519"));
    public static final BaseCodeMsg SERVER_IS_BUSY = BaseCodeMsg.app(500, MessageUtils.getLocale("errorCode.SERVER_IS_BUSY"));
    public static final BaseCodeMsg INSTANTIATION_ERROR = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.INSTANTIATION_ERROR"));
    public static final BaseCodeMsg MISS_PARAMETER = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.MISS_PARAMETER"));
    public static final BaseCodeMsg ORDER_AMOUNT_ERROR = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.ORDER_AMOUNT_ERROR"));
    public static final BaseCodeMsg ORDER_ERROR_LIMIT = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.ORDER_ERROR_LIMIT"));
    public static final BaseCodeMsg ORDER_IS_PAYED = BaseCodeMsg.app(400, MessageUtils.getLocale("errorCode.ORDER_IS_PAYED"));
    public static final BaseCodeMsg FIREND_REMARK_LIMIT_10 = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.FIREND_REMARK_LIMIT_10"));
    public static final BaseCodeMsg HAS_SIGN = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.HAS_SIGN"));
    public static final BaseCodeMsg FINISH_ALL_SIGN = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.FINISH_ALL_SIGN"));
    public static final BaseCodeMsg EXCHANGE_LIMIT = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.EXCHANGE_LIMIT"));
    public static final BaseCodeMsg INSUFFICIENT_BATTERRY = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.INSUFFICIENT_BATTERRY"));
    public static final BaseCodeMsg INSUFFICIENT_COUPONS = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.INSUFFICIENT_COUPONS"));
    public static final BaseCodeMsg USER_NOT_EXIST = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.USER_NOT_EXIST"));
    public static final BaseCodeMsg BIND_BANK_CARD = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.BIND_BANK_CARD"));
    public static final BaseCodeMsg SUCCESS_BANK_CARD = BaseCodeMsg.app(-2, MessageUtils.getLocale("errorCode.SUCCESS_BANK_CARD"));
    public static final BaseCodeMsg CHOOSE_SUPPORT_BANK = BaseCodeMsg.app(-3, MessageUtils.getLocale("errorCode.CHOOSE_SUPPORT_BANK"));
    public static final BaseCodeMsg MESSAGE_ERROR = BaseCodeMsg.app(-3, MessageUtils.getLocale("errorCode.MESSAGE_ERROR"));
    public static final BaseCodeMsg RECEIVE_LIMIT = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.RECEIVE_LIMIT"));
    public static final BaseCodeMsg WHISTLEBLOWER_NOT_EMPTY = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.WHISTLEBLOWER_NOT_EMPTY"));
    public static final BaseCodeMsg ERROR_SUBMIT_FAST = BaseCodeMsg.app(601, MessageUtils.getLocale("errorCode.ERROR_SUBMIT_FAST"));
    public static final BaseCodeMsg BIND_MESSAGE_ERROR = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.BIND_MESSAGE_ERROR"));
    public static final BaseCodeMsg NICK_LIMIT_10 = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.NICK_LIMIT_10"));
    public static final BaseCodeMsg SIGNATURE_LIMIT_1000 = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.SIGNATURE_LIMIT_1000"));
    public static final BaseCodeMsg MODIFY_ERROR_LIMIT = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.MODIFY_ERROR_LIMIT"));
    public static final BaseCodeMsg MODIFY_SUCCESS_LIMIT = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.MODIFY_SUCCESS_LIMIT"));
    public static final BaseCodeMsg ACCOUNT_PROTECTED = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.ACCOUNT_PROTECTED"));
    public static final BaseCodeMsg POST_FAST = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.POST_FAST"));
    public static final BaseCodeMsg EXCHANGE_FINISHED = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.EXCHANGE_FINISHED"));
    public static final BaseCodeMsg EXCHANGE_ERROR = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.EXCHANGE_ERROR"));
    public static final BaseCodeMsg OPERATION_FAILED = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.OPERATION_FAILED"));
    public static final BaseCodeMsg INVALID_OPERATION = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.INVALID_OPERATION"));
    public static final BaseCodeMsg INVITATION_SENT = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.INVITATION_SENT"));
    public static final BaseCodeMsg INVITATION_FAILED = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.INVITATION_FAILED"));
    public static final BaseCodeMsg REQUEST_TIMEOUT = BaseCodeMsg.app(504, MessageUtils.getLocale("errorCode.REQUEST_TIMEOUT"));
    public static final BaseCodeMsg NO_TEAM = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.NO_TEAM"));
    public static final BaseCodeMsg HAS_TEAM = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.HAS_TEAM"));
    public static final BaseCodeMsg NOT_JOIN_TEAM = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.NOT_JOIN_TEAM"));
    public static final BaseCodeMsg GIFT_LIMIT_5200 = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.GIFT_LIMIT_5200"));
    public static final BaseCodeMsg GIFT_HAS_DRAWN = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.GIFT_HAS_DRAWN"));
    public static final BaseCodeMsg BOSS_SEAT_STRING_1 = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.BOSS_SEAT_STRING_1"));
    public static final BaseCodeMsg BOSS_SEAT_STRING_2 = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.BOSS_SEAT_STRING_2"));
    public static final BaseCodeMsg ENTER_PHONE = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.ENTER_PHONE"));
    public static final BaseCodeMsg LOGIN_ERROR = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.LOGIN_ERROR"));
    public static final BaseCodeMsg PASSWORD_VALIDATE_1 = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.PASSWORD_VALIDATE_1"));
    public static final BaseCodeMsg LOGIN_ERROR_2 = BaseCodeMsg.app(-101, MessageUtils.getLocale("errorCode.LOGIN_ERROR_2"));
    public static final BaseCodeMsg GOLD_ENVELOPES_LIMIT_10 = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.GOLD_ENVELOPES_LIMIT_10"));
    public static final BaseCodeMsg GOLD_ENVELOPES_LIMIT_10_2 = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.GOLD_ENVELOPES_LIMIT_10_2"));
    public static final BaseCodeMsg GOLD_ENVELOPES_LIMIT_100 = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.GOLD_ENVELOPES_LIMIT_100"));
    public static final BaseCodeMsg GOLD_ENVELOPES_LIMIT_1000000 = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.GOLD_ENVELOPES_LIMIT_1000000"));
    public static final BaseCodeMsg GOLD_ENVELOPES_LIMIT_1 = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.GOLD_ENVELOPES_LIMIT_1"));
    public static final BaseCodeMsg GOLD_NOT_ENOUGH = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.GOLD_NOT_ENOUGH"));
    public static final BaseCodeMsg PLEASE_RECHARGE = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.PLEASE_RECHARGE"));
    public static final BaseCodeMsg EXCHANGE_GOLD_COINS_STRING_1 = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.EXCHANGE_GOLD_COINS_STRING_1"));
    public static final BaseCodeMsg EXCHANGE_GOLD_COINS_STRING_2 = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.EXCHANGE_GOLD_COINS_STRING_2"));
    public static final BaseCodeMsg EXCHANGE_GOLD_COINS_STRING_3 = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.EXCHANGE_GOLD_COINS_STRING_3"));
    public static final BaseCodeMsg DIAMONDS_ENVELOPES_LIMIT_1 = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.DIAMONDS_ENVELOPES_LIMIT_1"));
    public static final BaseCodeMsg DIAMONDS_ENVELOPES_LIMIT_100_1 = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.DIAMONDS_ENVELOPES_LIMIT_100_1"));
    public static final BaseCodeMsg DIAMONDS_ENVELOPES_LIMIT_100_2 = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.DIAMONDS_ENVELOPES_LIMIT_100_2"));
    public static final BaseCodeMsg DIAMONDS_ENVELOPES_LIMIT_10000 = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.DIAMONDS_ENVELOPES_LIMIT_10000"));
    public static final BaseCodeMsg DIAMONDS_ENVELOPES_LIMIT_1_2 = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.DIAMONDS_ENVELOPES_LIMIT_1_2"));
    public static final BaseCodeMsg DIAMONDS_NOT_ENOUGH = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.DIAMONDS_NOT_ENOUGH"));
    public static final BaseCodeMsg ROOM_EXCEPTION = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.ROOM_EXCEPTION"));
    public static final BaseCodeMsg HAVE_RECEIVED_ENVELOPES = BaseCodeMsg.app(2023, MessageUtils.getLocale("errorCode.HAVE_RECEIVED_ENVELOPES"));
    public static final BaseCodeMsg DID_NOT_GET_IT = BaseCodeMsg.app(200, MessageUtils.getLocale("errorCode.DID_NOT_GET_IT"));
    public static final BaseCodeMsg ENVELOPES_NOT_EXISTED = BaseCodeMsg.app(200, MessageUtils.getLocale("errorCode.ENVELOPES_NOT_EXISTED"));
    public static final BaseCodeMsg GOLD_ENVELOPES_LIMIT_1_2 = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.GOLD_ENVELOPES_LIMIT_1_2"));
    public static final BaseCodeMsg GOLD_ENVELOPES_LIMIT_50 = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.GOLD_ENVELOPES_LIMIT_50"));
    public static final BaseCodeMsg GOLD_ENVELOPES_LIMIT_100000 = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.GOLD_ENVELOPES_LIMIT_100000"));
    public static final BaseCodeMsg GOLD_ENVELOPES_LIMIT_10_3 = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.GOLD_ENVELOPES_LIMIT_10_3"));
    public static final BaseCodeMsg ENVELOPES_FROM_STRING_1 = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.ENVELOPES_FROM_STRING_1"));
    public static final BaseCodeMsg ENVELOPES_FROM_STRING_2 = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.ENVELOPES_FROM_STRING_2"));
    public static final BaseCodeMsg ADD_RECORD_ERROR = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.ADD_RECORD_ERROR"));
    public static final BaseCodeMsg DUPLICATE_REQUEST = BaseCodeMsg.app(200, MessageUtils.getLocale("errorCode.DUPLICATE_REQUEST"));
    public static final BaseCodeMsg SHOP_ARE_GONE = BaseCodeMsg.app(6001, MessageUtils.getLocale("errorCode.SHOP_ARE_GONE"));
    public static final BaseCodeMsg NOT_SUPPORT_PAY_TYPE = BaseCodeMsg.app(6003, MessageUtils.getLocale("errorCode.NOT_SUPPORT_PAY_TYPE"));
    public static final BaseCodeMsg DIAMONDS_NOT_ENOUGH_2 = BaseCodeMsg.app(6003, MessageUtils.getLocale("errorCode.DIAMONDS_NOT_ENOUGH_2"));
    public static final BaseCodeMsg RECHARGE_LIMIT = BaseCodeMsg.app(6005, MessageUtils.getLocale("errorCode.RECHARGE_LIMIT"));
    public static final BaseCodeMsg CREATE_ORDER_ERROR = BaseCodeMsg.app(6004, MessageUtils.getLocale("errorCode.CREATE_ORDER_ERROR"));
    public static final BaseCodeMsg NOT_SUPPORT_BUY_TYPE = BaseCodeMsg.app(6004, MessageUtils.getLocale("errorCode.NOT_SUPPORT_BUY_TYPE"));
    public static final BaseCodeMsg CAN_NOT_RECHARGE = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.CAN_NOT_RECHARGE"));
    public static final BaseCodeMsg HAS_BUY_1 = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.HAS_BUY_1"));
    public static final BaseCodeMsg UPLOAD_FIALED = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.UPLOAD_FIALED"));
    public static final BaseCodeMsg NOT_DATA = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.NOT_DATA"));
    public static final BaseCodeMsg EXCEPTION = BaseCodeMsg.app(4, MessageUtils.getLocale("errorCode.EXCEPTION"));
    public static final BaseCodeMsg NOT_ORDER_INFO = BaseCodeMsg.app(2, MessageUtils.getLocale("errorCode.NOT_ORDER_INFO"));
    public static final BaseCodeMsg ORDER_VERIFIED = BaseCodeMsg.app(3, MessageUtils.getLocale("errorCode.ORDER_VERIFIED"));
    public static final BaseCodeMsg NOT_SUPPORT_WX_PAY = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.NOT_SUPPORT_WX_PAY"));
    public static final BaseCodeMsg CREATE_QR_CODE_FAILED = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.CREATE_QR_CODE_FAILED"));
    public static final BaseCodeMsg PAY_FAILED = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.PAY_FAILED"));
    public static final BaseCodeMsg USER_NOT_BIND_WX = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.USER_NOT_BIND_WX"));
    public static final BaseCodeMsg USER_NOT_BIND_WX_2 = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.USER_NOT_BIND_WX_2"));
    public static final BaseCodeMsg USER_NOT_BIND_BANK_CARD = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.USER_NOT_BIND_BANK_CARD"));
    public static final BaseCodeMsg ORDER_CHECKED_FAILED = BaseCodeMsg.app(400, MessageUtils.getLocale("errorCode.ORDER_CHECKED_FAILED"));
    public static final BaseCodeMsg ORDER_TRANSFER_FAILED = BaseCodeMsg.app(1, MessageUtils.getLocale("errorCode.ORDER_TRANSFER_FAILED"));
    public static final BaseCodeMsg PUBLISH_LIMIT_128 = BaseCodeMsg.app(1, MessageUtils.getLocale("errorCode.PUBLISH_LIMIT_128"));
    public static final BaseCodeMsg COMMENT_LIMIT_100 = BaseCodeMsg.app(1, MessageUtils.getLocale("errorCode.COMMENT_LIMIT_100"));
    public static final BaseCodeMsg ORDER_RECEIVED = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.ORDER_RECEIVED"));
    public static final BaseCodeMsg ORDER_REJECT = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.ORDER_REJECT"));
    public static final BaseCodeMsg SERVICE_FINISHED = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.SERVICE_FINISHED"));
    public static final BaseCodeMsg REPEAT_ORDER = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.REPEAT_ORDER"));
    public static final BaseCodeMsg NAME_LIMIT = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.NAME_LIMIT"));
    public static final BaseCodeMsg UPDATE_STATUS_ERROR = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.UPDATE_STATUS_ERROR"));
    public static final BaseCodeMsg ALI_PAY_REQUEST_ERROR = BaseCodeMsg.app(400, MessageUtils.getLocale("errorCode.ALI_PAY_REQUEST_ERROR"));
    public static final BaseCodeMsg USER_NOT_BIND_ALI_PAY = BaseCodeMsg.app(400, MessageUtils.getLocale("errorCode.USER_NOT_BIND_ALI_PAY"));
    public static final BaseCodeMsg HAS_CP = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.HAS_CP"));
    public static final BaseCodeMsg OTHER_HAS_CP = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.OTHER_HAS_CP"));
    public static final BaseCodeMsg APPLY_ERROR_HAS_CP = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.APPLY_ERROR_HAS_CP"));
    public static final BaseCodeMsg SEX_ERROR = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.SEX_ERROR"));
    public static final BaseCodeMsg DIAMONDS_NOT_ENOUGH_3 = BaseCodeMsg.app(6003, MessageUtils.getLocale("errorCode.DIAMONDS_NOT_ENOUGH_3"));
    public static final BaseCodeMsg REMOVE_FAILED = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.REMOVE_FAILED"));
    public static final BaseCodeMsg CP_SUCCESS_STRING_1 = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.CP_SUCCESS_STRING_1"));
    public static final BaseCodeMsg CP_SUCCESS_STRING_2 = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.CP_SUCCESS_STRING_2"));
    public static final BaseCodeMsg REMOVE_CP = BaseCodeMsg.app(518, MessageUtils.getLocale("errorCode.REMOVE_CP"));
    public static final BaseCodeMsg REMOVE_CP_2 = BaseCodeMsg.app(517, MessageUtils.getLocale("errorCode.REMOVE_CP_2"));
    public static final BaseCodeMsg BUY_ERROR_1 = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.BUY_ERROR_1"));
    public static final BaseCodeMsg BUY_ERROR_2 = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.BUY_ERROR_2"));
    public static final BaseCodeMsg GIFT_ERROR_CODE = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.GIFT_ERROR_CODE"));
    public static final BaseCodeMsg BUG_ERROR_1 = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.BUG_ERROR_1"));
    public static final BaseCodeMsg TRANSFER_ERROR = BaseCodeMsg.app(202, MessageUtils.getLocale("errorCode.TRANSFER_ERROR"));
    public static final BaseCodeMsg APPEAL_SUCCESS = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.APPEAL_SUCCESS"));
    public static final BaseCodeMsg APPEAL_ERROR = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.APPEAL_ERROR"));
    public static final BaseCodeMsg SERVICE_NOT_FUND = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.SERVICE_NOT_FUND"));
    public static final BaseCodeMsg HAS_BIND_BANK_CARD = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.HAS_BIND_BANK_CARD"));
    public static final BaseCodeMsg INFO_MISS = BaseCodeMsg.app(-7, MessageUtils.getLocale("errorCode.INFO_MISS"));
    public static final BaseCodeMsg INFO_DISACCORD = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.INFO_DISACCORD"));
    public static final BaseCodeMsg NO_REAL_NAME = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.NO_REAL_NAME"));
    public static final BaseCodeMsg CHOOSE_PAY_METHOD = BaseCodeMsg.app(2010, MessageUtils.getLocale("errorCode.CHOOSE_PAY_METHOD"));
    public static final BaseCodeMsg SUPPORT_CASH = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.SUPPORT_CASH"));
    public static final BaseCodeMsg APPLY_ERROR = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.APPLY_ERROR"));
    public static final BaseCodeMsg SEND_ERROR = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.SEND_ERROR"));
    public static final BaseCodeMsg ON_PHONE = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.ON_PHONE"));
    public static final BaseCodeMsg OFFLINE = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.OFFLINE"));
    public static final BaseCodeMsg APPLY_ERROR_STRING_1 = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.APPLY_ERROR_STRING_1"));
    public static final BaseCodeMsg APPLY_ERROR_STRING_2 = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.APPLY_ERROR_STRING_2"));
    public static final BaseCodeMsg APPLY_ERROR_STRING_3 = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.APPLY_ERROR_STRING_3"));
    public static final BaseCodeMsg ORDER_NOT_FINISHED = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.ORDER_NOT_FINISHED"));
    public static final BaseCodeMsg CHOOSE_SHARE_TARGET = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.CHOOSE_SHARE_TARGET"));
    public static final BaseCodeMsg UNPAID_NO_REFUND = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.UNPAID_NO_REFUND"));
    public static final BaseCodeMsg FAILED_TO_PROCESS_ORDER = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.FAILED_TO_PROCESS_ORDER"));
    public static final BaseCodeMsg FAILED_TO_WITHDRAWAL_1 = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.FAILED_TO_WITHDRAWAL_1"));
    public static final BaseCodeMsg FAILED_TO_WITHDRAWAL_2 = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.FAILED_TO_WITHDRAWAL_2"));
    public static final BaseCodeMsg FAILED_TO_WITHDRAWAL_3 = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.FAILED_TO_WITHDRAWAL_3"));
    public static final BaseCodeMsg FAILED_TO_WITHDRAWAL_4 = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.FAILED_TO_WITHDRAWAL_4"));
    public static final BaseCodeMsg FAILED_TO_WITHDRAWAL_5 = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.FAILED_TO_WITHDRAWAL_5"));
    public static final BaseCodeMsg FAILED_TO_WITHDRAWAL_6 = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.FAILED_TO_WITHDRAWAL_6"));
    public static final BaseCodeMsg FAILED_TO_WITHDRAWAL_7 = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.FAILED_TO_WITHDRAWAL_7"));
    public static final BaseCodeMsg FAILED_TO_WITHDRAWAL_8 = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.FAILED_TO_WITHDRAWAL_8"));
    public static final BaseCodeMsg FAILED_TO_WITHDRAWAL_9 = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.FAILED_TO_WITHDRAWAL_9"));
    public static final BaseCodeMsg FAILED_TO_WITHDRAWAL_10 = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.FAILED_TO_WITHDRAWAL_10"));
    public static final BaseCodeMsg FAILED_TO_WITHDRAWAL_11 = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.FAILED_TO_WITHDRAWAL_11"));
    public static final BaseCodeMsg FAILED_TO_WITHDRAWAL_12 = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.FAILED_TO_WITHDRAWAL_12"));
    public static final BaseCodeMsg FAILED_TO_WITHDRAWAL_13 = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.FAILED_TO_WITHDRAWAL_13"));
    public static final BaseCodeMsg EXCHANGE_ERROR_TIPS = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.EXCHANGE_ERROR_TIPS"));
    public static final BaseCodeMsg FAILED_TO_WITHDRAWAL_14 = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.FAILED_TO_WITHDRAWAL_14"));
    public static final BaseCodeMsg NOT_SUPPORT_EXCHANGE_METHOD = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.NOT_SUPPORT_EXCHANGE_METHOD"));
    public static final BaseCodeMsg SERVICE_FAILED = BaseCodeMsg.app(400, MessageUtils.getLocale("errorCode.SERVICE_FAILED"));
    public static final BaseCodeMsg APPROVE_FAILED = BaseCodeMsg.app(400, MessageUtils.getLocale("errorCode.APPROVE_FAILED"));
    public static final BaseCodeMsg UNIONPAY_NOT_SUPPORTED = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.UNIONPAY_NOT_SUPPORTED"));
    public static final BaseCodeMsg APPROVE_FAILED_2 = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.APPROVE_FAILED_2"));
    public static final BaseCodeMsg REPEAT_PURCHASE = BaseCodeMsg.app(200, MessageUtils.getLocale("errorCode.REPEAT_PURCHASE"));
    public static final BaseCodeMsg ORDER_READY_PAY = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.ORDER_READY_PAY"));
    public static final BaseCodeMsg PAY_SERVICE_ERROR = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.PAY_SERVICE_ERROR"));
    public static final BaseCodeMsg FIND_ORDER_ERROR = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.FIND_ORDER_ERROR"));
    public static final BaseCodeMsg SETTLE_ERROR = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.SETTLE_ERROR"));
    public static final BaseCodeMsg SETTLE_ERROR_NOT_BIND_PHONE = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.SETTLE_ERROR_NOT_BIND_PHONE"));
    public static final BaseCodeMsg APPROVE_TYPE_ERROR = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.APPROVE_TYPE_ERROR"));
    public static final BaseCodeMsg DIAMOND_TYPE_ERROR = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.DIAMOND_TYPE_ERROR"));
    public static final BaseCodeMsg AVATAR_NOT_EMPTY = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.AVATAR_NOT_EMPTY"));
    public static final BaseCodeMsg PROCESSING_WAIT = BaseCodeMsg.app(1002, MessageUtils.getLocale("errorCode.PROCESSING_WAIT"));
    public static final BaseCodeMsg CANCEL_ACCOUNT_1 = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.CANCEL_ACCOUNT_1"));
    public static final BaseCodeMsg CANCEL_ACCOUNT_2 = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.CANCEL_ACCOUNT_2"));
    public static final BaseCodeMsg CANCEL_ACCOUNT_3 = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.CANCEL_ACCOUNT_3"));
    public static final BaseCodeMsg CANCEL_ACCOUNT_4 = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.CANCEL_ACCOUNT_4"));
    public static final BaseCodeMsg CANCEL_ACCOUNT_5 = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.CANCEL_ACCOUNT_5"));
    public static final BaseCodeMsg ACCOUNT_NOT_BIND_PHONE = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.ACCOUNT_NOT_BIND_PHONE"));
    public static final BaseCodeMsg ACCOUNT_NOT_SET_PASSWORD = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.ACCOUNT_NOT_SET_PASSWORD"));
    public static final BaseCodeMsg DUPLICATE_PASSWORD = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.DUPLICATE_PASSWORD"));
    public static final BaseCodeMsg VERIFICATION_CODE = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.VERIFICATION_CODE"));
    public static final BaseCodeMsg TIMEOUT = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.TIMEOUT"));
    public static final BaseCodeMsg ACTIVITY_NOT_BEGIN = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.ACTIVITY_NOT_BEGIN"));
    public static final BaseCodeMsg JOIN_LIMIT = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.JOIN_LIMIT"));
    public static final BaseCodeMsg ADVANCED_LOTTERY_NOT_OPEN = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.ADVANCED_LOTTERY_NOT_OPEN"));
    public static final BaseCodeMsg ACTIVITY_IS_GONE_J17 = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.ACTIVITY_IS_GONE_J17"));
    public static final BaseCodeMsg ACTIVITY_IS_GONE_J20 = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.ACTIVITY_IS_GONE_J20"));
    public static final BaseCodeMsg ACTIVITY_IS_NOT_SETTING_j19 = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.ACTIVITY_IS_NOT_SETTING_j19"));
    public static final BaseCodeMsg ACTIVITY_IS_GONE = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.ACTIVITY_IS_GONE"));
    public static final BaseCodeMsg ONE_DAY_ONE_CHANCE = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.ONE_DAY_ONE_CHANCE"));
    public static final BaseCodeMsg NO_RECEIVED = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.NO_RECEIVED"));
    public static final BaseCodeMsg UP_TO_30 = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.UP_TO_30"));
    public static final BaseCodeMsg PERSON_RECEIVING_GIFT_NOT_EMPTY = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.PERSON_RECEIVING_GIFT_NOT_EMPTY"));
    public static final BaseCodeMsg GIFT_IS_EXIST = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.GIFT_IS_EXIST"));
    public static final BaseCodeMsg GIFT_NEED_VIP = BaseCodeMsg.app(3666, MessageUtils.getLocale("errorCode.GIFT_NEED_VIP"));
    public static final BaseCodeMsg UPGRADE_LEVEL = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.UPGRADE_LEVEL"));
    public static final BaseCodeMsg GIFT_FAILED_1 = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.GIFT_FAILED_1"));
    public static final BaseCodeMsg GIFT_FAILED_2 = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.GIFT_FAILED_2"));
    public static final BaseCodeMsg ACCOUNT_DEDUCTION_FAILED = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.ACCOUNT_DEDUCTION_FAILED"));
    public static final BaseCodeMsg GIFT_VOUCHERS_NOT_ENOUGH = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.GIFT_VOUCHERS_NOT_ENOUGH"));
    public static final BaseCodeMsg DELETE_SUCCESS = BaseCodeMsg.app(0, MessageUtils.getLocale("errorCode.DELETE_SUCCESS"));
    public static final BaseCodeMsg MUSIC_NAME_NOT_EMPTY = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.MUSIC_NAME_NOT_EMPTY"));
    public static final BaseCodeMsg MUSIC_NOT_EMPTY = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.MUSIC_NOT_EMPTY"));
    public static final BaseCodeMsg MUSIC_EXIST = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.MUSIC_EXIST"));
    public static final BaseCodeMsg MUSIC_UPLOADED = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.MUSIC_UPLOADED"));
    public static final BaseCodeMsg ADD_FAILED = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.ADD_FAILED"));
    public static final BaseCodeMsg HAS_APPENDED = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.HAS_APPENDED"));
    public static final BaseCodeMsg ONLY_USER_ON_SEAT = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.ONLY_USER_ON_SEAT"));
    public static final BaseCodeMsg ONLY_MASTER_DELETE = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.ONLY_MASTER_DELETE"));
    public static final BaseCodeMsg NONE_RETRO_SIGNING = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.NONE_RETRO_SIGNING"));
    public static final BaseCodeMsg EXCHANGE_NOT_EXIST = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.EXCHANGE_NOT_EXIST"));
    public static final BaseCodeMsg NONE_NEW_USER = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.NONE_NEW_USER"));
    public static final BaseCodeMsg NONE_NEW_DEVICE = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.NONE_NEW_DEVICE"));
    public static final BaseCodeMsg IS_BINDING = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.IS_BINDING"));
    public static final BaseCodeMsg EMPTY_1 = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.EMPTY_1"));
    public static final BaseCodeMsg RESTRICTIONS_5 = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.RESTRICTIONS_5"));
    public static final BaseCodeMsg TIME_NOT_BEGIN = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.TIME_NOT_BEGIN"));
    public static final BaseCodeMsg INSUFFICIENT_USER = BaseCodeMsg.app(2012, MessageUtils.getLocale("errorCode.INSUFFICIENT_USER"));
    public static final BaseCodeMsg BUY_NUMBER_ERROR = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.BUY_NUMBER_ERROR"));
    public static final BaseCodeMsg AGAIN_EXCHANGE = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.AGAIN_EXCHANGE"));
    public static final BaseCodeMsg COMPLETED_TODAY = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.COMPLETED_TODAY"));
    public static final BaseCodeMsg NONE_CONVERTIBLE_1 = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.NONE_CONVERTIBLE_1"));
    public static final BaseCodeMsg NO_ORDER = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.NO_ORDER"));
    public static final BaseCodeMsg SERVICE_LIMIT_STRING_1 = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.SERVICE_LIMIT_STRING_1"));
    public static final BaseCodeMsg SERVICE_LIMIT_STRING_2 = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.SERVICE_LIMIT_STRING_2"));
    public static final BaseCodeMsg COUPON_CAN_NOT_USE = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.COUPON_CAN_NOT_USE"));
    public static final BaseCodeMsg COUPON_NOT_EXIST = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.COUPON_NOT_EXIST"));
    public static final BaseCodeMsg ORDER_HAS_PAY = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.ORDER_HAS_PAY"));
    public static final BaseCodeMsg ORDER_HAS_CANCEL = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.ORDER_HAS_CANCEL"));
    public static final BaseCodeMsg ORDER_HAS_CALLBACK = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.ORDER_HAS_CALLBACK"));
    public static final BaseCodeMsg ORDER_TO_BE_PAY = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.ORDER_TO_BE_PAY"));
    public static final BaseCodeMsg ORDER_REFUND = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.ORDER_REFUND"));
    public static final BaseCodeMsg UPLOAD_PIC_DESC = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.UPLOAD_PIC_DESC"));
    public static final BaseCodeMsg ORDER_NOT_ENTER_APPROVED = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.ORDER_NOT_ENTER_APPROVED"));
    public static final BaseCodeMsg HAS_APPEND = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.HAS_APPEND"));
    public static final BaseCodeMsg NO_PERMISSION_1 = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.NO_PERMISSION_1"));
    public static final BaseCodeMsg HAS_GUILD = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.HAS_GUILD"));
    public static final BaseCodeMsg GUILD_NOT_EXIST = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.GUILD_NOT_EXIST"));
    public static final BaseCodeMsg HAS_APPLY = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.HAS_APPLY"));
    public static final BaseCodeMsg GUILD_MASTER = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.GUILD_MASTER"));
    public static final BaseCodeMsg APPLY_WAIT = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.APPLY_WAIT"));
    public static final BaseCodeMsg OPERATION_NOT_EXIST = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.OPERATION_NOT_EXIST"));
    public static final BaseCodeMsg GUILD_PROCESSED = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.GUILD_PROCESSED"));
    public static final BaseCodeMsg JOIN_OTHER_GUILD = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.JOIN_OTHER_GUILD"));
    public static final BaseCodeMsg GUILD_MASTER_NOT_OUT = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.GUILD_MASTER_NOT_OUT"));
    public static final BaseCodeMsg NOT_MEMBER_CURRENT_GUILD= BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.NOT_MEMBER_CURRENT_GUILD"));
    public static final BaseCodeMsg TO_BE_REVIEWED= BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.TO_BE_REVIEWED"));
    public static final BaseCodeMsg MASTER_NEED_APPROVE= BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.MASTER_NEED_APPROVE"));
    public static final BaseCodeMsg UNABLE_ADD_GUILD= BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.UNABLE_ADD_GUILD"));
    public static final BaseCodeMsg NOT_JOIN_GUILD_MASTER= BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.NOT_JOIN_GUILD_MASTER"));
    public static final BaseCodeMsg LIMIT_1500= BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.LIMIT_1500"));
    public static final BaseCodeMsg MASTER_NOT_OPERATION= BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.MASTER_NOT_OPERATION"));
    public static final BaseCodeMsg USER_NOT_IN_GUILD= BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.USER_NOT_IN_GUILD"));
    public static final BaseCodeMsg ADMIN_ONLY_CANCEL= BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.ADMIN_ONLY_CANCEL"));
    public static final BaseCodeMsg OPERATION_ERROR= BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.OPERATION_ERROR"));
    public static final BaseCodeMsg PK_OVER= BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.PK_OVER"));
    public static final BaseCodeMsg NUMBER_LIMIT = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.NUMBER_LIMIT"));
    public static final BaseCodeMsg CHOOSE_GIFT_ID = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.CHOOSE_GIFT_ID"));
    public static final BaseCodeMsg PK_TICKET_OVER = BaseCodeMsg.app(2023, MessageUtils.getLocale("errorCode.PK_TICKET_OVER"));
    public static final BaseCodeMsg HAS_TICKET = BaseCodeMsg.app(2023, MessageUtils.getLocale("errorCode.HAS_TICKET"));
    public static final BaseCodeMsg PK_RUNNING = BaseCodeMsg.app(2023, MessageUtils.getLocale("errorCode.PK_RUNNING"));
    public static final BaseCodeMsg INVALID_CODE = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.INVALID_CODE"));
    public static final BaseCodeMsg BACK_PACK_NULL = BaseCodeMsg.app(7002, MessageUtils.getLocale("errorCode.BACK_PACK_NULL"));
    public static final BaseCodeMsg CP_03_ERROR = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.CP_03_ERROR"));
    public static final BaseCodeMsg CP_NOT_EXIST = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.CP_NOT_EXIST"));
    public static final BaseCodeMsg RELATION_SHIP_EXIST = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.RELATION_SHIP_EXIST"));
    public static final BaseCodeMsg APPLY_LIMIT = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.APPLY_LIMIT"));
    public static final BaseCodeMsg GUILD_NOT_PERMISSION = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.GUILD_NOT_PERMISSION"));
    public static final BaseCodeMsg RELATION_WAIT_APPROVE = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.CP_WAIT_APPROVE"));
    public static final BaseCodeMsg LOGOUT = BaseCodeMsg.app(201, MessageUtils.getLocale("errorCode.LOGOUT"));
    public static final BaseCodeMsg SEND_LIMIT = BaseCodeMsg.app(-1, MessageUtils.getLocale("errorCode.SEND_LIMIT"));
}
