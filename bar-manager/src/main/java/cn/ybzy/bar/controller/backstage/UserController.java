package cn.ybzy.bar.controller.backstage;

import cn.ybzy.bar.common.BaseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

/**
 * @author: CJ
 * @description: 用户管理模块Controller
 * @time: 2020/1/15 15:33
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	
	@PostMapping("login")
	public HashMap<String, Object> login(String username, String password) {
		
		return BaseUtil.back(1, "登录成功!");
	}
	
	@PostMapping("logout")
	public HashMap<String, Object> logout() {
		
		return BaseUtil.back(1, "退出成功!");
	}
}
