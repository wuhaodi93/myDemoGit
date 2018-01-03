package cn.net.gxht.cms.query.entity;

/**
 * Created by Administrator on 2017/12/25.
 */
public class Production {
    /**出厂日期(start~end)*/
    private String start;
    /**出厂日期(start~end)*/
    private String end;
    /**产地*/
    private String origin;

    public void setStart(String start){
        this.start = start;
    }
    public String getStart(){
        return this.start;
    }
    public void setEnd(String end){
        this.end = end;
    }
    public String getEnd(){
        return this.end;
    }
    public void setOrigin(String origin){
        this.origin = origin;
    }
    public String getOrigin(){
        return this.origin;
    }
}
