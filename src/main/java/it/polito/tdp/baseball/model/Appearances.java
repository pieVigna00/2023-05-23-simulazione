package it.polito.tdp.baseball.model;

public class Appearances {

    private Integer iD;
    private Integer year;
    private String teamCode;
    private Integer teamID;
    private String playerID;
    private Integer games;
    private Integer gamesStarted;
    private Integer gamesBatting;
    private Integer gamesDefense;
    private Integer gamesPitcher;
    private Integer gamesCatcher;
    

    /**
     * Costruttore
     * @param iD
     * @param year
     * @param teamCode
     * @param teamID
     * @param playerID
     * @param games
     * @param gamesStarted
     * @param gamesBatting
     * @param gamesDefense
     * @param gamesPitcher
     * @param gamesCatcher
     */
    public Appearances( Integer year, String teamCode, String playerID) {
		super();
		this.year = year;
		this.teamCode = teamCode;
		this.playerID = playerID;
	}
    

	public Integer getID(){
        return iD;
    }

    public void setID(Integer iD){
        this.iD=iD;
    }

    public Integer getYear(){
        return year;
    }

    public void setYear(Integer year){
        this.year=year;
    }

    public String getTeamCode(){
        return teamCode;
    }

    public void setTeamCode(String teamCode){
        this.teamCode=teamCode;
    }

    public Integer getTeamID(){
        return teamID;
    }

    public void setTeamID(Integer teamID){
        this.teamID=teamID;
    }

    public String getPlayerID(){
        return playerID;
    }

    public void setPlayerID(String playerID){
        this.playerID=playerID;
    }

    public Integer getGames(){
        return games;
    }

    public void setGames(Integer games){
        this.games=games;
    }

    public Integer getGamesStarted(){
        return gamesStarted;
    }

    public void setGamesStarted(Integer gamesStarted){
        this.gamesStarted=gamesStarted;
    }

    public Integer getGamesBatting(){
        return gamesBatting;
    }

    public void setGamesBatting(Integer gamesBatting){
        this.gamesBatting=gamesBatting;
    }

    public Integer getGamesDefense(){
        return gamesDefense;
    }

    public void setGamesDefense(Integer gamesDefense){
        this.gamesDefense=gamesDefense;
    }

    public Integer getGamesPitcher(){
        return gamesPitcher;
    }

    public void setGamesPitcher(Integer gamesPitcher){
        this.gamesPitcher=gamesPitcher;
    }

    public Integer getGamesCatcher(){
        return gamesCatcher;
    }

    public void setGamesCatcher(Integer gamesCatcher){
        this.gamesCatcher=gamesCatcher;
    }


	@Override
	public String toString() {
		return "Appearances [year=" + year + ", teamCode=" + teamCode + ", playerID=" + playerID + "]";
	}


}


