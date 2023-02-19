package javaSDET;

import java.util.ArrayList;
import java.util.HashSet;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_01_Data_Type {

	public static void main(String[] args) {
		// 1: Khai báo trước - khởi tạo sau
			String fullname; // Kiểu dữ liệu - tên biến
			fullname= "Ha Noi"; // Gán dữ liệu = giá trị phù hợp
			System.out.println(fullname);
			
		// 2: Khai báo + khởi tạo cùng lúc
			String addressName = "1994 Đường Láng";
			System.out.println(addressName);
		
		// Kiểu dữ liệu nguyên thủy: Primitive Data Type
			// Ký tự: char
				char c = 'A'; // Duy nhất 1 ký tự: Dùng dấu nháy đơn
				System.out.println(c);
		
			// Số nguyên: byte short int long
			// Không dấu - không thập phân
				byte bNumber = 127;
				
				short sNumber = 32767;
				
				int iNumber = 1223123;
				
				long lNumber = 466445454;
				
			// Số thực: float double
			// Có dấu - có thập phân
				float fNumber = 15.8f;
				
				double dNumebr =15.8d;
								
			// Logic: boolean
				boolean status = true;
				status = false; // Cập nhất lại giá trị mới
				
		// Kiểu dữ liệu tham chiếu: Reference Type
			// Chuỗi ký tự: String
				String cityName = "Nam Định";
				System.out.println(cityName);
				
				cityName = "Thái Bình";
				System.out.println(cityName);
				
			// Class
				FirefoxDriver fDriver;
				
			// Interface
				WebDriver driver;
				
			// Collection: Set List Queue
			// HashSet/ LinkedHashSet/ TreeSet
				HashSet hashSet = new HashSet<Object>();
				
			// ArrayList / LinkedList/ Vector
				ArrayList arrayList = new ArrayList<>();
				
			// Deque/...
			// Map: HashMap/ HashTable
				
			// Object
				Object number;
				
			// Array
				String [] student = {"Nguyen A", "Tran B"};
				Integer [] point = {15, 20, 30};

	}

}
