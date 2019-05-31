package com.lfchaim.tesseractdemo;

import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.ImageIOHelper;

public class OCRTest {

	public static void main(String[] args) {
		//test1();
		testRect();
	}

	private static void test1() {
		Tesseract tesseract = new Tesseract();
		try {
			// tesseract.setDatapath("D:/DataScienceCollection/Jars/tessdata");
			tesseract.setDatapath("D:/tmp/Tess4J/tessdata");
			tesseract.setLanguage("por");
			String text = tesseract.doOCR(new File("D:/tmp/img/CNH/CNH Fernando Cinza 20181209.png"));
			System.out.print(text);
		} catch (TesseractException e) {
			e.printStackTrace();
		}
	}
	
	private static void testRectScaled() {
		Tesseract tesseract = new Tesseract();
		try {
			String fileStr = "D:/tmp/img/CNH/CNH Fernando Cinza 20181209.png";
			File file = new File(fileStr);
			//380x45+220+170
			int xsize = 220;
			int ysize = 170;
			BufferedImage bufImage = null;
			ByteBuffer buf = null;
			try {
				bufImage = ImageIO.read(file);
				IIOImage image=new IIOImage(bufImage,null,null);
				buf = ImageIOHelper.getImageByteBuffer(image);
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			//Rectangle rect = new Rectangle(0, 0, 380, 45); // define an equal or smaller region of interest on the image
			int x = 442;
			int y = 333;
			int xPlus = 420;
			int yPlus = 141;
			Rectangle rect = new Rectangle(442,333,420,141);
			int bpp = 8; //Gray=8, RGB=24
			
			// Imagem tratada
			int w = bufImage.getWidth();
			int h = bufImage.getHeight();
			final int SCALE = 2;
			BufferedImage after = new BufferedImage(SCALE * w, SCALE * h, BufferedImage.TYPE_INT_ARGB);
			System.out.println("x: "+bufImage.getWidth()+" y: "+bufImage.getHeight()+" x2: "+after.getWidth()+" y2: "+after.getHeight());
			
			x = SCALE * x;
			y = SCALE * y;
			xPlus = SCALE * xPlus;
			yPlus = SCALE * yPlus;
			rect = new Rectangle(x,y,xPlus,yPlus);
			
			/*
			Tesseract in = new ReadImageText().getTesseractInstance("C:/Program Files (x86)/Tesseract-OCR/tessdata/", "eng");
			try {
				String resultText = in.doOCR(xsize, ysize, buf, "C:/Program Files (x86)/Tesseract-OCR/tessdata/hin.traineddata", rect, bpp);
				log.info("resultText: {}", resultText);
			} catch (TesseractException e) {
				e.printStackTrace();
			}
			*/
			
			// tesseract.setDatapath("D:/DataScienceCollection/Jars/tessdata");
			tesseract.setDatapath("D:/tmp/Tess4J/tessdata");
			tesseract.setLanguage("por");
			
			//String text = tesseract.doOCR(new File(fileStr),rect);
			//String text = tesseract.doOCR(after,rect);
			String text = tesseract.doOCR(after);
			
			System.out.print(text);
		} catch (TesseractException e) {
			e.printStackTrace();
		}
	}

	private static void testRect() {
		Tesseract tesseract = new Tesseract();
		try {
			String fileStr = "D:/tmp/img/CNH/CNH Fernando Cinza 20181209.png";
			File file = new File(fileStr);
			
			Rectangle rect = new Rectangle(442,333,420,141);
			int bpp = 8; //Gray=8, RGB=24
			
			// tesseract.setDatapath("D:/DataScienceCollection/Jars/tessdata");
			tesseract.setDatapath("D:/tmp/Tess4J/tessdata");
			tesseract.setLanguage("por");
			
			//String text = tesseract.doOCR(new File(fileStr),rect);
			String text = tesseract.doOCR(file,rect);
			
			System.out.print(text);
		} catch (TesseractException e) {
			e.printStackTrace();
		}
	}
}
