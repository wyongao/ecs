package com.ecs.dao.provider;

public class AccessdataProvider {
	public String fuzzyAccessData(String username,String userid) {
		StringBuffer sql = new StringBuffer("select * from accessdata where ");
		if (username != null && !username.equals("")) {
			sql.append("username like" + "'%" + username + "%'");
			
		}
		if (userid!=null && !userid.equals("")) {
			sql.append("userid ='"+userid+"'");
		}
		System.out.println(sql.toString());
		return sql.toString();
	}

}
