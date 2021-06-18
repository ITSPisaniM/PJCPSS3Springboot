package it.kennedy.cpss.springbootcpss.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import it.kennedy.cpss.springbootcpss.dao.OrdiniDao;
import it.kennedy.cpss.springbootcpss.dto.OrdiniDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import it.kennedy.cpss.springbootcpss.IService.IOrdiniService;
import it.kennedy.cpss.springbootcpss.Repository.IOrdiniRepository;

@Service
@Transactional
public class OrdiniService implements IOrdiniService{

	@Autowired
	IOrdiniRepository ordiniRepository;
	
	
	//GET ALL PAGINATION ORDINI
	@Override
	public List<OrdiniDto> getAllPagination(int pagina, int elPerPage) {
		List<OrdiniDto> listDto = new ArrayList<OrdiniDto>();
		PageRequest pageable = PageRequest.of(pagina, elPerPage);
		for (OrdiniDao dao : ordiniRepository.findAll(pageable)) {
			OrdiniDto dto = new OrdiniDto();
			daoToDto(dao, dto);
			listDto.add(dto);
		}
		return listDto;
	}
	
	
	@Override
	public List<OrdiniDto> getAll() {
		List<OrdiniDao> listaDao = ordiniRepository.findAll();
		List<OrdiniDto> listaDto = new ArrayList<OrdiniDto>();
		for (OrdiniDao dao : listaDao) {
			OrdiniDto dto = new OrdiniDto();
			daoToDto(dao, dto);
			listaDto.add(dto);
		}
		return listaDto;
	}
	
	
	// GET BY ID SERVICE
	@Override
	public OrdiniDto getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//--------------------------------------------------------------------------------------------------------------------------------
	// METHODS
	
	//DAO TO DTO METHOD
	private void daoToDto(OrdiniDao dao, OrdiniDto dto) {

		dto.setCompanylegalname(dao.getCompanylegalname());
		dto.setEarliestshipdate(dao.getEarliestshipdate());
		dto.setFulfillmentchannel(dao.getFulfillmentchannel());
		dto.setIsbusinessorder(dao.isIsbusinessorder());
		dto.setIsglobalexpressenabled(dao.isIsglobalexpressenabled());
		dto.setIspremiumorder(dao.isIspremiumorder());
		dto.setIsprime(dao.isIsprime());
		dto.setIssoldbyab(dao.isIssoldbyab());
		dto.setLastupdatedate(dao.getLastupdatedate());
		dto.setLatestshipdate(dao.getEarliestshipdate());
		dto.setMarketplaceid(dao.getMarketplaceid());
		dto.setNumberofitemsshipped(dao.getNumberofitemsshipped());
		dto.setNumberofitemsunshipped(dao.getNumberofitemsshipped());
		dto.setOrderstatus(dao.getOrderstatus());
		dto.setOrdertype(dao.getOrderstatus());
		dto.setPaymentmethod(dao.getPaymentmethod());
		dto.setPaymentmethoddetails(dao.getPaymentmethod());
		dto.setPurchasedate(dao.getPurchasedate());
		dto.setPurchaseordernumber(dao.getPurchaseordernumber());
		dto.setShipmentservicelevelcategory(dao.getShipmentservicelevelcategory());
		dto.setShippingaddresscity(dao.getShippingaddresscity());
		dto.setShippingaddressline1(dao.getShippingaddresscity());
		dto.setShippingaddressname(dao.getShippingaddresscity());
		dto.setShippingcitystateorregion(dao.getShippingcitystateorregion());
		dto.setShippingstateorregionpostalcode(dao.getShippingstateorregionpostalcode());
	}
	
	//DTO TO DAO METHOD
	@SuppressWarnings("unused")
	private void dtoToDao(OrdiniDto dto, OrdiniDao dao) {

		dao.setCompanylegalname(dto.getCompanylegalname());
		dao.setEarliestshipdate(dto.getEarliestshipdate());
		dao.setFulfillmentchannel(dto.getFulfillmentchannel());
		dao.setIsbusinessorder(dto.getIsbusinessorder());
		dao.setIsglobalexpressenabled(dto.getIsglobalexpressenabled());
		dao.setIspremiumorder(dto.getIspremiumorder());
		dao.setIsprime(dto.getIsprime());
		dao.setIssoldbyab(dto.getIssoldbyab());
		dao.setLastupdatedate(dto.getLastupdatedate());
		dao.setEarliestshipdate(dto.getLatestshipdate());
		dao.setMarketplaceid(dto.getMarketplaceid());
		dao.setNumberofitemsshipped(dto.getNumberofitemsshipped());
		dao.setNumberofitemsunshipped(dto.getNumberofitemsunshipped());
		dao.setOrderstatus(dto.getOrderstatus());
		dao.setOrdertype(dto.getOrdertype());
		dao.setPaymentmethod(dto.getPaymentmethod());
		dao.setPaymentmethoddetails(dto.getPaymentmethoddetails());
		dao.setPurchasedate(dto.getPurchasedate());
		dao.setPurchaseordernumber(dto.getPurchaseordernumber());
		dao.setShipmentservicelevelcategory(dto.getShipmentservicelevelcategory());
		dao.setShippingaddresscity(dto.getShippingaddresscity());
		dao.setShippingaddressline1(dto.getShippingaddressline1());
		dao.setShippingaddressname(dto.getShippingaddressname());
		dao.setShippingcitystateorregion(dto.getShippingcitystateorregion());
		dao.setShippingstateorregionpostalcode(dto.getShippingstateorregionpostalcode());
	}


}
