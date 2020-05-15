package com.ecs.dao.provider;

public class AccessdataProvider {
	public String fuzzyAccessData(String school,String college,String username,String userid) {
		StringBuffer sql = new StringBuffer("select * from accessdata where ");
		sql.append(" school='"+school+"'");
		if (college !=null && !college.equals("")) {
			sql.append("and college='"+college+"'");
		}
		if (username != null && !username.equals("")) {
			sql.append("and username like" + "'%" + username + "%'");
			
		}
		if (userid!=null && !userid.equals("")) {
			sql.append("and userid ='"+userid+"'");
		}
		sql.append(" ORDER BY date DESC");
		System.out.println(sql.toString());
		return sql.toString();
	}
	
	public String dynamicFindAccessData(String school,String college) {
		StringBuffer sql = new StringBuffer("select * from accessdata where ");
		sql.append("school='"+school+"'");
		if (college !=null&&!college.equals("")) {
			sql.append(" and college='"+college+"'");
		}
		sql.append(" ORDER BY date DESC");
		return sql.toString();
		
	}
}
