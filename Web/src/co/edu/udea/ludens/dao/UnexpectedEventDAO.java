package co.edu.udea.ludens.dao;

import java.util.List;

import co.edu.udea.ludens.domain.UnexpectedEvent;

public interface UnexpectedEventDAO extends FundamentalsOperationsDAO {

	public List<UnexpectedEvent> findAllUnexpectedEvents(String gameName);

}
