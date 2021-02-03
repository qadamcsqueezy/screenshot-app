package com.qada99.screenshot.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.qada99.screenshot.config.enumeration.OutputType;

public class FileGenerator {

	private PdfGenerator pdfGenarator;

	public FileGenerator() {
		super();
		this.pdfGenarator = new PdfGenerator();

	}

	public void generate(List<File> images, String output, OutputType outputType) throws IOException {

		if (outputType == OutputType.PDF) {
			pdfGenarator.generate(images, output);
		}

	}
}
