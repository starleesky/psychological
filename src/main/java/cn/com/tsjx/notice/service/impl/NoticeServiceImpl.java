package cn.com.tsjx.notice.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.tsjx.common.dao.BaseDao;
import cn.com.tsjx.common.service.BaseServiceImpl;
import cn.com.tsjx.notice.dao.NoticeDao;
import cn.com.tsjx.notice.entity.Notice;
import cn.com.tsjx.notice.service.NoticeService;

@Service("noticeService")
public class NoticeServiceImpl extends BaseServiceImpl<Notice, Long> implements NoticeService {

	@Resource
	private NoticeDao noticeDao;

	@Override
	protected BaseDao<Notice, Long> getBaseDao() {
		return this.noticeDao;
	}
}
