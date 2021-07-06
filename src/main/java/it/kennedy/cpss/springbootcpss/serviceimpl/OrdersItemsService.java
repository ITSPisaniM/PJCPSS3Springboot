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
        String itemAsin = filter.getItemAsin();
        String defaultStartDate = "1970-01-01 00:00:00";
        String defaultEndDate = "2022-01-01 00:00:00";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime startDateFormat = LocalDateTime.parse(defaultStartDate, formatter);
        if (startDate != null) {
            startDateFormat = LocalDateTime.parse(startDate, formatter);
        }

        LocalDateTime endDateFormat = LocalDateTime.parse(defaultEndDate, formatter);

        List<AnalisiFilterDto> analisiFilterDto = new ArrayList<>();

        for (int i = 0;i<7;i++) {

        endDateFormat = startDateFormat.plusDays(1);

        int quantita = 0;
        double ricavi = 00.00;

        if (itemAsin.isBlank()) {
            try {
                quantita = orderItemsRepository.totQuantita(startDateFormat.toLocalDate(), endDateFormat.toLocalDate());
                ricavi = orderItemsRepository.totRicavi(startDateFormat.toLocalDate(), endDateFormat.toLocalDate());
            }
            catch (Exception exc) {
                quantita = 0;
                ricavi = 0;
            }
        } else if (!itemAsin.isBlank()) {
            try {
                quantita = orderItemsRepository.totQuantitaOggetto(startDateFormat.toLocalDate(), endDateFormat.toLocalDate(), itemAsin);
                ricavi = orderItemsRepository.totRicaviOggetto(startDateFormat.toLocalDate(), endDateFormat.toLocalDate(), itemAsin);
            }
            catch (Exception exc) {
                quantita = 0;
                ricavi = 0;
            }
        }

        AnalisiFilterDto filterDto = new AnalisiFilterDto();

        filterDto.setQuantitaTot(quantita);
        filterDto.setRicaviTot(ricavi);
        filterDto.setStartDate(startDateFormat);
        if (!itemAsin.isBlank()) {
            filterDto.setItemAsin(itemAsin);
        }
        analisiFilterDto.add(filterDto);

        startDateFormat = startDateFormat.plusDays(1);
        }
        return analisiFilterDto;
    }

}
