package xyz.uutech.www.opencartservice.web.controller;

import org.springframework.web.bind.annotation.PathVariable;
import xyz.uutech.www.opencartservice.config.DBConfig;
import xyz.uutech.www.opencartservice.service.OrderService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.uutech.www.opencartservice.service.TestService;
import xyz.uutech.www.opencartservice.web.dto.error.ErrorCodeDTO;

import java.util.List;
import java.util.Map;

/**
 * 中央控制器类
 *
 * @author kazaff
 * @date 2016/5/11
 */
@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private TestService testService;

    @Autowired
    private DBConfig dbConfig;

    /**
     * 检查订单是否存在
     *
     * @param ordernum  订单号
     * @return true/false
     *
     * @author kazaff
     * @date 2016/5/11
     */
    @RequestMapping("/{ordernum:\\w+}/existence")
    public Map checkOrder(@PathVariable String ordernum) {
        Assert.hasLength(ordernum);
        Map<String, Integer> result = Maps.newHashMap();
        result.put("status", orderService.checkOrderExist(ordernum) ? 1 : 0);
        return result;
    }

    /**
     * 异常演示
     *
     * @return ErrorCodeDTO
     *
     * @author kazaff
     * @date 2016/5/11
     */
    @RequestMapping("/404")
    public ErrorCodeDTO errorDemo(){
        return new ErrorCodeDTO(404);
    }

    @RequestMapping("/test")
    public Map test(){
        List<Integer> ids = testService.getList();
        Map<String, Object> result = Maps.newHashMap();
        result.put("ids", ids);
        return result;
    }

    @RequestMapping("/dbconf")
    public Map dbconf(){
        Map<String, Object> result = Maps.newHashMap();
        result.put("url", dbConfig.getUrl());
        return result;
    }
}
