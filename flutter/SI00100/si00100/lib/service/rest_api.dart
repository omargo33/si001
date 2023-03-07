import 'dart:io';
import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:localization/localization.dart';
import 'package:si00100/pages/widgets.dart';

/// Class to fetch data from REST APIs via DIO api
class RestApiDio {
  final Dio _dio = Dio();
  final AlertDialogWidget _alertDialogWidget = const AlertDialogWidget();

  //Initialize dio
  void init() {
    _dio.options.connectTimeout = const Duration(seconds: 5);
    _dio.options.receiveTimeout = const Duration(seconds: 3);
  }

  /// Method return dio pre configured
  Dio getDio() {
    return _dio;
  }

  /// Method to analyze error output and add show dialog with error
  void analyzeError(BuildContext context, DioError dioError) {
    if (dioError.error is SocketException) {
      _alertDialogWidget.error(
          context, 'alert_net_error'.i18n(), 'alert_net_error_subtitle'.i18n());
    } else if (dioError.response != null) {
      if (dioError.response!.statusCode != null) {
        int statusCode = dioError.response!.statusCode!;
        _alertDialogWidget.error(
            context,
            _evaluarErrorJson(dioError.response!.data),
            "alert_title_http_status_error".i18n(['$statusCode']));
      }
    }
  }

  /// Method to evaluate error json and return string
  String _evaluarErrorJson(Map<String, dynamic> data) {
    String error = data['error'];

    if (error.isEmpty) {
      return 'alert_subtitle_invalid_credentials'.i18n();
    }

    return error.i18n();
  }
}
