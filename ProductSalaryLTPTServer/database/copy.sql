-- Tạo CSDL rồi import từng table
USE LuongSanPhamV2
GO

INSERT INTO LuongSanPhamV2.dbo.CaLam
SELECT *
FROM LuongSanPham.dbo.CaLam
    GO

INSERT INTO LuongSanPhamV2.dbo.PhongBan
SELECT [SoLuongNhanVien], [TrangThai], [MaPhongBan], [TenPhongBan]
FROM LuongSanPham.dbo.PhongBan
    GO

INSERT INTO LuongSanPhamV2.dbo.ChucVu
SELECT * FROM LuongSanPham.dbo.ChucVu
    GO


INSERT INTO LuongSanPhamV2.dbo.ToNhom
SELECT [SoLuongCongNhan], [TrangThai], [MaToNhom], [TenToNhom] FROM LuongSanPham.dbo.ToNhom
    GO

INSERT INTO LuongSanPhamV2.dbo.TayNghe
SELECT * FROM LuongSanPham.dbo.TayNghe
    GO

INSERT INTO LuongSanPhamV2.dbo.TrinhDo
SELECT * FROM LuongSanPham.dbo.TrinhDo
    GO

--
INSERT INTO LuongSanPhamV2.dbo.SanPham
SELECT [DonGia], [SoCongDoan], [SoLuongTon], [TrangThai], [MaSanPham], [ChatLieu], [DonViTinh], [HinhAnh],[TenSanPham] FROM LuongSanPham.dbo.SanPham
    GO

--
INSERT INTO LuongSanPhamV2.dbo.HopDong
SELECT [NgayBatDau], [NgayKetThuc], [SoTienCoc], [TongTien], [TrangThai], [MaHopDong], [TenHopDong], [TenKhachHang], [YeuCau]
FROM LuongSanPham.dbo.HopDong
    GO

INSERT INTO LuongSanPhamV2.dbo.ChiTietHopDong
SELECT [GiaDatLam], [SoLuong], [MaHopDong], [MaSanPham] FROM LuongSanPham.dbo.ChiTietHopDong
    GO

INSERT INTO LuongSanPhamV2.dbo.NhanVien
SELECT [GioiTinh], [HeSoLuong], [LuongCoSo], [NgaySinh], [NgayVaoLam], [TrangThai], [TroCap], [SoDienThoai], [CCCD], [MaChucVu],
    [MaNhanVien], [MaPhongBan], [MaTrinhDo], [Email], [DiaChi], [HinhAnh], [HoTen] FROM LuongSanPham.dbo.NhanVien
    GO

INSERT INTO LuongSanPhamV2.dbo.CongNhan
SELECT [GioiTinh],[NgaySinh], [NgayVaoLam], [TrangThai],  [TroCap], [SoDienThoai], [CCCD], [MaCongNhan], [MaTayNghe], [MaToNhom],
    [Email], [DiaChi], [HinhAnh], [HoTen] FROM LuongSanPham.dbo.CongNhan
    GO


--
INSERT INTO LuongSanPhamV2.dbo.LuongNhanVien
SELECT [Luong], [LuongThuong], [Nam], [NgayTinhLuong], [Thang], [MaLuong]
FROM LuongSanPham.dbo.LuongNhanVien
    GO


INSERT INTO LuongSanPhamV2.dbo.ChamCongNhanVien
SELECT NgayChamCong, TrangThai, MaCa, MaChamCong, MaNhanVien, MaLuong FROM LuongSanPham.dbo.ChamCongNhanVien
    GO

INSERT INTO LuongSanPhamV2.dbo.CongDoanSanPham
SELECT [GiaCongDoan], [SoLuongCanLam], [ThoiHan], [TrangThai], [MaCongDoan], [MaCongDoanLamTruoc], [MaSanPham], [TenCongDoan]
FROM LuongSanPham.dbo.CongDoanSanPham
    GO


INSERT INTO LuongSanPhamV2.dbo.PhanCongCongNhan
SELECT [NgayPhanCong], [TrangThai], [MaCongDoan], [MaCongNhan], [MaPhanCong]
FROM LuongSanPham.dbo.PhanCongCongNhan
    GO

INSERT INTO LuongSanPhamV2.dbo.LuongCongNhan
SELECT [Luong], [LuongThuong], [Nam], [NgayTinhLuong], [Thang], [MaLuong]
FROM LuongSanPham.dbo.LuongCongNhan
    GO

INSERT INTO LuongSanPhamV2.dbo.ChamCongCongNhan
SELECT [NgayChamCong], [SoLuongHoanThanh], [TrangThai], [MaCa], [MaChamCong], [MaPhanCong], [MaLuong]
FROM LuongSanPham.dbo.ChamCongCongNhan
    GO


INSERT INTO LuongSanPhamV2.dbo.Account
SELECT [TrangThai], [MaNhanVien], [MatKhau], [RoleName], [TaiKhoan]
FROM LuongSanPham.dbo.Account
    GO
