package com.tds.project.service.impl;



import com.tds.project.domain.SysLogininfor;
import com.tds.project.mapper.ISysLogininforMapper;
import com.tds.project.service.ISysLogininforService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ISysLogininforServiceImpl implements ISysLogininforService {
    @Autowired
    private ISysLogininforMapper iSysLogininforMapper;

    @Override
    public void insertLogininfor(SysLogininfor loginimfor) {
        iSysLogininforMapper.insertLogininfor(loginimfor);
    }
}
