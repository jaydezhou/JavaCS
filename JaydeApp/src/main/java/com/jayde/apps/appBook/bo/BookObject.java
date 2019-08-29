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
public class BookObject {
    String id;
    String name;
    int order;

    String tId;
    private BookType parentType;

    String sId;
    private BookSet parentSet;

    String aId;
    private Author author;

    long wordCounts;
    String comment;

    String bookText;

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

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
    }

    public BookType getParentType() {
        return parentType;
    }

    public void setParentType(BookType parentType) {
        this.parentType = parentType;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public BookSet getParentSet() {
        return parentSet;
    }

    public void setParentSet(BookSet parentSet) {
        this.parentSet = parentSet;
    }

    public String getaId() {
        return aId;
    }

    public void setaId(String aId) {
        this.aId = aId;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public long getWordCounts() {
        return wordCounts;
    }

    public void setWordCounts(long wordCounts) {
        this.wordCounts = wordCounts;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getBookText() {
        return bookText;
    }

    public void setBookText(String bookText) {
        this.bookText = bookText;
    }
}
