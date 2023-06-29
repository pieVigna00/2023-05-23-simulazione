package it.polito.tdp.baseball.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.baseball.model.Appearances;
import it.polito.tdp.baseball.model.Arco;
import it.polito.tdp.baseball.model.ArcoMomentaneo;
import it.polito.tdp.baseball.model.People;
import it.polito.tdp.baseball.model.Salary;
import it.polito.tdp.baseball.model.Team;


public class BaseballDAO {
	
	public List<People> readAllPlayers(Map<String, People> mappa){
		String sql = "SELECT * "
				+ "FROM people";
		List<People> result = new ArrayList<People>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				People people= new People(rs.getString("playerID"), 
						rs.getString("birthCountry"), 
						rs.getString("birthCity"), 
						rs.getString("deathCountry"), 
						rs.getString("deathCity"),
						rs.getString("nameFirst"), 
						rs.getString("nameLast"), 
						rs.getInt("weight"), 
						rs.getInt("height"), 
						rs.getString("bats"), 
						rs.getString("throws"),
						getBirthDate(rs), 
						getDebutDate(rs), 
						getFinalGameDate(rs), 
						getDeathDate(rs)) ;
				result.add(people);
				mappa.put(rs.getString("playerID"),people );
			}
			
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}
	
	
	
	public List<Team> readAllTeams(){
		String sql = "SELECT * "
				+ "FROM  teams";
		List<Team> result = new ArrayList<Team>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				result.add(new Team( rs.getInt("iD"),
						rs.getInt("year"), 
						rs.getString("teamCode"), 
						rs.getString("divID"), 
						rs.getInt("div_ID"), 
						rs.getInt("teamRank"),
						rs.getInt("games"), 
						rs.getInt("gamesHome"), 
						rs.getInt("wins"), 
						rs.getInt("losses"), 
						rs.getString("divisionWinnner"), 
						rs.getString("leagueWinner"),
						rs.getString("worldSeriesWinnner"), 
						rs.getInt("runs"), 
						rs.getInt("hits"), 
						rs.getInt("homeruns"), 
						rs.getInt("stolenBases"),
						rs.getInt("hitsAllowed"), 
						rs.getInt("homerunsAllowed"), 
						rs.getString("name"), 
						rs.getString("park")  ) );
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}
	
	
	
	
	//=================================================================
	//==================== HELPER FUNCTIONS   =========================
	//=================================================================
	
	
	
	/**
	 * Helper function per leggere le date e gestire quando sono NULL
	 * @param rs
	 * @return
	 */
	private LocalDateTime getBirthDate(ResultSet rs) {
		try {
			if (rs.getTimestamp("birth_date") != null) {
				return rs.getTimestamp("birth_date").toLocalDateTime();
			} else {
				return null;
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * Helper function per leggere le date e gestire quando sono NULL
	 * @param rs
	 * @return
	 */
	private LocalDateTime getDebutDate(ResultSet rs) {
		try {
			if (rs.getTimestamp("debut_date") != null) {
				return rs.getTimestamp("debut_date").toLocalDateTime();
			} else {
				return null;
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * Helper function per leggere le date e gestire quando sono NULL
	 * @param rs
	 * @return
	 */
	private LocalDateTime getFinalGameDate(ResultSet rs) {
		try {
			if (rs.getTimestamp("finalgame_date") != null) {
				return rs.getTimestamp("finalgame_date").toLocalDateTime();
			} else {
				return null;
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * Helper function per leggere le date e gestire quando sono NULL
	 * @param rs
	 * @return
	 */
	private LocalDateTime getDeathDate(ResultSet rs) {
		try {
			if (rs.getTimestamp("death_date") != null) {
				return rs.getTimestamp("death_date").toLocalDateTime();
			} else {
				return null;
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public List<People> getVertici(int anno, int salary){
		String sql="SELECT p.*, SUM(s.salary) AS salaryTot "
				+ "FROM salaries s, people p "
				+ "WHERE s.year=? AND p.playerID=s.playerID "
				+ "GROUP BY p.playerID "
				+ "HAVING salaryTot>?";
		List<People> result = new ArrayList<People>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			st.setInt(2, salary);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				People people= new People(rs.getString("playerID"), 
						rs.getString("birthCountry"), 
						rs.getString("birthCity"), 
						rs.getString("deathCountry"), 
						rs.getString("deathCity"),
						rs.getString("nameFirst"), 
						rs.getString("nameLast"), 
						rs.getInt("weight"), 
						rs.getInt("height"), 
						rs.getString("bats"), 
						rs.getString("throws"),
						getBirthDate(rs), 
						getDebutDate(rs), 
						getFinalGameDate(rs), 
						getDeathDate(rs)) ;
				result.add(people);
			}
			
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public List<ArcoMomentaneo> getCorrelazionePeople(Map<String, People> mappaPeople, int anno){
		String sql="SELECT DISTINCTROW a1.playerID AS player1, a2.playerID AS player2 "
				+ "FROM appearances a1, appearances a2 "
				+ "WHERE a1.`year`=? AND a1.`year`=a2.`year` AND a1.teamCode=a2.teamCode AND a1.playerID!=a2.playerID";
		List<ArcoMomentaneo> result = new ArrayList<>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
		
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				ArcoMomentaneo arco = new ArcoMomentaneo(mappaPeople.get(rs.getString("player1")), mappaPeople.get(rs.getString("player2")));
				result.add(arco);
			}
			
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	//da vedere questa query per come gestire insieme query vertici con query archi 
	
	
	public List<Arco> getEdges_v2(int anno, double salario, Map<String, People> playerIDMap) {
		String sql = "SELECT a1.playerID as pid1, a2.playerID as pid2, a1.teamID "
				+ "FROM appearances a1, appearances a2 "
				+ "WHERE a1.playerID < a2.playerID AND a1.teamID = a2.teamID AND a1.year = a2.year AND a1.year = ? "
				+ "AND a1.playerID IN (SELECT p.PlayerID "
				+ "						FROM people p, salaries s "
				+ "						WHERE p.playerID = s.playerID AND s.year=? "
				+ "						Group By p.playerID "
				+ "						HAVING SUM(s.salary) > ?) "
				+ "AND a2.playerID IN (SELECT p.PlayerID "
				+ "						FROM people p, salaries s "
				+ "						WHERE p.playerID = s.playerID AND s.year=? "
				+ "						Group By p.playerID "
				+ "						HAVING SUM(s.salary) > ?)";
		List<Arco> result = new ArrayList<Arco>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			st.setInt(2, anno);
			st.setDouble(3, salario);
			st.setInt(4, anno);
			st.setDouble(5, salario);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				People player1 = playerIDMap.get(rs.getString("pid1"));
				People player2 = playerIDMap.get(rs.getString("pid2"));
				result.add(new Arco(player1, 
						player2) );
			}
			
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}
	
		
	}

