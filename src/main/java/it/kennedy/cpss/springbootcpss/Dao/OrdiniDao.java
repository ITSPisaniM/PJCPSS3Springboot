package it.kennedy.cpss.springbootcpss.Dao;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "torders")
@Data
public class OrdiniDao {
	
	@Id
	@Column(name = "amazonorderid")
	private int amazonorderid;

	@Column(name = "buyeremail")
	private String buyeremail;

	@Column(name = "buyername")
	private String buyername;

	@Column(name = "companylegalname")
	private String companylegalname;

	@Column(name = "earliestshipdate")
	private Date earliestshipdate;

	@Column(name = "fulfillmentchannel")
	private int fulfillmentchannel;

	@Column(name = "isbusinessorder")
	private boolean isbusinessorder;

	@Column(name = "isglobalexpressenabled")
	private boolean isglobalexpressenabled;

	@Column(name = "ispremiumorder")
	private boolean ispremiumorder;

	@Column(name = "isprime")
	private boolean isprime;

	@Column(name = "issoldbyab")
	private boolean issoldbyab;

	@Column(name = "lastupdatedate")
	private Date lastupdatedate;
	
	@Column(name = "latestshipdate")
	private Date latestshipdate;
	
	@Column(name = "marketplaceid")
	private String marketplaceid;
	
	@Column(name = "numberofitemsshipped")
	private int numberofitemsshipped;
	
	@Column(name = "numberofitemsunshipped")
	private int numberofitemsunshipped;
	
	@Column(name = "orderstatus")
	private String orderstatus;
	
	@Column(name = "ordertype")
	private String ordertype;
	
	@Column(name = "paymentmethod")
	private String paymentmethod;
	
	@Column(name = "paymentmethoddetails")
	private String paymentmethoddetails;
	
	@Column(name = "purchasedate")
	private Date purchasedate;
	
	@Column(name = "purchaseordernumber")
	private int purchaseordernumber;
	
	@Column(name = "shipmentservicelevelcategory")
	private int shipmentservicelevelcategory;
	
	@Column(name = "shippingaddresscity")
	private String shippingaddresscity;
	
	@Column(name = "shippingaddressline1")
	private String shippingaddressline1;
	
	@Column(name = "shippingaddressname")
	private String shippingaddressname;
	
	@Column(name = "shippingcitystateorregion")
	private String shippingcitystateorregion;
	
	@Column(name = "shippingstateorregionpostalcode")
	private String shippingstateorregionpostalcode;

}
