package com.laibaijiang.yygh.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laibaijiang.yygh.commong.result.Result;
import com.laibaijiang.yygh.service.HospitalSetService;
import com.lbj.yygh.model.hosp.HospitalSet;
import com.lbj.yygh.vo.hosp.HospitalSetQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/hosp/hospitalSet")
@Api(tags = "医院设置管理")
public class HospitalSetController {

    @Autowired
    private HospitalSetService hospitalSetService;

    @GetMapping("/findAll")
    @ApiOperation(value = "获取所有医院设置")
    public Result findAll() {
        List<HospitalSet> hospitalSetList = hospitalSetService.finalAll();
        return Result.ok(hospitalSetList);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据id逻辑删除医院设置")
    public Result removeHospSet(@PathVariable Long id) {
        boolean remove = hospitalSetService.removeHospSet(id);
        return remove ? Result.ok() : Result.fail();
    }

    //3 条件查询带分页
    @GetMapping("/findHospSetByPage/{current}/{limit}")
    @ApiOperation(value = "分页查询医院设置")
    public Result findHospSetByPage(@PathVariable Long current, @PathVariable Long limit, @RequestBody
            (required = false) HospitalSetQueryVo hospitalSetQueryVo) {
        Page<HospitalSet> hospitalSetPage = hospitalSetService.findHospSetByPage(current, limit,  hospitalSetQueryVo);
        return Result.ok(hospitalSetPage);
    }

    //4 添加医院设置
    @PostMapping("/saveHospitalSet")
    @ApiOperation(value = "添加医院设置")
    public Result saveHospitalSet(@RequestBody HospitalSet hospitalSet) {
        boolean save = hospitalSetService.saveHospitalSet(hospitalSet);
        return save ? Result.ok() : Result.fail();
    }

    //5 根据id获取医院设置
    @GetMapping("/getHospSet/{id}")
    @ApiOperation(value = "根据id获取医院设置")
    public Result getHospSet(@PathVariable Long id) {
        HospitalSet hospitalSet = hospitalSetService.getHospSet(id);
        return Result.ok(hospitalSet);
    }

    //6 修改医院设置
    @PostMapping("/updateHospitalSet")
    @ApiOperation(value = "修改医院设置")
    public Result updateHospitalSet(@RequestBody HospitalSet hospitalSet) {
        boolean update = hospitalSetService.updateHospitalSet(hospitalSet);
        return update ? Result.ok() : Result.fail();
    }

    //7 批量删除医院设置
    @DeleteMapping("/batchRemove")
    @ApiOperation(value = "批量删除医院设置")
    public Result batchRemoveHospitalSet(@RequestBody List<Long> idList) {
        boolean batchRemove = hospitalSetService.batchRemoveHospitalSet(idList);
        return batchRemove? Result.ok() : Result.fail();
    }

}
