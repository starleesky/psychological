package cn.com.tsjx.comment.dao.impl;

import org.springframework.stereotype.Repository;

import cn.com.tsjx.common.dao.BaseDaoImpl;
import cn.com.tsjx.comment.dao.CommentDao;
import cn.com.tsjx.comment.entity.Comment;

@Repository("commentDao")
public class CommentDaoImpl extends BaseDaoImpl<Comment, Long> implements CommentDao {

}
