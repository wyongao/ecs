package com.ecs.dao.provider;

public class DayStudentProvider {
	public String selectWithParam(String college, String major, Integer classes, String snum) {
		StringBuffer sql = new StringBuffer("select * from day_student where ");
		// 学院\专业\班级\学号\
	
			if (college != null && !college.equals("")) {
				sql.append("college = '" + college + "'");
		
			if (major != null && !major.equals("")) {
				sql.append("and major = '" + major + "'");
			}
			if (classes != null && classes!=0) {
				sql.append("and classes = '" + classes + "'");
			}
			if (snum != null && !snum.equals("")) {
				sql.append("and snum = '" + snum + "'");
			}

		} else {
				if (major != null && !major.equals("")) {
					sql.append("and major = '" + major + "'");
					if (classes != null && classes!=0) {
						sql.append("and classes = '" + classes + "'");
					}
					if (snum != null && !snum.equals("")) {
						sql.append("and snum = '" + snum + "'");
					}
				
			} else {

					if (classes != null && classes!=0) {
						sql.append("and classes = '" + classes + "'");
					
					if (snum != null && !snum.equals("")) {
						sql.append("and snum = '" + snum + "'");
					}
				 }else {
	
						if (snum != null && !snum.equals("")) {
							sql.append("and snum ='" + snum + "'");
							return sql.toString();
						}
					} 
			}

		}	
	
		System.out.println(sql.toString());
		return sql.toString();

	}

	public String fuzzyQueryDaystudents(String name) {
		StringBuffer sql = new StringBuffer("select * from day_student where ");
		if (name != null && !name.equals("")) {
			sql.append("sname like" + "'%" + name + "%'");
		}
		System.out.println(sql.toString());
		return sql.toString();
	}
}
