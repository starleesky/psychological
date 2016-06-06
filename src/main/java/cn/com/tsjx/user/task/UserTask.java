package cn.com.tsjx.user.task;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.com.tsjx.user.service.UserService;

@Component
public class UserTask {

	@Resource
	UserService userService;
	
	/**
	 * 自动删除未及时激活的注册用户
	 */
	@Scheduled(cron="0 0/10 * * * ? ") //间隔10分钟执行
	public void autoDeteleUser() {
		userService.autoDeteleUser();
	}
}
