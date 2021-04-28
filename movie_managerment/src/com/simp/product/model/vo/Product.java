package com.simp.product.model.vo;

public class Product {
		private int productNo;//상품 번호
		private String pdCategory;//상품분류
		private String productName;//상품이름
		private int productPrice; //상품가격
		private int stock; //재고량
		private int accumulateSale; //누적판매수량
		private String pdStatus; //판매여부
		private String pdImg;//이미지
		private String pdInfo;//상품설명
		
		public Product() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Product(int productNo, String pdCategory, String productName, int productPrice, int stock,
				int accumulateSale, String pdStatus, String pdImg, String pdInfo) {
			super();
			this.productNo = productNo;
			this.pdCategory = pdCategory;
			this.productName = productName;
			this.productPrice = productPrice;
			this.stock = stock;
			this.accumulateSale = accumulateSale;
			this.pdStatus = pdStatus;
			this.pdImg = pdImg;
			this.pdInfo = pdInfo;
		}

		public int getProductNo() {
			return productNo;
		}

		public void setProductNo(int productNo) {
			this.productNo = productNo;
		}

		public String getPdCategory() {
			return pdCategory;
		}

		public void setPdCategory(String pdCategory) {
			this.pdCategory = pdCategory;
		}

		public String getProductName() {
			return productName;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}

		public int getProductPrice() {
			return productPrice;
		}

		public void setProductPrice(int productPrice) {
			this.productPrice = productPrice;
		}

		public int getStock() {
			return stock;
		}

		public void setStock(int stock) {
			this.stock = stock;
		}

		public int getAccumulateSale() {
			return accumulateSale;
		}

		public void setAccumulateSale(int accumulateSale) {
			this.accumulateSale = accumulateSale;
		}

		public String getPdStatus() {
			return pdStatus;
		}

		public void setPdStatus(String pdStatus) {
			this.pdStatus = pdStatus;
		}

		public String getPdImg() {
			return pdImg;
		}

		public void setPdImg(String pdImg) {
			this.pdImg = pdImg;
		}

		public String getPdInfo() {
			return pdInfo;
		}

		public void setPdInfo(String pdInfo) {
			this.pdInfo = pdInfo;
		}

		@Override
		public String toString() {
			return "Product [productNo=" + productNo + ", pdCategory=" + pdCategory + ", productName=" + productName
					+ ", productPrice=" + productPrice + ", stock=" + stock + ", accumulateSale=" + accumulateSale
					+ ", pdStatus=" + pdStatus + ", pdImg=" + pdImg + ", pdInfo=" + pdInfo + "]";
		}
		
		
		
}
