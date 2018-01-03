package cn.net.gxht.cms.query.entity;

/**
 * Created by Administrator on 2017/12/25.
 */
public class Renovate {
    /**翻新机概率*/
    private String probability;
    /**鉴定结果*/
    private String result;

    public void setProbability(String probability) {
        this.probability = probability;
    }

    public String getProbability() {
        return this.probability;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return this.result;
    }
}
