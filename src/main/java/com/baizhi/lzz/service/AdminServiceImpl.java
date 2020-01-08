package com.baizhi.lzz.service;

import com.baizhi.lzz.dao.AdminDao;
import com.baizhi.lzz.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminDao adminDao;
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
    public boolean loginAdmin(Admin admin) {
        Admin admin1 = adminDao.selectOne(admin);
        if(admin1!=null){
            if(admin1.getPassword().equals(admin.getPassword())){
                ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                requestAttributes.getRequest().getSession().setAttribute("admin",admin1);
                return true;
            }
        }
        return false;
    }
}