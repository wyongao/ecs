package com.ecs.dao.provider;

public class StudentProvider {
	
	public String selectWithParam(String college, String major, String classes) {
		StringBuffer sql = new StringBuffer("select * from student where ");
		// 学院\专业\班级\学号\
		
			if (college != null && !college.equals("")) {
				sql.append("college = '" + college + "'");
			
			if (major != null && !major.equals("")) {
				sql.append(" and major = '" + major + "'");
			}
			if (classes != null && classes!="") {
				sql.append(" and classes = '" + classes + "'");
			}

		} 
			else {
			
				if (major != null && !major.equals("")) {
					sql.append("major = '" + major + "'");
					if (classes != null && classes!="") {
						sql.append(" and classes = '" + classes + "'");
					}
				}
			 else {

					if (classes != null && classes!="") {
						sql.append("classes = '" + classes + "'");
					}
				} 
			
			}
			System.out.println(sql.toString());
			return sql.toString();
	}
		
	

}
