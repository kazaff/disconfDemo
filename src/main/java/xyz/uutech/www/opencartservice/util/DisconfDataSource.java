package xyz.uutech.www.opencartservice.util;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by PC on 2016/7/15.
 */
public class DisconfDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey(){
        return "TARGET";
    }
}
