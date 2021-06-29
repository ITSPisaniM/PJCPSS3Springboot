package it.kennedy.cpss.springbootcpss.serviceimpl;

import it.kennedy.cpss.springbootcpss.dao.ProdottiDao;
import it.kennedy.cpss.springbootcpss.dto.ProdottiDto;
import it.kennedy.cpss.springbootcpss.iservice.IProdottiService;
import it.kennedy.cpss.springbootcpss.repository.IProdottiRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdottiService implements IProdottiService {

    @Autowired
    IProdottiRepository prodottiRepository;

    // GET ALL PAGINATION PRODOTTI
    @Override
    public List<ProdottiDto> getAllPagination(Pageable pageable) {
        List<ProdottiDto> listDto = new ArrayList<>();
        for (ProdottiDao dao : prodottiRepository.findAll(pageable)) {
            var dto = daoToDto(dao);
            listDto.add(dto);
        }
        return listDto;
    }

    @Override
    public List<ProdottiDto> getAll() {
        List<ProdottiDao> listaDao = prodottiRepository.findAll();
        List<ProdottiDto> listaDto = new ArrayList<>();
        for (ProdottiDao dao : listaDao) {
            var dto = daoToDto(dao);
            listaDto.add(dto);
        }
        return listaDto;
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
        ProdottiDao dao = new ProdottiDao();
        try {
            dao = dtoToDao(dto);
            prodottiRepository.save(dao);
            return true;
        } catch (Exception exc) {
            return false;
        }
    }

    @Override
    public Boolean updateProdotto(ProdottiDto dto, int id) {
        return null;
    }

    @Override
    public Boolean deleteProdotto(int id) {
        return null;
    }

    @Override
    public int count() {
        int count = prodottiRepository.countProdotti();
        return count;
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
