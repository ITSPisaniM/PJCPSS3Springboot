package it.kennedy.cpss.springbootcpss.serviceimpl;

import it.kennedy.cpss.springbootcpss.dao.OrdiniDao;
import it.kennedy.cpss.springbootcpss.dto.OrdiniDto;
import it.kennedy.cpss.springbootcpss.iservice.IOrdiniService;
import it.kennedy.cpss.springbootcpss.repository.IOrdiniRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrdiniService implements IOrdiniService {

	@Autowired
	IOrdiniRepository ordiniRepository;

	// GET ALL PAGINATION ORDINI
	@Override
	public List<OrdiniDto> getAllPagination(int pagina, int elPerPage) {
		List<OrdiniDto> listDto = new ArrayList<>();
		var pageable = PageRequest.of(pagina, elPerPage);
		for (OrdiniDao dao : ordiniRepository.findAll(pageable)) {
			var dto = daoToDto(dao);
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
			var dto = daoToDto(dao);
			listaDto.add(dto);
		}
		return listaDto;
	}

	// GET BY ID ORDINE
	@Override
	public OrdiniDto findByAmazonOrderId(String id) {
		try {
			OrdiniDto dto = new OrdiniDto();
			String idString = id + "";
			OrdiniDao dao = ordiniRepository.findByAmazonOrderId(idString);
			dto = daoToDto(dao);
			return dto;
		} catch (Exception e) {
			return null;
		}
	}

  
	// --------------------------------------------------------------------------------------------------------------------------------
	// METHODS

	// DAO TO DTO METHOD
	private OrdiniDto daoToDto(OrdiniDao dao) {
		var mapper = new ModelMapper();
		return mapper.map(dao, OrdiniDto.class);
	}

	// DTO TO DAO METHOD
	private OrdiniDao dtoToDao(OrdiniDto dto) {
		var mapper = new ModelMapper();
		return mapper.map(dto, OrdiniDao.class);
	}

}
