package br.edu.utfpr.dv.siacoes.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.utfpr.dv.siacoes.model.SigetConfig;
import br.edu.utfpr.dv.siacoes.model.SigetConfig.AttendanceFrequency;
import br.edu.utfpr.dv.siacoes.model.SigetConfig.SupervisorFilter;

public class SigetConfigDAO extends TemplateConfigDAO<SigetConfig> {

	@Override
	protected SigetConfig loadObject(ResultSet rs) throws SQLException{
		SigetConfig config = new SigetConfig();
		
		config.getDepartment().setIdDepartment(rs.getInt("idDepartment"));
		config.setMinimumScore(rs.getDouble("minimumScore"));
		config.setRegisterProposal(rs.getInt("registerProposal") == 1);
		config.setShowGradesToStudent(rs.getInt("showgradestostudent") == 1);
		config.setSupervisorFilter(SupervisorFilter.valueOf(rs.getInt("supervisorFilter")));
		config.setCosupervisorFilter(SupervisorFilter.valueOf(rs.getInt("cosupervisorFilter")));
		config.setSupervisorIndication(rs.getInt("supervisorIndication"));
		config.setMaxTutoredStage1(rs.getInt("maxTutoredStage1"));
		config.setMaxTutoredStage2(rs.getInt("maxTutoredStage2"));
		config.setRequestFinalDocumentStage1(rs.getInt("requestFinalDocumentStage1") == 1);
		config.setRepositoryLink(rs.getString("repositoryLink"));
		config.setSupervisorJuryRequest(rs.getInt("supervisorJuryRequest") == 1);
		config.setSupervisorAgreement(rs.getInt("supervisorAgreement") == 1);
		config.setSupervisorJuryAgreement(rs.getInt("supervisorJuryAgreement") == 1);
		config.setValidateAttendances(rs.getInt("validateAttendances") == 1);
		config.setAttendanceFrequency(AttendanceFrequency.valueOf(rs.getInt("attendanceFrequency")));
		config.setMaxFileSize(rs.getInt("maxfilesize"));
		config.setMinimumJuryMembers(rs.getInt("minimumJuryMembers"));
		config.setMinimumJurySubstitutes(rs.getInt("minimumJurySubstitutes"));
		config.setJuryTimeStage1(rs.getInt("jurytimestage1"));
		config.setJuryTimeStage2(rs.getInt("jurytimestage2"));
		config.setSupervisorAssignsGrades(rs.getInt("supervisorAssignsGrades") == 1);
		
		return config;
	}

	@Override
	protected String getStringSqlFindDepartmentById() {
		return "SELECT * FROM sigetconfig WHERE idDepartment = ?";
	}

	@Override
	protected String getStringSqlInsert() {
		return "INSERT INTO sigetconfig(minimumScore, registerProposal, showgradestostudent, supervisorfilter, cosupervisorfilter, supervisorIndication, maxTutoredStage1, maxTutoredStage2, requestFinalDocumentStage1, repositoryLink, supervisorJuryRequest, supervisorAgreement, supervisorJuryAgreement, validateAttendances, attendanceFrequency, maxfilesize, minimumJuryMembers, minimumJurySubstitutes, jurytimestage1, jurytimestage2, supervisorAssignsGrades, idDepartment) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	}

	@Override
	protected String getStringSqlUpdate() {
		return "UPDATE sigetconfig SET minimumScore=?, registerProposal=?, showgradestostudent=?, supervisorfilter=?, cosupervisorfilter=?, supervisorIndication=?, maxTutoredStage1=?, maxTutoredStage2=?, requestFinalDocumentStage1=?, repositoryLink=?, supervisorJuryRequest=?, supervisorAgreement=?, supervisorJuryAgreement=?, validateAttendances=?, attendanceFrequency=?, maxfilesize=?, minimumJuryMembers=?, minimumJurySubstitutes=?, jurytimestage1=?, jurytimestage2=?, supervisorAssignsGrades=? WHERE idDepartment=?";
	}

	@Override
	protected int getDepartmentId(SigetConfig object) {
		return object.getDepartment().getIdDepartment();
	}

	@Override
	protected void ormSave(PreparedStatement stmt, SigetConfig object) throws SQLException {
		stmt.setDouble(1, object.getMinimumScore());
		stmt.setInt(2, (object.isRegisterProposal() ? 1 : 0));
		stmt.setInt(3, (object.isShowGradesToStudent() ? 1 : 0));
		stmt.setInt(4, object.getSupervisorFilter().getValue());
		stmt.setInt(5, object.getCosupervisorFilter().getValue());
		stmt.setInt(6, object.getSupervisorIndication());
		stmt.setInt(7, object.getMaxTutoredStage1());
		stmt.setInt(8, object.getMaxTutoredStage2());
		stmt.setInt(9, (object.isRequestFinalDocumentStage1() ? 1 : 0));
		stmt.setString(10, object.getRepositoryLink());
		stmt.setInt(11, (object.isSupervisorJuryRequest() ? 1 : 0));
		stmt.setInt(12, (object.isSupervisorAgreement() ? 1 : 0));
		stmt.setInt(13, (object.isSupervisorJuryAgreement() ? 1 : 0));
		stmt.setInt(14, (object.isValidateAttendances() ? 1 : 0));
		stmt.setInt(15, object.getAttendanceFrequency().getValue());
		stmt.setInt(16, object.getMaxFileSize());
		stmt.setInt(17, object.getMinimumJuryMembers());
		stmt.setInt(18, object.getMinimumJurySubstitutes());
		stmt.setInt(19, object.getJuryTimeStage1());
		stmt.setInt(20, object.getJuryTimeStage2());
		stmt.setInt(21, (object.isSupervisorAssignsGrades() ? 1 : 0));
		stmt.setInt(22, object.getDepartment().getIdDepartment());
	}
	
}
