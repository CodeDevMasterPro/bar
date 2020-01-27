package cn.ybzy.bar.controller.applets;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: CJ
 * @description: 首页controller
 * @time: 2020/1/14 11:40
 */
@Controller
@RequestMapping("/index")
public class IndexController {
	
	@ResponseBody
	@GetMapping("/test")
	public String test() {
		return "Test..........";
	}
}
