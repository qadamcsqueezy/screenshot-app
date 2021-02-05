package com.qada99.screenshot.service;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import com.qada99.screenshot.config.enumeration.PageOrientation;

public class PdfGenerator {

	public void generate(Settings settings, String outPut) throws IOException {
		PDDocument document = new PDDocument();
		for (File file : settings.getImages()) {
			PDPage page;
			if (settings.getOutputConfig().getPageOrientation() == PageOrientation.HORIZONTAL) {
				float POINTS_PER_INCH = 72;
				float POINTS_PER_MM = 1 / (10 * 2.54f) * POINTS_PER_INCH;
				page = new PDPage(new PDRectangle(297 * POINTS_PER_MM, 210 * POINTS_PER_MM));
			}
			else
				page = new PDPage();
			document.addPage(page);
			PDPageContentStream contentStream = new PDPageContentStream(document, page);
			PDImageXObject image = PDImageXObject.createFromFile(file.getAbsolutePath(), document);
			float scale = 1f;
//			contentStream.drawImage(image, 0, 0, image.getWidth() * scale, image.getHeight() * scale);
			float padding = settings.getOutputConfig().getPadding();
			contentStream.drawImage(image, padding, padding, page.getMediaBox().getWidth() - padding * 2,
					page.getMediaBox().getHeight() - padding * 2);

			;
			contentStream.close();
		}

		document.save(outPut);
		document.close();
	}
}
