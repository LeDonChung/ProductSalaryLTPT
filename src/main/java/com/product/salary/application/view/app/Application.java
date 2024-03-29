package com.product.salary.application.view.app;

import com.product.salary.application.view.contract.HopDongForm;
import com.product.salary.application.view.department.PhongBanForm;
import com.product.salary.application.view.department.TimKiemPhongBanForm;
import com.product.salary.application.view.employee.*;
import com.product.salary.application.view.groups.TimKiemToNhomForm;
import com.product.salary.application.view.groups.ToNhomForm;
import com.product.salary.application.view.menu.MenuItem;
import com.product.salary.application.view.other.DangNhapForm;
import com.product.salary.application.view.other.DoiMatKhauForm;
import com.product.salary.application.view.other.TrangChuForm;
import com.product.salary.application.view.product.CongDoanSanPhamForm;
import com.product.salary.application.view.product.SanPhamForm;
import com.product.salary.application.view.product.TimKiemSanPhamForm;
import com.product.salary.application.view.statistic.LuongCongNhanForm;
import com.product.salary.application.view.statistic.LuongNhanVienForm;
import com.product.salary.application.view.statistic.TongQuatForm;
import com.product.salary.application.view.worker.*;
import com.product.salary.application.common.SystemConstants;
import com.product.salary.application.entity.Account;
import com.product.salary.application.interfaces.ISendResponse;
import com.product.salary.application.utils.AuthUtils;
import com.product.salary.application.utils.LanguageUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Application extends JFrame {
	private JScrollPane jScrollPane1;
	private JPanel panelMenu;
	private JPanel menus;
	private JPanel panelBody;
	private MenuItem menuSubCapNhatNhanVien;
	private MenuItem menuSubTimNhanVien;
	private MenuItem menuSubChamCongNhanVien;
	private MenuItem menuSubTinhLuongNhanVien;
	private MenuItem menuHopDong;
	private MenuItem menuSubCapNhatCongNhan;
	private MenuItem menuSubTimCongNhan;
	private MenuItem menuSubChamCongCongNhan;
	private MenuItem menuSubTinhLuongCongNhan;
	private MenuItem menuSubPhanCongCongViec;
	private MenuItem menuCongNhan;
	private MenuItem menuSubCapNhatSanPham;
	private MenuItem menuSubTimSanPham;
	private MenuItem menuSubPhanCongDoan;
	private MenuItem menuSubCapNhatPhongBan;
	private MenuItem menuSubTimPhongBan;
	private MenuItem menuSubThongKeTongQuat;
	private MenuItem menuSubThongKeLuongNhanVien;
	private MenuItem menuSubThongKeLuongCongNhan;
	private MenuItem menuSubDoiMatKhau;
	private MenuItem menuSubDangXuat;
	private MenuItem menuSubThoat;
	private MenuItem menuHeThong;
	private MenuItem menuChoose;
	private JPanel pnlAppName;
	private JLabel lblTenChuongTrinh;
	private JPanel pnlLeft;
	private MenuItem menuSubChucVu;
	private MenuItem menuTrangChu;
	private JLabel pnlThoiGianThuc;
	private JLabel lblNewLabel_1;
	private final DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	private MenuItem menuBaoCaoThongKe;
	private MenuItem menuPhongBan;
	private MenuItem menuSanPham;
	private MenuItem menuNhanVien;
	private MenuItem menuSubTayNghe;
	private MenuItem menuSubCapNhatToNhom;
	private MenuItem menuSubTimToNhom;
	private MenuItem menuToNhom;

	public Application() {
		SystemConstants.initLanguage();
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("src/main/resources/icon/png/ic_logo.png"));

		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		/*
		 * Set full screen
		 */
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setUndecorated(true);

		panelMenu = new JPanel();
		panelMenu.setBackground(new Color(8, 92, 255));
		jScrollPane1 = new JScrollPane();
		menus = new JPanel();
		menus.setBackground(new Color(8, 92, 255));
		panelBody = new JPanel();
		pnlAppName = new JPanel();
		pnlAppName.setLayout(new BoxLayout(pnlAppName, BoxLayout.Y_AXIS));
		pnlAppName.setBackground(new Color(8, 92, 255));

		lblTenChuongTrinh = new JLabel(SystemConstants.BUNDLE.getString("app.tenChuongTrinh"));
		lblTenChuongTrinh.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTenChuongTrinh.setForeground(Color.WHITE);
		lblTenChuongTrinh.setFont(new Font("Times New Roman", Font.BOLD, 20));

		lblTenChuongTrinh.setHorizontalAlignment(SwingConstants.CENTER);

		pnlThoiGianThuc = new JLabel();
		pnlThoiGianThuc.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlThoiGianThuc.setForeground(Color.WHITE);
		pnlThoiGianThuc.setFont(new Font("Times New Roman", Font.BOLD, 20));
		pnlThoiGianThuc.setHorizontalAlignment(SwingConstants.CENTER);

		Box box = new Box(BoxLayout.Y_AXIS);
		box.add(Box.createVerticalStrut(25));
		box.add(lblTenChuongTrinh);
		box.add(Box.createVerticalStrut(25));
		box.add(pnlThoiGianThuc);
		box.add(Box.createVerticalStrut(25));

		pnlAppName.add(box);
		panelMenu.setBackground(new Color(8, 92, 255));
		panelMenu.setPreferredSize(new Dimension(250, 384));

		jScrollPane1.setBorder(null);

		menus.setLayout(new BoxLayout(menus, BoxLayout.Y_AXIS));
		jScrollPane1.setViewportView(menus);

		GroupLayout panelMenuLayout = new GroupLayout(panelMenu);

		panelMenu.setLayout(panelMenuLayout);
		panelMenuLayout
				.setHorizontalGroup(panelMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE));
		panelMenuLayout.setVerticalGroup(panelMenuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE));

		panelBody.setBackground(new Color(255, 255, 255));
		panelBody.setLayout(new BorderLayout());
		getContentPane().add(panelBody, BorderLayout.CENTER);

		pnlLeft = new JPanel();
		getContentPane().add(pnlLeft, BorderLayout.WEST);
		pnlLeft.setLayout(new BorderLayout());

		pnlLeft.add(pnlAppName, BorderLayout.NORTH);
		pnlLeft.add(panelMenu, BorderLayout.CENTER);

		lblNewLabel_1 = new JLabel((String) null);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1.setAlignmentX(0.5f);
		setSize(new Dimension(871, 473));
		setLocationRelativeTo(null);

		setMenu();
		runTime();
	}

	private void runTime() {
		new Timer(500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pnlThoiGianThuc.setText(df.format(new Date()));
			}
		}).start();
	}

	private void setMenu() {
		// ICON
		ImageIcon iconTrangChu = new ImageIcon("src/main/resources/icon/menu/dashboard.png");
		ImageIcon iconHopDong = new ImageIcon("src/main/resources/icon/menu/contract.png");
		ImageIcon iconUnCheck = new ImageIcon("src/main/resources/icon/menu/ic_uncheck.png");
		ImageIcon iconNhanVien = new ImageIcon("src/main/resources/icon/menu/employee.png");
		ImageIcon iconCongNhan = new ImageIcon("src/main/resources/icon/menu/worker.png");
		ImageIcon iconSanPham = new ImageIcon("src/main/resources/icon/menu/product.png");
		ImageIcon iconPhongBan = new ImageIcon("src/main/resources/icon/menu/department.png");
		ImageIcon iconBaoCaoThongKe = new ImageIcon("src/main/resources/icon/menu/static.png");
		ImageIcon iconHeThong = new ImageIcon("src/main/resources/icon/menu/dashboard.png");
		ImageIcon iconChucVu = new ImageIcon("src/main/resources/icon/menu/ic_position.png");
		ImageIcon iconTayNghe = new ImageIcon("src/main/resources/icon/menu/skill.png");
		ImageIcon iconToNhom = new ImageIcon("src/main/resources/icon/menu/group.png");

		// MENU TRANGCHU
		menuTrangChu = new MenuItem(iconTrangChu, SystemConstants.BUNDLE.getString("menu.trangChu"),
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						showForm(new TrangChuForm());
						clickMenu(menuTrangChu);
					}
				});
		// MENU NHANVIEN
		menuSubCapNhatNhanVien = new MenuItem(iconUnCheck, SystemConstants.BUNDLE.getString("menu.capNhat"),
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						showForm(new NhanVienForm());
						clickMenu(menuSubCapNhatNhanVien);
					}
				});
		menuSubTimNhanVien = new MenuItem(iconUnCheck, SystemConstants.BUNDLE.getString("menu.timKiem"),
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						showForm(new TimKiemNhanVienForm());
						clickMenu(menuSubTimNhanVien);

					}
				});
		menuSubChamCongNhanVien = new MenuItem(iconUnCheck, SystemConstants.BUNDLE.getString("menu.chamCong"),
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						showForm(new ChamCongNhanVienForm());
						clickMenu(menuSubChamCongNhanVien);
					}
				});
		menuSubTinhLuongNhanVien = new MenuItem(iconUnCheck, SystemConstants.BUNDLE.getString("menu.tinhLuong"),
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						showForm(new TinhLuongNhanVienForm());
						clickMenu(menuSubTinhLuongNhanVien);
					}
				});

		menuSubChucVu = new MenuItem(iconUnCheck, SystemConstants.BUNDLE.getString("menu.chucVu"),
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						showForm(new ChucVuForm());
						clickMenu(menuSubChucVu);
					}
				});

		// MENU CONGNHAN
		menuSubCapNhatCongNhan = new MenuItem(iconUnCheck, SystemConstants.BUNDLE.getString("menu.capNhat"),
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						showForm(new CongNhanForm());
						clickMenu(menuSubCapNhatCongNhan);
					}
				});
		menuSubTimCongNhan = new MenuItem(iconUnCheck, SystemConstants.BUNDLE.getString("menu.timKiem"),
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						showForm(new TimKiemCongNhanForm());
						clickMenu(menuSubTimCongNhan);
					}
				});
		menuSubChamCongCongNhan = new MenuItem(iconUnCheck, SystemConstants.BUNDLE.getString("menu.chamCong"),
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						showForm(new ChamCongCongNhanForm());
						clickMenu(menuSubChamCongCongNhan);
					}
				});
		menuSubTinhLuongCongNhan = new MenuItem(iconUnCheck, SystemConstants.BUNDLE.getString("menu.tinhLuong"),
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						showForm(new TinhLuongCongNhanForm());
						clickMenu(menuSubTinhLuongCongNhan);
					}
				});
		menuSubPhanCongCongViec = new MenuItem(iconUnCheck, SystemConstants.BUNDLE.getString("menu.phanCongViec"),
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						showForm(new PhanCongCongNhanForm());
						clickMenu(menuSubPhanCongCongViec);
					}
				});

		menuSubTayNghe = new MenuItem(iconUnCheck, SystemConstants.BUNDLE.getString("menu.tayNghe"),
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						showForm(new TayNgheForm());
						clickMenu(menuSubTayNghe);
					}
				});

		// MENU SANPHAM
		menuSubCapNhatSanPham = new MenuItem(iconUnCheck, SystemConstants.BUNDLE.getString("menu.capNhat"),
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						showForm(new SanPhamForm());
						clickMenu(menuSubCapNhatSanPham);
					}
				});
		menuSubTimSanPham = new MenuItem(iconUnCheck, SystemConstants.BUNDLE.getString("menu.timKiem"),
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						showForm(new TimKiemSanPhamForm());
						clickMenu(menuSubTimSanPham);
					}
				});
		menuSubPhanCongDoan = new MenuItem(iconUnCheck, SystemConstants.BUNDLE.getString("menu.phanCongDoan"),
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						showForm(new CongDoanSanPhamForm());
						clickMenu(menuSubPhanCongDoan);
					}
				});

		// MENU PHONGBAN
		menuSubCapNhatPhongBan = new MenuItem(iconUnCheck, SystemConstants.BUNDLE.getString("menu.capNhat"),
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						showForm(new PhongBanForm());
						clickMenu(menuSubCapNhatPhongBan);
					}
				});

		menuSubTimPhongBan = new MenuItem(iconUnCheck, SystemConstants.BUNDLE.getString("menu.timKiem"),
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						showForm(new TimKiemPhongBanForm());
						clickMenu(menuSubTimPhongBan);
					}
				});

		// MENU TONHOM
		menuSubCapNhatToNhom = new MenuItem(iconUnCheck, SystemConstants.BUNDLE.getString("menu.capNhat"),
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						showForm(new ToNhomForm());
						clickMenu(menuSubCapNhatToNhom);
					}
				});

		menuSubTimToNhom = new MenuItem(iconUnCheck, SystemConstants.BUNDLE.getString("menu.timKiem"),
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						showForm(new TimKiemToNhomForm());
						clickMenu(menuSubTimToNhom);
					}
				});

		// MENU THONGKE
		menuSubThongKeTongQuat = new MenuItem(iconUnCheck, SystemConstants.BUNDLE.getString("menu.tongQuat"),
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						showForm(new TongQuatForm());
						clickMenu(menuSubThongKeTongQuat);
					}
				});
		menuSubThongKeLuongNhanVien = new MenuItem(iconUnCheck, SystemConstants.BUNDLE.getString("menu.luongNhanVien"),
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						showForm(new LuongNhanVienForm());
						clickMenu(menuSubThongKeLuongNhanVien);
					}
				});
		menuSubThongKeLuongCongNhan = new MenuItem(iconUnCheck, SystemConstants.BUNDLE.getString("menu.luongCongNhan"),
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						showForm(new LuongCongNhanForm());
						clickMenu(menuSubThongKeLuongCongNhan);
					}
				});

		// MENU HETHONG
		menuSubDoiMatKhau = new MenuItem(iconUnCheck, SystemConstants.BUNDLE.getString("menu.doiMatKhau"),
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						changePassword();
					}
				});
		menuSubDangXuat = new MenuItem(iconUnCheck, SystemConstants.BUNDLE.getString("menu.dangXuat"),
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						logout();
					}
				});
		menuSubThoat = new MenuItem(iconUnCheck, SystemConstants.BUNDLE.getString("menu.thoatUngDung"),
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						AuthUtils.logout();
						exit();
					}
				});
		menuHeThong = new MenuItem(iconHeThong, SystemConstants.BUNDLE.getString("menu.heThong"), null,
				menuSubDoiMatKhau, menuSubDangXuat, menuSubThoat);

		Account account = (Account) AuthUtils.getUser();

		if (account == null) {
			JOptionPane.showConfirmDialog(this, "Vui lòng đăng nhập để sử dụng.");
			new DangNhapForm().setVisible(true);
			dispose();
		}

		if (account.getRoleName().equals("QLNV")) {
			// MENU HOP DONG
			menuHopDong = new MenuItem(iconHopDong, SystemConstants.BUNDLE.getString("menu.hopDong"),
					new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							showForm(new HopDongForm());
							clickMenu(menuHopDong);
						}
					});

			menuCongNhan = new MenuItem(iconCongNhan, SystemConstants.BUNDLE.getString("menu.congNhan"), null,
					menuSubCapNhatCongNhan, menuSubTimCongNhan, menuSubTayNghe, menuSubChamCongCongNhan,
					menuSubPhanCongCongViec);

			menuNhanVien = new MenuItem(iconNhanVien, SystemConstants.BUNDLE.getString("menu.nhanVien"), null,
					menuSubCapNhatNhanVien, menuSubTimNhanVien, menuSubChucVu, menuSubChamCongNhanVien);
			menuSanPham = new MenuItem(iconSanPham, SystemConstants.BUNDLE.getString("menu.sanPham"), null,
					menuSubCapNhatSanPham, menuSubTimSanPham, menuSubPhanCongDoan);
			menuPhongBan = new MenuItem(iconPhongBan, SystemConstants.BUNDLE.getString("menu.phongBan"), null,
					menuSubCapNhatPhongBan, menuSubTimPhongBan);
			menuToNhom = new MenuItem(iconToNhom, SystemConstants.BUNDLE.getString("menu.toNhom"), null,
					menuSubCapNhatToNhom, menuSubTimToNhom);

			addMenu(menuTrangChu, menuHopDong, menuNhanVien, menuCongNhan, menuSanPham, menuPhongBan, menuToNhom,
					menuHeThong);
		} else if (account.getRoleName().equals("QLL")) {

			menuCongNhan = new MenuItem(iconCongNhan, SystemConstants.BUNDLE.getString("menu.congNhan"), null,
					menuSubTimCongNhan, menuSubTinhLuongCongNhan);

			menuNhanVien = new MenuItem(iconNhanVien, SystemConstants.BUNDLE.getString("menu.nhanVien"), null,
					menuSubTimNhanVien, menuSubTinhLuongNhanVien);
			menuSanPham = new MenuItem(iconSanPham, SystemConstants.BUNDLE.getString("menu.sanPham"), null,
					menuSubTimSanPham);
			menuPhongBan = new MenuItem(iconPhongBan, SystemConstants.BUNDLE.getString("menu.phongBan"), null,
					menuSubTimPhongBan);
			menuToNhom = new MenuItem(iconToNhom, SystemConstants.BUNDLE.getString("menu.toNhom"), null,
					menuSubTimToNhom);
			menuBaoCaoThongKe = new MenuItem(iconBaoCaoThongKe, SystemConstants.BUNDLE.getString("menu.baoCaoThongKe"),
					null, menuSubThongKeTongQuat, menuSubThongKeLuongNhanVien, menuSubThongKeLuongCongNhan);
			addMenu(menuTrangChu, menuNhanVien, menuCongNhan, menuSanPham, menuPhongBan, menuToNhom, menuBaoCaoThongKe,
					menuHeThong);
			// Sau này xóa đi
		} else {
			// MENU HOP DONG
			menuHopDong = new MenuItem(iconHopDong, SystemConstants.BUNDLE.getString("menu.hopDong"),
					new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							showForm(new HopDongForm());
							clickMenu(menuHopDong);
						}
					});
			menuCongNhan = new MenuItem(iconCongNhan, SystemConstants.BUNDLE.getString("menu.congNhan"), null,
					menuSubCapNhatCongNhan, menuSubTimCongNhan, menuSubTayNghe, menuSubChamCongCongNhan,
					menuSubTinhLuongCongNhan, menuSubPhanCongCongViec);

			menuNhanVien = new MenuItem(iconNhanVien, SystemConstants.BUNDLE.getString("menu.nhanVien"), null,
					menuSubCapNhatNhanVien, menuSubTimNhanVien, menuSubChucVu, menuSubChamCongNhanVien,
					menuSubTinhLuongNhanVien);
			menuSanPham = new MenuItem(iconSanPham, SystemConstants.BUNDLE.getString("menu.sanPham"), null,
					menuSubCapNhatSanPham, menuSubTimSanPham, menuSubPhanCongDoan);
			menuPhongBan = new MenuItem(iconPhongBan, SystemConstants.BUNDLE.getString("menu.phongBan"), null,
					menuSubCapNhatPhongBan, menuSubTimPhongBan);
			menuToNhom = new MenuItem(iconToNhom, SystemConstants.BUNDLE.getString("menu.toNhom"), null,
					menuSubCapNhatToNhom, menuSubTimToNhom);
			menuBaoCaoThongKe = new MenuItem(iconBaoCaoThongKe, SystemConstants.BUNDLE.getString("menu.baoCaoThongKe"),
					null, menuSubThongKeTongQuat, menuSubThongKeLuongNhanVien, menuSubThongKeLuongCongNhan);
			addMenu(menuTrangChu, menuHopDong, menuNhanVien, menuCongNhan, menuSanPham, menuPhongBan, menuToNhom,
					menuBaoCaoThongKe, menuHeThong);
		}
//		// MENU HOP DONG
//		menuHopDong = new MenuItem(iconHopDong, SystemConstants.BUNDLE.getString("menu.hopDong"), new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				showForm(new HopDongForm());
//				clickMenu(menuHopDong);
//			}
//		});
//		menuCongNhan = new MenuItem(iconCongNhan, SystemConstants.BUNDLE.getString("menu.congNhan"), null,
//				menuSubCapNhatCongNhan, menuSubTimCongNhan, menuSubTayNghe, menuSubChamCongCongNhan,
//				menuSubTinhLuongCongNhan, menuSubPhanCongCongViec);
//
//		menuNhanVien = new MenuItem(iconNhanVien, SystemConstants.BUNDLE.getString("menu.nhanVien"), null,
//				menuSubCapNhatNhanVien, menuSubTimNhanVien, menuSubChucVu, menuSubChamCongNhanVien,
//				menuSubTinhLuongNhanVien);
//		menuSanPham = new MenuItem(iconSanPham, SystemConstants.BUNDLE.getString("menu.sanPham"), null,
//				menuSubCapNhatSanPham, menuSubTimSanPham, menuSubPhanCongDoan);
//		menuPhongBan = new MenuItem(iconPhongBan, SystemConstants.BUNDLE.getString("menu.phongBan"), null,
//				menuSubCapNhatPhongBan, menuSubTimPhongBan);
//		menuToNhom = new MenuItem(iconToNhom, SystemConstants.BUNDLE.getString("menu.toNhom"), null,
//				menuSubCapNhatToNhom, menuSubTimToNhom);
//		menuBaoCaoThongKe = new MenuItem(iconBaoCaoThongKe, SystemConstants.BUNDLE.getString("menu.baoCaoThongKe"),
//				null, menuSubThongKeTongQuat, menuSubThongKeLuongNhanVien, menuSubThongKeLuongCongNhan);
//		addMenu(menuTrangChu, menuHopDong, menuNhanVien, menuCongNhan, menuSanPham, menuPhongBan, menuToNhom,
//				menuBaoCaoThongKe, menuHeThong);
		menuChoose = menuTrangChu;
		clickMenu(menuTrangChu);
		showForm(new TrangChuForm());
	}

	private void clickMenu(MenuItem item) {
		Color noChoose = new Color(8, 92, 255);
		ImageIcon iconCheck = new ImageIcon("src/main/resources/icon/menu/ic_check.png");
		ImageIcon iconUnCheck = new ImageIcon("src/main/resources/icon/menu/ic_uncheck.png");

		// Nếu menu đang đc chọn là ... thì setup lại
		if (menuChoose.equals(menuSubCapNhatNhanVien)) {
			menuSubCapNhatNhanVien.setBackground(noChoose);
		} else if (menuChoose.equals(menuSubTimNhanVien)) {
			menuSubTimNhanVien.setBackground(noChoose);
		} else if (menuChoose.equals(menuSubChamCongNhanVien)) {
			menuSubChamCongNhanVien.setBackground(noChoose);
		} else if (menuChoose.equals(menuSubTinhLuongNhanVien)) {
			menuSubTinhLuongNhanVien.setBackground(noChoose);
		} else if (menuChoose.equals(menuSubCapNhatCongNhan)) {
			menuSubCapNhatCongNhan.setBackground(noChoose);
		} else if (menuChoose.equals(menuSubTimCongNhan)) {
			menuSubTimCongNhan.setBackground(noChoose);
		} else if (menuChoose.equals(menuSubChamCongCongNhan)) {
			menuSubChamCongCongNhan.setBackground(noChoose);
		} else if (menuChoose.equals(menuSubTinhLuongCongNhan)) {
			menuSubTinhLuongCongNhan.setBackground(noChoose);
		} else if (menuChoose.equals(menuSubPhanCongCongViec)) {
			menuSubPhanCongCongViec.setBackground(noChoose);
		} else if (menuChoose.equals(menuSubCapNhatSanPham)) {
			menuSubCapNhatSanPham.setBackground(noChoose);
		} else if (menuChoose.equals(menuSubTimSanPham)) {
			menuSubTimSanPham.setBackground(noChoose);
		} else if (menuChoose.equals(menuSubCapNhatPhongBan)) {
			menuSubCapNhatPhongBan.setBackground(noChoose);
		} else if (menuChoose.equals(menuSubTimPhongBan)) {
			menuSubTimPhongBan.setBackground(noChoose);
		} else if (menuChoose.equals(menuSubThongKeTongQuat)) {
			menuSubThongKeTongQuat.setBackground(noChoose);
		} else if (menuChoose.equals(menuSubThongKeLuongNhanVien)) {
			menuSubThongKeLuongNhanVien.setBackground(noChoose);
		} else if (menuChoose.equals(menuSubThongKeLuongCongNhan)) {
			menuSubThongKeLuongCongNhan.setBackground(noChoose);
		} else if (menuChoose.equals(menuSubPhanCongDoan)) {
			menuSubPhanCongDoan.setBackground(noChoose);
		} else if (menuChoose.equals(menuSubChucVu)) {
			menuSubChucVu.setBackground(noChoose);
		} else if (menuChoose.equals(menuTrangChu)) {
			menuTrangChu.setBackground(noChoose);
		} else if (menuChoose.equals(menuHopDong)) {
			menuHopDong.setBackground(noChoose);
		} else if (menuChoose.equals(menuSubTayNghe)) {
			menuSubTayNghe.setBackground(noChoose);
		} else if (menuChoose.equals(menuSubTimToNhom)) {
			menuSubTimToNhom.setBackground(noChoose);
		} else if (menuChoose.equals(menuSubCapNhatToNhom)) {
			menuSubCapNhatToNhom.setBackground(noChoose);
		}

		if (!item.equals(menuBaoCaoThongKe) && !item.equals(menuHopDong) && !item.equals(menuTrangChu)
				&& !item.equals(menuSanPham) && !item.equals(menuCongNhan) && !item.equals(menuNhanVien)
				&& !item.equals(menuPhongBan) && !item.equals(menuToNhom) && !item.equals(menuHeThong)) {
			item.setIcon(iconCheck);
		}

		if (item.equals(menuBaoCaoThongKe) || item.equals(menuHopDong) || item.equals(menuTrangChu)
				|| item.equals(menuSanPham) || item.equals(menuCongNhan) || item.equals(menuNhanVien)
				|| item.equals(menuPhongBan) || item.equals(menuToNhom) || item.equals(menuHeThong)) {
			menuSubCapNhatNhanVien.setIcon(iconUnCheck);
			menuSubTimNhanVien.setIcon(iconUnCheck);
			menuSubChamCongNhanVien.setIcon(iconUnCheck);
			menuSubTinhLuongNhanVien.setIcon(iconUnCheck);
			menuSubCapNhatCongNhan.setIcon(iconUnCheck);
			menuSubTimCongNhan.setIcon(iconUnCheck);
			menuSubChamCongCongNhan.setIcon(iconUnCheck);
			menuSubTinhLuongCongNhan.setIcon(iconUnCheck);
			menuSubPhanCongCongViec.setIcon(iconUnCheck);
			menuSubCapNhatSanPham.setIcon(iconUnCheck);
			menuSubTimSanPham.setIcon(iconUnCheck);
			menuSubCapNhatPhongBan.setIcon(iconUnCheck);
			menuSubTimPhongBan.setIcon(iconUnCheck);
			menuSubThongKeTongQuat.setIcon(iconUnCheck);
			menuSubThongKeLuongNhanVien.setIcon(iconUnCheck);
			menuSubThongKeLuongCongNhan.setIcon(iconUnCheck);
			menuSubPhanCongDoan.setIcon(iconUnCheck);
			menuSubCapNhatToNhom.setIcon(iconUnCheck);
			menuSubTimToNhom.setIcon(iconUnCheck);
			menuSubChucVu.setIcon(iconUnCheck);
			menuSubTayNghe.setIcon(iconUnCheck);
		} else {
			menuSubCapNhatNhanVien.setIcon(iconUnCheck);
			menuSubTimNhanVien.setIcon(iconUnCheck);
			menuSubChamCongNhanVien.setIcon(iconUnCheck);
			menuSubTinhLuongNhanVien.setIcon(iconUnCheck);
			menuSubCapNhatCongNhan.setIcon(iconUnCheck);
			menuSubTimCongNhan.setIcon(iconUnCheck);
			menuSubChamCongCongNhan.setIcon(iconUnCheck);
			menuSubTinhLuongCongNhan.setIcon(iconUnCheck);
			menuSubPhanCongCongViec.setIcon(iconUnCheck);
			menuSubCapNhatSanPham.setIcon(iconUnCheck);
			menuSubTimSanPham.setIcon(iconUnCheck);
			menuSubCapNhatPhongBan.setIcon(iconUnCheck);
			menuSubTimPhongBan.setIcon(iconUnCheck);
			menuSubThongKeTongQuat.setIcon(iconUnCheck);
			menuSubThongKeLuongNhanVien.setIcon(iconUnCheck);
			menuSubThongKeLuongCongNhan.setIcon(iconUnCheck);
			menuSubPhanCongDoan.setIcon(iconUnCheck);
			menuSubCapNhatToNhom.setIcon(iconUnCheck);
			menuSubTimToNhom.setIcon(iconUnCheck);
			menuSubChucVu.setIcon(iconUnCheck);
			menuSubTayNghe.setIcon(iconUnCheck);
			item.setIcon(iconCheck);
		}
		item.setBackground(new Color(43, 81, 195));
		menuChoose = item;
	}

	private void addMenu(MenuItem... menu) {

		for (int i = 0; i < menu.length; i++) {
			menus.add(menu[i]);
			ArrayList<MenuItem> subMenu = menu[i].getSubMenu();
			for (MenuItem m : subMenu) {
				addMenu(m);
			}
		}
		menus.revalidate();
	}

	public void showForm(Component component) {
		panelBody.removeAll();
		panelBody.add(component);
		panelBody.repaint();
		panelBody.revalidate();
	}

	public void login() {

	}

	public void logout() {
		int choose = JOptionPane.showConfirmDialog(null, SystemConstants.BUNDLE.getString("app.thongBaoDangXuat"),
				SystemConstants.BUNDLE.getString("app.dangXuat"), JOptionPane.YES_NO_OPTION);
		if (choose == JOptionPane.OK_OPTION) {
			AuthUtils.logout();
			new DangNhapForm().setVisible(true);
			dispose();
		}
	}

	public void changePassword() {
		new DoiMatKhauForm(new ISendResponse() {

			@Override
			public void confirm(boolean status) {
				if (status) {
					AuthUtils.logout();
					new DangNhapForm().setVisible(true);
					dispose();
				}
			}
		}).setVisible(true);
	}

	public void exit() {
		int status = JOptionPane.showConfirmDialog(this, SystemConstants.BUNDLE.getString("app.thongBaoThoatUngDung"),
				SystemConstants.BUNDLE.getString("app.thoat"), JOptionPane.YES_NO_OPTION);
		if (status == JOptionPane.OK_OPTION) {
			dispose();
		}
	}

	public void language() {
		if (SystemConstants.LANGUAGE == 0) {
			LanguageUtils.saveLanguage(1);
		} else if (SystemConstants.LANGUAGE == 1) {
			LanguageUtils.saveLanguage(0);
		}
		SystemConstants.initLanguage();
	}

}
