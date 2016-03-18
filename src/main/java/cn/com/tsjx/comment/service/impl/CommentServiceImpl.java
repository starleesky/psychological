package cn.com.tsjx.comment.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.tsjx.common.dao.BaseDao;
import cn.com.tsjx.common.service.BaseServiceImpl;
import cn.com.tsjx.comment.dao.CommentDao;
import cn.com.tsjx.comment.entity.Comment;
import cn.com.tsjx.comment.service.CommentService;

@Service("commentService")
public class CommentServiceImpl extends BaseServiceImpl<Comment, Long> implements CommentService {

	@Resource
	private CommentDao commentDao;

	@Override
	protected BaseDao<Comment, Long> getBaseDao() {
		return this.commentDao;
	}
}
