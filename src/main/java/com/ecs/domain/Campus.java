package com.ecs.domain;
/**
  * 校区对应的实体类
 * @author xuluyang
 *
 * 2020年3月7日
 */
public class Campus {
		private Integer id;
		private String campusname;
		private Integer parentid;
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getCampusname() {
			return campusname;
		}
		public void setCampusname(String campusname) {
			this.campusname = campusname;
		}
		public Integer getParentid() {
			return parentid;
		}
		public void setParentid(Integer parentid) {
			this.parentid = parentid;
		}
		@Override
		public String toString() {
			return "Campus [id=" + id + ", campusname=" + campusname + ", parentid=" + parentid + "]";
		}
		
}
