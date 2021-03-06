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
public class BookVolumn {
    String id;
    String name;
    int order;

    String bId;
    private BookObject parentBook;

    String volumnText;

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

    public String getbId() {
        return bId;
    }

    public void setbId(String bId) {
        this.bId = bId;
    }

    public BookObject getParentBook() {
        return parentBook;
    }

    public void setParentBook(BookObject parentBook) {
        this.parentBook = parentBook;
    }

    public String getVolumnText() {
        return volumnText;
    }

    public void setVolumnText(String volumnText) {
        this.volumnText = volumnText;
    }
}
