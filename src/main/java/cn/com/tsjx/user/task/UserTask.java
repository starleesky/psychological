//package cn.com.tsjx.user.task;
//
//import cn.com.tsjx.user.service.UserService;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//
//@Component
//public class UserTask {
//
//	@Resource
//	UserService userService;
//
//	/**
//	 * 自动删除未及时激活的注册用户
//	 */
////	@Scheduled(cron="0 0/10 * * * ? ") //间隔10分钟执行
//	public void autoDeteleUser() {
//		userService.autoDeteleUser();
//	}
//}
