package it.kennedy.cpss.springbootcpss.serviceimpl;

import it.kennedy.cpss.springbootcpss.dao.AcquistiDao;
import it.kennedy.cpss.springbootcpss.dao.AcquistiProdottiDao;
import it.kennedy.cpss.springbootcpss.dto.AcquistiDto;
import it.kennedy.cpss.springbootcpss.iservice.IAcquistiService;
import it.kennedy.cpss.springbootcpss.repository.IAcquistiProdottiRepository;
import it.kennedy.cpss.springbootcpss.repository.IAcquistiRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AcquistiService implements IAcquistiService {

    @Autowired
    IAcquistiRepository acquistiRepository;

    @Autowired
    IAcquistiProdottiRepository acquistiProdottiRepository;

// GET ALL PAGINATION
    @Override
    public Page<AcquistiDto> getAllPagination(Pageable pageable) {
        Page<AcquistiDao> pageListDao = acquistiRepository.findAll(pageable);
        return pageListDao.map(this::daoToDto);
    }

// GAT ALL LIST
    @Override
    public List<AcquistiDto> getAll() {
        List<AcquistiDao> listaDao = acquistiRepository.findAll();
        List<AcquistiDto> listaDto = new ArrayList<>();
        for (AcquistiDao dao : listaDao) {
            var dto = daoToDto(dao);
            listaDto.add(dto);
        }
        return listaDto;
    }

// GET BY ID
    @Override
    public AcquistiDto findByPurchaseId(int id) {
        try {
            AcquistiDto dto = new AcquistiDto();
            AcquistiDao dao = acquistiRepository.findByPurchaseId(id);
            dto = daoToDto(dao);
            return dto;
        } catch (Exception e) {
            return null;
        }
    }

// INSERT
    @Override
    public Boolean insertAcquisto(AcquistiDto dto) {
        AcquistiDao dao = new AcquistiDao();
        try {
            dao = dtoToDao(dto);
            acquistiRepository.save(dao);



            return true;
        } catch (Exception e) {

            return false;
        }
    }

// UPDATE
    @Override
    public Boolean modifyAcquisto(AcquistiDto dto, int id) {
        AcquistiDao dao = acquistiRepository.getById(id);
        dao = dtoToDao(dto);
        try {
            acquistiRepository.save(dao);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

// DELETE
    @Override
    @Transactional
    public Boolean deleteAcquisto(int id) {
        try {
            acquistiProdottiRepository.deleteByPurchaseId(id);
            acquistiRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



    // --------------------------------------------------------------------------------------------------------------------------------
    // METHODS

    // DAO TO DTO METHOD
    private AcquistiDto daoToDto(AcquistiDao dao) {
        var mapper = new ModelMapper();
        AcquistiDto dto = mapper.map(dao, AcquistiDto.class);

        List<AcquistiProdottiDao> acquistiprodottiDao = acquistiProdottiRepository.findByPurchaseId(dto.getPurchaseId());

        dto.setAcquistiProdotti(acquistiprodottiDao);
        return dto;
    }

    // DTO TO DAO METHOD
    private AcquistiDao dtoToDao(AcquistiDto dto) {
        var mapper = new ModelMapper();
        AcquistiDao dao = mapper.map(dto, AcquistiDao.class);
        dao.setBillDate(new Date());
        return dao;
    }

}
