package com.ecs.dao.provider;

public class ApplicationProvider {
		//分页动态查询
	public String selectWithParam(String school,String college,String major,String classes, String inout)
	{
		StringBuffer sql=new StringBuffer("select * from application where ");
			sql.append("school ='"+school+"'");
			if (college!=null && !college.equals("")) {
				sql.append("and college = '"+college+"'");
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
			sql.append(" and `inout`= '"+inout+"'");
			return sql.toString();
	}
	//根据学号和姓名模糊查询
	public String fuzzyApplications(String school,String college, String snum,String sname,String inout) {
		StringBuffer sql=new StringBuffer("select * from application where ");
		sql.append(" `inout` ='"+inout+"'");
		sql.append(" and school='"+school+"'");
		if (college!=null && !college.equals("")) {
			sql.append(" and college = "+"'"+college+"'" );
		}
		if (snum!=null && !snum.equals("")) {
			sql.append(" and snum = "+"'"+snum+"'" );
		}
		if (sname!=null && !sname.equals("")) {
			sql.append(" and sname like"+"'%"+sname+"%'");
		}

		return sql.toString();
	}
}
