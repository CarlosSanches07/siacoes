package br.edu.utfpr.dv.siacoes.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.utfpr.dv.siacoes.model.SigesConfig;
import br.edu.utfpr.dv.siacoes.model.SigetConfig.SupervisorFilter;

public class SigesConfigDAO extends TemplateConfigDAO<SigesConfig> {
	
	@Override
	protected SigesConfig loadObject(ResultSet rs) throws SQLException{
		SigesConfig config = new SigesConfig();
		
		config.getDepartment().setIdDepartment(rs.getInt("idDepartment"));
		config.setMinimumScore(rs.getDouble("minimumScore"));
		config.setSupervisorPonderosity(rs.getDouble("supervisorPonderosity"));
		config.setCompanySupervisorPonderosity(rs.getDouble("companySupervisorPonderosity"));
		config.setShowGradesToStudent(rs.getInt("showgradestostudent") == 1);
		config.setSupervisorFilter(SupervisorFilter.valueOf(rs.getInt("supervisorfilter")));
		config.setSupervisorFillJuryForm(rs.getInt("supervisorFillJuryForm") == 1);
		config.setMaxFileSize(rs.getInt("maxfilesize"));
		config.setJuryTime(rs.getInt("jurytime"));
		
		return config;
	}

	@Override
	protected String getStringSqlFindDepartmentById() {
		return "SELECT * FROM sigesconfig WHERE idDepartment = ?";
	}

	@Override
	protected String getStringSqlInsert() {
		return "INSERT INTO sigesconfig(minimumScore, supervisorPonderosity, companySupervisorPonderosity, showgradestostudent, supervisorfilter, supervisorFillJuryForm, maxfilesize, jurytime, idDepartment) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	}

	@Override
	protected String getStringSqlUpdate() {
		return "UPDATE sigesconfig SET minimumScore=?, supervisorPonderosity=?, companySupervisorPonderosity=?, showgradestostudent=?, supervisorfilter=?, supervisorFillJuryForm=?, maxfilesize=?, jurytime=? WHERE idDepartment=?";
	}

	@Override
	protected int getDepartmentId(SigesConfig object) {
		return object.getDepartment().getIdDepartment();
	}

	@Override
	protected void ormSave(PreparedStatement stmt, SigesConfig object) throws SQLException {
		stmt.setDouble(1, object.getMinimumScore());
		stmt.setDouble(2, object.getSupervisorPonderosity());
		stmt.setDouble(3, object.getCompanySupervisorPonderosity());
		stmt.setInt(4, object.isShowGradesToStudent() ? 1 : 0);
		stmt.setInt(5, object.getSupervisorFilter().getValue());
		stmt.setInt(6, object.isSupervisorFillJuryForm() ? 1 : 0);
		stmt.setInt(7, object.getMaxFileSize());
		stmt.setInt(8, object.getJuryTime());
		stmt.setInt(9, object.getDepartment().getIdDepartment());
	}
	
}
