package com.ecs.dao.provider;

public class TeacherProvider {
	public String selectWithParam(String college,String tnum) {
		StringBuffer sql=new StringBuffer("select * from teacher");
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
}
