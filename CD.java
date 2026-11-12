package BT.TUAN5;

public class CD {

	private int maCD;
	private String tuaCD;
	private int soBaiHat;
	private double giaThanh;
	
	public static final int MA_CD_MAC_DINH = 999999;
	public static final String TUA_CD_MAC_DINH = "chua xac dinh";
	
	public CD() {
		this.maCD = MA_CD_MAC_DINH;
		this.tuaCD= TUA_CD_MAC_DINH;
		this.soBaiHat = 1;
		this.giaThanh=1.0;
	}
	
	public CD(int maCD, String tuaCD, int soBaiHat, double giaThanh) {
		setMaCD(maCD);
		setTuaCD(tuaCD);
		setSoBaiHat(soBaiHat);
		setGiaThanh(giaThanh);
	}
	
	public int getMaCD() { return maCD; }
	public String getTuaCD() { return tuaCD;}
	public int getSoBaiHat() { return soBaiHat; }
	public double getGiaThanh() {  return giaThanh; }
	
	public void setMaCD(int maCD) {
		if(maCD <= 0) 
			throw new IllegalArgumentException("Loi: Ma CD phai > 0");
		this.maCD = maCD;
	}
	
	public void setTuaCD(String tuaCD) {
		if(tuaCD == null || tuaCD.trim().isEmpty())
			throw new IllegalArgumentException("Loi: Tua CD khong duoc rong");
		this.tuaCD=tuaCD;
	}
	
	public void setSoBaiHat(int soBaiHat) {
		if(soBaiHat <= 0) 
			throw new IllegalArgumentException("Loi: So bai hat phai > 0");
		this.soBaiHat=soBaiHat;
	}
	
	public void setGiaThanh(double giaThanh) {
		if(giaThanh <= 0)
			throw new IllegalArgumentException("Loi: Gia thanh phai > 0");
		this.giaThanh=giaThanh;
	}
	
	@Override
	public String toString() {
		return String.format(
				"| %-10d | %-25s | %-12d | %15.2f |",
				maCD,tuaCD,soBaiHat,giaThanh);
	}
}


public class CDList {
	private CD[] danhSachCD;
	private int soLuong;
	private int khaNang;
	
	private static final int KICH_THUOC_BAN_DAU = 5;
	
	public CDList() {
		khaNang = KICH_THUOC_BAN_DAU;
		danhSachCD = new CD[khaNang];
		soLuong = 0;
	}
	
	public int laySoLuong() { return soLuong; }
	public CD layCDTaiViTri(int n) { return danhSachCD[i]; }
	
	private void moRongMang() {
		int kichThuocMoi = khaNang * 2;
		CD[] mangMoi = new CD[kichThuocMoi];
		for(int i = 0; i <soLuong; i++) 
			mangMoi[i] = danhSachCD[i];
		danhSachCD = mangMoi;
		khaNang = kichThuocMoi;
		System.out.println("Da mo rong mang len" + khaNang + "phan tu.");
	}
	
	public int timViTriTheoMa(int maCD) {
		for(int i = 0; i<soLuong ; i++) 
			if(danhSachCD[i].getMaCD() == maCD) return i;
		return -1;
	}
	
	public boolean themCD(CD cd) {
		if(timViTriTheoMa(cd.getMaCD()) != -1) {
			System.out.println("Loi: Ma CD " + cd.getMaCD() + "da ton tai!");
			return false;
		}
		if(soLuong == khaNang) moRongMang();
		danhSachCD[soLuong] = cd;
		soLuong++;
		return true;
	}
	
	public boolean xoaCD(int maCD) {
		int viTri = timViTriTheoMa(maCD);
		if (viTri ==  -1) {
			System.out.println("Khong tim thay CD ma "+ maCD);
			return false;
		}
		for(int i = viTri; i<soLuong - 1; i++)
			danhSachCD[i] = danhSachCD[i + 1];
		danhSachCD[soLuong - 1]=null;
		soLuong--;
		return true;
	}
	
	public boolean capNhatCD(int maCD, String tuaMoi, int soBaiMoi, double giaMoi) {
		int viTri = timViTriTheoMa(maCD);
		if(viTri == -1) {
			System.out.println("Khong tim thay CD ma " + maCD);
			return false;
		}
		CD cd = danhSachCD[viTri];
		cd.setTuaCD(tuaMoi);
		cd.setSoBaiHat(soBaiMoi);
		cd.setGiaThanh(giaMoi);
		return true;
	}
	
	public CD timTheoMa(int maCD) {
		int viTri = timViTriTheoMa(maCD);
		return (viTri == -1) ? null : danhSachCD[viTri];
	}
	
	public CDList timTheoTienTo(String tuKhoa) {
		CDList ketQua = new CDList();
		String tk = tuKhoa.toLowerCase();
		for(int i = 0; i< soLuong; i++) {
			if(danhSachCD[i].getTuaCD().toLowerCase().startsWith(tk)) {
				ketQua.themCD(danhSachCD[i]);
			}
		}
		return ketQua;
	}
	
	public CDList timTheoHauTo(String tuKhoa) {
		CDList ketQua = new CDList();
		String tk = tuKhoa.toLowerCase();
		for(int i = 0; i< soLuong; i++) {
			if(danhSachCD[i].getTuaCD().toLowerCase().endsWith(tk)) {
				ketQua.themCD(danhSachCD[i]);
			}
		}
		return ketQua;
	}
	
	public CDList timGanGiong(String tuKhoa) {
		CDList ketQua = new CDList();
		String tk = tuKhoa.toLowerCase();
		for(int i = 0; i< soLuong; i++) {
			if(danhSachCD[i].getTuaCD().toLowerCase().contains(tk)) {
				ketQua.themCD(danhSachCD[i]);
			}
		}
		return ketQua;
	}
	
	public int tinhSoLuongCD() { return soLuong; }
	
	public double tinhTongGiaThanh() {
		double tong = 0;
		for(int i=0;i<soLuong ;i++) 
			tong+= danhSachCD[i].getGiaThanh();
		return tong;
	}
	
	public double tinhGiaTrungBinh() {
		return (soLuong == 0) ? 0 : tinhTongGiaThanh() / soLuong;
	}
	
	public CD timCDDatNhat() {
		if(soLuong == 0) return null;
		CD max = danhSachCD[0];
		for(int i = 1;i<soLuong; i++)
			if(danhSachCD[i].getGiaThanh() > max.getGiaThanh())
				max = danhSachCD[i];
		return max;
	}
	
	public CD timCDReNhat() {
		if(soLuong == 0) return null;
		CD min = danhSachCD[0];
		for(int i = 1;i<soLuong; i++)
			if(danhSachCD[i].getGiaThanh() < min.getGiaThanh())
				min = danhSachCD[i];
		return min;
	}
	
	public void hienThiDanhSach() {
		if(soLuong == 0) {
			System.out.println("Danh sach CD trong");
			return;
		}
		System.out.println("\n+----------+------------------------+-----------+----------------+");
		System.out.println("|   Ma CD   |          Tua CD       | So bai hat |  Gia thanh  |");
		System.out.println("\n+----------+------------------------+-----------+----------------+");
		for (int i = 0; i< soLuong ;i++) 
			System.out.println(danhSachCD[i]);
		System.out.println("\n+----------+------------------------+-----------+----------------+");
	}
	
	public void sapXepGiamDanTheoGia() {
		for(int i = 0; i< soLuong - 1;i++) 
			for(int j= i+1;j<soLuong; j++) 
				if(danhSachCD[i].getGiaThanh() < danhSachCD[i].getGiaThanh()) {
					CD temp = danhSachCD[i];
					danhSachCD[i] = danhSachCD[j];
					danhSachCD[j] = temp;
				}
		System.out.println("Da sap xep giam dan theo gia thanh");	
	}
	
	public void sapXepTangDanTheoTua() {
		for(int i = 0; i< soLuong - 1;i++) 
			for(int j= i+1;j<soLuong; j++) 
				if(danhSachCD[i].getTuaCD().compareToIgnoreCase(danhSachCD[j].getTuaCD()) >0) {
					CD temp = danhSachCD[i];
					danhSachCD[i] = danhSachCD[j];
					danhSachCD[j] = temp;
				}
		System.out.println("Da sap xep tang dan theo tua CD");	
	}
}
	
