package it.kennedy.cpss.springbootcpss.Dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdiniDto {

	public String companylegalname;
	public Date earliestshipdate;
	public Integer fulfillmentchannel;
	public Boolean isbusinessorder;
	public Boolean isglobalexpressenabled;
	public Boolean ispremiumorder;
	public Boolean isprime;
	public Boolean issoldbyab;
	public Date lastupdatedate;
	public Date latestshipdate;
	public String marketplaceid;
	public Integer numberofitemsshipped;
	public Integer numberofitemsunshipped;
	public String orderstatus;
	public String ordertype;
	public String paymentmethod;
	public String paymentmethoddetails;
	public Date purchasedate;
	public Integer purchaseordernumber;
	public Integer shipmentservicelevelcategory;
	public String shippingaddresscity;
	public String shippingaddressline1;
	public String shippingaddressname;
	public String shippingcitystateorregion;
	public String shippingstateorregionpostalcode;
	
}
