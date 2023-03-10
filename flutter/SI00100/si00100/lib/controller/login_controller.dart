import 'package:dio/dio.dart';
import 'package:flutter/material.dart';

import 'package:si00100/service/rest_api.dart';
import 'package:si00100/widget/alert_dialog_widget.dart';
import 'package:si00100/sharePreferences/preferences.dart';
import 'package:si00100/static/static_url.dart';

/// Clase para agregar control REST al login
class LoginController {
  final RestApiDio _rest = RestApiDio();
  final AlertDialogWidget _alertDialogWidget = const AlertDialogWidget();
  bool _isLogin = false;

  /// Login
  // TODO: implement encripcion
  // String basicAUTH='Basic ' + base64Encode(utf8.encode('$user:$password'));
  login(BuildContext context, String user, String password) async {
    _alertDialogWidget.wait(context);
    _isLogin = false;
    Preferences.init();
    _rest.init();

    await Future.delayed(const Duration(milliseconds: 700));

    await _rest
        .getDio()
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
      _alertDialogWidget.hideWait(context);
      _isLogin = true;
    }).catchError((e) {
      _alertDialogWidget.hideWait(context);
      _rest.analyzeError(context, e as DioError);
      _isLogin = false;
    });
  }

  // is login
  bool get isLogin => _isLogin;
}
