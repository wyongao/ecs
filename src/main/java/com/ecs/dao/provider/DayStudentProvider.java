package com.ecs.dao.provider;

public class DayStudentProvider {
	public String selectWithParam(String school,String college, String major, String classes, String snum,String date) {
		StringBuffer sql = new StringBuffer("select * from day_student where ");
		// 学院\专业\班级\学号\
			sql.append("school='"+school+"'");
			if (college != null && !college.equals("")) {
				sql.append(" and college = '" + college + "'");
		
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
					sql.append(" and major = '" + major + "'");
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
						sql.append(" and classes = '" + classes + "'");
					
					if (snum != null && !snum.equals("")) {
						sql.append("and snum = '" + snum + "'");
					}
					if (date !=null && !date.equals("")) {
						sql.append("and date = '"+ date +"'");
					}
				 }else {
	
						if (snum != null && !snum.equals("")) {
							sql.append(" and snum ='" + snum + "'");
							
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

	public String fuzzyQueryDaystudents(String school,String college,String name) {
		StringBuffer sql = new StringBuffer("select * from day_student where ");
		sql.append("school='"+school+"'");
		if (college!=null&&!college.equals("")) {
			sql.append(" and college ='"+college+"'");
		}
		if(name!=null && !name.equals("")) {
			sql.append(" and sname like"+"'%"+name+"%'");
		}
		System.out.println(sql.toString());
		return sql.toString();
	}
	
	public String countDayStudents(String school,String college,String date) {
		StringBuffer sql=new StringBuffer("select count(*) from day_student where ");
		sql.append("date = '"+date+"' and");
		if(college !=null && !college.equals("")) {
			sql.append(" school ='"+school+"' and college ='"+college+"'");
			return sql.toString();
		}else {
			sql.append(" school ='"+school+"'");
			return sql.toString();
		}
		
	}
	
	public String screenDayStudents(String temp) {
		StringBuffer sql= new StringBuffer(" SELECT * FROM day_student WHERE ");
		sql.append("temp >= '"+temp+"'");
		return sql.toString();
	}
	
	
	//筛选未打卡的学生
	public 	String screenNoSignStudents(String school,String college,String major,String classes,String date)
	{
		StringBuffer sql = new StringBuffer("SELECT * FROM student WHERE snum NOT IN (SELECT snum FROM day_student WHERE ");
		sql.append("date='"+date+"' ) ");
		if (school!=null&&!school.equals("")) {
			sql.append(" and school ='"+school+"'");
		}
		if (college!=null&&!college.equals("")) {
			sql.append(" and college ='"+college+"'");
		}
		if (major!=null&&!major.equals("")) {
			sql.append(" and major ='"+major+"'");
		}
		if (classes!=null&&!classes.equals("")) {
			sql.append(" and classes ='"+classes+"'");
		}
		System.out.println(sql.toString());
		return sql.toString();
	}
	
}
