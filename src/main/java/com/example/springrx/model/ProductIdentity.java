package com.example.springrx.model;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class ProductIdentity {

	@PrimaryKeyColumn(value = "product_id", type = PrimaryKeyType.PARTITIONED)
	String productId;
	@PrimaryKeyColumn(value = "color_code", type = PrimaryKeyType.CLUSTERED)
	String colorCode;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((colorCode == null) ? 0 : colorCode.hashCode());
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductIdentity other = (ProductIdentity) obj;
		if (colorCode == null) {
			if (other.colorCode != null)
				return false;
		} else if (!colorCode.equals(other.colorCode))
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		return true;
	}

}
