package com.javakc.pms.dispord.service;

import com.javakc.commonutils.jpa.base.service.BaseService;
import com.javakc.commonutils.jpa.dynamic.SimpleSpecificationBuilder;
import com.javakc.pms.dispord.dao.DispOrdDao;
import com.javakc.pms.dispord.entity.DispOrd;
import com.javakc.pms.vo.DispOrdQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class DispOrdService extends BaseService<DispOrdDao,DispOrd> {

    @Autowired
    private DispOrdDao dispOrdDao;

    /**
     * 查询所有调度指令库
     * @return
     */
    public List<DispOrd> findAll() {
        return dispOrdDao.findAll();
    }

    /**
     **带条件分页查询
     * @param dispOrdQuery
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page<DispOrd> findPageDispord(DispOrdQuery dispOrdQuery, int pageNo, int pageSize) {
        // 实例化查询条件对象
        SimpleSpecificationBuilder<DispOrd> simpleSpecificationBuilder = new SimpleSpecificationBuilder<>();
        if(!StringUtils.isEmpty(dispOrdQuery.getOrderName())){
            simpleSpecificationBuilder.and("orderName",":",dispOrdQuery.getOrderName());
        }

        Page page = dispOrdDao.findAll(simpleSpecificationBuilder.getSpecification(), PageRequest.of(pageNo - 1, pageSize));

        return page;
    }
}
