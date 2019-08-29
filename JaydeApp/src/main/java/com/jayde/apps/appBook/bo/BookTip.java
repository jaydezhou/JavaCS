package com.jayde.apps.appBook.bo;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appBook.bo
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-08-02 16:56
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-08-02 16:56
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class BookTip {
    static String[] TYPES =
            {"BookType", "BookSet", "BookObject", "BookVolumn", "BookChapter", "BookTip"};

    String id;
    String name;
    int order;

    String pId;
    String pType;

    String tipText;

    public static String[] getTYPES() {
        return TYPES;
    }

    public static void setTYPES(String[] TYPES) {
        BookTip.TYPES = TYPES;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getpType() {
        return pType;
    }

    public void setpType(String pType) {
        this.pType = pType;
    }

    public String getTipText() {
        return tipText;
    }

    public void setTipText(String tipText) {
        this.tipText = tipText;
    }
}
