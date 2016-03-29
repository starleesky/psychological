package cn.com.tsjx.notice.service;

import cn.com.tsjx.common.service.BaseService;
import cn.com.tsjx.notice.entity.Notice;

import java.util.List;

/**
 * 系统公告服务接口。
 */
public interface NoticeService extends BaseService<Notice, Long> {

	List<Notice> getUserAndAdminNotice(Notice notice);

}
