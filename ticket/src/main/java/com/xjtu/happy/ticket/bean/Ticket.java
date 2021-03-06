package com.xjtu.happy.ticket.bean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.event.EventListenerFactory;

import java.math.BigDecimal;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
public class Ticket {
    //车票id
    private int ticketId;

    //订单号
    private String orderNo;

    //车次号
    private String trainNum;

    //座位id
    private int seatId;

    //座位编号
    private String seatNo;

    //乘车日期
    private java.sql.Date travelTime;

    //价格
    private BigDecimal price;

    //姓名
    private String name;

    //证件号
    private String identityNum;

    //出发时间
    private Time startTime;

    //到达时间
    private Time endTime;

    //出发站id
    private int startStationid;

    //出发站名称
    private String startStationName;

    //到达站id
    private int endStationid;

    //到达站名称
    private String endStationName;

    //票状态
    private String ticketStatus;

    //购票用户
    private int ticketUserId;
    public int orderstate;

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getTrainNum() {
        return trainNum;
    }

    public void setTrainNum(String trainNum) {
        this.trainNum = trainNum;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public java.sql.Date getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(java.sql.Date travelTime) {
        this.travelTime = travelTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentityNum() {
        return identityNum;
    }

    public void setIdentityNum(String identityNum) {
        this.identityNum = identityNum;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public int getStartStationid() {
        return startStationid;
    }

    public void setStartStationid(int startStationid) {
        this.startStationid = startStationid;
    }

    public String getStartStationName() {
        return startStationName;
    }

    public void setStartStationName(String startStationName) {
        this.startStationName = startStationName;
    }

    public int getEndStationid() {
        return endStationid;
    }

    public void setEndStationid(int endStationid) {
        this.endStationid = endStationid;
    }

    public String getEndStationName() {
        return endStationName;
    }

    public void setEndStationName(String endStationName) {
        this.endStationName = endStationName;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public int getTicketUserId() {
        return ticketUserId;
    }

    public void setTicketUserId(int ticketUserId) {

        this.ticketUserId = ticketUserId;
    }

    public int getOrderstate() {
        return orderstate;
    }

    public void setOrderstate() throws ParseException {
        int i = this.orderstatus();
        this.orderstate = i;
    }

    //判断车票类型
    public int orderstatus() throws ParseException {
        String traveltime=  this.travelTime.toString();
        String time=this.startTime.toString();
        //发车时间
        String starttime=traveltime+' '+time;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date starttime1=df.parse(starttime);
        Long sT=starttime1.getTime();
        Long nowtime=System.currentTimeMillis()+60*60*1000;
        if (nowtime>sT){
            //时间逾期，不可改票，不可退票
            return 0;
        }
        else {
            String status=this.ticketStatus;
            if(status.equals("refunded")){
                //不可改票，退票成功
                return 1;
            }
            if (status.equals("rebooked")){
                //改票成功，可以退票
                return 2;
            }
            //正常票,可以改票，可以退票
            return 3;
        }
    }
}
