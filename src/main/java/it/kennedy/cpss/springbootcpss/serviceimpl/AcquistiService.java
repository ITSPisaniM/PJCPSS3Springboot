package it.kennedy.cpss.springbootcpss.serviceimpl;

import it.kennedy.cpss.springbootcpss.dao.AcquistiDao;
import it.kennedy.cpss.springbootcpss.dao.AcquistiProdottiDao;
import it.kennedy.cpss.springbootcpss.dto.AcquistiDto;
import it.kennedy.cpss.springbootcpss.dto.AcquistiProdottiDto;
import it.kennedy.cpss.springbootcpss.dto.input.AcquistiInsertDto;
import it.kennedy.cpss.springbootcpss.dto.input.ProdottoInput;
import it.kennedy.cpss.springbootcpss.iservice.IAcquistiService;
import it.kennedy.cpss.springbootcpss.repository.IAcquistiProdottiRepository;
import it.kennedy.cpss.springbootcpss.repository.IAcquistiRepository;
import it.kennedy.cpss.springbootcpss.repository.IProdottiRepository;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class AcquistiService implements IAcquistiService {

    @Autowired
    IAcquistiRepository acquistiRepository;
    @Autowired
    IProdottiRepository prodottiRepository;

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
    public Boolean insertAcquisto(AcquistiInsertDto dto) {
        AcquistiDao dao = new AcquistiDao();
        try {
            dao.setBillDate(LocalDate.now());
            dao.setBillNumber(dto.getBillNumber());
            dao.setSupplierId(dto.getSupplierId());

            acquistiRepository.save(dao);
            return true;
        } catch (Exception exc) {
            return false;
        }
    }

    @Override
    public int getLastId() {
        int idAcquisto = acquistiRepository.getLastId();
        return idAcquisto;
    }

    @Transactional
    @Override
    public Boolean insertPurchasesItems(ProdottoInput piDto, int idAcquisto) {
    try {

        var listDtoProdottiInput = piDto.getProdotto();
        for(var dto : listDtoProdottiInput){
            AcquistiProdottiDao dao = new AcquistiProdottiDao();
            dao.setPurchaseId(idAcquisto);
            dao.setAsin(dto.prodotto.asin);
            dao.setPurchasedAmount(dto.quantita);
            dao.setUnitPrice(dto.prodotto.price);

            //SALVATAGGIO PURCHASEITEM NEL DB
            acquistiProdottiRepository.save(dao);

            //OTTENIMENTO DELLA GIACENZA
            int giacenzaProdotto = prodottiRepository.getStockByAsin(dao.getAsin());

            //SETTAGGIO DELLA GIACENZA
                giacenzaProdotto -= dao.getPurchasedAmount();
            prodottiRepository.setStockByAsin(giacenzaProdotto, dao.getAsin());
        }
        return true;
    } catch (Exception exc){
        return false;
    }
    }


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
        dao.setBillDate(LocalDate.now());
        return dao;
    }

    private AcquistiProdottiDao dtoToDaoPurchaseditems(AcquistiProdottiDto dto){
        var mapper = new ModelMapper();
        AcquistiProdottiDao dao = mapper.map(dto, AcquistiProdottiDao.class);
        return dao;
    }


    private AcquistiDao acquistiInsertDtoToDao(AcquistiInsertDto dto){
        ModelMapper modelMapper = new ModelMapper();

        Converter<String, LocalDate> toStringDate = new AbstractConverter<String, LocalDate>() {
            @Override
            protected LocalDate convert(String source) {
                DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse(source, format);
                return localDate;
            }
        };


        modelMapper
                .typeMap(AcquistiInsertDto.class, AcquistiDao.class)
                .addMappings(mp -> mp.using(toStringDate).map(AcquistiInsertDto :: getBillDate, AcquistiDao :: setBillDate));

        return modelMapper.map(dto, AcquistiDao.class);
    }
}
