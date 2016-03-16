package cn.com.tsjx.notice.dao.impl;

import org.springframework.stereotype.Repository;

import cn.com.tsjx.common.dao.BaseDaoImpl;
import cn.com.tsjx.notice.dao.NoticeDao;
import cn.com.tsjx.notice.entity.Notice;

@Repository("noticeDao")
public class NoticeDaoImpl extends BaseDaoImpl<Notice, Long> implements NoticeDao {

}
