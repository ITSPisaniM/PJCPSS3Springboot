package it.kennedy.cpss.springbootcpss.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import it.kennedy.cpss.springbootcpss.dto.OrdiniDto;
import it.kennedy.cpss.springbootcpss.dao.OrdiniDao;
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

	// --------------------------------------------------------------------------------------------------------------------------------
	// METHODS

	// DAO TO DTO METHOD
	private void daoToDto(OrdiniDao dao, OrdiniDto dto) {

	}

	// DTO TO DAO METHOD
	@SuppressWarnings("unused")
	private void dtoToDao(OrdiniDto dto, OrdiniDao dao) {

	}

}
