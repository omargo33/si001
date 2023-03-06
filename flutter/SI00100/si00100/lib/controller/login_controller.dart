import 'package:dio/dio.dart';
import 'package:flutter/cupertino.dart';
import 'package:si00100/pages/widget/alert_dialog.dart';
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
  login(BuildContext context, String user, String password) async {
    var alertDialog = new AlertDialog(
        title: "Espere", description: "Espere por favor", type: "wait");

    alertDialog.wait(context, "sdfadf", "content");
    // wait for 5 seconds
    await Future.delayed(Duration(seconds: 5));

    // TODO: implement encripcion
    // String basicAUTH = 'Basic ' + base64Encode(utf8.encode('$user:$password'));
    Preferences.setUser = "";
    Preferences.setToken = "";
    Preferences.setIsLogin = true;
    Preferences.setTokenRefresh = "";

    await _dio
        .post(
      StaticUrl.urlAPILogin,
      data: {'username': user, 'password': password},
      options: Options(
        headers: {
          Headers.contentTypeHeader: "application/x-www-form-urlencoded",
        },
      ),
    )
        .then((onResponse) async {
      Preferences.setUser = user;
      Preferences.setToken = onResponse.headers.value('access-token')!;
      Preferences.setIsLogin = true;
      Preferences.setTokenRefresh = onResponse.headers.value('refresh-token')!;
      print(onResponse.data);
    }).catchError((e) {
      try {
        print(e);
        print(e.toString());
        DioError dioError = e as DioError;
        print(dioError.error);
        print(dioError.message);
        print(dioError.requestOptions);
        print(dioError.response!);
        print(dioError.response!.statusCode);
        print(dioError.stackTrace);
        print(dioError.type);
      } catch (e) {
        print("nuevos datos de error: " + e.toString());
      }
    });

    alertDialog.hideWait(context);

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
