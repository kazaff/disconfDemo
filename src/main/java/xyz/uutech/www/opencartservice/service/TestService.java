package xyz.uutech.www.opencartservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.uutech.www.opencartservice.repository.TestMapper;

import java.util.List;

/**
 * Created by PC on 2016/7/15.
 */
@Service
public class TestService {

    @Autowired
    protected TestMapper testMapper;

    public List<Integer> getList(){
        return testMapper.getList();
    }
}
