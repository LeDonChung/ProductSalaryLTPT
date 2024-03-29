package com.product.salary.application.dao.impl;

import com.product.salary.application.entity.TrinhDo;
import com.product.salary.application.dao.TrinhDoDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TrinhDoDAOImpl extends AbstractDAO implements TrinhDoDAO {

	@Override
	public List<TrinhDo> timKiemTatCaTrinhDo() {
		Connection connect = getConnection();
		Statement state = null;
		ResultSet rs = null;
		List<TrinhDo> dsTrinhDo = new ArrayList<TrinhDo>();
		if (connect != null) {
			try {
				String query = "SELECT * FROM TrinhDo";
				state = connect.createStatement();
				rs = state.executeQuery(query);

				while (rs.next()) {
					String maTrinhDo = rs.getString("MaTrinhDo");
					String tenTrinhDo = rs.getString("TenTrinhDo");
					
					TrinhDo td = new TrinhDo(maTrinhDo, tenTrinhDo);
					dsTrinhDo.add(td);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (connect != null) {
					try {
						connect.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (state != null) {
					try {
						state.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return dsTrinhDo;
	}

	@Override
	public TrinhDo timKiemBangMaTrinhDo(String maTrinhDo) {
		Connection connect = getConnection();
		Statement state = null;
		ResultSet rs = null;
		TrinhDo trinhDo = null;
		if (connect != null) {
			try {
				String query = "SELECT * FROM TrinhDo WHERE MaTrinhDo = " + maTrinhDo;
				state = connect.createStatement();
				rs = state.executeQuery(query);

				while (rs.next()) {
					String tenTrinhDo = rs.getString("TenTrinhDo");
					
					trinhDo = new TrinhDo(maTrinhDo, tenTrinhDo);
					
					if(trinhDo != null)
						return trinhDo;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (connect != null) {
					try {
						connect.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (state != null) {
					try {
						state.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}

}
