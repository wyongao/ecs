package com.ecs.dao.provider;

public class ApplicationProvider {
		//分页动态查询
	public String selectWithParam(String college,String major,String classes)
	{
		StringBuffer sql=new StringBuffer("select * from application where ");
			if (college!=null && !college.equals("")) {
				sql.append("college = '"+college+"'");
				if (major!=null && !major.equals("")) {
					sql.append("and major='"+major+"'");
				}
				if (classes!=null && !classes.equals("")) {
					sql.append("and classes= '"+classes+"'");
				}
			}else {
				if (major!=null && !major.equals("")) {
					sql.append("major='"+major+"'");
					if (classes!=null && !classes.equals("")) {
						sql.append("and classes= '"+classes+"'");
					}
				}else {
					if (classes!=null && !classes.equals("")) {
						sql.append("classes= '"+classes+"'");
						return sql.toString();
					}
				}
			}
			return sql.toString();
	}
	//根据学号和姓名模糊查询
	public String fuzzyApplications(String snum,String sname) {
		StringBuffer sql=new StringBuffer("select * from application where");
		if (snum!=null && !snum.equals("")) {
			sql.append(" snum = "+"'"+snum+"'" );
		}else {
			if (sname!=null && !sname.equals("")) {
				sql.append(" sname like"+"'%"+sname+"%'");
			}
			return sql.toString();
		}
		return sql.toString();
	}
}
