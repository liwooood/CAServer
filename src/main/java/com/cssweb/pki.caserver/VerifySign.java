package com.cssweb.pki.caserver;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;



public class VerifySign {

	public void VerifyCryptoAPISign() {
		try {

				/*
			File file = new File(
					"c:/workspace_java/verify/data/signresult.bin");
			int len = (int) file.length();
			byte[] b64 = new byte[len ];
			DataInputStream dis = new DataInputStream(new FileInputStream(file));
			dis.readFully(b64);
			dis.close();
			
			System.out.println(new String(b64, "UTF-8"));
			*/
			String b64 = "gCfWk655JCpiFoqUvbq5RE7yquUxXIC75ivF8DsHIsRnqWTTQwoeVBujgn8O150Fu274Emy1npvfOqPeun75nxBCflynUMMFl0fMpVnE66cbtQV2v1+fow+9M8btHRBCcd1jRA7ibioLH1DBMr9bvJ4V+T/cHRxtzooo4C7HbWY=";
			
			byte[] signData = Base64.decodeBase64(b64);
			System.out.println("签名结果长度" + signData.length);
			
			
			/*
			 * byte[] data = new byte[(int) file.length()];
			 * ByteBuffer bb = ByteBuffer.allocate((int) file.length());
			 * bb.position(0); bb.put(signData);
			 * bb.order(ByteOrder.LITTLE_ENDIAN);
			 * //bb.order(ByteOrder.BIG_ENDIAN); bb.position(0); bb.get(data);
			 */
			
			int signLen =signData.length;
			for (int i = 0; i <signLen  / 2; i++) {
				byte temp = signData[i];
				signData[i] = signData[signLen - i - 1];
				signData[signLen - i - 1] = temp;
			}
			

			
					
					//C=CN, O=CSDC Test, OU=Customers01, CN=C@1@100004121
			CertificateFactory certFactory = CertificateFactory
					.getInstance("X.509");
			FileInputStream fis = new FileInputStream(
					"c:/workspace_java/verify/data/100004121.cer");
			X509Certificate cert = (X509Certificate) certFactory
					.generateCertificate(fis);
			PublicKey publicKey = cert.getPublicKey();
			if (publicKey == null)
				System.out.println("public key is null");

			
			// new org.bouncycastle.jce.provider.BouncyCastleProvider()
			Signature verify = Signature.getInstance("MD5WithRSA");

			// 
			verify.initVerify(publicKey);

			// raw data
			
			// certtest测试原文
			//String rawData = "测试hellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohellohello";
			String rawData = "测试123";
			
			byte[] data = rawData.getBytes("GBK");
			
			String tmp = new String(data, "UTF-8");
			System.out.println(rawData);
			System.out.println(tmp);
			
			verify.update(data);
			//verify.update((byte) 0);

			// 
			boolean bRet = verify.verify(signData);
			if (bRet)
				System.out.println("success");
			else
				System.out.println("error");

		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VerifySign verify = new VerifySign();
		verify.VerifyCryptoAPISign();

	}

}
