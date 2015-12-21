package bowlingCalculator;

public class BowlingCalculator {

	private int score = 0;
	private int scoreInRound = 0;
	private int roundNumber = 1;
	private int rollsInRound = 0;
	private int strikeBonusCounter = 0;
	private boolean strikeBonus = false;
	private boolean spareBonus = false;
	private boolean gameFinished = false;
	private boolean bonusRoll = false;

	final static int MAX_SCORE_IN__ROUND = 10;

	public void roll(int pins) {
		rollsInRound++;
		score += pins;
		scoreInRound += pins;

		addBonus(pins);
		checkBonus();
		
		if ((scoreInRound == MAX_SCORE_IN__ROUND || rollsInRound == 2) && roundNumber <= 9) {
			roundNumber++;
			rollsInRound = 0;
			scoreInRound = 0;
		}
		lastRound();
	}

	private void checkBonus() {
		if ((scoreInRound == MAX_SCORE_IN__ROUND && rollsInRound == 2) && roundNumber < 10) {
			spareBonus = true;
		}
		
		if ((scoreInRound == MAX_SCORE_IN__ROUND && rollsInRound == 1) && roundNumber < 10) {
			strikeBonus = true;
			strikeBonusCounter++;
		}
		
		if (roundNumber >= 10) {
			strikeBonusCounter = 0;
		}
	}

	private boolean addBonus(int pins) {
		if (strikeBonus == false && spareBonus == false) {
			return false;
		}

		score += pins;
		spareBonus = false;

		if (strikeBonusCounter > 1) {
			score += pins;
		}

		if (scoreInRound < MAX_SCORE_IN__ROUND) {
			strikeBonusCounter = 0;
		}

		if (rollsInRound == 2) {
			strikeBonus = false;
		}
		return strikeBonus;
	}

	private void lastRound() {
		if (bonusRoll == true) {
			gameFinished = true;
			bonusRoll = false;
		}

		if (roundNumber == 10 && scoreInRound == MAX_SCORE_IN__ROUND) {
			bonusRoll = true;
		}

		if (roundNumber == 10 && (rollsInRound > 1 && scoreInRound < MAX_SCORE_IN__ROUND)) {
			gameFinished = true;
		}
	}

	public int getFrameNumber() {
		return roundNumber;
	}

	public int getScore() {
		return score;
	}

	public boolean isFinished() {
		return gameFinished;
	}

}
