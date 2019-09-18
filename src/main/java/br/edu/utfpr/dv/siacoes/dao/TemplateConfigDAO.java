package br.edu.utfpr.dv.siacoes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.utfpr.dv.siacoes.log.UpdateEvent;

public abstract class TemplateConfigDAO<T> {

	protected abstract String getStringSqlFindDepartmentById();
	protected abstract String getStringSqlInsert();
	protected abstract String getStringSqlUpdate();
	
	protected abstract int getDepartmentId(T object);

	protected abstract T loadObject(ResultSet rs) throws SQLException;

	protected abstract void ormSave(PreparedStatement stmt, T object) throws SQLException;


    public final T findByDepartment(int idDepartment) throws SQLException {
        Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try{
			conn = ConnectionDAO.getInstance().getConnection();
            String sql = getStringSqlFindDepartmentById();
			stmt = conn.prepareStatement(sql);
		
			stmt.setInt(1, idDepartment);
			
			rs = stmt.executeQuery();
			
			if(rs.next()){
				return this.loadObject(rs);
			}else{
				return null;
			}
		}finally{
			if((rs != null) && !rs.isClosed())
				rs.close();
			if((stmt != null) && !stmt.isClosed())
				stmt.close();
			if((conn != null) && !conn.isClosed())
				conn.close();
		}
	}

	public final int save(int idUser, T config) throws SQLException{
		boolean insert = (this.findByDepartment(getDepartmentId(config)) == null);
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try{
			conn = ConnectionDAO.getInstance().getConnection();
			
			String sql = insert ? getStringSqlInsert() : getStringSqlUpdate();
			stmt = conn.prepareStatement(sql);

			ormSave(stmt, config);
			stmt.execute();
			
			new UpdateEvent(conn).registerUpdate(idUser, config);
			
			return getDepartmentId(config);
		}finally{
			if((stmt != null) && !stmt.isClosed())
				stmt.close();
			if((conn != null) && !conn.isClosed())
				conn.close();
		}
	}
	
}