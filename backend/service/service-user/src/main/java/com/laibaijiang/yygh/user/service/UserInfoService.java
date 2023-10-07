package com.laibaijiang.yygh.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lbj.yygh.model.user.UserInfo;
import com.lbj.yygh.vo.user.LoginVo;

import java.util.Map;

public interface UserInfoService extends IService<UserInfo> {
    Map<String, Object> loginUser(LoginVo loginVo);
}
