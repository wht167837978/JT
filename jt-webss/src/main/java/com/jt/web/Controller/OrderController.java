package com.jt.web.Controller;

import com.jt.web.ThreadLocal.UserThreadLocal;
import com.jt.web.pojo.TbCart;
import com.jt.web.pojo.TbOrder;
import com.jt.web.service.CartService;
import com.jt.web.service.OrderService;
import com.jt.web.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;
    @RequestMapping("/create")
    public String create(Model model){
        long id = UserThreadLocal.get().getId();
        List<TbCart> cartByUserId = cartService.findCartByUserId(id);
        model.addAttribute("carts",cartByUserId);
        return "order-cart";
    }
    @RequestMapping("/submit")
    @ResponseBody
    public SysResult submit(TbOrder order){
        try {
            long id = UserThreadLocal.get().getId();
            order.setUserId(id);
            String oderitemid=orderService.saveorder(order);
            if(!StringUtils.isEmpty(oderitemid)){
                return SysResult.oK(oderitemid);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
return SysResult.build(201,"提交订单失败");
    }
    @RequestMapping("success")
    public String fiandOrderId(String id,Model model){
        System.out.println(id);
        TbOrder order = orderService.fiandOrderByid(id);
        model.addAttribute("order",order);
        return "success";
    }
    @RequestMapping("/myOrder")
    public String myOrder(){

        return "my-ordersSysResult.java";
    }

}
