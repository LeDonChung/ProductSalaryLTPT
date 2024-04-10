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
		try (var em = getEntityManager()){
			return em.createQuery("SELECT td FROM TrinhDo td", TrinhDo.class).getResultList();
		}
	}

	@Override
	public TrinhDo timKiemBangMaTrinhDo(String maTrinhDo) {
		try (var em = getEntityManager()){
			return em.find(TrinhDo.class, maTrinhDo);
		}
	}

}
