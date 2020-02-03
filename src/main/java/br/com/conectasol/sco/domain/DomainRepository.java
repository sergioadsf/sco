package br.com.conectasol.sco.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface DomainRepository extends MongoRepository<Domain, Long> {

    Domain findFirstByDomain(String domain);

    Domain findByDomainAndDisplayAds(String domain, boolean displayAds);

    //Supports native JSON query string
    @Query("{domain:'?0'}")
    Domain findCustomByDomain(String domain);

    @Query("{domain: { $regex: ?0 } })")
    List<Domain> findCustomByRegExDomain(String domain);
}
