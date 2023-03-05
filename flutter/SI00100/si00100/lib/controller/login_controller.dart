import 'package:dio/dio.dart';
import 'package:flutter/cupertino.dart';
import 'package:si00100/sharePreferences/preferences.dart';
import 'package:si00100/static/static_url.dart';

/// Clase para agregar control REST al login
///

class LoginController {
  /// Constructor
  ///
  LoginController();

  final Dio _dio = Dio();

  void configureDio() {
    // Set default configs
    //_dio.options.baseUrl = 'https://api.pub.dev';
    _dio.options.connectTimeout = Duration(seconds: 5);
    _dio.options.receiveTimeout = Duration(seconds: 3);
  }

  // method return integer
  Future<int> login(String user, String password) async {
    // TODO: implement encripcion
    // String basicAUTH = 'Basic ' + base64Encode(utf8.encode('$user:$password'));

    Response response;
    response = await _dio.post(
      StaticUrl.urlAPILogin,
      data: {'username': user, 'password': password},
      options: Options(
        headers: {
          Headers.contentTypeHeader: "application/x-www-form-urlencoded",
        },
      ),
    );

    if (response.statusCode == 200) {
      Preferences.setUser = user;
      Preferences.setToken = response.headers.value('access-token')!;
      Preferences.setIsLogin = true;
      Preferences.setTokenRefresh = response.headers.value('refresh-token')!;
      print('token ' + Preferences.token);
    }

    return 0;
  }

  /// Recuperar clave
  ///
  /// [email] email
  /// [context] contexto
  /// [onSuccess] funcion a ejecutar si el login es exitoso
  /// [onError] funcion a ejecutar si el login es erroneo
  ///
  Future<void> recuperarClave(String email, BuildContext context,
      {Function? onSuccess, Function? onError}) async {
    // TODO implementar recuperar clave
    // SI DA ERROR SE MUESTRA
    //DialogWidget.showError(context, "Error clave", "oportunidades + mensaje del rest");

    // ELSE
    if (onSuccess != null) {
      onSuccess();
    }
  }

  /// Cambiar clave
  ///
  /// [user] usuario
  /// [password] clave
  /// [passwordNew] clave nueva
  /// [passwordConfirm] confirmacion de clave nueva
  /// [context] contexto
  /// [onSuccess] funcion a ejecutar si el login es exitoso
  /// [onError] funcion a ejecutar si el login es erroneo
  ///
  Future<void> cambiarClave(String user, String password, String passwordNew,
      String passwordConfirm, BuildContext context,
      {Function? onSuccess, Function? onError}) async {
    // TODO implementar cambiar clave
    // SI DA ERROR SE MUESTRA
    //DialogWidget.showError(context, "Error clave", "oportunidades + mensaje del rest");

    // ELSE
    if (onSuccess != null) {
      onSuccess();
    }
  }
}
