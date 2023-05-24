package com.simplilearn.storageservice.util;

import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ImageUtils {

	public static byte[] compressImage(byte[] bytes) {
		Deflater deflater = new Deflater();
		deflater.setLevel(Deflater.BEST_COMPRESSION);
		deflater.setInput(bytes);
		deflater.finish();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(bytes.length);
		byte[] tmp = new byte[4*1024];
		while(!deflater.finished()) {
			int size = deflater.deflate(tmp);
			outputStream.write(tmp,0,size);
		}
		try {
			outputStream.close();
		}catch (Exception e) {
		}
		return outputStream.toByteArray();
	}

	public static byte[] decompressImage(byte[] imageData) {
		Inflater inflater = new Inflater();
		inflater.setInput(imageData);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(imageData.length);
		byte[] tmp = new byte[4*1024];
		try {
			while(!inflater.finished()) {
				int size = inflater.inflate(tmp);
				outputStream.write(tmp,0,size);
			}
		}
		catch (Exception e) {
		}
		return outputStream.toByteArray();
	}


}
