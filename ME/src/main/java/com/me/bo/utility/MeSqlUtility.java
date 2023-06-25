package com.me.bo.utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class MeSqlUtility {

	public static List<Map<String, Object>> rsToListofHm(ResultSet rs) throws SQLException {
		ResultSetMetaData md = rs.getMetaData();
		int columns = md.getColumnCount();
		List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
		while (rs.next()) {
			Map<String, Object> row = new HashMap<String, Object>();
			for (int i = 1; i <= columns; i++) {
				row.put(md.getColumnLabel(i).toUpperCase(), rs.getObject(i));
			}
			results.add(row);
		}
		return results;
	}

	public static int insertOneRow(JdbcTemplate jdbcTemplate, String tableName, Map<String, Object> dataMap) {
		StringBuilder sql = new StringBuilder("INSERT INTO ").append(tableName).append(" (");
		StringBuilder placeholders = new StringBuilder();

		for (Iterator<String> iter = dataMap.keySet().iterator(); iter.hasNext();) {
			sql.append(iter.next());
			placeholders.append("?");

			if (iter.hasNext()) {
				sql.append(",");
				placeholders.append(",");
			}
		}
		sql.append(") VALUES (").append(placeholders).append(")");
		int i = 0;
		Object[] params = new Object[dataMap.values().size()];
		for (Object value : dataMap.values()) {
			params[i++] = value;
		}

		System.out.println(sql.toString() + "  params:  [" + Arrays.asList(params) + "]");
		return jdbcTemplate.update(sql.toString(), params);

	}
	
	public static List<HashMap<String, Object>> executeSelectQuery(JdbcTemplate jdbcTemplate,String query) {
        return jdbcTemplate.query(query, new RowMapper<HashMap<String, Object>>() {
            @Override
            public HashMap<String, Object> mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                HashMap<String, Object> resultMap = new HashMap<>();
                int columnCount = resultSet.getMetaData().getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = resultSet.getMetaData().getColumnLabel(i);
                    Object columnValue = resultSet.getObject(i);
                    resultMap.put(columnName, columnValue);
                }
                return resultMap;
            }
        });
    }

}
