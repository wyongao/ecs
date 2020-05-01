package com.ecs.dao.provider;

public class DayTeacherProvider {
	public String fuzzyQueryDayTeachers(String name) {
		StringBuffer sql=new StringBuffer("select * from day_teacher where ");
		if(name!=null && !name.equals("")) {
			sql.append("tname like"+"'%"+name+"%'");
		}
		System.out.println(sql.toString());
		return sql.toString();
	}
	
	public String selectWithParam(String college,String tnum) {
		StringBuffer sql=new StringBuffer("select * from day_teacher");
		if(college!=null && !college.equals("")) {
			sql.append(" where college ="+"'"+college+"'");
			if(tnum!=null && !tnum.equals("")) {
				sql.append(" and tnum ="+"'"+tnum+"'");
			}
			return sql.toString();
		}else {
			sql.append(" where tnum ="+"'"+tnum+"'");
			return sql.toString();
		}
	}
	
	public String countDayTeachers(String school,String college,String date) {
		StringBuffer sql=new StringBuffer("select count(*) from day_teacher where ");
		sql.append("date = '"+date+"' and");
		if(college !=null && !college.equals("")) {
			sql.append(" school ='"+school+"' and college ='"+college+"'");
			return sql.toString();
		}else {
			sql.append(" school ='"+school+"'");
			return sql.toString();
		}
		
	}
}
