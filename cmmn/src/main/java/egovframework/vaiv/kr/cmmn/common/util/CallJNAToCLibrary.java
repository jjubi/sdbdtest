package egovframework.vaiv.kr.cmmn.common.util;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.Pointer;
import com.sun.jna.win32.StdCallLibrary;

public class CallJNAToCLibrary {
	/**
	 * C to Java 매칭되는 타입
	 * 		C	size					|		JAVA	
	 *    char (8-bit Integer)			|		byte	
	 *    short (16-bit Integer)		|		short	
	 *    wchar_t (16/32-bit character)	|		char	
	 *    int (32-bit Integer)			|		int		
	 *    int (boolean value)			|		boolean	
	 *    long (32/64-bit Integer)		|		NativeLong
	 *    logn long (64-bit Integer)	|		long
	 *    float (32-bit FP)				|		float
	 *    double (64-bit FP)			|		double
	 *    char* (C string)				|		String
	 *    void* (pointer)				|		Pointer		
	 * */
	public interface CInterface extends Library {
		//사용할 메소드 정의하기 
		public void printf(String format, Object... args);
	}
	
	//ex : //
//	public interface User32 extends StdCallLibrary {
//        User32 INSTANCE = (User32) Native.loadLibrary("user32", User32.class);
//        interface WNDENUMPROC extends StdCallCallback {
//        	boolean callback(Pointer hWnd, Pointer arg);
//        }
//        boolean EnumWindows(WNDENUMPROC lpEnumFunc, Pointer arg);
//        int GetWindowTextA(Pointer hWnd, byte[] lpString, int nMaxCount);
//	}
	
	public static void main(String[] args) {
		//라이브러리 인터페이스 호출 방식들 
		//1. System.loadLibrary("라이브러리 이름");
		//2. System.load("Path 설정");
		//3. CInterface ci = Native.loadLibrary("msvcrt", CInterface.class);
		//4. 
		CInterface ci = Native.loadLibrary((Platform.isWindows() ? "msvcrt" : "c"), CInterface.class);
		ci.printf("Argument : %s\n", "Hi Argument");
		
		
		
		//ex : //
//		final User32 user32 = User32.INSTANCE;
//        user32.EnumWindows(new User32.WNDENUMPROC() {
//        	int count;
//        	public boolean callback(Pointer hWnd, Pointer userData) {
//        		byte[] windowText = new byte[512];
//	            user32.GetWindowTextA(hWnd, windowText, 512);
//	            String wText = Native.toString(windowText);
//	            wText = (wText.isEmpty()) ? "" : "; text: " + wText;
//	            System.out.println("Found window " + hWnd + ", total "+ ++count + wText);
//	            return true;
//	       }
//        }, null);
	}
}
