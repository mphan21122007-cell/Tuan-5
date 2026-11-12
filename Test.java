package BT.TUAN5;
import java.util.Scanner;
public class Main {
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		CDList ql = new CDList();
		int luaChon = -1;
		
		do {
			System.out.println("\n			MENU QUAN LY CD			  ");
			System.out.println("\n 1.Them CD(tu mo rong mang khi day) ");
			System.out.println("\n 2.Xoa CD theo ma 				  ");
			System.out.println("\n 3.Sua/Cap nhat CD theo ma		  ");
			System.out.println("\n 4.Tim kiem theo ma				  ");
			System.out.println("\n 5.Tim theo tua - TIEN TO (ben trai)");
			System.out.println("\n 6.Tim theo tua - HAU TO (ben phai) ");
			System.out.println("\n 7.Tim theo tua - GAN GIONG (chua)  ");
			System.out.println("\n 8.Hien thi danh sach				  ");
			System.out.println("\n 9.Thong ke						  ");
			System.out.println("\n 10.Sap xep giam dan theo gia thanh ");
			System.out.println("\n 11.Sap xep tang dan theo tua CD	  ");
			System.out.println("\n 0. Thoat						      ");
			System.out.print("Chon chuc nang");
			
			try {
				luaChon = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Vui long nhap so!");
				continue;
			}
			
			switch (luaChon) {
				case 1: themCD(ql); break;
				case 2: xoaCD(ql); break;
				case 3: capNhatCD(ql); break;
				case 4: timTheoMa(ql); break;
				case 5: timTheoTienTo(ql); break;
				case 6: timTheoHauTo(ql); break;
				case 7: timGanGiong(ql); break;
				case 8: ql.hienThiDanhSach(); break;
				case 9: thongKe(ql); break;
				case 10:ql.sapXepGiamDanTheoGia(); ql.hienThiDanhSach(); break;
				case 11:ql.sapXepTangDanTheoTua(); ql.hienThiDanhSach(); break;
				case 0: System.out.println("Tam biet! ");
				default: System.out.println("Lua chon khong hop le");
			} while (luaChon != 0);
			
			sc.close();
		}
	}
}
