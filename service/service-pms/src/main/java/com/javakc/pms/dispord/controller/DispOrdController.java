package com.javakc.pms.dispord.controller;


import com.javakc.commonutils.api.APICODE;
import com.javakc.pms.dispord.entity.DispOrd;
import com.javakc.pms.dispord.service.DispOrdService;
import com.javakc.pms.vo.DispOrdQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin // ## 解决跨域
@Api(tags = "调度指令库 - 控制层")
@RestController
@RequestMapping("/pms/dispord")
public class DispOrdController {

    @Autowired
    private DispOrdService dispOrdService;

    @ApiOperation(value = "查询所有指令库")
    @GetMapping
    public APICODE findAll() {
        List<DispOrd> dispOrdList = dispOrdService.findAll();
        // int i=1/0;
        return APICODE.OK().data("items", dispOrdList);// 传进去的key（“items”）和value（“dispOrdList”）
    }

    /**
     * 带条件的分页查询
     */

    @ApiOperation(value = "带条件的分页查询")
    @GetMapping("{pageNo}/{pageSize}")
    public APICODE findPageDispOrd(DispOrdQuery dispOrdQuery,
                                   @PathVariable(name = "pageNo") int pageNo,
                                   @PathVariable(name = "pageSize") int pageSize) {

        Page<DispOrd> page = dispOrdService.findPageDispord(dispOrdQuery, pageNo, pageSize);
        long totalElements = page.getTotalElements();
        List<DispOrd> dispOrdList = page.getContent();
        return APICODE.OK().data("total", totalElements).data("items", dispOrdList);
    }

    /**
     * 新增方法
     * @return
     */
    @ApiOperation(value = "新增调度指令库")
    @PostMapping("saveDispOrd")
    public APICODE saveDispOrd(@RequestBody DispOrd dispOrd) {
        dispOrdService.saveOrUpdate(dispOrd);
        return APICODE.OK();
    }

    /**
     * 根据ID删除
     * @param dispOrdId
     * @return
     */
    @ApiOperation(value = "根据ID删除调度指令库")
    @DeleteMapping("{dispOrdId}")
    public APICODE deleteDispOrdById(@PathVariable(name = "dispOrdId") String dispOrdId){
        dispOrdService.removeById(dispOrdId);
        return APICODE.OK();
    }

    /**
     * 根据ID修改
     * @param dispOrd
     * @return
     */
    @ApiOperation(value = "修改调度指令库")
    @PutMapping("updateDispOrd")
    public APICODE updateDispOrd(@RequestBody DispOrd dispOrd){
        dispOrdService.saveOrUpdate(dispOrd);
        return APICODE.OK();
    }

    /**
     * 根据ID查询
     * @return
     */
    @ApiOperation(value = "根据ID获取调度指令库")
    @GetMapping("{dispOrdId}")
    public APICODE getDispOrdById(@PathVariable(name = "dispOrdId") String dispOrdId){
        DispOrd dispOrd = dispOrdService.getById(dispOrdId);
        return APICODE.OK().data("dispOrd",dispOrd);
    }

}
