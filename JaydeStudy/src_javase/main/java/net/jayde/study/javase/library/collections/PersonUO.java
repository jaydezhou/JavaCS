package net.jayde.study.javase.basic.collections;

import lombok.Data;

/**
 * @ProjectName: JavaCS
 * @Package: net.jayde.study.javase.basic.collections
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2018/5/19 上午9:23
 * @UpdateUser: The Modified user
 * @UpdateDate: 2018/5/19 上午9:23
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
@Data
public class PersonUO {
    String id;
    String name;

    public PersonUO(String inputId, String inputName) {
        id = inputId;
        name = inputName;
    }
}
