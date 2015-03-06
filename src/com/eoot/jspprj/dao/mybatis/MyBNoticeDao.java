package com.eoot.jspprj.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.eoot.jspprj.dao.NoticeDao;
import com.eoot.jspprj.model.Notice;

public class MyBNoticeDao implements NoticeDao{

	@Override
	public Notice getNotice(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Notice> getNotices(int page, String query, String field) {
		SqlSession sqlSession = MyBatisMain.getSqlSessionFactory().openSession(true);
		NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class);
		return noticeDao.getNotices(page, query, field);
	}

	@Override
	public List<Notice> getNotices(int page, String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Notice> getNotices(int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Notice prevNotice(String currentCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Notice nextNotice(String currentCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Notice notice) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Notice notice) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String code) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSize(String query, String field) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSize(String query) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String lastCode() {
		// TODO Auto-generated method stub
		return null;
	}

}
