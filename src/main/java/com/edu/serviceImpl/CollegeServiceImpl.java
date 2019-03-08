package com.edu.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.edu.mapper.CollegeMapper;
import com.edu.pojo.College;
import com.edu.pojo.CollegeExample;
import com.edu.service.CollegeService;

@Service
public class CollegeServiceImpl implements CollegeService {

    @Autowired
    private CollegeMapper collegeMapper;

    public List<College> finAll() throws Exception {
        CollegeExample collegeExample = new CollegeExample();
        CollegeExample.Criteria criteria = collegeExample.createCriteria();
        criteria.andCollegeidIsNotNull();
        return collegeMapper.selectByExample(collegeExample);
    }
}
