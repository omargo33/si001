import 'package:shared_preferences/shared_preferences.dart';

//class of preferences
// author: o.velez@qapaq.io
class Preferences {
  static late SharedPreferences _preferences;

  // variables
  static String _user = "";
  static bool _isLogin = false;
  static String _token = "";
  static String _tokenRefresh = "";

  // inicialize preferences
  static Future init() async {
    _preferences = await SharedPreferences.getInstance();
    _user = _preferences.getString("user") ?? "";
    _isLogin = _preferences.getBool("isLogin") ?? false;
    _token = _preferences.getString("token") ?? "";
    _tokenRefresh = _preferences.getString("tokenRefresh") ?? "";
  }

  // getters and setters
  static set setUser(String value) {
    _preferences.setString("user", value);
    _user = value;
  }

  static get name {
    return _preferences.getString("user") ?? _user;
  }

  static set setIsLogin(bool value) {
    _preferences.setBool("isLogin", value);
    _isLogin = value;
  }

  static bool get isLogin {
    return _preferences.getBool("isLogin") ?? _isLogin;
  }

  static set setToken(String value) {
    _preferences.setString("token", value);
    _token = value;
  }

  static String get token {
    return _preferences.getString("token") ?? _token;
  }

  static set setTokenRefresh(String value) {
    _preferences.setString("tokenRefresh", value);
    _tokenRefresh = value;
  }

  static get tokenRefresh {
    return _preferences.getString("tokenRefresh") ?? _tokenRefresh;
  }
}
