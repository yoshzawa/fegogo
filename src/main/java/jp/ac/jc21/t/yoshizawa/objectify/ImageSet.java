package jp.ac.jc21.t.yoshizawa.objectify;

public class ImageSet {
	private boolean isImage;
	private String url;
	private int height;
	private int top;
	private Long[] questionIds;
	
	public ImageSet(Long[] questionIds) {
		setImage(false);
		setQuestionIds(questionIds);
	}	
	
	public ImageSet(
			 String url,
			 int height,
			 int top
			) {
		setImage(true);
		setUrl(url);
		setHeight(height);
		setTop(top);
	}

	
	public ImageSet(Long id) {
		this(singleToArray(id));
	}

	private static Long[] singleToArray(Long id) {
		Long[] list = new Long[1];
		list[0]=id;
		return list;	
	}

	/**
	 * @return the isImage
	 */
	public boolean isImage() {
		return isImage;
	}
	/**
	 * @param isImage the isImage to set
	 */
	public void setImage(boolean isImage) {
		this.isImage = isImage;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	/**
	 * @return the top
	 */
	public int getTop() {
		return top;
	}
	/**
	 * @param top the top to set
	 */
	public void setTop(int top) {
		this.top = top;
	}
	/**
	 * @return the questionIds
	 */
	public Long[] getQuestionIds() {
		return questionIds;
	}
	/**
	 * @param questionIds the questionIds to set
	 */
	public void setQuestionIds(Long[] questionIds) {
		this.questionIds = questionIds;
	}
	

}
