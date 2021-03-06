package io.leopard.exporter.mysql;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

import io.leopard.exporter.ExportSqlBuilder;
import io.leopard.exporter.Exporter;
import io.leopard.exporter.ExporterLeopardBeanPropertyRowMapper;
import io.leopard.jdbc.Jdbc;

public class ExporterMysqlImpl implements Exporter {

	private Jdbc jdbc;

	public ExporterMysqlImpl(Jdbc jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public <T> List<T> export(Class<T> model, int start, int size) {
		ExportSqlBuilder builder = new ExportSqlBuilder(model, ExportSqlBuilder.ESC_MYSQL);
		String tableName = builder.getTableName();
		String sql = builder.buildSql();
		System.out.println(sql);
		// return jdbc.queryForList(sql, model);
		try {
			return jdbc.getJdbcTemplate().query(sql, new ExporterLeopardBeanPropertyRowMapper<T>(model, tableName));
		}
		catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public Jdbc getJdbc() {
		return jdbc;
	}

}
