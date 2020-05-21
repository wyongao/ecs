package com.ecs.dao.provider;

public class TrackStudentProvider {

	public String searchStudentDynamic(String school, String college, String snum, String name) {
		StringBuffer sql = new StringBuffer("select * from track_student where ");
		sql.append("DATE_SUB(CURDATE(), INTERVAL 14 DAY) <= date and school='"+school+"'");
		if (college!=null&&!college.equals("")) {
			sql.append(" and college ='"+college+"'");
		}
		if(snum!=null && !snum.equals("")) {
			sql.append(" and snum ='"+snum+"'");
		}
		if(name!=null && !name.equals("")) {
			sql.append(" and sname ='"+name+"'");
		}
		sql.append(" order by date");
//		System.out.println(sql.toString());
		return sql.toString();
	}
}
