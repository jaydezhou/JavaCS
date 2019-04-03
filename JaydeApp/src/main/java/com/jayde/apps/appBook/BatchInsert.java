package com.jayde.apps.appBook;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appBook
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-02-19 17:05
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-02-19 17:05
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class BatchInsert {
    public static void main(String[] args) {
        String PID = "02-01-07";
        String[] names = {
                "凉州词(王之涣)",
                "出塞·秦时明月汉时关(王昌龄)",
                "塞上曲·蝉鸣空桑林(王昌龄)",
                "塞下曲·饮马渡秋水(王昌龄)",
                "长信怨(王昌龄)",
                "渭城曲(王维)",
                "秋夜曲(王维)",
                "洛阳女儿行(王维)",
                "老将行(王维)",
                "桃源行(王维)",
                "清平调·云想衣裳花想容(李白)",
                "清平调·一枝红艳露凝香(李白)",
                "清平调·名花倾国两相欢(李白)",
                "行路难·金樽清酒斗十千(李白)",
                "行路难·大道如青天(李白)",
                "行路难·有耳莫洗颍川水(李白)",
                "将进酒(李白)",
                "玉阶怨(李白)",
                "长相思·其一(李白)",
                "长相思·其二(李白)",
                "长干行·其一(李白)",
                "蜀道难(李白)",
                "子夜吴歌·春歌(李白)",
                "子夜吴歌·夏歌(李白)",
                "子夜吴歌·秋歌(李白)",
                "子夜吴歌·冬歌(李白)",
                "关山月(李白)",
                "江南曲(李益)",
                "古从军行(李颀)",
                "哀王孙(杜甫)",
                "兵车行(杜甫)",
                "丽人行(杜甫)",
                "哀江头(杜甫)",
                "金缕衣(佚名)",
                "独不见(沈佺期)",
                "烈女操(孟郊)",
                "游子吟(孟郊)",
                "燕歌行(高适)",
                "长干行·君家何处住(崔颢)",
                "长干行·家临九江水(崔颢)",
                "塞下曲·鹫翎金仆姑(卢纶)",
                "塞下曲·林暗草惊风(卢纶)",
                "塞下曲·月黑雁飞高(卢纶)",
                "塞下曲·野幕敞琼筵(卢纶)"
        };


        for (int i = 0; i < names.length; i++) {
            String aid;
            if (i < 9) {
                aid = PID + "-0" + (i + 1);
            } else {
                aid = PID + "-" + (i + 1);
            }
            System.out.println("insert into bookinfo value('" +
                    aid + "','" + PID + "','ARTICLE','" + names[i] + "','');");
            System.out.println("insert into bookarticle value('"+aid+"-01','"+aid+"',1,'','','"+names[i]+":正文');");
            System.out.println("insert into bookarticle value('"+aid+"-02','"+aid+"',0,'','','"+names[i]+":注释');");
            System.out.println("insert into bookarticle value('"+aid+"-03','"+aid+"',0,'','','"+names[i]+":鉴赏');");
            System.out.println("insert into bookarticle value('"+aid+"-04','"+aid+"',0,'','','"+names[i]+":背景');");
            System.out.println("insert into bookarticle value('"+aid+"-05','"+aid+"',0,'','','"+names[i]+":简析');");
            System.out.println("insert into bookarticle value('"+aid+"-06','"+aid+"',0,'','','"+names[i]+":');");
            System.out.println("insert into bookarticle value('"+aid+"-07','"+aid+"',0,'','','"+names[i]+":');");
        }


    }
}

