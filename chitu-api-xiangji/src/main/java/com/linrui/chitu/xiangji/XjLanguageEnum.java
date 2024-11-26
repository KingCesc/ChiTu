package com.linrui.chitu.xiangji;


/**
 * 象寄语言枚举
 *
 * @author nick.zw
 * @since 2024/6/19
 */
public enum XjLanguageEnum {

    CHS("CHS", "中文简体"),

    CHT("CHT", "中文繁体"),

    ENG("ENG", "英语"),

    ID("ID", "印尼语"),

    MS("MS", "马来语"),

    TH("TH", "泰语"),

    JW("JW", "爪哇语"),

    TL("TL", "他加禄语(菲律宾)"),

    PLK("ENG", "波兰语"),

    PTB("PTB", "葡萄牙语"),

    ESP("ESP", "西班牙语"),

    RUS("RUS", "俄语"),

    KOR("KOR", "韩语"),

    JPN("JPN", "日语"),

    MY("MY", "缅甸语"),

    NLD("NLD", "荷兰语"),

    ITA("ITA", "意大利语"),

    HUN("HUN", "匈牙利语"),

    FRA("FRA", "法语"),

    DEU("DEU", "德语"),

    CSY("CSY", "捷克语"),

    AR("AR", "阿拉伯语");

    XjLanguageEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private final String code;
    private final String desc;

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
