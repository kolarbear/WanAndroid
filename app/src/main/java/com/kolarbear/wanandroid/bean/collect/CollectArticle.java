package com.kolarbear.wanandroid.bean.collect;

import java.util.List;

/**
 * Created by Administrator on 2018/5/31.
 */

public class CollectArticle {

    /**
     * curPage : 1
     * datas : [{"author":"鸿洋","chapterId":61,"chapterName":"Android测试相关","courseId":13,"desc":"","envelopePic":"","id":6027,"link":"http://www.wanandroid.com/blog/show/2085","niceDate":"2018-03-22","origin":"","originId":2490,"publishTime":1521686926000,"title":"必知必会 | Android 测试相关的方方面面都在这儿","userId":211,"visible":0,"zan":0},{"author":"liuhe688","chapterId":10,"chapterName":"Activity","courseId":13,"desc":"","envelopePic":"","id":238,"link":"http://blog.csdn.net/liuhe688/article/details/6761337","niceDate":"2017-10-25","origin":"","originId":4,"publishTime":1508943755000,"title":"基础总结篇之三：Activity的task相关","userId":211,"visible":0,"zan":0},{"author":"Xing","chapterId":10,"chapterName":"Activity","courseId":13,"desc":"","envelopePic":"","id":218,"link":"http://iluhcm.com/2017/03/05/handle-asynchronous-callbacks-when-activity-finishes/","niceDate":"2017-10-24","origin":"","originId":1350,"publishTime":1508825320000,"title":"如何在Activity/Fragment结束时处理异步回调？","userId":211,"visible":0,"zan":0}]
     * offset : 0
     * over : true
     * pageCount : 1
     * size : 20
     * total : 3
     */

    private int curPage;
    private int offset;
    private boolean over;
    private int pageCount;
    private int size;
    private int total;
    private List<DatasBean> datas;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * author : 鸿洋
         * chapterId : 61
         * chapterName : Android测试相关
         * courseId : 13
         * desc :
         * envelopePic :
         * id : 6027
         * link : http://www.wanandroid.com/blog/show/2085
         * niceDate : 2018-03-22
         * origin :
         * originId : 2490
         * publishTime : 1521686926000
         * title : 必知必会 | Android 测试相关的方方面面都在这儿
         * userId : 211
         * visible : 0
         * zan : 0
         */

        private String author;
        private int chapterId;
        private String chapterName;
        private int courseId;
        private String desc;
        private String envelopePic;
        private int id;
        private String link;
        private String niceDate;
        private String origin;
        private int originId;
        private long publishTime;
        private String title;
        private int userId;
        private int visible;
        private int zan;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public int getChapterId() {
            return chapterId;
        }

        public void setChapterId(int chapterId) {
            this.chapterId = chapterId;
        }

        public String getChapterName() {
            return chapterName;
        }

        public void setChapterName(String chapterName) {
            this.chapterName = chapterName;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getEnvelopePic() {
            return envelopePic;
        }

        public void setEnvelopePic(String envelopePic) {
            this.envelopePic = envelopePic;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getNiceDate() {
            return niceDate;
        }

        public void setNiceDate(String niceDate) {
            this.niceDate = niceDate;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public int getOriginId() {
            return originId;
        }

        public void setOriginId(int originId) {
            this.originId = originId;
        }

        public long getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(long publishTime) {
            this.publishTime = publishTime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getVisible() {
            return visible;
        }

        public void setVisible(int visible) {
            this.visible = visible;
        }

        public int getZan() {
            return zan;
        }

        public void setZan(int zan) {
            this.zan = zan;
        }
    }
}
