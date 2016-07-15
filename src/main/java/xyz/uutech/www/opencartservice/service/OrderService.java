package xyz.uutech.www.opencartservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.uutech.www.opencartservice.model.Order;
import xyz.uutech.www.opencartservice.repository.OrderMapper;

/**
 * 订单服务类
 *
 * @author kazaff
 * @date 2016/5/11.
 */
@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 检查指定订单是否已经存在
     *
     * @param ordernum 指定订单
     * @return true：存在 false：不存在
     *
     * @author kazaff
     * @date 2016/5/11
     */
    public boolean checkOrderExist(String ordernum) {
        Order order = orderMapper.getByOrdernum(ordernum);
        if(order != null){
            return true;
        }
        return false;
    }
}
