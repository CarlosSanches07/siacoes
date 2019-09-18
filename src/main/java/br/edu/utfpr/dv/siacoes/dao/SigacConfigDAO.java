package br.edu.utfpr.dv.siacoes.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.utfpr.dv.siacoes.model.SigacConfig;

public class SigacConfigDAO extends TemplateConfigDAO<SigacConfig> {
	
	@Override
	protected SigacConfig loadObject(ResultSet rs) throws SQLException{
		SigacConfig config = new SigacConfig();
		
		config.getDepartment().setIdDepartment(rs.getInt("idDepartment"));
		config.setMinimumScore(rs.getDouble("minimumScore"));
		config.setMaxFileSize(rs.getInt("maxfilesize"));
		
		return config;
	}

	@Override
	protected String getStringSqlFindDepartmentById() {
		return "SELECT * FROM sigacconfig WHERE idDepartment = ?";
	}

	@Override
	protected String getStringSqlInsert() {
		return "INSERT INTO sigacconfig(minimumScore, maxfilesize, idDepartment) VALUES(?, ?, ?)";
	}

	@Override
	protected String getStringSqlUpdate() {
		return "UPDATE sigacconfig SET minimumScore=?, maxfilesize=? WHERE idDepartment=?";
	}

	@Override
	protected int getDepartmentId(SigacConfig object) {
		return object.getDepartment().getIdDepartment();
	}

	@Override
	protected void ormSave(PreparedStatement stmt, SigacConfig object) throws SQLException {
		stmt.setDouble(1, object.getMinimumScore());
		stmt.setInt(2, object.getMaxFileSize());
		stmt.setInt(3, object.getDepartment().getIdDepartment());

	}

}
