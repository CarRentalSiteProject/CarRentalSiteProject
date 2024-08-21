package com.tcg.action;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import ecpay.payment.integration.*;
import ecpay.payment.integration.domain.AioCheckOutALL;
import ecpay.payment.integration.exception.EcpayException;

//webservlet 宣告路由時，不用分號 ; 結尾
@WebServlet("/payment")
public class PaymentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AllInOne allInOne;

    @Override
    public void init() throws ServletException{
        super.init();
        allInOne = new AllInOne("");
    }

    @Override
    protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException {
        AioCheckOutALL obj = new AioCheckOutALL();
        //代入商家ID，但payment_conf.xml已有設定
        //obj.setMerchantID("");
        String tno = req.getParameter("tno");
        String total = req.getParameter("total");
        obj.setMerchantTradeNo(tno);
        obj.setMerchantTradeDate("2024/08/21 09:54:05");
        obj.setTotalAmount(total);
        obj.setTradeDesc("Test Payment LOL");
        obj.setItemName("JSP_online_course");
        //設定在金流介面，使用者點選返回商家頁面的連結
        obj.setReturnURL("http://localhost:8080/n-jsp/");
        //交易完成後，回傳交易結果的連結
        obj.setOrderResultURL("http://localhost:8080/car-rental/paymentResult");

        String form = "";
        try{
            form = allInOne.aioCheckOut(obj, null);
        }catch(EcpayException e){
            e.printStackTrace();
        }
        resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().write(form);
    }
}
