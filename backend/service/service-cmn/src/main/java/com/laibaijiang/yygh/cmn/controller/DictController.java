package com.laibaijiang.yygh.cmn.controller;

import com.laibaijiang.yygh.cmn.service.DictService;
import com.laibaijiang.yygh.commong.result.Result;
import com.lbj.yygh.model.cmn.Dict;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(tags = "数据字典管理")
@RestController
@RequestMapping("/admin/cmn/dict")
//@CrossOrigin
public class DictController {
    @Autowired
    private DictService dictService;

    @ApiOperation(value = "根据id查询子数据")
    @GetMapping("/findChildData/{id}")
    public Result findChildData(@PathVariable Long id) {
        List<Dict> dictList = dictService.findChildData(id);
        return Result.ok(dictList);
    }

    @ApiOperation(value = "字典导出")
    @GetMapping("/exportData")
    public void exportData(HttpServletResponse response) {
        dictService.exportData(response);
    }

    @ApiOperation(value = "向字典中导入数据")
    @PostMapping("/importData")
    public Result importData(MultipartFile file){
        dictService.importData(file);
        return Result.ok();
    }

    @ApiOperation(value = "获取数据字典中dictCode和value查询名称")
    @GetMapping("/getName/{dictCode}/{value}")
    public String getName(@PathVariable String dictCode,
                          @PathVariable String value) {
        return dictService.getDictName(dictCode, value);
    }

    @ApiOperation(value = "获取数据字典中value查询名称")
    @GetMapping("/getName/{value}")
    public String getName(@PathVariable String value) {
        return dictService.getDictName("", value);
    }

    @ApiOperation(value = "根据dictCode获取下级节点")
    @GetMapping(value = "/findByDictCode/{dictCode}")
    public Result<List<Dict>> findByDictCode(
            @PathVariable String dictCode) {
        List<Dict> list = dictService.findByDictCode(dictCode);
        return Result.ok(list);
    }
}
