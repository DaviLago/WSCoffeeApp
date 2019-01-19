package br.edu.fatec.main.persistency;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.edu.fatec.main.model.AuthenticationToken;

@Repository
public interface AuthenticationTokenRepository extends MongoRepository<AuthenticationToken, String> {
	
	AuthenticationToken findOneByTokenId(String tokenId);

//	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
//	@Query("DELETE FROM AuthenticationToken t WHERE t.expirationDate < :expirationDate")
//	void deleteExpiredTokens(@Param("expirationDate") Date expirationDate);

}
