package it.kennedy.cpss.springbootcpss.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import it.kennedy.cpss.springbootcpss.dao.OrdiniDao;
import it.kennedy.cpss.springbootcpss.dto.OrdiniDto;
import it.kennedy.cpss.springbootcpss.iservice.IOrdiniService;
import it.kennedy.cpss.springbootcpss.repository.IOrdiniRepository;

@Service
@Transactional
public class OrdiniService implements IOrdiniService {

	@Autowired
	IOrdiniRepository ordiniRepository;

	// GET ALL PAGINATION ORDINI
	@Override
	public List<OrdiniDto> getAllPagination(int pagina, int elPerPage) {
		List<OrdiniDto> listDto = new ArrayList<>();
		var pageable = PageRequest.of(pagina, elPerPage);
		for (OrdiniDao dao : ordiniRepository.findAll(pageable)) {
			var dto = new OrdiniDto();
			daoToDto(dao, dto);
			listDto.add(dto);
		}
		return listDto;
	}

	// GET ALL ORDINI
	@Override
	public List<OrdiniDto> getAll() {
		List<OrdiniDao> listaDao = ordiniRepository.findAll();
		List<OrdiniDto> listaDto = new ArrayList<>();
		for (OrdiniDao dao : listaDao) {
			var dto = new OrdiniDto();
			daoToDto(dao, dto);
			listaDto.add(dto);
		}
		return listaDto;
	}

	// GET BY ID SERVICE
	@Override
	public OrdiniDto getById(int id) {
		try {
			var dto = new OrdiniDto();
			daoToDto(ordiniRepository.getById(id), dto);
			return dto;
		} catch (Exception exc) {
			return null;
		}
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	// METHODS

	// DAO TO DTO METHOD
	private void daoToDto(OrdiniDao dao, OrdiniDto dto) {
		dto.setPurchaseDate(dao.getPurchaseDate());
		dto.setLastUpdatedDate(dao.getLastUpdatedDate());
		dto.setOrderSatus(dao.getOrderSatus());
		dto.setFullfillmentChannel(dao.getFullfillmentChannel());
		dto.setNumberOfItemsShipped(dao.getNumberOfItemsShipped());
		dto.setNumebrOfItemsUnshipped(dao.getNumebrOfItemsUnshipped());
		dto.setPaymentMethod(dao.getPaymentMethod());
		dto.setPaymentMethodDetails(dao.getPaymentMethodDetails());
		dto.setMarketplaceId(dao.getMarketplaceId());
		dto.setShipmentServiceLevelCategory(dao.getShipmentServiceLevelCategory());
		dto.setOrderType(dao.getOrderType());
		dto.setEarliestsShipDate(dao.getEarliestsShipDate());
		dto.setLatestShipDate(dao.getLatestShipDate());
		dto.setBusinessOrder(dao.isBusinessOrder());
		dto.setPrime(dao.isPrime());
		dto.setGlobalExpressEnabled(dao.isGlobalExpressEnabled());
		dto.setPremiumOrder(dao.isPremiumOrder());
		dto.setSoldByAb(dao.isSoldByAb());
		dto.setCompanyLegalName(dao.getCompanyLegalName());
		dto.setBuyerEmail(dao.getBuyerEmail());
		dto.setBuyerName(dao.getBuyerName());
		dto.setPurchaseOrderNumber(dao.getPurchaseOrderNumber());
		dto.setShippingAddressName(dao.getShippingAddressName());
		dto.setShippingAddressLine1(dao.getShippingAddressLine1());
		dto.setShippingAddressCity(dao.getShippingAddressCity());
		dto.setShippingCityStateOrRegion(dao.getShippingCityStateOrRegion());
		dto.setShippingStateOrRegionPostalCode(dao.getShippingStateOrRegionPostalCode());
	}

	// DTO TO DAO METHOD
	@SuppressWarnings("unused")
	private void dtoToDao(OrdiniDto dto, OrdiniDao dao) {
		dao.setPurchaseDate(dto.getPurchaseDate());
		dao.setLastUpdatedDate(dto.getLastUpdatedDate());
		dao.setOrderSatus(dto.getOrderSatus());
		dao.setFullfillmentChannel(dto.getFullfillmentChannel());
		dao.setNumberOfItemsShipped(dto.getNumberOfItemsShipped());
		dao.setNumebrOfItemsUnshipped(dto.getNumebrOfItemsUnshipped());
		dao.setPaymentMethod(dto.getPaymentMethod());
		dao.setPaymentMethodDetails(dto.getPaymentMethodDetails());
		dao.setMarketplaceId(dto.getMarketplaceId());
		dao.setShipmentServiceLevelCategory(dto.getShipmentServiceLevelCategory());
		dao.setOrderType(dto.getOrderType());
		dao.setEarliestsShipDate(dto.getEarliestsShipDate());
		dao.setLatestShipDate(dto.getLatestShipDate());
		dao.setBusinessOrder(dto.isBusinessOrder());
		dao.setPrime(dto.isPrime());
		dao.setGlobalExpressEnabled(dto.isGlobalExpressEnabled());
		dao.setPremiumOrder(dto.isPremiumOrder());
		dao.setSoldByAb(dto.isSoldByAb());
		dao.setCompanyLegalName(dto.getCompanyLegalName());
		dao.setBuyerEmail(dto.getBuyerEmail());
		dao.setBuyerName(dto.getBuyerName());
		dao.setPurchaseOrderNumber(dto.getPurchaseOrderNumber());
		dao.setShippingAddressName(dto.getShippingAddressName());
		dao.setShippingAddressLine1(dto.getShippingAddressLine1());
		dao.setShippingAddressCity(dto.getShippingAddressCity());
		dao.setShippingCityStateOrRegion(dto.getShippingCityStateOrRegion());
		dao.setShippingStateOrRegionPostalCode(dto.getShippingStateOrRegionPostalCode());
	}

}
