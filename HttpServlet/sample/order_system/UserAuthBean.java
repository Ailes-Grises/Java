package webApplication27;

public class UserAuthBean {

  /** Creates a new instance of UserAuthBean */
  public UserAuthBean() {
  }

  public boolean execute(UserBean ub) {
    if (ub.getaName().equals("taro") == true && ub.getPassword().equals("taro") == true) {
      return true;
    } else {
      return false;
    }
  }
}
