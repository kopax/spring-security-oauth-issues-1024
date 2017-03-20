
package com.common.typeHandler;

import com.common.store.UniqueStore;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.util.StringUtils;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(List.class)
public class CommaSeparatedTypeHandler extends BaseTypeHandler<List<Object>> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, List<Object> list, JdbcType jdbcType) throws SQLException {
		ps.setString(i, StringUtils.collectionToDelimitedString(list, UniqueStore.COMMA_DELIMITER));
	}

	@Override
	public List<Object> getNullableResult(ResultSet rs, String columnName) throws SQLException {
		String resultString = rs.getString(columnName);
		return getFromResultString(resultString);
	}

	@Override
	public List<Object> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return getFromResultString(rs.getString(columnIndex));
	}

	@Override
	public List<Object> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return getFromResultString(cs.getString(columnIndex));
	}

	private List<Object> getFromResultString(String result) {
		ArrayList<Object> list = new ArrayList<>();
		for (Object item : result.split(UniqueStore.COMMA_DELIMITER)) {
			list.add(item);
		}

		return list;
	}
}