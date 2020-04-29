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
	
	
	/**
	 * 根据姓名模糊查询
	 * @param name
	 * @return
	 */
	
	public String fuzzyQueryTeacher(String name) {
		StringBuffer sql = new StringBuffer("select * from teacher where ");
		if (name != null && !name.equals("")) {
			sql.append("tname like" + "'%" + name + "%'");
			
		}
		System.out.println(sql.toString());
		return sql.toString();
	}
	
	public String countTeachers(String school,String college) {
		StringBuffer sql=new StringBuffer("select count(*) from teacher where ");
		if(college !=null && !college.equals("")) {
			sql.append("school ='"+school+"' and college ='"+college+"'");
			return sql.toString();
		}else {
			sql.append("school ='"+school+"'");
			return sql.toString();
		}
		
	}


}
