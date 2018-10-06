package cn.com.tsjx.notice.dao.impl;

import cn.com.tsjx.common.enums.Deleted;
import cn.com.tsjx.common.web.model.Params;
import org.springframework.stereotype.Repository;

import cn.com.tsjx.common.dao.BaseDaoImpl;
import cn.com.tsjx.notice.dao.NoticeDao;
import cn.com.tsjx.notice.entity.Notice;

import java.util.List;

@Repository("noticeDao")
public class NoticeDaoImpl extends BaseDaoImpl<Notice, Long> implements NoticeDao {

	@Override public List<Notice> getUserAndAdminNotice(Notice notice) {
		Params params = Params.create();
		params.add("deleted", Deleted.NO.value);
		params.add("userId", notice.getUserId());
		return this.selectList(this.getMethodName(), params);
	}
}
