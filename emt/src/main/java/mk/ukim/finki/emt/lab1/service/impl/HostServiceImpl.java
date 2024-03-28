package mk.ukim.finki.emt.lab1.service.impl;

import mk.ukim.finki.emt.lab1.model.Country;
import mk.ukim.finki.emt.lab1.model.Host;
import mk.ukim.finki.emt.lab1.repository.CountryRepository;
import mk.ukim.finki.emt.lab1.repository.HostRepository;
import mk.ukim.finki.emt.lab1.service.HostService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.InvalidParameterException;
import java.util.List;

@Service
public class HostServiceImpl implements HostService {

    private final HostRepository hostRepository;
    private final CountryRepository countryRepository;

    public HostServiceImpl(HostRepository hostRepository, CountryRepository countryRepository) {
        this.hostRepository = hostRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Host> listAll() {
        return this.hostRepository.findAll();
    }

    @Override
    public Host findById(Long id) {
        return this.hostRepository.findById(id).orElseThrow(InvalidParameterException::new);
    }

    @Override
    public void deleteById(Long id) {
        this.hostRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Host create(String name, String surname, Long countryId) {
       Country country = countryRepository.findById(countryId).orElseThrow(InvalidParameterException::new);
        return this.hostRepository.save(new Host(name,surname,country));
    }

    @Override
    @Transactional
    public Host update(Long id, String name, String surname, Long countryId) {
        Host host = this.findById(id);
        Country country = countryRepository.findById(countryId).orElseThrow(InvalidParameterException::new);
        host.setName(name);
        host.setSurname(surname);
        host.setCountry(country);
        return this.hostRepository.save(host);
    }
}
