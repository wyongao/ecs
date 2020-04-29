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

			if (classes != null && !classes.equals("")) {

				sql.append(" and classes = '" + classes + "'");
			}

		} 
			else {
			
				if (major != null && !major.equals("")) {
					sql.append("major = '" + major + "'");

					if (classes != null && !classes.equals("")) {

						sql.append(" and classes = '" + classes + "'");
					}
				}
			 else {


					if (classes != null && !classes.equals("")) {

						sql.append("classes = '" + classes + "'");
					}
				} 
			
			}
			System.out.println(sql.toString());
			return sql.toString();
	}
	
	/**
	 * 根据姓名模糊查询,学号进行查询
	 * @param name
	 * @param snum
	 * @return
	 */
		
	public String fuzzyQueryStudents(String name,String snum) {
		StringBuffer sql = new StringBuffer("select * from student where ");
		if (name != null && !name.equals("")) {
			sql.append("sname like" + "'%" + name + "%'");
			
		}else {
			if(snum!=null && !snum.equals("")) {
				sql.append("snum ="+snum);
			}
		}
		System.out.println(sql.toString());
		return sql.toString();
	}
	/**
	 * 查找学生的数量
	 * @param school
	 * @param college
	 * @return
	 */
	public String countStudents(String school,String college) {
		StringBuffer sql=new StringBuffer("select count(*) from student where ");
		if(college !=null && !college.equals("")) {
			sql.append("school ='"+school+"' and college ='"+college+"'");
			return sql.toString();
		}else {
			sql.append("school ='"+school+"'");
			return sql.toString();
		}
		
	}

}
