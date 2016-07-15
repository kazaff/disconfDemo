package xyz.uutech.www.opencartservice.repository;

import xyz.uutech.www.opencartservice.model.Order;

/**
 * Created by PC on 2016/5/11.
 */
public interface OrderMapper {
    Order getByOrdernum(String ordernum);
}
