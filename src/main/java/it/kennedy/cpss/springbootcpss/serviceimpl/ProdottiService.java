package it.kennedy.cpss.springbootcpss.serviceimpl;

import it.kennedy.cpss.springbootcpss.dao.ProdottiDao;
import it.kennedy.cpss.springbootcpss.dto.ProdottiDto;
import it.kennedy.cpss.springbootcpss.iservice.IProdottiService;
import it.kennedy.cpss.springbootcpss.repository.IProdottiRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public Page<ProdottiDto> getAllPagination(Pageable pageable) {
        Page<ProdottiDao> pageListDto = prodottiRepository.findAll(pageable);

        return pageListDto.map(this::daoToDto);
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
            ProdottiDao dao = prodottiRepository.findByAsin(asin);
            return daoToDto(dao);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Boolean insertProdotto(ProdottiDto dto) {
        var dao = new ProdottiDao();
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
        return false;
    }

    @Override
    public Boolean deleteProdotto(int id) {
        return false;
    }

    @Override
    public int count() {
        return prodottiRepository.countProdotti();
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
