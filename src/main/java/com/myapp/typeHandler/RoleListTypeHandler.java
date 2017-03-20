package com.myapp.typeHandler;

import com.common.store.UniqueStore;
import com.domain.userManagement.Role;
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
public class RoleListTypeHandler extends BaseTypeHandler<List<Role>> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, List<Role> roleList, JdbcType jdbcType) throws SQLException {
		ps.setString(i, StringUtils.collectionToDelimitedString(roleList, UniqueStore.COMMA_DELIMITER));
	}

	@Override
	public List<Role> getNullableResult(ResultSet rs, String columnName) throws SQLException {
		String resultString = rs.getString(columnName);
		return getFromResultString(resultString);
	}

	@Override
	public List<Role> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		return getFromResultString(rs.getString(columnIndex));
	}

	@Override
	public List<Role> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return getFromResultString(cs.getString(columnIndex));
	}

	private List<Role> getFromResultString(String result) {
		ArrayList<Role> roleList = new ArrayList<>();
		for (String role : result.split(UniqueStore.COMMA_DELIMITER)) {
			roleList.add(Role.valueOf(role));
		}
		return roleList;
	}
}