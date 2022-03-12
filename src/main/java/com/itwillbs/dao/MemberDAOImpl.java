package com.itwillbs.dao;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	//멤버변수
	private DataSource dataSource;
	SimpleJdbcTemplate template;
	
	@Inject
	public void setDataSource(DataSource dataSource) {
//		this.dataSource = dataSource;
		template = new SimpleJdbcTemplate(dataSource);
	}
	
	String insertsql = "insert into member(id,pass,name,date) values(?,?,?,?)";
	
	@Override
	public void insertMember(MemberDTO memberDTO) {
		// 처리작업
		System.out.println("MemberDAOImpl insertmember()");
		//DB작업
		template.update(insertsql, memberDTO.getId(), memberDTO.getPass(), memberDTO.getName(), memberDTO.getDate());
	}
}
