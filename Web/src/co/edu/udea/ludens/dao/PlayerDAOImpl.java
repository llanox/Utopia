package co.edu.udea.ludens.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import co.edu.udea.ludens.domain.Game;
import co.edu.udea.ludens.domain.Player;
import co.edu.udea.ludens.domain.User;
@Repository
public class PlayerDAOImpl extends ObjectDBDAO implements PlayerDAO {
	public Logger logger = Logger.getLogger(this.getClass());


	@Override
	public Player findPlayerByUserName(final String userName) {
		
		Class clazz = Player.class;
		Player player = null;		
		String SQL ="SELECT o FROM " + clazz.getName()+ " o  WHERE o.user.login "+" LIKE '%"+ userName + "'";		
		CriteriaQuery<Object> query = em.getCriteriaBuilder().createQuery();
		TypedQuery<Player> q2 = em.createQuery(SQL, clazz);
	    List<Player> players = q2.getResultList();
	    
		if(players!=null & !players.isEmpty())
		player = players.get(0);
		
		return player;
		
	}

	@Override
	public List<Player> findAllPlayersByGameName(String gameName) {

		List<Player> players = new ArrayList<Player>();		
		Class clazz = Game.class;
		Game game = null;
		List<Game> games = new ArrayList<Game>();
		
		String SQL ="SELECT o FROM " + clazz.getName()+ " o  WHERE o.name "+" LIKE '%"+ gameName + "'";
		CriteriaQuery<Object> query = em.getCriteriaBuilder().createQuery();
		TypedQuery<Game> q2 = em.createQuery(SQL, clazz);
		games = q2.getResultList();
		
		if(games!=null && !games.isEmpty())
		game = games.get(0);
		
		if(game!=null)
			players = game.getPlayers();
		
		return players;
	}
	


	@Override
	public List<Player> findAllPlayers() {
		List<Player> result = (List<Player>) this.findObjectByType(Player.class);
		return result;
	}

	@Override
	public List<Player> findAllPlayersByGameName(boolean participatingInGame,String gameName) {
	    List<Player> players= new ArrayList<Player>();
		Class clazz = Player.class;
	
		String SQL ="SELECT o FROM " + clazz.getName()+ " o JOIN FETCH o.materials WHERE o.game.name "+" LIKE '%"+ gameName + "' AND o.user.participatingInGame.toString() "+" LIKE '%"+ participatingInGame + "'";
		
		CriteriaQuery<Object> query = em.getCriteriaBuilder().createQuery();
		TypedQuery<Player> q2 = em.createQuery(SQL, clazz);
		players = q2.getResultList();
		return players;
	}
	

	
	@Override
	public Object saveOrUpdate(Object o) {
        logger.info("Saving player instance ");
        Player player = (Player) o;
        Game game =null;
        User user = null;
        
//        if(player.getGame()!=null && player.getGame().getId()!=null){
//        	game = em.find(Game.class, player.getGame().getId());
//        	 logger.debug("getting saved game ");
//        	player.setGame(game);
//        }
////        
//        if(player.getUser()!=null && player.getUser().getId()!=null){
//        	user = em.find(User.class, player.getUser().getId());
//        	 logger.debug("getting saved user ");
//            player.setUser(user);	
//        }
       
        em.merge(player.getGame());
        em.merge(player.getUser());
        
        if(player.getId()!=null)
        em.merge(player);		
        else
        em.persist(player);
        
		em.flush();
		
		return player;
	}

}
