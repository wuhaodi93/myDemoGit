package cn.net.gxht.cms.common.entity;

import org.apache.commons.collections.map.HashedMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/11.
 */
public class Page {
    /**
     * 当前页默认为1
     */
    private Integer currentPage=1;
    /**
     * 每夜默认为10条
     */
    private Integer pageSize=5;
    /**
     * 总条目数
     */
    private Integer amount;

    private Integer phoneamount;

    /**
     * 开始条目数
     * @return
     */
    private Integer startIndex=0;
    /**
     * 结束条目数
     */
    private Integer endIndex;

    /**
     * 总页数
     * @return
     */
    private Integer allPage=1;

    public void setAllPage(Integer allPage) {
        this.allPage = allPage;
    }

    public Integer getStartIndex(){
        startIndex=(currentPage-1)*pageSize;
    return startIndex;
    }
    public Integer getEndIndex(){
        if(amount!=null)
        {
            endIndex= currentPage*pageSize<amount?currentPage*pageSize-1:amount-1;
        }
        if(phoneamount!=null)
        {
            endIndex= currentPage*pageSize<phoneamount?currentPage*pageSize-1:phoneamount-1;
        }
        return endIndex;
    }
    public Integer getPhoneamount() {
        return phoneamount;
    }

    public void setPhoneamount(Integer phoneamount) {
        this.phoneamount = phoneamount;
    }
    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public void setEndIndex(Integer endIndex) {
        this.endIndex = endIndex;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getAllPage() {
        return allPage;
    }

    public void setAllPage() {
        if(amount!=null){
            allPage=amount%pageSize>0?amount/pageSize+1:amount/pageSize;
            if(amount==0){
                allPage=1;
            }
        }
        if(phoneamount!=null){
            allPage=phoneamount%pageSize>0?phoneamount/pageSize+1:phoneamount/pageSize;
            if(phoneamount==0){
                allPage=1;
            }
        }


    }

    @Override
    public String toString() {
        return "Page{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", amount=" + amount +
                ", phoneamount=" + phoneamount +
                ", startIndex=" + startIndex +
                ", endIndex=" + endIndex +
                ", allPage=" + allPage +
                '}';
    }
}
