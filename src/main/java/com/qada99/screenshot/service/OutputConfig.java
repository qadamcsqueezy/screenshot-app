package com.qada99.screenshot.service;

import com.qada99.screenshot.config.enumeration.OutputType;
import com.qada99.screenshot.config.enumeration.PageOrientation;

public class OutputConfig {

	private PageOrientation pageOrientation;
	private OutputType type;
	private float padding;
	private boolean keepImage;

	public OutputConfig() {
		super();

	}

	public OutputConfig(PageOrientation pageOrientation, OutputType type, float padding, boolean keepImage) {
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

	public float getPadding() {
		return padding;
	}

	public void setPadding(float padding) {
		this.padding = padding;
	}

	public boolean isKeepImage() {
		return keepImage;
	}

	public void setKeepImage(boolean keepImage) {
		this.keepImage = keepImage;
	}

}
