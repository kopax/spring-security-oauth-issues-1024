
package com.common.typeHandler;

import com.google.gson.Gson;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(Map.class)
public class JsonMapTypeHandler extends BaseTypeHandler<Map<String, Object>> {

	private Gson gson = new Gson();

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Map<String, Object> map, JdbcType jdbcType) throws SQLException {
		ps.setString(i, gson.toJson(map));
	}

	@Override
	public Map<String, Object> getNullableResult(ResultSet rs, String columnName) throws SQLException {
		String resultString = rs.getString(columnName);
		return getFromResultString(resultString);
	}

	@Override
	public Map<String, Object> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return getFromResultString(rs.getString(columnIndex));
	}

	@Override
	public Map<String, Object> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return getFromResultString(cs.getString(columnIndex));
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> getFromResultString(String result) {
		return gson.fromJson(result, Map.class);
	}
}