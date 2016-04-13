package cn.com.tsjx.infomation.task;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.com.tsjx.infomation.service.InfomationService;

@Component
public class InfomationTask {

	@Resource
	InfomationService infomationService;
	
	/**
	 * 自动下架定时任务
	 */
	@Scheduled(cron="0 0 0/10 * * ? ") //间隔1小时执行
	public void autoDown() {
		infomationService.autoDown();
	}
}
