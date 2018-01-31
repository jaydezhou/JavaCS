package net.jayde.app.musicTag.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class CueSongBean {

    private static Logger logger = LogManager.getLogger(CueSongBean.class.getName());
    /**
     * cue song bean
     * @author xuweilin
     *
     */
        private String title;  //title
        private String performer;  //performer
        private String indexBegin;  //begintime
        private String indexEnd;   //endtime
        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }
        public String getPerformer() {
            return performer;
        }
        public void setPerformer(String performer) {
            this.performer = performer;
        }
        public String getIndexBegin() {
            return indexBegin;
        }
        public void setIndexBegin(String indexBegin) {
            this.indexBegin = indexBegin;
        }
        public String getIndexEnd() {
            return indexEnd;
        }
        public void setIndexEnd(String indexEnd) {
            this.indexEnd = indexEnd;
        }

    @Override
    public String toString() {
        return "CueSongBean{" +
                "title='" + title + '\'' +
                ", performer='" + performer + '\'' +
                ", indexBegin='" + indexBegin + '\'' +
                ", indexEnd='" + indexEnd + '\'' +
                '}';
    }
}
