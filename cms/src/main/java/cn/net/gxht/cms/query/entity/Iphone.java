package cn.net.gxht.cms.query.entity;

/**
 * Created by Administrator on 2017/12/25.
 * 手机查询信息实体类
 */
public class Iphone {
    private String key="081e15b69465c5c6f99ace5b8fde39d5";
    /**
     * 序列号
     */
    private String sn;
    /**
     * 型号
     */
    private String model;
    /**
     * 容量
     */
    private String capacity;
    /**
     * 颜色
     */
    private String color;
    /**
     * 版本
     */
    private String number;
    /**
     * 类型
     */
    private String identifier;
    /**
     * 模型
     */
    private String order;
    /**
     * 网络
     */
    private String network;
    /**
     * 激活状态
     */
    private boolean activated;
    /**
     * 激活日期
     */
    private Purchase purchase;
    /**
     * 保修结束日期
     */
    private String coverage;
    /**
     * 保修剩余
     */
    private int daysleft;
    /**
     * 技术支持
     */
    private String support;
    /**
     * 产品信息
     */
    private Production production;
    /**
     * 鉴定信息
     */
    private Renovate renovate;
    /**
     * 图片信息
     */
    private String img;

    public String getKey() {
        return key;
    }
    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getSn() {
        return this.sn;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return this.model;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getCapacity() {
        return this.capacity;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return this.number;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getOrder() {
        return this.order;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getNetwork() {
        return this.network;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public boolean getActivated() {
        return this.activated;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Purchase getPurchase() {
        return this.purchase;
    }

    public void setCoverage(String coverage) {
        this.coverage = coverage;
    }

    public String getCoverage() {
        return this.coverage;
    }

    public void setDaysleft(int daysleft) {
        this.daysleft = daysleft;
    }

    public int getDaysleft() {
        return this.daysleft;
    }

    public void setSupport(String support) {
        this.support = support;
    }

    public String getSupport() {
        return this.support;
    }

    public void setProduction(Production production) {
        this.production = production;
    }

    public Production getProduction() {
        return this.production;
    }

    public void setRenovate(Renovate renovate) {
        this.renovate = renovate;
    }

    public Renovate getRenovate() {
        return this.renovate;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImg() {
        return this.img;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"sn\":\"")
                .append(sn).append('\"');
        sb.append(",\"model\":\"")
                .append(model).append('\"');
        sb.append(",\"capacity\":\"")
                .append(capacity).append('\"');
        sb.append(",\"color\":\"")
                .append(color).append('\"');
        sb.append(",\"number\":\"")
                .append(number).append('\"');
        sb.append(",\"identifier\":\"")
                .append(identifier).append('\"');
        sb.append(",\"order\":\"")
                .append(order).append('\"');
        sb.append(",\"network\":\"")
                .append(network).append('\"');
        sb.append(",\"activated\":")
                .append(activated);
        sb.append(",\"purchase\":")
                .append(purchase);
        sb.append(",\"coverage\":\"")
                .append(coverage).append('\"');
        sb.append(",\"daysleft\":")
                .append(daysleft);
        sb.append(",\"support\":\"")
                .append(support).append('\"');
        sb.append(",\"production\":")
                .append(production);
        sb.append(",\"img\":\"")
                .append(img).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
