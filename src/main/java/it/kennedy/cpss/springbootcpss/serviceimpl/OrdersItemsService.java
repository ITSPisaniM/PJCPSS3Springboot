package it.kennedy.cpss.springbootcpss.serviceimpl;

import it.kennedy.cpss.springbootcpss.dto.input.AnalisiFilterDto;
import it.kennedy.cpss.springbootcpss.iservice.IOrdersItemsService;
import it.kennedy.cpss.springbootcpss.repository.IOrdersItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrdersItemsService implements IOrdersItemsService {

    @Autowired
    IOrdersItemsRepository orderItemsRepository;

    @Override
    public List<AnalisiFilterDto> findByFilters(AnalisiFilterDto filter) throws ParseException {

        String startDate = filter.getStartDateS() + " 00:00:00";
        String endDate = filter.getEndDateS() + " 00:00:00";
        String analiticsType = filter.getAnaliticsType();
        String defaultStartDate = "1970-01-01 00:00:00";
        String defaultEndDate = "2022-01-01 00:00:00";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime startDateFormat = LocalDateTime.parse(defaultStartDate, formatter);
        if (startDate != null) {
            startDateFormat = LocalDateTime.parse(startDate, formatter);
        }

        LocalDateTime endDateFormat = LocalDateTime.parse(defaultEndDate, formatter);
        if (startDate != null) {
            endDateFormat = LocalDateTime.parse(endDate, formatter);
        }

        List<AnalisiFilterDto> analisiFilterDto = new ArrayList<>();

        for (int i = 0;i<7;i++) {

        endDateFormat = startDateFormat.plusDays(1);

        int quantita = 0;
        int ricavi = 0;

        if (analiticsType.equals("1")) {
            try {
                quantita = orderItemsRepository.totQuantita(startDateFormat.toLocalDate(), endDateFormat.toLocalDate());
                ricavi = orderItemsRepository.totRicavi(startDateFormat.toLocalDate(), endDateFormat.toLocalDate());
            }
            catch (Exception exc) {
                quantita = 0;
                ricavi = 0;
            }
        } else if (analiticsType.equals("2")) {
            // servizi per item
        } else if (analiticsType.equals("3")) {
            // servizi per categoria
        }

        AnalisiFilterDto filterDto = new AnalisiFilterDto();

        filterDto.setQuantitaTot(quantita);
        filterDto.setRicaviTot(ricavi);
        filterDto.setStartDate(startDateFormat);
        filterDto.setEndDate(endDateFormat);
        filterDto.setAnaliticsType(analiticsType);
        analisiFilterDto.add(filterDto);

        startDateFormat = startDateFormat.plusDays(1);
        }
        return analisiFilterDto;
    }

}
