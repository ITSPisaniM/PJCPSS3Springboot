package it.kennedy.cpss.springbootcpss.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import it.kennedy.cpss.springbootcpss.Dao.OrdiniDao;
import it.kennedy.cpss.springbootcpss.Dto.OrdiniDto;
import it.kennedy.cpss.springbootcpss.IService.IOrdiniService;
import it.kennedy.cpss.springbootcpss.Repository.IOrdiniRepository;

@Service
@Transactional
public class OrdiniService implements IOrdiniService{

	@Autowired
	IOrdiniRepository ordiniRepository;
	
	
	//GET ALL PAGINATION ORDINI
	@Override
	public List<OrdiniDto> getAll(int pagina, int elPerPage) {
		List<OrdiniDto> listDto = new ArrayList<OrdiniDto>();
		PageRequest pageable = PageRequest.of(pagina, elPerPage);
		for (OrdiniDao dao : ordiniRepository.findAll(pageable)) {
			OrdiniDto dto = new OrdiniDto();
			daoToDto(dao, dto);
			listDto.add(dto);
		}
		return listDto;
	}
	
	
	// GET BY ID SERVICE
	@Override
	public OrdiniDto getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	//DAO TO DTO METHOD
	private void daoToDto(OrdiniDao dao, OrdiniDto dto) {

		dto.setCompanyLegalName(dao.getCompanyLegalName());
		dto.setEarliestShipDate(dao.getEarliestShipDate());
		dto.setFulfillmentChannel(dao.getFulfillmentChannel());
		dto.setIsBusinessOrder(dao.isIsBusinessOrder());
		dto.setIsGlobalExpressEnabled(dao.isIsGlobalExpressEnabled());
		dto.setIsPremiumOrder(dao.isIsPremiumOrder());
		dto.setIsPrime(dao.isIsPrime());
		dto.setIsSoldByAB(dao.isIsSoldByAB());
		dto.setLastUpdateDate(dao.getLastUpdateDate());
		dto.setLatestShipDate(dao.getLatestShipDate());
		dto.setMarketplaceId(dao.getMarketplaceId());
		dto.setNumberOfItemsShipped(dao.getNumberOfItemsShipped());
		dto.setNumberOfItemsUnshipped(dao.getNumberOfItemsUnshipped());
		dto.setOrderStatus(dao.getOrderStatus());
		dto.setOrderType(dao.getOrderType());
		dto.setPaymentMethod(dao.getPaymentMethod());
		dto.setPaymentMethodDetails(dao.getPaymentMethodDetails());
		dto.setPurchaseDate(dao.getPurchaseDate());
		dto.setPurchaseOrderNumber(dao.getPurchaseOrderNumber());
		dto.setShipmentServiceLevelCategory(dao.getShipmentServiceLevelCategory());
		dto.setShippingAddressCity(dao.getShippingAddressCity());
		dto.setShippingAddressLine1(dao.getShippingAddressLine1());
		dto.setShippingAddressName(dao.getShippingAddressName());
		dto.setShippingCityStateOrRegion(dao.getShippingCityStateOrRegion());
		dto.setShippingStateOrRegionPostalCode(dao.getShippingStateOrRegionPostalCode());
	}
	
	@SuppressWarnings("unused")
	private void dtoToDao(OrdiniDto dto, OrdiniDao dao) {

		dao.setCompanyLegalName(dto.getCompanyLegalName());
		dao.setEarliestShipDate(dto.getEarliestShipDate());
		dao.setFulfillmentChannel(dto.getFulfillmentChannel());
		dao.setIsBusinessOrder(dto.getIsBusinessOrder());
		dao.setIsGlobalExpressEnabled(dto.getIsGlobalExpressEnabled());
		dao.setIsPremiumOrder(dto.getIsPremiumOrder());
		dao.setIsPrime(dto.getIsPrime());
		dao.setIsSoldByAB(dto.getIsSoldByAB());
		dao.setLastUpdateDate(dto.getLastUpdateDate());
		dao.setLatestShipDate(dto.getLatestShipDate());
		dao.setMarketplaceId(dto.getMarketplaceId());
		dao.setNumberOfItemsShipped(dto.getNumberOfItemsShipped());
		dao.setNumberOfItemsUnshipped(dto.getNumberOfItemsUnshipped());
		dao.setOrderStatus(dto.getOrderStatus());
		dao.setOrderType(dto.getOrderType());
		dao.setPaymentMethod(dto.getPaymentMethod());
		dao.setPaymentMethodDetails(dto.getPaymentMethodDetails());
		dao.setPurchaseDate(dto.getPurchaseDate());
		dao.setPurchaseOrderNumber(dto.getPurchaseOrderNumber());
		dao.setShipmentServiceLevelCategory(dto.getShipmentServiceLevelCategory());
		dao.setShippingAddressCity(dto.getShippingAddressCity());
		dao.setShippingAddressLine1(dto.getShippingAddressLine1());
		dao.setShippingAddressName(dto.getShippingAddressName());
		dao.setShippingCityStateOrRegion(dto.getShippingCityStateOrRegion());
		dao.setShippingStateOrRegionPostalCode(dto.getShippingStateOrRegionPostalCode());
	}

}
