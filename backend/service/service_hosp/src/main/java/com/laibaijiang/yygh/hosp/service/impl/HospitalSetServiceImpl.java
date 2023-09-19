package com.laibaijiang.yygh.hosp.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laibaijiang.yygh.common.utils.MD5;
import com.laibaijiang.yygh.hosp.mapper.HospitalSetMapper;
import com.laibaijiang.yygh.hosp.service.HospitalSetService;
import com.lbj.yygh.model.hosp.HospitalSet;
import com.lbj.yygh.vo.hosp.HospitalSetQueryVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class HospitalSetServiceImpl extends ServiceImpl<HospitalSetMapper, HospitalSet> implements HospitalSetService {


    @Override
    public List<HospitalSet> finalAll() {
        // 创建条件构造器，并清除查询条件
        QueryWrapper<HospitalSet> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", 0);

        // 使用条件构造器查询数据
        return list(queryWrapper);
    }

    @Override
    public boolean removeHospSet(Long id) {
        return baseMapper.deleteById(id) == 1;
    }

    @Override
    public Page<HospitalSet> findHospSetByPage(Long current, Long limit, HospitalSetQueryVo hospitalSetQueryVo) {
        Page<HospitalSet> page = new Page<>(current, limit);
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
        String hoscode = hospitalSetQueryVo.getHoscode();
        if (!StrUtil.isEmpty(hoscode)) {
            wrapper.like("hoscode", hoscode);
        }
        String hosname = hospitalSetQueryVo.getHosname();
        if (!StrUtil.isEmpty(hosname)) {
            wrapper.like("hosname", hosname);
        }
        return baseMapper.selectPage(page, wrapper);
    }

    @Override
    public boolean saveHospitalSet(HospitalSet hospitalSet) {
        // 更改状态： 1可用 0 不可用
        hospitalSet.setStatus(1);
        Random random = new Random();
        hospitalSet.setSignKey(MD5.encrypt(System.currentTimeMillis()+""+random.nextInt(1000)));
        return baseMapper.insert(hospitalSet) == 1;
    }

    @Override
    public HospitalSet getHospSet(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public boolean updateHospitalSet(HospitalSet hospitalSet) {
        return baseMapper.updateById(hospitalSet) == 1;
    }

    @Override
    public boolean batchRemoveHospitalSet(List<Long> idList) {
        return baseMapper.deleteBatchIds(idList) >= 1;
    }

    @Override
    public boolean lockHospitalSet(Long id, Integer status) {
        HospitalSet hospitalSet = baseMapper.selectById(id);
        //设置状态
        hospitalSet.setStatus(status);
        return baseMapper.updateById(hospitalSet) == 1;
    }

    @Override
    public void sendKey(Long id) {
        HospitalSet hospitalSet = baseMapper.selectById(id);
        String signKey = hospitalSet.getSignKey();
        String hoscode = hospitalSet.getHoscode();
    }

    @Override
    public String getSignKey(String hoscode) {
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
        wrapper.eq("hoscode", hoscode);
        return baseMapper.selectOne(wrapper).getSignKey();
    }


}
