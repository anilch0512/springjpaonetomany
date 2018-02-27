package netgloo.controllers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import netgloo.models.User;
import netgloo.models.UserDSR;
import netgloo.models.UserDSRError;
import netgloo.models.UserDSRErrorBackUp;
import netgloo.models.UserDao;

/**
 * A class to test interactions with the MySQL database using the UserDao class.
 *
 * @author netgloo
 */
@Controller
public class UserController {

	// ------------------------
	// PUBLIC METHODS
	// ------------------------

	@RequestMapping(value = "/ping")
	@ResponseBody
	public String ping() {
		return "ALIVE" + System.currentTimeMillis();
	}

	/**
	 * Create a new user with an auto-generated id and email and name as passed
	 * values.
	 */
	@RequestMapping(value = "/create")
	@ResponseBody
	public String create(@RequestBody String jsonString) {
		try {
			System.out.println("jsonString..." + jsonString);
			JSONObject jsonObject = new JSONObject();
			JSONParser jsonParser = new JSONParser();
			jsonObject = (JSONObject) jsonParser.parse(jsonString);

			User user = new User();
			user.setCuid("123456");
			user.setUserId("123456");
			user.setCreatedBy("ADMIN");
			user.setUpdatedBy("ADMIN");
			user.setCreateTimestamp(new Timestamp(System.currentTimeMillis()));
			user.setLastUpdateTimestamp(new Timestamp(System.currentTimeMillis()));

			List<UserDSR> userDSRs = new ArrayList<>();
			UserDSR dsr = new UserDSR();
			dsr.setSpid("123456");
			dsr.setPrimaryDlrNum("9999");
			dsr.setUser(user);
			userDSRs.add(dsr);
			user.setUserDSRList(userDSRs);

			List<UserDSRError> userDSRErrors = new ArrayList<>();
			UserDSRError userDSRError = new UserDSRError();
			userDSRError.setSpid("123456");
			userDSRError.setPrimaryDlrNum("9999");
			userDSRError.setUser(user);
			userDSRErrors.add(userDSRError);
			user.setUserDSRErrorList(userDSRErrors);

			List<UserDSRErrorBackUp> userDSRErrorBackUps = new ArrayList<>();
			UserDSRErrorBackUp userDSRErrorBackUp = new UserDSRErrorBackUp();
			userDSRErrorBackUp.setSpid("123456");
			userDSRErrorBackUp.setPrimaryDlrNum("9999");
			userDSRErrorBackUp.setUser(user);
			userDSRErrorBackUps.add(userDSRErrorBackUp);
			user.setUserErrBckUpDSRList(userDSRErrorBackUps);

			userDao.save(user);
			System.out.println("jsonObject.." + jsonObject);

		} catch (Exception ex) {
			ex.printStackTrace();
			return "Error creating the user: " + ex.toString();
		}
		return "User succesfully created!";
	}

	// /**
	// /**
	// * /delete --> Delete the user having the passed id.
	// *
	// * @param id
	// * The id of the user to delete
	// * @return A string describing if the user is successfully deleted or not.
	// */
	// @RequestMapping("/delete")
	// @ResponseBody
	// public String delete(long id) {
	// try {
	// User user = new User(id);
	// userDao.delete(user);
	// } catch (Exception ex) {
	// return "Error deleting the user: " + ex.toString();
	// }
	// return "User successfully deleted!";
	// }
	//
	// /**
	// * /get-by-email --> Return the id for the user having the passed email.
	// *
	// * @param email
	// * The email to search in the database.
	// * @return The user id or a message error if the user is not found.
	// */
	// @RequestMapping("/get-by-email")
	// @ResponseBody
	// public String getByEmail(String email) {
	// String userId;
	// try {
	// User user = userDao.findByEmail(email);
	// userId = String.valueOf(user.getId());
	// } catch (Exception ex) {
	// return "User not found";
	// }
	// return "The user id is: " + userId;
	// }
	//
	// /**
	// * /update --> Update the email and the name for the user in the database
	// * having the passed id.
	// *
	// * @param id
	// * The id for the user to update.
	// * @param email
	// * The new email.
	// * @param name
	// * The new name.
	// * @return A string describing if the user is successfully updated or not.
	// */
	// @RequestMapping("/update")
	// @ResponseBody
	// public String updateUser(long id, String email, String name) {
	// try {
	// User user = userDao.findOne(id);
	// user.setEmail(email);
	// user.setName(name);
	// userDao.save(user);
	// } catch (Exception ex) {
	// return "Error updating the user: " + ex.toString();
	// }
	// return "User successfully updated!";
	// }

	// ------------------------
	// PRIVATE FIELDS
	// ------------------------

	@Autowired
	private UserDao userDao;

} // class UserController
