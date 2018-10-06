package cn.com.tsjx.notice.dao;

import cn.com.tsjx.common.dao.BaseDao;
import cn.com.tsjx.notice.entity.Notice;

import java.util.List;

public interface NoticeDao extends BaseDao<Notice, Long> {

	public List<Notice> getUserAndAdminNotice(Notice notice);
}
