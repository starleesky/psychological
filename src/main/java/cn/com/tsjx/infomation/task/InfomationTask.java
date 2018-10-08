package cn.com.tsjx.infomation.task;

import cn.com.tsjx.infomation.service.InfomationService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class InfomationTask {

	@Resource
	InfomationService infomationService;
	
	/**
	 * 自动下架定时任务
	 */
//	@Scheduled(cron="0 0 0/10 * * ? ") //间隔1小时执行
	public void autoDown() {
		infomationService.autoDown();
	}
}
