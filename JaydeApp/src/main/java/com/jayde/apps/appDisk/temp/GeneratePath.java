package com.jayde.apps.appDisk.temp;

import com.jayde.util.diskutils.OnlyDirectory;
import lombok.extern.log4j.Log4j;

import java.io.File;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appDisk.temp
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2018/12/13 下午10:53
 * @UpdateUser: The Modified user
 * @UpdateDate: 2018/12/13 下午10:53
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
@Log4j
public class GeneratePath {
    String rootPath = "/Volumes/MusicP5T/标准文件夹/【音频】/流行音乐(无损)/华语/华语男/";

    public GeneratePath() {
        step1_likepath();

    }

    public void step1_likepath() {
        File rootFile = new File(rootPath);
        File[] paths = rootFile.listFiles(new OnlyDirectory());
        for (File path : paths) {
            log.info(path);
            if (path.getName().startsWith("{歌手}")) {
                String singerName = path.getName().substring(4);
                log.info(singerName);
                File[] files = path.listFiles(new OnlyDirectory());
                String likePathName = singerName + "<★" + singerName + "精选★>";
                boolean existPath = false;
                for (File file : files) {
                    if (file.getName().equals(likePathName)) {
                        existPath = true;
                    }
                }
                if (existPath == false) {
                    File newPath = new File(path.getAbsolutePath()+"/" + likePathName);
//                    newPath.createNewFile();
                    log.info(newPath.getAbsolutePath());
                    newPath.mkdir();
                }
            }
        }
    }

    public static void main(String[] args) {
        GeneratePath generatePath = new GeneratePath();
    }
}
