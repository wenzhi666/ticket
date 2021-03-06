package com.xjtu.happy.ticket.controller.order;

import com.xjtu.happy.ticket.bean.*;
import com.xjtu.happy.ticket.service.login.LoginService;
import com.xjtu.happy.ticket.service.login.UserService;
import com.xjtu.happy.ticket.service.management.TrainService;
import com.xjtu.happy.ticket.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class PayController {
    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;
    @Autowired
    TrainService trainService;
    @Autowired
    LoginService loginService;

    @RequestMapping("/submitPay")
    public String submitPay(User user, Model model, HttpServletRequest req){
        HttpSession session=req.getSession();

        Cookie[] cookies = req.getCookies();
        String strName = "";
        for (Cookie cookie : cookies) {
            switch(cookie.getName()){
                case "userName":
                    strName = cookie.getValue();
                    break;
                default:
                    break;
            }
        }
        User operateUser = loginService.GetUserByname(strName);

        TicketLeft ticketSelected=(TicketLeft)session.getAttribute("ticketSelected");
        Orders order = (Orders)session.getAttribute("order");
        TicketSeat seat = (TicketSeat)session.getAttribute("ticketseat");
        String name = (String)session.getAttribute("name");
        String identityNum = (String)session.getAttribute("identityNum");
        Ticket ticket = new Ticket();
        Train train = trainService.FindTrainById(ticketSelected.getTrainId());
        ticket.setOrderNo(order.getOrderNo());
        ticket.setTrainNum(train.getTrainNum());
        ticket.setSeatId(seat.getSeatId());
        ticket.setSeatNo(seat.getSeatType()+seat.getSeatNo());
        ticket.setTravelTime(ticketSelected.getTravelTime());
        ticket.setPrice(order.getTotalPrice());
        ticket.setName(name);
        ticket.setIdentityNum(identityNum);
        ticket.setStartTime(train.getStartTime());
        ticket.setEndTime(train.getEndTime());
        ticket.setStartStationid(train.getStartStationid());
        ticket.setEndStationid(train.getEndStationid());
        ticket.setStartStationName(train.getStartStationName());
        ticket.setEndStationName(train.getEndStationName());
        ticket.setTicketStatus("normal");
        ticket.setTicketUserId(operateUser.getUserId());

        if(orderService.InsertTicketAndChangeOrder(ticket)) {
            model.addAttribute("msg","支付成功");
            return "redirect:/orders";
        }
        else
        {
            model.addAttribute("msg","支付失败");
            return "order";
        }

    }
}
