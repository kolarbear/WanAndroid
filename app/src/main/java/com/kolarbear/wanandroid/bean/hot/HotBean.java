package com.kolarbear.wanandroid.bean.hot;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2018/6/1.
 */
@Entity
public class HotBean {

    /**
     * id : 6
     * link :
     * name : 面试
     * order : 1
     * visible : 1
     */
    @Id(autoincrement = true)
    private Long index;
    private int id;
    private String link;
    private String name;
    private int order;
    private int visible;

    @Generated(hash = 1028864897)
    public HotBean(Long index, int id, String link, String name, int order,
            int visible) {
        this.index = index;
        this.id = id;
        this.link = link;
        this.name = name;
        this.order = order;
        this.visible = visible;
    }

    @Generated(hash = 1964254435)
    public HotBean() {
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

    public int getVisible() {
        return visible;
    }

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public Long getIndex() {
        return this.index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }
}
