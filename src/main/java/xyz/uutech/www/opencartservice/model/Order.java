package xyz.uutech.www.opencartservice.model;

/**
 * Created by PC on 2016/5/11.
 */
public class Order extends BaseModel {
    private Integer id;
    private String ordernum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum;
    }
}
