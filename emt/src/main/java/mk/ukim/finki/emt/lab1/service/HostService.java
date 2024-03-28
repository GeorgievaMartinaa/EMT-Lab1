package mk.ukim.finki.emt.lab1.service;

import mk.ukim.finki.emt.lab1.model.Host;

import java.util.List;

public interface HostService {
    List<Host> listAll();
    Host findById(Long id);
    void deleteById(Long id);
    Host create(String name, String surname, Long countryId);
    Host update(Long id, String name, String surname, Long countryId);

}
