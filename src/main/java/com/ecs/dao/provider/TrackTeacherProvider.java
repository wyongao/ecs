package com.ecs.dao.provider;

public class TrackTeacherProvider {

	
	public String searchTeacherDynamic(String school, String college, String tnum, String name) {
		StringBuffer sql = new StringBuffer("select * from track_teacher where ");
		sql.append("DATE_SUB(CURDATE(), INTERVAL 14 DAY) <= date and school='"+school+"'");
		if (college!=null&&!college.equals("")) {
			sql.append(" and college ='"+college+"'");
		}
		if(tnum!=null && !tnum.equals("")) {
			sql.append(" and tnum ='"+tnum+"'");
		}
		if(name!=null && !name.equals("")) {
			sql.append(" and tname ='"+name+"'");
		}
		sql.append(" order by date");
//		System.out.println(sql.toString());
		return sql.toString();
	}
}
