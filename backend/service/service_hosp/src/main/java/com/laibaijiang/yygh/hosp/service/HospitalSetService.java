package com.laibaijiang.yygh.hosp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lbj.yygh.model.hosp.HospitalSet;
import com.lbj.yygh.vo.hosp.HospitalSetQueryVo;

import java.util.List;

public interface HospitalSetService extends IService<HospitalSet> {
    List<HospitalSet> finalAll();

    boolean removeHospSet(Long id);

    Page<HospitalSet> findHospSetByPage(Long current, Long limit, HospitalSetQueryVo hospitalSetQueryVo);

    boolean saveHospitalSet(HospitalSet hospitalSet);

    HospitalSet getHospSet(Long id);

    boolean updateHospitalSet(HospitalSet hospitalSet);

    boolean batchRemoveHospitalSet(List<Long> idList);

    boolean lockHospitalSet(Long id, Integer status);

    void sendKey(Long id);
}
