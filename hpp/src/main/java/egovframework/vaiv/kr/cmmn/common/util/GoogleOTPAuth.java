package egovframework.vaiv.kr.cmmn.common.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base32;
import org.springframework.stereotype.Service;

/**
 * GoogleOTP 인증 Util : google OTP 인증 관련 util
 * @category 공통
 * @author jo
 * @since 2021-03-02
 * @version v1.0
 * @see
 * <pre>
 *  ******************************************
 *  수정 이력
 *  
 *  수정일                  수정자                 수정내용
 *  ------------------------------------------
 *  2021.03.02    jo           최초 등록
 * 
 * 
 *  ******************************************
 *  Copyright 2021 VAIV Co.
 *  All rights reserved
 * </pre>
 */
@Service("GoogleOTPAuth")
public class GoogleOTPAuth {
//	public HashMap<String, String> keyGenerate(String userName, String hostName) {
	public HashMap<String, String> keyGenerate(String userName) {
		HashMap<String, String> returnMap = new HashMap<>();
		
//		byte[] buffer = new byte[secretSize + numOfScratchCodes * scratchCodeSize];
		byte[] buffer = new byte[5 + 5 * 5];
		new Random().nextBytes(buffer);
		Base32 codec = new Base32();
//		byte[] secretKey = Arrays.copyOf(buffer, secretSize);
		byte[] secretKey = Arrays.copyOf(buffer, 10);
		byte[] bEncodedKey = codec.encode(secretKey);
		
		String encodedkey = new String(bEncodedKey);
		String url = getQRBarcodeURL(userName, "vaiv.kr", encodedkey);
		returnMap.put("encodedkey", encodedkey);
		returnMap.put("url", url);
		
		return returnMap;
	}
	
	private String getQRBarcodeURL(String user, String host, String secret) {
		String format = "http://chart.apis.google.com/chart?cht=qr&amp;chs=300x300&amp;chl=otpauth://totp/%s@%s%%3Fsecret%%3D%s&amp;chld=H|0";
		
		return String.format(format, user, host, secret);
	}
	
	public boolean check_code(String secret, long code, long t) throws InvalidKeyException, NoSuchAlgorithmException {
		Base32 codec = new Base32();
		byte[] decodedKey = codec.decode(secret);
		
		int window = 3;
		for(int i = -window; i <= window; i++) {
			long hash = verify_code(decodedKey, t + i);
			if(hash == code) {
				return true;
			}
		}
		
		return false;
	}
	
	private static int verify_code(byte[] key, long t) throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] data = new byte[8];
        long value = t;
        for (int i = 8; i-- > 0; value >>>= 8) {
            data[i] = (byte) value;
        }
 
        SecretKeySpec signKey = new SecretKeySpec(key, "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(signKey);
        byte[] hash = mac.doFinal(data);
 
        int offset = hash[20 - 1] & 0xF;
 
        // We're using a long because Java hasn't got unsigned int.
        long truncatedHash = 0;
        for (int i = 0; i < 4; ++i) {
            truncatedHash <<= 8;
            // We are dealing with signed bytes:
            // we just keep the first byte.
            truncatedHash |= (hash[offset + i] & 0xFF);
        }
 
        truncatedHash &= 0x7FFFFFFF;
        truncatedHash %= 1000000;
 
        return (int) truncatedHash;
    }
}
