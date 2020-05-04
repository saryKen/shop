package com.model.order;

import com.model.product.ScenicSpot;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Order {
    private String orderId;//订单id

    private Integer userId;//用户Id

    private String scenicSpotInfo;//景点信息

    private Integer ticketNum;//门票数量

    private BigDecimal money;//订单金额，单位：元

    private Date startTime;//门票有效开始时间

    private Date endTime;//门票有效截止时间

    private Date createTime;//创建时间

    private String state;//订单状态


    private ScenicSpot scenicSpot;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getScenicSpotInfo() {
        return scenicSpotInfo;
    }

    public void setScenicSpotInfo(String scenicSpotInfo) {
        this.scenicSpotInfo = scenicSpotInfo == null ? null : scenicSpotInfo.trim();
    }

    public Integer getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(Integer ticketNum) {
        this.ticketNum = ticketNum;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}