/**
Copyright (c) 2013 Carnegie Mellon University Silicon Valley. 
All rights reserved. 

This program and the accompanying materials are made available
under the terms of dual licensing(GPL V2 for Research/Education
purposes). GNU Public License v2.0 which accompanies this distribution
is available at http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 

Please contact http://www.cmu.edu/silicon-valley/ if you have any 
questions.
 */
package servlet;

// TODO: Auto-generated Javadoc
/**
 * The Class CreditController.
 */
public class CreditController {
	
	/**
	 * User credit.
	 *
	 * @param user the user
	 * @return the int
	 */
	public int userCredit(String user) {
		int creditLeft = DbAccess.getUserCredit(user);
		return creditLeft;
	}
	
	/**
	 * Remaining credit.
	 *
	 * @param user the user
	 * @param incr_by the incr_by
	 * @param bid the bid
	 * @return the int
	 */
	public int remainingCredit(String user,String incr_by, Integer bid) {
		int creditLeft = DbAccess.updateCredit(user,incr_by,bid); 
		return creditLeft;
	}


}
