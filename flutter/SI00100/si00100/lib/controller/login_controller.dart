import 'package:flutter/cupertino.dart';

/// Clase para agregar control REST al login
///

class LoginController {
  /// Constructor
  ///
  LoginController();

  /// Login
  ///
  /// [user] usuario
  /// [password] clave
  /// [context] contexto
  /// [onSuccess] funcion a ejecutar si el login es exitoso
  /// [onError] funcion a ejecutar si el login es erroneo
  ///
  Future<void> login(String user, String password, BuildContext context,
      {Function? onSuccess, Function? onError}) async {
    // TODO implementar login
    // SI DA ERROR SE MUESTRA
    //DialogWidget.showError(context, "Error clave", "oportunidades + mensaje del rest");

    // ELSE
    if (onSuccess != null) {
      onSuccess();
    }
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
