package bowling;

import java.util.ArrayList;

/**
 * Cette classe a pour but d'enregistrer le nombre de quilles abattues lors des
 * lancés successifs d'<b>un seul et même</b> joueur, et de calculer le score
 * final de ce joueur
 */
public class SinglePlayerGame {
    
        private ArrayList<Tour> tours;
        

	/**
	 * Constructeur
	 */
	public SinglePlayerGame() {
            this.tours = new ArrayList();
	}
        
        public class Tour{
            public boolean strike;
            public boolean spare;
            public int lancers[];
            
            public Tour(){
                this.strike = false;
                this.spare = false;
                this.lancers = new int[2];
                this.lancers[0] = -1;
                this.lancers[1] = -1;
            }
            
            public boolean isEnd(){
                return (this.strike || this.lancers[1]!=-1);
            }
            
            public int score(){
                return this.lancers[0]+this.lancers[1];
            }
            
        }

	/**
	 * Cette méthode doit être appelée à chaque lancé de boule
	 *
	 * @param nombreDeQuillesAbattues le nombre de quilles abattues lors de
	 * ce lancé
	 */
	public void lancer(int nombreDeQuillesAbattues) {
            if (this.tours.isEmpty()){
                this.tours.add(new Tour());
                this.tours.get(0).lancers[0]=nombreDeQuillesAbattues;
                if (nombreDeQuillesAbattues==10){
                    this.tours.get(0).strike=true;
                    this.tours.get(0).lancers[1]=0;
                }
            }
            else if (this.tours.get(this.tours.size()-1).isEnd()){
                this.tours.add(new Tour());
                this.tours.get(this.tours.size()-1).lancers[0]=nombreDeQuillesAbattues;
                if (nombreDeQuillesAbattues==10){
                    this.tours.get(this.tours.size()-1).strike=true;
                    this.tours.get(this.tours.size()-1).lancers[1]=0;
                }
            } else {
                this.tours.get(this.tours.size()-1).lancers[1]=nombreDeQuillesAbattues;
                if (this.tours.get(this.tours.size()-1).lancers[0]+nombreDeQuillesAbattues==10){
                    this.tours.get(this.tours.size()-1).spare=true;
                }
            }
	}
        

	/**
	 * Cette méthode donne le score du joueur
	 *
	 * @return Le score du joueur
	 */
	public int score() {
            int score = 0;
            for (int i=0; i<this.tours.size(); i++){
                if (this.tours.get(i).strike && i<9){
                    if (this.tours.get(i+1).strike){
                        score+= this.tours.get(i).score()+this.tours.get(i+1).score()+this.tours.get(i+2).score();
                    }else{
                        score+= this.tours.get(i).score()+this.tours.get(i+1).score();
                    }
                }
                else if (this.tours.get(i).spare && i<9){
                    score+= this.tours.get(i).score()+this.tours.get(i+1).lancers[0];
                }else{
                    score+= this.tours.get(i).score();
                }
            }
            return score;
	}
}
