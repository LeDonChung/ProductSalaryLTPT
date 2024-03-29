package com.product.salary.application.service.impl;

import com.product.salary.application.dao.TrinhDoDAO;
import com.product.salary.application.dao.impl.TrinhDoDAOImpl;
import com.product.salary.application.entity.TrinhDo;
import com.product.salary.application.service.TrinhDoService;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TrinhDoServiceImpl implements TrinhDoService {
	private TrinhDoDAO trinhDoDAO;
	
	public TrinhDoServiceImpl() {
		this.trinhDoDAO = new TrinhDoDAOImpl();
	}

	@Override
	public List<TrinhDo> timKiemTatCaTrinhDo() {
		List<TrinhDo> danhSachTrinhDo = new ArrayList<>();
		try {
			danhSachTrinhDo = trinhDoDAO.timKiemTatCaTrinhDo();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Hệ thống đang có lỗi!");
			e.printStackTrace();
		}
		return danhSachTrinhDo;
	}

	@Override
	public TrinhDo timKiemBangMaTrinhDo(String maTrinhDo) {
		TrinhDo trinhDo = null;
		try {
			trinhDo = trinhDoDAO.timKiemBangMaTrinhDo(maTrinhDo);
			if(trinhDo == null) {
				JOptionPane.showMessageDialog(null, "Trình độ không tồn tại!");
			}else
				return trinhDo;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Hệ thống đang có lỗi!");
			e.printStackTrace();
		}
		
		return null;
	}

}
