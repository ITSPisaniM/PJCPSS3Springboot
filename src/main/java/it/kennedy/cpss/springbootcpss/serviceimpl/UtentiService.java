package it.kennedy.cpss.springbootcpss.serviceimpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.kennedy.cpss.springbootcpss.iservice.IUtentiService;
import it.kennedy.cpss.springbootcpss.repository.IUtentiRepository;

@Service
@Transactional
public class UtentiService implements IUtentiService {
    @Autowired
    IUtentiRepository utenteRepository;

}
