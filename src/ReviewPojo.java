

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ReviewPojo {
	String username, productModelName, productCategory, productPrice,
			retailerName, retailerZip, retailerCity, retailerState,
			productOnSale, manufactureName, manufactureRebate, userId, userAge,
			userGender, userOccupation, reviewRating, reviewDate, reviewText, _id;

	public String get_id() {
		return _id;
	}

	@JsonIgnore
	public void set_id(String _id) {
		this._id = _id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getProductModelName() {
		return productModelName;
	}

	public void setProductModelName(String productModelName) {
		this.productModelName = productModelName;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public String getRetailerName() {
		return retailerName;
	}

	public void setRetailerName(String retailerName) {
		this.retailerName = retailerName;
	}

	public String getRetailerZip() {
		return retailerZip;
	}

	public void setRetailerZip(String retailerZip) {
		this.retailerZip = retailerZip;
	}

	public String getRetailerCity() {
		return retailerCity;
	}

	public void setRetailerCity(String retailerCity) {
		this.retailerCity = retailerCity;
	}

	public String getRetailerState() {
		return retailerState;
	}

	public void setRetailerState(String retailerState) {
		this.retailerState = retailerState;
	}

	public String getProductOnSale() {
		return productOnSale;
	}

	public void setProductOnSale(String productOnSale) {
		this.productOnSale = productOnSale;
	}

	public String getManufactureName() {
		return manufactureName;
	}

	public void setManufactureName(String manufactureName) {
		this.manufactureName = manufactureName;
	}

	public String getManufactureRebate() {
		return manufactureRebate;
	}

	public void setManufactureRebate(String manufactureRebate) {
		this.manufactureRebate = manufactureRebate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserAge() {
		return userAge;
	}

	public void setUserAge(String userAge) {
		this.userAge = userAge;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public String getUserOccupation() {
		return userOccupation;
	}

	public void setUserOccupation(String userOccupation) {
		this.userOccupation = userOccupation;
	}

	public String getReviewRating() {
		return reviewRating;
	}

	public void setReviewRating(String reviewRating) {
		this.reviewRating = reviewRating;
	}

	public String getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

}
