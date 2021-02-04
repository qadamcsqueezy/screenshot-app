package com.qada99.screenshot.service;

import com.qada99.screenshot.config.enumeration.OutputType;
import com.qada99.screenshot.config.enumeration.PageOrientation;

public class OutputConfig {

	private PageOrientation pageOrientation;
	private OutputType type;
	private double padding;
	private boolean keepImage;

	public OutputConfig() {
		super();

	}

	public OutputConfig(PageOrientation pageOrientation, OutputType type, double padding, boolean keepImage) {
		super();
		this.pageOrientation = pageOrientation;
		this.type = type;
		this.padding = padding;
		this.keepImage = keepImage;
	}

	public PageOrientation getPageOrientation() {
		return pageOrientation;
	}

	public void setPageOrientation(PageOrientation pageOrientation) {
		this.pageOrientation = pageOrientation;
	}

	public OutputType getType() {
		return type;
	}

	public void setType(OutputType type) {
		this.type = type;
	}

	public double getPadding() {
		return padding;
	}

	public void setPadding(double padding) {
		this.padding = padding;
	}

	public boolean isKeepImage() {
		return keepImage;
	}

	public void setKeepImage(boolean keepImage) {
		this.keepImage = keepImage;
	}

}
