package it.kennedy.cpss.springbootcpss.serviceimpl;

import it.kennedy.cpss.springbootcpss.dao.ProdottiDao;
import it.kennedy.cpss.springbootcpss.dto.ProdottiDto;
import it.kennedy.cpss.springbootcpss.iservice.IProdottiService;
import it.kennedy.cpss.springbootcpss.repository.IProdottiRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdottiService implements IProdottiService {


    @Autowired
    IProdottiRepository prodottiRepository;

    // GET ALL PAGINATION PRODOTTI
    @Override
    public List<ProdottiDto> getAllPagination(int pagina, int elPerPage) {
        List<ProdottiDto> listDto = new ArrayList<>();
        var pageable = PageRequest.of(pagina, elPerPage);
        for (ProdottiDao dao : prodottiRepository.findAll(pageable)) {
            var dto = daoToDto(dao);
            listDto.add(dto);
        }
        return listDto;
    }

    @Override
    public ProdottiDto getByIdProdotto(String asin) {
        try {
            ProdottiDto dto = new ProdottiDto();
            String idString = asin + "";
            ProdottiDao dao = prodottiRepository.findByAsin(asin);
            dto = daoToDto(dao);
            return dto;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Boolean insertProdotto(ProdottiDto dto) {
        return null;
    }

    @Override
    public Boolean updateProdotto(ProdottiDto dto, int id) {
        return null;
    }

    @Override
    public Boolean deleteProdotto(int id) {
        return null;
    }


    // --------------------------------------------------------------------------------------------------------------------------------
    // METHODS

    // DAO TO DTO METHOD
    private ProdottiDto daoToDto(ProdottiDao dao) {
        var mapper = new ModelMapper();
        return mapper.map(dao, ProdottiDto.class);
    }

    // DTO TO DAO METHOD
    private ProdottiDao dtoToDao(ProdottiDto dto) {
        var mapper = new ModelMapper();
        return mapper.map(dto, ProdottiDao.class);
    }

}
