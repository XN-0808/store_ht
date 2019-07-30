package domain;

import java.util.List;

public class PageBean<T>
{
		// 总条数 total
		private Integer total;
	
		// 每页显示的数据 rows
		private List<T> rows;

		public Integer getTotal() {
			return total;
		}

		public void setTotal(Integer total) {
			this.total = total;
		}

		public List<T> getRows() {
			return rows;
		}

		public void setRows(List<T> rows) {
			this.rows = rows;
		}
		
		
}
