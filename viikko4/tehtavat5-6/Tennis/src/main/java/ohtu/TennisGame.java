package ohtu;

public class TennisGame {
    
    private int player1Score = 0;
    private int player2Score = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == player1Name) {
            player1Score += 1;
        }
        if (playerName == player2Name) {
            player2Score += 1;
        }
    }

    public String getScore() {        
        if (player1Score >= 4 || player2Score >= 4) {
        	return determineAdvantageOrWin();
        } else {
        	return determineScore();
        }    
    }
    
    private String determineAdvantageOrWin() {
    	int scoreDifference = player1Score - player2Score;
        if (scoreDifference == 0) {
        	return "Deuce";
        } else if (scoreDifference == 1) {
        	return "Advantage " + player1Name;
        } else if (scoreDifference == -1) {
        	return "Advantage " + player2Name;
        } else if (scoreDifference >= 2) {
        	return "Win for " + player1Name;
        } else {
        	return "Win for " + player2Name;
        }
    }
    
    private String determineScore() {
    	String score = getPlayer1Score();
    	score += "-";
    	score += getPlayer2Score();
    	return score;
    }
    
    private String getPlayer1Score() {
    	if (player1Score == 0) {
    		return "Love";
    	} else if (player1Score == 1) {
    		return "Fifteen";
    	} else if (player1Score == 2) {
    		return "Thirty";
    	} else {
    		return "Forty";
    	}
    }
    
    private String getPlayer2Score() {
    	if (player2Score == player1Score) {
    		return "All";
    	} else if (player2Score == 0) {
    		return "Love";
    	} else if (player2Score == 1) {
    		return "Fifteen";
    	} else if (player2Score == 2) {
    		return "Thirty";
    	} else {
    		return "Forty";
    	}
    }
    
    
    
    
}