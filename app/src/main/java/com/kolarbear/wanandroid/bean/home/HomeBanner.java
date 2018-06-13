package com.kolarbear.wanandroid.bean.home;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2018/5/21.
 */
@Entity
public class HomeBanner {


    /**
     * desc : 最新项目上线啦~
     * id : 13
     * imagePath : http://www.wanandroid.com/blogimgs/5ae04af4-72b9-4696-81cb-1644cdcd2d29.jpg
     * isVisible : 1
     * order : 0
     * title : 最新项目上线啦~
     * type : 0
     * url : http://www.wanandroid.com/pindex
     */
    @Id(autoincrement = true)
    private Long index;
    private String desc;
    private int id;
    private String imagePath;
    private int isVisible;
    private int order;
    private String title;
    private int type;
    private String url;

    @Generated(hash = 136412154)
    public HomeBanner(Long index, String desc, int id, String imagePath, int isVisible,
            int order, String title, int type, String url) {
        this.index = index;
        this.desc = desc;
        this.id = id;
        this.imagePath = imagePath;
        this.isVisible = isVisible;
        this.order = order;
        this.title = title;
        this.type = type;
        this.url = url;
    }

    @Generated(hash = 2105823777)
    public HomeBanner() {
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(int isVisible) {
        this.isVisible = isVisible;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getIndex() {
        return this.index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }
}
