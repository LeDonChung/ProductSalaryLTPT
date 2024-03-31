package com.product.salary.application.dao.impl;

import com.product.salary.application.entity.CongNhan;
import com.product.salary.application.entity.SanPham;
import com.product.salary.application.entity.TayNghe;
import com.product.salary.application.entity.ToNhom;
import com.product.salary.application.dao.CongNhanDAO;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CongNhanDAOImpl extends AbstractDAO implements CongNhanDAO, Serializable {

	@Override
	public List<CongNhan> timKiemTatCaCongNhan() {
		try(var em = getEntityManager()) {
			return em.createQuery("SELECT c FROM CongNhan c", CongNhan.class).getResultList();
		}
	}

	@Override
	public CongNhan capNhatCongNhan(CongNhan congNhan) {
		try(var em = getEntityManager()) {
			em.getTransaction().begin();
			em.merge(congNhan);
			em.getTransaction().commit();
			return congNhan;
		}
	}

	@Override
	public boolean capNhatTrangThaiCongNhan(String maCongNhan, boolean trangThai) {
		try(var em = getEntityManager()) {
			CongNhan congNhan = em.find(CongNhan.class, maCongNhan);
			if(congNhan != null) {
				em.getTransaction().begin();
				congNhan.setTrangThai(trangThai);
				em.getTransaction().commit();
				return true;
			}
			return false;
		}
	}

	@Override
	public List<CongNhan> timKiemCongNhan(CongNhan congNhan) {
		try(var em = getEntityManager()) {

			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<CongNhan> cq = cb.createQuery(CongNhan.class);
			Root<CongNhan> congNhanRoot = cq.from(CongNhan.class);

			List<Predicate> predicates = new ArrayList<>();
			if (!StringUtils.isBlank(congNhan.getMaCongNhan())) {
				predicates.add(cb.like(congNhanRoot.get("maCongNhan"), "%" + congNhan.getMaCongNhan() + "%"));
			}
			if (!StringUtils.isBlank(congNhan.getEmail())) {
				predicates.add(cb.like(congNhanRoot.get("email"), "%" + congNhan.getEmail() + "%"));
			}
			if (!StringUtils.isBlank(congNhan.getCccd())) {
				predicates.add(cb.like(congNhanRoot.get("cccd"), "%" + congNhan.getCccd() + "%"));
			}
			if (!StringUtils.isBlank(congNhan.getDiaChi())) {
				predicates.add(cb.like(congNhanRoot.get("diaChi"), "%" + congNhan.getDiaChi() + "%"));
			}
			if (!StringUtils.isBlank(congNhan.getHoTen())) {
				predicates.add(cb.like(congNhanRoot.get("hoTen"), "%" + congNhan.getHoTen() + "%"));
			}
			if (!StringUtils.isBlank(congNhan.getSoDienThoai())) {
				predicates.add(cb.like(congNhanRoot.get("soDienThoai"), "%" + congNhan.getSoDienThoai() + "%"));
			}
			if (congNhan.getGioiTinh() != 2) {
				predicates.add(cb.equal(congNhanRoot.get("gioiTinh"), congNhan.getGioiTinh()));
			}

			if (congNhan.getNgaySinh() != null) {
				predicates.add(cb.equal(congNhanRoot.get("ngaySinh"), congNhan.getNgaySinh()));
			}
			if (congNhan.getNgayVaoLam() != null) {
				predicates.add(cb.equal(congNhanRoot.get("ngayVaoLam"), congNhan.getNgayVaoLam()));
			}
			if (congNhan.getTroCap() != null && congNhan.getTroCap() >= 0 ) {
				predicates.add(cb.greaterThanOrEqualTo(congNhanRoot.get("troCap"), congNhan.getTroCap()));
			}
			if (congNhan.getHinhAnh() != null) {
				predicates.add(cb.equal(congNhanRoot.get("hinhAnh"), congNhan.getHinhAnh()));
			}
			if (congNhan.isTrangThai() != null) {
				predicates.add(cb.equal(congNhanRoot.get("trangThai"), congNhan.isTrangThai()));
			}
			if (congNhan.getToNhom() != null) {
				if (!congNhan.getToNhom().getMaToNhom().equals("XXXX")) {
					predicates.add(cb.equal(congNhanRoot.get("toNhom"), congNhan.getToNhom()));
				}
			}
			if (congNhan.getTayNghe() != null) {
				if (!congNhan.getTayNghe().getMaTayNghe().equals("XXXX")) {
					predicates.add(cb.equal(congNhanRoot.get("tayNghe"), congNhan.getTayNghe()));
				}
			}

			cq.where(cb.and(predicates.toArray(new Predicate[0])));

			TypedQuery<CongNhan> query = em.createQuery(cq);
			return query.getResultList();
		}
	}

	private String getConditions(CongNhan congNhan) {
		// Câu truy vấn
		StringBuilder query = new StringBuilder("");

		// Các điều kiện của câu truy vấn
		List<String> conditions = new ArrayList<String>();

		// Mã công nhân
		conditions.add(!StringUtils.isBlank(congNhan.getMaCongNhan())
				? String.format("MaCongNhan LIKE N'%%%s%%'", congNhan.getMaCongNhan())
				: "");

		// Họ tên công nhân
		conditions.add(
				!StringUtils.isBlank(congNhan.getHoTen()) ? String.format("HoTen LIKE N'%%%s%%'", congNhan.getHoTen())
						: "");

		// Số điện thoại
		conditions.add(!StringUtils.isBlank(congNhan.getSoDienThoai())
				? String.format("SoDienThoai LIKE N'%%%s%%'", congNhan.getSoDienThoai())
				: "");

		// CCCD
		conditions
				.add(!StringUtils.isBlank(congNhan.getCccd()) ? String.format("Cccd LIKE N'%%%s%%'", congNhan.getCccd())
						: "");

		// Email
		conditions.add(
				!StringUtils.isBlank(congNhan.getEmail()) ? String.format("Email LIKE N'%%%s%%'", congNhan.getEmail())
						: "");

		// Địa chỉ
		conditions.add(!StringUtils.isBlank(congNhan.getDiaChi())
				? String.format("DiaChi LIKE N'%%%s%%'", congNhan.getDiaChi())
				: "");

		// Trạng thái
		if (congNhan.isTrangThai() != null) {
			conditions.add(String.format("CongNhan.TrangThai = %d", congNhan.isTrangThai() ? 1 : 0));
		}

		// giới tính
		if (congNhan.getGioiTinh() != 2) {
			conditions.add(String.format("CongNhan.GioiTinh = %d", congNhan.getGioiTinh()));
		}

		// Ngày sinh
		if (congNhan.getNgaySinh() != null) {
			conditions.add(String.format("NgaySinh = '%s'", congNhan.getNgaySinh()));
		} else {
			conditions.add("");
		}

		// Ngày vào làm
		if (congNhan.getNgayVaoLam() != null) {
			conditions.add(String.format("NgayVaoLam = '%s'", congNhan.getNgayVaoLam()));
		} else {
			conditions.add("");
		}

		// Trợ cấp
		if (congNhan.getTroCap() != null) {
			conditions.add(congNhan.getTroCap() >= 0 ? String.format("TroCap >= %f", congNhan.getTroCap()) : "");
		}

		// Tổ nhóm
		if (congNhan.getToNhom() != null) {
			if (!congNhan.getToNhom().getMaToNhom().equals("XXXX")) {
				conditions.add(String.format("CongNhan.MaToNhom = '%s'", congNhan.getToNhom().getMaToNhom()));
			}
		}

		// Tay nghề
		if (congNhan.getTayNghe() != null) {
			if (!congNhan.getTayNghe().getMaTayNghe().equals("XXXX")) {
				conditions.add(String.format("CongNhan.MaTayNghe = '%s'", congNhan.getTayNghe().getMaTayNghe()));
			}
		}

		// Xóa các điều kiện rỗng
		conditions.removeIf((v) -> v.equals(""));

		if (!conditions.isEmpty()) {
			String conditionValue = StringUtils.join(conditions, " AND ");
			query.append(String.format(" WHERE %s", conditionValue));
		}

		return query.toString();
	}

	@Override
	public CongNhan themCongNhan(CongNhan congNhan) {
		try(var em = getEntityManager()) {
			em.getTransaction().begin();
			em.persist(congNhan);
			em.getTransaction().commit();
			return congNhan;
		}
	}

	@Override
	public CongNhan timKiemBangMaCongNhan(String maCongNhan) {
		try(var em = getEntityManager()) {
			return em.find(CongNhan.class, maCongNhan);
		}
	}

	@Override
	public CongNhan timKiemBangCCCD(String cccdS) {
		try(var em = getEntityManager()) {
			String query = "SELECT c FROM CongNhan c WHERE c.cccd = :cccd";
			return em.createQuery(query, CongNhan.class).setParameter("cccd", cccdS).getSingleResult();
		}
	}

	@Override
	public List<CongNhan> timKiemCongNhanBangMaToNhom(String maToNhom) {
		try(var em = getEntityManager()) {
			String query = "SELECT c FROM CongNhan c WHERE c.toNhom.maToNhom = :maToNhom";
			return em.createQuery(query, CongNhan.class).setParameter("maToNhom", maToNhom).getResultList();
		}
	}

	@Override
	public String layMaCongNhanCuoiCungCuaNam(int nam) {
		try(var em = getEntityManager()) {
			String query = "SELECT c FROM CongNhan c WHERE c.maCongNhan LIKE :maCongNhan ORDER BY c.maCongNhan DESC";
			List<CongNhan> congNhans = em.createQuery(query, CongNhan.class).setParameter("maCongNhan", "20" + nam + "%")
					.setMaxResults(1).getResultList();
			return congNhans.isEmpty() ? null : congNhans.get(0).getMaCongNhan();
		}
	}

	@Override
	public int tongSoLuongCongNhan() {
		try(var em = getEntityManager()) {
			String query = "SELECT COUNT(c) FROM CongNhan c";
			return em.createQuery(query, Long.class).getSingleResult().intValue();
		}
	}
}
