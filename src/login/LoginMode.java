/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

/**
 *
 * @author Paul
 */
public class LoginMode {
int id;
private String username;
private String password;
int drept;
   public  LoginMode(int indx, String user, String pass, int drpt) {
        id=indx;
        username=user;
        password=pass;
        drept=drpt;
    }
    public String getPwd() {
		return password;
	}

	public void setPwd(String pwd) {
		this.password   = pwd;
	}

//	public String getMsg() {
//		return msg;
//	}

//	public void setMsg(String msg) {
//		this.msg = msg;
//	}

	public String getUser() {
            System.out.println("testUsername de afisare");
		return username;
	}

	public void setUser(String user) {
		this.username = user;
	}
        public int getID(){
            return id;
        }
        public int getPermission(){
        return drept;
        }
        public void afisareUtilizatori(){
            System.out.println("utilizatorul:"+id+", username "+username+", parola "+password+", drept "+drept);
        }
//	//validate login
//	public String validateUsernamePassword() {
//		boolean valid = LoginDAO.validate(user, pwd);
//		if (valid) {
//			HttpSession session = SessionUtils.getSession();
//			session.setAttribute("username", user);
//			return "admin";
//		} else {
//			FacesContext.getCurrentInstance().addMessage(
//					null,
//					new FacesMessage(FacesMessage.SEVERITY_WARN,
//							"Incorrect Username and Passowrd",
//							"Please enter correct username and Password"));
//			return "login";
//		}
//	}
//
//	//logout event, invalidate session
//	public String logout() {
//		HttpSession session = SessionUtils.getSession();
//		session.invalidate();
//		return "login";
//	}
}
