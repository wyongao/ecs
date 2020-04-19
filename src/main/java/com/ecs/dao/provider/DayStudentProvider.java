package com.ecs.dao.provider;

public class DayStudentProvider {
	public String selectWithParam(String college, String major, String classes, String snum,String date) {
		StringBuffer sql = new StringBuffer("select * from day_student where ");
		// 学院\专业\班级\学号\
	
			if (college != null && !college.equals("")) {
				sql.append("college = '" + college + "'");
		
			if (major != null && !major.equals("")) {
				sql.append("and major = '" + major + "'");
			}
			if (classes != null && !classes.equals("")) {
				sql.append("and classes = '" + classes + "'");
			}
			if (snum != null && !snum.equals("")) {
				sql.append("and snum = '" + snum + "'");
			}
			if (date !=null && !date.equals("")) {
				sql.append("and date = '"+ date +"'");
			}

		} else {
				if (major != null && !major.equals("")) {
					sql.append("major = '" + major + "'");
					if (classes != null && classes!="") {
						sql.append("and classes = '" + classes + "'");
					}
					if (snum != null && !snum.equals("")) {
						sql.append("and snum = '" + snum + "'");
					}
					if (date !=null && !date.equals("")) {
						sql.append("and date = '"+ date +"'");
					}
				
			} else {

					if (classes != null && classes!="") {
						sql.append(" classes = '" + classes + "'");
					
					if (snum != null && !snum.equals("")) {
						sql.append("and snum = '" + snum + "'");
					}
					if (date !=null && !date.equals("")) {
						sql.append("and date = '"+ date +"'");
					}
				 }else {
	
						if (snum != null && !snum.equals("")) {
							sql.append(" snum ='" + snum + "'");
							
						}
						if (date !=null && !date.equals("")) {
							sql.append("and date = '"+ date +"'");
						}
						return sql.toString();
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
