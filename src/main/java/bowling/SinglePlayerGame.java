package bowling;

/**
 * Cette classe a pour but d'enregistrer le nombre de quilles abattues lors des
 * lancés successifs d'<b>un seul et même</b> joueur, et de calculer le score
 * final de ce joueur
 */
public class SinglePlayerGame {
    
        private int tourActuel;
        private int score;
        private int firstBoule;
        private int multi[];
        private Tour tour[];
        

	/**
	 * Constructeur
	 */
	public SinglePlayerGame() {
            this.tourActuel = 1;
            this.score = 0;
            this.firstBoule = -1;
            this.multi = new int[2];
            this.multi[0] = 1;
            this.multi[1] = 1;
           // TODO this.tour =
	}
        
        public class Tour{
            public boolean strike;
            public boolean spare;
            public int lancers[];
            
            public Tour(){
                this.strike = false;
                this.spare = false;
                this.lancers[0] = -1;
                this.lancers[1] = -1;
            }
            
            public Tour(boolean st, boolean sp, int pre, int sec){
                this.strike = st;
                this.spare = sp;
                this.lancers[0] = pre;
                this.lancers[1] = sec;
            }
        }

	/**
	 * Cette méthode doit être appelée à chaque lancé de boule
	 *
	 * @param nombreDeQuillesAbattues le nombre de quilles abattues lors de
	 * ce lancé
	 */
	public void lancer(int nombreDeQuillesAbattues) {
            if (this.tourActuel<=10){
                this.score += (nombreDeQuillesAbattues*this.multi[0]);
            } else {
                this.score += nombreDeQuillesAbattues;
            }
            this.multi[0]=this.multi[1];
            this.multi[1]=1;
            if (this.firstBoule!=-1) {
                if (this.firstBoule+nombreDeQuillesAbattues==10){
                    // Spare
                    this.multi[0] += 1;
                }
                this.tourActuel += 1;
                this.firstBoule = -1;
            } else if (nombreDeQuillesAbattues==10 && this.tourActuel<10){
                // Strike
                this.multi[0] += 1;
                this.multi[1] += 1;
                this.tourActuel += 1;
            } else {
                this.firstBoule = nombreDeQuillesAbattues;
            }
	}
        

	/**
	 * Cette méthode donne le score du joueur
	 *
	 * @return Le score du joueur
	 */
	public int score() {
		return this.score;
	}
}
