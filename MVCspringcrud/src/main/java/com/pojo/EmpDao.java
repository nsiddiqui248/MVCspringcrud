package com.pojo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class EmpDao {
JdbcTemplate template;
	
	public void setTeamplate(JdbcTemplate template) {
		this.template=template;
	}
	
	public int save(Emp e) {
		String sql="insert into Emp99(name,salary,designation) values('"+e.getName()+"',"+e.getSalary()+",'"+e.getDesignation()+"')";
		return template.update(sql);
	}
	
	public int update(Emp e) {
		String sql="update Emp99 set name='"+e.getName()+"', salary="+e.getSalary()+", designation='"+e.getDesignation()+"'";
		return template.update(sql);
	}
	
	public int delete(int id) {
		String sql="delete from Emp99 where id="+id;
		return template.update(sql);
	}
	
	public Emp getEmpById(int id) {
		String sql="select * from Emp99 where id="+id;
		 return template.query(sql, new ResultSetExtractor<Emp>(){  
			    
		     public Emp extractData(ResultSet rs) throws SQLException, DataAccessException {
		    	 Emp e=new Emp();  
		        while(rs.next()){  
		        	e.setId(rs.getInt(1));
		            e.setName(rs.getString(2));
		            e.setSalary(rs.getFloat(3));
		            e.setDesignation(rs.getString(4));
			        }  
		        return e;  
		        }  
		     });
	}
	
	public List<Emp> getEmployees(){    
	    return template.query("select * from Emp99",new RowMapper<Emp>(){  
	        public Emp mapRow(ResultSet rs, int row) throws SQLException {    
	            Emp e=new Emp();
	            e.setId(rs.getInt(1));
	            e.setName(rs.getString(2));
	            e.setSalary(rs.getFloat(3));
	            e.setDesignation(rs.getString(4));
	            return e;
	        }    
	    });
	}
	
	public int truncate() {
		String sql="truncate table Emp99";
		return template.update(sql);
	}
}
