package net.jayde.app.pm.pojo;

import java.util.ArrayList;
import java.util.List;

public class ProjectObject {
  List<ProjectQuestion> questions = new ArrayList<ProjectQuestion>();
  String id;
  String sortorder;
  String pname;
  String gid;
  String powner;
  String built;
  String bcontact;
  String tcontact;
  String rcontact;
  String precompany;
  String predate;
  String premoney;
  String presellcontact;
  String prebuildcontact;
  String premaintaincontact;
  String prefunction;
  String premachine;
  String preuseip;
  String pretestip;
  String prerelation;
  String newcompany;
  String newdate;
  String newmoney;
  String newsellcontact;
  String newbuildcontact;
  String newmaintaincontact;
  String newfunction;
  String newmachine;
  String newrelation;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getSortorder() {
    return sortorder;
  }

  public void setSortorder(String sortorder) {
    this.sortorder = sortorder;
  }

  public String getPname() {
    return pname;
  }

  public void setPname(String pname) {
    this.pname = pname;
  }

  public String getGid() {
    return gid;
  }

  public void setGid(String gid) {
    this.gid = gid;
  }

  public String getPowner() {
    return powner;
  }

  public void setPowner(String powner) {
    this.powner = powner;
  }

  public String getBuilt() {
    return built;
  }

  public void setBuilt(String built) {
    this.built = built;
  }

  public String getBcontact() {
    return bcontact;
  }

  public void setBcontact(String bcontact) {
    this.bcontact = bcontact;
  }

  public String getTcontact() {
    return tcontact;
  }

  public void setTcontact(String tcontact) {
    this.tcontact = tcontact;
  }

  public String getRcontact() {
    return rcontact;
  }

  public void setRcontact(String rcontact) {
    this.rcontact = rcontact;
  }

  public String getPrecompany() {
    return precompany;
  }

  public void setPrecompany(String precompany) {
    this.precompany = precompany;
  }

  public String getPredate() {
    return predate;
  }

  public void setPredate(String predate) {
    this.predate = predate;
  }

  public String getPremoney() {
    return premoney;
  }

  public void setPremoney(String premoney) {
    this.premoney = premoney;
  }

  public String getPresellcontact() {
    return presellcontact;
  }

  public void setPresellcontact(String presellcontact) {
    this.presellcontact = presellcontact;
  }

  public String getPrebuildcontact() {
    return prebuildcontact;
  }

  public void setPrebuildcontact(String prebuildcontact) {
    this.prebuildcontact = prebuildcontact;
  }

  public String getPremaintaincontact() {
    return premaintaincontact;
  }

  public void setPremaintaincontact(String premaintaincontact) {
    this.premaintaincontact = premaintaincontact;
  }

  public String getPrefunction() {
    return prefunction;
  }

  public void setPrefunction(String prefunction) {
    this.prefunction = prefunction;
  }

  public String getPremachine() {
    return premachine;
  }

  public void setPremachine(String premachine) {
    this.premachine = premachine;
  }

  public String getPreuseip() {
    return preuseip;
  }

  public void setPreuseip(String preuseip) {
    this.preuseip = preuseip;
  }

  public String getPretestip() {
    return pretestip;
  }

  public void setPretestip(String pretestip) {
    this.pretestip = pretestip;
  }

  public String getPrerelation() {
    return prerelation;
  }

  public void setPrerelation(String prerelation) {
    this.prerelation = prerelation;
  }

  public String getNewcompany() {
    return newcompany;
  }

  public void setNewcompany(String newcompany) {
    this.newcompany = newcompany;
  }

  public String getNewdate() {
    return newdate;
  }

  public void setNewdate(String newdate) {
    this.newdate = newdate;
  }

  public String getNewmoney() {
    return newmoney;
  }

  public void setNewmoney(String newmoney) {
    this.newmoney = newmoney;
  }

  public String getNewsellcontact() {
    return newsellcontact;
  }

  public void setNewsellcontact(String newsellcontact) {
    this.newsellcontact = newsellcontact;
  }

  public String getNewbuildcontact() {
    return newbuildcontact;
  }

  public void setNewbuildcontact(String newbuildcontact) {
    this.newbuildcontact = newbuildcontact;
  }

  public String getNewmaintaincontact() {
    return newmaintaincontact;
  }

  public void setNewmaintaincontact(String newmaintaincontact) {
    this.newmaintaincontact = newmaintaincontact;
  }

  public String getNewfunction() {
    return newfunction;
  }

  public void setNewfunction(String newfunction) {
    this.newfunction = newfunction;
  }

  public String getNewmachine() {
    return newmachine;
  }

  public void setNewmachine(String newmachine) {
    this.newmachine = newmachine;
  }

  public String getNewrelation() {
    return newrelation;
  }

  public void setNewrelation(String newrelation) {
    this.newrelation = newrelation;
  }

  public List<ProjectQuestion> getQuestions() {
    return questions;
  }

  public void setQuestions(List<ProjectQuestion> questions) {
    this.questions = questions;
  }

  @Override
  public String toString() {
    return "PO:{" + pname + '}';
  }

  public String showProjectInfo() {
    return "ProjectObject{\n" + "questions=" + questions + ", \nid='" + id + '\'' + ", \nsortorder='" + sortorder + '\''
        + ", \npname='" + pname + '\'' + ", \ngid='" + gid + '\'' + ", \npowner='" + powner + '\'' + ", \nbuilt='" + built
        + '\'' + ", \nbcontact='" + bcontact + '\'' + ", \ntcontact='" + tcontact + '\'' + ", \nrcontact='" + rcontact + '\''
        + ", \n\nprecompany='" + precompany + '\'' + ", \npredate='" + predate + '\'' + ", \npremoney='" + premoney + '\''
        + ", \npresellcontact='" + presellcontact + '\'' + ", \nprebuildcontact='" + prebuildcontact + '\''
        + ", \npremaintaincontact='" + premaintaincontact + '\'' + ", \nprefunction='" + prefunction + '\''
        + ", \npremachine='" + premachine + '\'' + ", \npreuseip='" + preuseip + '\'' + ", \npretestip='" + pretestip + '\''
        + ", \nprerelation='" + prerelation + '\'' + ", \n\nnewcompany='" + newcompany + '\'' + ", \nnewdate='" + newdate + '\''
        + ", \nnewmoney='" + newmoney + '\'' + ", \nnewsellcontact='" + newsellcontact + '\'' + ", \nnewbuildcontact='"
        + newbuildcontact + '\'' + ", \nnewmaintaincontact='" + newmaintaincontact + '\'' + ", \nnewfunction='"
        + newfunction + '\'' + ", \nnewmachine='" + newmachine + '\'' + ", \nnewrelation='" + newrelation + '\'' + '}';
  }

  public String formatProjectMd(){
    String mdText = "";
    System.out.println("### "+pname);
    System.out.println("| 类型          | 内容                                       |");
    System.out.println("| :---------- | :--------------------------------------- |");
    System.out.println(formatOneFiled("项目名称",pname));
    System.out.println(formatOneFiled("相关部门",built));
    System.out.println(formatOneFiled("业务联系人/电话/邮件 ",bcontact));
    System.out.println(formatOneFiled("科技联系人/电话/邮件 ",tcontact));
    System.out.println(formatOneFiled("",""));
    System.out.println(formatOneFiled("原厂商",precompany));
    System.out.println(formatOneFiled("原建设时间",predate));
    System.out.println(formatOneFiled("原合同金额",premoney));
    System.out.println(formatOneFiled("原销售经理/电话/邮件",presellcontact));
    System.out.println(formatOneFiled("原项目经理/电话/邮件",prebuildcontact));
    System.out.println(formatOneFiled("原运维人员/电话/邮件",premaintaincontact));
    System.out.println(formatOneFiled("原主要功能",prefunction));
    System.out.println(formatOneFiled("原硬件架构",premaintaincontact));
    System.out.println(formatOneFiled("原生产环境",preuseip));
    System.out.println(formatOneFiled("原测试环境",pretestip));
    System.out.println(formatOneFiled("原关联系统",prerelation));
    System.out.println(formatOneFiled("",""));
    System.out.println(formatOneFiled("新厂商",newcompany));
    System.out.println(formatOneFiled("新建设时间",newdate));
    System.out.println(formatOneFiled("新合同金额",newmoney));
    System.out.println(formatOneFiled("新销售经理/电话/邮件",newsellcontact));
    System.out.println(formatOneFiled("新项目经理/电话/邮件",newbuildcontact));
    System.out.println(formatOneFiled("新运维人员/电话/邮件",newmaintaincontact));
    System.out.println(formatOneFiled("新主要功能",newfunction));
    System.out.println(formatOneFiled("新硬件架构",newmachine));
    System.out.println(formatOneFiled("新关联系统",newrelation));
    System.out.println(formatOneFiled("",""));

    return mdText;
  }

  private String formatOneFiled(String filedTitle,String filedValue){
    return  "| "+filedTitle+"        | "+filedValue+" |";
  }
}
