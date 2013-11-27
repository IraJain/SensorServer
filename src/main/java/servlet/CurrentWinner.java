package servlet;

public class CurrentWinner {
	 float getWinner(String room,Long start){
		float wintemp = 0;
		
		 
		 try {
			 wintemp = DbAccess.getCurrentWin(room,start);
			
			}
			catch ( Exception e ) {
		        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		        System.exit(0);
		      }
		 return wintemp;
	
		}

}
