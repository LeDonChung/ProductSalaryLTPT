package com.product.salary.application.dao.impl;

import com.product.salary.application.entity.Account;
import com.product.salary.application.entity.NhanVien;
import com.product.salary.application.dao.AccountDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAOImpl extends AbstractDAO implements AccountDAO, Serializable {

	@Override
	public Account timKiemTaiKhoanHoatDongBangTaiKhoanVaMatKhau(String taiKhoanS, String matKhauS) {
		EntityManager em = getEntityManager();
		Query query = em.createQuery("SELECT a FROM Account a WHERE a.taiKhoan = :taiKhoan AND a.matKhau = :matKhau AND a.trangThai = true");
		query.setParameter("taiKhoan", taiKhoanS);
		query.setParameter("matKhau", matKhauS);
		return (Account) query.getSingleResult();
	}

	@Override
	public List<Account> timKiemTatCaTaiKhoan() {
		try(var em = getEntityManager()) {
			return em.createQuery("SELECT a FROM Account a", Account.class).getResultList();
		}
	}





	@Override
	public boolean capNhatMatKhau(String taiKhoan, String matKhau) {
		try (var em = getEntityManager()) {
			Account account = em.find(Account.class, taiKhoan);
			if (account != null) {
				em.getTransaction().begin();
				account.setMatKhau(matKhau);
				em.merge(account);
				em.getTransaction().commit();
				return true;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return false;
	}


	@Override
	public Account timKiemBangTaiKhoan(String taiKhoan) {
		try (var em = getEntityManager()) {
			return em.find(Account.class, taiKhoan);
		}
	}

}
